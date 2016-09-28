package com.spring.datatable.app.dataTable.data;

import java.util.ArrayList;
import java.util.List;

/**
 * DataTableRequest https://datatables.net/manual/server-side
 * 
 * @author ponleu.chhorn
 */
public class DataTableParam {
	private int draw;
	private int start;
	private int length;
	private List<DataTableColumn> columns;
	private List<DataTableOrder> order;
	private DataTableSearch search;
	private long _;

	public DataTableParam() {
		this.columns = new ArrayList<>();
		this.order = new ArrayList<>();
		this.search = new DataTableSearch();
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<DataTableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DataTableColumn> columns) {
		this.columns = columns;
	}

	public List<DataTableOrder> getOrder() {
		return order;
	}

	public void setOrder(List<DataTableOrder> order) {
		this.order = order;
	}

	public DataTableSearch getSearch() {
		return search;
	}

	public void setSearch(DataTableSearch search) {
		this.search = search;
	}

	public long get_() {
		return _;
	}

	public void set_(long _) {
		this._ = _;
	}

}
