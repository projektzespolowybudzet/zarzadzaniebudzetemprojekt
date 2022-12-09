package am.jsl.dolarek.util;

import java.text.DecimalFormat;

/**
 * Definiuje stałe.
 */
public class Constants {
    public static final String SLASH = "/";
    public static final String UTF8 = "UTF-8";
    public static final String LANGUAGE = "language";
    public static String PAGE = "page";
    public static final int PAGE_SIZE = 10;

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_FORMAT_L = "MM/dd/yyyy";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.00");

    public static final String USER_PROFILE_DEFAULT_IMG = "/static/img/profile.jpg";

    /**
     * Wygenerowane przez użytkownika ścieżki zasobów.
     * Dla użytkownika Każdy zostanie utworzony osobny folder o nazwie składającej się z identyfikatora użytkownika.
     */
    public static final String USER_IMG_PATH = "/userimg/";
    public static final String USER_IMG_PATHPATTERN = "/userimg/**";
    public static final String USER_HTML_PATH = "/userhtml/";
    public static final String USER_HTML_PATHPATTERN = "/userhtml/**";

    public static final int PROFILE_IMG_WIDTH = 225;
    public static final int PROFILE_IMG_HEIGHT = 225;
}
