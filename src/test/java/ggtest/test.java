package ggtest;

import org.testng.annotations.Test;

public class test extends BaseTest {

    @Test
    public void runTest()  {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
