import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Create a Word Game
 */
public class WordGame implements ActionListener {
  private JFrame frame;
  private WordPanel[] wordPanels;
  private int count;
  private UserPanel userPanel;
  private String secretWord;

  public WordGame() {
    wordPanels = new WordPanel[6]; // 6 tries
    count = 0;
    frame = new JFrame("Word Game");
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLayout(new GridLayout(7, 1));
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    userPanel = new UserPanel();

    for (int i = 0; i < wordPanels.length; i++) {
      wordPanels[i] = new WordPanel(); // gets each row of word panels
      frame.add(wordPanels[i]);
    }
    userPanel.getEnterButton().addActionListener(this);
    frame.add(userPanel); // adds the user input and enter button
    frame.revalidate();

    secretWord = randomizeWord();
    //System.out.println("Secret Word: " + secretWord);
  }

  /**
   * Reads words(must be 6 letters) from dictionary txt file and stores it into a wordlist arraylist
   *
   * @return arraylist that has 6 letter words read from dictionary txt file
   */
  public ArrayList<String> readWordList() {
    String name = "Dictionary.txt";
    File file = new File(name);
    ArrayList<String> wordList = new ArrayList<>();
    Scanner sc = null;

    // scans dictionary and stores into arraylist
    try {
      sc = new Scanner(file);
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        // checks word is 6 letters
        if (line.length() == 6) {
          wordList.add(line);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Error, file not found");
    } finally {
      if (sc != null) {
        sc.close();
      }
    }
    return wordList;
  }

  /**
   * Randomizes word chosen for the wordgame
   *
   * @return randomized word
   */
  public String randomizeWord() {
    Random random = new Random();
    int randomIndex = random.nextInt(readWordList().size());
    return readWordList().get(randomIndex).trim().toUpperCase();
  }

  /**
   * Checks if user's word matches secret word and if so outputs respective message
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();
    String userWord = this.userPanel.getUserWord().getText();

    if (count > 5) {
      JOptionPane.showMessageDialog(null, "USER LOST" + "\nCorrect Word: " + secretWord, "Sorry!", JOptionPane.INFORMATION_MESSAGE);
      frame.dispose();
      return;
    }

    if (action.equals("Enter") && userWord.length() == 6) {
      if (checkUserWordEqualsSecretWord(userWord.trim().toUpperCase())) {
        JOptionPane.showMessageDialog(null, "USER WON" + "\nTries: " + (count +1), "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
        return;
      }
      count++;
    }
  }

  public boolean checkUserWordEqualsSecretWord(String userWord) {
    // split the word so each letter can be checked
    String[] userInput = userWord.split("");
    List<String> wordLetters = Arrays.asList(secretWord.split(""));
    // store vals that correspond to if letter is right and in right position
    ArrayList<Boolean> letterMatches = new ArrayList<>();

      for (int k = 0; k < 6; k++) {
        if (wordLetters.contains(userInput[k])) {
          if (wordLetters.get(k).equals(userInput[k])) {
            getActivePanel().updatePanel(userInput[k], k, Color.GREEN); // letter is in right spot and is correct
            letterMatches.add(true);
          } else {
            getActivePanel().updatePanel(userInput[k], k, Color.YELLOW); // letter is correct but not in right spot
            letterMatches.add(true);
          }
        } else {
          getActivePanel().updatePanel(userInput[k], k, Color.RED); // wrong letter and position
          letterMatches.add(false);
        }
      }
    return !letterMatches.contains(false);
  }

  public WordPanel getActivePanel() {
    return this.wordPanels[count];
  }
  
  /*public void wordGame() {

    Scanner input = new Scanner(System.in);
    String wordChosen = randomizeWord();
    char[] word = new char[wordChosen.length()];

    for (int i = 0; i < word.length; i++) {
      word[i] = wordChosen.charAt(i);
    }

    System.out.println("Rules:\nThe word has 6 letters\nYou have 6 tries to guess it\n(Remember"
        + " to use lowercase letters and proper words only)\nEnter a word to begin:");

    for (int i = 0; i < 6; i++) {
      //Taking input from the user.
      String userInput = input.nextLine();
      if (userInput.length() != 6) {
        if (i == 5) {
          System.out.println("USER LOST!\nCorrect answer = " + String.valueOf(word));
          break;
        }
        System.out.println("Invalid input: try again");
      }
      else {
        char[] userWord = new char[userInput.length()];
        for(int j = 0; j < userInput.length(); j++){
          userWord[j] = userInput.charAt(j);
        }

        //Comparing it to given word.
        for (int k = 0; k < 6; k++) {
          for (int l = 0; l < userInput.length(); l++) {

            if (userWord[l] == word[k]) {

              if (l == k) {
                System.out.println(userWord[l] + " is at the right position.");
                break;
              }

              System.out.println(userWord[l] + " is present in the word");
              break;

            }

          }
        }

        //Final part
        if (String.valueOf(userWord).equals(String.valueOf(word))) {
          System.out.println("USER WON!\nAttempts: " + (i + 1));
        }
        else {
          if (i == 6) {
            System.out.println("USER LOST!\nCorrect answer = " + String.valueOf(word));
            break;
          }
          System.out.println("Try again");
        }
      }
    }
  }*/
}
