package com.letterbox.controller;

import com.letterbox.entity.Movie;
import com.letterbox.dto.MovieDTO;
import com.letterbox.repository.MovieRepository;
import com.letterbox.service.MovieService;
import com.letterbox.service.CsvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/movies", "/movies"})
public class MovieController {

    private final MovieService movieService;
    private final MovieRepository movieRepository;
    private final CsvService csvService;

    public MovieController(MovieService movieService,
                           MovieRepository movieRepository,
                           CsvService csvService) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.csvService = csvService;
    }

    // ENDPOINTS

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
        return movieService.saveMovie(dto);
    }

    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieRepository.findByGenreIgnoreCase(genre);
    }

    @GetMapping("/year/{year}")
    public List<Movie> getMoviesByYear(@PathVariable Integer year) {
        return movieRepository.findByReleaseYear(year);
    }

    // ENDPOINTS CSV

    @GetMapping("/export/csv")
    public ResponseEntity<String> exportCsv() {
        List<Movie> movies = movieService.getAllMovies();

        String csv = csvService.exportMoviesToCsv(movies);

        return ResponseEntity.ok()
                .header("Content-Type", "text/csv")
                .header("Content-Disposition", "attachment; filename=movies.csv")
                .body(csv);
    }

    @PostMapping("/import/csv")
    public ResponseEntity<?> importCsv(@RequestBody String csvContent) {
        try {
            List<Movie> imported = csvService.importMoviesFromCsv(csvContent);
            return ResponseEntity.ok(imported);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al importar CSV: " + e.getMessage());
        }
    }
}