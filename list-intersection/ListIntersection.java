import java.util.*;
import java.io.*;

public class ListIntersection {

    public static void main(String... args) {
		try {
			Scanner sc = new Scanner(new File("test.txt"));

			String firstLine = sc.nextLine();
			String secondLine = sc.nextLine();
			do {
				firstLine = sc.nextLine();
				secondLine = sc.nextLine();

				Node head1 = parseLine(firstLine);
				Node head2 = parseLine(secondLine);

				System.out.println("The lists \"" + firstLine + "\" and \"" + secondLine + "\" intersect at nodes..."); 

				Node output = getIntersection(head1, head2);

				while(output != null) {
					System.out.print(output.val);
					output = output.next;
				}
				System.out.println();
				System.out.println("--------------");

			} while (sc.hasNextLine());

			sc.close();
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
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

    public static Node getIntersection(Node head1, Node head2) {
		int l1 = getLength(head1);
        int l2 = getLength(head2);
        
        Node cur1 = head1;
        Node cur2 = head2;
        
        if (l1 < l2) {
            int diff = l2 - l1;
            cur2 = skip(head2, diff);
        } else {
            int diff = l1 - l2;
            cur1 = skip(head1, diff);
        }

        boolean sameNode = false;
        while (cur1 != null && cur2 != null) {
            if (cur1.val == cur2.val) {
                if (cur1.next != null && cur2.next != null) {
                    sameNode = checkNext(cur1, cur2);
                } else {
                    sameNode = true;
                }
            }
            
            if (sameNode) {
                return cur1;
            } else {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
        }
        
        return null;
	}

    public static int getLength(Node head) {
        Node current = head;
        int counter = 0;
        
        while (current != null) {
            counter ++;
			current = current.next;
        }
        
        return counter;
    }
    
    public static Node skip(Node current, int diff) {
		while (current != null && diff > 0) {
            current = current.next;
			-- diff;
        }
        
        if (diff == 0) {
            return current;
        } else {
            return null;
        }
    }
    
    public static boolean checkNext(Node h1, Node h2) {
        while (h1 != null && h2 != null) {
            if (h1.val != h2.val) {
                return false;
            } else {
                h1 = h1.next;
                h2 = h2.next;
            }
        }
        
        return true;
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
