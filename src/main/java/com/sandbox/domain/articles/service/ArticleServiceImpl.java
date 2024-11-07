package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.repository.ArticleRepository;
import com.sandbox.domain.articles.entity.Article;
import com.sandbox.domain.articles.dto.ArticleCursorResp;
import com.sandbox.domain.articles.dto.ArticleOffsetResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements  ArticleService {
    private final ArticleRepository dao;

    @Override
    public void makeArticleList(List<Article> articleList) {
        dao.makeArticleList(articleList);
    }


    @Override
    public ArticleOffsetResp getOffsetPage(int size, int page) {
        int startNum = page-1;
        List<Article> totalArticles = dao.getOffsetList(); //dao에서 리스트 가져오기

        int totalSize = totalArticles.size();
        int fromIdx = startNum*size;

        if(fromIdx >= totalSize) {
            return new ArticleOffsetResp(null, List.of());
        }

        List<Article> subArticles = totalArticles.subList(fromIdx, fromIdx + size);

        return new ArticleOffsetResp(totalArticles.size() / size, subArticles);
    }

    @Override
    public ArticleCursorResp getCursorPage(int size, int cursorId) {
        List<Article> articleList = dao.getCursorList(size, cursorId); //dao에서 리스트 가져오기

        if(articleList.isEmpty()) {
            return new ArticleCursorResp(null, articleList);
        }

        return new ArticleCursorResp(articleList.get(articleList.size() - 1).getId(), articleList);
    }
}
