package com.kayvanbree.rockstardb.repositories;

import com.kayvanbree.rockstardb.models.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends CrudRepository<Band, Long> {
}
