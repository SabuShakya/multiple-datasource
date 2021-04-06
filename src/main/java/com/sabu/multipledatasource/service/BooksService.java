package com.sabu.multipledatasource.service;

import com.sabu.multipledatasource.entity.common.Books;
import com.sabu.multipledatasource.dao.BooksDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/04/05
 */
@Service
public class BooksService {
    private BooksDAO booksDAO;

    @Autowired
    public BooksService(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    public void addBook(Books book) {
        booksDAO.saveAndFlush(book);
    }

    public List getAllBooksFromMaster() {
        return booksDAO.getAllBooksFromMaster();
    }

    public List getAllBooksFromSlave() {
        return booksDAO.getAllBooksFromSlave();
    }
}
