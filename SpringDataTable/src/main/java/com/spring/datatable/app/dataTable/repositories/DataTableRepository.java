package com.spring.datatable.app.dataTable.repositories;

import com.spring.datatable.app.dataTable.data.DataTableParam;
import com.spring.datatable.app.dataTable.data.DataTableResponse;

public interface DataTableRepository<T> {
	public DataTableResponse<T> getDataTableResponse(DataTableParam request);
}
