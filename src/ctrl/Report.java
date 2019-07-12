package ctrl;

import java.util.List;
import java.util.Map;

import bean.POItem;
import bean.User;
import dao.OrderDAO;

public class Report extends Controller {
	private User user;
	private Map<String, List<POItem>> report;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, List<POItem>> getReport() {
		return report;
	}

	public void setReport(Map<String, List<POItem>> report) {
		this.report = report;
	}

	public boolean isAdmin() {
		return user != null && user.getType().equals("ADMIN");
	}

	public String validCall() {
		if (isAdmin()) {
			try {
				report = OrderDAO.getMonthReport();
			} catch (Exception e) {
				addError("Cannot retrieve monthly report!");
			}
			return null;
		}
		return "index?faces-redirect=true";
	}

	public String gotoReport() throws Exception {
		if (isAdmin()) {
			return "report?faces-redirect=true";
		} else {
			addError("Forbidden", "Sorry, you are not an admin!");
			return null;
		}
	}
}
