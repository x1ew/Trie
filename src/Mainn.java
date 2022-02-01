package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mainn {
	public static void insert(LinkedList[] Arr, TrieTree t, Scanner s) {
		System.out.println("enter number of students you want to add:");
		int n = s.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.println("enter name:");
			String sname = s.next();
			System.out.println("enter student number:");
			String snumber = s.next();
			System.out.println("enter student average:");
			int srate = s.nextInt();
			System.out.println("enter student field:");
			String sfield = s.next();
			while (true) {
				if (t.search(snumber)) {
					System.out.println("enter student number:");
					snumber = s.next();
				} else {
					Student object = new Student(sname, snumber, srate, sfield);
					t.insert(snumber, calculatehash(snumber));
					Arr[t.searchhash(snumber)].append(object);
					break;
				}
			} // Dont add twice
		}
		System.out.println("The student was successfully added");
	}
		
	public static void edit(LinkedList[] Arr, TrieTree t, Scanner s, String inputt) {
		System.out.println("enter new name:");
		String newname = s.next();
		System.out.println("enter new student number:");
		String newnumber = s.next();
		System.out.println("enter new student average:");
		int newrate = s.nextInt();
		System.out.println("enter new student field:");
		String newfield = s.next();
		while (true) {
			if (t.search(newnumber) && !newnumber.equals(inputt)) { // check number 													
				System.out.println("enter student number:"); 
				newnumber = s.next();
			} else {
				Student objectt = new Student(newname, newnumber, newrate, newfield);
				Arr[t.searchhash(inputt)].remove(inputt);
				t.delet(inputt);
				t.insert(newnumber, calculatehash(newnumber));
				Arr[t.searchhash(newnumber)].append(objectt);
				System.out.println("The student was successfully edited");
				break;
			}
		}
	}
	public static int calculatehash(String stdnumber) {
		long value = Long.parseLong(stdnumber);
		int ans = (int) ((value % 11) * (31 % 11)) % 11;
		return ans;
	}// using 31 for hashing
	public static void main(String args[]) {
		List<String> autolist = new ArrayList<String>();
		LinkedList Arr[] = new LinkedList[11];
		for (int i = 0; i < 11; i++) {
			LinkedList list = new LinkedList();
			Arr[i] = list;
		} 
		TrieTree t = new TrieTree();
		Scanner s = new Scanner(System.in);
		int start;
		do {
			System.out.println("1)insert student 2)search student 3)return");
			start = s.nextInt();
			if (start == 1) { 
				insert(Arr, t, s);

			} else if (start == 2) {
				String inputt;
				while (true) {
					System.out.println("Enter number: ");
					inputt = s.next();
					if (t.search(inputt)) {
						break;
					}
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					autolist = t.allauto(inputt);
					for (int i = 0; i < autolist.size(); i++) {
						if (autolist.get(i) != "") {
							System.out.println(autolist.get(i));
						}
					}
				}
				Student object = Arr[t.searchhash(inputt)].search(inputt);
				int select;
				do {
					System.out.println("1)edit student 2)delet student 3)see profile 4)return");
					select = s.nextInt();
					if (select == 1) { // edit
						edit(Arr, t, s, inputt);
						break;
					} else if (select == 2) {
						Arr[t.searchhash(inputt)].remove(inputt);
						t.delet(inputt);
						System.out.println("The student was successfully deleted");
						break;
					} else if (select == 3) {
						System.out.println("name: " + object.getName());
						System.out.println("number: " + object.getNumber());
						System.out.println("score: " + object.getScore());
						System.out.println("field: " + object.getField());
					}
				} while (select != 4);
			}
		} while (start != 3);
		s.close();
	}
}

