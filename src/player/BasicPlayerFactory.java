/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.util.Iterator;
import java.util.List;
import javazoom.jlgui.basicplayer.BasicPlayer;

/**
 *
 * @author pauln
 */
public class BasicPlayerFactory {

    public BasicPlayer getPlayer() {
        BasicPlayer bp = new BasicPlayer();
        List mixers = bp.getMixers();
        if (mixers == null) {
            throw new RuntimeException("Mixer not found");
        }

        // Use first mixer available.
        Iterator it = mixers.iterator();
        if (it.hasNext()) {
            String mixer = (String) it.next();
            bp.setMixerName(mixer);
        }
        return bp;
    }
}
