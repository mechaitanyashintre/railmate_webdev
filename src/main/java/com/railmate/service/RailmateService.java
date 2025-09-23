package com.railmate.service;

import java.util.List;

import com.railmate.dto.RoutesDTO;
import com.railmate.dto.TrainsDTO;

public interface RailmateService {

    // req1
    public boolean insertRouteId(RoutesDTO routesDTO);

    // req2
    public RoutesDTO fetchRouteDetails(Integer route_id);

    // req3
    public List<Integer> fetchSrcAndDest(String src, String dest);

    // req4
    public boolean updateSrcAndDestDetails(Integer route_id, RoutesDTO routesDTO);

    // req5
    public boolean deleteTrain(int route_id, int train_id);

    // req6
    public boolean updateTrainDetails(Integer route_id, TrainsDTO trainsDTO);

    // req7
    public boolean insertTrainDetails(TrainsDTO trainsDTO);

    // req8
    public boolean updateFareDetails(Integer train_id, TrainsDTO trainsDTO);
}
