package BrickBreaker_Game;


import javax.swing.*;

class 	Mainclass {

	 public static void main(String[] args) {
	        JFrame obj = new JFrame();
	        BrickBreaker_Game.GamePlay gameplay = new GamePlay();
	        obj.setBounds(50,50,900,600);
	        obj.setTitle("BrickBreaker");
	        obj.setResizable(false);
	        obj.setVisible(true);
	        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        obj.add(gameplay);
                JOptionPane.showMessageDialog(obj, " WELCOM TO BRICK BREAKER GAME ");
	    }
}
