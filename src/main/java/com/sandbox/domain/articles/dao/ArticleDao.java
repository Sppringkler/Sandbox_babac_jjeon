package com.sandbox.domain.articles.dao;

import com.sandbox.domain.articles.dto.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ArticleDao {
    @PersistenceContext
    private final EntityManager em;

    public void makeArticleList(List<Article> articleList) {
        for(Article article:articleList) {
            if (article.getId() == 0) { // 새로 만드는 경우
                em.persist(article);
            } else { // 기존에 있는 경우
                em.merge(article);
            }
        }
    }

    public List<Article> getArticleList() {
        return em.createQuery("select article from Article article ",Article.class).getResultList();
    }
}
