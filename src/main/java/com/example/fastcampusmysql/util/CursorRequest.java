package com.example.fastcampusmysql.util;

public record CursorRequest(Long key, int size) {
    //커서 키를 설정할 때에는 unique함이 보장이 되어야한다. 즉, 식별자를 사용하여 unique를 보장한다.
    // 클라이언트가 최초 요청을 할때에는 설정된 key가 없기 때문에 default 키를 설정해준다.

    public boolean hasKey(){
        return key != null;
    }
    //key를 가지고 있느냐, 아니냐에 관한 정책이 바뀔 수 있기 때문!

    public static final Long NONE_KEY = -1L;

    public CursorRequest next(Long key){
        return new CursorRequest(key, size);
    }



}
