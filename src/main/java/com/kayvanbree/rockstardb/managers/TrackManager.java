package com.kayvanbree.rockstardb.managers;

import com.kayvanbree.rockstardb.models.Track;
import com.kayvanbree.rockstardb.repositories.TrackRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TrackManager {
    private final TrackRepository trackRepository;

    public TrackManager(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Iterable<Track> getTracks() {
        return trackRepository.findAll();
    }

    public Optional<Track> getTrack(long id) {
        return trackRepository.findById(id);
    }

    public Track insert(Track track) throws Exception {
        Optional<Track> existing = trackRepository.findById(track.getId());
        if (existing.isPresent()) {
            throw new Exception(track.getName() + " (" + track.getId() + ") already exists");
        }
        return trackRepository.save(track);
    }

    public void delete(long id) throws Exception {
        Optional<Track> track = trackRepository.findById(id);
        if (track.isPresent()) {
            trackRepository.delete(track.get());
        } else {
            throw new Exception("Track not found");
        }
    }

    public Track update(long id, Track track) throws Exception {
        Optional<Track> existing = trackRepository.findById(id);
        if (existing.isPresent()) {
            track.setId(id);
            return trackRepository.save(track);
        } else {
            throw new Exception("Track not found");
        }
    }
}
