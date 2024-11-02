package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dao.ArticleDao;
import com.sandbox.domain.articles.dto.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements  ArticleService {
    private final ArticleDao dao;

    @Override
    public void makeArticleList(List<Article> articleList) {
        dao.makeArticleList(articleList);
    }


    @Override
    public Map<String, Object> getOffsetPage(int size, int page) {
        Map<String, Object> map = new HashMap<>();
        int startNum = page-1;
        List<Article> totalArticles = dao.getArticleList(); //dao에서 리스트 가져오기

        int totalSize = totalArticles.size();
        int fromIdx = startNum*size;

        if(fromIdx>=totalSize) {
            map.put("totalPage",0);
            map.put("articles",List.of());
            return map;
        }

        List<Article> subArticles = totalArticles.subList(fromIdx,startNum*size+size);
        map.put("totalPage", totalArticles.size()/size);
        map.put("articles", subArticles);

        return map;
    }

    @Override
    public Map<String, Object> getCursorPage(int size, int CursorId) {
        List<Article> articleList = dao.getArticleList(); //dao에서 리스트 가져오기


        return Map.of();
    }


}
