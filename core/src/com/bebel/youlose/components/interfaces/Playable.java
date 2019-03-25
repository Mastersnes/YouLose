package com.bebel.youlose.components.interfaces;

/**
 * Represente une entitée exposant des conditions de victoires ou de perte
 */
public interface Playable {
    boolean lose();
    boolean win();
}