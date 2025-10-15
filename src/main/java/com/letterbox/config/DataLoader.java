package com.letterbox.config;

import com.letterbox.Entity.Movie;
import com.letterbox.Entity.Review;
import com.letterbox.repository.MovieRepository;
import com.letterbox.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        if (movieRepository.count() == 0) {
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
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Pulp Fiction");
        movie2.setDirector("Quentin Tarantino");
        movie2.setReleaseYear(1994);
        movie2.setGenre("Crimen");
        movie2.setSynopsis("Historias entrelazadas del mundo criminal.");
        movieRepository.save(movie2);

        // --- Reseñas ---
        Review review1 = new Review();
        review1.setMovieId(1L);
        review1.setReviewerName("María González");
        review1.setRating(5.0);
        review1.setComment("Una obra maestra del cine.");
        review1.setIsFavorite(true);
        reviewRepository.save(review1);

        Review review2 = new Review();
        review2.setMovieId(2L);
        review2.setReviewerName("Carlos Rodríguez");
        review2.setRating(4.5);
        review2.setComment("Tarantino en su máximo esplendor.");
        review2.setIsFavorite(true);
        reviewRepository.save(review2);

        System.out.println("✅ Datos de muestra cargados");
    }
}
