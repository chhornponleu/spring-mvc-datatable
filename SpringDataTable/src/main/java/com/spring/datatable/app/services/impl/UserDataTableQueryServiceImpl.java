package com.spring.datatable.app.services.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.spring.datatable.app.dataTable.data.DataTableParam;
import com.spring.datatable.app.dataTable.repositories.AbstrDataTableQueryRepository;
import com.spring.datatable.app.models.User;
import com.spring.datatable.app.services.UserDataTableService;

@Service("userDataTableQueryServiceImpl")
public class UserDataTableQueryServiceImpl extends AbstrDataTableQueryRepository<User> implements UserDataTableService {

	@Override
	public String getRecordsTotalQuery() {
		return "SELECT COUNT(*) FROM User";
	}

	@Override
	public String getFilteredRecordCountQuery() {
		return "SELECT COUNT(*) FROM User where username LIKE :username";// ORDER BY :orderColumn :orderDir";
	}

	@Override
	public String getFilteredRecordQuery() {
		return "FROM User where username LIKE :username";// ORDER BY :orderColumn :orderDir";
	}

	@Override
	protected void setUpQueryParams(DataTableParam request, Query query) {
		query.setParameter("username", "%" + request.getSearch().getValue() + "%");

		/*String orderColumn = "username";
		String orderDir = "ASC";
		if (!request.getOrder().isEmpty()) {
			DataTableOrder order = request.getOrder().get(0);
			switch (order.getColumn()) {
			case 1:
				orderColumn = "password";
				break;

			default:
				orderColumn = "username";
				break;
			}

		}
		query.setParameter("orderColumn", orderColumn);
		query.setParameter("orderDir", orderDir);*/
	}
}
