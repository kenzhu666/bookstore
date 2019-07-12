package util;

public class StarUtil {
	public static String getRateStar(double rate) {
		String result = "";
		String star = "<span class=\"fa fa-star checked\"></span> ";
		String empty = "<span class=\"fa fa-star-o checked\"></span> ";
		String half = "<span class=\"fa fa-star-half-empty checked\"></span> ";
		for (int i = 1; i < 6; i++) {
			if (i <= rate) {
				result += star;
			} else {
				if (i - 0.5 <= rate)
					result += half;
				else
					result += empty;
			}
		}
		return result;
	}
	
	public static String getRateStar(String s) {
		Double rate = Double.parseDouble(s);
		return getRateStar(rate);
	}
}
