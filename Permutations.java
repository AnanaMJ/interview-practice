import java.util.*;

public class Permutations {
	
	public static void main(String... args) {
		if (args.length != 1) {
			System.err.println("Must supply exactly one argument");
			return;
		}

		char[] options = args[0].toCharArray();
		ArrayList<String> output = new ArrayList<>();
		makePermutations(options, output);
		System.out.println("Made " + output.size() + " permutation(s):");
		for (String s : output) {
			System.out.println(s);
		}
	}

	private static void makePermutations(char[] options, ArrayList<String> output) {
		// code goes here
		
		boolean[] available = new boolean[options.length];

		for (int i = 0; i < available.length; ++i) {
			available[i] = true;
		}
		HashSet<String> uniqueOutput = new HashSet<>();
		permute(options, new char[options.length], available, 0, uniqueOutput);

		output.addAll(uniqueOutput);
	}

	private static void permute(char[] options, char[] chosen, boolean[] available, int index, HashSet<String> uniqueOutput) {
		
		// stop when index is options.length; make a string and add it t the output
		if (index == options.length) {
			uniqueOutput.add(new String(chosen));

		} else {
			for (int i = 0; i < options.length; ++i) {
				if (available[i]) {
					chosen[index] = options[i];
					available[i] = false;
					permute(options, chosen, available, index + 1, uniqueOutput);
					available[i] = true;
				}
			}
		}	
	
	}

}
