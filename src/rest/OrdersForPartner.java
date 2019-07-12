package rest;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.OrderDAO;
import dao.UserDAO;

@Path("orders")
public class OrdersForPartner {
	private ListWrapper lw;
	
	public OrdersForPartner() {
		try {
			lw = OrderDAO.getAllOrder();
		} catch (Exception e) {
			lw = new ListWrapper();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ListWrapper getOrdersByPartNumber(@QueryParam("partner") String partner) {
		try {
			int partnerNumber = Integer.parseInt(partner);
			if (UserDAO.isPartner(partnerNumber)) {
				return lw;
			}
			throw new ForbiddenException();
		} catch (Exception e) {
			throw new ForbiddenException();
		}
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public ListWrapper getOrdersByPartNumberJson(@QueryParam("partner") String partner) {
		return getOrdersByPartNumber(partner);
	}

}
