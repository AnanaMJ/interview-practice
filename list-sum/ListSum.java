import java.util.*;
import java.io.*;

public class ListSum {

    public static void main(String... args) {
		try {
			Scanner sc = new Scanner(new File("test.txt"));
			String firstLine = sc.nextLine();
			String secondLine = sc.nextLine();

			while (sc.hasNextLine()) {

				Node head1 = parseLine(firstLine);
				Node head2 = parseLine(secondLine);

				System.out.println("The sum between " + firstLine + " and " + secondLine + " is ..."); 

				Node output = getSum(head1, head2);

				while(output != null) {
					System.out.print(output.val);
					output = output.next;
				}

				System.out.println("--------------");

				if(sc.hasNextLine()) {
					firstLine = sc.nextLine();
					secondLine = sc.nextLine();
				}
			}

			sc.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

	public static Node parseLine(String line) {
		String[] elements = line.split("");
		
		Node input = new Node(Integer.valueOf(elements[0]));
        Node head = input;
        
		for(int i = 1; i < elements.length; ++i) {
			input.setNext(new Node(Integer.valueOf(elements[i])));
            input = input.next;
        }

		return head;
	}

    public static Node getSum(Node head1, Node head2) {
		Node current1 = head1;
		Node current2 = head2;

		// going through all the nodes while 1 and 2 have the same length
		while (current1 != null && current2 != null) {
			current1 = current1.next;
			current2 = current2.next;
		}

		// if 1 has remaining digits
		if (current1 != null) {
			head2 = padList(head2, current1);
		} else if (current2 != null) {
			head1 = padList(head1, current2);
		}

		current1 = head1;
		current2 = head2;
		System.out.print("node 1: ");
		while (current1 != null) {
			System.out.print(current1.val);
			current1 = current1.next;
		}
		System.out.println();
		System.out.print("node 2: ");
		while (current2 != null) {
			System.out.print(current2.val);
			current2 = current2.next;
		}
		System.out.println();

		// Add lists
		PartialSum sum = addListHelper(head1, head2);

		// If there was a carry value left over, insert this at the front of the list
		// Otherwise, return the list
		if (sum.carry == 0) {
			return sum.sum;
		} else {
			Node result = insertBefore(sum.sum, sum.carry);
			return result;
		}
	}

	public static PartialSum addListHelper(Node current1, Node current2) {
		if (current1 == null && current2 == null) {
			PartialSum sum = new PartialSum();
			return sum;
		}

		// Add smaller digits recursively
		PartialSum sum = addListHelper(current1.next, current2.next);

		// Add carry to current data
		int value = sum.carry + current1.val + current2.val;

		// Insert sum of current digits
		Node full_result = insertBefore(sum.sum, value % 10);

		// Return sum so far, and the carry value
		sum.sum = full_result;
		sum.carry = value / 10;
		return sum;
	}

	public static Node insertBefore(Node sum, int carry) {
		Node head = new Node(carry);
		if (head != null) {
			head.next = sum;
		}
		return head;
	}

	public static Node padList(Node head, Node current) {
		while (current != null) {
			Node newHead = new Node(0);
			newHead.next = head;
			head = newHead;

			current = current.next;
		}
		return head;
	}
}

class PartialSum {
	Node sum = null;
	int carry = 0;
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
