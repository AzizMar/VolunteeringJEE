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

import org.apache.commons.logging.impl.AvalonLogger;

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
			actions.add(action);
				//System.out.println(actions);
		}
		return actions;
		
	
	}
	
	
}
