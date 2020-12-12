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
        cal.set(2020,11, 6,17, 54, 47);
        Date start = cal.getTime();
        cal.set(2020,11, 6,19, 46, 22);
        Date finish = cal.getTime();
        System.out.println(commons.displayFinishTime(start, finish));
    }
}