package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping(value = "/books")
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
        ArrayList<Book> booksList = bookService.findAll(pageable);
        return new ResponseEntity<>(booksList, HttpStatus.OK);
    }

    @ApiOperation(value = "Updates book info based on id", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Updated Successfully", response = void.class),
    })
    @PutMapping(value = "/book/{bookid}")
    public ResponseEntity<?> updateStudent(
            @RequestBody
                    Book updateBook,
            @ApiParam(value = "Book id", required = true, example = "1")
            @PathVariable
                    long Bookid)
    {
        bookService.update(updateBook, Bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
