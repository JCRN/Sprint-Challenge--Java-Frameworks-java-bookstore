package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;


@RestController
public class BookController
{
    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Return all books using Paging and Sorting", response = Book.class, responseContainer =
            "ArrayList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/books",
            produces = {"application/json"})
    public ResponseEntity<?> findAllBooks(
            @PageableDefault(page = 0,
                    size = 5)
                    Pageable pageable)
    {
        ArrayList<Book> booksList = (ArrayList<Book>) bookService.findAll(pageable);
        return new ResponseEntity<>(booksList, HttpStatus.OK);
    }

    @ApiOperation(value = "Updates book info based on id", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Updated Successfully", response = void.class),
    })
    @PutMapping(value = "data/books/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateBook(
            @RequestBody
                    Book updateBook,
            @ApiParam(value = "Book id", required = true, example = "1")
            @PathVariable long id)
    {
        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Combines a book with an author",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book and Author Combined Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error Combining Book With Author", response = ErrorDetail.class)
    })
    @PostMapping(value = "data/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> saveBookAuthorCombo(HttpServletRequest request, @PathVariable("bookid") long bookid,
                                                 @PathVariable("authorid") long authorid) {
        bookService.bookAuthorCombo(bookid, authorid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a Book",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Deleted Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error Deleting Book", response = ErrorDetail.class)
    })

    @DeleteMapping("/data/books/{id}")
    public ResponseEntity<?> deleteBookById(
            @PathVariable
                    long id
    ) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
