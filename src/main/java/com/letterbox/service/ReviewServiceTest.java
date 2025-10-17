package com.letterbox.service;

import com.letterbox.dto.ReviewDTO;
// usa el paquete real de tus entidades:
import com.letterbox.entity.Review;     // <-- o com.letterbox.Entity.Review
import com.letterbox.entity.Movie;      // <-- o com.letterbox.Entity.Movie

import com.letterbox.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired private ReviewService reviewService;
    @Autowired private MovieRepository movieRepository;

    @Test
    void save_and_list_favorites_ok() {
        Movie m = new Movie();
        m.setTitle("Matrix");
        m.setDirector("Wachowski");
        m.setReleaseYear(1999);
        m.setGenre("Sci-Fi");
        m.setSynopsis("Neo awakens.");
        m.setPosterUrl("http://img");
        m = movieRepository.save(m);

        ReviewDTO dto = new ReviewDTO();
        dto.setMovieId(m.getId());
        dto.setReviewerName("Agu");
        dto.setRating(5.0);
        dto.setComment("Obra maestra");
        dto.setIsFavorite(true);

        Review saved = reviewService.saveReview(dto);
        assertThat(saved.getId()).isNotNull();

        List<Review> favs = reviewService.getFavoriteReviews();
        Long movieId = m.getId();  // ya no usamos en lambda

        assertThat(favs)
                .extracting(Review::getMovieId)
                .contains(movieId);

    }
}
