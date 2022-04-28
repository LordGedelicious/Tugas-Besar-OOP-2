package com.aetherwars;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import com.aetherwars.model.*;
import com.aetherwars.model.Character;
import com.aetherwars.util.CSVReader;

public class AetherWars extends Application implements EventHandler<ActionEvent> {
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
  private static final String PTN_CSV_FILE_PATH = "card/data/spell_ptn.csv";
  private static final String MORPH_CSV_FILE_PATH = "card/data/spell_morph.csv";
  private static final String SWAP_CSV_FILE_PATH = "card/data/spell_swap.csv";
  private static final String LVL_CSV_FILE_PATH = "card/data/spell_lvl.csv";
  private static final String DEFAULT_IMG_PATH = "card/image/Default.png";

  private static CardLibrary cLib;

  private static Text turn;
  private static Text currentPlayer;
  private static Text nowHp1;
  private static Text nowHp2;
  
  private static Image image;

  private static ImageView img1A = new ImageView();
  private static ImageView img1B = new ImageView();
  private static ImageView img1C = new ImageView();
  private static ImageView img1D = new ImageView();
  private static ImageView img1E = new ImageView();

  private static ImageView img2A = new ImageView();
  private static ImageView img2B = new ImageView();
  private static ImageView img2C = new ImageView();
  private static ImageView img2D = new ImageView();
  private static ImageView img2E = new ImageView();

  private static Text draw;
  private static Text plan;
  private static Text attack;
  private static Text end;

  private static ImageView hand1 = new ImageView();
  private static ImageView hand2 = new ImageView();
  private static ImageView hand3 = new ImageView();
  private static ImageView hand4 = new ImageView();
  private static ImageView hand5 = new ImageView();
  private static Text handMana1;
  private static Text handMana2;
  private static Text handMana3;
  private static Text handMana4;
  private static Text handMana5;
  private static ImageView handHover = new ImageView();
  private static Text handDetail;
  private static Text handDesc;
  private static Card card;
  private static SummonedChar summon;
  private static PTN ptn;
  private static MORPH morph;
  private static LVL lvl;
  private static SWAP swap;

  private static Text mana;
  private static Text curMana;
  private static Text maxMana;
  private static Text deck;
  private static Text curDeck;
  private static Text maxDeck;

  private static List<Card> drawCard;
  private static Text drawText;
  private static ImageView draw1 = new ImageView();
  private static ImageView draw2 = new ImageView();
  private static ImageView draw3 = new ImageView();
  private static Text drawMana1;
  private static Text drawMana2;
  private static Text drawMana3;

  private static Pane tangan1 = new Pane();
  private static Pane tangan2 = new Pane();
  private static Pane tangan3 = new Pane();
  private static Pane tangan4 = new Pane();
  private static Pane tangan5 = new Pane();

  private static Board board;

  private static Player player1;
  private static Player player2;

  private static Integer ronde = 0;
  private static boolean isDrawed = false;

  public void loadCards() throws IOException, URISyntaxException {
    cLib = new CardLibrary();
    File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    File lvlCSVFile = new File(getClass().getResource(LVL_CSV_FILE_PATH).toURI());
    File morphCSVFile = new File(getClass().getResource(MORPH_CSV_FILE_PATH).toURI());
    File ptnCSVFile = new File(getClass().getResource(PTN_CSV_FILE_PATH).toURI());
    File swapCSVFile = new File(getClass().getResource(SWAP_CSV_FILE_PATH).toURI());
    cLib.fillLibrary(characterCSVFile, lvlCSVFile, morphCSVFile, ptnCSVFile, swapCSVFile);
  }

