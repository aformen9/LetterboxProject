package com.letterbox.controller;

import com.letterbox.Entity.Review;          // OJO: Entity con E mayúscula
import com.letterbox.dto.ReviewDTO;
import com.letterbox.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
// Expo ambas rutas por compatibilidad: /api/reviews (front) y /reviews (lo que tenías)
@RequestMapping({"/api/reviews", "/reviews"})
public class ReviewController {

    private final ReviewService reviewService;   // ✅ usamos la capa de servicio (Persona 3)

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 1) Todas las reseñas (orden DESC por fecha)
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // 2) Reseña por ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> r = reviewService.getReviewById(id);
        return r.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3) Reseñas de una película específica (orden DESC por fecha)
    @GetMapping("/movie/{movieId}")
    public List<Review> getReviewsByMovie(@PathVariable Long movieId) {
        return reviewService.getReviewsByMovieId(movieId);
    }

    // 4) Favoritas
    @GetMapping("/favorites")
    public List<Review> getFavoriteReviews() {
        return reviewService.getFavoriteReviews();
    }

    // 5) Crear reseña
    @PostMapping
    public Review createReview(@RequestBody ReviewDTO dto) {
        // DTO debe venir con: movieId, reviewerName, rating, comment, (isFavorite opcional)
        return reviewService.saveReview(dto);
    }
}
