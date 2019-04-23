package sample;

public class Sea {
    int[][] grid;
    Ship[] ships;
    int[] lenghts = {2, 3, 3, 4, 5};

    public Sea() {
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
        for(int i=0; i<5; i++){
            if(ships[i].getDirection()==true)
            {
                for(int j=0; j<ships[i].getLength(); j++){
                    grid[ships[i].getX()+j][ships[i].getY()] = ships[i].getId();
                }
            }
            else{
                for(int j=0; j<ships[i].getLength(); j++){
                    grid[ships[i].getX()][ships[i].getY()+j] = ships[i].getId();
                }
            }
        }
    }

}
