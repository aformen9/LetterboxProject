package com.letterbox.repository;

import com.letterbox.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Buscar películas por género (ignora mayúsculas/minúsculas)
    List<Movie> findByGenreIgnoreCase(String genre);

    // Buscar películas por año de estreno
    List<Movie> findByReleaseYear(Integer releaseYear);

    // Búsqueda general: título, director o género
    @Query("SELECT m FROM Movie m WHERE " +
            "LOWER(m.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(m.director) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(m.genre) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Movie> searchMovies(@Param("query") String query);
}

