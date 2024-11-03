package com.sandbox.domain.articles.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ArticleList {
    private List<Article> articles;

}