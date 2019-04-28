package sample;

public class Sea {
    private int[][] grid;
    private Ship[] ships;
    private int[] lenghts = {2, 3, 3, 4, 5};
    private int totalHp;

    public Sea() {
        totalHp = 2+3+3+4+5;
        ships = new Ship[5];
        grid = new int[10][10];
        for(int i=0; i<5; i++){
            ships[i] = new Ship();
            ships[i].createShip(i, lenghts[i]);
        }
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                //id=9 - empty field
                grid[i][j] = 9;
            }
        }
        //add checking collisions
        int[] tempShipX;
        int[] tempShipY;
        boolean ok=false;
        for(int i=0; i<5; i++){
            tempShipX = new int[ships[i].getLength()];
            tempShipY = new int[ships[i].getLength()];
            if(ships[i].getDirection()==true)
            {
                while(ok==false){
                    for(int j=0; j<ships[i].getLength(); j++){
                        tempShipX[j]=ships[i].getX()+j;
                        tempShipY[j]=ships[i].getY();
                    }
                    for(int j=0; j<ships[i].getLength(); j++){
                        if(grid[tempShipX[j]][tempShipY[j]]!=9){
                            ships[i].reroll();
                            break;
                        }
                        if(j==ships[i].getLength()-1){
                            ok = true;
                        }
                    }

                }
                for(int j=0; j<ships[i].getLength(); j++){
                    grid[ships[i].getX()+j][ships[i].getY()] = ships[i].getId();
                }
            }
            else{
                while(ok==false){
                    for(int j=0; j<ships[i].getLength(); j++){
                        tempShipX[j]=ships[i].getX();
                        tempShipY[j]=ships[i].getY()+j;
                    }
                    for(int j=0; j<ships[i].getLength(); j++){
                        if(grid[tempShipX[j]][tempShipY[j]]!=9){
                            ships[i].reroll();
                            break;
                        }
                        if(j==ships[i].getLength()-1){
                            ok = true;
                        }
                    }

                }
                for(int j=0; j<ships[i].getLength(); j++){
                    grid[ships[i].getX()][ships[i].getY()+j] = ships[i].getId();
                }
            }
            ok = false;
        }
    }

    public int checkHp(){
        return totalHp;
    }

    public Ship getShipInfo(int id){
        return ships[id];
    }

    //0 - miss, 1 - hit, 2 - defeat
    //3 - sink <can be added later>
    public int shoot(int x, int y){
        if(grid[x][y]==9){
            return 0;
        }
        else{
            ships[grid[x][y]].hit();
            --totalHp;
            if(ships[grid[x][y]].isAlive()==false)
            {
                //return 3
            }
            if(totalHp==0){
                return 2;
            }
            return 1;
        }
    }
}
