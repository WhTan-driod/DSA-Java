import java.util.*;

//Class to count the occurrences of each word in a given text using HashMap
public class HashMapTextCounter
{
    //Static method to count words in the provided text
    public static Map<String, Integer> countWords(String text) 
    {
        //initialize a HashMap to store word counts
        Map<String, Integer> wordCountMap = new HashMap<>();

        //intialize a String array to hold words after splitting the text
        String[] words = text.toLowerCase().split("\\W+");
    
        //Iterate through each word in the array
        for (String word : words) 
        {
            if (!word.isEmpty()) 
            {
                //Update the word count in the HashMap
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        }
        
        return wordCountMap;
    }
    public static void main(String[] args) 
    {
        //Sample string
        String text = "This is a sample text with several words. This text is for testing word count.";
        
        //Call the countWords method and store the result
        Map<String, Integer> wordCount = countWords(text);
        
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) 
        {
            //Display each word and its count
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}