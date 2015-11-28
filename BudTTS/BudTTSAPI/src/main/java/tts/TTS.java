package tts;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat.Type;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTS {
	private String sayString;
	private String sayVoice;
	private AudioOutput outAudio;
	
	public TTS(String say, String voice){
		this.sayString = say;
		this.sayVoice = voice;
		loadAudio();
	}
	
	private synchronized void loadAudio()	{

		System.setProperty("mbrola.base", "src/main/resources/mbrola");		
		SingleStreamAudioPlayer audioPlayer = null;
		AudioOutput myAudio = null;
		//System.out.println();
		//System.out.println("Using voice: " + sayVoice);
		
		 /* The VoiceManager manages all the voices for FreeTTS.
		  */
		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice helloVoice = voiceManager.getVoice(this.sayVoice);
		
		if (helloVoice == null) {
		    System.err.println(
		        "Cannot find a voice named "
		+ this.sayVoice + ".  Please specify a different voice.");
		// System.exit(1);
		}else{
			try{
				helloVoice.allocate();
				
				 /* Synthesize speech.
				  */
				//create a audioplayer to dump the output file
				audioPlayer = new SingleStreamAudioPlayer("C://output",Type.WAVE);
				 //attach the audioplayer 
				helloVoice.setAudioPlayer(audioPlayer);
				
				
				
				 helloVoice.speak(this.sayString);
				
				
				 /* Clean up and leave.
				  */
				helloVoice.deallocate();
				//don't forget to close the audioplayer otherwise file will not be saved
				audioPlayer.close();
				myAudio = audioPlayer.getAudioOutput();
//				ByteArrayOutputStream os = new ByteArrayOutputStream();
//				 is.a
//				int bCount = IOUtils.copy(is, os);
//			   os.flush();
//			    //os.close();
//				IOUtils.closeQuietly(os);
//
//			    myAudio = os.toByteArray();
				
			}catch (Exception ioe){
				ioe.printStackTrace();
			}
			
		}
		
		 /* Allocates the resources for the voice.
		 */
		
		
		this.outAudio = myAudio;
	}
	
	public AudioOutput getOutAudio(){
		    return this.outAudio;
	}

}
