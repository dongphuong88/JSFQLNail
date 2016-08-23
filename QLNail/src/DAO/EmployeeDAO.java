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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.MimeTypeParameterList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import POJO.Employee;

public class EmployeeDAO {
	public static void main(String[] args) {
		
		Date time = new Date();

		getStaffNames();
		
		System.out.println( (new Date()).getTime() - time.getTime() );
		
	}
	
	public static JSONArray getStaffNames() {
		JSONParser parser = new JSONParser();
		JSONArray empNames = new JSONArray();
//        try {
//            JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(HelperDAO.DIRECTORY + "employees.dat"));
//            JSONArray emps = (JSONArray) jsonObject.get("employees");
//            for (Object object : emps) {
//            	empNames.add( ((JSONObject)object).get("name").toString());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

		return empNames;
	}
}
