package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_SERY_ORDER database table.
 * 
 */
@Entity
@Table(name="MISS_SERY_ORDER")
public class MissSeryOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissSeryOrderPK id;

	@Column(name="MSO_AMOUNT")
	private Long msoAmount;

	@Column(name="MSO_WEEK")
	private Long msoWeek;

    public MissSeryOrder() {
    }

	public MissSeryOrderPK getId() {
		return this.id;
	}

	public void setId(MissSeryOrderPK id) {
		this.id = id;
	}
	
	public Long getMsoAmount() {
		return this.msoAmount;
	}

	public void setMsoAmount(Long msoAmount) {
		this.msoAmount = msoAmount;
	}

	public Long getMsoWeek() {
		return this.msoWeek;
	}

	public void setMsoWeek(Long msoWeek) {
		this.msoWeek = msoWeek;
	}

}