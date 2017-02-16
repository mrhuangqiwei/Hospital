package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {
	//通过传入ResultSet 转json
	public static String resultSetToJson(ResultSet rs)  
	{  
	   // json数组  
	  JSONArray array = new JSONArray();  
	   
	  // 获取列数  
	  ResultSetMetaData metaData;
	try {
		metaData = rs.getMetaData();
	 
	  int columnCount = metaData.getColumnCount();  
	    
	   // 遍历ResultSet中的每条数据  
	    while (rs.next()) {  
	       JSONObject jsonObj = new JSONObject();  
	         
	        // 遍历每一列  
	        for (int i = 1; i <= columnCount; i++) {  
	           String columnName =metaData.getColumnLabel(i).trim();  
	          String value = rs.getString(columnName); 
	           try {
				jsonObj.put(columnName, value);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }   
	        array.put(jsonObj);   
	   }  
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	    
	    
	   return array.toString();  
	} 
}
