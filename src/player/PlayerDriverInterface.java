/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import library.Album;

/**
 *
 * @author pauln
 */
public interface PlayerDriverInterface extends Runnable {
    
    public void loadAlbum(Album album);
    
    public void eject();
    
    public void playTrack(int trackNumber);
    
    public void pause();
    
    public void resume();
    
    public void stop();
    
    public void setListner(PlayerDriverListnerInterface listner);
}