  @Override
  public void start(Stage stage) throws FileNotFoundException {

    try {
      this.loadCards();
    } catch (Exception e) {}

    // Player untuk tes board 
    player1 = new Player("Steve", 60);
    player2 = new Player("Alex", 40);
    player1.randomDeck(cLib);
    player2.randomDeck(cLib);
    board = new Board(player1, player2);

    // Player setup
    Text turn_text = new Text("Turn");
    turn_text.setX(600);
    turn_text.setY(50);

    currentPlayer = new Text(player1.getName());
    currentPlayer.setX(610);
    currentPlayer.setY(100);

    turn = new Text(String.valueOf(ronde));
    turn.setX(640);
    turn.setY(50);

    //Player 1
    Text maxHp1 = new Text("/80");
    maxHp1.setX(100);
    maxHp1.setY(50);

    nowHp1 = new Text(String.valueOf(player1.getHp()));
    nowHp1.setX(85);
    nowHp1.setY(50);

    player1.increaseMana();
    player1.resetMana();

    Text playerMana1 = new Text("Mana:");
    playerMana1.setX(50);
    playerMana1.setY(200);

    Text playerName1 = new Text(player1.getName());
    playerName1.setX(85);
    playerName1.setY(70);

    image = new Image(getClass().getResourceAsStream("card/image/Steve.png"));
    ImageView playerImage1 = new ImageView(image);
    playerImage1.setX(50);
    playerImage1.setY(162.5);
    playerImage1.setFitWidth(100);
    playerImage1.setFitHeight(100);

    //player 2
    Text maxHp2 = new Text("/80");
    maxHp2.setX(1180);
    maxHp2.setY(50);

    nowHp2 = new Text(String.valueOf(player2.getHp()));
    nowHp2.setX(1165);
    nowHp2.setY(50);

    Text playerName2 = new Text(player2.getName());
    playerName2.setX(1165);
    playerName2.setY(70);

    image = new Image(getClass().getResourceAsStream("card/image/Alex.png"));
    ImageView playerImage2 = new ImageView(image);
    playerImage2.setX(1140);
    playerImage2.setY(162.5);
    playerImage2.setFitWidth(100);
    playerImage2.setFitHeight(100);

    // Card image setup
    image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));

    setImageBattleground(1);
    img1A.setX(225);
    img1A.setY(100);
    img1A.setFitWidth(50);
    img1A.setFitHeight(100);

    setImageBattleground(2);
    img1B.setX(350);
    img1B.setY(100);
    img1B.setFitWidth(50);
    img1B.setFitHeight(100);

    setImageBattleground(3);
    img1C.setX(225);
    img1C.setY(225);
    img1C.setFitWidth(50);
    img1C.setFitHeight(100);

    setImageBattleground(4);
    img1D.setX(350);
    img1D.setY(225);
    img1D.setFitWidth(50);
    img1D.setFitHeight(100);

    setImageBattleground(5);
    img1E.setX(475);
    img1E.setY(162.5);
    img1E.setFitWidth(50);
    img1E.setFitHeight(100);

    setImageBattleground(6);
    img2A.setX(1005);
    img2A.setY(100);
    img2A.setFitWidth(50);
    img2A.setFitHeight(100);

    setImageBattleground(7);
    img2B.setX(880);
    img2B.setY(100);
    img2B.setFitWidth(50);
    img2B.setFitHeight(100);

    setImageBattleground(8);
    img2C.setX(1005);
    img2C.setY(225);
    img2C.setFitWidth(50);
    img2C.setFitHeight(100);

    setImageBattleground(9);
    img2D.setX(880);
    img2D.setY(225);
    img2D.setFitWidth(50);
    img2D.setFitHeight(100);

    setImageBattleground(10);
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
    next.setLayoutY(380);
    next.setOnAction(this);

    // Hand setup
    image = new Image(getClass().getResourceAsStream(player1.hand.getCard(0).ImagePath));
    setImageHand(1);
    // hand1.setX(100);
    // hand1.setY(500);
    hand1.setX(15);
    hand1.setY(10);
    hand1.setFitWidth(50);
    hand1.setFitHeight(100);
    hand1.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Turn1 && player1.hand.getSize() > 0) {
          card = player1.hand.getCard(0);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });
    handMana1 = new Text("Mana " + Integer.toString(player1.hand.getCard(0).Mana));
    handMana1.setX(100);
    handMana1.setY(625);

    image = new Image(getClass().getResourceAsStream(player1.hand.getCard(1).ImagePath));
    setImageHand(2);
    // hand2.setX(200);
    // hand2.setY(500);
    hand2.setX(15);
    hand2.setY(10);
    hand2.setFitWidth(50);
    hand2.setFitHeight(100);
    hand2.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Turn1 && player1.hand.getSize() > 1) {
          card = player1.hand.getCard(1);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });
    handMana2 = new Text("Mana " + Integer.toString(player1.hand.getCard(1).Mana));
    handMana2.setX(200);
    handMana2.setY(625);

    image = new Image(getClass().getResourceAsStream(player1.hand.getCard(2).ImagePath));
    setImageHand(3);
    // hand3.setX(300);
    // hand3.setY(500);
    hand3.setX(15);
    hand3.setY(10);
    hand3.setFitWidth(50);
    hand3.setFitHeight(100);
    hand3.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Turn1 && player1.hand.getSize() > 2) {
          card = player1.hand.getCard(2);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });
    handMana3 = new Text("Mana " + Integer.toString(player1.hand.getCard(2).Mana));
    handMana3.setX(300);
    handMana3.setY(625);

    image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
    setImageHand(4);
    // hand4.setX(400);
    // hand4.setY(500);
    hand4.setX(15);
    hand4.setY(10);
    hand4.setFitWidth(50);
    hand4.setFitHeight(100);
    hand4.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Turn1 && player1.hand.getSize() > 3) {
          card = player1.hand.getCard(3);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });
    handMana4 = new Text();
    handMana4.setX(400);
    handMana4.setY(625);

    setImageHand(5);
    // hand5.setX(500);
    // hand5.setY(500);
    hand5.setX(15);
    hand5.setY(10);
    hand5.setFitWidth(50);
    hand5.setFitHeight(100);
    hand5.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Turn1 && player1.hand.getSize() > 4) {
          card = player1.hand.getCard(4);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });
    handMana5 = new Text();
    handMana5.setX(500);
    handMana5.setY(625);

    setImageHand(0);
    handHover.setX(650);
    handHover.setY(475);
    handHover.setFitWidth(75);
    handHover.setFitHeight(150);

    handDetail = new Text();
    handDetail.setX(800);
    handDetail.setY(500);
    handDetail.setWrappingWidth(100);

    handDesc = new Text();
    handDesc.setX(925);
    handDesc.setY(500);
    handDesc.setWrappingWidth(150);

    // Mana & Deck
    mana = new Text("Mana");
    mana.setX(1150);
    mana.setY(510);

    curMana = new Text(String.valueOf(player1.getCurMana()));
    curMana.setX(1150);
    curMana.setY(530);

    maxMana = new Text("/" + String.valueOf(player1.getMaxMana()));
    maxMana.setX(1165);
    maxMana.setY(530);

    deck = new Text("Deck");
    deck.setX(1150);
    deck.setY(560);

    curDeck = new Text(String.valueOf(player1.deckSize - player1.hand.getSize()));
    curDeck.setX(1150);
    curDeck.setY(580);

    maxDeck = new Text("/" + String.valueOf(player1.deckSize));
    maxDeck.setX(1165);
    maxDeck.setY(580);

    // Draw setup
    drawText = new Text("PICK A CARD");
    drawText.setX(200);
    drawText.setY(800);

    drawCard = player1.deck.showTopThreeCards();
    image = new Image(getClass().getResourceAsStream(drawCard.get(0).ImagePath));
    setImageDraw(1);
    draw1.setX(400);
    draw1.setY(700);
    draw1.setFitWidth(80);
    draw1.setFitHeight(160);
    draw1.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (drawCard.size() > 0) {
          card = drawCard.get(0);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });
    draw1.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        clickOnDraw(0);
        if (board.Turn1) {
          for (int i = 0; i < player1.hand.getSize(); i++) {
            image = new Image(getClass().getResourceAsStream(player1.hand.getCard(i).ImagePath));
            setImageHand(i+1);
            setManaHand(i+1, "Mana " + Integer.toString(player1.hand.getCard(i).Mana));
          }
          player1.deck.returnCard(0);
          curDeck.setText(String.valueOf(player1.deckSize - player1.hand.getSize()));
        } else {
          for (int i = 0; i < player2.hand.getSize(); i++) {
            image = new Image(getClass().getResourceAsStream(player2.hand.getCard(i).ImagePath));
            setImageHand(i+1);
            setManaHand(i+1, "Mana " + Integer.toString(player2.hand.getCard(i).Mana));
          }
          player2.deck.returnCard(0);
          curDeck.setText(String.valueOf(player2.deckSize - player1.hand.getSize()));
        }
        image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
        setDefaultDraw();
        isDrawed = true;
      }
    });
    drawMana1 = new Text("Mana " + Integer.toString(drawCard.get(0).Mana));
    drawMana1.setX(415);
    drawMana1.setY(885);

    image = new Image(getClass().getResourceAsStream(drawCard.get(1).ImagePath));
    setImageDraw(2);
    draw2.setX(600);
    draw2.setY(700);
    draw2.setFitWidth(80);
    draw2.setFitHeight(160);
    draw2.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (drawCard.size() > 1) {
          card = drawCard.get(1);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });
    draw2.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        clickOnDraw(1);
        if (board.Turn1) {
          for (int i = 0; i < player1.hand.getSize(); i++) {
            image = new Image(getClass().getResourceAsStream(player1.hand.getCard(i).ImagePath));
            setImageHand(i+1);
            setManaHand(i+1, "Mana " + Integer.toString(player1.hand.getCard(i).Mana));
          }
          player1.deck.returnCard(1);
          curDeck.setText(String.valueOf(player1.deckSize - player1.hand.getSize()));
        } else {
          for (int i = 0; i < player2.hand.getSize(); i++) {
            image = new Image(getClass().getResourceAsStream(player2.hand.getCard(i).ImagePath));
            setImageHand(i+1);
            setManaHand(i+1, "Mana " + Integer.toString(player2.hand.getCard(i).Mana));
          }
          player2.deck.returnCard(1);
          curDeck.setText(String.valueOf(player2.deckSize - player1.hand.getSize()));
        }
        image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
        setDefaultDraw();
        isDrawed = true;
      }
    });
    drawMana2 = new Text("Mana " + Integer.toString(drawCard.get(1).Mana));
    drawMana2.setX(615);
    drawMana2.setY(885);

    image = new Image(getClass().getResourceAsStream(drawCard.get(2).ImagePath));
    setImageDraw(3);
    draw3.setX(800);
    draw3.setY(700);
    draw3.setFitWidth(80);
    draw3.setFitHeight(160);
    draw3.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (drawCard.size() > 2) {
          card = drawCard.get(2);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });

    draw3.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        clickOnDraw(2);
        if (board.Turn1) {
          for (int i = 0; i < player1.hand.getSize(); i++) {
            image = new Image(getClass().getResourceAsStream(player1.hand.getCard(i).ImagePath));
            setImageHand(i+1);
            setManaHand(i+1, "Mana " + Integer.toString(player1.hand.getCard(i).Mana));
          }
          player1.deck.returnCard(2);
          curDeck.setText(String.valueOf(player1.deckSize - player1.hand.getSize()));
        } else {
          for (int i = 0; i < player2.hand.getSize(); i++) {
            image = new Image(getClass().getResourceAsStream(player2.hand.getCard(i).ImagePath));
            setImageHand(i+1);
            setManaHand(i+1, "Mana " + Integer.toString(player2.hand.getCard(i).Mana));
          }
          player2.deck.returnCard(2);
          curDeck.setText(String.valueOf(player2.deckSize - player1.hand.getSize()));
        }
        image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
        setDefaultDraw();
        isDrawed = true;
      }
    });
    drawMana3 = new Text("Mana " + Integer.toString(drawCard.get(2).Mana));
    drawMana3.setX(815);
    drawMana3.setY(885);

    // Group setup
    //Group root = new Group();
    Pane root = new Pane();
    root.getChildren().add(turn_text);
    root.getChildren().add(turn);
    root.getChildren().add(currentPlayer);
    root.getChildren().add(maxHp1);
    root.getChildren().add(maxHp2);
    root.getChildren().add(nowHp1);
    root.getChildren().add(nowHp2);
    root.getChildren().add(playerName1);
    root.getChildren().add(playerName2);
    root.getChildren().add(playerImage1);
    root.getChildren().add(playerImage2);

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

    root.getChildren().add(hand1);
    root.getChildren().add(hand2);
    root.getChildren().add(hand3);
    root.getChildren().add(hand4);
    root.getChildren().add(hand5);
    root.getChildren().add(handMana1);
    root.getChildren().add(handMana2);
    root.getChildren().add(handMana3);
    root.getChildren().add(handMana4);
    root.getChildren().add(handMana5);
    root.getChildren().add(handHover);
    root.getChildren().add(handDetail);
    root.getChildren().add(handDesc);

    root.getChildren().add(mana);
    root.getChildren().add(curMana);
    root.getChildren().add(maxMana);
    root.getChildren().add(deck);
    root.getChildren().add(curDeck);
    root.getChildren().add(maxDeck);

    root.getChildren().add(drawText);
    root.getChildren().add(draw1);
    root.getChildren().add(draw2);
    root.getChildren().add(draw3);
    root.getChildren().add(drawMana1);
    root.getChildren().add(drawMana2);
    root.getChildren().add(drawMana3);

    tangan1.setStyle("-fx-background-color: gray;");
    tangan1.setPrefHeight(150.0);
    tangan1.setPrefWidth(90.0);
    tangan1.setLayoutX(90);
    tangan1.setLayoutY(500);
    tangan1.getChildren().add(hand1);

    tangan2.setStyle("-fx-background-color: gray;");
    tangan2.setPrefHeight(150.0);
    tangan2.setPrefWidth(90.0);
    tangan2.setLayoutX(190);
    tangan2.setLayoutY(500);
    tangan2.getChildren().add(hand2);

    tangan3.setStyle("-fx-background-color: gray;");
    tangan3.setPrefHeight(150.0);
    tangan3.setPrefWidth(90.0);
    tangan3.setLayoutX(290);
    tangan3.setLayoutY(500);
    tangan3.getChildren().add(hand3);

    tangan4.setStyle("-fx-background-color: gray;");
    tangan4.setPrefHeight(150.0);
    tangan4.setPrefWidth(90.0);
    tangan4.setLayoutX(390);
    tangan4.setLayoutY(500);
    tangan4.getChildren().add(hand4);

    tangan5.setStyle("-fx-background-color: gray;");
    tangan5.setPrefHeight(150.0);
    tangan5.setPrefWidth(90.0);
    tangan5.setLayoutX(490);
    tangan5.setLayoutY(500);
    tangan5.getChildren().add(hand5);

    hand1.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        resetSelect();
        tangan1.setStyle("-fx-background-color: yellow;");
      }
    });

    hand2.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        resetSelect();
        tangan2.setStyle("-fx-background-color: yellow;");
      }
    });

    hand3.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        resetSelect();
        tangan3.setStyle("-fx-background-color: yellow;");
      }
    });

    root.getChildren().add(tangan1);
    root.getChildren().add(tangan2);
    root.getChildren().add(tangan3);
    root.getChildren().add(tangan4);
    root.getChildren().add(tangan5);

    Scene scene = new Scene(root, 1280, 950);

    stage.setTitle("Minecraft: Aether Wars - Monangisbeneran");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

  public void handle(ActionEvent event) {
    if (isDrawed) {
      board.nextPhase();
      if (board.getPhase() == TypePhase.DRAW) {//DRAW
        draw.setFill(Color.ORANGE);
        if (board.Turn1){
          ronde++;
          turn.setText(String.valueOf(ronde));
          player1.increaseMana();
          player1.resetMana();
          curMana.setText(String.valueOf(player1.getCurMana()));
          maxMana.setText("/"+String.valueOf(player1.getMaxMana()));
          currentPlayer.setText(player1.getName());
  
          if (player1.deck.isEmpty()){
            //player pertama kalah
            System.out.println("Player 1 kalah");
          }
          else{
            drawCard = player1.deck.showTopThreeCards();
            //ntar pilih kartu
            //get card dari deck sesuai dengan pilihan player
            //kalo hand penuh yang paling kiri (index 0) buang
            Card kartu = player1.deck.getCard(0);
            player1.hand.addCard(kartu);
          }
        }
        else{
          player2.increaseMana();
          player2.resetMana();
          curMana.setText(String.valueOf(player2.getCurMana()));
          maxMana.setText("/"+String.valueOf(player2.getMaxMana()));
          currentPlayer.setText(player2.getName());
          if (player2.deck.isEmpty()){
            //player pertama kalah
            System.out.println("Player 2 kalah");
          }
          else{
            drawCard = player2.deck.showTopThreeCards();
            //ntar pilih kartu
            Card kartu = player2.deck.getCard(0);
            player2.hand.addCard(kartu);
          }
        }
        end.setFill(Color.BLACK);
      } else if (board.getPhase() == TypePhase.PLANNING) { //PLAN
        plan.setFill(Color.ORANGE);
        draw.setFill(Color.BLACK);
      } else if (board.getPhase() == TypePhase.ATTACK) { //ATTACK
        attack.setFill(Color.ORANGE);
        plan.setFill(Color.BLACK);
      } else { //END
        end.setFill(Color.ORANGE);
        attack.setFill(Color.BLACK);
      }
      turn.setText(Integer.toString(board.getRound()));      
    }
  }

  public static void setImageBattleground(int idx_card) {
    if (idx_card == 1) {
      img1A.setImage(image);
    } else if (idx_card == 2) {
      img1B.setImage(image);
    } else if (idx_card == 3) {
      img1C.setImage(image);
    } else if (idx_card == 4) {
      img1D.setImage(image);
    } else if (idx_card == 5) {
      img1E.setImage(image);
    } else if (idx_card == 6) {
      img2A.setImage(image);
    } else if (idx_card == 7) {
      img2B.setImage(image);
    } else if (idx_card == 8) {
      img2C.setImage(image);
    } else if (idx_card == 9) {
      img2D.setImage(image);
    } else if (idx_card == 10) {
      img2E.setImage(image);
    }
  }

  public static void setImageHand(int idx_card) {
    if (idx_card == 1) {
      hand1.setImage(image);
    } else if (idx_card == 2) {
      hand2.setImage(image);
    } else if (idx_card == 3) {
      hand3.setImage(image);
    } else if (idx_card == 4) {
      hand4.setImage(image);
    } else if (idx_card == 5) {
      hand5.setImage(image);
    } else if (idx_card == 0) {
      handHover.setImage(image);
    }
  }

  public static void setManaHand(int idx_card, String text) {
    if (idx_card == 1) {
      handMana1.setText(text);
    } else if (idx_card == 2) {
      handMana2.setText(text);
    } else if (idx_card == 3) {
      handMana3.setText(text);
    } else if (idx_card == 4) {
      handMana4.setText(text);
    } else if (idx_card == 5) {
      handMana5.setText(text);
    }
  }

  public static void setImageDraw(int idx_draw) {    
    if (idx_draw == 1) {
      draw1.setImage(image);
    } else if (idx_draw == 2) {
      draw2.setImage(image);
    } else if (idx_draw == 3) {
      draw3.setImage(image);
    } 
  }

  public static void setDefaultDraw() {
    while (drawCard.size() != 0) {
      drawCard.remove(0);
    }
    draw1.setImage(image);
    draw2.setImage(image);
    draw3.setImage(image);
    drawText.setText("");
    drawMana1.setText("");
    drawMana2.setText("");
    drawMana3.setText("");
  }

  public static void updateDetailHand() {
    if (card.getClass().equals(Character.class)) {
      summon = new SummonedChar((Character) card);
      handDetail.setText(summon.c.Nama + "\nATK : " + summon.baseAtk + "\nHP : " + summon.baseHp + "\nLevel : " + summon.Level + "\nEXP : " + summon.Exp + "/" + summon.Exp_need + "\nType : " + summon.c.tipe.toString());
      handDesc.setText(summon.c.Deskripsi);
    } else if (card.getClass().equals(PTN.class)) {
      ptn = (PTN) card;
      handDetail.setText(ptn.Nama + "\nATK : " + Integer.toString(ptn.Attack) +  "\nHP : " + Integer.toString(ptn.HP) + "\nDuration : " + Integer.toString(((TempSpell) ptn).duration));
      handDesc.setText(ptn.Deskripsi);
    } else if (card.getClass().equals(MORPH.class)) {
      morph = (MORPH) card;
      handDetail.setText(morph.Nama + "\nTarget ID : " + Integer.toString(morph.id_swap_target));
      handDesc.setText(morph.Deskripsi);
    } else if (card.getClass().equals(LVL.class)) {
      lvl = (LVL) card;
      handDetail.setText(lvl.Nama + "\nLevel : " + lvl.direction);
      handDesc.setText(lvl.Deskripsi);
    } else if (card.getClass().equals(SWAP.class)) {
      swap = (SWAP) card;
      handDetail.setText(swap.Nama + "\nATK <-> HP");
      handDesc.setText(swap.Deskripsi);
    }
  }

  public static void clickOnDraw(int idx) {
    if (drawCard.size() > idx) {
      card = drawCard.get(idx);
      if (board.Turn1) {
        // if (player1.hand.isFull()) {
        //   player1.hand.removeCard(0);
        // }
        player1.hand.addCard(card);
      } else {
        // if (player2.hand.isFull()) {
        //   player2.hand.removeCard(0);
        // }
        player2.hand.addCard(card);
      }
    }
  }

  public void resetSelect(){
    tangan1.setStyle("-fx-background-color: gray;");
    tangan2.setStyle("-fx-background-color: gray;");
    tangan3.setStyle("-fx-background-color: gray;");
    tangan4.setStyle("-fx-background-color: gray;");
    tangan5.setStyle("-fx-background-color: gray;");
  }
}
