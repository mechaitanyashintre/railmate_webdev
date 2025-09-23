package com.railmate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.railmate.dto.TrainsDTO;

@Entity
@Table(name="trains")
public class Trains {

    @Id
    private Integer train_id;
    private String trainName;
    private String arrivalTime;
    private String departureTime;
    private Double fare;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="route_id")
    private Routes route;

    public Trains() { super(); }

    public Trains(Integer train_id, String trainName, String arrivalTime, String departureTime, Double fare, Routes route) {
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

    public static TrainsDTO preparePlaDTO(Trains trainsEntity) {
        return new TrainsDTO(trainsEntity.getTrain_id(), trainsEntity.getTrainName(), trainsEntity.getArrivalTime(),
                             trainsEntity.getDepartureTime(), trainsEntity.getFare(), trainsEntity.getRoute());
    }
}
