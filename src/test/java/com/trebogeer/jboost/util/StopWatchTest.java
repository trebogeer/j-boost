package com.trebogeer.jboost.util;

import java.util.concurrent.TimeUnit;

/**
 * @author dimav
 *         Date: 4/4/13
 *         Time: 2:53 PM
 */
public class StopWatchTest {

    public static void main(String... args) {
        StopWatch sw = new StopWatch("testing stop watch");

        sw.start("loop");
        for (int i = 0; i < 100000; i++) {
                System.currentTimeMillis();
        }
        sw.stop();
        sw.start("next loop");

        for (int i = 0; i < 200000; i++) {
            System.currentTimeMillis();
        }
        sw.stop();
       // System.out.println(sw.shortSummary());
        System.out.println(sw.prettyPrint());

        sw = new StopWatch("testing stop watch - sec", true, TimeUnit.SECONDS);

        sw.start("loop - sec");
        for (int i = 0; i < 50000000; i++) {
            System.currentTimeMillis();
        }
        sw.stop();
        sw.start("next loop - sec");

        for (int i = 0; i < 30000000; i++) {
            System.currentTimeMillis();
        }
        sw.stop();
        //System.out.println(sw.shortSummary());
        System.out.println(sw.prettyPrint());
    }
}
