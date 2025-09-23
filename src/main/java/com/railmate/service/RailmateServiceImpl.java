package com.railmate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railmate.dto.RoutesDTO;
import com.railmate.dto.TrainsDTO;
import com.railmate.entity.Routes;
import com.railmate.entity.Trains;
import com.railmate.repository.RoutesRepository;
import com.railmate.repository.TrainsRepository;

@Service
public class RailmateServiceImpl implements RailmateService {

    @Autowired
    private RoutesRepository routesRepository;

    @Autowired
    private TrainsRepository trainsRepository;

    // req1
    public boolean insertRouteId(RoutesDTO routesDTO) {
        Routes resRouteEntity = routesRepository.saveAndFlush(RoutesDTO.prepareEntity(routesDTO));
        return resRouteEntity != null;
    }

    // req2
    public RoutesDTO fetchRouteDetails(Integer route_id) {
        Optional<Routes> optionalRoutes = routesRepository.findById(route_id);
        Routes routesEntity = optionalRoutes.get();
        return Routes.prepareDTO(routesEntity);
    }

    // req3
    public List<Integer> fetchSrcAndDest(String src, String dest) {
        Routes routesEntity = routesRepository.fetchSrcAndDest(src, dest);
        if (routesEntity != null) {
            return trainsRepository.fetchListofTrainsByRouteId(routesEntity.getRoute_id());
        } else {
            return new ArrayList<>();
        }
    }

    // req4
    public boolean updateSrcAndDestDetails(Integer route_id, RoutesDTO routesDTO) {
        Optional<Routes> optionalRoutes = routesRepository.findById(route_id);
        if (optionalRoutes.isPresent()) {
            Routes routesEntity = optionalRoutes.get();
            routesEntity.setSource(routesDTO.getSource());
            routesEntity.setDestination(routesDTO.getDestination());
            routesRepository.save(routesEntity);
            return true;
        } else {
            return false;
        }
    }

    // req5
    public boolean deleteTrain(int route_id, int train_id) {
        int delCount = trainsRepository.deleteTrainByRouteId(train_id, route_id);
        return delCount > 0;
    }

    // req6
    public boolean updateTrainDetails(Integer route_id, TrainsDTO trainsDTO) {
        List<Trains> trainsList = trainsRepository.findByRouteId(route_id);
        boolean res = false;
        for (Trains train : trainsList) {
            if (train.getTrain_id().equals(trainsDTO.getTrain_id())) {
                train.setTrainName(trainsDTO.getTrainName());
                train.setArrivalTime(trainsDTO.getArrivalTime());
                train.setDepartureTime(trainsDTO.getDepartureTime());
                train.setFare(trainsDTO.getFare());
                trainsRepository.save(train);
                res = true;
                break;
            }
        }
        return res;
    }

    // req7
    public boolean insertTrainDetails(TrainsDTO trainsDTO) {
        Routes routesEntity = trainsDTO.getRoute();
        routesRepository.saveAndFlush(routesEntity);
        Trains resTrainEntity = trainsRepository.saveAndFlush(TrainsDTO.preparePlaEntity(trainsDTO));
        return resTrainEntity != null;
    }

    // req8
    public boolean updateFareDetails(Integer train_id, TrainsDTO trainsDTO) {
        Optional<Trains> optionalTrain = trainsRepository.findById(train_id);
        if (optionalTrain.isPresent()) {
            Trains trainEntity = optionalTrain.get();
            trainEntity.setFare(trainsDTO.getFare());
            trainsRepository.save(trainEntity);
            return true;
        } else {
            return false;
        }
    }
}
