import java.io.*;
import java.util.*;

public class Shrub implements Comparable<Shrub> {
    private double size, price, nice;
    private String name;
    private static String compare;

    public Shrub(String name, double nice, double price, double size) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.nice = nice;
    }

    public static void setCompare(String compare) {
        Shrub.compare = compare;
    }

    @Override
    public int compareTo(Shrub o) {
        return switch (compare) {
            case "SIZE" -> Double.compare(o.size, this.size); // largest
            case "PRICE" -> Double.compare(this.price, o.price); // smallest
            case "NICE" -> Double.compare(o.nice, this.nice); // largest
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("shrubbery.dat"));
        int cases = in.nextInt();

        while (cases-- > 0) {
            int n = in.nextInt();
            Shrub[] arr = new Shrub[n];

            for (int i = 0; i < n; i++)
                arr[i] = new Shrub(in.next(), in.nextDouble(), in.nextDouble(), in.nextDouble());
            Shrub.setCompare(in.next());

            Arrays.sort(arr);

            for (Shrub s : arr) System.out.println(s);

            System.out.println();
        }
    }
}
