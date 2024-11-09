package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dto.ArticleCursorResp;
import com.sandbox.domain.articles.dto.ArticleOffsetResp;
import com.sandbox.domain.articles.dto.ArticleReq;
import com.sandbox.domain.articles.dto.ArticleSuccessMsgResp;

import java.util.List;

public interface ArticleService {
    ArticleSuccessMsgResp makeArticleList(List<ArticleReq> articleList);
//    ArticleOffsetResp getOffsetPage(int size, int page);
//    ArticleCursorResp getCursorPage(int size, int CursorId);
}
