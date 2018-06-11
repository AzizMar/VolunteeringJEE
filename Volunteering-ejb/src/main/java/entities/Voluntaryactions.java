package entities;
// Generated Jun 6, 2018 5:08:14 AM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Voluntaryactions generated by hbm2java
 */
@Entity
@Table(name = "voluntaryactions", catalog = "vasp")
public class Voluntaryactions implements java.io.Serializable {

	private Integer actionId;
	private Aspnetusers aspnetusers;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private int maxVolunteers;
	private int actionType;
	private String address;
	private Set<Invitations> invitationses = new HashSet<Invitations>(0);
	private Set<Aspnetusers> aspnetuserses = new HashSet<Aspnetusers>(0);

	public Voluntaryactions() {
	}

	public Voluntaryactions(String name, String description, Date startDate, Date endDate, int maxVolunteers,
			int actionType, String address) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxVolunteers = maxVolunteers;
		this.actionType = actionType;
		this.address = address;
	}

	public Voluntaryactions(Aspnetusers aspnetusers, String name, String description, Date startDate, Date endDate,
			int maxVolunteers, int actionType, String address, Set<Invitations> invitationses,
			Set<Aspnetusers> aspnetuserses) {
		this.aspnetusers = aspnetusers;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxVolunteers = maxVolunteers;
		this.actionType = actionType;
		this.address = address;
		this.invitationses = invitationses;
		this.aspnetuserses = aspnetuserses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ActionId", unique = true, nullable = false)
	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CreatorNgo_Id")
	public Aspnetusers getAspnetusers() {
		return this.aspnetusers;
	}

	public void setAspnetusers(Aspnetusers aspnetusers) {
		this.aspnetusers = aspnetusers;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "StartDate", nullable = false, length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndDate", nullable = false, length = 19)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "MaxVolunteers", nullable = false)
	public int getMaxVolunteers() {
		return this.maxVolunteers;
	}

	public void setMaxVolunteers(int maxVolunteers) {
		this.maxVolunteers = maxVolunteers;
	}

	@Column(name = "ActionType", nullable = false)
	public int getActionType() {
		return this.actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	@Column(name = "Address", nullable = false)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "voluntaryactions")
	public Set<Invitations> getInvitationses() {
		return this.invitationses;
	}

	public void setInvitationses(Set<Invitations> invitationses) {
		this.invitationses = invitationses;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "voluntaryactionvolunteers", catalog = "vasp", joinColumns = {
			@JoinColumn(name = "VoluntaryAction_ActionId", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Volunteer_Id", nullable = false, updatable = false) })
	public Set<Aspnetusers> getAspnetuserses() {
		return this.aspnetuserses;
	}

	public void setAspnetuserses(Set<Aspnetusers> aspnetuserses) {
		this.aspnetuserses = aspnetuserses;
	}

}
