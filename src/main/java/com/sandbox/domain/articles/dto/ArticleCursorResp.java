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
    private Integer lastId;
    private List<Article> articles;
}
