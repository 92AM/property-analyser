package uk.co.rightmove.propertyanalyser.service;

import uk.co.rightmove.propertyanalyser.model.Property;

import java.util.List;

/**
 * This service class carries out various calculations based on the data that is available on  "property-data.json"
 * dataset.
 */
public interface PropertiesAnalyserService {

    /**
     * Calculates the mean price of properties based on postcode outward.
     *
     * @param postcodeOutward String
     * @return mean price in double
     */
    double calculateMeanPriceOfPropertiesBasedOnPostcodeOutward(String postcodeOutward);

    /**
     * Calculates the difference between average price of two property types, this method will work out the average
     * price of the first property type and the second property type. This method will then deduct the average of
     * the second property type from the first property type.
     *
     * @param firstPropertyType String
     * @param secondPropertyType String
     * @return difference in average in double
     */
    double calculateDifferenceBetweenAveragePricesOfTwoPropertyTypes(String firstPropertyType,
                                                                     String secondPropertyType);

    /**
     * Finds list of most expensive properties which falls within the top 10% of the price bracket.
     *
     * @return List of Property(s)
     */
    List<Property> findTopXPercentOfMostExpensiveProperties(double percent);
}
