/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package walljukebox;
import library.LibraryFactory;
import library.Library;
import library.Album;
import player.Player;
import player.PlayerDriverInterface;
import player.PlayerDriverMp3;
import display.TestGui;
import display.TestGui2;

/**
 *
 * @author pauln
 */
public class WallJukeBox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LibraryFactory libFactory = new LibraryFactory();
        Library lib = libFactory.getLibrary();
        
        
        /* Start the PlayDriver in a thread */
        PlayerDriverInterface driver = new PlayerDriverMp3("C:\\Users\\pauln\\Music");
        Thread playDriverThread = new Thread(driver);
        playDriverThread.start();
        
        /* Start the Player Controller */
        Player playController = new Player(driver);
        
        /* Start Display Output & Regsister */
        //TestDisplay display = new TestDisplay();
        //playController.registerListner(display);
        
        TestGui guiDisplay = new TestGui(playController);
        playController.registerListner(guiDisplay);
        
        TestGui2 testGui = new TestGui2();
        testGui.show();
        testGui.setPlayer(playController);
        playController.registerListner(testGui);
        
        /* Play test album */
        Album album = lib.getAlbumById(0);
        playController.load(album);
        
                
    }
    
}
