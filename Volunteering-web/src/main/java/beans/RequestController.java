package beans;

import java.util.Map;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
public class RequestController {
	 private String param1;
	

	    public String parametersAction(){
	        FacesContext fc = FacesContext.getCurrentInstance();
	        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
	        param1 = params.get("actionId");
	        return param1;
	    }

	    public String getParam1() {
	        return param1;
	    }

	  
}
