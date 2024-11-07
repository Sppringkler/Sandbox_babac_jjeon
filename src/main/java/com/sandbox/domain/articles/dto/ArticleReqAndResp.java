package com.sandbox.domain.articles.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReqAndResp {
    private int id;
    private String title;
    private String createdAt;
}
