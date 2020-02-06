package com.sun.zq.controller;

import com.google.common.collect.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("user", getUserName());
        model.addAttribute("role", getAuthority());
        return "home";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("user", getUserName());
        model.addAttribute("role", getAuthority());
        return "admin";
    }

    @RequestMapping("/dba")
    public String dba(Model model) {
        model.addAttribute("user", getUserName());
        model.addAttribute("role", getAuthority());
        return "dba";
    }

    @RequestMapping("/accessDenied")
    public String accessDenied(Model model) {
        model.addAttribute("user", getUserName());
        model.addAttribute("role", getAuthority());
        return "accessDenied";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }

    private String getUserName() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("userName = " + userName);

        return userName;
    }

    private String getAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = Lists.newArrayList();
        authentication.getAuthorities().forEach(grantedAuthority -> {
            roles.add(grantedAuthority.getAuthority());
        });
        System.out.println("role = " + roles);

        return roles.toString();
    }
}
