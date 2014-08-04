/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team5163.Event;

import static Team5163.Logger.Logger.log;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Yiwen Dong
 */
public class EventManager implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//                //Create and set up the window.
//        //log("Start");
//        JFrame frame = new JFrame("HelloWorldSwing");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Add the ubiquitous "Hello World" label.
//        JLabel label = new JLabel("Hello World");
//        frame.getContentPane().add(label);
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //log("Peace out mother fucker.");
    }
    
}
