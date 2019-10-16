package uk.co.rightmove.propertyanalyser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.co.rightmove.propertyanalyser.model.Property;
import uk.co.rightmove.propertyanalyser.service.PropertiesAnalyserService;

import java.util.List;

/**
 * Property Analyser REST controller class provides RESTful endpoint for all calling systems, through http protocol.
 */
@RestController
public class PropertiesAnalyserRestController {

    @Autowired
    private PropertiesAnalyserService propertiesAnalyserService;


    /**
     * Calling on this REST endpoint will result in invocation of 'calculateMeanPriceBasedOnPostcodeOutward()'
     * method to calculate the mean price of all properties based on given postcode outward.
     *
     * REST address : "/mean/{postcode}/outward"
     *
     * @param postcode String
     * @return mean price in double
     */
    @GetMapping(value = "/mean/{postcode}/outward")
    public double callCalculateMeanPriceBasedOnPostcodeOutward(@PathVariable String postcode) {

        return propertiesAnalyserService.calculateMeanPriceBasedOnPostcodeOutward(postcode);
    }


    /**
     * Calling of this REST endpoint will result in invocation of 'callFindTopXPercentOfMostExpensiveProperties()'
     * method to find the list of properties that falls under the given top percentage. For example if '0.10'
     * is passed in as input parameter then top 10% of the properties will be fed back to the calling system.
     *
     * REST address : "/top/{percent}"
     *
     * @param percent String
     * @return List of Property(s)
     */
    @GetMapping(value = "/top/{percent}")
    public List<Property> callFindTopXPercentOfMostExpensiveProperties(@PathVariable String percent) {

        return propertiesAnalyserService.findTopXPercentOfMostExpensiveProperties(Double.valueOf(percent));
    }


    /**
     * Calling on this REST endpoint will result in invocation of 'calculateAveragePriceDifferenceOfTwoPropertyTypes()'
     * method to calculate average price difference of the two given property types.
     *
     * REST address : "/average-difference/first-property/{firstPropertyType}/second-property/{secondPropertyType}"
     *
     * @param firstPropertyType String
     * @param secondPropertyType String
     * @return value in double
     */
    @GetMapping(value = "/average-difference/first-property/{firstPropertyType}/second-property/{secondPropertyType}")
    public double callCalculateAveragePriceDifferenceOfTwoPropertyTypes(
            @PathVariable String firstPropertyType,
            @PathVariable String secondPropertyType) {

        return propertiesAnalyserService.calculateAveragePriceDifferenceOfTwoPropertyTypes(
                firstPropertyType,
                secondPropertyType);
    }

}
