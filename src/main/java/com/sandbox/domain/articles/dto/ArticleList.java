package com.sandbox.domain.articles.dto;

import com.sandbox.domain.articles.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ArticleList {
    private List<Article> articles;

}
