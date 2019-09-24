package com.kayvanbree.rockstardb.managers;

import com.kayvanbree.rockstardb.models.Band;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BandManagerTest {
    private BandManager bandManager;
    private List<Band> bandList;
    private Band b1, b2, b3, b4, b5;

    @Before
    public void setup() throws Exception {
        com.kayvanbree.rockstardb.repositories.BandRepository bandRepository = mock(com.kayvanbree.rockstardb.repositories.BandRepository.class);
        bandManager = new BandManager(bandRepository);

        b1 = new Band(1, "Tumbler's Edge");
        b2 = new Band(2, "Opiat Sun");
        b3 = new Band(3, "Jimi Hendrix");
        b4 = new Band(4, "Menschwalsch");
        b5 = new Band(5, "Lalallaa");

        bandList = new ArrayList<>();
        bandList.add(b1);
        bandList.add(b2);
        bandList.add(b3);

        when(bandRepository.findAll()).thenReturn(bandList);
        when(bandRepository.findById(any(Long.class))).thenReturn(Optional.of(b1));
        when(bandRepository.save(any())).thenReturn(b1);
    }

    @Test
    public void getBands() throws Exception {
        Iterable<Band> bands = bandManager.getBands();
        assertEquals(bandList, bands);
    }

    @Test
    public void getBand() throws Exception {
        Band band = bandManager.getBand(1L).get();
        assertEquals(b1, band);
    }

    @Test
    public void deleteBand() throws Exception {
        bandManager.delete(1L);
    }

    @Test
    public void updateBand() throws Exception {
        // Oops, got the name wrong in the @Before...
        Band band = new Band(2, "Opiate Sun");
        Band result = bandManager.update(1L, band);
        assertEquals(b1, result);
    }

    @Test
    public void insertBand() throws Exception {
        bandManager.insert(b4);
        Band band = bandManager.getBand(3L).get();
        assertEquals(b1, band);
    }
}
