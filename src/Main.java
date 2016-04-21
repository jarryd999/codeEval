
/* Sample code to read in test cases:*/
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		BufferedReader buffer = new BufferedReader(new FileReader(file));

		// variables to handle input
		String line;
		String[] lineArr;
		String[] seq;
		int[] sequence;

		// hash sets to keep track of the pairs,
		// and individual numbers already added to help runtime & avoid
		// duplicates
		LinkedHashSet<ArrayList<Integer>> pairs;
		HashSet<Integer> duplicatePrevention;

		// loop through input file lines
		while ((line = buffer.readLine()) != null) {
			// instantiate HashSets
			duplicatePrevention = new HashSet<Integer>();
			pairs = new LinkedHashSet<ArrayList<Integer>>();

			// read the input line
			line = line.trim();
			lineArr = line.split(";");
			seq = lineArr[0].split(",");
			int target = Integer.parseInt(lineArr[1]);

			// put into an int array
			sequence = new int[seq.length];
			for (int i = 0; i < seq.length; i++) {
				sequence[i] = Integer.parseInt(seq[i]);
			}

			// loop through the array
			for (int i = 0; i < sequence.length - 1; i++) {
				// if too high or already used, break the loop
				if (sequence[i] > target || duplicatePrevention.contains(sequence[i])) {
					break;
				}
				// otherwise iterate over the subsequent numbers in the sequence
				// and look for pair
				for (int j = i + 1; j < sequence.length; j++) {
					// if sum is too high, break the inner loop
					if (sequence[i] + sequence[j] > target || duplicatePrevention.contains(sequence[j])) {
						break;
					}
					// if a pair is found, add it to the HashSets and break the
					// loop
					if (sequence[i] + sequence[j] == target) {
						ArrayList<Integer> pair = new ArrayList<Integer>();
						pair.add(sequence[i]);
						pair.add(sequence[j]);
						duplicatePrevention.add(sequence[i]);
						duplicatePrevention.add(sequence[j]);
						pairs.add(pair);
						break;
					}
				}
			}

			// instantiate a new string buffer to produce output string
			StringBuffer output = new StringBuffer();

			// iterate over each pair and add it to the string buffer
			for (ArrayList<Integer> pair : pairs) {
				for (Integer i : pair) {
					output.append(i);
					output.append(",");
				}
				// replace the extra comma with a semicolon
				output.deleteCharAt(output.length() - 1);
				output.append(";");
			}
			// if >=1 pair found, get rid of extra semicolon and print output
			if (output.length() > 0) {
				output.deleteCharAt(output.length() - 1);
				System.out.println(output.toString());
			}
			// if no pairs found, print "NULL"
			else {
				System.out.println("NULL");
			}
		}
	}
}
