package sbu.edu;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.util.ParserException;

import sbu.edu.webpage.Reader;

/**
 * @author NhatTan This class is to find the sequence of links between 2
 *         website.
 */
public class LinkSequenceFinder {
	public static final String WIKIPEDIA = "en.wikipedia.org";

	/**
	 * @param args
	 * @throws ParserException 
	 */
	public static void main(String[] args) throws ParserException {
		String websiteLink1 = "https://en.wikipedia.org/wiki/Honey";
//		String websiteLink2 = "https://en.wikipedia.org/wiki/Abraham_Lincoln";
		String websiteLink2 = "https://en.wikipedia.org/wiki/Kitchen_utensil";
//		String websiteLink2 = "https://en.wikipedia.org/wiki/American_biscuit";
		
		List<String> linkSequence = new ArrayList<String>();
		List<String> linkDatabase = new ArrayList<String>();
		linkSequence.add(websiteLink1);
		linkDatabase.add(websiteLink1);
		searchWebsite(websiteLink1, 0, websiteLink2, 2, linkSequence,linkDatabase);
		System.out.println("DONE");
	}
	
	public static boolean isStop = false;

	public static List<String> searchWebsite(String inputLink, int currentLevel, String targetLink, int maxLevel,
			List<String> linkSequence, List<String> linkDatabase ) {
		List<String> result = null;
		
		if (currentLevel > maxLevel)
			return result;

		List<String> links;
		try {
			links = Reader.listDomainLinks(inputLink, LinkSequenceFinder.WIKIPEDIA);
		} catch (ParserException e) {
			return result;
		}
		
		if (links.contains(targetLink)){
			linkSequence.add(targetLink);
			result = linkSequence;
			isStop = true;
			System.out.println("We found the link sequence as follows.");
			for (int i = 0; i < linkSequence.size(); i++)
				System.out.println(linkSequence.get(i).toString());
			return result;
		}
			
			
		int size = links.size();
//		System.out.println(size + " links on " + inputLink);
		currentLevel++;
		for (int i = 0; i < size; i++){
			String link = links.get(i).toString();
//			if(!linkDatabase.contains(link)) {
				linkDatabase.add(link);
				linkSequence.add(link);			
				result = searchWebsite(link, currentLevel, targetLink, maxLevel, linkSequence, linkDatabase);
				linkSequence.remove(linkSequence.size()-1);
//			}
		}

		return result;
	}

}
