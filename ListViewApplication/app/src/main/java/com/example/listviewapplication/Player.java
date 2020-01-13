package com.example.listviewapplication;

import java.util.Objects;

public class Player {
    String player;

    public Player(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player1 = (Player) o;
        return ((Player) o).getPlayer().equals( getPlayer() );
    }

}
