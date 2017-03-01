/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author pauln
 */
public class LibraryRepoJson {

    private final String libDir;
    private final Library library;
    private boolean isLoaded = false;

    public LibraryRepoJson(String libraryDirectory) throws Exception {
        /* Validate library directory exists */
        File libDir = new File(libraryDirectory);
        if (!libDir.exists() || !libDir.isDirectory()) {
            throw new Exception("Library directory " + libraryDirectory + " doesn't exist or isn't a directory");
        }
        this.libDir = libDir.getCanonicalPath();
        this.library = new Library(this.libDir);
    }

    /**
     *
     * @return @throws java.lang.Exception
     */
    public Library load() throws Exception {
        if (!isLoaded) {
            loadAlbums();
            this.isLoaded = true;
        }
        return this.library;
    }

    private void loadAlbums() throws Exception {
        /* Validate library json file exists */
        File jsonFile = new File(this.libDir + "/library.json");
        if (!jsonFile.exists() || !jsonFile.isFile()) {
            throw new Exception("Missing the library.json file expected at " + jsonFile.getCanonicalFile());
        }

        /* Read and parse Json Library */
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonLibrary = (JSONObject) jsonParser.parse(new FileReader(jsonFile));
        JSONArray albums = (JSONArray) jsonLibrary.get("albums");
        

        for (Object a : albums) {
            JSONObject jsonAlbum = (JSONObject) a;

            String title = (String) jsonAlbum.get("title");
            String artist = (String) jsonAlbum.get("artist");
            int wallRow = Integer.parseInt(jsonAlbum.get("wallRow").toString());
            int wallCol = Integer.parseInt(jsonAlbum.get("wallCol").toString());
            JSONArray songs = (JSONArray) jsonAlbum.get("songs");

            Album album = this.library.addAlbum(title, artist, wallRow, wallCol);
            loadSongs(songs, album);
        }
    }

    private void loadSongs(JSONArray songs, Album album) throws Exception {
        for (Object s : songs) {
            JSONObject jsonSong = (JSONObject) s;
            
            String title = (String) jsonSong.get("title");
            String artist = (String) jsonSong.get("artist");
            String file = (String) jsonSong.get("file");
            
            album.addSong(title, artist, file);
        }
    }

}
