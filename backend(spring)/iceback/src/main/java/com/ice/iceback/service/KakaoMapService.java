package com.ice.iceback.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ice.iceback.dto.response.Place;

@Service
public class KakaoMapService {
    private final RestTemplate restTemplate;
    private final String KAKAO_API_KEY = "06e06cd4e76e9364d3d362a225c29093";

    public KakaoMapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Place> getNearbyPlaces(double latitude, double longitude) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=식당&카페&x={longitude}&y={latitude}&radius=1000";

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {},
                longitude,
                latitude
        );

        List<Map<String, Object>> documents = (List<Map<String, Object>>) response.getBody().get("documents");

        List<Place> places = documents.stream()
                .map(doc -> new Place((String) doc.get("place_name"), (String) doc.get("road_address_name"), (String) doc.get("address_name")))
                .collect(Collectors.toList());

        return places;
    }
}