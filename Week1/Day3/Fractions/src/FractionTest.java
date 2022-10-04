import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class FractionTest {
    FractionTest() {
    }

    @Test
    public void runAllTests() {
        compareTo();
        plus();
        minus();
        times();
        dividedBy();
        reciprocal();
        testToString();
        toDouble();
        reduce();
    }

    @org.junit.jupiter.api.Test
    private void compareTo() {
        ArrayList<Fraction> fractionList = new ArrayList<Fraction>();
        fractionList.add(new Fraction(1,2));
        fractionList.add(new Fraction(1,4));
        fractionList.add(new Fraction(1,6));
        fractionList.add(new Fraction(1,3));

        ArrayList<Fraction> sortedFractionList = new ArrayList<Fraction>();
        sortedFractionList.add(new Fraction(1,6));
        sortedFractionList.add(new Fraction(1,4));
        sortedFractionList.add(new Fraction(1,3));
        sortedFractionList.add(new Fraction(1,2));

        Collections.sort(fractionList);

        for (int i = 0; i < 4; i++) {
            assert (fractionList.get(i).toString().equals(sortedFractionList.get(i).toString()));
        }
    }
    @org.junit.jupiter.api.Test
    private void plus() {
        try {
            Fraction fr1 = new Fraction(1,2);
            //Fraction fr2 = new Fraction(1,4);
            Fraction fr2 = new Fraction(1,0);
            Fraction fr3 = new Fraction(2,0);
            assert (fr1.plus(fr2).toString().equals(fr3.toString()));
        }
        catch (ArithmeticException ex) {
            System.out.println(ex.toString());
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @org.junit.jupiter.api.Test
    private void minus() {
        try {
            Fraction fr1 = new Fraction(1, 2);
            //Fraction fr2 = new Fraction(1, 4);
            Fraction fr2 = new Fraction(1,0);
            Fraction fr3 = new Fraction(1, 4);
            assert (fr1.minus(fr2).toString().equals(fr3.toString()));
        }
        catch (ArithmeticException ex) {
            System.out.println(ex.toString());
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @org.junit.jupiter.api.Test
    private void times() {
        try {
            Fraction fr1 = new Fraction(1, 2);
            //Fraction fr2 = new Fraction(1, 4);
            Fraction fr2 = new Fraction(1,0);
            Fraction fr3 = new Fraction(1, 8);
            assert (fr1.times(fr2).toString().equals(fr3.toString()));
        }
        catch (ArithmeticException ex) {
            System.out.println(ex.toString());
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @org.junit.jupiter.api.Test
    private void dividedBy() {
        try {
            Fraction fr1 = new Fraction(1, 2);
            //Fraction fr2 = new Fraction(1, 4);
            Fraction fr2 = new Fraction(1,4);
            Fraction fr3 = new Fraction(2, 1);
            assert (fr1.dividedBy(fr2).toString().equals(fr3.toString()));
        }
        catch (ArithmeticException ex) {
            System.out.println(ex.toString());
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @org.junit.jupiter.api.Test
    private void reciprocal() {
        try {
            Fraction fr1 = new Fraction(1, 2);
            Fraction fr2 = new Fraction(2, 1);
            assert (fr1.reciprocal().toString().equals(fr2.toString()));
        }
        catch (ArithmeticException ex) {
            System.out.println(ex.toString());
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @org.junit.jupiter.api.Test
    private void testToString() {
        try {
            Fraction fr1 = new Fraction(1,2);
            String strFr1 = "1/2";
            assert (fr1.toString().equals(strFr1));
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @org.junit.jupiter.api.Test
    private void toDouble() {
        try {
            Fraction fr1 = new Fraction(1,2);
            double doubFr = (double)1/2;
            assert (fr1.toDouble() == doubFr);
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @org.junit.jupiter.api.Test
    private void reduce() {
        try {
            Fraction fr1 = new Fraction(2,4);
            fr1.reduce();
            Fraction fr2 = new Fraction(1,2);
            assert (fr1.toString().equals(fr2.toString()));
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}