package com.sandbox.domain.articles.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleList {
    private List<ArticleResp> articles;
}
