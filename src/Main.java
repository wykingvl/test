import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
//        numbersExample();
//        stringsExample();
    }

    public static void numbersExample() {
        SortedLinkedList<Integer> sortedInt = new SortedLinkedList<>(Comparator.comparingInt(Integer::valueOf));
        sortedInt.add(9);
        sortedInt.add(1);
        sortedInt.add(4);
        sortedInt.addAll(Arrays.asList(13, 2, 13, 15, 13, 20, 3, 8, 3, 21));

        printList(sortedInt);
        System.out.println("List Contains 30: " + sortedInt.contains(30));
        System.out.println("First index of s13: " + sortedInt.indexOf(13));
        System.out.println("Last index of s13: " + sortedInt.lastIndexOf(13));
        System.out.println("List Contains 9: " + sortedInt.contains(9));
        System.out.println("List Contains 20: " + sortedInt.contains(20));

        printList(sortedInt);
        sortedInt.remove((Integer) 13);
        printList(sortedInt);
        sortedInt.remove((Integer) 21);
        printList(sortedInt);
        sortedInt.remove((Integer) 1);
        printList(sortedInt);
        sortedInt.remove(0);
        printList(sortedInt);

        System.out.println("First element: " + sortedInt.get(0));
        System.out.println("Middle element: " + sortedInt.get(sortedInt.size() / 2));
        System.out.println("Last element: " + sortedInt.get(sortedInt.size() - 1));

        System.out.println("List is empty? " + sortedInt.isEmpty());
        sortedInt.clear();
        System.out.println("List is empty? " + sortedInt.isEmpty());
    }

    public static void stringsExample() {
        SortedLinkedList<String> sortedStrings = new SortedLinkedList<>(Comparator.naturalOrder());
        sortedStrings.add("s1");
        sortedStrings.add("s2");
        sortedStrings.add("s4");
        sortedStrings.addAll(Arrays.asList("s13", "s2", "s13", "s15", "s13", "s20", "s3", "s8", "s3", "s21"));

        printList(sortedStrings);
        System.out.println("List Contains 30: " + sortedStrings.contains("s30"));
        System.out.println("First index of 13: " + sortedStrings.indexOf("s13"));
        System.out.println("Last index of 13: " + sortedStrings.lastIndexOf("s13"));
        System.out.println("List Contains 9: " + sortedStrings.contains("s9"));
        System.out.println("List Contains 20: " + sortedStrings.contains("s20"));

        printList(sortedStrings);
        sortedStrings.remove("s13");
        printList(sortedStrings);
        sortedStrings.remove("s21");
        printList(sortedStrings);
        sortedStrings.remove("s1");
        printList(sortedStrings);
        sortedStrings.remove(0);
        printList(sortedStrings);

        System.out.println("First element: " + sortedStrings.get(0));
        System.out.println("Middle element: " + sortedStrings.get(sortedStrings.size() / 2));
        System.out.println("Last element: " + sortedStrings.get(sortedStrings.size() - 1));

        System.out.println("List is empty? " + sortedStrings.isEmpty());
        sortedStrings.clear();
        System.out.println("List is empty? " + sortedStrings.isEmpty());
    }

    public static void printList(SortedLinkedList sortedInt) {
        Iterator<Integer> itt = sortedInt.iterator();
        while (itt.hasNext()) {
            System.out.print(itt.next() + " ");
        }
        System.out.println();

    }
}