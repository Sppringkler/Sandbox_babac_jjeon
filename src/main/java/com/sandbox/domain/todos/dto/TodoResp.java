package com.sandbox.domain.todos.dto;

import com.sandbox.domain.todos.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor //파라미터가 없는 디폴트 생성자 생성 -> 클래스에 명시적으로 선언된 생성자가 없더라도 인스턴스를 생성할 수 있다.
@AllArgsConstructor //모든 파라미터를 받는 생성자 생성
public class TodoResp {
    private String message;
    private List<Todo> todos;
}
