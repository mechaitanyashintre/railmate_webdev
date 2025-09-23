package com.railmate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.railmate.entity.Trains;

public interface TrainsRepository extends JpaRepository<Trains, Integer> {

    @Query("select t.train_id from Trains t where t.route.route_id=?1")
    List<Integer> fetchListofTrainsByRouteId(Integer route_id);

    @Transactional
    @Modifying
    @Query("delete from Trains t where t.train_id=?1 and t.route.route_id=?2")
    int deleteTrainByRouteId(Integer train_id, Integer route_id);

    @Query("select t from Trains t where t.route.route_id=?1")
    List<Trains> findByRouteId(Integer route_id);
}
