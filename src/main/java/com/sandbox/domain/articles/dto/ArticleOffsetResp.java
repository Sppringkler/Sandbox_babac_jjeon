package com.sandbox.domain.articles.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleOffsetResp {
    int totalPages;
    List<Article> articles;
}
