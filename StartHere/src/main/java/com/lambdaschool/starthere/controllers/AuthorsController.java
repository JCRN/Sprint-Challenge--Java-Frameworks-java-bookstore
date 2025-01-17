package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.Authors;
import com.lambdaschool.starthere.services.AuthorsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorsController
{
    @Autowired
    AuthorsService authorsService;

    @ApiOperation(value = "Return all Authors", response = Authors.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you " +
                    "want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records " +
                    "per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value =
                    "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })

    // http://localhost:2019/authors
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAuthors(Pageable pageable) {
        List<Authors> authorsList = authorsService.findAll(pageable);
        return new ResponseEntity<>(authorsList, HttpStatus.OK);
    }

}