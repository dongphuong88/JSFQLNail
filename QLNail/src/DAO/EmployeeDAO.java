/**
 * JSON obj and BufferedWriter
 */
package DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import POJO.Employee;

public class EmployeeDAO {
	public static void main(String[] args) {
		// testing purpose
//		JSONObject obj = new JSONObject();
		String directory = "C:\\data\\QLNail\\";
//		
//		List<Employee> emps = new ArrayList<>();
//		Employee emp = new Employee();
//		emp.setName("Kelly");
//		emps.add(emp);
//		
//		emp = new Employee();
//		emp.setName("Nancy");
//		emps.add(emp);
//		
//		obj.put("employees", emps);
//
//		try {
			
//			FileWriter fw = new FileWriter(directory + "employees.dat");
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write(obj.toString());
//			bw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		JSONArray empNames = new JSONArray();
		JSONParser parser = new JSONParser();
		 
        try {
            JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(HelperDAO.DIRECTORY + "employees.dat"));
            JSONArray emps = (JSONArray) jsonObject.get("employees");
            for (Object object : emps) {
            	empNames.add( ((JSONObject)object).get("name").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(empNames);
	}
	
	public static String getStaffNames() {
		JSONParser parser = new JSONParser();
		JSONArray empNames = new JSONArray();
        try {
            JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(HelperDAO.DIRECTORY + "employees.dat"));
            JSONArray emps = (JSONArray) jsonObject.get("employees");
            for (Object object : emps) {
            	empNames.add( ((JSONObject)object).get("name").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

		return empNames.toString();
	}
}
