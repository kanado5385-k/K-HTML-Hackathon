import 'package:flutter/material.dart';
import 'package:iceflutter/chat_bot2_page.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'ice',
      home: ChatBot2Page(),
      debugShowCheckedModeBanner: false,
      theme: ThemeData( //임의로 구현함
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
      ),
    );
}
}
