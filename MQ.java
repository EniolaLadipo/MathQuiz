import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MQ{
    JFrame mainFrame;
    JPanel mainPanel;
    JLabel welcome;
    JButton addition, subtraction, multiplication, division;
    GridBagConstraints gbc = new GridBagConstraints();

    abstract class ArithQ{
        JPanel quizPanel, scorePanel, dataPanel;
        JButton check, retry;
        JTextField inputBox;
        JLabel mathQuestion, dataLabel;

        public void nameFrame();

        public void generate();

        public void makeQuizPanel(){
            resetGBC();
            quizPanel = new JPanel(new GridBagLayout());


        }
    }

    public void resetGBC(){
        gbc.gridx = 0;
        gbc.gridy = 0;
    }

    public void makeMainPanel(){
        resetGBC();
        gbc.insets = new Insets(10,10,10,10);

        mainFrame = new JFrame("Math Quiz Menu");
        mainPanel = new JPanel(new GridBagLayout());
        welcome = new JLabel("Welcome to the Math Quiz");
        welcome.setFont(new Font("TimesRoman",Font.BOLD,30));

        mainPanel.add(welcome,gbc);
        gbc.gridy++;

        addition = new JButton("Addition");
        mainPanel.add(addition,gbc);
        gbc.gridy++;

        subtraction = new JButton("Subtraction");
        mainPanel.add(subtraction,gbc);
        gbc.gridy++;

        multiplication = new JButton("Multiplication");
        mainPanel.add(multiplication,gbc);
        gbc.gridy++;

        division = new JButton("Division");
        mainPanel.add(division,gbc);
    }

    public void makeMainFrame(){
        makeMainPanel();
        mainFrame.add(mainPanel);
        mainFrame.setSize(450,450);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public MQ(){
        makeMainFrame();
    }
    public static void main(String[] args) {
        new MQ();
    }
}