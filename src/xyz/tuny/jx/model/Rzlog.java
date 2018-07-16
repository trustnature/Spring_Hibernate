package xyz.tuny.jx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="rz_log")
public class Rzlog implements Serializable {

	@Id
	@GeneratedValue
    private Long id;
	
    private String qymc;
    private String qysh;
    private String sqm;
    private String operatime;

    public Rzlog() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "qymc", length = 150)
    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    @Column(name = "qysh", length = 40)
    public String getQysh() {
		return qysh;
	}

	public void setQysh(String qysh) {
		this.qysh = qysh;
	}

	public String getSqm() {
		return sqm;
	}
    @Column(name = "sqm", length = 60)
	public void setSqm(String sqm) {
		this.sqm = sqm;
	}

	@Column(name = "opttime", length = 60)
	public String getOperatime() {
		return operatime;
	}

	public void setOperatime(String operatime) {
		this.operatime = operatime;
	}

  
}