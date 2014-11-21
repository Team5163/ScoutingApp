/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Team5163.Login;

import static Team5163.Logger.Logger.log;
import Team5163.ObjectRegistry;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Yiwen Dong
 */
public class LoginData {

    private boolean started = false;
    private Map<String, Integer> mapOfUser = new HashMap<>();

    public LoginData() {
    }

    public void start() {
        mapOfUser = ObjectRegistry.getDataBase().getLogins();
        started = true;
    }

    public void stop() {
//Close the database connection, maybe?
    }

    public void refreshMap() {
        mapOfUser = ObjectRegistry.getDataBase().getLogins();
    }

    public boolean checkUser(String name, String password) {
        refreshMap();
        if (!started) {
            this.start();
        }
        if (mapOfUser.containsKey(name)) {
            if (mapOfUser.get(name) == password.hashCode()) {
                return true;
            }
        }
        return false;
    }

    public void addUser(String name, String password, int teamnum) {
        refreshMap();
        if (!started) {
            this.start();
        }
        if (mapOfUser.containsKey(name)) {
            mapOfUser = ObjectRegistry.getDataBase().removeUser(name);
        }
        mapOfUser = ObjectRegistry.getDataBase().addUser(name, password.hashCode(), teamnum);
    }

    public void listUser() {
        refreshMap();
        log("--------------------Begin to list User--------------------------");
        for (Map.Entry<String, Integer> entry : mapOfUser.entrySet()) {
            log("User: \"" + entry.getKey() + "\" with hash: \"" + entry.getValue().toString() + "\"");
        }
        log("---------------------Done listing User--------------------------");
    }
}
