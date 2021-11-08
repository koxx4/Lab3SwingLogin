package org.example;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main( String[] args ) {
        SwingUtilities.invokeLater(() -> {
            try{
                var frame = new MyFrame("LoginApp");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }
}
