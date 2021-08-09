package com.codestates.seb.StatesAirlineServer.API;

import com.codestates.seb.StatesAirlineServer.Controller.AirportController;
import com.codestates.seb.StatesAirlineServer.Data.AirportData;
import com.codestates.seb.StatesAirlineServer.Domain.AirportDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@RestController
public class AirportAPI implements AirportController {
    private List<AirportDTO> airportList = AirportData.getInstance().AirportList;
    private List<AirportDTO> filterdList = new ArrayList<>();

    @Override
    @GetMapping(value = "/airport")
    public List<AirportDTO> AirportFindByKeyWord(@RequestParam(required = true) String query) {
        try{
            filterdList.clear();
            if (query != null) {
                for (int i = 0; i < airportList.size(); i++) {
                    if (airportList.get(i).getCode().contains(query.toUpperCase())) {
                        filterdList.add(airportList.get(i));
                    }
                }
                return filterdList;
            }

            return airportList;
        }catch (Exception e){
            return null;
        }
    }





}
