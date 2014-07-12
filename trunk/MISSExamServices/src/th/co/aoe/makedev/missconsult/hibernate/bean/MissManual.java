package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the MISS_MANUAL database table.
 * 
 */
@Entity
@Table(name="MISS_MANUAL")
public class MissManual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MM_ID")
	private Long mmId;

	@Column(name="MM_FILE_NAME")
	private String mmFileName;

	@Column(name="MM_HOTLINK")
	private String mmHotlink;

	@Column(name="MM_PATH")
	private String mmPath;

	//bi-directional many-to-one association to MissSery
    @ManyToOne
	@JoinColumn(name="MS_ID")
	private MissSery missSery;

    public MissManual() {
    }

	public Long getMmId() {
		return this.mmId;
	}

	public void setMmId(Long mmId) {
		this.mmId = mmId;
	}

	public String getMmHotlink() {
		return this.mmHotlink;
	}

	public void setMmHotlink(String mmHotlink) {
		this.mmHotlink = mmHotlink;
	}

	public String getMmPath() {
		return this.mmPath;
	}

	public void setMmPath(String mmPath) {
		this.mmPath = mmPath;
	}

	public MissSery getMissSery() {
		return this.missSery;
	}

	public void setMissSery(MissSery missSery) {
		this.missSery = missSery;
	}

	public String getMmFileName() {
		return mmFileName;
	}

	public void setMmFileName(String mmFileName) {
		this.mmFileName = mmFileName;
	}
	
}