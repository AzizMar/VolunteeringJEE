package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.json.JsonObject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.Email;

import entities.Aspnetusers;

@ManagedBean
@SessionScoped
public class UserBean {


 private String Id = null ;
 private String Name;
 private String Email;
 private String PhoneNumber ="7589652";
 private String Image = "https://www.w3schools.com/w3images/avatar2.png";
 private String Role;

private String inputPassword;
@Email
private String inputEmail;

private String loginMsg="";
	
	public Aspnetusers getUserById(String id) {

		Response response = ClientBuilder.newClient()
				.target("http://localhost:59875/api/account/getuser/"+id)
				.request(MediaType.APPLICATION_JSON).get();

		JsonObject userJson = response.readEntity(JsonObject.class);
		Aspnetusers aspUser = new Aspnetusers();
		aspUser.setUserName(userJson.getString("Name"));
		aspUser.setEmail(userJson.getString("Email"));

		System.out.println("AspUser Name: " + aspUser.getUserName());
		System.out.println("AspUser Email: " + aspUser.getEmail());

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
				"http://localhost:59875/api/account/login/"+email+"/"+password)
				.request(MediaType.APPLICATION_JSON).get();

		return (resp.readEntity(String.class));
	}
	
	

	public Boolean isUserAuthenticated() {

		Response resp = ClientBuilder.newClient()
				.target("http://localhost:59875/api/account/isUserAuth")
				.request(MediaType.APPLICATION_JSON)
				.header("auth","azizxmar@gmail.com:a123456")
				.get();

		return resp.readEntity(Boolean.class);
	}

	public String getUserNameById(String id) {

		return getUserById(id).getUserName();
	}
	
	
	
	public String SignIn() {

		String email = inputEmail;
		String password = inputPassword;
		Response resp = ClientBuilder.newClient().target(
				"http://localhost:59875/api/account/login/"+email+"/"+password)
				.request(MediaType.APPLICATION_JSON).get();
				JsonObject userJson = resp.readEntity(JsonObject.class);
				Id =userJson.getString("Id");

	    		
        if (!Id.equals("empty"))  {
        	
    		Name =userJson.getString("Name");
    		Email =userJson.getString("Email");
    		Role =userJson.getString("Role");
        	
        	printUserInfo();
        	//loginMsg ="Already Logged In";
    		return "/index?faces-redirect=true";
		}
		
        printUserInfo();
    	loginMsg ="Failed Login attempt";
		System.out.println("Failed login attempt");
		return "login";
	}
	
	
	
	public String signOut(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//		try {
//			FacesContext.getCurrentInstance().getExternalContext().dispatch("index.jsf");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "/index?faces-redirect=true";
	}
	

	
	
	
	//=====================
	//====== Test methods
	//=====================
	 

	public void printUserInfo() {
		
		System.out.println(Id);
		System.out.println(Name);
		System.out.println(Email);
		System.out.println(PhoneNumber);
		System.out.println(Image);
		System.out.println(Role);
	}
	
	public void testValues() {
		
		System.out.println("Is USER Auth?:");
		System.out.println(isUserAuthenticated());
		
		System.out.println("USER LOGIN:");
		System.out.println(userLogin("azizxmar@gmail.comss", "a123456"));

		System.out.println("Is USER Auth?:");
		System.out.println(isUserAuthenticated());

		System.out.println("USER ID:");
		System.out.println(getAppUserId());
		
		//getUserById("c892ad9e-d71b-425b-9a7b-1f1c856db05f");

	}
	
	
	//===================================//
	//======== GETTERS & SETTERS  ======//
	//=================================//
	 public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	public String getInputEmail() {
		return inputEmail;
	}

	public void setInputEmail(String inputEmail) {
		this.inputEmail = inputEmail;
	}

	public String getLoginMsg() {
		return loginMsg;
	}

	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}
	
	
}
