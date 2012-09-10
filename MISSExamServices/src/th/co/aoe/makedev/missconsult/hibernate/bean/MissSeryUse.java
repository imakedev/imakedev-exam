package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_SERY_USE database table.
 * 
 */
@Entity
@Table(name="MISS_SERY_USE")
public class MissSeryUse implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissSeryUsePK id;

	@Column(name="MSU_WEEK")
	private Long msuWeek;

    public MissSeryUse() {
    }

	public MissSeryUsePK getId() {
		return this.id;
	}

	public void setId(MissSeryUsePK id) {
		this.id = id;
	}
	
	public Long getMsuWeek() {
		return this.msuWeek;
	}

	public void setMsuWeek(Long msuWeek) {
		this.msuWeek = msuWeek;
	}

}