package com.sabu.multipledatasource.controller;

import com.sabu.multipledatasource.entity.common.Books;
import com.sabu.multipledatasource.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/04/05
 */
@RestController
public class BooksController {
    private BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping(value = "/books/add", consumes = {"application/json"}, method = RequestMethod.POST)
    public void addBook(@RequestBody Books book) {
        booksService.addBook(book);
    }

    @RequestMapping(value = "/books/find/master", method = RequestMethod.GET)
    public List getAllBooksFromMaster() {
        return booksService.getAllBooksFromMaster();
    }

    @RequestMapping(value = "/books/find/slave", method = RequestMethod.GET)
    public List getAllBooksFromSlave() {
        return booksService.getAllBooksFromSlave();
    }
}
