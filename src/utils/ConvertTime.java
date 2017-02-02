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
}
}
