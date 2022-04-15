package com.aetherwars.model;
import java.util.List;
import java.util.ArrayList;

public class Board implements IHand {
    private int Round;
    private Player Player1;
    private Player Player2;
    private List<Card> Battleground1;
    private List<Card> Battleground2;
    private boolean Turn1;
    private boolean Turn2;
    private TypePhase Phase;

    Board(Player player1, Player player2) {
        this.Round = 1;
        this.Player1 = player1;
        this.Player2 = player2;
        this.Battleground1 = new ArrayList<Card>();
        this.Battleground2 = new ArrayList<Card>();
        this.Turn1 = true;
        this.Turn2 = false;
        this.Phase = TypePhase.DRAW;
    }

    public void nextPhase() {
        if (this.Phase == TypePhase.DRAW) {
            this.Phase = TypePhase.PLANNING;
        } else if (this.Phase == TypePhase.PLANNING) {
            this.Phase = TypePhase.ATTACK;
        } else if (this.Phase == TypePhase.ATTACK) {
            this.Phase = TypePhase.END;
        } else {
            if (this.Turn2) {
                this.Round += 1;
            }
            this.Turn1 = !this.Turn1;
            this.Turn2 = !this.Turn2;
            this.Phase = TypePhase.DRAW;
        }
    }

    public boolean isPlayer1Full() {
        return (this.Battleground1.size() == 5);
    }

    public boolean isPlayer2Full() {
        return (this.Battleground2.size() == 5);
    }

    public void addCardPlayer1(Card c) {
        this.Battleground1.add(c);
    }

    public void addCardPlayer2(Card c) {
        this.Battleground2.add(c);
    }

    public void removeCardPlayer1(int i) {
        this.Battleground1.remove(i);
    }

    public void removeCardPlayer2(int i) {
        this.Battleground2.remove(i);
    }
}