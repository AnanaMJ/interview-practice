import java.util.*;
import java.io.*;

public class KthToLastElement {

    public static void main(String... args) {
		try {
			Scanner sc = new Scanner(new File("test.txt"));
			String line = sc.nextLine();
			
			while (sc.hasNextLine()) {
				String[] elements = line.split(" ");

				Node input = new Node(elements[0]);
				Node head = input;
				for(int i = 1; i < elements.length; ++i) {
					input.setNext(new Node(elements[i]));
					input = input.next;
				}

				int k = 0;
				if(sc.hasNextLine()) {
					k = Integer.valueOf(sc.nextLine());
				}

				System.out.println("The Kth to last element from \"" + line + "\", where k is " + k + " is ..."); 

				Node output = getKthToLastElement(head, k);
				System.out.println(output.data);

				if(sc.hasNextLine()) {
					line = sc.nextLine();
				}
			}

			sc.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public static Node getKthToLastElement(Node head, int k) {
		Node start = head;
		Node end = start;

		for (int i = 1; i < k; ++i) {
			if (end.next != null) {
				end = end.next;
			} else {
				if (i != k+1) {
					throw new IllegalArgumentException("K is greater than the length of the list");
				}
				break;
			}
		}

		while (end.next != null) {
			start = start.next;
			end = end.next;
		}

		return start;
	}
}

class Node {
	String data;
	Node next;

	public Node(String el) {
		data = el;
		next = null;
	}

	public void setNext(Node nextNode) {
		next = nextNode;
	}
}
