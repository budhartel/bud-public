package tts;

import java.io.InputStream;

public class AudioOutput {
	private String name;
	private String type;
	private InputStream audioStream;
	private byte[] audioBytes;
	private int fileSize;
	
	
	public AudioOutput(String name, String type, InputStream audioStream,
			int fileSize, byte[] aBytes) {
		super();
		this.name = name;
		this.type = type;
		this.audioStream = audioStream;
		this.fileSize = fileSize;
		this.audioBytes = aBytes;
	}


	public String getName() {
		return name;
	}


	public String getType() {
		return type;
	}


	public InputStream getAudioStream() {
		return audioStream;
	}


	public byte[] getAudioBytes() {
		return audioBytes;
	}


	public int getFileSize() {
		return fileSize;
	}
	
	
	
	
}
