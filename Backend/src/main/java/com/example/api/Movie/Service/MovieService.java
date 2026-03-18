package com.example.api.Movie.Service;

import com.example.api.Movie.Model.Movie;
import com.example.api.Movie.Repository.MovieRepository;
import com.example.api.User.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.User.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService{

    @Autowired
    private MovieRepository movieRepository;


    public List<Movie> getAllUsers() {
        return movieRepository.findAll();
    }

    // TO-DO


}
