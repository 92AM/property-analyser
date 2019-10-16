package uk.co.rightmove.propertyanalyser.model;

import lombok.*;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Property {

    private int propertyReference;

    private int price;

    private int bedrooms;

    private int bathrooms;

    private String houseNumber;

    private String address;

    private String region;

    private String postcode;

    private String propertyType;

}
