/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import library.Album;
import library.Song;

/**
 *
 * @author pauln
 */
public interface PlayerListnerInterface {
    
    public void playerAudioData(byte[] pcmData);
    
    public void playerEventAlbumLoading(Album album);
    
    public void playerEventAlbumLoaded(Album alubm);
    
    public void playerEventSeekingSong(Album album, int trackNumber);
    
    public void playerEventSongStarted(Album album, int trackNumber);    
    
    public void playerEvenetSongProgress(Album album, int trackNumber, int songPosInSeconds);
       
    public void playerEventSongPaused(Album album, int trackNumber);
    
    public void playerEventStopping(Album album);
    
    public void playerEventStopped(Album album);
    
    public void playerEventEjecting(Album album);
    
    public void playerEventNoAlbumPresent();    
    
}
