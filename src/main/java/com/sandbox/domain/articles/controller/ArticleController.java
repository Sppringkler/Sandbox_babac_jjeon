package com.sandbox.domain.articles.controller;

import com.sandbox.domain.articles.dao.ArticleRepository;
import com.sandbox.domain.articles.dto.Article;
import com.sandbox.domain.articles.dto.ArticleList;
import com.sandbox.domain.articles.service.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleServiceImpl as;

    @GetMapping("/paging/offset")
    public ResponseEntity getPagingArticles(@RequestParam("page") int page,@RequestParam("size") int size){
        Map<String, Object> map = as.getPagingArticles(size, page);

        if(map != null){
            return ResponseEntity.ok(map);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/paging/cursor")
    public ResponseEntity getCursorArticles(@RequestParam("cursorId") int cursorId,@RequestParam("size") int size){
        Map<String, Object> map = as.getCursorArticles(size, cursorId);
        System.out.println("===============================");
        System.out.println(cursorId);
        System.out.println(size);

        if(map != null){
            return ResponseEntity.ok(map);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/make")
    public ResponseEntity makeArticle(@RequestBody ArticleList list){

        if(as.makeArticles(list.getArticles())){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
