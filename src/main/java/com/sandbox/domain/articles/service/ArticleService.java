package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dto.Article;
import com.sandbox.domain.articles.dto.ArticleOffsetResp;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ArticleService {
    void makeArticleList(List<Article> articleList);
    ArticleOffsetResp getOffsetPage(int size, int page);
    Map<String, Object> getCursorPage(int size, int CursorId);


}
