package uk.co.rightmove.propertyanalyser.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesAnalyserServiceImplTest {

    private static final double DELTA = 1e-15;

    @Autowired
    private PropertiesAnalyserServiceImpl propertiesAnalyserService;

    @Test
    public void testCalculateMeanPriceOfPropertiesBasedOnPostcodeOutward() {

        assertEquals(String.format("Expected result is %s", 0.0),0.0,
                this.propertiesAnalyserService.calculateMeanPriceOfPropertiesBasedOnPostcodeOutward("HHH"),
                DELTA);

        assertEquals(String.format("Expected result is %s", 1158750.0), 1158750.0,
                this.propertiesAnalyserService.calculateMeanPriceOfPropertiesBasedOnPostcodeOutward("W1F"),
                DELTA);

        assertEquals(String.format("Expected result is %s", 2440000.0), 2440000.0,
                this.propertiesAnalyserService.calculateMeanPriceOfPropertiesBasedOnPostcodeOutward("GU13"),
                DELTA);

        assertEquals(String.format("Expected result is %s", 230861.0),230861.0,
                this.propertiesAnalyserService.calculateMeanPriceOfPropertiesBasedOnPostcodeOutward("SH1"),
                DELTA);
    }

    @Test
    public void calculateDifferenceBetweenAveragePricesOfTwoPropertyTypes() {

        assertEquals(String.format("Expected result is %s", 92386.0), 92386.0,
                this.propertiesAnalyserService
                        .calculateDifferenceBetweenAveragePricesOfTwoPropertyTypes("Detached", "Flat"),
                DELTA);

        assertEquals(String.format("Expected result is %s", 1078250.0), 1078250.0,
                this.propertiesAnalyserService
                        .calculateDifferenceBetweenAveragePricesOfTwoPropertyTypes("Mansion", "Flat"),
                DELTA);
    }

    @Test
    public void findTopTenPercentMostExpensiveProperties() {

        this.propertiesAnalyserService.findTopXPercentOfMostExpensiveProperties(0.10).forEach(p -> {
            assertNotNull(p);
            assertEquals(String.format("Expected result is %s", "Brighton Road "), "Brighton Road ", p.getAddress());
            assertEquals(String.format("Expected result is %s", "GU13 4DD"), "GU13 4DD", p.getPostcode());
            assertEquals(String.format("Expected result is %s", 7500000), 7500000, p.getPrice());
            assertEquals(String.format("Expected result is %s", 12), 12, p.getPropertyReference());
            assertEquals(String.format("Expected result is %s", 11), 11, p.getBedrooms());
            assertEquals(String.format("Expected result is %s", 4), 4, p.getBathrooms());
            assertEquals(String.format("Expected result is %s", ""), "", p.getHouseNumber());
            assertEquals(String.format("Expected result is %s", "Surrey"), "Surrey", p.getRegion());
            assertEquals(String.format("Expected result is %s", "Mansion"), "Mansion", p.getPropertyType());
        });

    }
}