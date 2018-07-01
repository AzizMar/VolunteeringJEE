package beans;

import javax.faces.bean.ManagedBean;
import javax.json.JsonObject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Aspnetusers;

@ManagedBean
public class UserBean {

	// @EJB
	// public UserService us;

	public Aspnetusers getUserById(String id) {

		Response response = ClientBuilder.newClient()
				.target("http://localhost:59875/api/account/getuser/" + id)
				.request(MediaType.APPLICATION_JSON).get();

		JsonObject userJson = response.readEntity(JsonObject.class);
		Aspnetusers aspUser = new Aspnetusers();
		aspUser.setUserName(userJson.getString("Name"));

		System.out.println("AspUser Name: " + aspUser.getUserName());

		return aspUser;
	}

	public String getAppUserId() {

		Response response = ClientBuilder.newClient()
				.target("http://localhost:59875/api/account/GetUserId")
				.request(MediaType.APPLICATION_JSON).get();

		String userId = response.readEntity(String.class);

		return userId;

		// if (!userId.equals("UserIdNotFound")) {
		// return userId;
		// }
		//
		// return ("UserIdNotFound");
	}

	
	public String userLogin(String email, String password) {

		Response resp = ClientBuilder.newClient().target(
				"http://localhost:59875/api/account/authenticate/"+email+"/"+password)
				.request(MediaType.APPLICATION_JSON).get();

		return (resp.readEntity(String.class));
	}
	
	

	public Boolean isUserAuthenticated() {

		Response resp = ClientBuilder.newClient()
				.target("http://localhost:59875/api/account/isUserAuth")
				.request(MediaType.APPLICATION_JSON).get();

		return resp.readEntity(Boolean.class);
	}

	public String getUserNameById(String id) {

		return getUserById(id).getUserName();
	}

	public void testValues() {

		System.out.println("Is USER Auth?:");
		System.out.println(isUserAuthenticated());
		
		System.out.println("USER LOGIN:");
		System.out.println(userLogin("azizxmar@gmail.com", "a123456"));

		System.out.println("Is USER Auth?:");
		System.out.println(isUserAuthenticated());

		System.out.println("USER ID:");
		System.out.println(getAppUserId());
		

	}
}
