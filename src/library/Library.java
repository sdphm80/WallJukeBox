/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author pauln
 */
public class Library {

    private final ArrayList<Album> albums = new ArrayList<>();

    private final String libDir;

    public Library(String libraryDirectory) throws Exception {

        /* Validate library directory exists */
        File libDirO = new File(libraryDirectory);
        if (!libDirO.exists() || !libDirO.isDirectory()) {
            throw new Exception("Library directory " + libraryDirectory + " doesn't exist or isn't a directory");
        }
        this.libDir = libDirO.getCanonicalPath();  
    }

    public Album[] getAllAlbums() {
        return this.albums.toArray(new Album[this.albums.size()]);
    }

    public Album addAlbum(String title, String artist, int wallRow, int wallCol) throws Exception {
        Album alubm = new Album(title, artist, wallRow, wallCol, this.libDir);
        this.albums.add(alubm);
        return alubm;
    }

    public void deleteAlbum(Album album) {
        this.albums.remove(album);
    }

}
