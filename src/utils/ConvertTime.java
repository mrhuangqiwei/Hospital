package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertTime {

/**转换时间格式如2017-08-12 08**/
public static  String  converttimetoYYMMDDHH(String date){
	
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  Date date1 = null;
	  try {
         date1=sdf.parse(date);
          
      } catch (ParseException ex) {
          System.out.print("jycx"+"时间转换有错误");
      }
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
      String tablename=dateFormat.format(date1);
      return tablename;
}/**转换时间格式如2017-08-12 08 00**/
public static  String  converttimetoYYMMDDHH00(String date){
	
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  Date date1 = null;
	  try {
       date1=sdf.parse(date);
        
    } catch (ParseException ex) {
        System.out.print("jycx"+"时间转换有错误");
    }
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00.000");
    String tablename=dateFormat.format(date1);
    return tablename;
}

/* 
 * 将时间转换为时间戳
 */    
public static String dateToStamp(String s) {
	
    String res;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date date = null;
	try {
		date = simpleDateFormat.parse(s);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    long ts = date.getTime();
    res = String.valueOf(ts);
    return res;
	
}
/* 
 * 将时间戳转换为时间
 */
public static String stampToDate(String s){
    String res;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    long lt = new Long(s);
    Date date = new Date(lt);
    res = simpleDateFormat.format(date);
    return res;
}
}
