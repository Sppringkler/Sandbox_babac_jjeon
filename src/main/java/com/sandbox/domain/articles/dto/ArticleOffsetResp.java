package com.sandbox.domain.articles.dto;

import com.sandbox.domain.articles.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleOffsetResp {
    private Integer totalPage;
    private List<ArticleResp> articles;
}