package vpn_keep_alive.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Logger {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
    public static void printLogOnConsole(final String msg){
        System.out.println(
                surroundWithBrackets(DATE_TIME_FORMATTER.format(LocalDateTime.now())) + " " + msg
        );
    }

    private static String surroundWithBrackets(final String msg){
        return "[" + msg + "]";
    }
}
