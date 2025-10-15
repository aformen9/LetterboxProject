package com.letterbox.controller;

import com.letterbox.Entity.Movie;
import com.letterbox.dto.MovieDTO;
import com.letterbox.repository.MovieRepository;
import com.letterbox.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
// Soporta ambas bases para no romper nada: /api/movies y /movies
@RequestMapping({"/api/movies", "/movies"})
public class MovieController {

    private final MovieService movieService;        // Persona 3
    private final MovieRepository movieRepository;  // para genre/year ya existentes

    public MovieController(MovieService movieService,
                           MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    // 1) Obtener todas las películas
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // 2) Obtener una película por ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> m = movieService.getMovieById(id);
        return m.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3) Buscar películas por texto (título/director). Ej: /api/movies/search?query=matrix
    @GetMapping("/search")
    public List<Movie> search(@RequestParam String query) {
        return movieService.searchMovies(query);
    }

    // 4) Crear película (por si el front lo usa)
    @PostMapping
    public Movie create(@RequestBody MovieDTO dto) {
        return movieService.saveMovie(dto); // dto.year -> entity.releaseYear
    }

    // 5) Filtros adicionales (conservamos lo que ya tenías)
    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieRepository.findByGenreIgnoreCase(genre);
    }

    @GetMapping("/year/{year}")
    public List<Movie> getMoviesByYear(@PathVariable Integer year) {
        return movieRepository.findByReleaseYear(year);
    }
}
