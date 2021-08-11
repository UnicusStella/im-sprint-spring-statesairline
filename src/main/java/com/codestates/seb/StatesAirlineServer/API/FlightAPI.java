package com.codestates.seb.StatesAirlineServer.API;

import com.codestates.seb.StatesAirlineServer.Controller.FlightController;
import com.codestates.seb.StatesAirlineServer.Data.FlightData;
import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightAPI implements FlightController {
    private List<FlightDTO.Info> flightList = FlightData.getInstacne().getFlightList();
    private List<FlightDTO.Info> filterdList = new ArrayList<>();


    @Override
    @GetMapping(value = "/flight")
    public List<FlightDTO.Info> FilterFlightList(@RequestParam(required = false) String departure_times,
                                                 @RequestParam(required = false) String arrival_times,
                                                 @RequestParam(required = false) String departure,
                                                 @RequestParam(required = false) String destination) {
        // 출발 시간이 있는경우
        // 출발지가 있는경우
        try {
            filterdList.clear();
            if (departure_times != null && arrival_times != null) {
                for (int i = 0; i < flightList.size(); i++) {
                    if (departure_times.equals(flightList.get(i).getDeparture_times()) &&
                            arrival_times.equals(flightList.get(i).getArrival_times())) {
                        filterdList.add(flightList.get(i));
                    }
                }
                return filterdList;
            } else if (departure != null || destination != null) {
                for (int j = 0; j < flightList.size(); j++) {
                    if (departure.equals(flightList.get(j).getDeparture()) &&
                            destination.equals((flightList.get(j).getDestination()))) {
                        filterdList.add(flightList.get(j));
                    }
                }
                return filterdList;

            }
            return flightList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @GetMapping(value = "/flight/{uuid}")
    public FlightDTO.Info FindById(@PathVariable(value = "uuid", required = true) String id) {

        for (int i = 0; i < flightList.size(); i++) {
            if (id.equals(flightList.get(i).getUuid())) {
                return flightList.get(i);
            }
        }
        return null;
    }

    @Override
    @PutMapping(value = "flight/{uuid}")
    public FlightDTO.Info UpdateFlightData(@PathVariable(value = "uuid", required = true) String id,
                                           @RequestBody(required = false) FlightDTO.Request data) {
        if (id != null) {
            for (int i = 0; i < flightList.size(); i++) {
                if (id.equals(flightList.get(i).getUuid())) {
                    if (data.getDeparture() != null) {
                        flightList.get(i).setDeparture(data.getDeparture());
                    }
                    if (data.getDestination() != null) {
                        flightList.get(i).setDestination(data.getDestination());
                    }
                    if (data.getDeparture_times() != null) {
                        flightList.get(i).setDeparture_times(data.getDeparture_times());
                    }
                    if (data.getArrival_times() != null) {
                        flightList.get(i).setArrival_times(data.getArrival_times());
                    }
                    return flightList.get(i);
                }
            }
        }
        return null;
    }
}
