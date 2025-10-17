package com.letterbox.controller;

import com.letterbox.entity.Review;          // OJO: Entity con E mayúscula
import com.letterbox.dto.ReviewDTO;
import com.letterbox.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping({"/api/reviews", "/reviews"})
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> r = reviewService.getReviewById(id);
        return r.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/movie/{movieId}")
    public List<Review> getReviewsByMovie(@PathVariable Long movieId) {
        return reviewService.getReviewsByMovieId(movieId);
    }


    @GetMapping("/favorites")
    public List<Review> getFavoriteReviews() {
        return reviewService.getFavoriteReviews();
    }


    @PostMapping
    public Review createReview(@RequestBody ReviewDTO dto) {

        return reviewService.saveReview(dto);
    }
}
