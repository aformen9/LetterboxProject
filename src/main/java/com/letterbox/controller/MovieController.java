package com.letterbox.controller;

import com.letterbox.Entity.Movie;
import com.letterbox.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    // Constructor para inyectar el repositorio
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // 1️⃣ Obtener todas las películas
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // 2️⃣ Obtener una película por ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3️⃣ Buscar películas por género
    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieRepository.findByGenreIgnoreCase(genre);
    }

    // 4️⃣ Buscar películas por año de lanzamiento
    @GetMapping("/year/{year}")
    public List<Movie> getMoviesByYear(@PathVariable Integer year) {
        return movieRepository.findByReleaseYear(year);
    }
}
