package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dto.ArticleReq;
import com.sandbox.domain.articles.dto.ArticleSuccessMsgResp;
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
        // ArticleReq 객체를 Article 엔티티로 변환
        List<Article> articles = articleList.stream().map(
                articleReq -> new Article(articleReq.getId(), articleReq.getTitle(), articleReq.getCreatedAt())
        ).collect(Collectors.toList());

        dao.makeArticleList(articles);
        return new ArticleSuccessMsgResp("article리스트 생성 완료");
    }
}
