package com.kayvanbree.rockstardb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kayvanbree.rockstardb.managers.BandManager;
import com.kayvanbree.rockstardb.managers.TrackManager;
import com.kayvanbree.rockstardb.models.Band;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kayvanbree.rockstardb.models.Track;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class RockstardbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RockstardbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(BandManager bandManager, TrackManager trackManager) {
		return args -> {
			feedBands(bandManager);
			feedTracks(trackManager);
		};
	}

	private void feedBands(BandManager bandManager) {
		// read json and write to db
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Band>> typeReference = new TypeReference<List<Band>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/bands.json");
		try {
            List<Band> bands = mapper.readValue(inputStream,typeReference);
            for(int i = 0; i < bands.size(); i++) {
                bandManager.insert(bands.get(i));
            }
            System.out.println("Bands Saved!");
        } catch (Exception e){
            System.out.println("Unable to save bands: " + e.getMessage());
        }
	}

	private void feedTracks(TrackManager trackManager) {
		// read json and write to db
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Track>> typeReference = new TypeReference<List<Track>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/tracks.json");
		try {
            List<Track> tracks = mapper.readValue(inputStream,typeReference);
            for(int i = 0; i < tracks.size(); i++) {
                trackManager.insert(tracks.get(i));
            }
            System.out.println("Tracks Saved!");
        } catch (Exception e){
            System.out.println("Unable to save tracks: " + e.getMessage());
        }
	}
}
