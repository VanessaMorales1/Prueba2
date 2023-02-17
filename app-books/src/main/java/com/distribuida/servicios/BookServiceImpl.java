package com.distribuida.servicios;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService {
    @PersistenceContext(unitName = "Conexion")
    private EntityManager em;

    @Override
    public List<Book> findAll()  {
        return em
                .createNamedQuery("Book.findAll",Book.class)
                .getResultList();
    }

    @Override
    public Book findById(Integer id){
        return em.find(Book.class,id);
    }


    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void insert(Book book) {
        em.persist(book);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void update(Integer id, Book book){
        var book1=this.findById(id);
        book1.setIsbn(book.getIsbn());
        book1.setAuthor(book.getAuthor());
        book1.setTitle(book.getTitle());
        book1.setPrice(book.getPrice());
        em.persist(book);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Integer id) {
        em.remove(findById(id));
    }

}
