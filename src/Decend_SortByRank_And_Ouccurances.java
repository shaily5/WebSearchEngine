import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Decend_SortByRank_And_Ouccurances {
	//Sorts values of given HashMap in descending order
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static HashMap<String,Integer> sortValuesInverse(HashMap<String,Integer> map){
		List list = new LinkedList(map.entrySet());
		//Custom Comparator
		Collections.sort(list, new Comparator(){@Override
		public int compare(Object o1, Object o2){return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());}});
		//copying the sorted list in HashMap to preserve the iteration order
		HashMap<String,Integer> sortedHashMap = new LinkedHashMap<>();
		for (Iterator it = list.iterator(); it.hasNext();)
		{
		 Map.Entry<String,Integer> entry = (Map.Entry<String,Integer>) it.next();
		sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}
	//sorts values of given HashMap in ascending order
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap<String,Integer> sortValues(HashMap<String,Integer> map){
		List list = new LinkedList(map.entrySet());
		//Custom Comparator
		Collections.sort(list, new Comparator(){@Override
		public int compare(Object o1, Object o2){return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());}});
		//copying the sorted list in HashMap to preserve the iteration order
		HashMap<String,Integer> sortedHashMap = new LinkedHashMap<>();
		for (Iterator it = list.iterator(); it.hasNext();){
			 Map.Entry<String,Integer> entry = (Map.Entry<String,Integer>) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

	//Sorts search output in Descending Rank. ArrayList contains list of files that have the given phrase.
	public static Map<String,Integer> sortByRank(ArrayList<String> as, String phrase) {
		HashMap<String,Integer> wordCount = new HashMap<>();

		for(String fileName : as) {
			String[] words = Init_Search.getWordsFromFile(new File("WebPages/"+fileName));
			for(String word:words) {
				if(word.toLowerCase().equals(phrase.split("\\W+")[0])) {
					if(wordCount.containsKey(fileName)) {
						wordCount.put(fileName, wordCount.get(fileName)+1);
					}
					else {
						wordCount.put(fileName, 1);
					}
				}
			}
		}
		Map<String,Integer> sortedMap = sortValuesInverse(wordCount);
		return sortedMap;
	}
}
