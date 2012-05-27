package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_TEMPLATE database table.
 * 
 */
@Entity
@Table(name="MISS_TEMPLATE")
public class MissTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MT_ID")
	private Long mtId;

	@Column(name="MT_NAME")
	private String mtName;

	@Column(name="MT_TEMPLATE")
	private String mtTemplate;

	/*//bi-directional many-to-one association to MissQuestion
	@OneToMany(mappedBy="missTemplate")
	private Set<MissQuestion> missQuestions;*/

    public MissTemplate() {
    }

	public Long getMtId() {
		return this.mtId;
	}

	public void setMtId(Long mtId) {
		this.mtId = mtId;
	}

	public String getMtName() {
		return this.mtName;
	}

	public void setMtName(String mtName) {
		this.mtName = mtName;
	}

	public String getMtTemplate() {
		return this.mtTemplate;
	}

	public void setMtTemplate(String mtTemplate) {
		this.mtTemplate = mtTemplate;
	}

	/*public Set<MissQuestion> getMissQuestions() {
		return this.missQuestions;
	}

	public void setMissQuestions(Set<MissQuestion> missQuestions) {
		this.missQuestions = missQuestions;
	}*/
	
}