package com.letterbox.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewRatingDTO {
    @NotNull
    private Long userId;
    @Min(1) @Max(5)
    private Integer value;

    // getters/setters
    public Long getUserId() { return userId; }
    public void setUserId(Long u) { this.userId = u; }
    public Integer getValue() { return value; }
    public void setValue(Integer v) { this.value = v; }
}
