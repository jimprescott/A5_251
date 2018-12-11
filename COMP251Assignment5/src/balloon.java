import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;


public class balloon {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		
		String inFile = "testBalloons.txt";
		ArrayList<ArrayList<Integer>> arrProbs = buildList(inFile);
		ArrayList<Integer> numArrow = new ArrayList<Integer>();
		
		for(ArrayList<Integer> arr : arrProbs) {
			numArrow.add(runGame(arr));
		}
		
		String outFile = "testBalloonsResults.txt";
		writeFile(outFile, numArrow);
		
		
		long endTime = System.currentTimeMillis();
			
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
	}
	
	public static ArrayList<ArrayList<Integer>> buildList(String file) {
		ArrayList<ArrayList<Integer>> arrProbs = new ArrayList<ArrayList<Integer>>();
		try {
			
			Scanner s = new Scanner(new File(file));
			String ln1 = s.nextLine();
			int numProb = Integer.parseInt(ln1);
			
			s.nextLine();
			
			for(int i = 0; i < numProb; i++) {
				String[] str = s.nextLine().split(" ");
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for(int j = 0; j < str.length; j++) {
					temp.add(Integer.parseInt(str[j]));
				}
				arrProbs.add(temp);
			}
			s.close();
		}
		
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(1); 
		}
		return arrProbs;
	}
	
	public static void shoot(ArrayList<Integer> arr) {
		int xPos = 0;
		int yPos = arr.get(xPos);
		
		arr.remove(xPos);
		yPos--;
		
		while(xPos < arr.size() && yPos > 0) {
			if(arr.get(xPos) == yPos) {
				arr.remove(xPos);
				yPos--;
			}
			else {
				xPos++;
			}
		}
	}
	
	public static int runGame(ArrayList<Integer> arr) {
		int counter = 0;
		while(!arr.isEmpty()) {
			shoot(arr);
			counter++;
		}
		return counter;
	}
	
	public static void writeFile(String file, ArrayList<Integer> numArrow) {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i < numArrow.size(); i++) {
				bw.write(String.valueOf(numArrow.get(i)));
				bw.newLine();
			}
			bw.close();			
		} 
		catch (IOException e) {
		      System.out.println("Error - " + e.toString());
		}
	}
}
