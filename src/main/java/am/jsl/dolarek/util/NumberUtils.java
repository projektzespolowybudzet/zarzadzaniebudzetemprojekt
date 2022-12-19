package am.jsl.dolarek.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Definiuje metody do zaokrąglania, formatowania liczb.
 */
public class NumberUtils {

  public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(
    "# ## ### ##0.00"
  );
  public static final DecimalFormat PERCENT_DECIMAL_FORMAT = new DecimalFormat(
    "# ### ### ##0.00"
  );

  public static double round(double value, int places) {
    if (places < 0) {
      throw new IllegalArgumentException();
    }

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  public static double round(double value) {
    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(0, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  public static String formatPercent(double amount) {
    return PERCENT_DECIMAL_FORMAT.format(amount);
  }

  public static String formatAmount(double amount) {
    return DECIMAL_FORMAT.format(amount);
  }
}
