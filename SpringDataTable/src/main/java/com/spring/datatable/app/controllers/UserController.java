package com.spring.datatable.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.datatable.app.dao.UserDao;
import com.spring.datatable.app.dataTable.data.DataTableParam;
import com.spring.datatable.app.dataTable.data.DataTableResponse;
import com.spring.datatable.app.models.User;
import com.spring.datatable.app.services.UserDataTableService;
import com.spring.datatable.app.utils.ScreenViewResolvers;
import com.spring.datatable.app.utils.SecurityUtils;

@Controller
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private SecurityUtils securityUtils;

	@Autowired
	@Qualifier("userDataTableServiceImpl")
	private UserDataTableService userDataTableService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginForm() {
		return ScreenViewResolvers.USER_LOGIN;
	}

	@ResponseBody
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public User me() {
		String username = securityUtils.getCurrentAuthenticatedToken().getName().toString();
		return userDao.getByUsername(username);
	}

	@ResponseBody
	@RequestMapping(value = "/datatable", method = RequestMethod.POST)
	public DataTableResponse<User> getUserDataTable(@RequestBody DataTableParam request) {
		return userDataTableService.getDataTableResponse(request);
	}
}
