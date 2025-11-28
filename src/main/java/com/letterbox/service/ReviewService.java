package com.letterbox.service;

import com.letterbox.dto.ReviewDTO;
import com.letterbox.entity.Review;
import com.letterbox.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional(readOnly = true)
    public List<Review> getAllReviews() {
        return reviewRepository.findAllByOrderByReviewDateDesc();
    }

    @Transactional(readOnly = true)
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review saveReview(ReviewDTO dto) {
        Review review = new Review();
        review.setMovieId(dto.getMovieId());
        review.setReviewerName(dto.getReviewerName());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setIsFavorite(Boolean.TRUE.equals(dto.getIsFavorite()));
        return reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByMovieId(Long movieId) {
        return reviewRepository.findByMovieIdOrderByReviewDateDesc(movieId);
    }

    @Transactional(readOnly = true)
    public List<Review> getFavoriteReviews() {
        return reviewRepository.findByIsFavoriteTrueOrderByReviewDateDesc();

    }

    // JAVA STREAMS

    @Transactional(readOnly = true)
    public Double getAverageRatingForMovie(Long movieId) {
        List<Review> reviews = reviewRepository.findByMovieIdOrderByReviewDateDesc(movieId);
        return reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }

    @Transactional(readOnly = true)
    public List<Review> getTopRatedReviews(int limit) {
        return reviewRepository.findAll().stream()
                .sorted(Comparator.comparing(Review::getRating).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByMinRating(Double minRating) {
        return reviewRepository.findAllByOrderByReviewDateDesc().stream()
                .filter(review -> review.getRating() >= minRating)
                .collect(Collectors.toList());
    }

}
