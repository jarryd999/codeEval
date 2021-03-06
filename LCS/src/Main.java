// Sample code to read in test cases:
import java.io.*;
public class Main {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line, str1, str2, output;
        String lineArr[], lcs[][];
        int M, N, count[][];
        
        while ((line = buffer.readLine()) != null) {
            output = "";
            line = line.trim();
            lineArr = line.split(";");
            str1 = lineArr[0];
            str2 = lineArr[1];
            M = str1.length();
            N = str2.length();
            count = new int[M+1][N+1];
            lcs = new String[M+1][N+1];
           	for (int i = 0; i < M; i++){
           		lcs[i][0] = "";
           	}
           	for (int i = 0; i < N; i++){
           		lcs[0][i] = "";
           	}
           	
            for (int i = 1; i <= M; i++){
                for (int j = 1; j <= N; j++){
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)){
                        count[i][j] = count[i-1][j-1] + 1;
                        lcs[i][j] = lcs[i-1][j-1] + String.valueOf(str1.charAt(i-1));
                    }else{
                        count[i][j] = Math.max(count[i-1][j], count[i][j-1]);
                        if (count[i-1][j] >= count[i][j-1]){
                        	lcs[i][j] = lcs[i-1][j];
                        }else{
                        	lcs[i][j] = lcs[i][j-1];
                        }
                    }
                }
            }
            
            System.out.println(lcs[M][N]);
        }
    }
}
