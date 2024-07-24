import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:iceflutter/dto/Place.dart';
import 'package:iceflutter/dto/myLocation.dart';
import 'package:http/http.dart' as http;

class ChatBot2Page extends StatefulWidget {
  const ChatBot2Page({super.key});

  @override
  State<ChatBot2Page> createState() => _ChatBot2PageState();
}

class _ChatBot2PageState extends State<ChatBot2Page> {
  MyLocation myLocation = MyLocation(latitude: 35.855332, longitude: 128.495965); //임의로 설정
  List<Place> places = []; //반환 받은 카페/식당의 정보 저장을 위한 리스트

  @override
  void initState() {
    super.initState();
    fetchNearbyPlaces();
  }

  Future<void> fetchNearbyPlaces() async { //1000미터 이내 식당/카페 이름과 주소 반환하는 API 연동
    final url = Uri.parse(
        'http://192.168.0.40:8080/nearby-places?latitude=${myLocation.latitude}&longitude=${myLocation.longitude}');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      List<dynamic> jsonResponse = json.decode(utf8.decode(response.bodyBytes));
      setState(() {
        places = jsonResponse.map((place) => Place.fromJson(place)).take(3).toList();
      });
    } else {
      throw Exception('데이터를 가져오는 과정에서 오류 발생');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("식당/카페 추천"),
      ),
      body: Center(
        child: places.isEmpty
            ? const CircularProgressIndicator()
            : ListView.builder(
                itemCount: places.length,
                itemBuilder: (context, index) {
                  final place = places[index];
                  return ListTile( //테스트를 위해 ui로 노출
                    title: Text(place.placeName),
                    subtitle: Text('${place.roadAddressName}\n${place.addressName}'),
                  );
                },
              ),
      ),
    );
  }
}