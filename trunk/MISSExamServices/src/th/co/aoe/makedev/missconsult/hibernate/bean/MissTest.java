package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_TEST database table.
 * 
 */
@Entity
@Table(name="MISS_TEST")
public class MissTest implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MTEST_ID")
	private Long mtestId;*/
	@EmbeddedId
	private MissTestPK id;
	
	@Column(name="MTEST_ANS")
	private String mtestAns;

	@Column(name="MTEST_SET")
	private String mtestSet;

	@Column(name="MTEST_STATUS")
	private String mtestStatus;

	

    public MissTest() {
    }

/*	public Long getMtestId() {
		return this.mtestId;
	}

	public void setMtestId(Long mtestId) {
		this.mtestId = mtestId;
	}
*/
	public String getMtestAns() {
		return this.mtestAns;
	}

	public void setMtestAns(String mtestAns) {
		this.mtestAns = mtestAns;
	}

	public String getMtestSet() {
		return this.mtestSet;
	}

	public void setMtestSet(String mtestSet) {
		this.mtestSet = mtestSet;
	}

	public String getMtestStatus() {
		return this.mtestStatus;
	}

	public void setMtestStatus(String mtestStatus) {
		this.mtestStatus = mtestStatus;
	}

	public MissTestPK getId() {
		return id;
	}

	public void setId(MissTestPK id) {
		this.id = id;
	}
	
}