package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.service.FollowReadService;
import com.example.fastcampusmysql.domain.post.entitiy.Post;
import com.example.fastcampusmysql.domain.post.entitiy.Timeline;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.TimelineReadService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class GetTimelinePostsUsercase {
    final private FollowReadService followReadService;
    final private PostReadService postReadService;
    final private TimelineReadService timelineReadService;
    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest){
        /*
        1. memberId - >follow 조회
       2. 1번 결과로 게시물 조회
         */
        var followings  = followReadService.getFollowings(memberId);
        var followMemberIds = followings.stream().map(Follow::getToMemberId).toList();
        return postReadService.getPosts(followMemberIds, cursorRequest);
    }
    public PageCursor<Post> executeByTimeLline(Long memberId, CursorRequest cursorRequest){
        /*
        1. timeline  - >follow 조회
       2. 1번 결과로 게시물 조회
         */
        var  pagedTimlelines  = timelineReadService.getTimelines(memberId, cursorRequest);
        var postIds = pagedTimlelines.body().stream().map(Timeline::getPostId).toList();
        var posts = postReadService.getPosts(postIds);
        return new PageCursor( pagedTimlelines.nextCurosrRequest(), posts);
    }

}
