package edu.jsu.mcis;

import org.json.simple.*;
import java.sql.*;
import org.json.simple.JSONArray;

public class DatabaseTest {

    JSONArray getJSONData() {
        
        JSONObject jsonObject = new JSONObject();
        
        JSONArray column = new JSONArray();
        JSONArray data = new JSONArray();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/p2_test", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from people");

            
            

            column.add("firstname");
            column.add("middleinitial");
            column.add("lastname");
            column.add("address");
            column.add("city");
            column.add("state");
            column.add("zip");

            
            jsonObject.put("column", column);
            jsonObject.put("data", data);

            while (rs.next()) {
                //System.out.println(rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6) + "  " + rs.getString(7) + "  " + rs.getString(8));
                
                JSONArray rows = new JSONArray();
                
                rows.add(rs.getString(2));
                rows.add(rs.getString(3));
                rows.add(rs.getString(4));
                rows.add(rs.getString(5));
                rows.add(rs.getString(6));
                rows.add(rs.getString(7));
                rows.add(rs.getString(8));
                
                
                data.add(rows);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
                 
        JSONArray rows = (JSONArray)jsonObject.get("column");
        JSONArray datas = (JSONArray)jsonObject.get("data");
        
        
        

        int j = 0;
        int counter = 1;

        JSONArray result = new JSONArray();
        
        for (int i = 0; i < datas.size(); i++) {
            JSONObject jsonOb = new JSONObject();
            while (j < counter) {
                JSONArray part = (JSONArray) datas.get(j);
                for (int k = 0; k < part.size(); k++) {
                    
                        jsonOb.put(rows.get(k), part.get(k));
                   
                }
                j++;
            }
            counter++;
            result.add(jsonOb);
           
        }
        
        
        return result;
    }
}
