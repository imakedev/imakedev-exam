package th.co.aoe.makedev.missconsult.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MISS_EPT_MESSAGE_CONFIG database table.
 * 
 */
@Entity
@Table(name="MISS_EPT_MESSAGE_CONFIG")
public class MissEptMessageConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MissEptMessageConfigPK id;

	@Column(name="MEMC_DESC")
	private String memcDesc;

	@Column(name="MEMC_KEY")
	private String memcKey;

	@Lob
	@Column(name="MEMC_MESSAGE")
	private String memcMessage;

	public MissEptMessageConfig() {
	}

	public MissEptMessageConfigPK getId() {
		return this.id;
	}

	public void setId(MissEptMessageConfigPK id) {
		this.id = id;
	}

	public String getMemcDesc() {
		return this.memcDesc;
	}

	public void setMemcDesc(String memcDesc) {
		this.memcDesc = memcDesc;
	}

	public String getMemcKey() {
		return this.memcKey;
	}

	public void setMemcKey(String memcKey) {
		this.memcKey = memcKey;
	}

	public String getMemcMessage() {
		return this.memcMessage;
	}

	public void setMemcMessage(String memcMessage) {
		this.memcMessage = memcMessage;
	}

}