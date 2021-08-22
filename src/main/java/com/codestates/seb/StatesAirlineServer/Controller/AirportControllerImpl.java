package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Data.AirportData;
import com.codestates.seb.StatesAirlineServer.Domain.AirportDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class AirportControllerImpl implements AirportController{
    private final List<AirportDTO> airportList = AirportData.getInstance().AirportList;

    @Override
    @GetMapping(value = "/airport")
    public List<AirportDTO> AirportFindByKeyWord(@RequestParam String query) {
        return airportList
                .stream()
                .filter(item -> item.getCode().contains(query.toUpperCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
}
