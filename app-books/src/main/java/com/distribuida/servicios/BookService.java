package com.distribuida.servicios;

import com.distribuida.db.Book;
import java.util.List;

public interface BookService {

    List<Book> findAll();
    Book findById(Integer id);
    void insert(Book book);
    void update(Integer id, Book book);
    void delete(Integer id);

}
