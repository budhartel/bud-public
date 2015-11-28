package tts;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TTSController {

	
	@RequestMapping(value="/process", method=RequestMethod.GET, produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
	public ResponseEntity<byte[]> sayit2(@RequestParam(value="INPUT_TEXT", defaultValue="nothing to say") String sayString,
			@RequestParam(value="INPUT_TYPE", defaultValue="TEXT") String sayType,
			@RequestParam(value="AUDIO", defaultValue="WAVE_FILE") String audioFormat,
			@RequestParam(value="OUTPUT_TYPE", defaultValue="AUDIO") String outPutType,
			@RequestParam(value="LOCAL", defaultValue="mbrola_us2") String voice){
		TTS tts = new TTS(sayString,voice);
		
		HttpHeaders rh = new HttpHeaders();
		rh.setContentType(new MediaType("audio","vnd.wav"));
		rh.setContentLength(tts.getOutAudio().getFileSize());
		rh.setContentDispositionFormData("attachment", "output.wav");
		rh.add("Cache-Control", "no-cache, no-store, must-revalidate");
		rh.add("Pragma", "no-cache");
		rh.add("Expires", "0");

		return new ResponseEntity<byte[]>(tts.getOutAudio().getAudioBytes(),rh,HttpStatus.OK);

	}

	@RequestMapping("/locales")
	public Languages sayit(@RequestParam(value="langType", defaultValue="5") String localLang){
		
		Languages lang = new Languages(localLang);
		return lang;
		
	}
}
