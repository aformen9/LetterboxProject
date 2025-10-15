package com.letterbox.dto;

public class RatingRequest {
    private Long userId;
    private Integer value; // 1..5

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }
}

