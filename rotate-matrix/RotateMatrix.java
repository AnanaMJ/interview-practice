import java.util.*;

public class RotateMatrix {

    public static void main(String... args) {
		System.out.println(args[0]);

		String[] numbers = args[0].split(" ");
        int length = numbers.length;
		double sqrt = Math.sqrt(length);
		int lineLength = (int) sqrt;	
		System.out.println("line is " + lineLength + " long");

		if (Math.pow(sqrt,2) != Math.pow(lineLength, 2)) {
			throw new IllegalArgumentException("The matrix needs to be NxN");
		}

		int[][] input = new int[lineLength][lineLength];
		int counter = 0;
		for (int i = 0; i < lineLength; ++i) {
			for (int j = 0; j < lineLength; ++j) {
				input[i][j] = Integer.valueOf(numbers[counter]);
				++counter;
			}
		}

		System.out.println("Original matrix:");
		printMatrix(input, lineLength);

		int[][] rotated = rotateArray(input);
		System.out.println("Rotated matrix:");
		printMatrix(rotated, lineLength);
    }

    public static int[][] rotateArray(int[][] input) {
		int n = input[0].length - 1;
		int layer = 1;

		for (int i = 0; i <= n - layer; ++i) {
			for (int j = i; j <= n - layer; ++j) {
				// first side
				int temp = input[i][j];
				input[i][j] = input[n - j][i];
				input[n - j][i] = temp;

				// second side
				temp = input[i][j];
				input[i][j] = input[n - i][n - j];
				input[n - i][n - j] = temp;

				// third side
				temp = input[i][j];
				input[i][j] = input[j][n - i];
				input[j][n - i] = temp;
			}
			++layer;
		}

		return input;
    }

	public static void printMatrix(int[][] array, int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
                int current = array[i][j];

                if (current / 100 == 0) {
                    if (current / 10 == 0) {
                        System.out.print("  " + current + "  ");
                    } else {
                        System.out.print("  " + current + " ");
                    }
                } else {
                    System.out.print(" " + current + " ");
                }
            }
            System.out.println();

		}
	}
}

