package textgen;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class MarkovTextGeneratorTester {

    MarkovTextGeneratorLoL gen;
    
    @Before
    public void setUp() throws Exception {
        gen = new MarkovTextGeneratorLoL(new Random());
    }

    
    /**
     * Test train() method with three words
     */
    @Test
    public void testTrainFourWords() {
        gen.train("hi there hi aap");
        System.out.println(gen.toString());
        //  System.out.println(gen.wordList.l);        
        
    }

}
