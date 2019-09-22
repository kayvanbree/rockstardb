package com.kayvanbree.rockstardb.controllers;

import com.kayvanbree.rockstardb.managers.TrackManager;
import com.kayvanbree.rockstardb.models.Track;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/track")
public class TrackController {

    private final TrackManager trackManager;

    public TrackController(TrackManager trackManager) {
        this.trackManager = trackManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Track>> getTracks() {
        return new ResponseEntity<Iterable<Track>>(trackManager.getTracks(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Track> getTrack(@PathVariable long id) {
        Optional<Track> track = trackManager.getTrack(id);
        if (track.isPresent()) {
            return new ResponseEntity<Track>(track.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity insert(@RequestBody Track track) {
        try {
            trackManager.insert(track);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable long id, @RequestBody Track track) {
        try {
            trackManager.update(id, track);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id) {
        try {
            trackManager.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
