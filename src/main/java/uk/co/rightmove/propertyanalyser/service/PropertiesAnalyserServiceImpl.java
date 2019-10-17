package uk.co.rightmove.propertyanalyser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.co.rightmove.propertyanalyser.exception.PropertyAnalyserException;
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
     * Overloaded constructor of PropertiesAnalyserServiceImpl class, injecting PropertiesLoaderService into
     * PropertiesAnalyserServiceImpl in order to invoke loadPropertiesFromJson() method during bean creation to
     * load all Property(s) from provided dataset.
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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public double calculateAveragePriceDifferenceOfTwoPropertyTypes(String firstType,
                                                                    String secondType) {

        int firstTypeTotalPrice = 0;
        int secondTypeTotalPrice = 0;
        int propertyCount = 0;

        for (Property property : properties) {

            if (property.getPropertyType().equals(firstType)) {

                firstTypeTotalPrice += property.getPrice();
                propertyCount++;
            }

            if (property.getPropertyType().equals(secondType)) {

                secondTypeTotalPrice += property.getPrice();
                propertyCount++;
            }
        }

        return propertyCount == 0 ? 0 : Math.round((double) (firstTypeTotalPrice / propertyCount) -
                (double) (secondTypeTotalPrice / propertyCount));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Property> findTopXPercentOfMostExpensiveProperties(String percent) {

        try {

            if (!percent.matches(PropertyAnalyserConstants.PERCENT_REGEX)) {

                throw new PropertyAnalyserException(String.format(
                        "Input parameter (i.e. %s) doesn't match required regex of %s. A valid example is %s",
                        percent, PropertyAnalyserConstants.PERCENT_REGEX, "0.10"));

            } else {

                double percentAsDouble = Double.parseDouble(percent);

                int maxPrice = properties
                        .stream()
                        .max(Comparator.comparing(Property::getPrice))
                        .get()
                        .getPrice();

                double priceToBeat = maxPrice - (percentAsDouble * maxPrice);

                return properties
                        .stream()
                        .filter(p -> p.getPrice() > priceToBeat)
                        .collect(Collectors.toList());
            }

        } catch (NumberFormatException | PropertyAnalyserException ex) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Invalid parameter provided : %s \n | Reason : %s",
                            percent, ex.toString()), ex);
        }
    }
}
