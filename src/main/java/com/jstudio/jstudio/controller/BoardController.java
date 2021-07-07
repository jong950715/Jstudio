package com.jstudio.jstudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/board")
    public String board(){
        return "board";
    }

    @GetMapping("/board/post")
    public String post(){
        return "post";
    }

    @GetMapping("/board/postform")
    public String postForm(){
        return "postForm";
    }
}
