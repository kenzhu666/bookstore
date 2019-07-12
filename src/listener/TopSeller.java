package listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

import bean.POItem;
import dao.OrderDAO;

/**
 * Application Lifecycle Listener implementation class TopSeller
 *
 */
@WebListener
public class TopSeller implements ServletContextListener, ServletRequestAttributeListener {
	private List<POItem> top;

	/**
	 * Default constructor.
	 */
	public TopSeller() {

	}

	/**
	 * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
	 */
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		handleEvent(arg0);
	}

	/**
	 * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
	 */
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		handleEvent(arg0);
	}

	/**
	 * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
	 */
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		handleEvent(arg0);
	}

	private void handleEvent(ServletRequestAttributeEvent event) {
		if (event.getName().equals("transaction")) {
			if ((boolean)(event.getServletRequest().getAttribute("transaction"))) {
				update();
				event.getServletContext().setAttribute("top", top);
			}
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		update();
		arg0.getServletContext().setAttribute("top", top);
	}

	private void update() {
		try {
			top = OrderDAO.getTopSeller().subList(0, 5);
		} catch (Exception e) {
			top = new ArrayList<>();
		}
	}

}
