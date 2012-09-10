package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_REACTIVE_LOG database table.
 * 
 */
@Entity
@Table(name="MISS_REACTIVE_LOG")
public class MissReactiveLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissReactiveLogPK id;

	@Column(name="MRL_WEEK")
	private Long mrlWeek;

    public MissReactiveLog() {
    }

	public MissReactiveLogPK getId() {
		return this.id;
	}

	public void setId(MissReactiveLogPK id) {
		this.id = id;
	}
	
	public Long getMrlWeek() {
		return this.mrlWeek;
	}

	public void setMrlWeek(Long mrlWeek) {
		this.mrlWeek = mrlWeek;
	}

}