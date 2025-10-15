package com.letterbox.controller;

import com.letterbox.Entity.Review;
import com.letterbox.repository.ReviewRepository;
import com.letterbox.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewController(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    // 1️⃣ Obtener todas las reseñas
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // 2️⃣ Obtener una reseña por ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3️⃣ Obtener reseñas de una película específica
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Review>> getReviewsByMovie(@PathVariable Long movieId) {
        List<Review> reviews = reviewRepository.findByMovieIdOrderByReviewDateDesc(movieId);
        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviews);
    }
}
