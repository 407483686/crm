package CRM.entity;

import java.util.List;

import com.google.gson.annotations.Expose;

//用来做datagrid分页查询的对象
public class EasyUIDatagrid {
	@Expose
	private int total;
	@Expose
	private List<?> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
