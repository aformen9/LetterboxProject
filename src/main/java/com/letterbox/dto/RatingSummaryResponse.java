package com.letterbox.dto;

public class RatingSummaryResponse {
    private Long reviewId;
    private Double average;
    private Long count;

    public RatingSummaryResponse(Long reviewId, Double average, Long count) {
        this.reviewId = reviewId;
        this.average = average;
        this.count = count;
    }

    public Long getReviewId() { return reviewId; }
    public Double getAverage() { return average; }
    public Long getCount() { return count; }
}
