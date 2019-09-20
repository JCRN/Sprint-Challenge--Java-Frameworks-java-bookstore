package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Authors;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorsService
{
    List<Authors> findAll(Pageable pageable);
}
