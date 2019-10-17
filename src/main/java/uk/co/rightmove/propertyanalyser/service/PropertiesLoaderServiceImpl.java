package uk.co.rightmove.propertyanalyser.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import uk.co.rightmove.propertyanalyser.model.Property;
import uk.co.rightmove.propertyanalyser.util.PropertyAnalyserConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


/**
 * Service implementation class of PropertiesLoaderService interface.
 */
@Service
public class PropertiesLoaderServiceImpl implements PropertiesLoaderService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Property> loadPropertiesFromJson(JSONObject propertiesJsonObject) {

        JSONArray propertiesArrayJsonObject = propertiesJsonObject.getJSONArray(PropertyAnalyserConstants.PROPERTIES);
        List<Property> properties = new ArrayList<>();

        IntStream.range(0, propertiesArrayJsonObject.length())
                .mapToObj(propertiesArrayJsonObject::getJSONObject)
                .forEachOrdered(tempJsonObject -> properties.add(Property.builder()
                        .propertyReference(tempJsonObject.getInt(PropertyAnalyserConstants.PROPERTY_REFERENCE))
                        .price(tempJsonObject.getInt(PropertyAnalyserConstants.PRICE))
                        .bedrooms(tempJsonObject.getInt(PropertyAnalyserConstants.BEDROOMS))
                        .bathrooms(tempJsonObject.getInt(PropertyAnalyserConstants.BATHROOMS))
                        .houseNumber(tempJsonObject.getString(PropertyAnalyserConstants.HOUSE_NUMBER))
                        .address(tempJsonObject.getString(PropertyAnalyserConstants.ADDRESS))
                        .region(tempJsonObject.getString(PropertyAnalyserConstants.REGION))
                        .postcode(tempJsonObject.getString(PropertyAnalyserConstants.POSTCODE))
                        .propertyType(tempJsonObject.getString(PropertyAnalyserConstants.PROPERTY_TYPE))
                        .build()));

        return properties;
    }
}
