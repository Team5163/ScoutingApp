/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team5163.Login;

import static Team5163.Logger.Logger.log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yiwen Dong
 */
public class LoginData {
    
    public LoginData(){

    }
    
    public void getUsers() throws IOException{
        File dataFile = new File("users.xml");
        Team5163.Logger.Logger.log("ran");
        log("ran");
        BufferedReader reader = new BufferedReader(new FileReader("users.xml"));
        String line = null;
        do{
            line = reader.readLine();
            if(line != null){
                log(line);
            }
        } while(line != null);
    }
    
}
