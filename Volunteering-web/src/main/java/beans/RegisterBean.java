package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.Email;

@ManagedBean
@ViewScoped
public class RegisterBean {

	
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;

	@Email
	private String email;
	@Size(min=2, max=26)
	@NotNull
	private String name;
	@Size(min=2, max=26)
	private String password;
	@Size(min=6, max=26)
	private String passwordConfirmation;
	private String accountType="Volunteer";
	private String registerResult="";
	
	
	public String register(){
		
		if (password.equals(passwordConfirmation)) {
			
			Response resp = ClientBuilder.newClient().target(
					"http://localhost:59875/api/account/register/"+name+"/"+email+"/"+password+"/"+accountType)
					.request(MediaType.APPLICATION_JSON).get();
					String registerResult = resp.readEntity(String.class);
					System.out.println(registerResult);
					System.out.println(userBean.getInputEmail());
					
					userBean.setInputEmail(email);
					userBean.setInputPassword(password);
					userBean.SignIn();
					
					return "/index?faces-redirect=true";

		}
		else {
			
			registerResult="Password mismatch";
			return "/register?faces-redirect=true";
		}
	
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}


	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public String getRegisterResult() {
		return registerResult;
	}


	public void setRegisterResult(String registerResult) {
		this.registerResult = registerResult;
	}
	
	
	public UserBean getUserBean() {
		return userBean;
	}


	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	
	
}
