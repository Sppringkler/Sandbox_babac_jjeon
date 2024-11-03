package com.sandbox.domain.articles.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleOffsetResp {
    private int totalPage;
    private List<Article> articles;
}
