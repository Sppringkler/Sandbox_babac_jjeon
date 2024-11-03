package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dao.ArticleDao;
import com.sandbox.domain.articles.dto.Article;
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
            return new ArticleOffsetResp(0,List.of());
        }

        List<Article> subArticles = totalArticles.subList(fromIdx,startNum*size+size);

        return new ArticleOffsetResp(totalArticles.size()/size, subArticles);
    }

    @Override
    public Map<String, Object> getCursorPage(int size, int cursorId) {
        Map<String, Object> map = new HashMap<>();
        List<Article> articleList = dao.getCursorList(size, cursorId); //dao에서 리스트 가져오기
        System.out.println("====================");
        for(Article a : articleList) {
            System.out.println(a);
        }

        if(articleList.isEmpty()) {
            map.put("lastId",null);
            map.put("articles",articleList);
            return map;

        }

        map.put("lastId",articleList.get(articleList.size() - 1).getId());
        map.put("articles",articleList);

        return map;
    }


}