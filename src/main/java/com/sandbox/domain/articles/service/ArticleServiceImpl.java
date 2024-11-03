package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dao.ArticleDao;
import com.sandbox.domain.articles.dto.Article;
import com.sandbox.domain.articles.dto.ArticleCursorResp;
import com.sandbox.domain.articles.dto.ArticleList;
import com.sandbox.domain.articles.dto.ArticleOffsetResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements  ArticleService {

    private final ArticleDao dao;

    @Override
    public void makeArticleList(ArticleList articleList) {

        dao.makeArticleList(articleList.getArticles());
    }


    @Override
    public ArticleOffsetResp getOffsetPage(int size, int page) {

        int startNum = page - 1;
        List<Article> totalArticles = dao.getOffsetList(); //dao에서 리스트 가져오기

        int totalSize = totalArticles.size();
        int fromIdx = startNum * size;

        //fromIdx가 리스트 크기보다 크거나 같으면 빈 리스트 반환하기!
        if(fromIdx >= totalSize) {
            return new ArticleOffsetResp(null, List.of());
        }

        List<Article> subArticles = totalArticles.subList(fromIdx, startNum * size + size);

        return new ArticleOffsetResp(totalArticles.size() / size, subArticles);
    }

    @Override
    public ArticleCursorResp getCursorPage(int size, int cursorId) {
        List<Article> articleList = dao.getCursorList(size, cursorId); //dao에서 리스트 가져오기
        Integer lastId = null;

        if(!articleList.isEmpty()) {
            lastId = articleList.get(articleList.size() - 1).getId();
        }

        return new ArticleCursorResp(lastId, articleList);
    }
}
