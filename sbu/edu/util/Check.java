package sbu.edu.util;

public class Check {
	public static boolean isInDomain(String url, String domain){
		if (url.contains(domain))
			return true;
		return false;
	}
}
