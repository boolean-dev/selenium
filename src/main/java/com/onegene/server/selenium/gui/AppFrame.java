package com.onegene.server.selenium.gui;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * @ClassName AppFrame
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/1/15 11:55
 **/
//@Component
public class AppFrame extends JFrame implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        try {
            AppFrame frame = new AppFrame();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
