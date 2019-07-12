package rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date>{
	private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String marshal(Date date) throws Exception {
		return df.format(date);
	}

	@Override
	public Date unmarshal(String xml) throws Exception {
		return df.parse(xml);
	}
	
	
}
