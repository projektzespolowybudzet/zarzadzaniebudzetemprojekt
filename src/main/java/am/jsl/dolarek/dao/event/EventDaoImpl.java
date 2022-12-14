package am.jsl.dolarek.dao.event;

import am.jsl.dolarek.dao.AbstractDaoImpl;
import am.jsl.dolarek.dao.DBUtils;
import am.jsl.dolarek.domain.event.Event;
import am.jsl.dolarek.dto.event.EventListDTO;
import am.jsl.dolarek.search.EventSearchQuery;
import am.jsl.dolarek.search.ListPaginatedResult;
import am.jsl.dolarek.util.TextUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Implementacja interfejsu Dao do uzyskiwania dostępu do obiektu domeny {@link Event}.
 */
@Repository("eventDao")
@Lazy
public class EventDaoImpl extends AbstractDaoImpl implements EventDao {

  private EventLisBeanMapper eventListBeanMapper = new EventLisBeanMapper();

  private static final Map<String, String> sortByColumnMap = new HashMap<>();

  static {
    sortByColumnMap.put("eventType", "e.event_type AAA, e.id ");
    sortByColumnMap.put("message", "e.message AAA, e.id ");
    sortByColumnMap.put("performedBy", "u.login AAA, e.id ");
    sortByColumnMap.put("createdAt", "e.created_at AAA, e.id ");
  }

  @Autowired
  EventDaoImpl(DataSource dataSource) {
    super(dataSource);
  }

  private static final String saveEventSql =
    "insert into event (id, event_type, message, performed_by, created_at) " +
    " values(:id, :event_type, :message, :performed_by, sysdate())";

  /**
   * Save the given event to the database.
   *
   * @param event - the event to be saved
   */
  @Override
  public void saveEvent(Event event) {
    long id = DBUtils.getNextId(getJdbcTemplate(), "event");
    event.setId(id);

    Map<String, Object> params = new HashMap<String, Object>();
    params.put(DBUtils.id, id);
    params.put(EventMapper.EVENT_TYPE, event.getEventType());
    params.put(EventMapper.MESSAGE, event.getMessage());
    params.put(EventMapper.PERFORMED_BY, event.getPerformedBy());
    parameterJdbcTemplate.update(saveEventSql, params);
  }

  private static final String searchCommonSql =
    "select {columns} " +
    "from event e " +
    "left join pf_user u on u.id = e.performed_by " +
    "where 1=1 ";
  private static final String columnsSql =
    "distinct e.id, e.event_type, e.message, u.login as performed_by, e.created_at ";

  /**
   * This method will search for events that match the given search query.
   *
   * @param searchQuery - the EventSearchQuery to be used to search for events
   */
  @Override
  public ListPaginatedResult<EventListDTO> search(
    EventSearchQuery searchQuery
  ) {
    int rowsPerPage = searchQuery.getPageSize();
    int pageNum = searchQuery.getPage();
    int offset = (pageNum - 1) * rowsPerPage;
    String sortBy = "createdAt";
    boolean ascending = false;

    ListPaginatedResult<EventListDTO> pagingResult = new ListPaginatedResult<EventListDTO>();
    Map<String, Object> params = new HashMap<String, Object>();
    String whereClause = createtWhereClouse(searchQuery, params);

    String searchSql = searchCommonSql;
    searchSql = searchSql.replace("{columns}", columnsSql);

    StringBuilder limit = new StringBuilder();
    limit.append(" limit ").append(offset);
    limit.append(", ").append(rowsPerPage);

    StringBuilder finalSql = new StringBuilder();
    finalSql.append(searchSql);
    finalSql.append(whereClause);
    finalSql.append(createOrderByClause(sortBy, ascending));
    finalSql.append(limit.toString());

    List<EventListDTO> list = parameterJdbcTemplate.query(
      finalSql.toString(),
      params,
      eventListBeanMapper
    );

    searchSql = searchCommonSql;
    searchSql = searchSql.replace("{columns}", "count(e.id) as cnt");

    finalSql = new StringBuilder();
    finalSql.append(searchSql);
    finalSql.append(whereClause);

    long count = parameterJdbcTemplate.queryForObject(
      finalSql.toString(),
      params,
      Long.class
    );

    pagingResult.setTotal(count);
    pagingResult.setList(list);
    return pagingResult;
  }

  /**
   * Creates a clouse where clouse query.
   *
   * @param searchQuery - the search query to be used for the query
   * @param params - the map of parameters to be passed to the query
   */
  private String createtWhereClouse(
    EventSearchQuery searchQuery,
    Map<String, Object> params
  ) {
    StringBuilder where = new StringBuilder();

    if (searchQuery.getEventType() != 0) {
      where.append(" and e.event_type = :event_type");
      params.put("event_type", searchQuery.getEventType());
    }

    if (searchQuery.getPerformedBy() != 0) {
      where.append(" and e.performed_by = :performed_by");
      params.put("performed_by", searchQuery.getPerformedBy());
    }

    if (searchQuery.getCreatedAtStart() != null) {
      where.append(" and e.created_at >= :createdAtStart");
      params.put("createdAtStart", searchQuery.getCreatedAtStart());
    }
    if (searchQuery.getCreatedAtEnd() != null) {
      where.append(" and e.created_at <= :createdAtEnd");
      params.put("createdAtEnd", searchQuery.getCreatedAtEnd());
    }

    String message = searchQuery.getMessage();

    if (TextUtils.hasText(message)) {
      message = message.replace("*", "%");
      where.append(" and e.message like :message");
      params.put("message", message);
    }
    return where.toString();
  }

  /**
   * Creates the order by clause for the given sortBy column.
   *
   * @param sortBy - the column name to sort by
   * @param ascending - true if the order by clause is ascending or descending
   */
  private String createOrderByClause(String sortBy, boolean ascending) {
    String result = "";

    if (TextUtils.hasText(sortBy)) {
      String sortByCol = sortByColumnMap.get(sortBy);

      if (TextUtils.hasText(sortByCol)) {
        result += " order by " + sortByCol;

        if (ascending) {
          result = result.replaceAll("AAA", "");
        } else {
          result = result.replaceAll("AAA", "desc");
        }
      }
    }

    return result;
  }
}
