import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MQ{
    JFrame mainFrame;
    JPanel mainPanel;
    JLabel welcome;
    JButton addition, subtraction, multiplication, division;
    GridBagConstraints gbc = new GridBagConstraints();
    Random random = new Random();

    abstract class ArithQ{
        JPanel quizPanel, scorePanel, dataPanel;
        JButton check, retry;
        JTextField inputBox;
        JLabel mathQuestion, dataLabel;
        int n1, n2, result, answer;

        public abstract void nameFrame();

        public abstract void generate();

        public void makeQuizPanel(){
            nameFrame();
            generate();
            resetGBC();
            gbc.insets = new Insets(5,5,5,5);

            inputBox = new JTextField();
            inputBox.setPreferredSize(new Dimension(120, 30));
            inputBox.setHorizontalAlignment(SwingConstants.CENTER);
            inputBox.setFont(new Font("SansSerif",Font.BOLD,18));

            scorePanel = new JPanel(new BorderLayout());
            scorePanel.setBorder(new EmptyBorder(30, 30, 0, 30));

            JLabel positive = new JLabel("Correct: ");
            positive.setForeground(Color.GREEN);
            JLabel negative = new JLabel("Incorrect: ");
            negative.setForeground(Color.RED);

            scorePanel.add(positive,BorderLayout.WEST);
            scorePanel.add(negative,BorderLayout.EAST);

            quizPanel = new JPanel(new GridBagLayout());
            mathQuestion = new JLabel("");
            check = new JButton("Check");

            quizPanel.add(mathQuestion,gbc);
            gbc.gridy++;

            quizPanel.add(inputBox,gbc);
            gbc.gridy++;

            quizPanel.add(check,gbc);

            clearFrame();
            mainFrame.add(scorePanel,BorderLayout.NORTH);
            mainFrame.add(quizPanel,BorderLayout.CENTER);
            restartFrame();
        }

    }

    class Addition extends ArithQ{
        public void nameFrame(){
            mainFrame.setTitle("Math Quiz - Addition");
        }
        public void generate(){
            n1 = random.nextInt(51);
            n2 = random.nextInt(51);
            result = n1 + n2;
            mathQuestion.setText(n1 + " + " + n2);
        }

        public Addition(){
            makeQuizPanel();
        }
    }

    public void resetGBC(){
        gbc.gridx = 0;
        gbc.gridy = 0;
    }

    public void clearFrame(){
        mainFrame.getContentPane().removeAll();
    }

    public void restartFrame(){
        mainFrame.revalidate();
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
        addition.addActionListener(e -> new Addition());
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