package uk.co.rightmove.propertyanalyser.service;

import org.json.JSONObject;
import uk.co.rightmove.propertyanalyser.model.Property;

import java.util.List;

/**
 * Populates list of Property objects from the property-data.json file.
 */
@FunctionalInterface
public interface PropertiesLoaderService {

    /**
     * This method loads all the properties from the JSON file and then populates a list of Property object(s).
     *
     * @param propertiesJsonObject JSONObject
     * @return List of Property
     */
    List<Property> loadPropertiesFromJson(JSONObject propertiesJsonObject);
}
