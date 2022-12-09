package am.jsl.dolarek.search;

/**
*Podstawowa klasa zapytań, która zawiera informacje o stronicowaniu do wysyłania zapytań o elementy w sposób ogólny.
*@param <T> parametr typu
*/
public class Query<T> {
	protected long id;
	protected int page = 1;
	protected int pageSize = -1;
	protected String sortBy;
	protected boolean asc;

	public Query() {
	}

	public Query(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}
}
