package com.aetherwars;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.event.*;

import com.aetherwars.model.*;
import com.aetherwars.util.CSVReader;

public class AetherWars extends Application implements EventHandler<ActionEvent> {
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";

  private static CardLibrary cLib;

  private static Text turn;
  private static Text draw;
  private static Text plan;
  private static Text attack;
  private static Text end;

  private static Board board;

  //public void loadCards() throws IOException, URISyntaxException {
  public void loadCards(){
    cLib = new CardLibrary();
    cLib.fillLibrary();
    // File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    // CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
    // characterReader.setSkipHeader(true);
    // List<String[]> characterRows = characterReader.read();
    // for (String[] row : characterRows) {
    //   // Character c = new Character(row[1], row[3], TypeChar.valueOf(row[2]));
    //   // System.out.println(c);
    // }
  }

  @Override
  public void start(Stage stage) {
    // Player untuk tes board 
    Player player1 = new Player("Steve", 40);
    Player player2 = new Player("Alex", 40);
    board = new Board(player1, player2);

    Text turn_text = new Text("Turn");
    turn_text.setX(600);
    turn_text.setY(50);

    turn = new Text("");
    turn.setX(640);
    turn.setY(50);

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

    draw = new Text("DRAW");
    draw.setX(150);
    draw.setY(350);
    draw.setFill(Color.ORANGE);

    plan = new Text("PLAN");
    plan.setX(400);
    plan.setY(350);

    attack = new Text("ATTACK");
    attack.setX(650);
    attack.setY(350);

    end = new Text("END");
    end.setX(900);
    end.setY(350);

    Button next = new Button(">>");
    next.setLayoutX(1100);
    next.setLayoutY(325);
    next.setOnAction(this);

    Group root = new Group();
    root.getChildren().add(turn_text);
    root.getChildren().add(turn);
    root.getChildren().add(maxHp1);
    root.getChildren().add(maxHp2);
    root.getChildren().add(nowHp1);
    root.getChildren().add(nowHp2);
    root.getChildren().add(draw);
    root.getChildren().add(plan);
    root.getChildren().add(attack);
    root.getChildren().add(end);
    root.getChildren().add(next);

    Scene scene = new Scene(root, 1280, 640);

    stage.setTitle("Minecraft: Aether Wars - Monangisbeneran");
    stage.setScene(scene);
    stage.show();

    // try {
    //   this.loadCards();
    // } catch (Exception e) {
    //   turn.setText("Failed to load cards: " + e);
    // }

    this.loadCards();
  }

  public static void main(String[] args) {
    launch();
  }

  public void handle(ActionEvent event) {
    board.nextPhase();
    if (board.getPhase() == TypePhase.DRAW) {
      draw.setFill(Color.ORANGE);
      end.setFill(Color.BLACK);
    } else if (board.getPhase() == TypePhase.PLANNING) {
      plan.setFill(Color.ORANGE);
      draw.setFill(Color.BLACK);
    } else if (board.getPhase() == TypePhase.ATTACK) {
      attack.setFill(Color.ORANGE);
      plan.setFill(Color.BLACK);
    } else {
      end.setFill(Color.ORANGE);
      attack.setFill(Color.BLACK);
    }
    turn.setText(Integer.toString(board.getRound()));
  }
}
