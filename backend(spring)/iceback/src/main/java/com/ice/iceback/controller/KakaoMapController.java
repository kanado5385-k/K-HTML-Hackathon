package com.ice.iceback.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ice.iceback.dto.response.Place;
import com.ice.iceback.service.KakaoMapService;

@RestController
public class KakaoMapController {
    private final KakaoMapService kakaoMapService;

    public KakaoMapController(KakaoMapService kakaoMapService) {
        this.kakaoMapService = kakaoMapService;
    }

    @GetMapping("/nearby-places")
    public ResponseEntity<List<Place>> getNearbyPlaces(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        List<Place> places = kakaoMapService.getNearbyPlaces(latitude, longitude);
        return ResponseEntity.ok(places);
    }
}