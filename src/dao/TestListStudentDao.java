package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{
	private String baseSql = "select * from student where school_cd=?";

	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception{
		List<TestListStudent> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try{
			while (rSet.next()){
				TestListStudent testliststudent = new TestListStudent();
				testliststudent.setSubjectName(rSet.getString("subjectName"));
				testliststudent.setSubjectCd(rSet.getString("subjectCd"));
				testliststudent.setNum(rSet.getInt("num"));
				testliststudent.setPoint(rSet.getInt("point"));

				list.add(testliststudent);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	private List<TestListStudent> filter(Student student) throws Exception{
		List<TestListStudent> list = new ArrayList<>();
		try {
			while (student.next()){
			TestListStudent testliststudent = new TestListStudent();
			testliststudent.setStudent(student);
			list.add(testliststudent);
	}

	try {
		statement = connection.prepareStatement
	}
}