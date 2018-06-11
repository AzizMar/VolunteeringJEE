package entities;
// Generated Jun 6, 2018 5:08:14 AM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Invitations generated by hbm2java
 */
@Entity
@Table(name = "invitations", catalog = "vasp")
public class Invitations implements java.io.Serializable {

	private Integer invitationId;
	private Aspnetusers aspnetusers;
	private Voluntaryactions voluntaryactions;
	private int status;

	public Invitations() {
	}

	public Invitations(int status) {
		this.status = status;
	}

	public Invitations(Aspnetusers aspnetusers, Voluntaryactions voluntaryactions, int status) {
		this.aspnetusers = aspnetusers;
		this.voluntaryactions = voluntaryactions;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "InvitationId", unique = true, nullable = false)
	public Integer getInvitationId() {
		return this.invitationId;
	}

	public void setInvitationId(Integer invitationId) {
		this.invitationId = invitationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Volunteer_Id")
	public Aspnetusers getAspnetusers() {
		return this.aspnetusers;
	}

	public void setAspnetusers(Aspnetusers aspnetusers) {
		this.aspnetusers = aspnetusers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Action_ActionId")
	public Voluntaryactions getVoluntaryactions() {
		return this.voluntaryactions;
	}

	public void setVoluntaryactions(Voluntaryactions voluntaryactions) {
		this.voluntaryactions = voluntaryactions;
	}

	@Column(name = "Status", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
