package com.codestates.seb.StatesAirlineServer.API;

import com.codestates.seb.StatesAirlineServer.Controller.BookController;
import com.codestates.seb.StatesAirlineServer.Data.BookData;
import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookAPI implements BookController {
    private List<BookDTO> bookList = BookData.getInstance().getBookList();
    private List<BookDTO> filterdList = new ArrayList<>();


    @GetMapping(value = "/book")
    @Override
    public List<BookDTO> FindBook(@RequestParam(required = false) String flight_uuid,
                                  @RequestParam(required = false) String phone) {
        try {
            filterdList.clear();
            if (flight_uuid != null && phone != null) {
                for (int i = 0; i < bookList.size(); i++) {
                    if (flight_uuid.equals(bookList.get(i).getFlight_uuid()) &&
                            phone.equals(bookList.get(i).getPhone())) {
                        filterdList.add(bookList.get(i));
                    }
                }
                return filterdList;
            } else if (flight_uuid != null) {
                for (int i = 0; i < bookList.size(); i++) {
                    if (flight_uuid.equals(bookList.get(i).getFlight_uuid())) {
                        filterdList.add(bookList.get(i));
                    }
                }
                return filterdList;
            } else if (phone != null) {
                for (int i = 0; i < bookList.size(); i++) {
                    if (phone.equals(bookList.get(i).getPhone())) {
                        filterdList.add(bookList.get(i));
                    }
                }
                return filterdList;
            }

            return bookList;
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping(value = "/book")
    @Override
    public BookDTO CreateBook(@RequestBody(required = true) BookDTO createData) {
        bookList.add(createData);
        return createData;
    }

    @DeleteMapping(value = "/book")
    @Override
    public List<BookDTO> DeleteByPhone(@RequestParam(required = true) String phone) {
        try {
            if (!bookList.isEmpty()) {
                for (int i = 0; i < bookList.size(); i++) {
                    if (phone.equals(bookList.get(i).getPhone())) {
                        bookList.remove(i);
                        i--;
                    }
                }
                return bookList;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
}
