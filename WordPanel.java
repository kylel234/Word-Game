import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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

  public void updatePanel(String inputWord, int position, Color color) {
    columns[position].setBackground(color);
    columns[position].setText(inputWord);
  }

}
