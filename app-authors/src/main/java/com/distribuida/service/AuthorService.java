package com.distribuida.service;

import com.distribuida.db.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);
    void create(Author author);
    void update(Long id,Author author);
    void delete(Long id);
}
