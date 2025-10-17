package com.letterbox.service;

import com.letterbox.dto.MovieDTO;
import com.letterbox.entity.Movie;
import com.letterbox.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie saveMovie(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDirector(dto.getDirector());
        movie.setReleaseYear(dto.getYear());
        movie.setGenre(dto.getGenre());
        movie.setSynopsis(dto.getSynopsis());
        movie.setPosterUrl(dto.getPosterUrl());
        return movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public List<Movie> searchMovies(String query) {
        return movieRepository.searchMovies(query == null ? "" : query.toLowerCase());
    }
}
// hola

