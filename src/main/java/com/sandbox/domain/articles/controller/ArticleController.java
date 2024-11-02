package com.sandbox.domain.articles.controller;

import com.sandbox.domain.articles.dto.Article;
import com.sandbox.domain.articles.dto.ArticleCursorResp;
import com.sandbox.domain.articles.dto.ArticleList;
import com.sandbox.domain.articles.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("articles")
@CrossOrigin(origins = "https://ssafysandbox.vercel.app")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;

    @PostMapping("/make")
    public ResponseEntity<String> makeArticle(@RequestBody ArticleList articleResp) {
        List<Article> articleList = articleResp.getArticles();
        service.makeArticleList(articleList);
        return ResponseEntity.ok("article리스트 생성 완료");
    }

    @GetMapping("/paging/offset")
    public ResponseEntity getOffsetPage(
            @RequestParam("size") int size, @RequestParam ("page") int page) {

        Map<String, Object> res = service.getOffsetPage(size,page);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/paging/cursor")
    public ResponseEntity getCursorPage(@RequestParam("size") int size, @RequestParam("cursorId") int cursorId) {
        Map<String,Object> res = service.getCursorPage(size, cursorId);
        return ResponseEntity.ok(res);

    }

}
