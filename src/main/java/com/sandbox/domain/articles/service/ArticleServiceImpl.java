package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dto.*;
import com.sandbox.domain.articles.repository.ArticleRepository;
import com.sandbox.domain.articles.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements  ArticleService {
    private final ArticleRepository dao;

    @Override
    public ArticleSuccessMsgResp makeArticleList(List<ArticleReq> articleList) {
        List<Article> articles = articleList.stream().map(
                articleReq -> new Article(articleReq.getId(), articleReq.getTitle(), articleReq.getCreatedAt())
        ).collect(Collectors.toList());

        dao.makeArticleList(articles);
        return new ArticleSuccessMsgResp("article리스트 생성 완료");
    }


    @Override
    public ArticleOffsetResp getOffsetPage(int size, int page) {
        int startNum = page - 1;

        // 리스트랑 전체개수 조회
        List<Article> articles = dao.getOffsetList(size, startNum);
        int totalSize = (int) Math.ceil((double) dao.countArticles() / size);

        // Article -> ArticleResp
        List<ArticleResp> subArticles = articles.stream()
                .map(article -> new ArticleResp(article.getId(), article.getTitle(), article.getCreatedAt()))
                .collect(Collectors.toList());

        return new ArticleOffsetResp(totalSize, subArticles);
    }

    @Override
    public ArticleCursorResp getCursorPage(int size, int cursorId) {
        List<Article> articleList = dao.getCursorList(size, cursorId);

        if (articleList.isEmpty()) {
            return new ArticleCursorResp(null, List.of());
        }

        List<ArticleResp> articles = articleList.stream().map(
                articleReq -> new ArticleResp(articleReq.getId(), articleReq.getTitle(), articleReq.getCreatedAt())
        ).toList();

        return new ArticleCursorResp(articleList.get(articleList.size() - 1).getId(), articles);
    }
}
