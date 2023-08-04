import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

//Main class for Math Quiz
public class MQ{
    JFrame mainFrame;
    JPanel mainPanel;
    JLabel welcome;
    JButton addition, subtraction, multiplication, division;
    GridBagConstraints gbc = new GridBagConstraints();
    Random random = new Random();

    //Abstract class containing the panel and logic for each operation quiz
    abstract class ArithQ{
        JPanel quizPanel, scorePanel, dataPanel;
        JButton check, retry;
        JTextField inputBox;
        JLabel positive, negative, dataLabel;
        int n1, n2, result, answer;
        JLabel mathQuestion = new JLabel("");
        int positiveCounter = 0;
        int negativeCounter = 0;
        int addLimit = 101;
        int subLimit = 101;
        int multLimit = 25;
        int divLimit = 101;

        //This method will change the title of the fram depending on the type of quiz being done
        public abstract void nameFrame();

        //This method will set the math question to the appropriate operation depending on the quiz selected
        public abstract void generate();

        public void resetQuestion(int limit){
            n1 = random.nextInt(limit);
            n2 = random.nextInt(limit);
        }

        public void makeQuizPanel(){
            nameFrame();
            generate();
            resetGBC();
            gbc.insets = new Insets(5,5,5,5);

            mathQuestion.setFont(new Font("SansSerif",Font.BOLD,35));

            inputBox = new JTextField();
            inputBox.setPreferredSize(new Dimension(120, 30));
            inputBox.setHorizontalAlignment(SwingConstants.CENTER);
            inputBox.setFont(new Font("SansSerif",Font.BOLD,18));

            scorePanel = new JPanel(new BorderLayout());
            scorePanel.setBorder(new EmptyBorder(30, 30, 0, 30));

            positive = new JLabel("Correct: ");
            positive.setForeground(Color.GREEN);
            positive.setFont(new Font("SansSerif",Font.BOLD,25));

            negative = new JLabel("Incorrect: ");
            negative.setForeground(Color.RED);
            negative.setFont(new Font("SansSerif",Font.BOLD,25));

            scorePanel.add(positive,BorderLayout.WEST);
            scorePanel.add(negative,BorderLayout.EAST);

            quizPanel = new JPanel(new GridBagLayout());

            check = new JButton("Check");
            check.addActionListener(e -> check());

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

        public void check(){
            if(inputBox.getText().isEmpty()){
                negativeCounter++;
                inputBox.setText("");
            }  else {
                answer = Integer.parseInt(inputBox.getText());
                inputBox.setText("");

                if(answer == result){
                    positiveCounter++;
                    positive.setText("Correct: " + positiveCounter);
                } else {
                    negativeCounter++;
                    negative.setText("Incorrect: " + negativeCounter);
                }
            }
            generate();
        }
    }

    class Addition extends ArithQ{
        public void nameFrame(){
            mainFrame.setTitle("Math Quiz - Addition");
        }
        public void generate(){
            resetQuestion(addLimit);
            result = n1 + n2;
            mathQuestion.setText(n1 + " + " + n2);
        }
        public Addition(){
            makeQuizPanel();
        }
    }
    class Subtraction extends ArithQ{
        public void nameFrame(){
            mainFrame.setTitle("Math Quiz - Subtraction");
        }
        public void generate(){
            resetQuestion(subLimit);
            while(n2 > n1){
                resetQuestion(subLimit);
            }
            result = n1 - n2;
            mathQuestion.setText(n1 + " - " + n2);
        }
        public Subtraction(){
            makeQuizPanel();
        }
    }
    class Multiplication extends ArithQ{
        public void nameFrame(){
            mainFrame.setTitle("Math Quiz - Multiplication");
        }
        public void generate(){
            resetQuestion(multLimit);
            result = n1 * n2;;
            mathQuestion.setText(n1 + " x " + n2);
        }
        public Multiplication(){
            makeQuizPanel();
        }
    }
    class Division extends ArithQ{
        public void nameFrame(){
            mainFrame.setTitle("Math Quiz - Division");
        }
        public void generate(){
            resetQuestion(divLimit);
            while(n1 % n2 != 0){
                resetQuestion(addLimit);
            }
            result = n1 / n2;
            mathQuestion.setText(n1 + "\u00F7" + n2);
        }
        public Division(){
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