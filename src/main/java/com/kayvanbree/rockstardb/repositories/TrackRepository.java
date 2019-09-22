package com.kayvanbree.rockstardb.repositories;

import com.kayvanbree.rockstardb.models.Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {
    Optional<Track> findFirstByName(String name);
}
