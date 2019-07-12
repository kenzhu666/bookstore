package rest;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import bean.Book;
import dao.BookDAO;

@Path("product")
public class ProductInquiry {
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Book getBook(@QueryParam("id") String bid) {
		try {
			return BookDAO.getBookByID(bid);
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBookJson(@QueryParam("id") String bid) {
		return getBook(bid);
	}
}
