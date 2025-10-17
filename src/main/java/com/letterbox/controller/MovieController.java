package com.letterbox.controller;

import com.letterbox.entity.Movie;
import com.letterbox.dto.MovieDTO;
import com.letterbox.repository.MovieRepository;
import com.letterbox.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping({"/api/movies", "/movies"})
public class MovieController {

    private final MovieService movieService;
    private final MovieRepository movieRepository;

    public MovieController(MovieService movieService,
                           MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }


    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> m = movieService.getMovieById(id);
        return m.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/search")
    public List<Movie> search(@RequestParam String query) {
        return movieService.searchMovies(query);
    }


    @PostMapping
    public Movie create(@RequestBody MovieDTO dto) {
        return movieService.saveMovie(dto); // dto.year -> entity.releaseYear
    }


    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieRepository.findByGenreIgnoreCase(genre);
    }

    @GetMapping("/year/{year}")
    public List<Movie> getMoviesByYear(@PathVariable Integer year) {
        return movieRepository.findByReleaseYear(year);
    }
}
