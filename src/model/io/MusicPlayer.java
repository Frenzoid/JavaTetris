package model.io;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

	public static void play(String filePath) {
		new Thread(() -> {
			final JFXPanel fxPanel = new JFXPanel();
			Media hit = new Media(new File(filePath).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(hit);
			mediaPlayer.play();
		}).start();
	}
	
}
