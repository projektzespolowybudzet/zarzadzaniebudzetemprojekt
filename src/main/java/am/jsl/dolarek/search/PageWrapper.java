package am.jsl.dolarek.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * PageWrapper używany na stronach thymeleaf do wyświetlania pojedynczej strony
 * z obsługą paginacji.
 */
public class PageWrapper<T> {

  public static final int MAX_PAGE_ITEM_DISPLAY = 5;
  private boolean firstPageEnabled;
  private boolean previousPageEnabled;
  private boolean nextPageEnabled;
  private boolean lastPageEnabled;

  private int size = 0;
  private long dataCount;
  private int currentPage;
  private int pageSize;
  private int totalPages = 0;
  private List<T> content;
  private List<PageItem> items;

  public PageWrapper(
    ListPaginatedResult<T> result,
    int currentPage,
    int pageSize
  ) {
    this(result.getList(), result.getTotal(), currentPage, pageSize);
  }

  public PageWrapper(
    List<T> content,
    long total,
    int currentPage,
    int pageSize
  ) {
    this.content = content;
    this.dataCount = total;
    this.currentPage = currentPage;
    this.pageSize = pageSize;
    this.totalPages = (int) dataCount / pageSize;

    if ((dataCount % pageSize) > 0) {
      totalPages += 1;
    }

    if (totalPages <= 1) {
      return;
    }
    boolean firstLastPageEnabled = (totalPages >= 2);

    if (currentPage == 1) {
      firstPageEnabled = false;
      previousPageEnabled = false;
      nextPageEnabled = true;
      lastPageEnabled = firstLastPageEnabled;
    } else if (currentPage == totalPages) {
      firstPageEnabled = firstLastPageEnabled;
      previousPageEnabled = true;
      nextPageEnabled = false;
      lastPageEnabled = false;
    } else {
      firstPageEnabled = firstLastPageEnabled && (currentPage >= 2);
      previousPageEnabled = true;
      nextPageEnabled = true;
      lastPageEnabled =
        firstLastPageEnabled && (currentPage < (totalPages - 1));
    }

    int start, size;

    if (totalPages <= pageSize) {
      start = 1;
      size = totalPages;
    } else {
      if (currentPage <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
        start = 1;
        size = MAX_PAGE_ITEM_DISPLAY;
      } else if (currentPage >= totalPages - MAX_PAGE_ITEM_DISPLAY / 2) {
        start = totalPages - MAX_PAGE_ITEM_DISPLAY + 1;
        size = MAX_PAGE_ITEM_DISPLAY;
      } else {
        start = currentPage - MAX_PAGE_ITEM_DISPLAY / 2;
        size = MAX_PAGE_ITEM_DISPLAY;
      }
    }

    items = new ArrayList<PageItem>();

    IntStream
      .range(0, size)
      .forEach(i ->
        items.add(new PageItem(start + i, (start + i) == currentPage))
      );
  }

  /**
   * Gets the state of previousPageEnabled property.
   *
   * @return true if the previous page is enabled
   */
  public boolean isPreviousPageEnabled() {
    return previousPageEnabled;
  }

  /**
   * Sets whether the previous page is enabled.
   *
   * @param previousPageEnabled - true if previous page is enabled
   */
  public void setPreviousPageEnabled(boolean previousPageEnabled) {
    this.previousPageEnabled = previousPageEnabled;
  }

  /**
   * Gets the state of firstPageEnabled property.
   *
   * @return true if the first page is enabled.
   */
  public boolean isFirstPageEnabled() {
    return firstPageEnabled;
  }

  /**
   * Sets whether the first page is enabled.
   *
   * @param firstPageEnabled - true if the first page is enabled
   */
  public void setFirstPageEnabled(boolean firstPageEnabled) {
    this.firstPageEnabled = firstPageEnabled;
  }

  /**
   * Gets the state of the nextPageEnabled property.
   *
   * @return true if the next page is enabled.
   */
  public boolean isNextPageEnabled() {
    return nextPageEnabled;
  }

  /**
   * @param nextPageEnabled
   */
  /**
   * Sets the nextPageEnabled value for this ServiceContext.
   *
   * @param nextPageEnabled - true if the next page is enabled
   */
  public void setNextPageEnabled(boolean nextPageEnabled) {
    this.nextPageEnabled = nextPageEnabled;
  }

  /**
   * Gets the state of the lastPageEnabled property.
   *
   * @return true if the last page is enabled.
   */
  public boolean isLastPageEnabled() {
    return lastPageEnabled;
  }

  /**
   * Sets whether the last page is enabled.
   *
   * @param lastPageEnabled - true if the last page is enabled
   */
  public void setLastPageEnabled(boolean lastPageEnabled) {
    this.lastPageEnabled = lastPageEnabled;
  }

  /**
   * Gets the size of the report.
   *
   * @return the size of the report.
   */
  public int getSize() {
    return size;
  }

  /**
   * Sets the size of the report.
   *
   * @param size - the size of the report.
   */
  public void setSize(int size) {
    this.size = size;
  }

  /**
   * Gets the totalPages value for this ReportMonthBO.
   *
   * @return total pages of the report
   */
  public int getTotalPages() {
    return totalPages;
  }

  /**
   * Sets the totalPages value for this Report.
   *
   * @param totalPages - The totalPages value to set.
   */
  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  /**
   * Gets the value of the content property.
   *
   * @return List of the content
   */
  public List<T> getContent() {
    return content;
  }

  /**
   * Sets the content of the list.
   *
   * @param content - The content of the item.
   */
  public void setContent(List<T> content) {
    this.content = content;
  }

  /**
   * Gets the value of the items property.
   *
   * @return List<PageItem>
   */
  public List<PageItem> getItems() {
    return items;
  }

  /**
   * Sets the items value for this PageItem.
   *
   * @param items - The items to set.
   */
  public void setItems(List<PageItem> items) {
    this.items = items;
  }

  /**
   * Gets the current page number.
   *
   * @return the current page number.
   */
  public int getCurrentPage() {
    return currentPage;
  }

  /**
   * Sets the current page number.
   *
   * @param currentPage - The current page number.
   */
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  /**
   * Gets the value of the dataCount property.
   *
   * @return value of the dataCount property.
   */
  public long getDataCount() {
    return dataCount;
  }

  /**
   * Sets the dataCount attribute value.
   *
   * @param dataCount - The dataCount to set.
   */
  public void setDataCount(long dataCount) {
    this.dataCount = dataCount;
  }

  /**
   * Gets the value of the pageSize property.
   *
   * @return value of the pageSize property.
   */
  public int getPageSize() {
    return pageSize;
  }

  /**
   * Sets the pageSize value for this PageSize.
   *
   * @param pageSize - The new page size.
   */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public class PageItem {

    private int number;
    private boolean current;

    public PageItem(int number, boolean current) {
      this.number = number;
      this.current = current;
    }

    /**
     * Gets the number of the current item.
     *
     * @return the number of the current item.
     */
    public int getNumber() {
      return this.number;
    }

    /**
     * Gets the state of the isCurrent property.
     *
     * @return true if the current state of the object is currently being processed.
     */
    public boolean isCurrent() {
      return this.current;
    }
  }
}
