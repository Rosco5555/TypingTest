import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    long start = 0;
    int correctKeysPressed = 0; 
    int wordsTyped = 0; //Also the index of the word we are on
    JTextField userText;
    JLabel wordLabel;
    JPanel panel;
    JFrame frame;
    String wordString;
    String origString;
    String typedSoFar = "";
    String wrongString = "";
    Font myFont = new Font("Futura", Font.PLAIN, 30);
    //The words we are asked to type

    public GUI(String[] words) {
        //Combining the words into a string
        wordString = String.join(" ", words)+ " ";
        origString = wordString;

        //Creating the frame and panel
        panel = new JPanel();
        frame = new JFrame();
        int HEIGHT = 720;
        int WIDTH = 1280;
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setBackground(Color.black);
        panel.setLayout(null);

        //Our text entry field
        userText = new JTextField(2);
        userText.setBounds((WIDTH/2)-165,HEIGHT-100,330,50);
        userText.setFont(myFont);
        userText.setForeground(Color.white);
        //Creating the label to display the words
        wordLabel = new JLabel(wordString);
        wordLabel.setFont(myFont);
        wordLabel.setBounds(100, HEIGHT/2,20000,60);
        wordLabel.setForeground(Color.white);
        panel.add(wordLabel);
        panel.add(userText);

        //Adding the key listener
        userText.addKeyListener(new KeyAdapter() {
            @Override 
            public void keyPressed(KeyEvent e) {   
                //Get the time at the very start
                if (wordsTyped == 0) {
                    start = System.currentTimeMillis();
                }

                String key = String.valueOf(e.getKeyChar()); //Key pressed
                typedSoFar = userText.getText().replaceFirst("^\\s*", "") + key; //Exactly what is currently in the input box, we ignore leading spaces
                //We want to check, after each keypress that what we have in the inputBox matches exactly
                if (typedSoFar.equals(wordString.substring(0,correctKeysPressed+1))){
                    //If we pressed space we've completed a word
                    if (key.equals(" ")) {
                        correctKeysPressed = 0;
                        wordsTyped++;
                        //Removing the word from the front of the word string
                        String temp = wordString;
                        wordString = temp.replace(typedSoFar,"");
                        //Displaying the new wordString
                        wordLabel.setText(wordString);
                        //Reseting the input box
                        userText.setText("");

                        //If at any point our word string is empty, the game is over
                        if (wordString.isBlank() ) {
                            //Calculate WPM
                            long elapsed = System.currentTimeMillis() - start;
                            long seconds = (long) (elapsed / 1000);
                            float minutes = (float) seconds / 60;
                            float wpm = (float) ((origString.length())/5) / (minutes);
                            //Close game
                            frame.dispose();
                            //Open menu
                            Finish finish = new Finish(wpm);
                        }
                    }
                    else {
                        userText.setBackground(Color.GREEN);
                        correctKeysPressed++;
                    }
                }
                //If we pressed the wrong key, make the box red
                else {
                    userText.setBackground(Color.RED);
                }
            }
        });
        //Show what we've done
        frame.setVisible(true);
    }

}