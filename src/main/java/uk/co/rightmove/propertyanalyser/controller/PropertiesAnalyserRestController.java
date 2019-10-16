package uk.co.rightmove.propertyanalyser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.co.rightmove.propertyanalyser.model.Property;
import uk.co.rightmove.propertyanalyser.service.PropertiesAnalyserService;

import java.util.List;

@RestController
public class PropertiesAnalyserRestController {

    @Autowired
    private PropertiesAnalyserService propertiesAnalyserService;

    @GetMapping(value = "/mean/{postcode}")
    public double callCalculateMeanPriceOfPropertiesBasedOnPostcodeOutward(@PathVariable String postcode) {

        return propertiesAnalyserService.calculateMeanPriceOfPropertiesBasedOnPostcodeOutward(postcode);
    }


    @GetMapping(value = "/top/{percent}")
    public List<Property> callFindTopXPercentOfMostExpensiveProperties(@PathVariable String percent) {

        return propertiesAnalyserService.findTopXPercentOfMostExpensiveProperties(Double.valueOf(percent));
    }


    @GetMapping(value = "/average-difference/{firstPropertyType}/{secondPropertyType}")
    public double callCalculateDifferenceBetweenAveragePricesOfTwoPropertyTypes(
            @PathVariable String firstPropertyType,
            @PathVariable String secondPropertyType) {

        return propertiesAnalyserService.calculateDifferenceBetweenAveragePricesOfTwoPropertyTypes(
                firstPropertyType,
                secondPropertyType);
    }

}
