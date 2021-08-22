package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Data.FlightData;
import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "flight/{uuid}")
    public FlightDTO.Info FindById(@PathVariable(value = "uuid") String id) {
        return flightList
                .stream()
                .filter(item -> item.getUuid().equals(id))
                .findAny()
                .get();
    }

    @Override
    @PutMapping(value = "flight/{uuid}")
    public FlightDTO.Info UpdateFlightData(@PathVariable(value = "uuid") String id,
                                           @RequestBody(required = false) FlightDTO.Request data) {

        FlightDTO.Info filterData = flightList
                .stream()
                .filter(item -> item.getUuid().equals(id))
                .findAny()
                .get();

        if(data.getDeparture() != null)filterData.setDeparture(data.getDeparture());
        if(data.getDestination() != null)filterData.setDestination(data.getDestination());
        if(data.getDeparture_times() != null)filterData.setDeparture_times(data.getDeparture_times());
        if(data.getArrival_times() != null)filterData.setArrival_times(data.getArrival_times());

        return filterData;
    }
}
