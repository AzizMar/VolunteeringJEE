package beans;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Aspnetusers;
@ManagedBean
@ViewScoped
public class VoluntaryDetailsBean {
	private Integer actionId;
	private Aspnetusers aspnetusers;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private int maxVolunteers;
	private int actionType;
	private String address;
	DateFormat date = new  SimpleDateFormat("yyyy-MM-dd");
	
	public VoluntaryDetailsBean() {
		super();
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		actionId = Integer.parseInt(request.getParameter("actionId"));

}
	
	@PostConstruct	
	public void init() {
		
		Response resp = ClientBuilder.newClient().target(
				"http://localhost:59875/api/Action/getAction/"+actionId)
				.request(MediaType.APPLICATION_JSON).get();
//
//				if(resp.getStatus()==404)
//				{
//					
//					try {
//						FacesContext.getCurrentInstance().getExternalContext().dispatch("/error.jsf");
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//								System.out.println("Error");
//					}
//				
//				if(resp.getStatus()==200) 
//				{
//					JsonObject actionJson = resp.readEntity(JsonObject.class);
//					name =actionJson.getString("Name");
//				//	address =actionJson.getString("Address");
//				//	description =actionJson.getString("Description");
//					//startDate =date.parse(actionJson.getString("StartDate"));
//				//	endDate =date.parse(actionJson.getString("EndDate"));
//				//	maxVolunteers =actionJson.getInt("MaxVolunteers");
//					//actionType =actionJson.getInt("ActionType");
//
//					
//				}
	
}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public Aspnetusers getAspnetusers() {
		return aspnetusers;
	}

	public void setAspnetusers(Aspnetusers aspnetusers) {
		this.aspnetusers = aspnetusers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getMaxVolunteers() {
		return maxVolunteers;
	}

	public void setMaxVolunteers(int maxVolunteers) {
		this.maxVolunteers = maxVolunteers;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DateFormat getDate() {
		return date;
	}

	public void setDate(DateFormat date) {
		this.date = date;
	}
	
	
	
}
