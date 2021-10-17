package se.rich.grtiming.process.util;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

public class CommonsTest extends TestCase {

    private Commons commons;

    public void setUp() throws Exception {
        commons = new Commons();
    }

    public void testDisplayFinishTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(2020,11, 6,17, 54, 37);
        cal.set(Calendar.MILLISECOND, 977);
        Date start = cal.getTime();
        cal.set(2020,11, 6,19, 46, 39);
        cal.set(Calendar.MILLISECOND, 239);
        Date finish = cal.getTime();
        System.out.println(commons.displayFinishTime(start, finish));
    }
}