package sample;

import java.util.Scanner;

public class Sea {
    private int[][] grid;
    private Ship[] ships;
    private int[] lenghts = {2, 3, 3, 4, 5};
    private int totalHp;

    public Sea() {
        totalHp = 2+3+3+4+5;
        ships = new Ship[5];
        grid = new int[10][10];
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<5; i++){
            ships[i] = new Ship();
            //for random ship placement
            //ships[i].createShip(i, lenghts[i]);

            //for manual ship placement
            int x=0;
            int y=0;
            boolean d=true;
            int d2;
            boolean good=false;
            while (good==false) {
                System.out.println("Give direction of ship "+i+" (0-vertical / 1-horizontal)");
                d2 = scanner.nextInt();
                if(d2==1) {
                    d=true;
                    System.out.println("Give X of ship " + i);
                    x = scanner.nextInt();
                    if(x>10-lenghts[i]){
                        System.out.println("Wrong X");
                        continue;
                    }
                    System.out.println("Give Y of ship " + i);
                    y = scanner.nextInt();
                    if(y>=10){
                        System.out.println("Wrong Y");
                        continue;
                    }
                    good=true;
                }
                else if(d2==0){
                    d=false;
                    System.out.println("Give X of ship " + i);
                    x = scanner.nextInt();
                    if(x>=10){
                        System.out.println("Wrong X");
                        continue;
                    }
                    System.out.println("Give Y of ship " + i);
                    y = scanner.nextInt();
                    if(y>10-lenghts[i]){
                        System.out.println("Wrong Y");
                        continue;
                    }
                    good=true;
                }
                else {
                    System.out.println("Wrong direction");
                }
            }
            ships[i].makeShip(i, lenghts[i], d, x, y);

        }
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                //id=9 - empty field
                grid[i][j] = 9;
            }
        }
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
                            //for random ship placement
                            //ships[i].reroll();

                            //for manual ship placement
                            System.out.println("Collision! Give new spawn for ship "+i);
                            int x=0;
                            int y=0;
                            boolean d=true;
                            int d2;
                            boolean good=false;
                            while (good==false) {
                                System.out.println("Give direction of ship "+i+" (0-vertical / 1-horizontal)");
                                d2 = scanner.nextInt();
                                if(d2==1) {
                                    d=true;
                                    System.out.println("Give X of ship " + i);
                                    x = scanner.nextInt();
                                    if(x>10-lenghts[i]){
                                        System.out.println("Wrong X");
                                        continue;
                                    }
                                    System.out.println("Give Y of ship " + i);
                                    y = scanner.nextInt();
                                    if(y>=10){
                                        System.out.println("Wrong Y");
                                        continue;
                                    }
                                    good=true;
                                }
                                else if(d2==0){
                                    d=false;
                                    System.out.println("Give X of ship " + i);
                                    x = scanner.nextInt();
                                    if(x>=10){
                                        System.out.println("Wrong X");
                                        continue;
                                    }
                                    System.out.println("Give Y of ship " + i);
                                    y = scanner.nextInt();
                                    if(y>10-lenghts[i]){
                                        System.out.println("Wrong Y");
                                        continue;
                                    }
                                    good=true;
                                }
                                else {
                                    System.out.println("Wrong direction");
                                }
                            }
                            ships[i].makeShip(i, lenghts[i], d, x, y);

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
                            //for random ship placement
                            //ships[i].reroll();

                            //for manual ship placement
                            int x=0;
                            int y=0;
                            boolean d=true;
                            int d2;
                            boolean good=false;
                            while (good==false) {
                                System.out.println("Give direction of ship "+i+" (0-vertical / 1-horizontal)");
                                d2 = scanner.nextInt();
                                if(d2==1) {
                                    d=true;
                                    System.out.println("Give X of ship " + i);
                                    x = scanner.nextInt();
                                    if(x>10-lenghts[i]){
                                        System.out.println("Wrong X");
                                        continue;
                                    }
                                    System.out.println("Give Y of ship " + i);
                                    y = scanner.nextInt();
                                    if(y>=10){
                                        System.out.println("Wrong Y");
                                        continue;
                                    }
                                    good=true;
                                }
                                else if(d2==0){
                                    d=false;
                                    System.out.println("Give X of ship " + i);
                                    x = scanner.nextInt();
                                    if(x>=10){
                                        System.out.println("Wrong X");
                                        continue;
                                    }
                                    System.out.println("Give Y of ship " + i);
                                    y = scanner.nextInt();
                                    if(y>10-lenghts[i]){
                                        System.out.println("Wrong Y");
                                        continue;
                                    }
                                    good=true;
                                }
                                else {
                                    System.out.println("Wrong direction");
                                }
                            }
                            ships[i].makeShip(i, lenghts[i], d, x, y);

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
