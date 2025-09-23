package com.railmate.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import com.railmate.entity.Routes;

public class RoutesDTO {

    private Integer route_id;

    @NotBlank(message = "Source cannot be empty")
    @Pattern(regexp = "[A-Za-z]+")
    private String source;

    @NotBlank(message = "Destination cannot be empty")
    @Pattern(regexp = "[A-Za-z]+")
    private String destination;

    public RoutesDTO() {}

    public RoutesDTO(Integer route_id, String source, String destination) {
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

    public static Routes prepareEntity(RoutesDTO routesDTO) {
        return new Routes(routesDTO.getRoute_id(), routesDTO.getSource(), routesDTO.getDestination());
    }
}
