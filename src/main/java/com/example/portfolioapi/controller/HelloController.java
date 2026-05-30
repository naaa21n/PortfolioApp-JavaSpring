package com.example.portfolioapi.controller;

// =========================
// Spring MVC Import
// =========================

// REST API用
import org.springframework.web.bind.annotation.*;

// CORS設定用
import org.springframework.web.bind.annotation.CrossOrigin;

// =========================
// Hello Controller
// =========================

// REST API Controller
//
// 戻り値がJSONや文字列として返される
@RestController

// localhost:3000(Next.js)からの
// APIアクセスを許可
@CrossOrigin(origins = "http://localhost:3000")
public class HelloController {

    // =========================
    // GET API
    // =========================

    // GET:
    // /api/hello
    //
    // ブラウザアクセス例:
    // http://localhost:8080/api/hello
    @GetMapping("/api/hello")

    public String hello() {

        // 文字列レスポンス返却
        return "Hello World";
    }
}