package com.sabu.multipledatasource.dao;

import com.sabu.multipledatasource.entity.common.Books;
import com.sabu.multipledatasource.repository.readonly.BooksReadOnlyRepository;
import com.sabu.multipledatasource.repository.readwrite.BooksReadWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/04/05
 */
@Repository
public class BooksDAO implements BooksReadOnlyRepository, BooksReadWriteRepository {

    private BooksReadOnlyRepository booksReadOnlyRepository;

    private BooksReadWriteRepository booksReadWriteRepository;

    @Autowired
    public BooksDAO(
            BooksReadOnlyRepository booksReadOnlyRepository, BooksReadWriteRepository booksReadWriteRepository) {
        this.booksReadOnlyRepository = booksReadOnlyRepository;
        this.booksReadWriteRepository = booksReadWriteRepository;
    }

    public List<Books> getAllBooksFromMaster() {
        return booksReadWriteRepository.findAll();
    }

    public List<Books> getAllBooksFromSlave() {
        return booksReadOnlyRepository.findAll();
    }

    @Override
    public List<Books> findAll() {
        return booksReadOnlyRepository.findAll();
    }

    @Override
    public List<Books> findAll(Sort sort) {
        return booksReadOnlyRepository.findAll();
    }

    @Override
    public Page<Books> findAll(Pageable pageable) {
        return booksReadOnlyRepository.findAll(pageable);
    }

    @Override
    public List<Books> findAllById(Iterable<Long> iterable) {
        return booksReadOnlyRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Books books) {
        booksReadWriteRepository.delete(books);
    }

    @Override
    public void deleteAll(Iterable<? extends Books> iterable) {
        booksReadWriteRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        booksReadWriteRepository.deleteAll();
    }

    @Override
    public <S extends Books> S save(S s) {
        return booksReadWriteRepository.save(s);
    }

    @Override
    public <S extends Books> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Books> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {
        booksReadWriteRepository.flush();
    }

    @Override
    public <S extends Books> S saveAndFlush(S s) {
        return booksReadWriteRepository.saveAndFlush(s);
    }

    @Override
    public void deleteInBatch(Iterable<Books> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Books getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Books> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Books> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Books> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Books> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Books> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Books> boolean exists(Example<S> example) {
        return false;
    }
}
