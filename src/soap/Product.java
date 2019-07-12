package soap;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import bean.Book;
import dao.BookDAO;

public class Product {
	public String getProductInfo(String productId) {
		try {
			Book book = BookDAO.getBookByID(productId);
			JAXBContext jc = JAXBContext.newInstance(book.getClass());
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			sw.write("\n");
			marshaller.marshal(book, new StreamResult(sw));
			return sw.toString();
		} catch (Exception e) {
			return "\nNo record with bid=" + productId;
		}
	}
}
