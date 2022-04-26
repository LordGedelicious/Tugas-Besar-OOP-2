package com.aetherwars;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

  private static Image image;

  private static ImageView img1A;
  private static ImageView img1B;
  private static ImageView img1C;
  private static ImageView img1D;
  private static ImageView img1E;

  private static ImageView img2A;
  private static ImageView img2B;
  private static ImageView img2C;
  private static ImageView img2D;
  private static ImageView img2E;

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
  public void start(Stage stage) throws FileNotFoundException {
    // Player untuk tes board 
    Player player1 = new Player("Steve", 40);
    Player player2 = new Player("Alex", 40);
    board = new Board(player1, player2);

    // Player setup
    Text turn_text = new Text("Turn");
    turn_text.setX(600);
    turn_text.setY(50);

    turn = new Text("1");
    turn.setX(640);
    turn.setY(50);

    Text maxHp1 = new Text("/80");
    maxHp1.setX(100);
    maxHp1.setY(50);

    Text nowHp1 = new Text("80");
    nowHp1.setX(85);
    nowHp1.setY(50);

    Text playerName1 = new Text(player1.getName());
    playerName1.setX(85);
    playerName1.setY(70);

    Text maxHp2 = new Text("/80");
    maxHp2.setX(1180);
    maxHp2.setY(50);

    Text nowHp2 = new Text("80");
    nowHp2.setX(1165);
    nowHp2.setY(50);

    Text playerName2 = new Text(player2.getName());
    playerName2.setX(1165);
    playerName2.setY(70);

    // Card image setup
    this.setImage(1, "card/image/character/Creeper.png");
    img1A.setX(225);
    img1A.setY(100);
    img1A.setFitWidth(50);
    img1A.setFitHeight(100);

    this.setImage(2, "card/image/character/Creeper.png");
    img1B.setX(350);
    img1B.setY(100);
    img1B.setFitWidth(50);
    img1B.setFitHeight(100);

    this.setImage(3, "card/image/character/Creeper.png");
    img1C.setX(225);
    img1C.setY(225);
    img1C.setFitWidth(50);
    img1C.setFitHeight(100);

    this.setImage(4, "card/image/character/Creeper.png");
    img1D.setX(350);
    img1D.setY(225);
    img1D.setFitWidth(50);
    img1D.setFitHeight(100);

    this.setImage(5, "card/image/character/Creeper.png");
    img1E.setX(475);
    img1E.setY(162.5);
    img1E.setFitWidth(50);
    img1E.setFitHeight(100);

    this.setImage(6, "card/image/character/Creeper.png");
    img2A.setX(1005);
    img2A.setY(100);
    img2A.setFitWidth(50);
    img2A.setFitHeight(100);

    this.setImage(7, "card/image/character/Creeper.png");
    img2B.setX(880);
    img2B.setY(100);
    img2B.setFitWidth(50);
    img2B.setFitHeight(100);

    this.setImage(8, "card/image/character/Creeper.png");
    img2C.setX(1005);
    img2C.setY(225);
    img2C.setFitWidth(50);
    img2C.setFitHeight(100);

    this.setImage(9, "card/image/character/Creeper.png");
    img2D.setX(880);
    img2D.setY(225);
    img2D.setFitWidth(50);
    img2D.setFitHeight(100);

    this.setImage(10, "card/image/character/Creeper.png");
    img2E.setX(755);
    img2E.setY(162.5);
    img2E.setFitWidth(50);
    img2E.setFitHeight(100);

    // Phase setup
    draw = new Text("DRAW");
    draw.setX(150);
    draw.setY(400);
    draw.setFill(Color.ORANGE);

    plan = new Text("PLAN");
    plan.setX(400);
    plan.setY(400);

    attack = new Text("ATTACK");
    attack.setX(650);
    attack.setY(400);

    end = new Text("END");
    end.setX(900);
    end.setY(400);

    Button next = new Button(">>");
    next.setLayoutX(1100);
    next.setLayoutY(375);
    next.setOnAction(this);

    Group root = new Group();
    root.getChildren().add(turn_text);
    root.getChildren().add(turn);
    root.getChildren().add(maxHp1);
    root.getChildren().add(maxHp2);
    root.getChildren().add(nowHp1);
    root.getChildren().add(nowHp2);
    root.getChildren().add(playerName1);
    root.getChildren().add(playerName2);

    root.getChildren().add(img1A);
    root.getChildren().add(img1B);
    root.getChildren().add(img1C);
    root.getChildren().add(img1D);
    root.getChildren().add(img1E);
    root.getChildren().add(img2A);
    root.getChildren().add(img2B);
    root.getChildren().add(img2C);
    root.getChildren().add(img2D);
    root.getChildren().add(img2E);

    root.getChildren().add(draw);
    root.getChildren().add(plan);
    root.getChildren().add(attack);
    root.getChildren().add(end);
    root.getChildren().add(next);

    Scene scene = new Scene(root, 1280, 700);

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

  public void setImage(int idx_card, String path) {
    image = new Image(getClass().getResourceAsStream(path));
    if (idx_card == 1) {
      img1A = new ImageView(image);
    } else if (idx_card == 2) {
      img1B = new ImageView(image);
    } else if (idx_card == 3) {
      img1C = new ImageView(image);
    } else if (idx_card == 4) {
      img1D = new ImageView(image);
    } else if (idx_card == 5) {
      img1E = new ImageView(image);
    } else if (idx_card == 6) {
      img2A = new ImageView(image);
    } else if (idx_card == 7) {
      img2B = new ImageView(image);
    } else if (idx_card == 8) {
      img2C = new ImageView(image);
    } else if (idx_card == 9) {
      img2D = new ImageView(image);
    } else if (idx_card == 10) {
      img2E = new ImageView(image);
    }
  }
}
