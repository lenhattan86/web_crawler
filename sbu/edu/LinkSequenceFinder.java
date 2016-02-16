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
		linkSequence.add(websiteLink1);
		searchWebsite(websiteLink1, 0, websiteLink2, 2, linkSequence);
		System.out.println("DONE");
	}

	public static List<String> searchWebsite(String inputLink, int currentLevel, String targetLink, int maxLevel,
			List<String> linkSequence) {
		List<String> result = null;
		if (currentLevel > maxLevel)
			return result;

		if (inputLink.equals(targetLink)) {
			result = linkSequence;
			System.out.println("We found the link sequence as follows.");
			for (int i = 0; i < linkSequence.size(); i++)
				System.out.println(linkSequence.get(i).toString());
		} else {
			List<String> links;
			try {
				links = Reader.listDomainLinks(inputLink, LinkSequenceFinder.WIKIPEDIA);
			} catch (ParserException e) {
				return result;
			}
			int size = links.size();
			currentLevel++;
			for (int i = 0; i < size; i++){
				String link = links.get(i).toString();
				linkSequence.add(link);
				result = searchWebsite(link, currentLevel, targetLink, maxLevel, linkSequence);
				linkSequence.remove(linkSequence.size()-1);
			}
		}

		return result;
	}

}
