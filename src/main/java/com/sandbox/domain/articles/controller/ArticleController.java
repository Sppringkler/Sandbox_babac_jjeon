package com.sandbox.domain.articles.controller;

import com.sandbox.domain.articles.dto.ArticleCursorResp;
import com.sandbox.domain.articles.dto.ArticleList;
import com.sandbox.domain.articles.dto.ArticleOffsetResp;
import com.sandbox.domain.articles.service.ArticleService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("articles")
@CrossOrigin(origins = "https://ssafysandbox.vercel.app")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;

    @PostMapping("/make")
    public ResponseEntity<String> makeArticle(@RequestBody ArticleList articleList) {
        service.makeArticleList(articleList.getArticles());
        return ResponseEntity.ok("article리스트 생성 완료");
    }

    @GetMapping("/paging/offset")
    public ResponseEntity<ArticleOffsetResp> getOffsetPage(
            @RequestParam("size") int size,
            @RequestParam ("page") int page) {
        ArticleOffsetResp res = service.getOffsetPage(size,page);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/paging/cursor")
    public ResponseEntity<ArticleCursorResp> getCursorPage(
            @RequestParam("size") int size,
            @RequestParam("cursorId") int cursorId) {

        ArticleCursorResp res = service.getCursorPage(size, cursorId);
        return ResponseEntity.ok(res);
    }
}
