import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

//This class uses InvertedIndex functionality and uses HashMap to store the occurrences of words in the files.
public class Init_Search {
	public Map<Integer,String> sources;
    public HashMap<String, HashSet<Integer>> index;

	Init_Search(){
		prepareSearch();
	}

	//Populates the InvertedIndex database from files.
	public void prepareSearch() {
		sources = new HashMap<>(); 
        index = new HashMap<>();
		File directory = new File("WebPages");
		File files[] = directory.listFiles();

		for(int i =0; i<files.length;i++) {

			String[] words = getWordsFromFile(files[i]);
			sources.put(i,files[i].getName());
			createMap(words,i,files[i].getName());
		}
	}

	//Inserts provided value in the InvertedIndex database
	public void createMap(String[] words , int i, String fileName) {
		for(String word:words) {
			word = word.toLowerCase();
            if (!index.containsKey(word))
                index.put(word, new HashSet<>()); //new word
            index.get(word).add(i); //add new occurance
		}

	}

	//Searches the database for the given phrase
	public ArrayList<String> find(String phrase){
    	ArrayList<String> fileNames;
    	try {
    		phrase = phrase.toLowerCase();
    		fileNames = new ArrayList<>();
	        String[] words = phrase.split("\\W+");
	        String hashKey = words[0].toLowerCase();
	        if(index.get(hashKey) == null) {
	        	 System.out.println("Not found!!!");
	        	return null;
	        }
	        HashSet<Integer> res = new HashSet<>(index.get(hashKey));
	        for(String word: words){
	            res.retainAll(index.get(word));
	        }

	        if(res.size()==0) {
	            System.out.println("Not found!!!");
	            return null;
	        }
	        for(int num : res){
	        	fileNames.add(sources.get(num));
	        }
    	}catch(Exception e) {
    		 System.out.println("Not found!!!");
    		 System.out.println("Exception Found:" + e.getMessage());
    		 return null;
    	}
    return fileNames;
    }

	//Returns clean words from given file
	public static String[] getWordsFromFile(File f) {
		In in = new In(f);
		String text = in.readAll();
		text = text.replaceAll("[^a-zA-Z0-9\\s]", "");
		String[] words = text.split(" ");

		return words;

	}
}
