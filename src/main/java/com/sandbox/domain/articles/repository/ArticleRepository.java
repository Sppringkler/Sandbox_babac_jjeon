package com.sandbox.domain.articles.repository;

import com.sandbox.domain.articles.dto.ArticleResp;
import com.sandbox.domain.articles.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional
public class ArticleRepository {
    @PersistenceContext
    private final EntityManager em;

    public void makeArticleList(List<Article> articleList) {
        for (Article article : articleList) {
            if (article.getId() == 0) { // 새로 만드는 경우
                em.persist(article);
            } else { // 기존에 있는 경우
                em.merge(article);
            }
        }
    }

    // Article 리스트와 페이징 처리
    public List<Article> getOffsetList(int size, int page) {
        return em.createQuery("select article from Article article", Article.class)
                .setFirstResult(size * page)
                .setMaxResults(size)
                .getResultList();
    }

    // 총 Article 개수
    public Long countArticles() {
        return em.createQuery("select count(article) from Article article", Long.class)
                .getSingleResult();
    }




}