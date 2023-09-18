package com.chat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {

    // when the user is logged in oauth2, redirect to the frontend
    @RequestMapping("/")
    public RedirectView localRedirect() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/inbox");
        return redirectView;
    }
}