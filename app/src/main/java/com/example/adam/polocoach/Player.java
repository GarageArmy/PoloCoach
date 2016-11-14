package com.example.adam.polocoach;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by Adam on 2016. 11. 13..
 */
public class Player implements Serializable {
    String name;
    Stack<Integer> shootPosX;
    Stack<Integer> shootPosY;


    Player(String name){
        this.name = name;
        shootPosX = new Stack<>();
        shootPosY = new Stack<>();
    }

    public void shoot(int x, int y){
        shootPosX.push(x);
        shootPosY.push(y);
    }
}
