import java.util.*;

public class ManualHashTable 
{
    //create an array of linked lists for separate chaining
    //each linked list will hold WordEntry objects
    private List<WordEntry>[] table;
    private int size;

    // Constructor
    @SuppressWarnings("unchecked")
    public ManualHashTable(int size) 
    {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) 
        {
            table[i] = new LinkedList<>();
        }
    }

    // Hash function to compute index for a word
    private int hash(String key) 
    {
        return Math.abs(key.hashCode()) % size;
    }

    //Insert or update a word
    public void put(String word) 
    {
        //convert word to hash code
        int index = hash(word);

        //insert into the array at that index
        List<WordEntry> bucket = table[index];

        for (WordEntry entry : bucket) 
        {
            if (entry.word.equals(word)) 
            {
                //Word found, increment count
                entry.increment();
                return;
            }
        }

        //Word not found, add new entry
        bucket.add(new WordEntry(word));
    }

    //Get count of a word
    public int get(String word) 
    {
        int index = hash(word);
        List<WordEntry> bucket = table[index];

        for (WordEntry entry : bucket) 
        {
            if (entry.word.equals(word)) 
            {
                return entry.count;
            }
        }
        return 0;
    }

    public boolean remove(String word) 
    {
        int index = hash(word);
        List<WordEntry> bucket = table[index];

        Iterator<WordEntry> it = bucket.iterator();
        while (it.hasNext()) 
        {
            WordEntry entry = it.next();
            if (entry.word.equals(word)) 
            {
                it.remove();
                return true;
            }
        }
        return false;
    }

    // Display contents of the hash table
    public void printTable() 
    {
        for (int i = 0; i < size; i++) {
            System.out.print("Bucket " + i + ": ");
            for (WordEntry entry : table[i]) 
            {
                System.out.print(entry + " -> ");
            }
            System.out.println("null");
        }
    }

    //Inner class to represent a word and its count
    //Used in the linked list for separate chaining
    public class WordEntry 
    {
        String word;
        int count;

        public WordEntry(String word) 
        {
            this.word = word;
            this.count = 1;
        }

        public void increment()
        {
            count++;
        }

        @Override
        public String toString() 
        {
            return word + ": " + count;
        }
    }
    public static void main(String[] args) {
        //sample text
        String text = "This is a test. This test is simple and this test works.";

        //split text into individual words and store in array
        String[] words = text.toLowerCase().split("\\W+");

        ManualHashTable hashTable = new ManualHashTable(10);

        //insert words into the hash table
        for (String word : words) {
            hashTable.put(word);
        }

        System.out.println("Word counts:");
        hashTable.printTable();

        // Example operations
        System.out.println("\nCount for 'test': " + hashTable.get("test"));
        hashTable.remove("simple");
        System.out.println("After removing 'simple':");
        hashTable.printTable();
    }
}