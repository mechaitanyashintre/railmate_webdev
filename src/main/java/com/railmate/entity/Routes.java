package com.railmate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.railmate.dto.RoutesDTO;

@Entity
@Table(name="routes")
public class Routes {

    @Id
    private Integer route_id;
    private String source;
    private String destination;

    public Routes() {}

    public Routes(Integer route_id, String source, String destination) {
        super();
        this.route_id = route_id;
        this.source = source;
        this.destination = destination;
    }

    public Integer getRoute_id() { return route_id; }
    public void setRoute_id(Integer route_id) { this.route_id = route_id; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    @Override
    public String toString() {
        return "route_id=" + route_id + ", source=" + source + ", destination=" + destination;
    }

    public static RoutesDTO prepareDTO(Routes routesEntity) {
        return new RoutesDTO(routesEntity.getRoute_id(), routesEntity.getSource(), routesEntity.getDestination());
    }
}
