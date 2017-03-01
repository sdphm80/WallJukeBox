/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pauln
 */
public class Album {

    private final ArrayList<Song> songs = new ArrayList<>();
    private String title;
    private String artist;
    private int wallRow;
    private int wallCol;
    private final String albumDir;

    public Album(String title, String artist, int wallRow, int wallCol, String libraryDir) throws Exception {
        File albumDirO = new File(libraryDir + "/" + title);
        if (!albumDirO.exists() || !albumDirO.isDirectory()){
            throw new Exception("albuDir doesn't exist " + albumDirO.getCanonicalPath());
        }
        
        this.title = title;
        this.artist = artist;
        this.wallRow = wallRow;
        this.wallCol = wallCol;
        this.albumDir = albumDirO.getCanonicalPath();
    }

    public int getWallRow() {
        return wallRow;
    }

    public void setWallRow(int wallRow) {
        this.wallRow = wallRow;
    }

    public int getWallCol() {
        return wallCol;
    }

    public void setWallCol(int wallCol) {
        this.wallCol = wallCol;
    }
    

    public int getTrackCount() {
        return this.songs.size();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Song[] getSongs() {
        return this.songs.toArray(new Song[this.songs.size()]);
    }

    public Song getSongByTrackNumber(int trackNumber) {
        return this.songs.get(trackNumber - 1);
    }

    public void deleteSong(Song song) {
        this.songs.remove(song);
    }

    public Song addSong(String title, String artist, String file) throws Exception {
        if ("".equals(artist)) {
            artist = this.artist;
        }
        
        Song song = new Song(this, title, artist, file, this.albumDir);
        this.songs.add(song);
        return song;
    }

}
