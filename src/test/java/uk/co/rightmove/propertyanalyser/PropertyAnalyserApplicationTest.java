package uk.co.rightmove.propertyanalyser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PropertyAnalyserApplication.class)
public class PropertyAnalyserApplicationTest {

    @Test
    public void testPropertyAnalyserApplicationMainMethod() {

        boolean hasAppStarted = false;

        try {

            PropertyAnalyserApplication.main(new String[]{});
            hasAppStarted = true;

        } catch (Exception ex) {

            System.out.println(String.format("Application failed to start up, reason : %s", ex));

        } finally {

            assertTrue(String.format("Application should run, but it has failed to boot up. Start up status : %s",
                    hasAppStarted),
                    hasAppStarted);
        }
    }
}