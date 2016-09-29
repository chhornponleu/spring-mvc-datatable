package com.spring.datatable.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.datatable.app.controllers.abstr.AbstrBaseController;
import com.spring.datatable.app.events.CustomEventPublisher;
import com.spring.datatable.app.services.UserService;
import com.spring.datatable.app.utils.ScreenViewResolvers;
import com.spring.datatable.app.utils.SecurityUtils;

@Controller
public class DashboardController extends AbstrBaseController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomEventPublisher publisher;
	
	@Autowired
	SecurityUtils securityUtils;
	
	@RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
	public String index() {
		publisher.publishEvent("Hello");
		return ScreenViewResolvers.DASH_INDEX;
	};
}
