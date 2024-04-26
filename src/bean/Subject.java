package bean;
import java.io.Serializable;
public class Subject implements Serializable{
	/**
	 * 学校コード:String
	 */
	private String cd;
	/**
	 * 教員名:String
	 */
	private String name;
	/**
	 * School:School
	 */
	private School school;

	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public School getSchool(){
		return school;
	}
	public void setSchool(School school){
		this.school = school;
	}

}
