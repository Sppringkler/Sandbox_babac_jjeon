package com.sandbox.domain.todos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor //파라미터가 없는 디폴트 생성자 생성 -> 클래스에 명시적으로 선언된 생성자가 없더라도 인스턴스를 생성할 수 있다.
public class TodoResp {
    String message;
    List<Todo> todos;
}
