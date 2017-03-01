/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.ArrayList;

/**
 *
 * @author pauln
 */
public class Library {
    
    private final ArrayList<Album> albums;
    
    public Library(){
        this.albums = new ArrayList<>();
    }

    public Album findAlbumById(int id){
        return this.albums.get(id);
    }
    
    public Album[] getAllAlbums(){
        return this.albums.toArray(new Album[this.albums.size()]);
    }
    
    public Album getAlbumById(int id){
        return this.albums.get(id);
    }
    
    public void addAlbum(Album album){
        this.albums.add(album);
    }
    
    public void deleteAlbum(Album album){
        this.albums.remove(album);
    }
    
}
