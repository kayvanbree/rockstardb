package com.kayvanbree.rockstardb.controllers;

import com.kayvanbree.rockstardb.managers.BandManager;
import com.kayvanbree.rockstardb.models.Band;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/band")
public class BandController {

    private final BandManager bandManager;

    public BandController(BandManager bandManager) {
        this.bandManager = bandManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Band>> getBands() {
        return new ResponseEntity<Iterable<Band>>(bandManager.getBands(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Band> getBand(@PathVariable long id) {
        Optional<Band> band = bandManager.getBand(id);
        if (band.isPresent()) {
            return new ResponseEntity<Band>(band.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity insert(@RequestBody Band band) {
        try {
            bandManager.insert(band);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable long id, @RequestBody Band band) {
        try {
            bandManager.update(id, band);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id) {
        try {
            bandManager.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
