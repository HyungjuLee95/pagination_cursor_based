# pagination_cursor_based
pagination_cursor_based API
#### 생각
offset 기반의 page와 size 를 받는 것에 비해서 cursor기반 페이지네이션은 마지막으로 읽었던 key와 size를 받는다.
하여, 기존 pageable 객체를 사용하기 보다는 cursor 기반에서 사용할만한 객체를 만들겠다.
