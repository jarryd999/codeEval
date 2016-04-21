
/* Sample code to read in test cases:*/
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;
		String[] lineArr;


		// ArrayList to store coordinates
		ArrayList<Double[]> coords;

		// outer loop through all sets
		while (true) {
			// var to kep track of min value
			double minDistance = Double.POSITIVE_INFINITY;
			// instantiate new ArrayList for coordinates
			coords = new ArrayList<Double[]>();
			// grab the coordinate count
			line = buffer.readLine();
			line = line.trim();
			int coordCount = Integer.parseInt(line);

			// if terminal set, end loop
			if (coordCount == 0)
				break;

			// inner loop through coordinates in set
			for (int i = 0; i < coordCount; i++) {
				// read each coordinate in and parse values
				line = buffer.readLine();
				line = line.trim();
				lineArr = line.split(" ");
				Double[] tmp = new Double[2];
				tmp[0] = Double.parseDouble(lineArr[0]);
				tmp[1] = Double.parseDouble(lineArr[1]);

				// add the coordinate tuple to the ArrayList, duplicates will be
				// ignored
				coords.add(tmp);
			}

			// check distance b/w every node to find min distance in set
			for (int i = 0; i < coords.size() - 1; i++) {
				for (int j = i + 1; j < coords.size(); j++) {
					double dist = Math.min(minDistance, calculateDistance(coords.get(i), coords.get(j)));
					if (dist < 10000 && dist < minDistance)
						minDistance = dist;
				}
			}
			if (minDistance == Double.POSITIVE_INFINITY)
				System.out.println("INFINITY");
			else
				System.out.printf("%.4f\n", minDistance);
		}
	}

	private static double calculateDistance(Double[] doubles, Double[] doubles2) {
		double x1 = doubles[0], y1 = doubles[1], x2 = doubles2[0], y2 = doubles2[1];

		double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

		if (dist == 0.0)
			return Double.MAX_VALUE;

		return dist;
	}
}
