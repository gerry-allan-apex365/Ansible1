package org.example;

import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


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
            String json = readFileAsString(filename);

            String[] output = json.split("=>");
            String outcome = output[1];
            System.out.println(outcome);


            /*JSONObject jsonObject = new JSONObject(outcome);
            String ansibledistribution = jsonObject.getString("ansible_distribution");
           // String ansibledistribution = (String) jsonObject.get("ansible_distribution");
            System.out.println("Distribution is " + ansibledistribution);*/

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String readFileAsString(String file) throws Exception{
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}