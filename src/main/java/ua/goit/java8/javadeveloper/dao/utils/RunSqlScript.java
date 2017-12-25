package ua.goit.java8.javadeveloper.dao.utils;

/**
 * Created by t.oleksiv on 08/11/2017.
 */

import com.ibatis.common.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 thanks @author Dhinakaran Pragasam
 */
public class RunSqlScript {

    public static void run(String aSQLScriptFilePath) throws ClassNotFoundException,
            SQLException {

        // Create MySql Connection
        Connection con = ConnectionUtil.getConnection();

        try {
            // Initialize object for ScripRunner
            ScriptRunner sr = new ScriptRunner(con, false, false);

            // Give the input file to Reader
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(aSQLScriptFilePath);
            InputStreamReader inputStreamReader = new InputStreamReader(input);
            Reader reader = new BufferedReader(inputStreamReader);

            // Execute script
            sr.runScript(reader);

        } catch (Exception e) {
            System.err.println("Failed to Execute " + aSQLScriptFilePath
                    + " The error is: " + e.getMessage());
        }

        try {
            con.close();
        } finally {
            if(con != null){
                con.close();
            }
        }
    }
}