package com.letterbox.controller;

import com.letterbox.dto.RatingRequest;
import com.letterbox.dto.RatingSummaryResponse;
import com.letterbox.service.ReviewRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews/{reviewId}/ratings")
public class ReviewRatingController {

    private final ReviewRatingService service;

    public ReviewRatingController(ReviewRatingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RatingSummaryResponse> rate(
            @PathVariable Long reviewId,
            @RequestBody RatingRequest request
    ) {
        return ResponseEntity.ok(service.rate(reviewId, request));
    }

    @GetMapping("/summary")
    public ResponseEntity<RatingSummaryResponse> summary(@PathVariable Long reviewId) {
        return ResponseEntity.ok(service.summary(reviewId));
    }
}
