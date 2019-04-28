package com.productupvote.productupvote.controller;

import org.springframework.ui.Model;
/**
 * AppController
 * This class controls sets up basic urls and variables.
 *
 * @author U1554969 Jakub Chruslicki
 */
public class AppController {

    // Protected variables
    protected String DIRECTORY = "directory";
    protected String PAGE_TITLE_ID = "title";
    protected String USER = "user";
    protected String PERM = "perm";
    protected String LOGIN_REDIRECT = "redirect:/login";
    protected String BACKEND_LOGIN_REDIRECT = "redirect:/backend/login";
    protected String HOMEPAGE_REDIRECT = "redirect:/";
    protected String BACKEND_HOMEPAGE_REDIRECT = "redirect:/backend/";
    protected String FRONTEND_INDEX = "frontend/index/index-frontend";
    protected String BACKEND_INDEX = "backend/index/index-backend";


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
