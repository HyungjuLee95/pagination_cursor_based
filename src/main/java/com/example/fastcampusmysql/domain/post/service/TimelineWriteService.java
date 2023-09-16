package com.example.fastcampusmysql.domain.post.service;


import com.example.fastcampusmysql.domain.post.entitiy.Timeline;
import com.example.fastcampusmysql.domain.post.respository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineWriteService {
    final private TimelineRepository timelineRepository;

    public void deliveryToTimeline(Long postId, List<Long> toMemberIds){
        var timelines = toMemberIds.stream().map((memberid)-> toTimeline(postId, memberid))
                .toList();
        timelineRepository.bulkInsert(timelines);
    }

    private static Timeline toTimeline(Long postId, Long memberid) {
        return Timeline.builder().memberId(memberid).postId(postId).build();
    }
}
