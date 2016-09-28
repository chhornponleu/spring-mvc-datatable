package com.spring.datatable.app.services.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.spring.datatable.app.dataTable.data.DataTableOrder;
import com.spring.datatable.app.dataTable.data.DataTableParam;
import com.spring.datatable.app.dataTable.repositories.AbstrDataTableRepository;
import com.spring.datatable.app.models.User;
import com.spring.datatable.app.services.UserDataTableService;

@Service("userDataTableServiceImpl")
public class UserDataTableServiceImpl extends AbstrDataTableRepository<User> implements UserDataTableService {

	@Override
	protected Long getRecordsTotal() {
		// String hql = "SELECT COUNT(*) FROM User";
		Criteria cri = this.getSession().createCriteria(User.class);
		cri.setProjection(Projections.rowCount());
		return (Long) cri.uniqueResult();
	}

	@Override
	protected Long getFilteredRecord(DataTableParam request) {
		// String hql = "SELECT COUNT(*) FROM User where username LIKE
		// :username";
		Criteria cri = this.getSession().createCriteria(User.class);
		cri.add(Restrictions.like("username", "%" + request.getSearch().getValue() + "%"));
		cri.setProjection(Projections.rowCount());
		return (Long) cri.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	protected List<User> getPagingData(DataTableParam request) {
		// String hql = "FROM User where username LIKE :username ORDER BY
		// :orderColumn :orderDir";
		Criteria cri = this.getSession().createCriteria(User.class);
		cri.add(Restrictions.like("username", "%" + request.getSearch().getValue() + "%"));
		cri.setFirstResult(request.getStart());
		cri.setMaxResults(request.getLength());
		if (!request.getOrder().isEmpty()) {
			DataTableOrder order = request.getOrder().get(0);
			switch (order.getColumn()) {
			case 0:
				cri.addOrder(this.buildOrder("userId", order.getDir()));
				break;

			case 1:
				cri.addOrder(this.buildOrder("username", order.getDir()));

				break;
			default:
				break;
			}
		}
		return cri.list();
	}

}
