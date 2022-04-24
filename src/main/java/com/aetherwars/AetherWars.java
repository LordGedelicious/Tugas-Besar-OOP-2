package com.aetherwars;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.aetherwars.model.TypeChar;
import com.aetherwars.model.Character;
import com.aetherwars.util.CSVReader;

public class AetherWars extends Application {
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";

  public void loadCards() throws IOException, URISyntaxException {
    File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
    characterReader.setSkipHeader(true);
    List<String[]> characterRows = characterReader.read();
    for (String[] row : characterRows) {
      // Character c = new Character(row[1], row[3], TypeChar.valueOf(row[2]));
      // System.out.println(c);
    }
  }

  @Override
  public void start(Stage stage) {
    Text title = new Text("Loading...");
    title.setX(550);
    title.setY(600);

    Text maxHp1 = new Text("/80");
    maxHp1.setX(100);
    maxHp1.setY(50);

    Text nowHp1 = new Text("80");
    nowHp1.setX(85);
    nowHp1.setY(50);

    Text maxHp2 = new Text("/80");
    maxHp2.setX(1180);
    maxHp2.setY(50);

    Text nowHp2 = new Text("80");
    nowHp2.setX(1165);
    nowHp2.setY(50);

    Text draw = new Text("DRAW");
    draw.setX(250);
    draw.setY(300);

    Text plan = new Text("PLAN");
    plan.setX(500);
    plan.setY(300);

    Text attack = new Text("ATTACK");
    attack.setX(750);
    attack.setY(300);

    Text end = new Text("END");
    end.setX(1000);
    end.setY(300);

    Group root = new Group();
    root.getChildren().add(title);
    root.getChildren().add(maxHp1);
    root.getChildren().add(maxHp2);
    root.getChildren().add(nowHp1);
    root.getChildren().add(nowHp2);
    root.getChildren().add(draw);
    root.getChildren().add(plan);
    root.getChildren().add(attack);
    root.getChildren().add(end);

    Scene scene = new Scene(root, 1280, 640);

    stage.setTitle("Minecraft: Aether Wars - Monangisbeneran");
    stage.setScene(scene);
    stage.show();

    try {
      this.loadCards();
      title.setText("Minecraft: Aether Wars!");
    } catch (Exception e) {
      title.setText("Failed to load cards: " + e);
    }


  }

  public static void main(String[] args) {
    launch();
  }
}
