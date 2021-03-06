/* Sample code to read in test cases:*/
import java.io.*;
import java.util.*;

public class Solution {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;

        ArrayList<Character> letters;
        Set<String> output;
        while ((line = buffer.readLine()) != null) {

            letters = new ArrayList<Character>();
            line = line.trim();
            
            for (char c : line.toCharArray()){
                letters.add(c);
            }
            
            output = findPermutations(letters, new StringBuffer());

            StringBuffer print = new StringBuffer();
            for (String word : output){
            	print.append(word);
            	print.append(",");
            }
            print.deleteCharAt(print.length() - 1);
            System.out.println(print);
            
        }
    }
    
    public static Set<String> findPermutations(ArrayList<Character> letters, StringBuffer parentWord){
        Set<String> output = new TreeSet<String>();
        
        if (letters.size() == 1){
        	StringBuffer tmp = new StringBuffer(parentWord);
        	tmp.append(letters.get(0));
            output.add(tmp.toString());
            return output;
        }
        
        else{
        	ArrayList<Character> clone = (ArrayList<Character>) letters.clone();
        	for (char c : clone){
        		letters.remove((Character)c);
        		StringBuffer tmp = new StringBuffer(parentWord);
        		tmp.append(c);
        		output.addAll(findPermutations(letters, new StringBuffer(tmp)));
        		letters.add(c);
        	}
        	return output;
        }
    }
}
