package dao;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao{
	private String baseSql = "select * from test where =?";

	public Test get(Student student, Subject subject, School school, int no) throws Exception{}

}
