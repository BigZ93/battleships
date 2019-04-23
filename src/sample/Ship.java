package sample;

import java.util.Random;

public class Ship {
    int id;
    int x;
    int y;
    boolean direction;
    int length;
    boolean alive;

    public void createShip(int i, int l){
        id = i;
        Random r = new Random();
        //true - horizontal
        //false - vertical
        direction = r.nextBoolean();
        length = l;
        alive = true;
        if(direction==true){
            x = r.nextInt(10-length);
            y = r.nextInt(10);
        }
        else{
            x = r.nextInt(10);
            y = r.nextInt(10-length);
        }
    }

    public void sink(){
        alive = false;
    }

    public boolean isAlive(){
        return alive;
    }

    public int getId(){
        return id;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getLength(){
        return length;
    }

    public boolean getDirection(){
        return direction;
    }
}
