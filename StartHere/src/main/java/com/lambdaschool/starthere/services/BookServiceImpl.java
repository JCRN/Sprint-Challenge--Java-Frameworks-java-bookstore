package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll(Pageable pageable)
    {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException((Long.toString(id))));
        if (book.getBooktitle() != null)
        {
            currentBook.setBooktitle(book.getBooktitle());
        }

        if (book.getCopy() != 0)
        {
            currentBook.setCopy(book.getCopy());
        }

        if (book.getIsbn() != null)
        {
            currentBook.setIsbn(book.getIsbn());
        }

        return bookRepository.save(currentBook);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (bookRepository.findById(id).isPresent())
        {
            bookRepository.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public void bookAuthorCombo(long bookid, long authorid)
    {
        bookRepository.insertBookAuthor(bookid, authorid);
    }
}
