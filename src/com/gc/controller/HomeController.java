package com.gc.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/*
 * author: Jenna Price
 * 
 *
 */

@Controller
@SessionAttributes("sessionId") //assigned in the helloWorld method
public class HomeController {
	

	@RequestMapping("/")
	//the HttpSession parameter is for the session, the HttpServlet is for the COOKIE
	public ModelAndView helloWorld(HttpSession hSession, HttpServletResponse response) {
		String sessId= hSession.getId();
		hSession.setAttribute("sessionId", sessId);
		System.out.println(sessId);
		
		//demos adding our own cookie
		response.addCookie(new Cookie ("userId", "jprice"));
		
		return new ModelAndView("index", "message", sessId );
	}
	
	@RequestMapping("getsession")
	public ModelAndView keepSession(HttpSession hSession) {
		if(hSession.getAttribute("counter") == null) {
			hSession.setAttribute("counter", 0);
		}
		
		//cast the object that is returned from the session the an int
		Integer c = (Integer) hSession.getAttribute("counter");
		c++;
		hSession.setAttribute("counter", c);
		
		return new ModelAndView("sessiontracker", "sessioncounter", c);
	}
	
	
	
	@RequestMapping("seesessionid")
	//The ModelAttribute is being used for session example, and the @CookieValue is for the cookie example
	public String seesession(@ModelAttribute("sessionId") String sId, Model model, @CookieValue("userId") String cookieValue) {
		model.addAttribute("IdPassedIntoJSP", sId);
		model.addAttribute("CookiesValue", cookieValue);
		return "seesessionid";
	}
	
	
	
	
	

}