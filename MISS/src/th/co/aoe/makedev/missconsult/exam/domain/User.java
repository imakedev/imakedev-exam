package th.co.aoe.makedev.missconsult.exam.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="user")
public class User {
	
	@Id
	private Long id;
	
	private String firstName;
	private String lastName;
	
	@Column(unique=true)
	private String username;
	private String password;
	
	/*@OneToOne(mappedBy="user", cascade={CascadeType.ALL})
	private Role role;
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}*/
}
