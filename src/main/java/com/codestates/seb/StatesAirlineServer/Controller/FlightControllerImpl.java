package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Data.FlightData;
import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FlightControllerImpl implements FlightController{
    private final List<FlightDTO.Info> flightList = FlightData.getInstacne().getFlightList();

    @Override
    @GetMapping(value = "/flight")
    public List<FlightDTO.Info> FilterFlightList(@RequestParam(required = false) String departure_times,
                                                 @RequestParam(required = false) String arrival_times,
                                                 @RequestParam(required = false) String departure,
                                                 @RequestParam(required = false) String destination) {
        if(departure_times != null && arrival_times != null){
            return flightList
                    .stream()
                    .filter(item -> item.getDeparture_times().equals(departure_times))
                    .filter(item -> item.getArrival_times().equals(arrival_times))
                    .collect(Collectors.toList());
        }
        else if(departure != null && destination != null){
            return flightList
                    .stream()
                    .filter(item -> item.getDeparture().equals(departure))
                    .filter(item -> item.getDestination().equals(destination))
                    .collect(Collectors.toList());
        }
        return flightList;
    }

    @Override
    public FlightDTO.Info FindById(String id) {
        return null;
    }

    @Override
    public FlightDTO.Info UpdateFlightData(String id, FlightDTO.Request data) {
        return null;
    }
}
