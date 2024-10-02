package com.example.post.controller;

import com.example.post.entity.Post;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PostService postService;
    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(required = false, name = "sort_by", defaultValue = "createdAt") String sort_by,
                       @RequestParam(required = false, name = "per_page", defaultValue = "2") int per_page,
                       @RequestParam(required = false, name = "page", defaultValue = "1") int page) {

        // sort_by가 "null"이거나 빈 문자열인 경우 기본값 설정
        if (sort_by == null || sort_by.equals("null") || sort_by.isEmpty()) {
            sort_by = "createdAt";
        }
        model.addAttribute("sort_by", sort_by);
        model.addAttribute("per_page", per_page);

        // 페이징 처리된 게시물 가져오기                                     7개->2개
        Page<Post> postsOnPage = postService.findAll(page - 1, per_page, sort_by);
        //postsOnPage.getContent()
        int totalPages = postsOnPage.getTotalPages();
        System.out.println("totalPages" + totalPages); // 자동계산->4

        // 페이지 번호 리스트 생성 [1, 2, 3, ...]
        List<Integer> pages = new ArrayList<>();
        if (totalPages > 0) {
            pages = IntStream
                    .rangeClosed(0, totalPages - 1) // 0 1 2 3
                    .boxed()
                    .collect(Collectors.toList());
        }

        // 페이지 링크 생성
        List<String> links = new ArrayList<>();
        if (pages != null) {
            for (int link : pages) {
                String active = (link == postsOnPage.getNumber()) ? "active" : "";
                String tempLink = "/?per_page=" + per_page + "&page=" + (link + 1) + "&sort_by=" + sort_by;
                links.add("<li class=\"page-item " + active + "\"><a href=\"" + tempLink + "\" class='page-link'>" + (link + 1) + "</a></li>");
            }
            model.addAttribute("links", links);
        }
        // 모델에 게시물과 페이지 링크 전달
        model.addAttribute("posts", postsOnPage);

        return "home_views/list";
    }

    @GetMapping("/books")
    public String books(){
        return "home_views/books"; // books.html(검색-->Kakao Book API)
    }
}
