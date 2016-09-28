package com.spring.datatable.app.dataTable.repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.datatable.app.dataTable.data.DataTableParam;
import com.spring.datatable.app.dataTable.data.DataTableResponse;

@Repository
public abstract class AbstrDataTableQueryRepository<T> {

	@Autowired
	private SessionFactory sessionFactory;

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
		String hql = this.getRecordsTotalQuery();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	protected Long getFilteredRecord(DataTableParam request) {
		String hql = this.getFilteredRecordCountQuery();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		this.setUpQueryParams(request, query);
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	protected List<T> getData(DataTableParam request) {
		String hql = this.getFilteredRecordQuery();
		Query query = this.getSession().createQuery(hql);
		this.setUpQueryParams(request, query);
		query.setFirstResult(request.getStart());
		query.setMaxResults(request.getLength());
		return query.list();
	}

	/**
	 * HQL to query record without parameter
	 * 
	 * @return
	 */
	public abstract String getRecordsTotalQuery();

	/**
	 * HQL to query records [with parameters supplied]
	 * 
	 * @return String of HQL to
	 */
	public abstract String getFilteredRecordQuery();

	/**
	 * HQL to count filtered records [with parameters supplied]
	 * 
	 * @return String of HQL to
	 */
	public abstract String getFilteredRecordCountQuery();

	/**
	 * Override if DataTable require search filter
	 * 
	 * @param dataTableRequest
	 * @param query
	 */
	protected void setUpQueryParams(DataTableParam dataTableRequest, Query query) {

	}
}
