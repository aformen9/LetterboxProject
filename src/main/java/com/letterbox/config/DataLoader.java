package com.letterbox.config;

import com.letterbox.entity.Movie;
import com.letterbox.entity.Review;
import com.letterbox.repository.MovieRepository;
import com.letterbox.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    public DataLoader(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) {
        // Seed solo si la base está vacía
        if (movieRepository.count() == 0 && reviewRepository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        // --- Películas ---
        Movie movie1 = new Movie();
        movie1.setTitle("El Padrino");
        movie1.setDirector("Francis Ford Coppola");
        movie1.setReleaseYear(1972);
        movie1.setGenre("Drama");
        movie1.setSynopsis("La saga de una familia mafiosa italiana-americana.");
        movie1 = movieRepository.save(movie1); // <- recupero ID real

        Movie movie2 = new Movie();
        movie2.setTitle("Pulp Fiction");
        movie2.setDirector("Quentin Tarantino");
        movie2.setReleaseYear(1994);
        movie2.setGenre("Crimen");
        movie2.setSynopsis("Historias entrelazadas del mundo criminal.");
        movie2 = movieRepository.save(movie2); // <- recupero ID real

        // --- Reseñas ---
        Review review1 = new Review();
        review1.setMovieId(movie1.getId());           // ✅ no hardcodear 1L
        review1.setReviewerName("María González");
        review1.setRating(5.0);
        review1.setComment("Una obra maestra del cine.");
        review1.setIsFavorite(true);
        review1.setReviewDate(LocalDateTime.now());
        reviewRepository.save(review1);

        Review review2 = new Review();
        review2.setMovieId(movie2.getId());           // ✅ no hardcodear 2L
        review2.setReviewerName("Carlos Rodríguez");
        review2.setRating(4.5);
        review2.setComment("Tarantino en su máximo esplendor.");
        review2.setIsFavorite(true);
        review2.setReviewDate(LocalDateTime.now());
        reviewRepository.save(review2);

        System.out.println("✅ Datos de muestra cargados");
    }
}
