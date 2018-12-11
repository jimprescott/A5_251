import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

// Counts the number of islands in a 2D array where a 1 represents land and a 0 represents water
public class islands {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		String inFile = "testIslands.txt";
		ArrayList<Integer[][]> islands = buildIslands(inFile);
		ArrayList<Integer> numIslands = new ArrayList<Integer>();
		
		for(Integer[][] i : islands) {
			numIslands.add(countIslands(i));
		}
		
		System.out.println(numIslands.toString());
		String outFile = "testIslandsResults.txt";
		writeFile(outFile, numIslands);
				
		long endTime = System.currentTimeMillis();
		out.println("That took: " + (endTime - startTime) + " milliseconds.");
	}
	
	public static ArrayList<Integer[][]> buildIslands(String file) {
		ArrayList<Integer[][]> islands = new ArrayList<Integer[][]>();
		try {
			Scanner s = new Scanner(new File(file));
			int numProbs = Integer.parseInt(s.nextLine());
			for(int i = 0; i < numProbs; i++) {
				String[] rowCol = s.nextLine().split(" ");
				int rows = Integer.parseInt(rowCol[0]);
				int cols = Integer.parseInt(rowCol[1]);
				Integer[][] arr = new Integer[rows][cols];
				
				for(int j = 0; s.hasNextLine() && j < rows; j++) {
					String[] str = s.nextLine().split("");
					for(int k = 0; k < cols && k < str.length; k++) {
						if(str[k].equals("-")) {
							arr[j][k] = 1;
						}
						else if(str[k].equals("#")){
							arr[j][k] = 0;
						}
					}
				}
				islands.add(arr);
			}
			s.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			System.exit(1);
		}
		return islands;
	}
	
	public static void searchDFS(Integer[][] arr, int row, int col) {	
		int numRows = arr.length;
		int numCols = arr[0].length;
	
		if(col < numCols - 1) {
			if(arr[row][col + 1] == 1) {
				arr[row][col + 1] = 0;
				searchDFS(arr, row, col + 1);
			}
		}
		if(row < numRows - 1) {
			if(arr[row + 1][col] == 1) {
				arr[row + 1][col] = 0;
				searchDFS(arr, row + 1, col);
			}
		}
		if(col > 0) {
			if(arr[row][col - 1] == 1) {
				arr[row][col - 1] = 0;
				searchDFS(arr, row, col - 1);
			}
		}
		if(row > 0) {
			if(arr[row - 1][col] == 1) {
				arr[row - 1][col] = 0;
				searchDFS(arr, row - 1, col);
			}
		}
	}
	
	public static int countIslands(Integer[][] arr) {		
		int count = 0;
		int numRows = arr.length;
		int numCols = arr[0].length;
		
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				if(arr[i][j] == 1) {
					searchDFS(arr, i, j);
					count++;
				}
			}
		}
		return count;
	}
	
	public static void printArr(Integer[][] arr) { 
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	public static void writeFile(String file, ArrayList<Integer> numIsland) {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i < numIsland.size(); i++) {
				bw.write(String.valueOf(numIsland.get(i)));
				bw.newLine();
			}
			bw.close();			
		} 
		catch (IOException e) {
		      System.out.println("Error - " + e.toString());
		}
	}
	

}
