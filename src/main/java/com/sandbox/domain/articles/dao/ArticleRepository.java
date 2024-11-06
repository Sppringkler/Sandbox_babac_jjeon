package com.sandbox.domain.articles.dao;

import com.sandbox.domain.articles.dto.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public void makeArticles(List<Article> list){
        for (Article article : list) {
            em.merge(article);
        }
    }

    public List<Article> getArticles(){
        return em.createQuery("select article from Article article", Article.class).getResultList();
    }

    public List<Article> getArticlesWithCursor(int size, int cursorId){
        return em.createQuery(
                "select article from Article article " +
                        "where article.id > :cursorId ",Article.class
        ).setParameter("cursorId", cursorId)
                .setMaxResults(size)
                .getResultList();
    }
}
