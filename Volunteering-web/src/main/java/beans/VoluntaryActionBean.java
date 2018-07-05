package beans;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.apache.commons.logging.impl.AvalonLogger;

import entities.Aspnetusers;
import entities.Voluntaryactions;


@ManagedBean(name="actionBean")
@ApplicationScoped
public class VoluntaryActionBean {
	private Integer actionId;
	private Aspnetusers aspnetusers;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private int maxVolunteers;
	private int actionType;
	private String address;
	DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
	

	
	public Voluntaryactions getActionById(int id) {

		Response response = ClientBuilder.newClient()
				.target("http://localhost:59875/api/Action/getAction/"+id)
				.request(MediaType.APPLICATION_JSON).get();

		JsonObject actionJson = response.readEntity(JsonObject.class);
		Voluntaryactions action = new Voluntaryactions();
		action.setName(actionJson.getString("Name"));
		action.setAddress(actionJson.getString("Address"));
		
		
		return action;
		
		
	}
	

	public Voluntaryactions getAllActions() {

		Response response = ClientBuilder.newClient()
				.target("http://localhost:59875/api/Action/getActions/")
				.request(MediaType.APPLICATION_JSON).get();
		
		JsonObject actionJson = response.readEntity(JsonObject.class);
		
		Voluntaryactions action = new Voluntaryactions();
	
		action.setName(actionJson.getString("Name"));
		action.setAddress(actionJson.getString("Address"));
		return action;
	}
	
	
	public List<Voluntaryactions> getRecentActions() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Response response = ClientBuilder.newClient()
				.target("http://localhost:59875/api/Action/getActions/")
				.request(MediaType.APPLICATION_JSON).get();
		List<Voluntaryactions> actions = new ArrayList<Voluntaryactions>();
		
		JsonArray actionJson = response.readEntity(JsonArray.class);
			for(int i=0;i<actionJson.size();i++){                        
				Voluntaryactions action =  new Voluntaryactions();
			//JsonObject e= actionJson.getJsonObject(i);
			action.setActionId(actionJson.getJsonObject(i).getInt("ActionId"));
			action.setName(actionJson.getJsonObject(i).getString("Name"));
			action.setAddress(actionJson.getJsonObject(i).getString("Address"));
			action.setDescription(actionJson.getJsonObject(i).getString("Description"));
			action.setMaxVolunteers(actionJson.getJsonObject(i).getInt("MaxVolunteers"));
			action.setActionType(actionJson.getJsonObject(i).getInt("ActionType"));
			String string = actionJson.getJsonObject(i).getString("StartDate");
			String [] parts = string.split("T");
			System.out.println(parts[0]);
			
			DateFormat date = new  SimpleDateFormat("yyyy-MM-dd");

			try {
				
				action.setStartDate(date.parse(actionJson.getJsonObject(i).getString("StartDate")));
				action.setEndDate(date.parse(actionJson.getJsonObject(i).getString("EndDate")));
				System.out.println(action.getStartDate());
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			actions.add(action);
				//System.out.println(actions);
		}
		return actions;
		
	
	}
	
	public String ConvertActionType(int index)
	{
		String type="";
		switch (index) {
		case 0:
			type="Social";
			break;
		case 1:
			type= "Artistic";	
			break;
		case 2:
			type= "Enviromental";
			break;
		case 3:
			type="Solidarity";
			break;
		case 4:
			type= "Other";
			break;
		}
		
		return type;
	}
	
	public String ConvertDate(String date)
	{
		String p = date.replace("00:00:00 WAT","");
		
		return p;
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


	public DateFormat getFormat() {
		return format;
	}


	public void setFormat(DateFormat format) {
		this.format = format;
	}


}
