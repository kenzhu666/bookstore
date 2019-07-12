package rest;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListWrapper {

	@XmlElement(name = "order")
	private LinkedList<Order> list;

	public ListWrapper() {
		list = new LinkedList<>();
	}

	public void add(Order order) {
		list.add(order);
	}

	public boolean has(int id) {
		if (list.isEmpty()) {
			return false;
		}
		return list.getLast().getId() == id;
	}
	
	public Order get(int id) {
		if (has(id)) {
			return list.getLast();
		}
		return null;
	}
}
