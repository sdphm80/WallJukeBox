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
public class Song {
    
    private int trackNumber;
    private String title;
    private String artist;
    private int songLengthInSeconds;
    private int recordSide;
    private int recordStart;
    private int recordStop;
    
    /**
     *
     * @param title
     * @param artist
     * @param recordSide
     * @param recordStart
     * @param recordStop
     */
    public Song(String title, String artist, int songLengthInSeconds, int recordSide, int recordStart, int recordStop){
        this.title = title;
        this.artist = artist;
        this.songLengthInSeconds = songLengthInSeconds;
        this.recordSide = recordSide;
        this.recordStart = recordStart;
        this.recordStop = recordStop;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
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

    public int getSongLengthInSeconds() {
        return songLengthInSeconds;
    }

    public void setSongLengthInSeconds(int songLengthInSeconds) {
        this.songLengthInSeconds = songLengthInSeconds;
    }

    public int getRecordSide() {
        return recordSide;
    }

    public void setRecordSide(int recordSide) {
        this.recordSide = recordSide;
    }

    public int getRecordStart() {
        return recordStart;
    }

    public void setRecordStart(int recordStart) {
        this.recordStart = recordStart;
    }

    public int getRecordStop() {
        return recordStop;
    }

    public void setRecordStop(int recordStop) {
        this.recordStop = recordStop;
    }
    
    
}
