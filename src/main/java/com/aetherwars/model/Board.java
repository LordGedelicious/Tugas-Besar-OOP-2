package com.aetherwars.model;

public class Board {
    private int Round;
    public Player Player1;
    public Player Player2;
    private Battleground Battleground1;
    private Battleground Battleground2;
    public boolean Turn1;
    public boolean Turn2;
    private TypePhase Phase;

    public Board(Player player1, Player player2) {
        this.Round = 1;
        this.Player1 = player1;
        this.Player2 = player2;
        this.Battleground1 = new Battleground();
        this.Battleground2 = new Battleground();
        this.Turn1 = true;
        this.Turn2 = false;
        this.Phase = TypePhase.DRAW;
    }

    public int getRound() {
        return this.Round;
    }

    public TypePhase getPhase() {
        return this.Phase;
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
        return Battleground1.isFull();
    }

    public boolean isPlayer2Full() {
        return Battleground2.isFull();
    }

    public void addCardPlayer1(SummonedChar c) {
        Battleground1.addCard(c);
    }

    public void addCardPlayer2(SummonedChar c) {
        Battleground2.addCard(c);
    }

    public void removeCardPlayer1(int i) {
        Battleground1.removeChar(i);
    }

    public void removeCardPlayer2(int i) {
        Battleground2.removeChar(i);
    }
}