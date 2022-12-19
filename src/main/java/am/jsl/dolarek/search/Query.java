package am.jsl.dolarek.search;

/**
 *Podstawowa klasa zapytań, która zawiera informacje o stronicowaniu do wysyłania zapytań o elementy w sposób ogólny.
 *@param <T> parametr typu
 */
public class Query<T> {

  /**
   * Identyfikator elementu, który można znaleźć.
   */
  protected long id;
  /**
   * Bieżąca strona do zapytania.
   */
  protected int page = 1;
  /**
   * Liczba elementów, które należy zwrócić.
   */
  protected int pageSize = -1;
  /**
   * Sortowanie
   */
  protected String sortBy;
  /**
   * Prawda, jeśli sortowanie w kolejności rosnącej.
   */
  protected boolean asc;

  /**
   * Konstruktor domyślny.
   */
  public Query() {}

  /**
   * Konstruktor Query.
   *
   * @param page     strona
   * @param pageSize rozmiar strony
   */
  public Query(int page, int pageSize) {
    this.page = page;
    this.pageSize = pageSize;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets page.
   *
   * @return the page
   */
  public int getPage() {
    return page;
  }

  /**
   * Sets page.
   *
   * @param page the page
   */
  public void setPage(int page) {
    this.page = page;
  }

  /**
   * Gets page size.
   *
   * @return the page size
   */
  public int getPageSize() {
    return pageSize;
  }

  /**
   * Sets page size.
   *
   * @param pageSize the page size
   */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  /**
   * Gets sort by.
   *
   * @return the sort by
   */
  public String getSortBy() {
    return sortBy;
  }

  /**
   * Sets sort by.
   *
   * @param sortBy the sort by
   */
  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }

  /**
   * Is asc boolean.
   *
   * @return the boolean
   */
  public boolean isAsc() {
    return asc;
  }

  /**
   * Sets asc.
   *
   * @param asc the asc
   */
  public void setAsc(boolean asc) {
    this.asc = asc;
  }
}
