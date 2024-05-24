package com.urlshortener.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class RedirectController {

    @GetMapping("/")
    public void redirectToHomePage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/urls");
    }

}
