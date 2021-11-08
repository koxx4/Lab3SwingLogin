package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class MyFrame extends JFrame {

    JPanel mainPanel;
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

        this.setSize(new Dimension(600,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new FlowLayout());

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.RED);

        mainPanel.add(loginDescriptionLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordField);
        mainPanel.add(loginDescriptionLabel);

        loginButton.addActionListener((event) -> handleUserLogin(event) );

        this.add(mainPanel);
    }

    private void populateDummyUserData(){
        userData.put("nick", "123");
        userData.put("bob14", "xyz");
        userData.put("Peter", "admin");
    }

    private void handleUserLogin(ActionEvent event){
        String typedLogin = usernameField.getText();
        String typedPassword = passwordField.getPassword().toString();

        if(userData.containsKey(typedLogin)){
            String userPassword = userData.get(typedLogin);
            if(userPassword.equals(typedPassword)){
                mainPanel.setBackground(Color.GREEN);
                mainPanel.add(new Label("LOGIN SUCCESSFUL!"));
            }
        }
    }

}
