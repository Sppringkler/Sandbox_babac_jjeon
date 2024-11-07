package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dto.ArticleCursorResp;
import com.sandbox.domain.articles.dto.ArticleOffsetResp;
import com.sandbox.domain.articles.dto.ArticleReqAndResp;

import java.util.List;

public interface ArticleService {
    void makeArticleList(List<ArticleReqAndResp> articleList);
    ArticleOffsetResp getOffsetPage(int size, int page);
    ArticleCursorResp getCursorPage(int size, int CursorId);
}
