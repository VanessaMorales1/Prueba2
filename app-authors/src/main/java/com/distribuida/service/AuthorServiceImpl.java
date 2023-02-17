package com.distribuida.service;

import com.distribuida.db.Author;
import com.distribuida.respository.AuthorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.listAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id);
    }


    @Transactional
    @Override
    public void create(Author author) {
        authorRepository.persist(author);

    }
    @Transactional
    @Override
    public void update(Long id, Author author) {
        Author author1=findById(id);
        author1.setFirst_name(author.getFirst_name());
        author1.setLast_name(author.getLast_name());
        authorRepository.persist(author1);

    }

    @Transactional
    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
