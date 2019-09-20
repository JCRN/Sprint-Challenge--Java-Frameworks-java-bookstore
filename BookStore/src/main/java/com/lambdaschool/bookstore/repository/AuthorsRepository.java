package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.models.Authors;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorsRepository extends PagingAndSortingRepository<Authors, Long>
{
}
