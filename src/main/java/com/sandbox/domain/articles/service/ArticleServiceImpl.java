package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dao.ArticleRepository;
import com.sandbox.domain.articles.dto.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository ar;

    @Override
    public boolean makeArticles(List<Article> list) {
        try {
            ar.makeArticles(list);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> getPagingArticles(int size, int page) {
        List<Article> list = ar.getArticles();
        Map<String, Object> map = new HashMap<>();

        int idx = page-1;

        try{
            map.put("totalPage", list.size()/size);
            map.put("articles", list.subList(idx*size, idx*size+size));
        }catch (Exception e) {
            return null;
        }

        return map;

    }

    @Override
    public Map<String, Object> getCursorArticles(int size,int cursorId) {
        List<Article> list = ar.getArticlesWithCursor(size, cursorId);
        Integer lastid = list.isEmpty() ? null : list.get(list.size()-1).getId();

        Map<String, Object> map = new HashMap<>();

        try{
            map.put("lastId", lastid);
            map.put("articles", list);
        }catch (Exception e) {
            return null;
        }

        return map;
    }
}
