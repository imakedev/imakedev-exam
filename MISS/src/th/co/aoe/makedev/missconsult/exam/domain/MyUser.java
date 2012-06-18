package th.co.aoe.makedev.missconsult.exam.domain;

import java.io.Serializable;

public class MyUser implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public MyUser(String fullName) {
		super();
		this.fullName = fullName;
	}

	private String fullName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
