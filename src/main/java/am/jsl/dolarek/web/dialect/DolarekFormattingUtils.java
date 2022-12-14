package am.jsl.dolarek.web.dialect;

import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

/**
 * Date time formatter used by Thymeleaf that uses {@link PrettyTime} library.
 */
public class DolarekFormattingUtils {
    private final PrettyTime prettyTime;

    /**
     * Constructs a new DolarekFormattingUtils for the given locale.
     * @param locale the locale
     */
    public DolarekFormattingUtils(final Locale locale) {
        super();
        this.prettyTime = new PrettyTime(locale);
    }

    /**
     * Formats the given date using {@link PrettyTime} library.
     * @param date the Date
     * @return the string representing formatted date
     */
    public String prettyDate(final Date date) {
        return prettyTime.format(date);
    }

    /**
     * Formats the given timestamp using {@link PrettyTime} library.
     * @param timestamp the Timestamp
     * @return the string representing formatted timestamp
     */
    public String prettyTime(final Timestamp timestamp) {
        return prettyTime.format(timestamp);
    }
}
