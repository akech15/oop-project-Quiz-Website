package ge.edu.freeuni.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {

    public static java.sql.Date getDbDate(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = formatter.format(date);
            java.util.Date dateStr = formatter.parse(d);
            return new java.sql.Date(dateStr.getTime());
        } catch (Exception ex) {
            throw new IllegalStateException();
        }
    }

}
