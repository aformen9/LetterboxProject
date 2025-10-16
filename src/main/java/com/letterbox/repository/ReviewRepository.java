package com.letterbox.repository;

import com.letterbox.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Reseñas de una película ordenadas por fecha descendente
    List<Review> findByMovieIdOrderByReviewDateDesc(Long movieId);

    // Reseñas marcadas como favoritas
    List<Review> findByIsFavoriteTrueOrderByReviewDateDesc();

    // Todas las reseñas más recientes primero
    List<Review> findAllByOrderByReviewDateDesc();
}
