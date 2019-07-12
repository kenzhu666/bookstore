package ctrl;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class Controller {

	protected void addHint(String summary) {
		addHint(summary, null);
	}

	protected void addHint(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void addError(String summary) {
		addError(summary, null);
	}

	protected void addError(String summary, String detail) {
		addError(null, summary, detail);
	}

	protected void addError(String id, String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		FacesContext.getCurrentInstance().addMessage(id, message);
	}

	protected void addErrorMessage(Exception e) {
		FacesMessage message = new FacesMessage("Error: " + e.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void addErrorMessage(String clientID, String msg) {
		FacesMessage message = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage(clientID, message);
	}

	protected ExternalContext getContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	protected HttpSession getSession() {
		return (HttpSession) getContext().getSession(false);
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) getContext().getRequest();
	}

	protected Map<String, Object> getRequestAttr() {
		return getContext().getRequestMap();
	}

	protected Map<String, String> getRequestParam() {
		return getContext().getRequestParameterMap();
	}

	public static String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return "";
		}
		String words[] = str.split("\\s");
		String capitalizeWord = "";
		for (String w : words) {
			String first = w.substring(0, 1);
			String afterfirst = w.substring(1);
			capitalizeWord += first.toUpperCase() + afterfirst.toLowerCase() + " ";
		}
		return capitalizeWord.trim();
	}
}
