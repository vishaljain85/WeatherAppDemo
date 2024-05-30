package com.vg.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spa")
public class WeatherSPAController {

    @GetMapping("/weather")
    public String getWeather(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        String[] cities = {"Melbourne,AU", "Sydney,AU", "Brisbane,AU", "Perth,AU", "Canberra,AU", "Hobart,AU", "Darwin,AU"};
        model.addAttribute("cities", cities);
        return "weather";
    }

    @GetMapping("/user")
    public String getUser(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "user";
    }
}
