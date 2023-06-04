package BrickBreaker_Game;

import java.awt.*;


class BrickGenerator {
     public int Brick[][];
    public int bricksWidth;
    public int bricksHeight;
    public BrickGenerator(int row , int col){
        Brick = new int[row][col];
         for (int[] Brick1 : Brick) {
             for (int j = 0; j < Brick[0].length; j++) {
                 Brick1[j] = 1;
             }
         }
        bricksWidth = 650/col;
        bricksHeight = 270/row;
    }
    public void draw(Graphics2D g) {
        for (int i = 0; i < Brick.length; i++) {
            for (int j = 0; j < Brick[0].length; j++) {
                if (Brick[i][j] > 0) {
                    
                    if(((i+j)%3)*3==0){
                        g.setColor(Color.white);
                    g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);
                    }
                    else if(((i+j)%2)*2==0){
                        g.setColor(Color.blue);
                    g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);
                    }
                    else{
                        g.setColor(Color.red);
                    g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);
                    }
                 

                    g.setColor(Color.black);
                    g.drawRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);

                }
            }

        }
    }
    public void setBricksValue(int value,int row,int col)
    {
        Brick[row][col] = value;

    }
    
}
