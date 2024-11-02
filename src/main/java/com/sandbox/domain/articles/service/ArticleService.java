package com.sandbox.domain.articles.service;

import com.sandbox.domain.articles.dto.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ArticleService {
    boolean makeArticles(List<Article> list);
    Map<String, Object> getPagingArticles(int size, int page);
    Map<String, Object> getCursorArticles(int size, int cursorId);
}
