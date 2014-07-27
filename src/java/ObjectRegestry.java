
import DataBase.DataBase;
import Logger.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yiwen Dong
 */
public class ObjectRegestry {
    
    private static DataBase dataBase = new DataBase();
    private static Logger logger = new Logger();
    
    public static DataBase getDataBase(){
        return dataBase;
    }
    
    public static Logger getLogger(){
        return logger;
    }
}
