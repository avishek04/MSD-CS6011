public class Fraction {
    private long numerator;
    private long denominator;
    private double realRep;

    private long GCD() {
        long gcd = this.numerator < 0 ? -this.numerator : this.numerator;
        long remainder = this.denominator < 0 ? -this.denominator : this.denominator;
        while( remainder != 0 ) {
            long temp = remainder;
            remainder = gcd % remainder;
            gcd = temp;
        }
        return gcd;
    }

    Fraction() {
        this.numerator = 0;
        this.denominator = 1;
        this.realRep = 0;
    }

    Fraction(long n, long d) {
        this.realRep = (double) n / d;
        if (d < 0) {
            this.numerator = n * -1;
            this.denominator = d * -1;
        }
        else {
            this.numerator = n;
            this.denominator = d;
        }
        reduce();
        this.realRep = (double) n / d;
    }

    public Fraction plus(Fraction rhs) throws Exception {
        Fraction sum = null;
        if (rhs.denominator != 0 && this.denominator != 0) {
            sum = new Fraction(this.numerator * rhs.denominator + rhs.numerator * this.denominator, this.denominator * rhs.denominator);
        }
        else {
            throw new Exception();
        }
        //factorize it
        return sum;
    }

    public Fraction minus(Fraction rhs) throws Exception {
        Fraction sub = null;
        if (rhs.denominator != 0 && this.denominator != 0) {
            sub = new Fraction(this.numerator * rhs.denominator - rhs.numerator * this.denominator, this.denominator * rhs.denominator);
        }
        else {
            throw new Exception();
        }
        //factorize it
        return sub;
    }

    public Fraction times(Fraction rhs) throws Exception {
        Fraction mult = null;
        if (rhs.denominator != 0 && this.denominator != 0) {
            mult = new Fraction(this.numerator * rhs.numerator, this.denominator * rhs.denominator);
        }
        else {
            throw new Exception();
        }
        //factorize it
        return mult;
    }

    public Fraction dividedBy(Fraction rhs) throws Exception {
        Fraction div = null;
        if (rhs.numerator != 0 && this.denominator != 0) {
            div = new Fraction(this.numerator * rhs.denominator, this.denominator * rhs.numerator);
        }
        else {
            throw new Exception();
        }
        //factorize it
        return div;
    }

    public Fraction reciprocal() throws Exception {
        Fraction recp = null;
        if (this.numerator != 0)
            recp = new Fraction(this.denominator, this.numerator);
        else
            throw new Exception();
        //factorize it
        return recp;
    }

    public String toString() {
        long num = numerator < 0 ? -numerator : numerator;
        long den = denominator < 0 ? -denominator : denominator;
        if (realRep < 0) {
            return "-" + num + "/" + den;
        }
        return num + "/" + den;
    }

    public double toDouble() {
        return realRep;
    }

    public void reduce() {
        long gcd = GCD();
        this.numerator /= gcd;
        this.denominator /= gcd;
    }
}
