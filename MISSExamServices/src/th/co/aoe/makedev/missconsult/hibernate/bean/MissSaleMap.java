package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_SALE_MAP database table.
 * 
 */
@Entity
@Table(name="MISS_SALE_MAP")
public class MissSaleMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissSaleMapPK id;

    public MissSaleMap() {
    }

	public MissSaleMapPK getId() {
		return this.id;
	}

	public void setId(MissSaleMapPK id) {
		this.id = id;
	}
	
}