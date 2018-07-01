package test;

import javax.faces.bean.ManagedBean;
import javax.json.JsonObject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Aspnetusers;

@ManagedBean
public class TestRestApi {

	@SuppressWarnings("deprecation")
	public String printUser() {
		// ClientRequest asp1 = new
		// ClientRequest("http://localhost:59875/api/account/getuser/{id}");
		// asp1.pathParameter("id", "899c0db5-fb5e-48f1-9c46-e4970fedbcc5");
		//
		// ClientResponse<String> r1 = null;
		// try {
		//
		// r1 = asp1.get(String.class);
		// System.out.println("USER: " + r1.getEntity();
		//
		// } catch (Exception e) {
		//
		// System.out.println("CATCHED");
		// }

		Response response = ClientBuilder.newClient()
				.target("http://localhost:59875/api/account/getuser/ac549e29-6de6-4514-8717-ff7844a40a63")
				.request(MediaType.APPLICATION_JSON).get();

		JsonObject userJson = response.readEntity(JsonObject.class);

		System.out.println("USER HERE");
		System.out.println(userJson);

		Aspnetusers aspUser = new Aspnetusers();
		aspUser.setUserName(userJson.getString("Name"));

		System.out.println("AspUser Name: " + aspUser.getUserName());

		return "user";
	}

}
