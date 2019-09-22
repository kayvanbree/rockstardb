package com.kayvanbree.rockstardb.repositories;

import com.kayvanbree.rockstardb.models.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandRepository extends CrudRepository<Band, Long> {
    Optional<Band> findFirstByName(String name);
}
