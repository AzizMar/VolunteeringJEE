package beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ManagedBean
@ViewScoped
public class ProfileBean {

	private String id;
	 private String name;
	 private String email;
	 private String phoneNumber ;
	 private String image = "https://www.w3schools.com/w3images/avatar2.png";
	 private String role;
	


public ProfileBean() {
		super();
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		id = request.getParameter("id");

}





@PostConstruct	
public void init() {
	
	Response resp = ClientBuilder.newClient().target(
			"http://localhost:59875/api/account/GetUser/"+id)
			.request(MediaType.APPLICATION_JSON).get();

			if(resp.getStatus()==404)
			{
				
				try {
					FacesContext.getCurrentInstance().getExternalContext().dispatch("/error.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							System.out.println("Error");
				}
			
			if(resp.getStatus()==200) 
			{
				JsonObject userJson = resp.readEntity(JsonObject.class);
				name =userJson.getString("Name");
				email =userJson.getString("Email");
				if(userJson.isNull("PhoneNumber")) {
					phoneNumber = "NA";
				}
				role =userJson.getString("Role");
				
			}
			

			
}




	
	  //===========================//
	 //==== Getters & Setters ====//
	//===========================//
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	



	
	
}
