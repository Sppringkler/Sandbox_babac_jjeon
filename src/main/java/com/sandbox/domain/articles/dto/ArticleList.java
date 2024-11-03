package com.sandbox.domain.articles.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleList {
    private List<Article> articles;

}