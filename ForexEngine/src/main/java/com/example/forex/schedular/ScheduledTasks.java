package com.example.forex.schedular;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.forex.model.Forex;
import com.example.forex.repository.ForexRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class ScheduledTasks {
	@Autowired
	ForexRepository forexRepository;

	@Scheduled(cron = "0 1 0 * * ?")
	@SuppressWarnings("deprecation")
	public void scheduleTaskWithCronExpression() {
		String url_str = "https://api.exchangerate-api.com/v4/latest/USD";

		try {
			// Making Request
			URL url = new URL(url_str);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();

			// Convert to JSON
			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonObject jsonobj = root.getAsJsonObject();

			Forex forex = new Forex();
			forex.setCurrency("USD");
			forex.setRate(Double.parseDouble(jsonobj.get("USD").toString()));
			forex.setCreatedAt(new Date());
			forexRepository.save(forex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
