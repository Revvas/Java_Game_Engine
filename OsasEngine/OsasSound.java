package OsasEngine;

import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 
public class OsasSound implements AutoCloseable {
	private boolean released = false;
	private AudioInputStream stream = null;
	private Clip clip = null;
	private FloatControl volumeControl = null;
	private boolean playing = false;
	
	public OsasSound(String path) {
		File f = new File(path);
		try {
			stream = AudioSystem.getAudioInputStream(f);
			clip = AudioSystem.getClip();
			clip.open(stream);
			clip.addLineListener(new Listener());
			volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			released = true;
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
			exc.printStackTrace();
			released = false;
			
			close();
		}
	}
 
	// true if download, false if not
	public boolean isReleased() {
		return released;
	}
	
	// play now?
	public boolean isPlaying() {
		return playing;
	}
 
	// Start
	/*
	  breakOld if play
	  if breakOld==true, retry play
	  else nothing
	*/
	public void play(boolean breakOld) {
		if (released) {
			if (breakOld) {
				clip.stop();
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			} else if (!isPlaying()) {
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			}
		}
	}
	
	// play(true)
	public void play() {
		play(true);
	}
	
	// stop
	public void stop() {
		if (playing) {
			clip.stop();
		}
	}
	
	public void close() {
		if (clip != null)
			clip.close();
		
		if (stream != null)
			try {
				stream.close();
			} catch (IOException exc) {
				exc.printStackTrace();
			}
	}
 
	// wait finish play
	public void join() {
		if (!released) return;
		synchronized(clip) {
			try {
				while (playing)
					clip.wait();
			} catch (InterruptedException exc) {}
		}
	}
	
	// static for comfort
	public static OsasSound playSound(String f) {
		OsasSound snd = new OsasSound(f);
		

		snd.play();
		return snd;
	}
 
	private class Listener implements LineListener {
		public void update(LineEvent ev) {
			if (ev.getType() == LineEvent.Type.STOP) {
				playing = false;
				synchronized(clip) {
					clip.notify();
				}
			}
		}
	}
}