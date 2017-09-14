import java.util.*;
import java.io.*;

public class KthToTheLastElement {

    public static void main(String... args) {
//		FileReader fr = new FileReader("test");
//		BufferedReader textReader = new BufferedReader(fr);

//		String line = textReader.readLine();


//		while (line != null) {
		String[] arg = new String[2];
		arg[0] = "a b c d e f g";
		arg[1] = "8";
		String line = arg[0];
		while (true) {
			String[] elements = line.split(" ");
			
			Node input = new Node(elements[0]);
			Node head = input;
			for(int i = 1; i < elements.length; ++i) {
				input.setNext(new Node(elements[i]));
				input = input.next;
			}

//			line = textReader.readLine();
			line = arg[1];
			int k = 0;
			if(line != null) {
				k = Integer.valueOf(line);
			}

			Node output = getKthToLastElement(head, k);
			System.out.println(output.data);

			break;
			//line = textReader.readLine();
		}

//		textReader.close();
    }

    public static Node getKthToLastElement(Node head, int k) {
		Node start = head;
		Node end = start;
		System.out.println("end is "+end.data);

		for (int i = 1; i < k; ++i) {
			if (end.next != null) {
				end = end.next;
				System.out.println("end is now "+end.data);
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
			System.out.println("end is "+end.data+" and start is "+start.data);
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
