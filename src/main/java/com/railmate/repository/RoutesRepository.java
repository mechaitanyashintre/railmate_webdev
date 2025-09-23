package com.railmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.railmate.entity.Routes;

public interface RoutesRepository extends JpaRepository<Routes, Integer> {

    @Query("select r from Routes r where r.source = ?1 and r.destination = ?2")
    Routes fetchSrcAndDest(String src, String dest);
}
