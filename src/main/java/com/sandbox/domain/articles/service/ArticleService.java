package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dto.Article;
import com.sandbox.domain.articles.dto.ArticleCursorResp;
import com.sandbox.domain.articles.dto.ArticleList;
import com.sandbox.domain.articles.dto.ArticleOffsetResp;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ArticleService {
    void makeArticleList(ArticleList articleList);
    ArticleOffsetResp getOffsetPage(int size, int page);
    ArticleCursorResp getCursorPage(int size, int CursorId);
}
