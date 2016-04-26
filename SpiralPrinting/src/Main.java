
/* Sample code to read in test cases:*/
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;
		String[] lineArr;
		String[][] inputArr;
		while ((line = buffer.readLine()) != null) {
			line = line.trim();
			lineArr = line.split(";");
			int rows = Integer.parseInt(lineArr[0]);
			int cols = Integer.parseInt(lineArr[1]);

			line = lineArr[2];
			lineArr = line.split(" ");
			inputArr = new String[rows][cols];

			int iterator = 0;
			// build the 2d array
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					inputArr[i][j] = lineArr[iterator++];
				}
			}

			int shellCount = (int) Math.min(Math.ceil((double) cols / 2.0), Math.ceil((double) rows / 2.0));
			for (int i = 0; i < shellCount; i++) {
				printSpiralOnLevel(inputArr, i);
			}

			System.out.println();
		}

	}

	public static void printSpiralOnLevel(String[][] inputArr, int shellLevel) {
		int cols = inputArr.length;
		int rows = inputArr[0].length;

		// print top
		for (int i = shellLevel; i < rows - shellLevel; i++) {
			System.out.print(inputArr[shellLevel][i] + " ");
		}
		// print right
		for (int i = shellLevel + 1; i < cols - shellLevel; i++) {
			System.out.print(inputArr[i][rows - shellLevel - 1] + " ");
		}

		if (shellLevel < cols / 2) {
			// print bottom
			for (int i = rows - shellLevel - 2; i >= shellLevel; i--) {
				System.out.print(inputArr[cols - shellLevel - 1][i] + " ");
			}

		}

		if (shellLevel < rows / 2) {
			// print left
			for (int i = cols - shellLevel - 2; i > shellLevel; i--) {
				System.out.print(inputArr[i][shellLevel] + " ");
			}
		}
	}
}
