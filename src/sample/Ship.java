package sample;

import java.util.Random;

public class Ship {
    private int id;
    private int x;
    private int y;
    private boolean direction;
    private int length;
    private boolean alive;
    private int hp;

    public void createShip(int i, int l){
        id = i;
        Random r = new Random();
        //true - horizontal
        //false - vertical
        direction = r.nextBoolean();
        length = l;
        hp = l;
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

    public void hit(){
        --hp;
        if(hp==0){
            alive = false;
        }
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
