import java.util.*;
 
public class WordLadderDfs {
 
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
		Stack<State> open = new Stack<>();
		Set<String> closed = new HashSet<>();

		List<String> path = new ArrayList<>();
		open.push(new State(start, 0));
		while (!open.isEmpty()) {
			State current = open.pop();
			closed.add(current.word);

			if (path.size() == current.depth) {
				path.add(current.word);
			} else {
				path.set(current.depth, current.word);
			}

			if (current.word.equals(end)) {
				System.out.println(path.subList(0, current.depth + 1));
				break;
			}

			Set<String> variations = getVariations(current.word, dict);

			for (String variation : variations) {
				if (!closed.contains(variation)) {
					open.push(new State(variation, current.depth + 1));
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

	private static class State {
		String word;
		int depth;

		State(String word, int depth) {
			this.word = word;
			this.depth = depth;
		}
	}
 
}
