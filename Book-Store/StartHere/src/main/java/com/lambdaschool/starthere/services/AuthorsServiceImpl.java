package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Authors;
import com.lambdaschool.starthere.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorsService")
public class AuthorsServiceImpl implements AuthorsService
{
    @Autowired
    private AuthorsRepository authorsRepository;

    @Override
    public List<Authors> findAll(Pageable pageable)
    {
        List<Authors> list = new ArrayList<>();
        authorsRepository.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }
}
