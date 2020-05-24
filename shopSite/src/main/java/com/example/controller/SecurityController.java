package com.example.controller;

/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Joe Grandja
 */
@Controller
public class SecurityController {

  @RequestMapping("/hello")
 public ModelAndView root(HttpSession session,Principal principal) {
	  ModelAndView model = new ModelAndView("redirect:/bookRetrieveAll");
	    String memid = principal.getName();
		session.setAttribute("loginId", memid);
		model.addObject(memid);
		return model;
 }



 @RequestMapping("/login")
 public String login() {
  return "login";
 }

 @RequestMapping("/login-error")
 public String loginError(Model model) {
  model.addAttribute("loginError", true);
  return "login";
 }

}