package com.spring.datatable.app.dataTable.data;

import java.util.List;

/**
 * DataTableResponse 
 * ref: https://datatables.net/manual/server-side
 * @author ponleu.chhorn
 */
public class DataTableResponse<T> {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<T> data;
	private String error;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
