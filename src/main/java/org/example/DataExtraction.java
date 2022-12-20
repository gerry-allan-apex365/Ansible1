package org.example;

import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DataExtraction {


    public static void extracted(String filename) throws Exception {
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

    public static String readFileAsString(String file) throws Exception{
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
