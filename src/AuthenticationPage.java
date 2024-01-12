import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AuthenticationPage extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField scoreField;
    private JButton actionButton;
    private JLabel username,password,message;
    public static AuthenticationPage authenticationPage;
    private BackgroundOfAuthenticationPage backgroundOfAuthenticationPage;
    public AuthenticationPage(String action){
        authenticationPage = this;
        username = new JLabel("Username: ");
        password = new JLabel("Password: ");
        message = new JLabel();
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        scoreField = new JTextField(20);
        actionButton = new JButton(action);
        backgroundOfAuthenticationPage = new BackgroundOfAuthenticationPage();
        setLayout(null);
        setContentPane(backgroundOfAuthenticationPage);
        actionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                performAction(action);
            }
        });
    }

    private void performAction(String action){
        backgroundOfAuthenticationPage.repaint();
        String name,pass,path;
        name = usernameField.getText();
        pass = passwordField.getText();
        path = "Data/" + name + ".txt";
        File file = new File(path);
        if(action.equals("Login")){
//            if(!file.exists()){
//                message.setForeground(Color.WHITE);
//                message.setText("There is no user with this username!");
//            }
//            else{
//                ReadTextFile readTextFile = new ReadTextFile();
//                if(!pass.equals(readTextFile.ReadTextFile(name,pass,path,this))){
//                    message.setForeground(Color.WHITE);
//                    message.setText("Password Mismatch!");
//                }
//            }
            if(action.equals("Login")){
                if(!file.exists()){
                    message.setForeground(Color.WHITE);
                    message.setText("There is no user with this username!");
                }
                else{
                    ReadTextFile readTextFile = new ReadTextFile();
                    try{
                        String storedPassword = readTextFile.ReadTextFile(name, pass, path, this);
                        if (storedPassword != null && !pass.equals(storedPassword)) {
                            message.setForeground(Color.WHITE);
                            message.setText("Password Mismatch!");
                        }
                    }
                    catch(NullPointerException e){
                        e.printStackTrace();
                        message.setForeground(Color.WHITE);
                        message.setText("Error reading user data. Please try again.");
                    }
                }
            }
        }
        else if(action.equals("Register")){
            if(file.exists()){
                message.setForeground(Color.WHITE);
                message.setText("A user is already registered with this Username!");
            }
            else{
                new CreateTextFile(name,pass,path,0);
                dispose();
                AuthenticationPage authenticationPage = new AuthenticationPage("Login");
                authenticationPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                authenticationPage.setBounds(600,350,600,250);
                authenticationPage.setVisible(true);
                authenticationPage.message.setForeground(Color.WHITE);
                authenticationPage.message.setText("Enter username and password to login now!");
            }
        }
    }
    public void paint(Graphics graphics){
        username.setBounds(50,5,100,30);
        password.setBounds(50,45,100,30);
        usernameField.setBounds(150,5,300,30);
        passwordField.setBounds(150,45,300,30);
        actionButton.setBounds(220,100,100,40);
        message.setBounds(120,180,400,30);
        add(username);
        add(password);
        add(usernameField);
        add(passwordField);
        add(actionButton);
        add(message);
    }
}
