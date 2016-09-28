package com.spring.datatable.app.services;

import com.spring.datatable.app.dataTable.data.DataTableParam;
import com.spring.datatable.app.dataTable.data.DataTableResponse;
import com.spring.datatable.app.models.User;

public interface UserDataTableService {
	public DataTableResponse<User> getDataTableResponse(DataTableParam request);
}
