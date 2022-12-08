package am.jsl.personalfinances.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;

/**
*Klasa podstawowa z obsługą JDBC Dao Springframework.
*<p>Wymaga ustawienia {@link javax.sql.DataSource}.
*/
public class AbstractDaoImpl extends JdbcDaoSupport {

/**
*Zezwolenie na użycie nazwanych parametrów
*/
	protected NamedParameterJdbcTemplate parameterJdbcTemplate;

/**
*Tworzy nowy Abstract Dao Impl dla danego {@link Data Source}.
*@param dataSource źródło danych JDBC, aby uzyskać dostęp
*/
	public AbstractDaoImpl(DataSource dataSource) {
		super();
		setDataSource(dataSource);
		parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
}
