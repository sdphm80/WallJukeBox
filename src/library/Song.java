/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.File;

/**
 *
 * @author pauln
 */
public class Song {
    
    private final Album album;
    private String title;
    private String artist;
    private File file;
    
    /**
     *
     * @param album
     * @param title
     * @param artist
     * @param fileName
     * @param albumDir
     * @throws java.lang.Exception
     */
    public Song(Album album, String title, String artist, String fileName, String albumDir) throws Exception{
        File file = new File (albumDir + "/" + fileName);
        if (!file.exists() || !file.isFile()){
            throw new Exception("Can't find music file " + file.getCanonicalPath());
        }
        
        this.album = album;
        this.title = title;
        this.artist = artist;
        this.file = file;
    }
    
    public Album getAlbum(){
        return this.album;
    }

  
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    public File getFile(){
        return file;
    }

   
}
