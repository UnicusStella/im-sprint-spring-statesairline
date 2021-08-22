package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Data.BookData;
import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookControllerImpl implements BookController{
    private final List<BookDTO> bookList = BookData.getInstance().getBookList();

    @Override
    @GetMapping(value = "/book")
    public List<BookDTO> FindBook(@RequestParam(required = false) String flight_uuid,
                                  @RequestParam(required = false) String phone) {
        if(flight_uuid != null){
            return bookList
                    .stream()
                    .filter(item -> item.getFlight_uuid().equals(flight_uuid))
                    .collect(Collectors.toList());
        }
        else if(phone != null){
            return bookList
                    .stream()
                    .filter(item -> item.getPhone().equals(phone))
                    .collect(Collectors.toList());
        }
        return bookList;
    }

    @Override
    public BookDTO CreateBook(BookDTO createData) {
        return null;
    }

    @Override
    public List<BookDTO> DeleteByPhone(String phone) {
        return null;
    }
}
