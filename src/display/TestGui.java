/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import library.Album;
import player.PlayerListnerInterface;
import player.Player;

/**
 *
 * @author pauln
 */
public class TestGui implements PlayerListnerInterface {
    
    private JFrame frame;
    private JLabel timeLabel;
    private Player player;
    
    
    public TestGui(Player player){
        this.player = player;
        
        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("JukeBox GUI!");
        frame.setSize(300,250);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        /* Play Button */
        JButton playBtn = new JButton("Play");
        panel.add(playBtn);
        playBtn.setBounds(90, 5, 80, 30);
        playBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                player.play();
            }
        });
        
        
        /* Pause Button */
        JButton pauseBtn = new JButton("Pause");
        panel.add(pauseBtn);
        pauseBtn.setBounds(90,45,80,30);
        pauseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                player.pause();
            }
        }
        );
        
        /* Back Button */
        JButton backBtn = new JButton("Back");
        panel.add(backBtn);
        backBtn.setBounds(5,20,80,30);
        backBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                player.skipBack();
            }
        });
        
        
        /* Skip Button */
        JButton skipBtn = new JButton("skip");
        panel.add(skipBtn);
        skipBtn.setBounds(180,20,80,30);
        skipBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                player.skipFoward();
            }
        });
        
        /* Status Text */
        timeLabel = new JLabel("Timer");
        panel.add(timeLabel);
        timeLabel.setBounds(3, 190, 250, 20);
        
        
        
        panel.setVisible(true);
        
        
        frame.add(panel);
        frame.setVisible(true);
        
        
    }

    @Override
    public void playerAudioData(byte[] pcmData) {
        
    }

    @Override
    public void playerEventAlbumLoading(Album album) {
        timeLabel.setText("Loading " + album.getTitle());
    }

    @Override
    public void playerEventAlbumLoaded(Album album) {
        timeLabel.setText("Loaded! " + album.getTitle());
    }

    @Override
    public void playerEventSeekingSong(Album album, int trackNumber) {
        timeLabel.setText("Seeking Song... " + trackNumber);
    }

    @Override
    public void playerEventSongStarted(Album album, int trackNumber) {
        timeLabel.setText("Song Started " + trackNumber);
    }

    @Override
    public void playerEvenetSongProgress(Album album, int trackNumber, int songPosInSeconds) {
        int totalTracks = album.getTrackCount();
        timeLabel.setText("Track " + trackNumber + " of " + totalTracks + ": " + songPosInSeconds);
    }

    @Override
    public void playerEventSongPaused(Album album, int trackNumber) {
        timeLabel.setText("Paused");
    }

    @Override
    public void playerEventStopping(Album album) {
        timeLabel.setText("Stopping");
    }

    @Override
    public void playerEventStopped(Album album) {
        timeLabel.setText("Stopped");
    }

    @Override
    public void playerEventEjecting(Album album) {
        timeLabel.setText("Ejecting");
    }

    @Override
    public void playerEventNoAlbumPresent() {
        timeLabel.setText("No Album Loaded");
    }

}
