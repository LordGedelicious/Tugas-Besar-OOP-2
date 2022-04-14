package com.aetherwars.model;

interface ISummoned {
    // getter
  int getLevel();

  int getExp();


  // meningkatkan level karakter sebanyak 1
  // mereset xp ke 0
  // meningkatkan baseAtk dan baseHp sebanyak attackUp dan healthUp
  void levelUp();
}