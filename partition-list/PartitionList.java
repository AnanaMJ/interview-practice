import java.util.*;
import java.io.*;

public class PartitionList {

    public static void main(String... args) {
		try {
			Scanner sc = new Scanner(new File("test.txt"));
			String line = sc.nextLine();
			
			while (sc.hasNextLine()) {
				String[] elements = line.split(" ");

				Node input = new Node(Integer.valueOf(elements[0]));
				Node head = input;
				for(int i = 1; i < elements.length; ++i) {
					input.setNext(new Node(Integer.valueOf(elements[i])));
					input = input.next;
				}
				input.setNext(null);

				int k = 0;
				if(sc.hasNextLine()) {
					k = Integer.valueOf(sc.nextLine());
				}

				System.out.println("The partition in the list \"" + line + "\", will be made for element " + k + ": "); 

				Node output = partition(head, k);

				while (output != null) {
					System.out.print(output.val + " ");
					output = output.next;
				}
				System.out.println();

				if(sc.hasNextLine()) {
					line = sc.nextLine();
				}
			}

			sc.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public static Node partition(Node head, int k) {
		Node start = head;
		Node parent = head;
		Node current = parent.next;

		while (current != null) {
			if (current.val < k) {
				Node newHead = new Node(current.val);
				
				if (current.next != null) {
					current.val = current.next.val;
					current.next = current.next.next;
					parent.next = current;
				} else {
					parent.next = null;
					current = null;
				}

				newHead.next = start;
				start = newHead;
			} else { 
				parent = parent.next;
				current = current.next;
			}
		}

		return start;
	}
}

class Node {
	int val;
	Node next;

	public Node(int el) {
		val = el;
		next = null;
	}

	public void setNext(Node nextNode) {
		next = nextNode;
	}
}
