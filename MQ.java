import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MQ{
    JFrame mainFrame;
    JPanel mainPanel;
    JLabel label;

    public void makeMainFrame(){
        mainFrame = new JFrame("Math Quiz");
        mainPanel = new JPanel(new GridBagLayout());
        label = new JLabel("Welcome to the Math Quiz");
        label.setFont(new Font("TimesRoman",Font.BOLD,30));

        mainPanel.add(label);
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