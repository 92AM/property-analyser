package uk.co.rightmove.propertyanalyser.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.rightmove.propertyanalyser.model.Property;
import uk.co.rightmove.propertyanalyser.util.JSONLoader;
import uk.co.rightmove.propertyanalyser.util.PropertyAnalyserConstants;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesLoaderServiceImplTest {

    @Autowired
    private PropertiesLoaderService propertiesLoaderService;

    @Test
    public void testLoadPropertiesFromJson() throws IOException {

        List<Property> properties =
                this.propertiesLoaderService.loadPropertiesFromJson(JSONLoader
                        .loadJsonFromFile(PropertyAnalyserConstants.DATASET));

        assertEquals("The list size should be 24", 24, properties.size());

        IntStream.range(0, properties.size()).forEach(idx -> assertEquals(String
                        .format("Expected property reference here is %s ", idx + 1),
                idx + 1, properties.get(idx).getPropertyReference()));
    }
}