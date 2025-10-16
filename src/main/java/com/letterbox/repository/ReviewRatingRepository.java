package com.letterbox.repository;

import com.letterbox.entity.ReviewRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRatingRepository extends JpaRepository<ReviewRating, Long> {

    Optional<ReviewRating> findByUserIdAndReviewId(Long userId, Long reviewId);

    @Query("select coalesce(avg(r.score), 0) from ReviewRating r where r.reviewId = :reviewId")
    Double getAverageForReview(@Param("reviewId") Long reviewId);

    @Query("select count(r) from ReviewRating r where r.reviewId = :reviewId")
    Long getCountForReview(@Param("reviewId") Long reviewId);
}
