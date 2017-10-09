import java.util.*;
import java.io.*;

public class ListPalindrome {

    public static void main(String... args) {
		try {
			Scanner sc = new Scanner(new File("test.txt"));
			String firstLine = sc.nextLine();

			while (sc.hasNextLine()) {

				Node head1 = parseLine(firstLine);

				System.out.println("Checking if " + firstLine + " is palindrome ..."); 

				boolean output = isPalindrome(head1);

				System.out.println(output);

				System.out.println("--------------");

				if(sc.hasNextLine()) {
					firstLine = sc.nextLine();
				}
			}

			sc.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

	public static Node parseLine(String line) {
		String[] elements = line.split("");
		
		Node input = new Node(elements[0]);
        Node head = input;
        
		for(int i = 1; i < elements.length; ++i) {
			input.setNext(new Node(elements[i]));
            input = input.next;
        }

		return head;
	}

    public static boolean isPalindrome(Node head) {
		Node current = head;
		int length = 0;

		// going through all the nodes to get the length
		while (current != null) {
			current = current.next;
			++ length;
		}

		Stack<String> firstHalf = new Stack<>();
		current = head;

		for (int i = 0; i <= length / 2; ++ i) {
			firstHalf.push(current.val);
			current = current.next;
		}

		if (length % 2 != 0) {
			firstHalf.pop();
		}

		while (!firstHalf.isEmpty()) {
			String first = firstHalf.pop();
			if (!first.equals(current.val)) {
				return false;
			} else {
				current = current.next;
			}
		}

		return true;
	}

}

class Node {
	String val;
	Node next;

	public Node(String el) {
		val = el;
		next = null;
	}

	public void setNext(Node nextNode) {
		next = nextNode;
	}
}
