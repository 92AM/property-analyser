package uk.co.rightmove.propertyanalyser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.rightmove.propertyanalyser.model.Property;
import uk.co.rightmove.propertyanalyser.util.JSONLoader;
import uk.co.rightmove.propertyanalyser.util.PropertyAnalyserConstants;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation class of PropertiesAnalyserService interface.
 */
@Service
public class PropertiesAnalyserServiceImpl implements PropertiesAnalyserService {

    private final List<Property> properties;

    /**
     * Overloaded constructor of PropertiesAnalyserServiceImpl class.
     *
     * @param propertiesLoaderService PropertiesLoaderService
     * @throws IOException exception
     */
    @Autowired
    PropertiesAnalyserServiceImpl(PropertiesLoaderService propertiesLoaderService) throws IOException {

        this.properties = propertiesLoaderService.loadPropertiesFromJson(JSONLoader
                .loadJsonFromFile(PropertyAnalyserConstants.DATASET));
    }

    /**
     * {@inheritDoct}
     */
    @Override
    public double calculateMeanPriceBasedOnPostcodeOutward(String postcodeOutward) {

        return properties
                .stream()
                .filter(p -> p.getPostcode().startsWith(postcodeOutward))
                .mapToDouble(Property::getPrice)
                .average()
                .orElse(0);
    }

    /**
     * {@inheritDoct}
     */
    @Override
    public double calculateAveragePriceDifferenceOfTwoPropertyTypes(String firstPropertyType,
                                                                    String secondPropertyType) {

        int firstPropertyTypeTotalPrice = 0;
        int secondPropertyTypeTotalPrice = 0;
        int propertyCount = 0;

        for (Property property : properties) {
            if (property.getPropertyType().equals(firstPropertyType)) {

                firstPropertyTypeTotalPrice += property.getPrice();
                propertyCount++;
            }

            if (property.getPropertyType().equals(secondPropertyType)) {

                secondPropertyTypeTotalPrice += property.getPrice();
                propertyCount++;
            }
        }

        return (firstPropertyTypeTotalPrice / propertyCount) -
                (secondPropertyTypeTotalPrice / propertyCount);
    }

    /**
     * {@inheritDoct}
     */
    @Override
    public List<Property> findTopXPercentOfMostExpensiveProperties(double percent) {

        int maxPrice = properties
                .stream()
                .max(Comparator.comparing(Property::getPrice))
                .get()
                .getPrice();

        double priceToBeat = maxPrice - (percent * maxPrice);

        return properties
                .stream()
                .filter(p -> p.getPrice() > priceToBeat)
                .collect(Collectors.toList());
    }
}
