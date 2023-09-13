# pagination_cursor_based
pagination_cursor_based API
#### 생각
offset 기반의 page와 size 를 받는 것에 비해서 cursor기반 페이지네이션은 마지막으로 읽었던 key와 size를 받는다.
하여, 기존 pageable 객체를 사용하기 보다는 cursor 기반에서 사용할만한 객체를 만들겠다.

### Offset 방식과의 차이
Offset 기반의 경우 total count 갯수를 받아서 페이지를 나타낸다. 이말은 즉슨, 최신글이 작성되었을 경우, 중복으로 보여주는 경우가 있을 수 있다.
반면에 cursor 기반의 경우에는 key와 size를 받는다. 하여, 최신글이 작성이 된다고 할지라도, 마지막으로 확인한 key값을 전달하여 다음 페이지를 확인하기 때문에 중복으로 보여지지 않는다.
