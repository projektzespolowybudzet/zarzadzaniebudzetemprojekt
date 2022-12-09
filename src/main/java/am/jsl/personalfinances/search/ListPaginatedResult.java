package am.jsl.personalfinances.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
*Zbiera wyniki stronicowania list.
*/
public class ListPaginatedResult<T> implements Serializable {

	private long total = 0;
	private List<T> list = new ArrayList<T>();

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
