import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Creates boxes for letters of words to be stored in
 */
public class WordPanel extends JPanel {
  private JLabel[] columns = new JLabel[6];

  public WordPanel() {
    this.setLayout(new GridLayout(1, 5));
    Border border = BorderFactory.createLineBorder(Color.BLACK);
    for (int i = 0; i < 6; i++) {
      columns[i] = new JLabel();
      columns[i].setOpaque(true);
      columns[i].setBorder(border);
      this.add(columns[i]);
    }
  }

  /**
   * Changes the color of the panel/box
   *
   * @param inputWord the word inputted
   * @param position the pos of the word
   * @param color the color the user wants the panel to change to
   */
  public void updatePanel(String inputWord, int position, Color color) {
    columns[position].setBackground(color);
    columns[position].setText(inputWord);
  }

}
