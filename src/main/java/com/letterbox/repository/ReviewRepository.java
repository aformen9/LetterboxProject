package com.letterbox.repository;

import com.letterbox.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    List<Review> findByMovieIdOrderByReviewDateDesc(Long movieId);


    List<Review> findByIsFavoriteTrueOrderByReviewDateDesc();


    List<Review> findAllByOrderByReviewDateDesc();
}
