package com.sandbox.domain.articles.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCursorResp {
    private Integer lastId; //null값 리턴하기 위해서 Integer로 형 바꿈!
    private List<Article> articleList;
}
