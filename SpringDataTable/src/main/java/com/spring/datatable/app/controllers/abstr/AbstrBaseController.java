package com.spring.datatable.app.controllers.abstr;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.datatable.app.utils.SecurityUtils;

public abstract class AbstrBaseController {
	@Autowired
	protected SecurityUtils securityUtils;
}
