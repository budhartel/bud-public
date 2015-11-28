package tts;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Languages {
	private  long status;
    private  String[] languages;

    public Languages(String langType) {
        this.status = -1;
        this.languages = listAllVoices();
    }

    public long getStatus() {
        return status;
    }
    public String[] getLangages() {
        return languages;
    }

    private String[] listAllVoices() {
    	System.setProperty("mbrola.base", "src/main/resources/mbrola");      
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();
        String[] langNames = null;
        if(voices.length>0){
        	this.status=0;
        	langNames = new String[voices.length];
        	 for (int i = 0; i < voices.length; i++) {
        		 langNames[i] = voices[i].getName();
             }
        }
       
        return langNames;
    }
}
