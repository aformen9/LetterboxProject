package com.letterbox.service;

import com.letterbox.entity.Review;
import com.letterbox.entity.ReviewRating;
import com.letterbox.entity.User;
import com.letterbox.dto.RatingRequest;
import com.letterbox.dto.RatingSummaryResponse;
import com.letterbox.repository.ReviewRatingRepository;
import com.letterbox.repository.ReviewRepository;
import com.letterbox.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewRatingService {

    private final ReviewRatingRepository ratings;
    private final ReviewRepository reviews;
    private final UserRepository users;

    public ReviewRatingService(
            ReviewRatingRepository ratings,
            ReviewRepository reviews,
            UserRepository users
    ) {
        this.ratings = ratings;
        this.reviews = reviews;
        this.users = users;
    }

    @Transactional
    public RatingSummaryResponse rate(Long reviewId, RatingRequest dto) {
        // Validaciones básicas
        Review review = reviews.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("Reseña no encontrada"));
        User user = users.findById(dto.getUserId()).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        if (dto.getValue() == null || dto.getValue() < 1 || dto.getValue() > 5) {
            throw new IllegalArgumentException("Valor de voto inválido (1..5)");
        }

        // Upsert (actualiza si ya existe el voto del usuario)
        ReviewRating rr = ratings.findByUserIdAndReviewId(user.getId(), review.getId())
                .orElseGet(ReviewRating::new);

        rr.setUserId(user.getId());
        rr.setReviewId(review.getId());
        rr.setScore(dto.getValue()); // << score, no value
        ratings.save(rr);

        // Resumen actualizado
        Double avg = ratings.getAverageForReview(reviewId);
        Long count = ratings.getCountForReview(reviewId);
        return new RatingSummaryResponse(reviewId, avg, count);
    }

    @Transactional(readOnly = true)
    public RatingSummaryResponse summary(Long reviewId) {
        // Asegura que la reseña exista
        reviews.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("Reseña no encontrada"));
        Double avg = ratings.getAverageForReview(reviewId);
        Long count = ratings.getCountForReview(reviewId);
        return new RatingSummaryResponse(reviewId, avg, count);
    }
}
