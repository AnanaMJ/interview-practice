import java.util.*;
 
public class WordLadderBfs {
 
    public static void main(String... args) {
        if (args.length < 3) {
            throw new IllegalArgumentException();
        }
        String start = args[0];
        String end = args[1];
        Set<String> dictionary = new HashSet<>();
        for (int i = 0; i < args.length; ++i) {
            dictionary.add(args[i]);
        }
 
        findLadder(start, end, dictionary);
    }
 
    public static void findLadder(String start, String end, Set<String> dict) {
		// assume the ords are the same length
		Deque<List<String>> open = new LinkedList<>();
		Set<String> closed = new HashSet<>();

		List<String> root = new ArrayList<>();
		root.add(start);

		open.addLast(root);
		while (!open.isEmpty()) {
			List<String> current = open.removeFirst();
			String currentWord = current.get(current.size() - 1);
			closed.add(currentWord);

			if (currentWord.equals(end)) {
				System.out.println(current);
				break;
			}

			Set<String> variations = getVariations(currentWord, dict);

			for (String variation : variations) {
				if (!closed.contains(variation)) {
					List<String> child = new ArrayList<>();
					child.addAll(current);
					child.add(variation);
					open.addLast(child);
				}
			}
		}
    }

	private static Set<String> getVariations(String input, Set<String> dict) {
		char[] parent = input.toCharArray();
		Set<String> variations = new HashSet<>();

		for (int i = 0; i < parent.length; ++i) {
			char original = parent[i];
			for (int j = 0; j < 26; ++j) {
				char current = (char) ('a' + j);
				
				if (original != current) {
					parent[i] = current;
					String variation = new String(parent);

					if (dict.contains(variation)) {
						variations.add(variation);
					}
				}
			}
			parent[i] = original;
		}

		return variations;
	} 
}
