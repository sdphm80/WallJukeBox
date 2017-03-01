/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.Album;
import library.Song;

/**
 *
 * @author pauln
 */
public class Player implements PlayerDriverListnerInterface {

    private static final int PLAYING_STATE_STOPPED = 0;
    private static final int PLAYING_STATE_PLAYING = 1;
    private static final int PLAYING_STATE_PAUSED = 2;

    private final PlayerDriverInterface driver;

    private ArrayList<PlayerListnerInterface> listners;
    private Album currentAlbum;
    private int currentTrackNum;
    private int currentTotalTracks;
    private Song currentSong;
    private int currentSongProgressInSeconds = 0;
    private boolean autoPlayOnLoad;
    private int playingState;

    public Player(PlayerDriverInterface driver) {
        this.driver = driver;
        driver.setListner(this);
        listners = new ArrayList<>();

        currentAlbum = null;
        currentTrackNum = 0;
        autoPlayOnLoad = true;
        playingState = PLAYING_STATE_STOPPED;
    }

    public void load(Album album) {

        /* Eject album if there already is one */
        if (currentAlbum != null) {
            this.eject();
        }

        /* Notify Listners album is loading */
        listners.forEach((listner) -> {
            listner.playerEventAlbumLoading(album);
        });
        driver.loadAlbum(album);
        currentTotalTracks = album.getTrackCount();

        /* Notify Listners album is loaded */
        currentTrackNum = 0;
        currentAlbum = album;
        listners.forEach((listner) -> {
            listner.playerEventAlbumLoaded(album);
        });

        /* Run autoplay if applicable */
        if (autoPlayOnLoad) {
            play();
        }
    }

    public void eject() {

        /* Skip Ejecting if no album is loaded */
        if (currentAlbum == null) {
            return;
        }

        /* Stop playback if applicable */
        stop();

        /* Notify listerners of ejecting */
        listners.forEach((listner) -> {
            listner.playerEventEjecting(currentAlbum);
        });
        currentAlbum = null;
        currentTrackNum = 0;
        currentTotalTracks = 0;
        driver.eject();

        /* Notify listners of no album status */
        listners.forEach((listner) -> {
            listner.playerEventNoAlbumPresent();
        });

    }

    public void play() {

        /* Make sure an album is loaded */
        if (currentAlbum == null) {
            return;
        }

        switch (playingState) {
            case PLAYING_STATE_STOPPED:
                playTrack(1);
                break;
            case PLAYING_STATE_PLAYING:
                break;
            case PLAYING_STATE_PAUSED:
                resume();
                break;
        }

    }

    public void playTrack(int trackNumber) {
        /* Make sure an album is loaded */
        if (currentAlbum == null) {
            return;
        }

        /* Notify listners of track seek */
        listners.forEach((listner) -> {
            listner.playerEventSeekingSong(currentAlbum, trackNumber);
        });
        driver.playTrack(trackNumber);
        this.currentTrackNum = trackNumber;

        /* Notify listners track is playing */
        playingState = PLAYING_STATE_PLAYING;
        listners.forEach((listner) -> {
            listner.playerEventSongStarted(currentAlbum, trackNumber);
        });

    }

    private void resume() {
        if (playingState != Player.PLAYING_STATE_PAUSED) {
            return;
        }

        driver.resume();
        playingState = Player.PLAYING_STATE_PLAYING;
        listners.forEach((listner) -> {
            listner.playerEventSongStarted(currentAlbum, currentTrackNum);
        });
    }

    public void pause() {
        if (playingState != Player.PLAYING_STATE_PLAYING) {
            return;
        }

        driver.pause();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        playingState = Player.PLAYING_STATE_PAUSED;
        listners.forEach((listner) -> {
            listner.playerEventSongPaused(currentAlbum, currentTrackNum);
        });
    }

    public void stop() {
        if (playingState == Player.PLAYING_STATE_STOPPED) {
            return;
        }

        /* Notify listerners stopping is about to happen */
        listners.forEach((listner) -> {
            listner.playerEventStopping(currentAlbum);
        });

        /* Do Stop */
        driver.stop();
        playingState = Player.PLAYING_STATE_STOPPED;
        listners.forEach((listner) -> {
            listner.playerEventStopped(currentAlbum);
        });

    }

    public void skipFoward() {
        System.out.println("Skip Foward - " + currentTrackNum + "of" + currentTotalTracks);
        if (currentTrackNum < currentTotalTracks) {
            this.playTrack(currentTrackNum + 1);
        } else {
            this.stop();
        }
    }

    public void skipBack() {
        if (currentTrackNum == 0) {
            return;
        }

        if (currentTrackNum == 1) {
            this.playTrack(1);
            return;
        }

        /* If less than 3 seoncs has gone by, skip back 1 track */
        if (this.currentSongProgressInSeconds < 3) {
            playTrack(currentTrackNum - 1);

            /* Otherwise start the current track over */
        } else {
            playTrack(currentTrackNum);
        }
    }

    public void registerListner(PlayerListnerInterface listner) {
        listners.add(listner);
    }

    public void unRegsiterListner(PlayerListnerInterface listner) {
        listners.remove(listner);
    }

    @Override
    public void driverDataUpdate(int songProgressInSeconds, byte[] pcmData) {

        /* Notify listners of song progress if seconds ticked by */
        if (songProgressInSeconds != this.currentSongProgressInSeconds) {
            this.currentSongProgressInSeconds = songProgressInSeconds;
            listners.forEach((listner) -> {
                listner.playerEvenetSongProgress(currentAlbum, this.currentTrackNum, songProgressInSeconds);
            });
        }
        
        
        /* Notfy lisnters of new song Data */
        listners.forEach((listner) -> {
                listner.playerAudioData(pcmData);
            });
    }

    @Override
    public void driverSongFinished() {
        this.skipFoward();
    }
}
