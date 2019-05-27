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

    //for manual ship placement
    public void makeShip(int i, int l, boolean d, int x2, int y2){
        id = i;
        //true - horizontal
        //false - vertical
        direction = d;
        length = l;
        hp = l;
        alive = true;
        x=x2;
        y=y2;
    }

    //for random ship placement
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

    //for random ship placement
    public void reroll(){
        Random r = new Random();
        if(direction==true){
            x = r.nextInt(10-length);
            y = r.nextInt(10);
        }
        else{
            x = r.nextInt(10);
            y = r.nextInt(10-length);
        }
    }
}
