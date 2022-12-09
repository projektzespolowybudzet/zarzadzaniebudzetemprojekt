package am.jsl.dolarek.web.dialect;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

/**
 * Custom Thymeleaf dialect.
 */
public class DolarekDialect extends AbstractDialect implements IExpressionObjectDialect {
    private final IExpressionObjectFactory PF_TRACKER_EXPRESSION_OBJECTS_FACTORY = new DolarekExpressionFactory();

    public DolarekDialect() {
        super("dolarek");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return PF_TRACKER_EXPRESSION_OBJECTS_FACTORY;
    }
}
