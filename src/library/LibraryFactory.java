/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

/**
 *
 * @author pauln
 */
public class LibraryFactory {
    
    
    public Library getLibrary(){
        return this.getDemoLibrary();
    }
    
    public Library getLibrary(String libraryName){
        return this.getDemoLibrary();
    }
    
    
    private Library getDemoLibrary(){
        Library testLib = new Library();
        
        Album testAlbum = new Album("Test Album", "Various Artist");
        testLib.addAlbum(testAlbum);
        testAlbum.addSong(new Song("Test Song 1", "Madonaa", 120,0,0,120));
        testAlbum.addSong(new Song("Test Song 2", "Iggy", 180, 0, 120, 240));
        
        Album testAlbum2 = new Album("Test Alubm 2", "DJ Sammy");
        testLib.addAlbum(testAlbum2);
        testAlbum2.addSong(new Song("Test Song A", "DJ Sammy", 180, 0, 0, 140));
        testAlbum2.addSong(new Song("Test SOng B", "DJ Sammy", 190, 0, 140, 280));
        
        return testLib;
    }
}
