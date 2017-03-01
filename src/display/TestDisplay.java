/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;
import library.Album;
import player.PlayerListnerInterface;

/**
 *
 * @author pauln
 *//**
 *
 * @author pauln
 */
public class TestDisplay implements PlayerListnerInterface {

    @Override
    public void playerAudioData(byte[] pcmData) {
        
    }

    @Override
    public void playerEventAlbumLoading(Album album) {
        System.out.println("Loading Alubm");
    }

    @Override
    public void playerEventAlbumLoaded(Album alubm) {
        System.out.println("Album Loaded");
    }

    @Override
    public void playerEventSeekingSong(Album album, int trackNumber) {
        System.out.println("Seeking Song");
    }

    @Override
    public void playerEventSongStarted(Album album, int trackNumber) {
        System.out.println("Song Started");
    }

    @Override
    public void playerEvenetSongProgress(Album album, int trackNumber, int songPosInSeconds) {
        System.out.println("Song Progress " + songPosInSeconds);
    }

    @Override
    public void playerEventSongPaused(Album album, int trackNumber) {
        System.out.println("Song Paused");
    }

    @Override
    public void playerEventStopping(Album album) {
        System.out.println("Stopping");
    }

    @Override
    public void playerEventStopped(Album album) {
        System.out.println("Stopped");
    }

    @Override
    public void playerEventEjecting(Album album) {
        System.out.println("Ejecting");
    }

    @Override
    public void playerEventNoAlbumPresent() {
        System.out.println("Empty - No Alubm");
    }
    
}
