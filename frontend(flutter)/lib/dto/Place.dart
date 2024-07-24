class Place {
  final String placeName; //식당/카페 이름
  final String roadAddressName; // 도로 주소
  final String addressName; // 주소

  Place({required this.placeName, required this.roadAddressName, required this.addressName});

  factory Place.fromJson(Map<String, dynamic> json) {
    return Place(
      placeName: json['place_name'],
      roadAddressName: json['road_address_name'],
      addressName: json['address_name'],
    );
  }
}