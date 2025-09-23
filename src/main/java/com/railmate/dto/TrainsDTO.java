package com.railmate.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.railmate.entity.Routes;
import com.railmate.entity.Trains;

public class TrainsDTO {

    @NotNull
    @Min(value=1, message="train_id is mandatory")
    @Max(value=9999, message="train_id should be max 4 digits")
    private Integer train_id;

    private String trainName;
    private String arrivalTime;
    private String departureTime;
    private Double fare;
    private Routes route;

    public TrainsDTO() { super(); }

    public TrainsDTO(Integer train_id, String trainName, String arrivalTime, String departureTime, Double fare, Routes route) {
        super();
        this.train_id = train_id;
        this.trainName = trainName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.fare = fare;
        this.route = route;
    }

    public Integer getTrain_id() { return train_id; }
    public void setTrain_id(Integer train_id) { this.train_id = train_id; }
    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public Double getFare() { return fare; }
    public void setFare(Double fare) { this.fare = fare; }
    public Routes getRoute() { return route; }
    public void setRoute(Routes route) { this.route = route; }

    @Override
    public String toString() {
        return "train_id=" + train_id + ", trainName=" + trainName + ", arrivalTime=" + arrivalTime +
               ", departureTime=" + departureTime + ", fare=" + fare + ", route=" + route;
    }

    public static Trains preparePlaEntity(TrainsDTO trainsDTO) {
        return new Trains(trainsDTO.getTrain_id(), trainsDTO.getTrainName(), trainsDTO.getArrivalTime(),
                          trainsDTO.getDepartureTime(), trainsDTO.getFare(), trainsDTO.getRoute());
    }
}
