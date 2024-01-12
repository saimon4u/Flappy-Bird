import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame{
    private JButton loginButton;
    private JButton registerButton;
    private JLabel one,two;
    public static MenuFrame menuFrame;

    public MenuFrame(){
        menuFrame = this;
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        one = new JLabel("If you have already an account: ");
        two = new JLabel("For new account: ");
        setLayout(null);
        setContentPane(new BackgroundOfMainFrame());
        setTitle("Flappy Bird");
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                openLoginPage("Login");
            }
        });

        registerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                openLoginPage("Register");
            }
        });
    }

    private void openLoginPage(String action){
        AuthenticationPage authPage = new AuthenticationPage(action);
        authPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        authPage.setBounds(600,350,600,250);
        authPage.setVisible(true);
    }
    public void paint(Graphics graphics){
        one.setForeground(Color.WHITE);
        two.setForeground(Color.WHITE);
        one.setBounds(5,30,250,25);
        two.setBounds(5,80,250,25);
        loginButton.setBounds(255,30,100,25);
        registerButton.setBounds(255,80,100,25);
        loginButton.setForeground(Color.BLACK);
        registerButton.setForeground(Color.BLACK);
        add(one);
        add(two);
        add(loginButton);
        add(registerButton);
    }
}
