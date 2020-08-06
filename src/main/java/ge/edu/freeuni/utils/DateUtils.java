package ge.edu.freeuni.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public final class DateUtils {

    public static String millisecondsToTimeFormat(long milliseconds){
        long minute = milliseconds / 60000;
        long second = (milliseconds % 60000) / 1000;
        long millisecond = milliseconds % 1000;
        String result = String.format(
                "%d:%d:%d",
                minute,
                second,
                millisecond
        );
        return result;
    }

    public static long timeToMillisecondsFormat(String time){
        StringTokenizer tokenizer = new StringTokenizer(time, ":");
        long minute = Long.parseLong(tokenizer.nextToken());
        long second = Long.parseLong(tokenizer.nextToken());
        long millisecond = Long.parseLong(tokenizer.nextToken());
        long milliseconds = minute * 60000 + second * 1000 + millisecond;
        return millisecond;
    }

}
