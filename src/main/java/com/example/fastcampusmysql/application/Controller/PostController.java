package com.example.fastcampusmysql.application.Controller;


import com.example.fastcampusmysql.application.usecase.CreatePostUseCase;
import com.example.fastcampusmysql.application.usecase.GetTimelinePostsUsercase;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.entitiy.Post;
import com.example.fastcampusmysql.domain.post.service.PostReadService;
import com.example.fastcampusmysql.domain.post.service.PostWriteService;
import com.example.fastcampusmysql.util.CursorRequest;
import com.example.fastcampusmysql.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    final private PostWriteService postWriteService;
    final private PostReadService postReadService;
    final private GetTimelinePostsUsercase getTimelinePostsUsercase;
    final private CreatePostUseCase createPostUseCase;
    @PostMapping("")
    public Long create(@RequestBody PostCommand command){
        return createPostUseCase.execute(command);
    }

    @PostMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts( @RequestBody DailyPostCountRequest request){
        return postReadService.getDailyPostCounts(request);
    }

    @PostMapping("/member/{memberId}")
    public Page<Post> getPosts(
            @PathVariable Long memberId,
            Pageable pageable

    ){
        return postReadService.getPosts(memberId, pageable);
    }
    @PostMapping("/member/{memberId}/by_cursor")
    public PageCursor<Post> getPostsByCursor(
            @PathVariable Long memberId,
            CursorRequest cursorRequest

    ){
        return postReadService.getPosts(memberId, cursorRequest);
    }
    @PostMapping("/member/{memberId}/get_timeline")
    public PageCursor<Post> gettimeline(
            @PathVariable Long memberId,
            CursorRequest cursorRequest

    ){
        return getTimelinePostsUsercase.executeByTimeLline(memberId, cursorRequest);
    }
}
