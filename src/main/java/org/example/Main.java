package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
        try {

            // print a message
            System.out.println("Executing ansible");

            // create a process and execute notepad.exe
            //Process env = Runtime.getRuntime().exec("export OBJC_DISABLE_INITIALIZE_FORK_SAFETY=YES");
            Process proc = Runtime.getRuntime().exec("ansible win -m setup");

            // print another message
            System.out.println("Ansible should now display version.");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

// Read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            String filename = "/home/Archer/Ansible1/src/main/resources/ansibleinventory.json";
            FileWriter fWriter = new FileWriter(filename);
            while ((s = stdInput.readLine()) != null) {

                fWriter.write(s);
                fWriter.write("\r\n");

            }
            fWriter.close();

            DataExtraction.extracted(filename);

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}