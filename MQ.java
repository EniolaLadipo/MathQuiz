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

    public boolean stringWithNum(String text){
        return text.matches(".*\\d.*");
    }

    //Abstract class containing the panel and logic for each operation quiz
    abstract class ArithQ{
        JPanel quizPanel, scorePanel, dataPanel, numQuestionsPanel, difficultyPanel;
        JButton check, retry, close, easy, medium, hard, numQuestionsBtn;
        JTextField inputBox, numQuestionTF;
        JLabel positive, negative, dataLabel, difficultyLabel, numQuestionLabel;
        int n1, n2, result, answer, limit;
        JLabel mathQuestion = new JLabel("");
        int positiveCounter = 0, negativeCounter = 0, quizCounter = 0;
        //This method will change the title of the fram depending on the type of quiz being done
        public abstract void nameFrame();

        //This method will set the math question to the appropriate operation depending on the quiz selected
        public abstract void generate();

        public abstract void chooseDifficulty(ActionEvent e);

        public void resetQuestion(int limit){
            n1 = random.nextInt(limit);
            n2 = random.nextInt(limit);
        }

        public boolean onlyNumbers(String text){
            return text.matches("\\d+");
        }

        public void numQuestionCheck(){
            String numQuestion = numQuestionTF.getText();
            if(stringWithNum(numQuestion)){
                makeQuizPanel();
            }

        }

        public void makeDifficultyPanel(){
            resetGBC();
            gbc.insets = new Insets(5,5,5,5);
            difficultyPanel = new JPanel(new GridBagLayout());
            difficultyLabel = new JLabel("Choose your difficulty");
            difficultyLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            difficultyLabel.setFont(new Font("SansSerif",Font.BOLD,30));
            difficultyLabel.setBorder(new EmptyBorder(0, 0, 30, 0));

            difficultyPanel.add(difficultyLabel,gbc);
            gbc.gridy++;

            easy = new JButton("Easy");
            easy.addActionListener(e -> chooseDifficulty(e));

            medium = new JButton("Medium");
            medium.addActionListener(e -> chooseDifficulty(e));

            hard = new JButton("Hard");
            hard.addActionListener(e -> chooseDifficulty(e));

            difficultyPanel.add(easy,gbc);
            gbc.gridy++;

            difficultyPanel.add(medium,gbc);
            gbc.gridy++;

            difficultyPanel.add(hard,gbc);
            clearFrame();
            mainFrame.add(difficultyPanel);
            restartFrame();
        }

        public void makeNumQuestionPanel(){
            resetGBC();

            KeyListener listenForNum = new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e){
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        numQuestionCheck();
                    }
                }
            };

            numQuestionsPanel = new JPanel(new GridBagLayout());
            numQuestionLabel = new JLabel("Number of questions to answer");
            numQuestionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            numQuestionLabel.setFont(new Font("SansSerif",Font.BOLD,25));
            numQuestionLabel.setBorder(new EmptyBorder(0,0,20,0));
            numQuestionsPanel.add(numQuestionLabel,gbc);
            gbc.gridy++;

            numQuestionTF = new JTextField();
            numQuestionTF.setPreferredSize(new Dimension(120, 30));
            numQuestionTF.setHorizontalAlignment(SwingConstants.CENTER);
            numQuestionTF.setFont(new Font("SansSerif",Font.BOLD,18));
            numQuestionTF.addKeyListener(listenForNum);
            numQuestionsPanel.add(numQuestionTF,gbc);
            gbc.gridy++;

            numQuestionsBtn = new JButton("Submit");
            numQuestionsBtn.setFont(new Font("SansSerif",Font.BOLD,18));
            numQuestionsBtn.addActionListener(e -> numQuestionCheck());
            numQuestionsPanel.add(numQuestionsBtn,gbc);
            clearFrame();
            mainFrame.add(numQuestionsPanel);
            restartFrame();
        }

        public void makeQuizPanel(){
            nameFrame();
            generate();
            resetGBC();

            KeyListener listenForAnswer = new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e){
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        check();
                    }
                }
            };

            Font fontForScores = new Font("SansSerif",Font.BOLD,19);
            gbc.insets = new Insets(5,5,5,5);

            mathQuestion.setFont(new Font("SansSerif",Font.BOLD,30));
            mathQuestion.setBorder(new EmptyBorder(0, 0, 30, 0));

            inputBox = new JTextField();
            inputBox.setPreferredSize(new Dimension(120, 30));
            inputBox.setHorizontalAlignment(SwingConstants.CENTER);
            inputBox.setFont(new Font("SansSerif",Font.BOLD,18));
            inputBox.addKeyListener(listenForAnswer);

            scorePanel = new JPanel(new BorderLayout());
            scorePanel.setBorder(new EmptyBorder(30, 30, 0, 30));

            positive = new JLabel("Correct: ");
            positive.setForeground(Color.GREEN);
            positive.setFont(fontForScores);

            negative = new JLabel("Incorrect: ");
            negative.setForeground(Color.RED);
            negative.setFont(fontForScores);

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
            if(inputBox.getText().isEmpty() || !(onlyNumbers(inputBox.getText()))){
                negativeCounter++;
                inputBox.setText("");
                negative.setText("Incorrect: " + negativeCounter);
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
            
            quizCounter++;
            if(quizCounter == 3){
                makeDataPanel();
            } else {
                generate();
            }
        }

        public void makeDataPanel(){
            resetGBC();
            dataPanel = new JPanel(new GridBagLayout());
            dataLabel = new JLabel("<html>You got <font color='green'>" + positiveCounter + "</font> correct and <font color='red'>" + negativeCounter + "</font> wrong</html>");
            dataLabel.setFont(new Font("SansSerif",Font.BOLD,26));
            dataLabel.setBorder(new EmptyBorder(0,0,30,0));

            dataPanel.add(dataLabel,gbc);
            gbc.gridy++;

            retry = new JButton("Try Again");
            retry.addActionListener(e -> backToMenu());
            dataPanel.add(retry,gbc);
            gbc.gridy++;
            
            close = new JButton("Close");
            close.addActionListener(e -> exitQuiz());
            dataPanel.add(close,gbc);
            
            clearFrame();
            mainFrame.add(dataPanel);
            restartFrame();
        }
    }

    class Addition extends ArithQ{
        public void nameFrame(){
            mainFrame.setTitle("Math Quiz - Addition");
        }
        public void chooseDifficulty(ActionEvent e){
            String difficulty = e.getActionCommand();
            switch (difficulty){
                case "Easy":
                    limit = 20;
                    break;
                case "Medium":
                    limit = 40;
                    break;
                case "Hard":
                    limit = 60;
                    break;
            }
            makeNumQuestionPanel();
        }
        public void generate(){
            resetQuestion(limit);
            result = n1 + n2;
            mathQuestion.setText(n1 + " + " + n2);
        }
        public Addition(){
            makeDifficultyPanel();
        }
    }
    class Subtraction extends ArithQ{
        public void nameFrame(){
            mainFrame.setTitle("Math Quiz - Subtraction");
        }
        public void chooseDifficulty(ActionEvent e){
            String difficulty = e.getActionCommand();
            switch (difficulty){
                case "Easy":
                    limit = 20;
                    break;
                case "Medium":
                    limit = 40;
                    break;
                case "Hard":
                    limit = 60;
                    break;
            }
            makeNumQuestionPanel();
        }
        public void generate(){
            resetQuestion(limit);
            while(n2 > n1){
                resetQuestion(limit);
            }
            result = n1 - n2;
            mathQuestion.setText(n1 + " - " + n2);
        }
        public Subtraction(){
            makeDifficultyPanel();
        }
    }
    class Multiplication extends ArithQ{
        public void nameFrame(){
            mainFrame.setTitle("Math Quiz - Multiplication");
        }
        public void chooseDifficulty(ActionEvent e){
            String difficulty = e.getActionCommand();
            switch (difficulty){
                case "Easy":
                    limit = 9;
                    break;
                case "Medium":
                    limit = 12;
                    break;
                case "Hard":
                    limit = 15;
                    break;
            }
            makeNumQuestionPanel();
        }
        public void generate(){
            resetQuestion(limit);
            result = n1 * n2;;
            mathQuestion.setText(n1 + " x " + n2);
        }
        public Multiplication(){
            makeDifficultyPanel();
        }
    }
    class Division extends ArithQ{
        public void nameFrame(){
            mainFrame.setTitle("Math Quiz - Division");
        }
        public void chooseDifficulty(ActionEvent e){
            String difficulty = e.getActionCommand();
            switch (difficulty){
                case "Easy":
                    limit = 50;
                    break;
                case "Medium":
                    limit = 100;
                    break;
                case "Hard":
                    limit = 150;
                    break;
            }
            makeNumQuestionPanel();
        }
        public void generate(){
            resetQuestion(limit);
            while(n1 % n2 != 0){
                resetQuestion(limit);
            }
            result = n1 / n2;
            mathQuestion.setText(n1 + "\u00F7" + n2);
        }
        public Division(){
            makeDifficultyPanel();
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
        mainPanel = new JPanel(new GridBagLayout());
        welcome = new JLabel("Welcome to the Math Quiz");
        welcome.setFont(new Font("TimesRoman",Font.BOLD,30));
        welcome.setBorder(new EmptyBorder(0, 0, 20, 0));

        mainPanel.add(welcome,gbc);
        gbc.gridy++;

        addition = new JButton("Addition");
        addition.addActionListener(e -> new Addition());
        mainPanel.add(addition,gbc);
        gbc.gridy++;

        subtraction = new JButton("Subtraction");
        subtraction.addActionListener(e -> new Subtraction());
        mainPanel.add(subtraction,gbc);
        gbc.gridy++;

        multiplication = new JButton("Multiplication");
        multiplication.addActionListener(e -> new Multiplication());
        mainPanel.add(multiplication,gbc);
        gbc.gridy++;

        division = new JButton("Division");
        division.addActionListener(e -> new Division());
        mainPanel.add(division,gbc);
    }

    public void makeMainFrame(){
        initialiseFrame();
        makeMainPanel();
        mainFrame.add(mainPanel);
        mainFrame.setSize(650,500);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initialiseFrame(){
        mainFrame = new JFrame("Math Quiz - Menu");
    }

    public void backToMenu(){
        clearFrame();
        mainFrame.setTitle("Math Quiz - Menu");
        makeMainPanel();
        mainFrame.add(mainPanel);
        restartFrame();
    }
    
    public void exitQuiz(){
        mainFrame.dispose();
    }
    
    public MQ(){
        makeMainFrame();
    }
    public static void main(String[] args) {
        new MQ();
    }
}