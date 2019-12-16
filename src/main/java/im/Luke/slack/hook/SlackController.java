package im.Luke.slack.hook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

@RestController
public class SlackController {
	
	@GetMapping("/slackTest")
	public void webHook() {
		
		System.out.println("======================================");
		SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
		api.call(new SlackMessage("Hook pratice"));
		System.out.println("======================================");
		
	}

}
