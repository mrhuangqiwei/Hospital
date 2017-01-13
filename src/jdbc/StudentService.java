package jdbc;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import org.omg.CORBA.PUBLIC_MEMBER;

import bean.WeiXinUserBean;



public class StudentService {

	Dao dao = Dao.getInstance();

	/**
	 * 
	  * @Title: list
	  * @Description: 分页查找
	  * @param @param keyword
	  * @param @param pageSize
	  * @param @param nowPage
	  * @throws
	 */
	public ArrayList<Object[]> list(String keyword, int pageSize, int nowPage) {
		ArrayList<Object[]> dataList = new ArrayList<Object[]>();

		int start = 1, end = 50;
		if (pageSize > 0 && nowPage > 0) {
			start = pageSize * (nowPage - 1) + 1;
			end = pageSize * nowPage;
		}
		String sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY s_id desc) AS row, * FROM dbo.student_info where s_name like '%"
				+ keyword
				+ "%' or s_no like '%"
				+ keyword
				+ "%' ) AS TABLE1 WHERE row BETWEEN "
				+ start
				+ " AND "
				+ end
				+ " order by s_id desc";
		Vector<Vector<Object>> list = dao.selectSomeNote(sql);
		for (Vector<Object> li : list) {
			Object[] o = new Object[12];
			o[0] = li.get(1);
			o[1] = li.get(2);
			o[2] = li.get(3);
			o[3] = li.get(4);
			o[4] = li.get(5);
			o[5] = li.get(6);
			o[6] = li.get(7);
			o[7] = li.get(8);
			o[8] = li.get(9);
			o[9] = li.get(10);
			o[10] = li.get(11);
			o[11] = li.get(12);
			dataList.add(o);
		}
		return dataList;
	}

	/**
	  * @Title: getRowCount
	  * @Description: 获取总条数
	  * @param @param keyword	关键字
	 */
	public int getRowCount(String keyword) {
		String sql = "SELECT COUNT(s_id) AS total_num FROM dbo.student_info where s_name like '%"+keyword+"%' or s_no like '%"+keyword+"%'";
		return (Integer) dao.selectOnlyValue(sql);
	}
	
	/**
	  * @Title: getLastStudentNo
	  * @Description: 获取最大学号
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getLastStudentNo() {
		String sql = "select MAX(s_no) from student_info";
		return (String) dao.selectOnlyValue(sql);
	}
	
	
	/**
	 * 
	  * @Title: insertStudent
	  * @Description: 添加学生信息
	  * @param @param fileInfo
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
/**
	public boolean insertStudent(StudentInfoBean fileInfo) {
		boolean ok = false;
		String sql = "insert into student_info(s_no,s_name,s_pass,s_email,s_enter,s_sex,s_photo,s_birth,s_type) values('"
				+ fileInfo.getS_no()
				+ "','"
				+ fileInfo.getS_name()
				+ "','"
				+ fileInfo.getS_pass()
				+ "','"
				+ fileInfo.getS_email()
				+ "','"
				+ fileInfo.getS_enter()
				+ "','"
				+ fileInfo.getS_sex()
				+ "','"
				+ fileInfo.getS_photo()
				+ "','"
				+ fileInfo.getS_birth()
				+ "','"
				+ fileInfo.getS_type() + "')";
		ok = dao.insert(sql);
		return ok;
	}**/

	/**
	 * 
	  * @Title: updateStudent
	  * @Description: 修改学生信息
	  * @param @param fileInfo
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	/**
	public boolean updateStudent(StudentInfoBean fileInfo) {
		boolean ok = false;
		
		String sql = "update student_info set s_name='" + fileInfo.getS_name()
				+ "',s_pass='" + fileInfo.getS_pass() + "',s_enter='"
				+ fileInfo.getS_enter() + "',s_birth='" + fileInfo.getS_birth()
				+ "',s_email='" + fileInfo.getS_email() + "',s_photo='"
				+ fileInfo.getS_photo() + "',s_sex='" + fileInfo.getS_sex()
				+ "',s_type='" + fileInfo.getS_type() + "' where s_no='"
				+ fileInfo.getS_no() + "'";
		
		if(fileInfo.getS_photo() == null || "".equals(fileInfo.getS_photo().trim())){
			sql = "update student_info set s_name='" + fileInfo.getS_name()
					+ "',s_pass='" + fileInfo.getS_pass() + "',s_enter='"
					+ fileInfo.getS_enter() + "',s_birth='" + fileInfo.getS_birth()
					+ "',s_email='" + fileInfo.getS_email() + "',s_sex='" + fileInfo.getS_sex()
					+ "',s_type='" + fileInfo.getS_type() + "' where s_no='"
					+ fileInfo.getS_no() + "'";
		}
		
		ok = dao.update(sql);
		return ok;
	}
**/
	/**
	 * 
	  * @Title: deleteStudent
	  * @Description: 删除学生
	  * @param @param id
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	public boolean deleteStudent(String id) {
		boolean ok = false;
		String sql = "delete student_info where s_id = '"+id+"'";
		ok = dao.delete(sql);
		return ok;
	}

	/** 根据ID查找学生信息*/
	/**
	public StudentInfoBean getStudentByID(String id) {
		StudentInfoBean bean = new StudentInfoBean();
		String sql = "select * from student_info where s_id='"+id+"'";
		Vector<Object> v = dao.selectOnlyNote(sql);
		bean.setS_no((String) v.get(1));
		bean.setS_name((String) v.get(2));
		bean.setS_pass((String) v.get(3));
		bean.setS_email((String) v.get(4));
		
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        String tsStr = sdf.format(v.get(5));  
		bean.setS_enter(tsStr);
		
		bean.setS_sex(String.valueOf(v.get(6)));
		
		tsStr = sdf.format(v.get(8));  
		bean.setS_birth(tsStr);
		
		bean.setS_type((String) v.get(9));
		return bean;
	}**/
/**
	public static void main(String[] args){
		StudentService service = new StudentService();
		service.getStudentByID("1");
	}**/
	
	public boolean  insertweixinuser (WeiXinUserBean bean) {
		boolean ok = false;
		String sql = "insert gyb_user(userid, username,sex,subscribe,city,province,county,headimgurl,subscribe_time,remark,groupid,tagid_list,isaddbz,isfriendbz)"
				+ "values('"+bean.getOpenid()+"','"+bean.getNickname()+"'"
						+ ",'"+bean.getSex()+"','"+bean.getSubscribe()+"'"
								+ ",'"+bean.getCity()+"','"+bean.getProvince()+"','"+bean.getCountry()+"',"
										+ "'"+bean.getHeadimgurl()+"','"+bean.getSubscribe_time()+"','"+bean.getRemark()+"','"+bean.getGroupid()+"','"+bean.getTagid_list()+"','"+0+"'"
												+ ",'"+0+"')" ;
		ok = dao.insert(sql);
		return ok;
	}

}
