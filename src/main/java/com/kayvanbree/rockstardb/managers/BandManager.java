package com.kayvanbree.rockstardb.managers;

import com.kayvanbree.rockstardb.models.Band;
import com.kayvanbree.rockstardb.repositories.BandRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BandManager {
    private final BandRepository bandRepository;

    public BandManager(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public Iterable<Band> getBands() {
        return bandRepository.findAll();
    }

    public Optional<Band> getBand(long id) {
        return bandRepository.findById(id);
    }

    public Band insert(Band band) throws Exception {
        Optional<Band> existing = bandRepository.findFirstByName(band.getName());
        if (existing.isPresent()) {
            throw new Exception("Already exists");
        }
        return bandRepository.save(band);
    }

    public void delete(long id) throws Exception {
        Optional<Band> band = bandRepository.findById(id);
        if (band.isPresent()) {
            bandRepository.delete(band.get());
        } else {
            throw new Exception("Band not found");
        }
    }

    public Band update(long id, Band band) throws Exception {
        Optional<Band> existing = bandRepository.findById(id);
        if (existing.isPresent() && id == band.getId()) {
            Band updated = existing.get();
            updated.setName(band.getName());
            return bandRepository.save(band);
        } else {
            throw new Exception("Band not found");
        }
    }
}
