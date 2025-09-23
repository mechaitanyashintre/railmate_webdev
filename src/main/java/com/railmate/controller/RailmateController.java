package com.railmate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.railmate.dto.RoutesDTO;
import com.railmate.dto.TrainsDTO;
import com.railmate.service.RailmateService;
import com.railmate.utilities.CustomMessages;

@RestController
@RequestMapping("/routes")
@Validated
public class RailmateController {

    @Autowired
    RailmateService service;

    private static final Logger logger = Logger.getLogger(RailmateController.class.getName());

    static {
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }

    // Req1: Insert Route
    @PostMapping(consumes="application/json")
    public ResponseEntity<Map<String, String>> insertRouteId(@Valid @RequestBody RoutesDTO routesDTO) {
        Map<String, String> resp = new HashMap<>();
        try {
            Random random = new Random();
            int randomNumber = random.nextInt(900) + 100;
            RoutesDTO routesDTO1 = new RoutesDTO(randomNumber, routesDTO.getSource(), routesDTO.getDestination());
            boolean res = service.insertRouteId(routesDTO1);
            if (res) {
                resp.put(CustomMessages.STATUS, CustomMessages.SUCCESS);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES1a);
                resp.put("Route Id", String.valueOf(randomNumber));
                logger.info(CustomMessages.TEXTRES1a);
            } else {
                resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES1b);
                logger.info(CustomMessages.TEXTRES1b);
            }
        } catch (Exception e) {
            resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
            resp.put(CustomMessages.EXCEPTIONMESSAGE, e.getLocalizedMessage());
            logger.log(Level.SEVERE, e.getLocalizedMessage());
        }
        return ResponseEntity.ok(resp);
    }

    // Req2: Fetch Route Details
    @GetMapping(value = "/{route_id}", params="v1", produces="application/json")
    public Map<String, String> fetchRouteDetails(
            @PathVariable("route_id") @Digits(integer=3, fraction=0, message="route_id should be of 3 digit")
            @Min(value=100, message="route_id should be of 3 digit") 
            @Max(value=999, message="route_id should be of 3 digit") int route_id) {

        Map<String, String> resp = new HashMap<>();
        try {
            RoutesDTO routesDTO = service.fetchRouteDetails(route_id);
            if (routesDTO != null) {
                resp.put(CustomMessages.STATUS, CustomMessages.SUCCESS);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES2a);
                resp.put("Route Detail", routesDTO.toString());
                logger.info(CustomMessages.TEXTRES2a);
            } else {
                resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES2b);
                logger.info(CustomMessages.TEXTRES2b);
            }
        } catch (Exception e) {
            resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
            resp.put(CustomMessages.EXCEPTIONMESSAGE, e.getLocalizedMessage());
            logger.log(Level.SEVERE, e.getLocalizedMessage());
        }
        return resp;
    }

    // Req3: Fetch Trains by Source & Destination
    @GetMapping(produces="application/json")
    public Map<String, String> fetchSrcAndDest(@RequestParam("src") String src, @RequestParam("dest") String dest) {
        Map<String, String> resp = new HashMap<>();
        try {
            List<Integer> trainList = service.fetchSrcAndDest(src, dest);
            if (!trainList.isEmpty()) {
                resp.put(CustomMessages.STATUS, CustomMessages.SUCCESS);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES3a);
                resp.put("Train List", trainList.toString());
                logger.info(CustomMessages.TEXTRES3a);
            } else {
                resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES3b);
                logger.info(CustomMessages.TEXTRES3b);
            }
        } catch(Exception e) {
            resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
            resp.put(CustomMessages.EXCEPTIONMESSAGE, e.getLocalizedMessage());
            logger.log(Level.SEVERE, e.getLocalizedMessage());
        }
        return resp;
    }

    // Req4: Update Route
    @PutMapping(value = "/{route_id}", params="v2", consumes="application/json")
    public ResponseEntity<Map<String, String>> updateSrcAndDestDetails(
            @PathVariable("route_id") Integer route_id, 
            @Valid @RequestBody RoutesDTO routesDTO) {

        Map<String, String> resp = new HashMap<>();
        try {
            boolean res = service.updateSrcAndDestDetails(route_id, routesDTO);
            if (res) {
                resp.put(CustomMessages.STATUS, CustomMessages.SUCCESS);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES4a);
                resp.put("Route Id", String.valueOf(route_id));
                logger.info(CustomMessages.TEXTRES4a);
            } else {
                resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES4b);
                logger.info(CustomMessages.TEXTRES4b);
            }
        } catch (Exception e) {
            resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
            resp.put(CustomMessages.EXCEPTIONMESSAGE, e.getLocalizedMessage());
            logger.log(Level.SEVERE, e.getLocalizedMessage());
        }
        return ResponseEntity.ok(resp);
    }

    // Req5: Delete Train
    @DeleteMapping(value = "/{route_id}/{train_id}")
    public Map<String, String> deleteTrain(@PathVariable("route_id") int route_id, @PathVariable("train_id") int train_id) {
        Map<String, String> resp = new HashMap<>();
        try {
            boolean res = service.deleteTrain(route_id, train_id);
            if(res) {
                resp.put(CustomMessages.STATUS, CustomMessages.SUCCESS);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES5a);
                resp.put("Train Id", String.valueOf(train_id));
                logger.info(CustomMessages.TEXTRES5a);
            } else {
                resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES5b);
                logger.info(CustomMessages.TEXTRES5b);
            }
        } catch (Exception e) {
            resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
            resp.put(CustomMessages.EXCEPTIONMESSAGE, e.getLocalizedMessage());
            logger.log(Level.SEVERE, e.getLocalizedMessage());
        }
        return resp;
    }

    // Req6: Update Train Details
    @PutMapping(value = "/{route_id}", consumes="application/json")
    public Map<String, String> updateTrainDetails(@PathVariable("route_id") int route_id, @RequestBody TrainsDTO trainsDTO) {
        Map<String, String> resp = new HashMap<>();
        try {
            boolean res = service.updateTrainDetails(route_id, trainsDTO);
            if(res) {
                resp.put(CustomMessages.STATUS, CustomMessages.SUCCESS);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES6a);
                resp.put("Route Id", String.valueOf(route_id));
                logger.info(CustomMessages.TEXTRES6a);
            } else {
                resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES6b);
                logger.info(CustomMessages.TEXTRES6b);
            }
        } catch (Exception e) {
            resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
            resp.put(CustomMessages.EXCEPTIONMESSAGE, e.getLocalizedMessage());
            logger.log(Level.SEVERE, e.getLocalizedMessage());
        }
        return resp;
    }

    // Req7: Insert Train Details
    @PostMapping(value = "/trains", consumes="application/json")
    public Map<String, String> insertTrainDetails(@RequestBody TrainsDTO trainsDTO) {
        Map<String, String> resp = new HashMap<>();
        try {
            boolean res = service.insertTrainDetails(trainsDTO);
            if(res) {
                resp.put(CustomMessages.STATUS, CustomMessages.SUCCESS);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES7a);
                resp.put("Train Id", String.valueOf(trainsDTO.getTrain_id()));
                logger.info(CustomMessages.TEXTRES7a);
            } else {
                resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES7b);
                logger.info(CustomMessages.TEXTRES7b);
            }
        } catch (Exception e) {
            resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
            resp.put(CustomMessages.EXCEPTIONMESSAGE, e.getLocalizedMessage());
            logger.log(Level.SEVERE, e.getLocalizedMessage());
        }
        return resp;
    }

    // Req8: Update Fare Details
    @PutMapping(value = "/trains/{train_id}", consumes="application/json")
    public Map<String, String> updateFareDetails(@PathVariable("train_id") int train_id, @RequestBody TrainsDTO trainsDTO) {
        Map<String, String> resp = new HashMap<>();
        try {
            boolean res = service.updateFareDetails(train_id, trainsDTO);
            if(res) {
                resp.put(CustomMessages.STATUS, CustomMessages.SUCCESS);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES8a);
                resp.put("Train Id", String.valueOf(train_id));
                logger.info(CustomMessages.TEXTRES8a);
            } else {
                resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
                resp.put(CustomMessages.MESSAGE, CustomMessages.TEXTRES8b);
                logger.info(CustomMessages.TEXTRES8b);
            }
        } catch (Exception e) {
            resp.put(CustomMessages.STATUS, CustomMessages.FAIL);
            resp.put(CustomMessages.EXCEPTIONMESSAGE, e.getLocalizedMessage());
            logger.log(Level.SEVERE, e.getLocalizedMessage());
        }
        return resp;
    }
}
