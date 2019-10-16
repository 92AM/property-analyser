package uk.co.rightmove.propertyanalyser.util;

import lombok.experimental.UtilityClass;

/**
 * Utility class that hold all the constant variables used across the project.
 */
@UtilityClass
public final class PropertyAnalyserConstants {

    public static final String DATASET = "property-data.json";

    public static final String PROPERTIES = "properties";
    public static final String PROPERTY_REFERENCE = "propertyReference";
    public static final String PRICE = "price";
    public static final String BEDROOMS = "bedrooms";
    public static final String BATHROOMS = "bathrooms";
    public static final String HOUSE_NUMBER = "houseNumber";
    public static final String ADDRESS = "address";
    public static final String REGION = "region";
    public static final String POSTCODE = "postcode";
    public static final String PROPERTY_TYPE = "propertyType";

    public static final String PERCENT_REGEX = "^(?:0*(?:\\.\\d+)?|1(\\.0*)?)$";
}
