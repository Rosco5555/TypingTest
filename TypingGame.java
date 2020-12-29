import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TypingGame {
    public static String[] makeWords(int n) {
        int count = 0;
        String[] words = new String[n];
        try {
            Scanner sc = new Scanner(new File("words.txt"));
            ArrayList<String> lines = new ArrayList<String>();
        
            while (sc.hasNextLine() && count < 5000) {
                lines.add(sc.nextLine());
                count++;
            }
            String[] arr = lines.toArray(new String[0]);
            //Randomly selecting 50 words
            int i = 0;
            while (i < n) {
                int rnd = new Random().nextInt(5000);
                if (arr[rnd].length() > 4 ) {
                    words[i] = arr[rnd];
                    i++;
                }
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return words;
    } 

    public static void main(String[] args) {
        GUI gui = new GUI(TypingGame.makeWords(30));
    }
}   
