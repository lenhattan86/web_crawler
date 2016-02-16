package sbu.edu.webpage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import sbu.edu.util.Check;


/**
 * @author NhatTan
 * This class is to generate the all the http links on a website based on a defined condition.
 */
public class Reader {
	
	/**
	 * @param url
	 * @param domain
	 * @return
	 */
	public static List<String> listDomainLinks(String url, String domain) throws ParserException{
		List<String> linkList = new ArrayList<String>();
		// list all links.
		List<String> allLinkList = getLinksOnPage(url);
		// filter out the out-of-domain links.
		for (int i = 0; i<allLinkList.size(); i++){
			String link = allLinkList.get(i);
			if(Check.isInDomain(link, domain)){
				linkList.add(link);
			}
		}
		return linkList;
	}
	
	public static List<String> getLinksOnPage(final String url) throws ParserException {
	    final Parser htmlParser = new Parser(url);
	    final List<String> result = new LinkedList<String>();

	    try {
	        final NodeList tagNodeList = htmlParser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
	        for (int j = 0; j < tagNodeList.size(); j++) {
	            final LinkTag loopLink = (LinkTag) tagNodeList.elementAt(j);
	            if(loopLink.isHTTPLikeLink()){
	            	final String loopLinkStr = loopLink.getLink();
	            	if((!loopLinkStr.contains(".jpg"))
	            		&&(!loopLinkStr.contains(url+"#")) ){
		            	result.add(loopLinkStr);
	            	}
	            }
	        }
	    } catch (ParserException e) {
	        e.printStackTrace(); // TODO handle error
	    }

	    return result;
	}
}
