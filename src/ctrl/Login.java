package ctrl;

import javax.faces.context.FacesContext;

import bean.User;
import dao.UserDAO;

public class Login extends Controller {
	private String username;
	private String password;
	private boolean loggedIn;
	private String pageFrom;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(String pageFrom) {
		this.pageFrom = pageFrom;
	}

	public String gotoLogin() {
		pageFrom = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		return "login?faces-redirect=true";
	}

	public String redirectToLogin() {
		if (!loggedIn) {
			return gotoLogin();
		}
		return null;
	}

	public String login() {
		try {
			loggedIn = UserDAO.validate(username, password);
			if (loggedIn) {
				User user = UserDAO.getUserByUsername(username);
				getSession().setAttribute("user", user);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				addHint("Login Successful", "Welcome " + user.getFullName());
				return (pageFrom == null ? "index" : pageFrom) + "?faces-redirect=true";
			} else {
				addError("Login Failed", "The password is incorrect!");
			}
		} catch (Exception e) {
			addErrorMessage(e);
		}
		return null;
	}

	public String logout() {
		loggedIn = false;
		username = null;
		password = null;
		getSession().setAttribute("user", null);
		return "index?faces-redirect=true";
	}

}
