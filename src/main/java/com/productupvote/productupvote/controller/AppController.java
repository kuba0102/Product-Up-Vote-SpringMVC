package com.productupvote.productupvote.controller;

import org.springframework.ui.Model;

public class AppController {

    // Protected variables
    protected String PAGE_TITLE_ID = "title";
    protected String USER = "user";
    protected String PERM = "perm";
    protected String LOGIN_REDIRECT = "redirect:/login";
    protected String BACKEND_LOGIN_REDIRECT = "redirect:/backend/login";
    protected String HOMEPAGE_REDIRECT = "redirect:/";
    protected String BACKEND_HOMEPAGE_REDIRECT = "redirect:/backend/";


    /**
     * This method display Unauthorised page.
     * @param model model settings.
     * @param error error message to display.
     * @return directory path of the html page to render.
     */
    protected String displayUnauthorised(Model model, String error){
        model.addAttribute("error", error);
        return "backend/error/unauthorised";
    }

}
