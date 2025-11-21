/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userregistration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.ArrayList;
/**
 *
 * @author RC_Student_Lab
 */
public class Stream {
    public static ArrayList<MessageRecord> readStoredMessages(String path) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(path);

            return gson.fromJson(reader, new TypeToken<ArrayList<MessageRecord>>(){}.getType());
        } catch (Exception e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
