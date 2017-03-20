package javaMisc.oops;

public class RecursiveCallsNConstructorsSample {

}

class Animal {

    public Animal(String str) {
        System.out.println("Animal");
    }

    public static void main(String[] args) {
        // Horse h = new Horse("abc");
    }

    // recusive calls between two methods is allowed
    // but not allowed between constructors
    public void m1() {
        m2();
    }

    public void m2() {
        m1();
    }
}

/* Example of recursive calls in constructors
 * ======================================== */
/* class Horse extends Animal {
 * public Horse(String str) {
 * this(6);
 * System.out.println("Horse");
 * }
 * 
 * public Horse(int val) {
 * this();
 * }
 * 
 * public Horse() {
 * this("abc");
 * }
 * } */