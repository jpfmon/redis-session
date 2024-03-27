package com.montojo.redissession.api;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    private final String HOME_VIEW_COUNT = "HOME_VIEW_COUNT";

    @GetMapping("/")
    public String home(Authentication authentication, HttpSession httpSession) {
        incrementCount(httpSession, HOME_VIEW_COUNT);
        return "Hello " + authentication.getName();
    }

    @GetMapping("/count")
    public String count(HttpSession httpSession) {
        return "HOME_VIEW_COUNT: " + httpSession.getAttribute(HOME_VIEW_COUNT);
    }

    private void incrementCount(HttpSession httpSession, String homeViewCount) {

        var homeviewcount = httpSession.getAttribute(homeViewCount) == null ? 0 : (Integer) httpSession.getAttribute(homeViewCount);
        httpSession.setAttribute(homeViewCount, homeviewcount +=1);
    }
}
