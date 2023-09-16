package com.example.fastcampusmysql.domain.post.entitiy;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Timeline {
    final private Long id;
    final private Long memberId;
    final private Long postId;
    final LocalDateTime createdAt;

    @Builder
    public Timeline(Long id, Long memberId, Long postId, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.postId = Objects.requireNonNull(postId);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
