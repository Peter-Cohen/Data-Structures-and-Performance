package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

    // The list of words with their next words
    private List<ListNode> wordList;

    // The starting "word"
    private String firstWordInSourceText;

    // The random number generator
    private Random rnGenerator;

    public MarkovTextGeneratorLoL(Random generator) {
        wordList = new LinkedList<ListNode>();
        firstWordInSourceText = "";
        rnGenerator = generator;
    }

    /** Train the generator by adding the sourceText 
     * @param   sourcetext
     * */
    @Override
    public void train(String sourceText) {
        // TODO:
        //  - sourceText of length 0
        //  - sourceText of length 1
        //  - unit tests; but: relevant methods and fields are all private!
        //  - what to do if already trained? 
        
        if (!wordList.isEmpty()) { return; }
        
        String[] allWords = sourceText.split("[\\s]+"); // Is .split("\\s+") also okay?
        
        
        firstWordInSourceText = allWords[0];
        String prevWord = firstWordInSourceText;

        for (int i = 1; i < allWords.length; i++) {
            ListNode getNode = findListNodeByWord(prevWord);
            getNode.addNextWord(allWords[i]);
            prevWord = allWords[i];
        }

        
        // Add the last word in the sourceText if necessary and add starter word to its list of nextWords
        String lastWordInSourceText = allWords[allWords.length - 1];
        ListNode getNode = findListNodeByWord(lastWordInSourceText);
        getNode.addNextWord(firstWordInSourceText);
    }

    
    /**
     * Generate the number of words requested.
     */
    @Override
    public String generateText(int numWords) {
        // TODO: Implement this method
    
        if (wordList.size() == 0) {
            return "";
        }
        String currentWord = firstWordInSourceText;
        String outputText = "";
        
        for (int i = 0; i < numWords - 1; i++) {
            ListNode node = findListNodeByWord(currentWord);
            if (node == null) {
                node = findListNodeByWord(firstWordInSourceText);
            }
            String w = node.getRandomNextWord(rnGenerator);
            outputText += " " + w;
            currentWord = w;
        }     
        
        return outputText;
    }

    
    
    
    
    
    // Can be helpful for debugging
    @Override
    public String toString() {
        String toReturn = "";
        for (ListNode n : wordList) {
            toReturn += n.toString();
        }
        return toReturn;
    }

    /** Retrain the generator from scratch on the source text */
    @Override
    public void retrain(String sourceText) {
        wordList.clear();
        train(sourceText);
    }

    

    
    
    /**
     * Helper method to check if a word is already a node in the wordList
     * 
     * @param   the word to look up in the wordListword
     * @return  the ListNode where word is found, or null if not found
     * 
     */
    private ListNode findListNodeByWord(String word) {
        // is checking for a wordList of null necessary??
        for (ListNode node : wordList) {
            if (node.getWord().equals(word)) {
                return node;
            }
        }
        ListNode node = new ListNode(word);
        wordList.add(node);
        return node; 
    }

    
    
    
    
    /**
     * This is a minimal set of tests. Note that it can be difficult to test
     * methods/classes with randomized behavior.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // feed the generator a fixed random value for repeatable behavior
        MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
        String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
        System.out.println(textString);
        gen.train(textString);
        System.out.println(gen);
        System.out.println(gen.generateText(20));
        String textString2 = "You say yes, I say no, " + "You say stop, and I say go, go, go, "
                + "Oh no. You say goodbye and I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello. " + "I say high, you say low, "
                + "You say why, and I say I don't know. " + "Oh no. "
                + "You say goodbye and I say hello, hello, hello. "
                + "I don't know why you say goodbye, I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello. " + "Why, why, why, why, why, why, "
                + "Do you say goodbye. " + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
                + "I don't know why you say goodbye, I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello. " + "You say yes, I say no, "
                + "You say stop and I say go, go, go. " + "Oh, oh no. "
                + "You say goodbye and I say hello, hello, hello. "
                + "I don't know why you say goodbye, I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello, hello, hello, "
                + "I don't know why you say goodbye, I say hello, hello, hello,";
        System.out.println(textString2);
        gen.retrain(textString2);
        System.out.println(gen);
        System.out.println(gen.generateText(20));

    }

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
    // The word that is linking to the next words
    private String word;

    // The next words that could follow it
    private List<String> nextWords;

    ListNode(String word) {
        this.word = word;
        nextWords = new LinkedList<String>();
    }

    public String getWord() {
        return word;
    }

    public void addNextWord(String nextWord) {
        nextWords.add(nextWord);
    }

    public String getRandomNextWord(Random generator) {
        // TODO: Implement this method
        // The random number generator should be passed from
        // the MarkovTextGeneratorLoL class
        if (nextWords.size() == 0 ) {
            return null;
        }
        return nextWords.get(generator.nextInt(nextWords.size()));
    }

    
    public String toString() {
        String toReturn = word + ": ";
        for (String s : nextWords) {
            toReturn += s + "->";
        }
        toReturn += "\n";
        return toReturn;
    }

}
