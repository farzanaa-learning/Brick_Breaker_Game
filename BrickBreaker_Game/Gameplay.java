package BrickBreaker_Game;


import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class GamePlay extends JPanel implements KeyListener , ActionListener {
    
    private boolean play = false;
    private int score = 0;
    private int totalbricks = 81;
    private Timer Timer;
    private int delay = 6;
    
    //ball bauncer position
    private int playerX = 210;
    
    //ball position
    private int ballposX = 70;
    private int ballposY = 350;
    
    //describe ball position 
    private int ballXdir = -2;
    private int ballYdir = -2;
    private BrickGenerator Brick;

    public GamePlay() {
        Brick = new BrickGenerator(9, 9);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer = new Timer(delay, this);
        Timer.start();
    }
    
     public void paint(Graphics g) {
    	 //canvas
        g.setColor(Color.black);
        g.fillRect(1, 1, 850, 600);

        Brick.draw((Graphics2D) g);

        //borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 600);
        g.fillRect(0, 0, 850, 3);
        g.fillRect(850, 3, 3, 600);

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("" + score, 800, 40);

        //ball bauncer
        g.setColor(Color.white);
        g.fillRect(playerX, 550, 120, 10);

        //ball
        g.setColor(Color.GREEN);
        g.fillOval(ballposX, ballposY, 20, 20);

        
        //case when ball touches botttm boundary
        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("    Game Over Score: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 190, 340);
        }
        
        //case when you won
        if(totalbricks == 0){
            play = false;
            ballYdir = 0;
            ballXdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    Hey You Won : With Score "+score,190,300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 190, 340);
        }

        g.dispose();


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();

        if (play) {
            
            //ball baunce action when hit to slider
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 10))) {
                ballYdir = -ballYdir;
            }

            //A label to break from outer loop
            A:for (int i = 0; i < Brick.Brick.length; i++) {
                for (int j = 0; j < Brick.Brick[0].length; j++) {
                    if (Brick.Brick[i][j] > 0) {
                        int brickX = j * Brick.bricksWidth + 80;
                        int brickY = i * Brick.bricksHeight + 50;
                        int bricksWidth =Brick.bricksWidth;
                        int bricksHeight = Brick.bricksHeight;
                        
                        
                        //ball baunce action when hit to bricks
                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) {
                            Brick.setBricksValue(0, i, j);
                            totalbricks--;
                            score += 5;
                            if (ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }


                }
            }


            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 830) {
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 730) {
                playerX = 730;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalbricks = 81;
                Brick = new BrickGenerator(9, 9);

                repaint();
            }
        }


        }

        public void moveRight ()
        {
            play = true;
            playerX += 20;
        }
        public void moveLeft ()
        {
            play = true;
            playerX -= 20;
        }
        
    
   
}

