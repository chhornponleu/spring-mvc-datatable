package com.spring.datatable.app.dataTable.repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.datatable.app.dataTable.DataTableHelper;
import com.spring.datatable.app.dataTable.data.DataTableOrder;
import com.spring.datatable.app.dataTable.data.DataTableParam;
import com.spring.datatable.app.dataTable.data.DataTableResponse;

@Repository
public abstract class AbstrDataTableCriteriaRepository<T> {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Class<T> getDomainClass() {
		ParameterizedType thisType = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<T>) thisType.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unused")
	private String getDomainClassName() {
		return this.getDomainClass().getName();
	}

	@Transactional(readOnly = true)
	public final DataTableResponse<T> getDataTableResponse(DataTableParam request) {
		DataTableResponse<T> response = new DataTableResponse<>();
		response.setDraw(request.getDraw());
		response.setRecordsFiltered(this.getFilteredRecord(request).intValue());
		response.setRecordsTotal(this.getRecordsTotal().intValue());
		response.setData(this.getData(request));
		return response;
	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	protected Long getRecordsTotal() {
		Criteria cri = this.getSession().createCriteria(this.getDomainClass());
		cri.setProjection(Projections.rowCount());
		return (Long) cri.uniqueResult();
	}

	protected Long getFilteredRecord(DataTableParam request) {
		Criteria cri = this.getSession().createCriteria(this.getDomainClass());

		cri.add(Restrictions.like("username", "%" + request.getSearch().getValue() + "%"));

		cri.setProjection(Projections.rowCount());
		return (Long) cri.uniqueResult();
	}

	protected List<T> getData(DataTableParam request) {
		Criteria cri = this.getSession().createCriteria(this.getDomainClass());
		cri.add(Restrictions.like("username", "%" + request.getSearch().getValue() + "%"));
		if (request.getOrder().isEmpty()) {
			cri.addOrder(Order.asc("username"));
		} else {
			DataTableOrder order = request.getOrder().get(0);
			switch (order.getColumn()) {
			case 0:
				cri.addOrder(buildOrder("username", order.getDir()));
				break;

			default:
				break;
			}
		}
		return null;
	}

	private Order buildOrder(String fieldName, String dir) {
		if (DataTableHelper.ORDER_ASC.equals(dir.toUpperCase())) {
			return Order.asc(fieldName);
		} else {
			return Order.desc(fieldName);
		}
	}
}
