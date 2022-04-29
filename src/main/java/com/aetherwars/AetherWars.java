package com.aetherwars;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Map;

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
  private static final Card DEFAULT_CARD = new Card(0, "-", "-", "-", 0);
  private static final Character DEFAULT_CHAR = new Character(0, "-", TypeChar.NEUTRAL, "-", "-", 0, 0, 0, 0, 0);
  private static final SummonedChar DEFAULT_SUM_CHAR = new SummonedChar(DEFAULT_CHAR);

  private static CardLibrary cLib;

  private static Alert alert;
  private static ButtonType buttontype;

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

  private static Button addExp;
  private static Button deleteCard;

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

  private static SummonedChar offense;
  private static SummonedChar defense;

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

  private static Pane summoned1A = new Pane();
  private static Pane summoned1B = new Pane();
  private static Pane summoned1C = new Pane();
  private static Pane summoned1D = new Pane();
  private static Pane summoned1E = new Pane();
  private static Pane summoned2A = new Pane();
  private static Pane summoned2B = new Pane();
  private static Pane summoned2C = new Pane();
  private static Pane summoned2D = new Pane();
  private static Pane summoned2E = new Pane();

  private static Board board;

  private static Player player1;
  private static Player player2;

  private static Integer ronde = 0;
  private static boolean isDrawed = false;
  private static String key;

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
    player1 = new Player("Steve", ThreadLocalRandom.current().nextInt(40, 60 + 1));
    // player1 = new Player("Steve", 7);
    player2 = new Player("Alex", ThreadLocalRandom.current().nextInt(40, 60 + 1));
    player1.randomDeck(cLib);
    player2.randomDeck(cLib);
    board = new Board(player1, player2);

    // Alert setup
    alert = new Alert(Alert.AlertType.NONE);
    alert.setTitle("Winner Confirmation");
    buttontype = new ButtonType("Congrats", ButtonData.OK_DONE);
    alert.getDialogPane().getButtonTypes().add(buttontype);

    // Player setup
    Text turn_text = new Text("Turn");
    turn_text.setX(600);
    turn_text.setY(50);

    currentPlayer = new Text(player1.getName());
    currentPlayer.setX(600);
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
    playerImage1.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.ATTACK && board.Turn2 && board.Battleground1.ActiveCard.isEmpty()){
          defense = DEFAULT_SUM_CHAR;
          if (offense != null){
            offense.attackPlayer(player1);
            nowHp1.setText(Integer.toString(player1.HP));
            if (player1.getHp() <= 0){
              alert.setContentText("Player 2 is winner");
              alert.showAndWait();
            }
            board.Battleground2.setAlreadyAttack(offense.defense, true);
            resetATKBGColor();
            resetOffenseDefense();
          }
        }
      }
    });

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
    playerImage2.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.ATTACK && board.Turn1 && board.Battleground2.ActiveCard.isEmpty()){
          defense = DEFAULT_SUM_CHAR;
          if (offense != null){
            offense.attackPlayer(player2);
            nowHp2.setText(Integer.toString(player2.HP));
            if (player2.getHp() <= 0){
              alert.setContentText("Player 1 is winner");
              alert.showAndWait();
            }
            board.Battleground1.setAlreadyAttack(offense.defense, true);
            resetATKBGColor();
            resetOffenseDefense();
          }
        }
      }
    });

    // Card image setup
    image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));

    setImageBattleground(1);
    img1A.setX(25);
    img1A.setY(25);
    img1A.setFitWidth(50);
    img1A.setFitHeight(50);
    img1A.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground1.ActiveCard.containsKey("A")) {
          summon = board.Battleground1.getChar("A");
          Character c = summon.c;
          image = new Image(getClass().getResourceAsStream(c.ImagePath));
          handHover.setImage(image);
          updateDetailBattleground();
        }
      }
    });
    img1A.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (card.getClass().equals(Character.class)) { 
            if (board.Turn1) {
              if (card.Mana <= player1.getCurMana()) {
                if (!board.Battleground1.ActiveCard.containsKey("A")) {
                  summon = new SummonedChar((Character) card);
                  try {
                    board.Battleground1.addCard(summon, "A");
                  } catch (Exception e) {
                    // pass
                  }
                  image = new Image(getClass().getResourceAsStream(card.ImagePath));
                  img1A.setImage(image);
                  player1.hand.inHand.remove(card);
                  resetGuiHand(player1);
                  curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                } 
                resetSelect();
                card = DEFAULT_CARD;
                key = "A";
              } else {
                if (board.Battleground1.ActiveCard.containsKey("A")) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "A";
                }
              }
            }
          } else {
            if (!card.getClass().equals(Card.class)) {
              if ((card.Mana <= player1.getCurMana() && board.Turn1) || (card.Mana <= player2.getCurMana() && board.Turn2)) {
                if (board.Battleground1.ActiveCard.containsKey("A")) {
                  summon = board.Battleground1.getChar("A");
                  Spell s = (Spell) card;
                  if (s.tipe == TypeSpell.MORPH) {
                    MORPH m = (MORPH) s;
                    m.setTarget((Character) cLib.getCardByID(m.id_swap_target));
                  }
                  s.setUser(summon);
                  s.use();
                  summon = s.getUser();
                  resetGUIBattleGroundOnChar();
                  updateDetailBattleground();
                  if (board.Turn1) {
                    player1.hand.inHand.remove(card);
                    resetGuiHand(player1);
                  } else {
                    player2.hand.inHand.remove(card);
                    resetGuiHand(player2);
                  }
                  if (board.Turn1) {
                    curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                  } else {
                    curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                  }
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "A";
                }
              } else {
                if (board.Battleground1.ActiveCard.containsKey("A") && board.Turn1) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "A";
                }
              }
            } else {
              if (board.Turn1 && board.Battleground1.ActiveCard.containsKey("A")) {
                key = "A";
              }
            }
          }
        } else if (board.getPhase() == TypePhase.ATTACK) {
          if (board.Turn1) {
            if (board.Battleground1.ActiveCard.containsKey("A")){
              if (!board.Battleground1.checkAlreadyAttacked("A")){
                offense = board.Battleground1.getChar("A");
                offense.setDefense("A");
                if (defense != null){ 
                  board.Battleground1.setAlreadyAttack("A",true);
                  if (defense == DEFAULT_SUM_CHAR){
                    offense.attackPlayer(player2);
                    nowHp2.setText(Integer.toString(player2.HP));
                    if (player2.getHp() <= 0){
                      alert.setContentText("Player 1 is winner");
                      alert.showAndWait();
                    }
                  }else{
                    offense.attackCharacter(defense);
                    defense.attackCharacter(offense);
                    if (offense.checkDie()){
                      board.Battleground1.removeChar("A");
                    } 
                    if (defense.checkDie()){
                      board.Battleground2.removeChar(defense.defense);
                    }
                    resetGUIBattleGroundOnChar();
                  }
                  resetATKBGColor();
                  resetOffenseDefense();
                }
              }
            }
          } else if (board.Turn2) {
            if (board.Battleground1.ActiveCard.containsKey("A")){
              defense = board.Battleground1.getChar("A");
              defense.setDefense("A");
              if (offense != null && offense != DEFAULT_SUM_CHAR){
                offense.attackCharacter(defense);
                defense.attackCharacter(offense);
                board.Battleground2.setAlreadyAttack(offense.defense, true);
                if (offense.checkDie()){
                  board.Battleground2.removeChar(offense.defense);
                } 
                if (defense.checkDie()){
                  board.Battleground1.removeChar("A");
                }
                resetGUIBattleGroundOnChar();
                resetATKBGColor();
                resetOffenseDefense();
              }
            }
          }
        }
      }
    });

    setImageBattleground(2);
    img1B.setX(25);
    img1B.setY(25);
    img1B.setFitWidth(50);
    img1B.setFitHeight(50);
    img1B.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground1.ActiveCard.containsKey("B")) {
          summon = board.Battleground1.getChar("B");
          Character c = summon.c;
          image = new Image(getClass().getResourceAsStream(c.ImagePath));
          handHover.setImage(image);
          updateDetailBattleground();
        }
      }
    });
    img1B.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (card.getClass().equals(Character.class)) { 
            if (board.Turn1) {
              if (card.Mana <= player1.getCurMana()) {
                if (!board.Battleground1.ActiveCard.containsKey("B")) {
                  summon = new SummonedChar((Character) card);
                  try {
                    board.Battleground1.addCard(summon, "B");
                  } catch (Exception e) {
                    // pass
                  }
                  image = new Image(getClass().getResourceAsStream(card.ImagePath));
                  img1B.setImage(image);
                  player1.hand.inHand.remove(card);
                  resetGuiHand(player1);
                  curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                }
                resetSelect();
                card = DEFAULT_CARD;
                key = "B";
              } else {
                if (board.Battleground1.ActiveCard.containsKey("B")) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "B";
                }
              }
            }
          } else {
            if (!card.getClass().equals(Card.class)) {
              if ((card.Mana <= player1.getCurMana() && board.Turn1) || (card.Mana <= player2.getCurMana() && board.Turn2)) {
                if (board.Battleground1.ActiveCard.containsKey("B")) {
                  summon = board.Battleground1.getChar("B");
                  Spell s = (Spell) card;
                  if (s.tipe == TypeSpell.MORPH) {
                    MORPH m = (MORPH) s;
                    m.setTarget((Character) cLib.getCardByID(m.id_swap_target));
                  }
                  s.setUser(summon);
                  s.use();
                  summon = s.getUser();
                  resetGUIBattleGroundOnChar();
                  updateDetailBattleground();
                  if (board.Turn1) {
                    player1.hand.inHand.remove(card);
                    resetGuiHand(player1);
                  } else {
                    player2.hand.inHand.remove(card);
                    resetGuiHand(player2);
                  }
                  if (board.Turn1) {
                    curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                  } else {
                    curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                  }
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "B";
                }
              } else {
                if (board.Battleground1.ActiveCard.containsKey("B") && board.Turn1) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "B";
                }
              }
            } else {
              if (board.Turn1 && board.Battleground1.ActiveCard.containsKey("B")) {
                key = "B";
              }
            }
          }
        } else if (board.getPhase() == TypePhase.ATTACK) {
          if (board.Turn1) {
            if (board.Battleground1.ActiveCard.containsKey("B")){
              if (!board.Battleground1.checkAlreadyAttacked("B")){
                offense = board.Battleground1.getChar("B");
                offense.setDefense("B");
                if (defense != null){ 
                  board.Battleground1.setAlreadyAttack("B",true);
                  if (defense == DEFAULT_SUM_CHAR){
                    offense.attackPlayer(player2);
                    nowHp2.setText(Integer.toString(player2.HP));
                    if (player2.getHp() <= 0){
                      alert.setContentText("Player 1 is winner");
                      alert.showAndWait();
                    }
                  }else{
                    offense.attackCharacter(defense);
                    defense.attackCharacter(offense);
                    if (offense.checkDie()){
                      board.Battleground1.removeChar("B");
                    } 
                    if (defense.checkDie()){
                      board.Battleground2.removeChar(defense.defense);
                    }
                    resetGUIBattleGroundOnChar();
                  }
                  resetATKBGColor();
                  resetOffenseDefense();
                }
              }
            }
          } else if (board.Turn2) {
            if (board.Battleground1.ActiveCard.containsKey("B")){
              defense = board.Battleground1.getChar("B");
              defense.setDefense("B");
              if (offense != null && offense != DEFAULT_SUM_CHAR){
                board.Battleground2.setAlreadyAttack(offense.defense, true);
                offense.attackCharacter(defense);
                defense.attackCharacter(offense);
                if (offense.checkDie()){
                  board.Battleground2.removeChar(offense.defense);
                } 
                if (defense.checkDie()){
                  board.Battleground1.removeChar("B");
                }
                resetGUIBattleGroundOnChar();
                resetATKBGColor();
                resetOffenseDefense();
              }
            }
          }
        }
      }
    });

    setImageBattleground(3);
    img1C.setX(25);
    img1C.setY(25);
    img1C.setFitWidth(50);
    img1C.setFitHeight(50);
    img1C.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground1.ActiveCard.containsKey("C")) {
          summon = board.Battleground1.getChar("C");
          Character c = summon.c;
          image = new Image(getClass().getResourceAsStream(c.ImagePath));
          handHover.setImage(image);
          updateDetailBattleground();
        }
      }
    });
    img1C.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (card.getClass().equals(Character.class)) { 
            if (board.Turn1) {
              if (card.Mana <= player1.getCurMana()) {
                if (!board.Battleground1.ActiveCard.containsKey("C")) {
                  summon = new SummonedChar((Character) card);
                  try {
                    board.Battleground1.addCard(summon, "C");
                  } catch (Exception e) {
                    // pass
                  }
                  image = new Image(getClass().getResourceAsStream(card.ImagePath));
                  img1C.setImage(image);
                  player1.hand.inHand.remove(card);
                  resetGuiHand(player1);
                  curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                }
                resetSelect();
                card = DEFAULT_CARD;
                key = "C";
              } else {
                if (board.Battleground1.ActiveCard.containsKey("C")) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "C";
                }
              }
            }
          } else {
            if (!card.getClass().equals(Card.class)) {
              if ((card.Mana <= player1.getCurMana() && board.Turn1) || (card.Mana <= player2.getCurMana() && board.Turn2)) {
                if (board.Battleground1.ActiveCard.containsKey("C")) {
                  summon = board.Battleground1.getChar("C");
                  Spell s = (Spell) card;
                  if (s.tipe == TypeSpell.MORPH) {
                    MORPH m = (MORPH) s;
                    m.setTarget((Character) cLib.getCardByID(m.id_swap_target));
                  }
                  s.setUser(summon);
                  s.use();
                  summon = s.getUser();
                  resetGUIBattleGroundOnChar();
                  updateDetailBattleground();
                  if (board.Turn1) {
                    player1.hand.inHand.remove(card);
                    resetGuiHand(player1);
                  } else {
                    player2.hand.inHand.remove(card);
                    resetGuiHand(player2);
                  }
                  if (board.Turn1) {
                    curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                  } else {
                    curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                  }
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "C";
                }
              } else {
                if (board.Battleground1.ActiveCard.containsKey("C") && board.Turn1) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "C";
                }
              }
            } else {
              if (board.Turn1 && board.Battleground1.ActiveCard.containsKey("C")) {
                key = "C";
              }
            }
          }
        } else if (board.getPhase() == TypePhase.ATTACK) {
          if (board.Turn1) {
            if (board.Battleground1.ActiveCard.containsKey("C")){
              if (!board.Battleground1.checkAlreadyAttacked("C")){
                offense = board.Battleground1.getChar("C");
                offense.setDefense("C");
                if (defense != null){ 
                  board.Battleground1.setAlreadyAttack("C",true);
                  if (defense == DEFAULT_SUM_CHAR){
                    offense.attackPlayer(player2);
                    nowHp2.setText(Integer.toString(player2.HP));
                    if (player2.getHp() <= 0){
                      alert.setContentText("Player 1 is winner");
                      alert.showAndWait();
                    }
                  }else{
                    offense.attackCharacter(defense);
                    defense.attackCharacter(offense);
                    if (offense.checkDie()){
                      board.Battleground1.removeChar("C");
                    } 
                    if (defense.checkDie()){
                      board.Battleground2.removeChar(defense.defense);
                    }
                    resetGUIBattleGroundOnChar();
                  }
                  resetATKBGColor();
                  resetOffenseDefense();
                }
              }
            }
          } else if (board.Turn2) {
            if (board.Battleground1.ActiveCard.containsKey("C")){
              defense = board.Battleground1.getChar("C");
              defense.setDefense("C");
              if (offense != null && offense != DEFAULT_SUM_CHAR){
                board.Battleground2.setAlreadyAttack(offense.defense, true);
                offense.attackCharacter(defense);
                defense.attackCharacter(offense);
                if (offense.checkDie()){
                  board.Battleground2.removeChar(offense.defense);
                } 
                if (defense.checkDie()){
                  board.Battleground1.removeChar("C");
                }
                resetGUIBattleGroundOnChar();
                resetATKBGColor();
                resetOffenseDefense();
              }
            }
          }
        }
      }
    });

    setImageBattleground(4);
    img1D.setX(25);
    img1D.setY(25);
    img1D.setFitWidth(50);
    img1D.setFitHeight(50);
    img1D.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground1.ActiveCard.containsKey("D")) {
          summon = board.Battleground1.getChar("D");
          Character c = summon.c;
          image = new Image(getClass().getResourceAsStream(c.ImagePath));
          handHover.setImage(image);
          updateDetailBattleground();
        }
      }
    });
    img1D.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (card.getClass().equals(Character.class)) { 
            if (board.Turn1) {
              if (card.Mana <= player1.getCurMana()) {
                if (!board.Battleground1.ActiveCard.containsKey("D")) {
                  summon = new SummonedChar((Character) card);
                  try {
                    board.Battleground1.addCard(summon, "D");
                  } catch (Exception e) {
                    // pass
                  }
                  image = new Image(getClass().getResourceAsStream(card.ImagePath));
                  img1D.setImage(image);
                  player1.hand.inHand.remove(card);
                  resetGuiHand(player1);
                  curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                }
                resetSelect();
                card = DEFAULT_CARD;
                key = "D";
              } else {
                if (board.Battleground1.ActiveCard.containsKey("D")) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "D";
                }
              }
            }
          } else {
            if (!card.getClass().equals(Card.class)) {
              if ((card.Mana <= player1.getCurMana() && board.Turn1) || (card.Mana <= player2.getCurMana() && board.Turn2)) {
                if (board.Battleground1.ActiveCard.containsKey("D")) {
                  summon = board.Battleground1.getChar("D");
                  Spell s = (Spell) card;
                  if (s.tipe == TypeSpell.MORPH) {
                    MORPH m = (MORPH) s;
                    m.setTarget((Character) cLib.getCardByID(m.id_swap_target));
                  }
                  s.setUser(summon);
                  s.use();
                  summon = s.getUser();
                  resetGUIBattleGroundOnChar();
                  updateDetailBattleground();
                  if (board.Turn1) {
                    player1.hand.inHand.remove(card);
                    resetGuiHand(player1);
                  } else {
                    player2.hand.inHand.remove(card);
                    resetGuiHand(player2);
                  }
                  if (board.Turn1) {
                    curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                  } else {
                    curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                  }
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "D";
                }
              } else {
                if (board.Battleground1.ActiveCard.containsKey("D") && board.Turn1) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "D";
                }
              }
            } else {
              if (board.Turn1 && board.Battleground1.ActiveCard.containsKey("D")) {
                key = "D";
              }
            }
          }
        }else if (board.getPhase() == TypePhase.ATTACK) {
          if (board.Turn1) {
            if (board.Battleground1.ActiveCard.containsKey("D")){
              if (!board.Battleground1.checkAlreadyAttacked("D")){
                offense = board.Battleground1.getChar("D");
                offense.setDefense("D");
                if (defense != null){ 
                  board.Battleground1.setAlreadyAttack("D",true);
                  if (defense == DEFAULT_SUM_CHAR){
                    offense.attackPlayer(player2);
                    nowHp2.setText(Integer.toString(player2.HP));
                    if (player2.getHp() <= 0){
                      alert.setContentText("Player 1 is winner");
                      alert.showAndWait();
                    }
                  }else{
                    offense.attackCharacter(defense);
                    defense.attackCharacter(offense);
                    if (offense.checkDie()){
                      board.Battleground1.removeChar("D");
                    } 
                    if (defense.checkDie()){
                      board.Battleground2.removeChar(defense.defense);
                    }
                    resetGUIBattleGroundOnChar();
                  }
                  resetATKBGColor();
                  resetOffenseDefense();
                }
              }
            }
          } else if (board.Turn2) {
            if (board.Battleground1.ActiveCard.containsKey("D")){
              defense = board.Battleground1.getChar("D");
              defense.setDefense("D");
              if (offense != null && offense != DEFAULT_SUM_CHAR){
                board.Battleground2.setAlreadyAttack(offense.defense, true);
                offense.attackCharacter(defense);
                defense.attackCharacter(offense);
                if (offense.checkDie()){
                  board.Battleground2.removeChar(offense.defense);
                } 
                if (defense.checkDie()){
                  board.Battleground1.removeChar("D");
                }
                resetGUIBattleGroundOnChar();
                resetATKBGColor();
                resetOffenseDefense();
              }
            }
          }
        }
      }
    });

    setImageBattleground(5);
    img1E.setX(25);
    img1E.setY(25);
    img1E.setFitWidth(50);
    img1E.setFitHeight(50);
    img1E.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground1.ActiveCard.containsKey("E")) {
          summon = board.Battleground1.getChar("E");
          Character c = summon.c;
          image = new Image(getClass().getResourceAsStream(c.ImagePath));
          handHover.setImage(image);
          updateDetailBattleground();
        }
      }
    });
    img1E.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (card.getClass().equals(Character.class)) { 
            if (board.Turn1) {
              if (card.Mana <= player1.getCurMana()) {
                if (!board.Battleground1.ActiveCard.containsKey("E")) {
                  summon = new SummonedChar((Character) card);
                  try {
                    board.Battleground1.addCard(summon, "E");
                  } catch (Exception e) {
                    // pass
                  }
                  image = new Image(getClass().getResourceAsStream(card.ImagePath));
                  img1E.setImage(image);
                  player1.hand.inHand.remove(card);
                  resetGuiHand(player1);
                  curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                }
                resetSelect();
                card = DEFAULT_CARD;
                key = "E";
              } else {
                if (board.Battleground1.ActiveCard.containsKey("E")) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "E";
                }
              }
            }
          } else {
            if (!card.getClass().equals(Card.class)) {
              if ((card.Mana <= player1.getCurMana() && board.Turn1) || (card.Mana <= player2.getCurMana() && board.Turn2)) {
                if (board.Battleground1.ActiveCard.containsKey("E")) {
                  summon = board.Battleground1.getChar("E");
                  Spell s = (Spell) card;
                  if (s.tipe == TypeSpell.MORPH) {
                    MORPH m = (MORPH) s;
                    m.setTarget((Character) cLib.getCardByID(m.id_swap_target));
                  }
                  s.setUser(summon);
                  s.use();
                  summon = s.getUser();
                  resetGUIBattleGroundOnChar();
                  updateDetailBattleground();
                  if (board.Turn1) {
                    player1.hand.inHand.remove(card);
                    resetGuiHand(player1);
                  } else {
                    player2.hand.inHand.remove(card);
                    resetGuiHand(player2);
                  }
                  if (board.Turn1) {
                    curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                  } else {
                    curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                  }
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "E";
                }
              } else {
                if (board.Battleground1.ActiveCard.containsKey("E") && board.Turn1) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "E";
                }
              }
            } else {
              if (board.Turn1 && board.Battleground1.ActiveCard.containsKey("E")) {
                key = "E";
              }
            }
          }
        } else if (board.getPhase() == TypePhase.ATTACK) {
          if (board.Turn1) {
            if (board.Battleground1.ActiveCard.containsKey("E")){
              if (!board.Battleground1.checkAlreadyAttacked("E")){
                offense = board.Battleground1.getChar("E");
                offense.setDefense("E");
                if (defense != null){ 
                  board.Battleground1.setAlreadyAttack("E",true);
                  if (defense == DEFAULT_SUM_CHAR){
                    offense.attackPlayer(player2);
                    nowHp2.setText(Integer.toString(player2.HP));
                    if (player2.getHp() <= 0){
                      alert.setContentText("Player 1 is winner");
                      alert.showAndWait();
                    }
                  }else{
                    offense.attackCharacter(defense);
                    defense.attackCharacter(offense);
                    if (offense.checkDie()){
                      board.Battleground1.removeChar("E");
                    } 
                    if (defense.checkDie()){
                      board.Battleground2.removeChar(defense.defense);
                    }
                    resetGUIBattleGroundOnChar();
                  }
                  resetATKBGColor();
                  resetOffenseDefense();
                }
              }
            }
          } else if (board.Turn2) {
            if (board.Battleground1.ActiveCard.containsKey("E")){
              defense = board.Battleground1.getChar("E");
              defense.setDefense("E");
              if (offense != null && offense != DEFAULT_SUM_CHAR){
                board.Battleground2.setAlreadyAttack(offense.defense, true);
                offense.attackCharacter(defense);
                defense.attackCharacter(offense);
                if (offense.checkDie()){
                  board.Battleground2.removeChar(offense.defense);
                } 
                if (defense.checkDie()){
                  board.Battleground1.removeChar("E");
                }
                resetGUIBattleGroundOnChar();
                resetATKBGColor();
                resetOffenseDefense();
              }
            }
          }
        }
      }
    });

    setImageBattleground(6);
    img2A.setX(25);
    img2A.setY(25);
    img2A.setFitWidth(50);
    img2A.setFitHeight(50);
    img2A.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground2.ActiveCard.containsKey("A")) {
          summon = board.Battleground2.getChar("A");
          Character c = summon.c;
          image = new Image(getClass().getResourceAsStream(c.ImagePath));
          handHover.setImage(image);
          updateDetailBattleground();
        }
      }
    });
    img2A.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (card.getClass().equals(Character.class)) { 
            if (board.Turn2) {
              if (card.Mana <= player2.getCurMana()) {
                if (!board.Battleground2.ActiveCard.containsKey("A")) {
                  summon = new SummonedChar((Character) card);
                  try {
                    board.Battleground2.addCard(summon, "A");
                  } catch (Exception e) {
                    // pass
                  }
                  image = new Image(getClass().getResourceAsStream(card.ImagePath));
                  img2A.setImage(image);
                  player2.hand.inHand.remove(card);
                  resetGuiHand(player2);
                  curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                }
                resetSelect();
                card = DEFAULT_CARD;
                key = "A";
              } else {
                if (board.Battleground2.ActiveCard.containsKey("A")) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "A";
                }
              }
            }
          } else {
            if (!card.getClass().equals(Card.class)) {
              if ((card.Mana <= player1.getCurMana() && board.Turn1) || (card.Mana <= player2.getCurMana() && board.Turn2)) {
                if (board.Battleground2.ActiveCard.containsKey("A")) {
                  summon = board.Battleground2.getChar("A");
                  Spell s = (Spell) card;
                  if (s.tipe == TypeSpell.MORPH) {
                    MORPH m = (MORPH) s;
                    m.setTarget((Character) cLib.getCardByID(m.id_swap_target));
                  }
                  s.setUser(summon);
                  s.use();
                  summon = s.getUser();
                  resetGUIBattleGroundOnChar();
                  updateDetailBattleground();
                  if (board.Turn1) {
                    player1.hand.inHand.remove(card);
                    resetGuiHand(player1);
                  } else {
                    player2.hand.inHand.remove(card);
                    resetGuiHand(player2);
                  }
                  if (board.Turn1) {
                    curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                  } else {
                    curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                  }
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "A";
                }
              } else {
                if (board.Battleground2.ActiveCard.containsKey("A") && board.Turn2) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "A";
                }
              }
            } else {
              if (board.Turn2 && board.Battleground2.ActiveCard.containsKey("A")) {
                key = "A";
              }
            }
          }
        } else if (board.getPhase() == TypePhase.ATTACK) {
          if (board.Turn2) {
            if (board.Battleground2.ActiveCard.containsKey("A")){
              if (!board.Battleground2.checkAlreadyAttacked("A")){
                offense = board.Battleground2.getChar("A");
                offense.setDefense("A");
                if (defense != null){ 
                  board.Battleground2.setAlreadyAttack("A",true);
                  if (defense == DEFAULT_SUM_CHAR){
                    offense.attackPlayer(player1);
                    nowHp1.setText(Integer.toString(player1.HP));
                    if (player1.getHp() <= 0){
                      alert.setContentText("Player 2 is winner");
                      alert.showAndWait();
                    }
                  }else{
                    offense.attackCharacter(defense);
                    defense.attackCharacter(offense);
                    if (offense.checkDie()){
                      board.Battleground2.removeChar("A");
                    } 
                    if (defense.checkDie()){
                      board.Battleground1.removeChar(defense.defense);
                    }
                    resetGUIBattleGroundOnChar();
                  }
                  resetATKBGColor();
                  resetOffenseDefense();
                }
              }
            }
          } else if (board.Turn1) {
            if (board.Battleground2.ActiveCard.containsKey("A")){
              defense = board.Battleground2.getChar("A");
              defense.setDefense("A");
              if (offense != null && offense != DEFAULT_SUM_CHAR){
                board.Battleground1.setAlreadyAttack(offense.defense, true);
                offense.attackCharacter(defense);
                defense.attackCharacter(offense);
                if (offense.checkDie()){
                  board.Battleground1.removeChar(offense.defense);
                } 
                if (defense.checkDie()){
                  board.Battleground2.removeChar("A");
                }
                resetGUIBattleGroundOnChar();
                resetATKBGColor();
                resetOffenseDefense();
              }
            }
          }
        }
      }
    });

    setImageBattleground(7);
    img2B.setX(25);
    img2B.setY(25);
    img2B.setFitWidth(50);
    img2B.setFitHeight(50);
    img2B.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground2.ActiveCard.containsKey("B")) {
          summon = board.Battleground2.getChar("B");
          Character c = summon.c;
          image = new Image(getClass().getResourceAsStream(c.ImagePath));
          handHover.setImage(image);
          updateDetailBattleground();
        }
      }
    });
    img2B.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (card.getClass().equals(Character.class)) { 
            if (board.Turn2) {
              if (card.Mana <= player2.getCurMana()) {
                if (!board.Battleground2.ActiveCard.containsKey("B")) {
                  summon = new SummonedChar((Character) card);
                  try {
                    board.Battleground2.addCard(summon, "B");
                  } catch (Exception e) {
                    // pass
                  }
                  image = new Image(getClass().getResourceAsStream(card.ImagePath));
                  img2B.setImage(image);
                  player2.hand.inHand.remove(card);
                  resetGuiHand(player2);
                  curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                }
                resetSelect();
                card = DEFAULT_CARD;
                key = "B";
              } else {
                if (board.Battleground2.ActiveCard.containsKey("B")) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "B";
                }
              }
            }
          } else {
            if (!card.getClass().equals(Card.class)) {
              if ((card.Mana <= player1.getCurMana() && board.Turn1) || (card.Mana <= player2.getCurMana() && board.Turn2)) {
                if (board.Battleground2.ActiveCard.containsKey("B")) {
                  summon = board.Battleground2.getChar("B");
                  Spell s = (Spell) card;
                  if (s.tipe == TypeSpell.MORPH) {
                    MORPH m = (MORPH) s;
                    m.setTarget((Character) cLib.getCardByID(m.id_swap_target));
                  }
                  s.setUser(summon);
                  s.use();
                  summon = s.getUser();
                  resetGUIBattleGroundOnChar();
                  updateDetailBattleground();
                  if (board.Turn1) {
                    player1.hand.inHand.remove(card);
                    resetGuiHand(player1);
                  } else {
                    player2.hand.inHand.remove(card);
                    resetGuiHand(player2);
                  }
                  if (board.Turn1) {
                    curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                  } else {
                    curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                  }
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "B";
                }
              } else {
                if (board.Battleground2.ActiveCard.containsKey("B") && board.Turn2) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "B";
                }
              }
            } else {
              if (board.Turn2 && board.Battleground2.ActiveCard.containsKey("B")) {
                key = "B";
              }
            }
          }
        } else if (board.getPhase() == TypePhase.ATTACK) {
          if (board.Turn2) {
            if (board.Battleground2.ActiveCard.containsKey("B")){
              if (!board.Battleground2.checkAlreadyAttacked("B")){
                offense = board.Battleground2.getChar("B");
                offense.setDefense("B");
                if (defense != null){ 
                  board.Battleground2.setAlreadyAttack("B",true);
                  if (defense == DEFAULT_SUM_CHAR){
                    offense.attackPlayer(player1);
                    nowHp1.setText(Integer.toString(player1.HP));
                    if (player1.getHp() <= 0){
                      alert.setContentText("Player 2 is winner");
                      alert.showAndWait();
                    }
                  }else{
                    offense.attackCharacter(defense);
                    defense.attackCharacter(offense);
                    if (offense.checkDie()){
                      board.Battleground2.removeChar("B");
                    } 
                    if (defense.checkDie()){
                      board.Battleground1.removeChar(defense.defense);
                    }
                    resetGUIBattleGroundOnChar();
                  }
                  resetATKBGColor();
                  resetOffenseDefense();
                }
              }
            }
          } else if (board.Turn1) {
            if (board.Battleground2.ActiveCard.containsKey("B")){
              defense = board.Battleground2.getChar("B");
              defense.setDefense("B");
              if (offense != null && offense != DEFAULT_SUM_CHAR){
                board.Battleground1.setAlreadyAttack(offense.defense, true);
                offense.attackCharacter(defense);
                defense.attackCharacter(offense);
                if (offense.checkDie()){
                  board.Battleground1.removeChar(offense.defense);
                } 
                if (defense.checkDie()){
                  board.Battleground2.removeChar("B");
                }
                resetGUIBattleGroundOnChar();
                resetATKBGColor();
                resetOffenseDefense();
              }
            }
          }
        }
      }
    });

    setImageBattleground(8);
    img2C.setX(25);
    img2C.setY(25);
    img2C.setFitWidth(50);
    img2C.setFitHeight(50);
    img2C.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground2.ActiveCard.containsKey("C")) {
          summon = board.Battleground2.getChar("C");
          Character c = summon.c;
          image = new Image(getClass().getResourceAsStream(c.ImagePath));
          handHover.setImage(image);
          updateDetailBattleground();
        }
      }
    });
    img2C.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (card.getClass().equals(Character.class)) { 
            if (board.Turn2) {
              if (card.Mana <= player2.getCurMana()) {
                if (!board.Battleground2.ActiveCard.containsKey("C")) {
                  summon = new SummonedChar((Character) card);
                  try {
                    board.Battleground2.addCard(summon, "C");
                  } catch (Exception e) {
                    // pass
                  }
                  image = new Image(getClass().getResourceAsStream(card.ImagePath));
                  img2C.setImage(image);
                  player2.hand.inHand.remove(card);
                  resetGuiHand(player2);
                  curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                }
                resetSelect();
                card = DEFAULT_CARD;
                key = "C";
              } else {
                if (board.Battleground2.ActiveCard.containsKey("C")) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "C";
                }
              }
            }
          } else {
            if (!card.getClass().equals(Card.class)) {
              if ((card.Mana <= player1.getCurMana() && board.Turn1) || (card.Mana <= player2.getCurMana() && board.Turn2)) {
                if (board.Battleground2.ActiveCard.containsKey("C")) {
                  summon = board.Battleground2.getChar("C");
                  Spell s = (Spell) card;
                  if (s.tipe == TypeSpell.MORPH) {
                    MORPH m = (MORPH) s;
                    m.setTarget((Character) cLib.getCardByID(m.id_swap_target));
                  }
                  s.setUser(summon);
                  s.use();
                  summon = s.getUser();
                  resetGUIBattleGroundOnChar();
                  updateDetailBattleground();
                  if (board.Turn1) {
                    player1.hand.inHand.remove(card);
                    resetGuiHand(player1);
                  } else {
                    player2.hand.inHand.remove(card);
                    resetGuiHand(player2);
                  }
                  if (board.Turn1) {
                    curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                  } else {
                    curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                  }
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "C";
                }
              } else {
                if (board.Battleground2.ActiveCard.containsKey("C") && board.Turn2) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "C";
                }
              }
            } else {
              if (board.Turn2 && board.Battleground2.ActiveCard.containsKey("C")) {
                key = "C";
              }
            }
          }
        } else if (board.getPhase() == TypePhase.ATTACK) {
          if (board.Turn2) {
            if (board.Battleground2.ActiveCard.containsKey("C")){
              if (!board.Battleground2.checkAlreadyAttacked("C")){
                offense = board.Battleground2.getChar("C");
                offense.setDefense("C");
                if (defense != null){ 
                  board.Battleground2.setAlreadyAttack("C",true);
                  if (defense == DEFAULT_SUM_CHAR){
                    offense.attackPlayer(player1);
                    nowHp1.setText(Integer.toString(player1.HP));
                    if (player1.getHp() <= 0){
                      alert.setContentText("Player 2 is winner");
                      alert.showAndWait();
                    }
                  }else{
                    offense.attackCharacter(defense);
                    defense.attackCharacter(offense);
                    if (offense.checkDie()){
                      board.Battleground2.removeChar("C");
                    } 
                    if (defense.checkDie()){
                      board.Battleground1.removeChar(defense.defense);
                    }
                    resetGUIBattleGroundOnChar();
                  }
                  resetATKBGColor();
                  resetOffenseDefense();
                }
              }
            }
          } else if (board.Turn1) {
            if (board.Battleground2.ActiveCard.containsKey("C")){
              defense = board.Battleground2.getChar("C");
              defense.setDefense("C");
              if (offense != null && offense != DEFAULT_SUM_CHAR){
                board.Battleground1.setAlreadyAttack(offense.defense, true);
                offense.attackCharacter(defense);
                defense.attackCharacter(offense);
                if (offense.checkDie()){
                  board.Battleground1.removeChar(offense.defense);
                } 
                if (defense.checkDie()){
                  board.Battleground2.removeChar("C");
                }
                resetGUIBattleGroundOnChar();
                resetATKBGColor();
                resetOffenseDefense();
              }
            }
          }
        }
      }
    });

    setImageBattleground(9);
    img2D.setX(25);
    img2D.setY(25);
    img2D.setFitWidth(50);
    img2D.setFitHeight(50);
    img2D.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground2.ActiveCard.containsKey("D")) {
          summon = board.Battleground2.getChar("D");
          Character c = summon.c;
          image = new Image(getClass().getResourceAsStream(c.ImagePath));
          handHover.setImage(image);
          updateDetailBattleground();
        }
      }
    });
    img2D.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (card.getClass().equals(Character.class)) { 
            if (board.Turn2) {
              if (card.Mana <= player2.getCurMana()) {
                if (!board.Battleground2.ActiveCard.containsKey("D")) {
                  summon = new SummonedChar((Character) card);
                  try {
                    board.Battleground2.addCard(summon, "D");
                  } catch (Exception e) {
                    // pass
                  }
                  image = new Image(getClass().getResourceAsStream(card.ImagePath));
                  img2D.setImage(image);
                  player2.hand.inHand.remove(card);
                  resetGuiHand(player2);
                  curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                }
                resetSelect();
                card = DEFAULT_CARD;
                key = "D";
              } else {
                if (board.Battleground2.ActiveCard.containsKey("D")) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "D";
                }
              }
            }
          } else {
            if (!card.getClass().equals(Card.class)) {
              if ((card.Mana <= player1.getCurMana() && board.Turn1) || (card.Mana <= player2.getCurMana() && board.Turn2)) {
                if (board.Battleground2.ActiveCard.containsKey("D")) {
                  summon = board.Battleground2.getChar("D");
                  Spell s = (Spell) card;
                  if (s.tipe == TypeSpell.MORPH) {
                    MORPH m = (MORPH) s;
                    m.setTarget((Character) cLib.getCardByID(m.id_swap_target));
                  }
                  s.setUser(summon);
                  s.use();
                  summon = s.getUser();
                  resetGUIBattleGroundOnChar();
                  updateDetailBattleground();
                  if (board.Turn1) {
                    player1.hand.inHand.remove(card);
                    resetGuiHand(player1);
                  } else {
                    player2.hand.inHand.remove(card);
                    resetGuiHand(player2);
                  }
                  if (board.Turn1) {
                    curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                  } else {
                    curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                  }
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "D";
                }
              } else {
                if (board.Battleground2.ActiveCard.containsKey("D") && board.Turn2) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "D";
                }
              }
            } else {
              if (board.Turn2 && board.Battleground2.ActiveCard.containsKey("D")) {
                key = "D";
              }
            }
          }
        } else if (board.getPhase() == TypePhase.ATTACK) {
          if (board.Turn2) {
            if (board.Battleground2.ActiveCard.containsKey("D")){
              if (!board.Battleground2.checkAlreadyAttacked("D")){
                offense = board.Battleground2.getChar("D");
                offense.setDefense("D");
                if (defense != null){ 
                  board.Battleground2.setAlreadyAttack("D",true);
                  if (defense == DEFAULT_SUM_CHAR){
                    offense.attackPlayer(player1);
                    nowHp1.setText(Integer.toString(player1.HP));
                    if (player1.getHp() <= 0){
                      alert.setContentText("Player 2 is winner");
                      alert.showAndWait();
                    }
                  }else{
                    offense.attackCharacter(defense);
                    defense.attackCharacter(offense);
                    if (offense.checkDie()){
                      board.Battleground2.removeChar("D");
                    } 
                    if (defense.checkDie()){
                      board.Battleground1.removeChar(defense.defense);
                    }
                    resetGUIBattleGroundOnChar();
                  }
                  resetATKBGColor();
                  resetOffenseDefense();
                }
              }
            }
          } else if (board.Turn1) {
            if (board.Battleground2.ActiveCard.containsKey("D")){
              defense = board.Battleground2.getChar("D");
              defense.setDefense("D");
              if (offense != null && offense != DEFAULT_SUM_CHAR){
                board.Battleground1.setAlreadyAttack(offense.defense, true);
                offense.attackCharacter(defense);
                defense.attackCharacter(offense);
                if (offense.checkDie()){
                  board.Battleground1.removeChar(offense.defense);
                } 
                if (defense.checkDie()){
                  board.Battleground2.removeChar("D");
                }
                resetGUIBattleGroundOnChar();
                resetATKBGColor();
                resetOffenseDefense();
              }
            }
          }
        }
      }
    });

    setImageBattleground(10);
    img2E.setX(25);
    img2E.setY(25);
    img2E.setFitWidth(50);
    img2E.setFitHeight(50);
    img2E.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground2.ActiveCard.containsKey("E")) {
          summon = board.Battleground2.getChar("E");
          Character c = summon.c;
          image = new Image(getClass().getResourceAsStream(c.ImagePath));
          handHover.setImage(image);
          updateDetailBattleground();
        }
      }
    });
    img2E.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (card.getClass().equals(Character.class)) { 
            if (board.Turn2) {
              if (card.Mana <= player2.getCurMana()) {
                if (!board.Battleground2.ActiveCard.containsKey("E")) {
                  summon = new SummonedChar((Character) card);
                  try {
                    board.Battleground2.addCard(summon, "E");
                  } catch (Exception e) {
                    // pass
                  }
                  image = new Image(getClass().getResourceAsStream(card.ImagePath));
                  img2E.setImage(image);
                  player2.hand.inHand.remove(card);
                  resetGuiHand(player2);
                  curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                }
                resetSelect();
                card = DEFAULT_CARD;
                key = "E";
              } else {
                if (board.Battleground2.ActiveCard.containsKey("A")) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "A";
                }
              }
            }
          } else {
            if (!card.getClass().equals(Card.class)) {
              if ((card.Mana <= player1.getCurMana() && board.Turn1) || (card.Mana <= player2.getCurMana() && board.Turn2)) {
                if (board.Battleground2.ActiveCard.containsKey("E")) {
                  summon = board.Battleground2.getChar("E");
                  Spell s = (Spell) card;
                  if (s.tipe == TypeSpell.MORPH) {
                    MORPH m = (MORPH) s;
                    m.setTarget((Character) cLib.getCardByID(m.id_swap_target));
                  }
                  s.setUser(summon);
                  s.use();
                  summon = s.getUser();
                  resetGUIBattleGroundOnChar();
                  updateDetailBattleground();
                  if (board.Turn1) {
                    player1.hand.inHand.remove(card);
                    resetGuiHand(player1);
                  } else {
                    player2.hand.inHand.remove(card);
                    resetGuiHand(player2);
                  }
                  if (board.Turn1) {
                    curMana.setText(Integer.toString(player1.reduceMana(card.Mana)));
                  } else {
                    curMana.setText(Integer.toString(player2.reduceMana(card.Mana)));
                  }
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "E";
                }
              } else {
                if (board.Battleground2.ActiveCard.containsKey("E") && board.Turn2) {
                  resetSelect();
                  card = DEFAULT_CARD;
                  key = "E";
                }
              }
            } else {
              if (board.Turn2 && board.Battleground2.ActiveCard.containsKey("E")) {
                key = "E";
              }
            }
          }
        } else if (board.getPhase() == TypePhase.ATTACK) {
          if (board.Turn2) {
            if (board.Battleground2.ActiveCard.containsKey("E")){
              if (!board.Battleground2.checkAlreadyAttacked("E")){
                offense = board.Battleground2.getChar("E");
                offense.setDefense("E");
                if (defense != null){ 
                  board.Battleground2.setAlreadyAttack("E",true);
                  if (defense == DEFAULT_SUM_CHAR){
                    offense.attackPlayer(player1);
                    nowHp1.setText(Integer.toString(player1.HP));
                    if (player1.getHp() <= 0){
                      alert.setContentText("Player 2 is winner");
                      alert.showAndWait();
                    }
                  }else{
                    offense.attackCharacter(defense);
                    defense.attackCharacter(offense);
                    if (offense.checkDie()){
                      board.Battleground2.removeChar("E");
                    } 
                    if (defense.checkDie()){
                      board.Battleground1.removeChar(defense.defense);
                    }
                    resetGUIBattleGroundOnChar();
                  }
                  resetATKBGColor();
                  resetOffenseDefense();
                }
              }
            }
          } else if (board.Turn1) {
            if (board.Battleground2.ActiveCard.containsKey("E")){
              defense = board.Battleground2.getChar("E");
              defense.setDefense("E");
              if (offense != null && offense != DEFAULT_SUM_CHAR){
                board.Battleground1.setAlreadyAttack(offense.defense, true);
                offense.attackCharacter(defense);
                defense.attackCharacter(offense);
                if (offense.checkDie()){
                  board.Battleground1.removeChar(offense.defense);
                } 
                if (defense.checkDie()){
                  board.Battleground2.removeChar("E");
                }
                resetGUIBattleGroundOnChar();
                resetATKBGColor();
                resetOffenseDefense();
              }
            }
          }
        }
      }
    });

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

    // Add exp setup 
    addExp = new Button("Add Exp");
    addExp.setVisible(false);
    addExp.setLayoutX(585);
    addExp.setLayoutY(275);
    addExp.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (key != null ) {
          if (board.Turn1 && !player1.isEmptyMana()) {
            board.Battleground1.getChar(key).addExp(1);;
            summon = board.Battleground1.getChar(key);
            updateDetailBattleground();
            curMana.setText(Integer.toString(player1.reduceMana(1)));
          } else if (board.Turn2 && !player2.isEmptyMana()) {
            board.Battleground2.getChar(key).addExp(1);
            summon = board.Battleground2.getChar(key);
            updateDetailBattleground();
            curMana.setText(Integer.toString(player2.reduceMana(1)));
          }
        }
      }
    });
    
    // Delete card setup
    deleteCard = new Button("Delete Card");
    deleteCard.setVisible(false);
    deleteCard.setLayoutX(575);
    deleteCard.setLayoutY(325);
    deleteCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (!card.getClass().equals(Card.class)) {
          if (board.Turn1) {
            player1.hand.inHand.remove(card);
            resetGuiHand(player1);
          } else {
            player2.hand.inHand.remove(card);
            resetGuiHand(player2);
          }
          card = DEFAULT_CARD;
          image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
          handHover.setImage(image);
          updateDetailHand();
          resetSelect();
        } else {
          if (key != null) {
            image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
            if (board.Turn1) {
              board.Battleground1.removeChar(key);
              setImageBattleground(getIdxFromKey(key));
            } else {
              board.Battleground2.removeChar(key);
              setImageBattleground(getIdxFromKey(key) + 5);
            }
            key = null;
            handHover.setImage(image);
            updateDetailHand();
            resetSelectBG();
          }
        }
      }
    });

    // Hand setup
    image = new Image(getClass().getResourceAsStream(player1.hand.getCard(0).ImagePath));
    setImageHand(1);
    hand1.setX(15);
    hand1.setY(10);
    hand1.setFitWidth(50);
    hand1.setFitHeight(100);
    hand1.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (board.Turn1 && player1.hand.getSize() > 0) {
            card = player1.hand.getCard(0);
            image = new Image(getClass().getResourceAsStream(card.ImagePath));
            handHover.setImage(image);
            key = null;
          }
        }
      }
    });
    handMana1 = new Text("Mana " + Integer.toString(player1.hand.getCard(0).Mana));
    handMana1.setX(15);
    handMana1.setY(125);

    image = new Image(getClass().getResourceAsStream(player1.hand.getCard(1).ImagePath));
    setImageHand(2);
    hand2.setX(15);
    hand2.setY(10);
    hand2.setFitWidth(50);
    hand2.setFitHeight(100);
    hand2.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (board.Turn1 && player1.hand.getSize() > 1) {
            card = player1.hand.getCard(1);
            image = new Image(getClass().getResourceAsStream(card.ImagePath));
            handHover.setImage(image);
            resetSelectBG();
            key = null;
          }
        }
      }
    });
    handMana2 = new Text("Mana " + Integer.toString(player1.hand.getCard(1).Mana));
    handMana2.setX(15);
    handMana2.setY(125);

    image = new Image(getClass().getResourceAsStream(player1.hand.getCard(2).ImagePath));
    setImageHand(3);
    hand3.setX(15);
    hand3.setY(10);
    hand3.setFitWidth(50);
    hand3.setFitHeight(100);
    hand3.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (board.Turn1 && player1.hand.getSize() > 2) {
            card = player1.hand.getCard(2);
            image = new Image(getClass().getResourceAsStream(card.ImagePath));
            handHover.setImage(image);
            resetSelectBG();
            key = null;
          }
        }
      }
    });
    handMana3 = new Text("Mana " + Integer.toString(player1.hand.getCard(2).Mana));
    handMana3.setX(15);
    handMana3.setY(125);

    image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
    setImageHand(4);
    hand4.setX(15);
    hand4.setY(10);
    hand4.setFitWidth(50);
    hand4.setFitHeight(100);
    hand4.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (board.Turn1 && player1.hand.getSize() > 3) {
            card = player1.hand.getCard(3);
            image = new Image(getClass().getResourceAsStream(card.ImagePath));
            handHover.setImage(image);
            resetSelectBG();
            key = null;
          }
        }
      }
    });
    handMana4 = new Text();
    handMana4.setX(15);
    handMana4.setY(125);

    setImageHand(5);
    hand5.setX(15);
    hand5.setY(10);
    hand5.setFitWidth(50);
    hand5.setFitHeight(100);
    hand5.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.getPhase() == TypePhase.PLANNING) {
          if (board.Turn1 && player1.hand.getSize() > 4) {
            card = player1.hand.getCard(4);
            image = new Image(getClass().getResourceAsStream(card.ImagePath));
            handHover.setImage(image);
            resetSelectBG();
            key = null;
          }
        }
      }
    });
    handMana5 = new Text();
    handMana5.setX(15);
    handMana5.setY(125);

    setHover(player1);

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
    // System.out.println(drawCard.get(0).ImagePath);
    // System.out.println(drawCard.get(1).ImagePath);
    // System.out.println(drawCard.get(2).ImagePath);
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
        if (board.Turn1){
          clickDraw(player1, 0);
        } else {
          clickDraw(player2, 0);
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
        if (board.Turn1){
          clickDraw(player1, 1);
        } else {
          clickDraw(player2, 1);
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
        if (board.Turn1){
          clickDraw(player1, 2);
        } else {
          clickDraw(player2, 2);
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

    root.getChildren().add(addExp);
    root.getChildren().add(deleteCard);

    root.getChildren().add(draw);
    root.getChildren().add(plan);
    root.getChildren().add(attack);
    root.getChildren().add(end);
    root.getChildren().add(next);

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
    tangan1.getChildren().add(handMana1);

    tangan2.setStyle("-fx-background-color: gray;");
    tangan2.setPrefHeight(150.0);
    tangan2.setPrefWidth(90.0);
    tangan2.setLayoutX(190);
    tangan2.setLayoutY(500);
    tangan2.getChildren().add(hand2);
    tangan2.getChildren().add(handMana2);

    tangan3.setStyle("-fx-background-color: gray;");
    tangan3.setPrefHeight(150.0);
    tangan3.setPrefWidth(90.0);
    tangan3.setLayoutX(290);
    tangan3.setLayoutY(500);
    tangan3.getChildren().add(hand3);
    tangan3.getChildren().add(handMana3);

    tangan4.setStyle("-fx-background-color: gray;");
    tangan4.setPrefHeight(150.0);
    tangan4.setPrefWidth(90.0);
    tangan4.setLayoutX(390);
    tangan4.setLayoutY(500);
    tangan4.getChildren().add(hand4);
    tangan4.getChildren().add(handMana4);

    tangan5.setStyle("-fx-background-color: gray;");
    tangan5.setPrefHeight(150.0);
    tangan5.setPrefWidth(90.0);
    tangan5.setLayoutX(490);
    tangan5.setLayoutY(500);
    tangan5.getChildren().add(hand5);
    tangan5.getChildren().add(handMana5);

    hand1.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (!handMana1.getText().equals("")){
          resetSelect();
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
          }
          tangan1.setStyle("-fx-background-color: yellow;");
        }
      }
    });

    hand2.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (!handMana2.getText().equals("")){
          resetSelect();
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
          }
          tangan2.setStyle("-fx-background-color: yellow;");
        }
      }
    });

    hand3.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (!handMana3.getText().equals("")){
          resetSelect();
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
          }
          tangan3.setStyle("-fx-background-color: yellow;");
        }
      }
    });

    hand4.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (!handMana4.getText().equals("")){
          resetSelect();
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
          }
          tangan4.setStyle("-fx-background-color: yellow;");
        }
      }
    });

    hand5.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (!handMana5.getText().equals("")){
          resetSelect();
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
          }
          tangan5.setStyle("-fx-background-color: yellow;");
        }
      }
    });

    root.getChildren().add(tangan1);
    root.getChildren().add(tangan2);
    root.getChildren().add(tangan3);
    root.getChildren().add(tangan4);
    root.getChildren().add(tangan5);

    summoned1A.setStyle("-fx-background-color: gray;");
    summoned1A.setPrefHeight(100.0);
    summoned1A.setPrefWidth(100.0);
    summoned1A.setLayoutX(225);
    summoned1A.setLayoutY(100);
    summoned1A.getChildren().add(img1A);

    summoned1B.setStyle("-fx-background-color: gray;");
    summoned1B.setPrefHeight(100.0);
    summoned1B.setPrefWidth(100.0);
    summoned1B.setLayoutX(325);
    summoned1B.setLayoutY(100);
    summoned1B.getChildren().add(img1B);

    summoned1C.setStyle("-fx-background-color: gray;");
    summoned1C.setPrefHeight(100.0);
    summoned1C.setPrefWidth(100.0);
    summoned1C.setLayoutX(225);
    summoned1C.setLayoutY(200);
    summoned1C.getChildren().add(img1C);

    summoned1D.setStyle("-fx-background-color: gray;");
    summoned1D.setPrefHeight(100.0);
    summoned1D.setPrefWidth(100.0);
    summoned1D.setLayoutX(325);
    summoned1D.setLayoutY(200);
    summoned1D.getChildren().add(img1D);

    summoned1E.setStyle("-fx-background-color: gray;");
    summoned1E.setPrefHeight(100.0);
    summoned1E.setPrefWidth(100.0);
    summoned1E.setLayoutX(425);
    summoned1E.setLayoutY(150);
    summoned1E.getChildren().add(img1E);

    summoned1A.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground1.ActiveCard.containsKey("A") && (board.getPhase() == TypePhase.PLANNING || board.getPhase() == TypePhase.ATTACK)){
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
            summoned1A.setStyle("-fx-background-color: yellow;");
          }
          else if (board.getPhase() == TypePhase.ATTACK){
            // resetSelectBG1();
            resetATKBGColor();
            if (!board.Battleground1.checkAlreadyAttacked("A")){
              summoned1A.setStyle("-fx-background-color: yellow;");
            }
          }
        }
      }
    });

    summoned1B.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground1.ActiveCard.containsKey("B") && (board.getPhase() == TypePhase.PLANNING || board.getPhase() == TypePhase.ATTACK)){
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
            summoned1B.setStyle("-fx-background-color: yellow;");
          }
          else if (board.getPhase() == TypePhase.ATTACK){
            // resetSelectBG1();
            resetATKBGColor();
            if (!board.Battleground1.checkAlreadyAttacked("B")){
              summoned1B.setStyle("-fx-background-color: yellow;");
            }
          }
        }
      }
    });

    summoned1C.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground1.ActiveCard.containsKey("C") && (board.getPhase() == TypePhase.PLANNING || board.getPhase() == TypePhase.ATTACK)){
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
            summoned1C.setStyle("-fx-background-color: yellow;");
          }
          else if (board.getPhase() == TypePhase.ATTACK){
            //resetSelectBG1();
            resetATKBGColor();
            if (!board.Battleground1.checkAlreadyAttacked("C")){
              summoned1C.setStyle("-fx-background-color: yellow;");
            }
          }
        }
      }
    });

    summoned1D.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground1.ActiveCard.containsKey("D") && (board.getPhase() == TypePhase.PLANNING || board.getPhase() == TypePhase.ATTACK)){
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
            summoned1D.setStyle("-fx-background-color: yellow;");
          }
          else if (board.getPhase() == TypePhase.ATTACK){
            //resetSelectBG1();
            resetATKBGColor();
            if (!board.Battleground1.checkAlreadyAttacked("D")){
              summoned1D.setStyle("-fx-background-color: yellow;");
            }
          }
        }
      }
    });

    summoned1E.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground1.ActiveCard.containsKey("E") && (board.getPhase() == TypePhase.PLANNING || board.getPhase() == TypePhase.ATTACK)){
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
            summoned1E.setStyle("-fx-background-color: yellow;");
          }
          else if (board.getPhase() == TypePhase.ATTACK){
            // resetSelectBG1();
            resetATKBGColor();
            if (!board.Battleground1.checkAlreadyAttacked("E")){
              summoned1E.setStyle("-fx-background-color: yellow;");
            }
          }
        }
      }
    });

    root.getChildren().add(summoned1A);
    root.getChildren().add(summoned1B);
    root.getChildren().add(summoned1C);
    root.getChildren().add(summoned1D);
    root.getChildren().add(summoned1E);

    summoned2A.setStyle("-fx-background-color: gray;");
    summoned2A.setPrefHeight(100.0);
    summoned2A.setPrefWidth(100.0);
    summoned2A.setLayoutX(950);
    summoned2A.setLayoutY(100);
    summoned2A.getChildren().add(img2A);

    summoned2B.setStyle("-fx-background-color: gray;");
    summoned2B.setPrefHeight(100.0);
    summoned2B.setPrefWidth(100.0);
    summoned2B.setLayoutX(850);
    summoned2B.setLayoutY(100);
    summoned2B.getChildren().add(img2B);

    summoned2C.setStyle("-fx-background-color: gray;");
    summoned2C.setPrefHeight(100.0);
    summoned2C.setPrefWidth(100.0);
    summoned2C.setLayoutX(950);
    summoned2C.setLayoutY(200);
    summoned2C.getChildren().add(img2C);

    summoned2D.setStyle("-fx-background-color: gray;");
    summoned2D.setPrefHeight(100.0);
    summoned2D.setPrefWidth(100.0);
    summoned2D.setLayoutX(850);
    summoned2D.setLayoutY(200);
    summoned2D.getChildren().add(img2D);

    summoned2E.setStyle("-fx-background-color: gray;");
    summoned2E.setPrefHeight(100.0);
    summoned2E.setPrefWidth(100.0);
    summoned2E.setLayoutX(750);
    summoned2E.setLayoutY(150);
    summoned2E.getChildren().add(img2E);

    summoned2A.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground2.ActiveCard.containsKey("A") && (board.getPhase() == TypePhase.PLANNING || board.getPhase() == TypePhase.ATTACK)){
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
            summoned2A.setStyle("-fx-background-color: yellow;");
          }
          else if (board.getPhase() == TypePhase.ATTACK){
            resetATKBGColor();
            if (!board.Battleground2.checkAlreadyAttacked("A")){
              summoned2A.setStyle("-fx-background-color: yellow;");
            }
          }
        }
      }
    });

    summoned2B.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground2.ActiveCard.containsKey("B") && (board.getPhase() == TypePhase.PLANNING || board.getPhase() == TypePhase.ATTACK)){
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
            summoned2B.setStyle("-fx-background-color: yellow;");
          }
          else if (board.getPhase() == TypePhase.ATTACK){
            resetATKBGColor();
            if (!board.Battleground2.checkAlreadyAttacked("B")){
              summoned2B.setStyle("-fx-background-color: yellow;");
            }
          }
        }
      }
    });

    summoned2C.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground2.ActiveCard.containsKey("C") && (board.getPhase() == TypePhase.PLANNING || board.getPhase() == TypePhase.ATTACK)){
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
            summoned2C.setStyle("-fx-background-color: yellow;");
          }
          else if (board.getPhase() == TypePhase.ATTACK){
            resetATKBGColor();
            if (!board.Battleground2.checkAlreadyAttacked("C")){
              summoned2C.setStyle("-fx-background-color: yellow;");
            }
          }
        }
      }
    });

    summoned2D.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground2.ActiveCard.containsKey("D") && (board.getPhase() == TypePhase.PLANNING || board.getPhase() == TypePhase.ATTACK)){
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
            summoned2D.setStyle("-fx-background-color: yellow;");
          }
          else if (board.getPhase() == TypePhase.ATTACK){
            resetATKBGColor();
            if (!board.Battleground2.checkAlreadyAttacked("D")){
              summoned2D.setStyle("-fx-background-color: yellow;");
            }
          }
        }
      }
    });

    summoned2E.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (board.Battleground2.ActiveCard.containsKey("E") && (board.getPhase() == TypePhase.PLANNING || board.getPhase() == TypePhase.ATTACK)){
          if (board.getPhase() == TypePhase.PLANNING){
            resetSelectBG();
            summoned2E.setStyle("-fx-background-color: yellow;");
          }
          else if (board.getPhase() == TypePhase.ATTACK){
            resetATKBGColor();
            if (!board.Battleground2.checkAlreadyAttacked("E")){
              summoned2E.setStyle("-fx-background-color: yellow;");
            }
          }
        }
      }
    });

    root.getChildren().add(summoned2A);
    root.getChildren().add(summoned2B);
    root.getChildren().add(summoned2C);
    root.getChildren().add(summoned2D);
    root.getChildren().add(summoned2E);

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
        resetSelect();
        draw.setFill(Color.ORANGE);
        if (board.Turn1){
          ronde++;
          reduceSpellDuration();
          turn.setText(String.valueOf(ronde));
          startOfDraw(player1);
          if (player1.curDeckSize == 0){
            //player pertama kalah
            alert.setContentText("Player 2 is winner");
            alert.showAndWait();
          }
          else{
            drawCard = player1.deck.showTopThreeCards();
          }
        }
        else{
          startOfDraw(player2);
          if (player2.curDeckSize == 0){
            //player pertama kalah
            alert.setContentText("Player 1 is winner");
            alert.showAndWait();
          }
          else{
            drawCard = player2.deck.showTopThreeCards();
          }
        }
        isDrawed = false;
        end.setFill(Color.BLACK);
      } else if (board.getPhase() == TypePhase.PLANNING) { //PLAN
        card = DEFAULT_CARD;
        key = null;
        addExp.setVisible(true);
        deleteCard.setVisible(true);
        plan.setFill(Color.ORANGE);
        resetSelect();
        resetSelectBG();
        draw.setFill(Color.BLACK);
      } else if (board.getPhase() == TypePhase.ATTACK) { //ATTACK
        addExp.setVisible(false);
        deleteCard.setVisible(false);
        attack.setFill(Color.ORANGE);
        resetSelect();
        resetSelectBG();
        resetATKBGColor();
        plan.setFill(Color.BLACK);
      } else { //END
        end.setFill(Color.ORANGE);
        resetSelectBG();
        resetAlreadyAttack();
        resetOffenseDefense();
        attack.setFill(Color.BLACK);
      }
      turn.setText(Integer.toString(board.getRound()));      
    }
  }

  public static int getIdxFromKey(String key) {
    if (key == "A") {
      return 1;
    } else if (key == "B") {
      return 2;
    } else if (key == "C") {
      return 3;
    } else if (key == "D") {
      return 4;
    } else if (key == "E") {
      return 5;
    }
    return 0;
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

  public static void setManaDraw(int idx_draw, String text) {    
    if (idx_draw == 1) {
      drawMana1.setText(text);
    } else if (idx_draw == 2) {
      drawMana2.setText(text);
    } else if (idx_draw == 3) {
      drawMana3.setText(text);
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
    } else {
      handDetail.setText("");
      handDesc.setText("");
    }
  }

  public static void updateDetailBattleground() {
    handDetail.setText(summon.c.Nama + "\nATK : " + summon.baseAtk + "\nHP : " + summon.baseHp + "\nLevel : " + summon.Level + "\nEXP : " + summon.Exp + "/" + summon.Exp_need + "\nType : " + summon.c.tipe.toString());
    handDesc.setText(summon.c.Deskripsi);
  }

  public static void clickOnDraw(int idx) {
    if (drawCard.size() > idx) {
      card = drawCard.get(idx);
      if (board.Turn1) {
        player1.hand.addCard(card);
        player1.curDeckSize -= 1;
      } else {
        player2.hand.addCard(card);
        player2.curDeckSize -= 1;
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

  public void resetGuiHand(Player player){
    int i = 0;
    while (i<5){
      if (i<player.hand.getSize()){
        image = new Image(getClass().getResourceAsStream(player.hand.getCard(i).ImagePath));
        setImageHand(i+1);
        if (player.hand.getCard(i).ID == 401 || player.hand.getCard(i).ID == 402){
          setManaHand(i+1, "Mana???");
        }
        else{
          setManaHand(i+1, "Mana " + Integer.toString(player.hand.getCard(i).Mana));
        }
      }
      else{
        image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
        setImageHand(i+1);
        setManaHand(i+1,"");
      }
      i++;
    }
  }

  public void getDrawGUI(Player player){
    int i = 0;
    while (i < 3){
      if (i < player.curDeckSize){
        image = new Image(getClass().getResourceAsStream(player.deck.getCard(i).ImagePath));
        setImageDraw(i+1);
        setManaDraw(i+1, "Mana " + Integer.toString(player.deck.getCard(i).Mana));
      }
      else{
        image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
        setImageDraw(i+1);
        setManaDraw(i+1, "");
      }
      i++;
    }
    drawText.setText("PICK A CARD");
  }

  public void setHover(Player player){
    hand1.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (player.hand.getSize() > 0) {
          card = player.hand.getCard(0);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });

    hand2.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (player.hand.getSize() > 1) {
          card = player.hand.getCard(1);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });


    hand3.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (player.hand.getSize() > 2) {
          card = player.hand.getCard(2);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });

    hand4.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (player.hand.getSize() > 3) {
          card = player.hand.getCard(3);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });

    hand5.setOnMouseEntered(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        if (player.hand.getSize() > 4) {
          card = player.hand.getCard(4);
          image = new Image(getClass().getResourceAsStream(card.ImagePath));
          handHover.setImage(image);
          updateDetailHand();
        }
      }
    });
  }

  public void startOfDraw(Player player){
    player.increaseMana();
    player.resetMana();
    curMana.setText(String.valueOf(player.getCurMana()));
    maxMana.setText("/"+String.valueOf(player.getMaxMana()));
    currentPlayer.setText(player.getName());
    resetGuiHand(player);
    getDrawGUI(player);
    setHover(player);
    curDeck.setText(String.valueOf(player.curDeckSize));
    maxDeck.setText("/" + String.valueOf(player.deckSize));
  }

    
  public void clickDraw(Player player, Integer pos){
    resetGuiHand(player);
    player.deck.returnCard(pos);
    curDeck.setText(String.valueOf(player.curDeckSize));
    player.deck.shuffleCards();
  }

  public void resetSelectBG(){
    summoned1A.setStyle("-fx-background-color: gray;");
    summoned1B.setStyle("-fx-background-color: gray;");
    summoned1C.setStyle("-fx-background-color: gray;");
    summoned1D.setStyle("-fx-background-color: gray;");
    summoned1E.setStyle("-fx-background-color: gray;");
    summoned2A.setStyle("-fx-background-color: gray;");
    summoned2B.setStyle("-fx-background-color: gray;");
    summoned2C.setStyle("-fx-background-color: gray;");
    summoned2D.setStyle("-fx-background-color: gray;");
    summoned2E.setStyle("-fx-background-color: gray;");
  }

  public void resetSelectBG1(){
    summoned1A.setStyle("-fx-background-color: gray;");
    summoned1B.setStyle("-fx-background-color: gray;");
    summoned1C.setStyle("-fx-background-color: gray;");
    summoned1D.setStyle("-fx-background-color: gray;");
    summoned1E.setStyle("-fx-background-color: gray;");
  }

  public void resetSelectBG2(){
    summoned2A.setStyle("-fx-background-color: gray;");
    summoned2B.setStyle("-fx-background-color: gray;");
    summoned2C.setStyle("-fx-background-color: gray;");
    summoned2D.setStyle("-fx-background-color: gray;");
    summoned2E.setStyle("-fx-background-color: gray;"); 
  }

  public void resetATKBGColor(){
    for (String key : board.Battleground1.ActiveCard.keySet()) {
      if (!board.Battleground1.checkAlreadyAttacked(key)){
        if (key == "A"){
          summoned1A.setStyle("-fx-background-color: gray;");
        } else if (key == "B"){
          summoned1B.setStyle("-fx-background-color: gray;");
        } else if (key == "C"){
          summoned1C.setStyle("-fx-background-color: gray;");
        } else if (key == "D"){
          summoned1D.setStyle("-fx-background-color: gray;");
        } else if (key == "E"){
          summoned1E.setStyle("-fx-background-color: gray;");
        }
      } else {
        if (key == "A"){
          summoned1A.setStyle("-fx-background-color: red;");
        } else if (key == "B"){
          summoned1B.setStyle("-fx-background-color: red;");
        } else if (key == "C"){
          summoned1C.setStyle("-fx-background-color: red;");
        } else if (key == "D"){
          summoned1D.setStyle("-fx-background-color: red;");
        } else if (key == "E"){
          summoned1E.setStyle("-fx-background-color: red;");
        }
      }
    }
    for (String key : board.Battleground2.ActiveCard.keySet()) {
      if (!board.Battleground2.checkAlreadyAttacked(key)){
        if (key == "A"){
          summoned2A.setStyle("-fx-background-color: gray;");
        } else if (key == "B"){
          summoned2B.setStyle("-fx-background-color: gray;");
        } else if (key == "C"){
          summoned2C.setStyle("-fx-background-color: gray;");
        } else if (key == "D"){
          summoned2D.setStyle("-fx-background-color: gray;");
        } else if (key == "E"){
          summoned2E.setStyle("-fx-background-color: gray;");
        }
      } else {
        if (key == "A"){
          summoned2A.setStyle("-fx-background-color: red;");
        } else if (key == "B"){
          summoned2B.setStyle("-fx-background-color: red;");
        } else if (key == "C"){
          summoned2C.setStyle("-fx-background-color: red;");
        } else if (key == "D"){
          summoned2D.setStyle("-fx-background-color: red;");
        } else if (key == "E"){
          summoned2E.setStyle("-fx-background-color: red;");
        }
      }
    }
  }

  public void resetAlreadyAttack(){
    for (String key : board.Battleground1.ActiveCard.keySet()) {
      board.Battleground1.setAlreadyAttack(key, false);
    }
    for (String key : board.Battleground2.ActiveCard.keySet()) {
      board.Battleground2.setAlreadyAttack(key, false);
    }
  }

  public void resetOffenseDefense(){
    offense = null;
    defense = null;
  }

  public void resetGUIBattleGroundOnChar(){
    if (board.Battleground1.ActiveCard.containsKey("A")){
      image = new Image(getClass().getResourceAsStream(board.Battleground1.getImagePath("A")));
      setImageBattleground(1);
    } else {
      image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
      setImageBattleground(1);
    }
    if (board.Battleground1.ActiveCard.containsKey("B")){
      image = new Image(getClass().getResourceAsStream(board.Battleground1.getImagePath("B")));
      setImageBattleground(2);
    } else {
      image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
      setImageBattleground(2);
    }
    if (board.Battleground1.ActiveCard.containsKey("C")){
      image = new Image(getClass().getResourceAsStream(board.Battleground1.getImagePath("C")));
      setImageBattleground(3);
    } else {
      image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
      setImageBattleground(3);
    }
    if (board.Battleground1.ActiveCard.containsKey("D")){
      image = new Image(getClass().getResourceAsStream(board.Battleground1.getImagePath("D")));
      setImageBattleground(4);
    } else {
      image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
      setImageBattleground(4);
    }
    if (board.Battleground1.ActiveCard.containsKey("E")){
      image = new Image(getClass().getResourceAsStream(board.Battleground1.getImagePath("E")));
      setImageBattleground(5);
    } else {
      image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
      setImageBattleground(5);
    }

    if (board.Battleground2.ActiveCard.containsKey("A")){
      image = new Image(getClass().getResourceAsStream(board.Battleground2.getImagePath("A")));
      setImageBattleground(6);
    } else {
      image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
      setImageBattleground(6);
    }
    if (board.Battleground2.ActiveCard.containsKey("B")){
      image = new Image(getClass().getResourceAsStream(board.Battleground2.getImagePath("B")));
      setImageBattleground(7);
    } else {
      image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
      setImageBattleground(7);
    }
    if (board.Battleground2.ActiveCard.containsKey("C")){
      image = new Image(getClass().getResourceAsStream(board.Battleground2.getImagePath("C")));
      setImageBattleground(8);
    } else {
      image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
      setImageBattleground(8);
    }
    if (board.Battleground2.ActiveCard.containsKey("D")){
      image = new Image(getClass().getResourceAsStream(board.Battleground2.getImagePath("D")));
      setImageBattleground(9);
    } else {
      image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
      setImageBattleground(9);
    }
    if (board.Battleground2.ActiveCard.containsKey("E")){
      image = new Image(getClass().getResourceAsStream(board.Battleground2.getImagePath("E")));
      setImageBattleground(10);
    } else {
      image = new Image(getClass().getResourceAsStream(DEFAULT_IMG_PATH));
      setImageBattleground(10);
    }
  }

  public void reduceSpellDuration(){
    for (String key : board.Battleground1.ActiveCard.keySet()){
      board.Battleground1.getChar(key).DecreaseSpellDuration();
    }
    for (String key : board.Battleground2.ActiveCard.keySet()){
      board.Battleground2.getChar(key).DecreaseSpellDuration();
    }
  }
}
