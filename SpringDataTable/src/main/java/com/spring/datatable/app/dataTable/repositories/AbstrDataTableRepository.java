package com.spring.datatable.app.dataTable.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.datatable.app.dataTable.DataTableHelper;
import com.spring.datatable.app.dataTable.data.DataTableParam;
import com.spring.datatable.app.dataTable.data.DataTableResponse;

@Repository
public abstract class AbstrDataTableRepository<T> {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public final DataTableResponse<T> getDataTableResponse(DataTableParam request) {
		DataTableResponse<T> response = new DataTableResponse<>();
		response.setDraw(request.getDraw());
		response.setRecordsFiltered(this.getFilteredRecord(request).intValue());
		response.setRecordsTotal(this.getRecordsTotal().intValue());
		response.setData(this.getPagingData(request));
		return response;
	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	protected Order buildOrder(String fieldName, String dir) {
		if (DataTableHelper.ORDER_ASC.equals(dir.toUpperCase())) {
			return Order.asc(fieldName);
		} else {
			return Order.desc(fieldName);
		}
	}

	protected abstract Long getRecordsTotal();

	protected abstract Long getFilteredRecord(DataTableParam request);

	protected abstract List<T> getPagingData(DataTableParam request);
}
