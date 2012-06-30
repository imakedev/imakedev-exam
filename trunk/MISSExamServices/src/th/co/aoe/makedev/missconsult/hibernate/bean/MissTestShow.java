package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_TEST_SHOW database table.
 * 
 */
@Entity
@Table(name="MISS_TEST_SHOW")
public class MissTestShow implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissTestShowPK id;


	@Column(name="MTS_VALUE")
	private String mtsValue;

	@Column(name="MTS_ORDER")
	private Long mtsOrder;
	
    public MissTestShow() {
    }

	public MissTestShowPK getId() {
		return this.id;
	}

	public void setId(MissTestShowPK id) {
		this.id = id;
	}
	

	public String getMtsValue() {
		return this.mtsValue;
	}

	public void setMtsValue(String mtsValue) {
		this.mtsValue = mtsValue;
	}

	public Long getMtsOrder() {
		return mtsOrder;
	}

	public void setMtsOrder(Long mtsOrder) {
		this.mtsOrder = mtsOrder;
	}

}