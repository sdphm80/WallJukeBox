/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

/**
 *
 * @author pauln
 */
public interface PlayerDriverListnerInterface {
    
    public void driverDataUpdate(int songProgressInSeconds, byte[] pcmData);
    
    public void driverSongFinished();
}
