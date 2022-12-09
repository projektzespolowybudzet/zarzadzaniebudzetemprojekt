package am.jsl.dolarek.web.dialect;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

/**
 * The Dolarek is custom Thymeleaf expression object for date time formatting purposes.
 */
public final class Dolarek {
    /**
     * Formatter Utils.
     */
    private DolarekFormattingUtils formattingUtils;

    /**
     * Creates a new Dolarek with given locale and time zone.
     * @param locale the Locale
     */
    public Dolarek(final Locale locale) {
        super();
        this.formattingUtils = new DolarekFormattingUtils(locale);
    }

    /**
     * Called by Thymeleaf engine for formatting the given date.
     * @param date the Date
     * @return string representing formatted date
     */
    public String prettyDate(final Date date) {
        return formattingUtils.prettyDate(date);
    }

    /**
     * Called by Thymeleaf engine for formatting the given timestamp.
     * @param timestamp the timestamp to format
     * @return string representing formatted timestamp
     */
    public String prettyTime(final Timestamp timestamp) {
        return formattingUtils.prettyTime(timestamp);
    }
}
