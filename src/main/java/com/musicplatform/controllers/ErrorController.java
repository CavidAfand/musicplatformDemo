package com.musicplatform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public ModelAndView doGet(HttpServletRequest request, Model model) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            System.out.println("Status: " + statusCode);
            System.out.println();
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                ModelAndView mv = new ModelAndView("redirect:error");
                mv.addObject("error", "Page not found");
                return mv;
            }
        }

        return new ModelAndView("error");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
