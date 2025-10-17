package com.letterbox.service;

import com.letterbox.dto.MovieDTO;
// usa el paquete real de tus entidades:
import com.letterbox.entity.Movie;      // <-- o com.letterbox.Entity.Movie

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    void save_and_search_movie_ok() {
        MovieDTO dto = new MovieDTO();
        dto.setTitle("Inception");
        dto.setDirector("Christopher Nolan");
        dto.setYear(2010);                // mapea a releaseYear en la entidad
        dto.setGenre("Sci-Fi");
        dto.setSynopsis("Dream heist.");
        dto.setPosterUrl("http://img");

        Movie saved = movieService.saveMovie(dto);
        assertThat(saved.getId()).isNotNull();

        List<Movie> found = movieService.searchMovies("inception");
        assertThat(found).extracting(Movie::getTitle).contains("Inception");
    }
}

// Smoke tests de tus Services (sin controllers)