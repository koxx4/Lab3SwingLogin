package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public final class MyFrame extends JFrame {

    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 600;
    private final Color FAILURE_PANEL_COLOR = Color.RED;
    private final Color SUCCESS_PANEL_COLOR = Color.GREEN;
    private final LoginPanel loginPanel = new LoginPanel();
    private final JMenuBar menuBar = new JMenuBar();
    private final Map<String, String> userData = new HashMap();

    public MyFrame(String title) throws HeadlessException{
        super(title);
        this.populateDummyUserData();
        this.setSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        initializeMenuBar();

        loginPanel.getLoginButton()
                .addActionListener( (event) -> handleUserLogin(event) );

        loginPanel.getCancelButton()
                .addActionListener( (event) -> loginPanel.clearLoginInputs() );

        this.add(loginPanel, BorderLayout.CENTER);
    }

    private void initializeMenuBar() {
        var jMenu = new JMenu("Help");
        var possibleLoginsHintItem = new JMenuItem("All possible logins");
        possibleLoginsHintItem.addActionListener((event) -> showPossibleLoginsDialog());
        jMenu.add(possibleLoginsHintItem);
        menuBar.add(jMenu);
        setJMenuBar(this.menuBar);
    }

    private void showPossibleLoginsDialog() {
        StringBuilder loginsMessageBuilder = new StringBuilder();
        for(var login : this.userData.keySet())
            loginsMessageBuilder.append(String.format("Login: %s, password: %s \n",
                    login,
                    this.userData.get(login)));


        JOptionPane.showMessageDialog(this,
                loginsMessageBuilder.toString(),
                "Confidential ;)",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void populateDummyUserData(){
        userData.put("nick", "123");
        userData.put("bob14", "xyz");
        userData.put("Peter", "admin");
    }

    private void handleUserLogin(ActionEvent event){
        String typedLogin = loginPanel.getUsernameField().getText();
        String typedPassword = String.valueOf(loginPanel.getPasswordField().getPassword());

        if(userData.containsKey(typedLogin)){
            String userPassword = userData.get(typedLogin);
            if(userPassword.equals(typedPassword))
                handleSuccessfulLogin();
        }
        else
            handleUnsuccessfulLogin();
    }

    private void handleSuccessfulLogin(){
        loginPanel.setBackground(SUCCESS_PANEL_COLOR);
        JOptionPane.showMessageDialog(this,
                "Successful login! \u2611",
                "Yay!",
                JOptionPane.INFORMATION_MESSAGE);
        loginPanel.clearLoginInputs();
    }

    private void handleUnsuccessfulLogin(){
        loginPanel.setBackground(FAILURE_PANEL_COLOR);
        JOptionPane.showMessageDialog(this,
                "Invalid login! \u274c",
                ":(",
                JOptionPane.ERROR_MESSAGE);
        loginPanel.clearLoginInputs();
    }

}
