package com.letterbox.service;

import com.letterbox.dto.MovieDTO;
import com.letterbox.entity.Movie;
import com.letterbox.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


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
        // NUEVO: Validar formato de posterUrl con regex
        if (dto.getPosterUrl() != null && !dto.getPosterUrl().trim().isEmpty()) {
            if (!isValidImageUrl(dto.getPosterUrl())) {
                throw new IllegalArgumentException("URL de poster inválida. Debe ser http/https y terminar en jpg, jpeg, png, gif o webp");
            }
        }

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

    // ==================== MÉTODOS CON JAVA STREAMS ====================

    /**
     * Filtra películas por género Y año mínimo, ordenadas alfabéticamente
     * Demuestra: filter() con múltiples condiciones, sorted(), collect()
     */
    @Transactional(readOnly = true)
    public List<Movie> findByGenreAndYearSorted(String genre, Integer minYear) {
        return movieRepository.findAll().stream()
                .filter(movie -> genre == null || movie.getGenre().equalsIgnoreCase(genre))
                .filter(movie -> minYear == null || movie.getReleaseYear() >= minYear)
                .sorted(Comparator.comparing(Movie::getTitle))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las N películas más recientes (mayor año de lanzamiento)
     * Demuestra: sorted() con reversed(), limit()
     */
    @Transactional(readOnly = true)
    public List<Movie> getMostRecentMovies(int limit) {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getReleaseYear).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    // ==================== VALIDACIÓN CON PATTERN/MATCHER ====================

    /**
     * Valida que la URL del poster sea una URL válida de imagen
     * Acepta: http/https + extensiones: jpg, jpeg, png, gif, webp
     */
    private boolean isValidImageUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }

        // Patrón regex para URL de imagen
        String urlRegex = "^https?://.*\\.(jpg|jpeg|png|gif|webp)$";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);

        return matcher.matches();
    }

}





