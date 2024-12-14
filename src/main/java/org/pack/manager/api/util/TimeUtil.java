package org.pack.manager.api.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeUtil {

    private static long startTime;
    private static String title;
    private static long totalElapsedTime;

    public static void restartTotalTime() {
        totalElapsedTime = 0;
    }

    public static void start(String title) {
        TimeUtil.title = title;
        startTime = System.currentTimeMillis();
    }

    public static void stopAndPrintElapsedTime(String title) {
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        totalElapsedTime += elapsedTime;

        log.info(">> ⏱ {}s [{}]", (elapsedTime / 1000.0), title);
    }

    public static void printTotalTime() {
        log.info("**********************************************************************");
        log.info("TOTAL Millis: {}", totalElapsedTime);
        log.info("TOTAL Seconds: {}", (totalElapsedTime / 1000.0));
        log.info("**********************************************************************");
    }
}