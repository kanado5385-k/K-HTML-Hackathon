package com.ice.iceback.dto.response;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    private String place_name;
    private String road_address_name;
    private String address_name;
}