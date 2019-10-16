package uk.co.rightmove.propertyanalyser.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.co.rightmove.propertyanalyser.model.Property;
import uk.co.rightmove.propertyanalyser.service.PropertiesAnalyserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PropertiesAnalyserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertiesAnalyserService mockPropertiesAnalyserService;

    @Test
    public void testCallCalculateMeanPriceBasedOnPostcodeOutward() throws Exception {

        String postcodeOutward = "W1F";
        String resultInString = "1158750.0";

        when(mockPropertiesAnalyserService.calculateMeanPriceBasedOnPostcodeOutward(postcodeOutward))
                .thenReturn(1158750.0);

        MvcResult result = mockMvc.perform(get("/mean/{postcode}/outward", postcodeOutward))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();

        verify(mockPropertiesAnalyserService).calculateMeanPriceBasedOnPostcodeOutward(any(String.class));

        assertEquals(String.format("Expected result is %s", resultInString), resultInString,
                result.getResponse().getContentAsString());
    }


    @Test
    public void testCallFindTopXPercentOfMostExpensiveProperties() throws Exception {

        String percent = "0.10";
        List<Property> resultProperties = new ArrayList<>();

        resultProperties.add(Property.builder()
                .propertyReference(12)
                .price(7500000)
                .bedrooms(11)
                .bathrooms(4)
                .houseNumber("")
                .address("Brighton Road ")
                .region("Surrey")
                .postcode("GU13 4DD")
                .propertyType("Mansion")
                .build());

        String expectedResultInJson = new Gson().toJson(resultProperties);

        when(mockPropertiesAnalyserService.findTopXPercentOfMostExpensiveProperties(percent))
                .thenReturn(resultProperties);

        MvcResult result = mockMvc.perform(get("/top/{percent}", percent))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();

        verify(mockPropertiesAnalyserService).findTopXPercentOfMostExpensiveProperties(any(String.class));

        assertEquals(String.format("Expected result is %s", expectedResultInJson), expectedResultInJson,
                result.getResponse().getContentAsString());
    }


    @Test
    public void testCallCalculateAveragePriceDifferenceOfTwoPropertyTypes() throws Exception {

        String firstType = "Detached";
        String secondType = "Flat";
        String resultInString = "92386.0";

        when(mockPropertiesAnalyserService
                .calculateAveragePriceDifferenceOfTwoPropertyTypes(any(String.class),
                        any(String.class))).thenReturn(92386.0);

        MvcResult result = mockMvc
                .perform(get("/average-difference/property/{firstType}/property/{secondType}",
                        firstType,
                        secondType))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();

        verify(mockPropertiesAnalyserService)
                .calculateAveragePriceDifferenceOfTwoPropertyTypes(any(String.class),
                        any(String.class));

        assertEquals(String.format("Expected result is %s", resultInString), resultInString,
                result.getResponse().getContentAsString());
    }
}