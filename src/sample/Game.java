package sample;

public class Game {
    private Sea mySea;

    public Game(){
        mySea = new Sea();
    }

    public Ship getShipInfo(int id){
        return mySea.getShipInfo(id);
    }

    public int getShot(int x, int y){
        return mySea.shoot(x, y);
    }







}
