package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import com.example.fastcampusmysql.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreatePostUseCase {
    final private PostWriteService postWriteService;
    final private TimelineWriteService timelineWriteService;
    final private FollowReadService followReadService;


    @Transactional
    public long execute(PostCommand postCommand){
        var postId = postWriteService.create(postCommand);
        var followMemberIds = followReadService
                .getFollowers(postCommand.memberId())
                .stream()
                .map(Follow::getFromMemberId)
                .toList();
                //follow목록을 가져오는 것
        timelineWriteService.deliveryToTimeline(postId, followMemberIds);
        return postId;
        //	Propagation propagation() default Propagation.REQUIRED; 찾아보기
    }
}
