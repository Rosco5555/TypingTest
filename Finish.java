import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;  

public class Finish {
    //Has a start button that creates an instance of gui
    JPanel panel;
    JFrame frame;
    JLabel label;
    JButton startButton;
    Font myFont = new Font("Futura", Font.PLAIN, 30);

    public Finish(float wpm) {
        panel = new JPanel();
        frame = new JFrame();
        int HEIGHT = 720;
        int WIDTH = 1280;
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setBackground(Color.black);
        panel.setLayout(null);
        //Label to display wpm
        label = new JLabel("You type at: " + Math.round(wpm) + "wpm");
        label.setFont(myFont);
        label.setBounds(WIDTH/2-170, (HEIGHT/2)- 100,1280,50);
        label.setForeground(Color.white);
        panel.add(label);
        //Start game button
        startButton = new JButton("PLAY AGAIN");
        startButton.setBackground(Color.red);
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setFont(myFont);
        startButton.setBounds(WIDTH/2-200, HEIGHT/2 , 400, 150);
        startButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //Close current menu 
                        frame.dispose();
                        //Open game
                        GUI gui = new GUI(TypingGame.makeWords(30));
                    }  
                });
        panel.add(startButton);
        frame.setVisible(true);  
    }     
}