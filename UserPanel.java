import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
  private JTextField userWord;
  private JButton enterButton;

  public UserPanel() {
    this.setLayout(new GridLayout(1, 5));
    userWord = new JTextField();
    this.add(userWord);
    enterButton = new JButton("Enter");
    this.add(enterButton);
  }

  public JButton getEnterButton() {
    return enterButton;
  }

  public JTextField getUserWord() {
    return userWord;
  }
}
