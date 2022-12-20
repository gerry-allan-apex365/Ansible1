package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.*;



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

            extracted(filename);

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void extracted(String filename) throws Exception {
        String json = readFileAsString(filename);
        String[] output = json.split("=>");
        String outcome = output[1];

        JSONObject jsonObject = new JSONObject(outcome);
        JSONObject jsonObject1 = jsonObject.getJSONObject("ansible_facts");

        String ansible_distribution = jsonObject1.getString("ansible_distribution");
        String ansible_distribution_major_version = jsonObject1.getString("ansible_distribution_major_version");
        String ansible_distribution_version = jsonObject1.getString("ansible_distribution_version");

        System.out.println("ansible_distribution :" +ansible_distribution);
        System.out.println("ansible_distribution_major_version :" +ansible_distribution_major_version);
        System.out.println("ansible_distribution_version :" +ansible_distribution_version);
    }

    private static String readFileAsString(String file) throws Exception{
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}