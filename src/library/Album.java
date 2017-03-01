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
public class Album {
    
    private ArrayList<Song> songs;
    private String title;
    private String artist;
    
    public Album(String title, String artist){
        this.songs = new ArrayList<>();
        this.title = title;
        this.artist = artist;        
    }
    
    public int getTrackCount(){
        return this.songs.size();
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setTitle(String title){
        this.title=title;
    }
    
    public String getArtist(){
        return this.artist;
    }
    
    public void setArtist(String artist){
        this.artist = artist;
    }
    
    public Song[] getAllSongs(){
        return this.songs.toArray(new Song[this.songs.size()]);
    }
    
    public Song getSongByTrackNumber(int trackNumber){
        return this.songs.get(trackNumber - 1);
    }
    
    public void deleteSong(Song song){
        this.songs.remove(song);
    }
    
    public void addSong(Song song){
       this.songs.add(song);
    }
    
}
