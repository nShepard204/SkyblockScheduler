package com.example.skyscheduler;

import com.example.skyscheduler.Controllers.HypixelController;
import com.example.skyscheduler.Objects.SkyblockTime;
import com.example.skyscheduler.Utilities.TimeUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Instant;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class SkyblockScheduler {
	@Value("${mailgun.api.key}")
	private String mailgunApiKey;
	@Value("${hypixel.api.key}")
	private String hypixelApiKey;
	@Value("${mailgun.domain.sandbox}")
	private String mailgunDevDomain;
	@Value("${hypixel.priv.uuid}")
	private String shepUuid;

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	HypixelController hypixelController;
	@Autowired
	TimeUtils sbTime;

	Logger logger = Logger.getGlobal();
	public static void main(String[] args) {
		SpringApplication.run(SkyblockScheduler.class, args);
	}

	@GetMapping("/")
	public String landingPage(){
		SkyblockTime time = new SkyblockTime();

		logger.info(time.fromInstant(Instant.now()).toString());
		//logger.info(hypixelTest());
		return "Hello World!";
	}

	public JsonNode sendEmail() throws UnirestException {
		HttpResponse<JsonNode> request = Unirest.post(String.format("https://api.mailgun.net/v3/%s/messages", this.mailgunDevDomain))
				.basicAuth("api", this.mailgunApiKey)
				.queryString("from", "Skyblock Scheduler <skyblock@scheduler.com>")
				.queryString("to", "nShepard204@gmail.com")
				.queryString("subject", "Hello!")
				.queryString("text", "The hard part's over. You got this.")
				.asJson();
		return request.getBody();
	}

	public String hypixelTest(){
		HttpResponse<JsonNode> playerData = Unirest.get("https://api.hypixel.net/v2/resources/games")
				.header("API-Key", this.hypixelApiKey)
				.queryString("uuid", this.shepUuid)
				.asJson();
		return playerData.getBody().toPrettyString();
	}
}
