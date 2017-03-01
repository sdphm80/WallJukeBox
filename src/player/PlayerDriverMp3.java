/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import library.Album;
import library.Song;

/**
 *
 * @author pauln
 */
public class PlayerDriverMp3 implements PlayerDriverInterface, BasicPlayerListener {

    private BasicPlayer bp;
    private final String mp3LibraryFolder;
    private Album album;
    private String albumFilePath;
    private int currentTrack;
    private PlayerDriverListnerInterface listner;

    public PlayerDriverMp3(String mp3LibraryFolder) {
        /* Validate MP3 Library Folder */
        File f = new File(mp3LibraryFolder);
        if (!f.exists() || !f.isDirectory()) {
            throw new RuntimeException("MP3 Library Directory " + mp3LibraryFolder + " is not valid");
        }

        /* Store Variables */
        BasicPlayerFactory bpFactory = new BasicPlayerFactory();
        this.bp = bpFactory.getPlayer();
        this.bp.addBasicPlayerListener(this);
        this.mp3LibraryFolder = mp3LibraryFolder;
        this.currentTrack = 1;
        this.album = null;
        this.albumFilePath = null;
    }

    @Override
    public void run() {
    }

    @Override
    public void loadAlbum(Album album) {
        this.album = album;
        this.albumFilePath = album.getTitle();
        demoPause(1);
        
    }

    @Override
    public void eject() {
        this.album = null;
        this.currentTrack = 0;
        demoPause(1);
    }

    @Override
    public void playTrack(int trackNumber) {
        demoPause(1);
        if (this.album == null) {
            return;
        }
        this.currentTrack = trackNumber;

        Song song =  this.album.getSongByTrackNumber(trackNumber);
        String filePath = mp3LibraryFolder + "\\" + albumFilePath + "\\" + trackNumber + " - " + song.getTitle() + ".mp3";
        File f = new File(filePath);

        try {
            bp.open(f);
            bp.play();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayerDriverMp3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pause() {
        demoPause(1);
        try {
            bp.pause();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayerDriverMp3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void resume() {
        demoPause(1);
        try {
            bp.resume();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayerDriverMp3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void stop() {
        demoPause(1);
        try {
            bp.stop();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayerDriverMp3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setListner(PlayerDriverListnerInterface listner) {
        this.listner = listner;
    }
    
    private void demoPause(int durationInSeconds){
        /*try {
            Thread.sleep(durationInSeconds * 100);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayerDriverMp3.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @Override
    public void opened(Object o, Map map) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void progress(int bytesRead, long microSeconds, byte[] bytes, Map map) {
        int songProgress = (int) Math.round(microSeconds / 1000000);
        this.listner.driverDataUpdate(songProgress, bytes);
    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
        System.out.println(bpe.toString());
    }

    @Override
    public void setController(BasicController bc) {
        System.out.println("Set basic Controller!?!?!?!?");
    }

}
