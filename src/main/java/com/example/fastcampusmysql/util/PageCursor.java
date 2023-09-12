package com.example.fastcampusmysql.util;

import java.util.List;

public record PageCursor<T> (
        CursorRequest nextCurosrRequest,
        List<T> body
){}
