package com.example.fastcampusmysql.domain.post.service;


import com.example.fastcampusmysql.domain.post.entitiy.Post;
import com.example.fastcampusmysql.domain.post.entitiy.Timeline;
import com.example.fastcampusmysql.domain.post.respository.TimelineRepository;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineReadService {
    final private TimelineRepository timelineRepository;

    public PageCursor<Timeline> getTimelines(Long memberId, CursorRequest cursorRequest){
        var timelines = findAllBy(memberId, cursorRequest);
        var nextKey = timelines.stream()
                .mapToLong(Timeline::getId)
                .min().orElse(CursorRequest.NONE_KEY);
        return new PageCursor(cursorRequest.next(nextKey), timelines) ;
    }

    private List<Timeline>  findAllBy(Long memberId, CursorRequest cursorRequest) {
        if(cursorRequest.hasKey()) {
            return timelineRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
        }
        return  timelineRepository.finallAllByMemberIdOrderByIdDesc(memberId, cursorRequest.size());
    }



}
