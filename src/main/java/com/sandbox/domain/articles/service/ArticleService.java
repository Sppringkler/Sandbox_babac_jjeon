package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.entity.Article;

import com.sandbox.domain.articles.dto.ArticleCursorResp;
import com.sandbox.domain.articles.dto.ArticleOffsetResp;

import java.util.List;

public interface ArticleService {
    void makeArticleList(List<Article> articleList);
    ArticleOffsetResp getOffsetPage(int size, int page);
    ArticleCursorResp getCursorPage(int size, int CursorId);
}
