package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public final class MyFrame extends JFrame {

    final int WINDOW_WIDTH = 600;
    final int WINDOW_HEIGHT = 600;
    JPanel mainPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    JLabel loginStatusInfo;
    JButton loginButton = new JButton("Login");
    JButton cancelButton = new JButton("Cancel");
    JPasswordField passwordField = new JPasswordField();
    JTextField usernameField = new JTextField();
    JLabel loginDescriptionLabel = new JLabel("Type in your login and password! We will not steal your data :) !");
    Map<String, String> userData = new HashMap();

    public MyFrame(String title) throws HeadlessException{
        super(title);
        this.populateDummyUserData();

        this.setSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        initializeComponents();

        mainPanel.add(buttonsPanel);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void initializeComponents() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.YELLOW);
        buttonsPanel.setBackground(Color.YELLOW);
        buttonsPanel.setLayout(new FlowLayout());

        mainPanel.add(loginDescriptionLabel);
        usernameField.setMaximumSize(new Dimension((int) (WINDOW_WIDTH * 0.8), 100));
        passwordField.setMaximumSize(new Dimension((int) (WINDOW_WIDTH * 0.8), 100));
        mainPanel.add(usernameField);
        mainPanel.add(passwordField);
        buttonsPanel.add(loginButton);
        buttonsPanel.add(cancelButton);

        loginButton.addActionListener( (event) -> handleUserLogin(event) );
        cancelButton.addActionListener( (event) -> clearUserLoginForms() );
    }

    private void populateDummyUserData(){
        userData.put("nick", "123");
        userData.put("bob14", "xyz");
        userData.put("Peter", "admin");
    }

    private void handleUserLogin(ActionEvent event){
        String typedLogin = usernameField.getText();
        String typedPassword = String.valueOf(passwordField.getPassword());

        if(userData.containsKey(typedLogin)){
            String userPassword = userData.get(typedLogin);
            if(userPassword.equals(typedPassword))
                handleSuccessfulLogin();
        }
        else
            handleUnsuccessfulLogin();
    }

    private void clearUserLoginForms(){
        this.passwordField.setText("");
        this.usernameField.setText("");
    }

    private void handleSuccessfulLogin(){
        mainPanel.setBackground(Color.GREEN);
        buttonsPanel.setBackground(Color.GREEN);
        JOptionPane.showMessageDialog(this,
                "Successful login!",
                "Yay!",
                JOptionPane.INFORMATION_MESSAGE);
        clearUserLoginForms();
    }

    private void handleUnsuccessfulLogin(){
        mainPanel.setBackground(Color.RED);
        buttonsPanel.setBackground(Color.RED);
        JOptionPane.showMessageDialog(this,
                "Invalid login!",
                ":(",
                JOptionPane.ERROR_MESSAGE);
        this.clearUserLoginForms();
    }

}
