import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FractionTest {
    FractionTest() {
    }

    @Test
    public void runAllTests() {
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
    private void plus() {
        Fraction fr1 = new Fraction(1,2);
        Fraction fr2 = new Fraction(1,4);
        Fraction fr3 = new Fraction(3,4);
        assert (fr1.plus(fr2).toString().equals(fr3.toString()));
    }

    @org.junit.jupiter.api.Test
    private void minus() {
        Fraction fr1 = new Fraction(1,2);
        Fraction fr2 = new Fraction(1,4);
        Fraction fr3 = new Fraction(1,4);
        assert (fr1.minus(fr2).toString().equals(fr3.toString()));
    }

    @org.junit.jupiter.api.Test
    private void times() {
        Fraction fr1 = new Fraction(1,2);
        Fraction fr2 = new Fraction(1,4);
        Fraction fr3 = new Fraction(1,8);
        assert (fr1.times(fr2).toString().equals(fr3.toString()));
    }

    @org.junit.jupiter.api.Test
    private void dividedBy() {
        Fraction fr1 = new Fraction(1,2);
        Fraction fr2 = new Fraction(1,4);
        Fraction fr3 = new Fraction(2,1);
        assert (fr1.dividedBy(fr2).toString().equals(fr3.toString()));
    }

    @org.junit.jupiter.api.Test
    private void reciprocal() {
        Fraction fr1 = new Fraction(1,2);
        Fraction fr2 = new Fraction(2,1);
        assert (fr1.reciprocal().toString().equals(fr2.toString()));
    }

    @org.junit.jupiter.api.Test
    private void testToString() {
        Fraction fr1 = new Fraction(1,2);
        String strFr1 = "1/2";
        assert (fr1.toString().equals(strFr1));
    }

    @org.junit.jupiter.api.Test
    private void toDouble() {
        Fraction fr1 = new Fraction(1,2);
        double doubFr = (double)1/2;
        assert (fr1.toDouble() == doubFr);
    }

    @org.junit.jupiter.api.Test
    private void reduce() {
        Fraction fr1 = new Fraction(2,4);
        fr1.reduce();
        Fraction fr2 = new Fraction(1,2);
        assert (fr1.toString().equals(fr2.toString()));
    }
}