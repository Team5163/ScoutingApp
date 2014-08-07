/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Team5163.Login;

import static Team5163.Logger.Logger.log;
import Team5163.ObjectRegestry;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    private String filePath = ObjectRegestry.getWorkingDir() + "\\users.xml";
    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document doc;

    public LoginData() {
//        try {
//            docFactory = DocumentBuilderFactory.newInstance();
//            docBuilder = docFactory.newDocumentBuilder();
//            doc = docBuilder.parse(filePath);
//            doc.getDocumentElement().normalize();
//
//            //Node loginData = doc.getFirstChild();
//            NodeList Users = doc.getElementsByTagName("User");
//
//            for (int a = 0; a < Users.getLength(); a++) {
//                //log("Am at: " + Users.item(a).getNodeName());
//                String name = Users.item(a).getAttributes().getNamedItem("name").getTextContent();
//                String pass = Users.item(a).getTextContent();
//                //log("AddedUser: " + name);
//                //log("AddedHash: " + pass);
//                mapOfUser.put(name, Integer.valueOf(pass));
//            }
//            started = true;
//        } catch (ParserConfigurationException | SAXException | IOException ex) {
//            Logger.getLogger(LoginData.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void start() {
        if (!started) {
            try {
                docFactory = DocumentBuilderFactory.newInstance();
                docBuilder = docFactory.newDocumentBuilder();
                doc = docBuilder.parse(filePath);
                doc.getDocumentElement().normalize();

                //Node loginData = doc.getFirstChild();
                NodeList Users = doc.getElementsByTagName("User");

                for (int a = 0; a < Users.getLength(); a++) {
                    //log("Am at: " + Users.item(a).getNodeName());
                    String name = Users.item(a).getAttributes().getNamedItem("name").getTextContent();
                    String pass = Users.item(a).getTextContent();
                    //log("AddedUser: " + name);
                    //log("AddedHash: " + pass);
                    mapOfUser.put(name, Integer.valueOf(pass));
                }
                
                log("Login Data Started");
                started = true;
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(LoginData.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            log("Already Started");
        }
    }

    public void stop() {
        if (started) {
            try {
                
                //doc.createElement("User").setTextContent("hash");.setAttribute("naem", "username");
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filePath));
                transformer.transform(source, result);
                log("Login Data stoped.");
                this.finalize();
            } catch (TransformerException ex) {
                Logger.getLogger(LoginData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Throwable ex) {
                Logger.getLogger(LoginData.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            log("Did not start");
        }
    }

    public boolean checkUser(String name, String password) {
        if (mapOfUser.containsKey(name)) {
            if (mapOfUser.get(name) == password.hashCode()) {
                return true;
            }
        }
        return false;
    }

    public void addUser(String name, String password) {
        if (mapOfUser.containsKey(name)) {
            mapOfUser.remove(name);
        }
        mapOfUser.put(name, password.hashCode());
    }

    public void listUser() {
        log("--------------------Begin to list User--------------------------");
        for (Map.Entry<String, Integer> entry : mapOfUser.entrySet()) {
            log("User: \"" + entry.getKey() + "\" with hash: \"" + entry.getValue().toString() + "\"");
        }
        log("---------------------Done listing User--------------------------");
    }
}
