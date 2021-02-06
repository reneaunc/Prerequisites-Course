import java.util.*;

public class PlanTest {
	public static void main(String[] args) {
		System.out.println("\nBeginning of Test 1. Constructing Array...");
		int[][] array1 = prerequisiteGenerator(3);
		int num1 = countArray(array1);
		System.out.print("Randomly generated array implies " + num1 + " course(s): \n");
		printArray(array1);
		CoursePlanner.plan(num1, array1);
		System.out.println("End of Test 1.\n");

		System.out.println("Beginning of Test 2. Constructing Array...");
		int[][] array2 = prerequisiteGenerator(5);
		int num2 = countArray(array2);
		System.out.print("Randomly generated array implies " + num2 + " course(s): \n");
		printArray(array2);
		CoursePlanner.plan(num2, array2);
		System.out.println("End of Test 2.\n");

		System.out.println("Beginning of Test 3. Constructing Array...");
		int[][] array3 = prerequisiteGenerator(10);
		int num3 = countArray(array3);
		System.out.print("Randomly generated array implies " + num3 + " course(s): \n");
		printArray(array3);
		CoursePlanner.plan(num3, array3);
		System.out.println("End of Test 3.\n");
	}

	// this method generates a list of prerequisits as a 2D array
	private static int[][] prerequisiteGenerator(int size) {
		Random r = new Random();
		int[][] result = new int[size][2];
		for (int i = 0; i < size; i++) {
			result[i][0] = r.nextInt(11);
			result[i][1] = r.nextInt(11);
			while (result[i][0] == result[i][1]) {
				result[i][1] = r.nextInt(11);
			}
		}
		return result;
	}

	// Prints out the elements of a 2D array in a matrix format

	private static void printArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " "); 
			}
			System.out.println();
		}
	}

	// This countArray returns the number of unique courses given an int[][] of courses

	// private static int countArray(int[][] arr) {
	// 	int count = 0;
	// 	boolean[] unique = new boolean[11];
	// 	for (int i = 0; i < 11; i++) {
	// 		unique[i] = true;
	// 	}
	// 	for (int i = 0; i < arr.length; i++) {
	// 		for (int j = 0; j < arr[i].length; j++) {
	// 			if (unique[arr[i][j]]) {
	// 				count++;
	// 				unique[arr[i][j]] = false;
	// 			}
	// 		}
	// 	}
	// 	return count;
	// }

	// This countArray returns the max value of the given int[][] + 1

	private static int countArray(int[][] arr) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] > max) {
					max = arr[i][j];
				}
			}
		}
		return max+1;
	}
}
