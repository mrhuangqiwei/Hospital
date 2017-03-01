package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

/* 
 * 将时间戳转换为时间格式如2017-02-23
 */
public static String stampToDateyyddmm(String s){
    String res;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    long lt = new Long(s);
    Date date = new Date(lt);
    res = simpleDateFormat.format(date);
    return res;
}
/* 
 * 将时间戳转换为时间格式如date
 */
public static Date stampToDateyyddmmdate(String s){
    String res;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    long lt = new Long(s);
    Date date = new Date(lt);
    res = simpleDateFormat.format(date);
    Date date2 = null;
	try {
		date2 = simpleDateFormat.parse(res);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return date2;
}

 public static boolean  datesdiferrent(String ghrq) {
	 
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
	  Date date1 = null;
	  Date date =new Date();
	  try {
      date1=sdf.parse(ghrq);
       
   } catch (ParseException ex) {
       System.out.print("jycx"+"时间转换有错误");
   }
	  String Bigdadate=sdf.format(date1);
	  String nowdateString=sdf.format(date);
	  boolean ok=false;
	  if(Bigdadate.equals(nowdateString)){
		  ok=true;
	  }else{ok=false;}
	  return ok;
 }
	
 /**获取当前时间格式如2017-08-12 08 00:00.000**/
 public static  String  GetdatebefoYYMMDDHH00(){
 	Date date1=new Date();
 	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00.000");
     String tablename=dateFormat.format(date1);
     return tablename;
 }

	
/**获取当前时间格式如2017-08-12 08 23:59.000**/
public static  String  GetdateafterYYMMDDHH00(){
	Date date1=new Date();
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59.000");
  String tablename=dateFormat.format(date1);
  return tablename;
}


/**获取当前时间格式如2017-08-12 08 23:59.212**/
public static  String  Getdnowdatexx(){
Date date1=new Date();
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
String tablename=dateFormat.format(date1);
return tablename;
}


/**
 * 比较当前日期和输入日期相差的天数
 * @param date
 * @throws ParseException 
 */
public static int  daysBetween( String date) {
	Date date2=new Date();
	Date date3=null;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	  try {
		date2=sdf.parse(sdf.format(date2));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		date3=    sdf.parse(sdf.format(sdf.parse(date)));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  Calendar cal =Calendar.getInstance();
	  cal.setTime(date2);    
      long time1 = cal.getTimeInMillis();                 
      cal.setTime(date3);    
      long time2 = cal.getTimeInMillis();         
      long between_days=(time1-time2)/(1000*3600*24);  
      return Integer.parseInt(String.valueOf(between_days));    
}
}
