package project2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author cathal aherne
 * Project start date 1/4/2017
 * compiler is Eclipse
 * this is the assignment that we were given to complete in Object Orientated 
 * programming, second semester, we were given a choice of creating one of six
 * existing briefs, but also could could instead create our own.
 * the project i chose to do was the abuse text analyzer. this project
 * opens 1 file containing swear words, and another file that is the file to be analysed,
 * and checks both for the same swear words. i used the inbuilt GUI builder in intellij
 * as i started to create my project on this, but i had too many problems so i swapped back to 
 * eclipse.
 * the algorithm used in this project is N^2 but i am sure that if i put more htought into it, this could be refined.
 */
public class GUI {

    //These are the variables used by the GUI when creating the project

	
	private JTextArea textArea1;
	private JTextField textField1;
	private JTextField textField2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    public JPanel panelMain;

    //these are the variables used in the creating of the filewriter

    private FileWriter fWriter = null;
    private BufferedWriter writer = null;
    private Scanner scan = null;
    private Scanner scan2 = null;
    private String str = null;
    private String str2 = null;
    int count =0;
    private Scanner scancheck = null;
    private String upstr2 =null;

    //at the start of the project, all of the variables used in the project start at 0.

    int shouting = 0;
    int swearing = 0;
    int wordcount = 0;
    int totalabuse = 0;
    float percentage = 0;

    //Constructor

    public GUI() {

        //Action listener for the button that reads new abusive words into the file

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Used to read in the new abusive word from the textfield into the abusive words file

                JTextField textField = textField1; //
                String text = textField.getText();

                File filecheck = new File("C:\\Users\\outla\\workspace\\Filetest\\cathal.txt");

                try {
                    scancheck = new Scanner(filecheck);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                //Initialisation of the arraylist checkdict

                ArrayList<String> checkdict = new ArrayList<>();

                while(scancheck.hasNextLine())
                {
                    str = scancheck.nextLine();
                    checkdict.add(str);
                }

                for (String str: checkdict) {
                    if (text.contains(str)) {
                        count++;
                    }
                }

                //Try catch to read the abuse text file into the variable abuse

                if(count == 0) {

                    try {

                        //Reads in the file of abusive words and appends the new
                        //word the user wishes to add to the end of the file

                        fWriter = new FileWriter("", true);
                        writer = new BufferedWriter(fWriter);
                        writer.write(String.valueOf(text));
                        writer.newLine();
                        writer.close();
                        String saved = ("Your input of " + text.length() + " characters was saved.");
                        JOptionPane.showMessageDialog(null, saved);
                    } catch (Exception c) {
                        System.out.println("Error!");
                    }

                }

                //This else statement tells the user when the word they are
                //trying to add is already in the file.

                else
                {
                    JOptionPane.showMessageDialog(null,"Word already added");
                    count=0;
                }
            }
        });

        //Action listener for the second button to check the file for abusive language

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                shouting = 0;
                swearing = 0;
                wordcount = 0;
                totalabuse = 0;
                percentage = 0;

                //this is where the user selects which file they want analysed for abusive words

                JTextField textField = textField2;
                String text2 = textField.getText();


                File file = new File("C:\\Users\\outla\\workspace\\Filetest\\");

                //If I do not allow the user to name the file, with an auto extension added, they will find they get errors a lot,
                //so for that sake its easier to add them by default

                File file2 = new File("C:\\Users\\outla\\workspace\\Filetest\\" + text2 + ".txt");

                //the first scanner reads the abusive text file

                try {
                    scan = new Scanner(file);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

              
                //this scanner reads the file to be checked for abusive words
                try {
                    scan2 = new Scanner(file2);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                //This array will have all the words to check against the text file
                //this is the array which all of the words are stored in, and then compared to the words in the swear file.
                ArrayList<String> abusive_dictionary = new ArrayList<>();


                //this while loop reads the abusive text files into array an array named abusive_dictionary.
                while(scan.hasNextLine())
                {
                    str = scan.nextLine();
                    abusive_dictionary.add(str);
                }
                
                
                //this while loop scans the files that are to be checked for abusive words.
                while (scan2.hasNextLine())
                {
                    str2 = scan2.nextLine();

                    //this for loop splits all words in the file into seperate words

                    for (String s2: str2.split(" ")) {


                        wordcount++;

                   
                        //this nested structure counts the number of each abusive word in the file
                        for (String str : abusive_dictionary) {

                            //set variable upstr2 to all caps and compare
                            //to search for shouting

                            upstr2 = s2.toUpperCase();
                            if(upstr2.equals(s2))
                            {
                                shouting++;
                            }

                            //Set str and 2s2 to lowercase to check for swear words

                            str = str.toLowerCase();
                            s2 = s2.toLowerCase();
                            if (s2.equals(str)) {
                                swearing++;
                            }
                        }
                    }
                }

                //Message dialogue boxes to give the user feedback

                String output = (swearing + " Swear words appeared");
                JOptionPane.showMessageDialog(null, output);


                String caps = (shouting + " Words shouted ");
                JOptionPane.showMessageDialog(null, caps);

                totalabuse = swearing + shouting;

                String totwords = ("Total abusive words(including shouting): " + totalabuse);
                JOptionPane.showMessageDialog(null, totwords);

                percentage = 100/wordcount*totalabuse;

                String end = ("Total percentage of abuse: " + percentage);
                JOptionPane.showMessageDialog(null, end);
            }
        });

        //this action listener displays the contents of the abuse dictionary

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Set the buffered reader abuse to null

                BufferedReader abuse  = null;

                //this try catch was to read the abusive text into the variable abuse
                
                try {
                    abuse = new BufferedReader(new FileReader("cathal.txt"));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                String line = null;

                //this try catch read the file line by line

                try {
                    line = abuse.readLine();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //this while loop prints the contents of the abusive file to the screen so that the user can see.
                while(line != null){
                    textArea1.append(line + "\n");
                    try {
                        line = abuse.readLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
