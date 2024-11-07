package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dto.ArticleReqAndResp;
import com.sandbox.domain.articles.exception.ErrorArticleResp;
import com.sandbox.domain.articles.repository.ArticleRepository;
import com.sandbox.domain.articles.entity.Article;
import com.sandbox.domain.articles.dto.ArticleCursorResp;
import com.sandbox.domain.articles.dto.ArticleOffsetResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements  ArticleService {
    private final ArticleRepository dao;

    @Override
    public void makeArticleList(List<ArticleReqAndResp> articleList) {
        // ArticleReq 객체를 Article 엔티티로 변환
        List<Article> articles = articleList.stream().map(
                articleReq -> new Article(articleReq.getId(), articleReq.getTitle(), articleReq.getCreatedAt())
        ).collect(Collectors.toList());

        dao.makeArticleList(articles);
    }



    @Override
    public ArticleOffsetResp getOffsetPage(int size, int page) {
        Map<String, Object> totalArticles = dao.getOffsetList(size, page-1); //dao에서 리스트 가져오기

        @SuppressWarnings("unchecked")
        List<Article> articles = (List<Article>)totalArticles.get("list");

        List<ArticleReqAndResp> list = articles.stream().map(
                articleReq -> new ArticleReqAndResp(articleReq.getId(), articleReq.getTitle(), articleReq.getCreatedAt())
        ).toList();

        int totalSize = (int)totalArticles.get("size");

        ArticleOffsetResp res = new ArticleOffsetResp(totalSize, list);

        if(res.getArticles() == null){
            throw new ErrorArticleResp("정상적이지 않은 요청입니다");
        }else{
            return res;
        }
    }

    @Override
    public ArticleCursorResp getCursorPage(int size, int cursorId) {
        List<Article> articleList = dao.getCursorList(size, cursorId); //dao에서 리스트 가져오기

        List<ArticleReqAndResp> articles = articleList.stream().map(
                articleReq -> new ArticleReqAndResp(articleReq.getId(), articleReq.getTitle(), articleReq.getCreatedAt())
        ).toList();

        if(articleList.isEmpty()) {
            return new ArticleCursorResp(null, articles);
        }

        return new ArticleCursorResp(articleList.get(articleList.size() - 1).getId(), articles);
    }
}
