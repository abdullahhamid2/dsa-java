package edu.emory.cs.algebraic;

import java.nio.ReadOnlyBufferException;
import java.util.Arrays;

public class LongIntegerQuiz extends LongInteger {
    public LongIntegerQuiz(LongInteger n) { super(n); }

    public LongIntegerQuiz(String n) { super(n); }

    @Override
    protected void addDifferentSign(LongInteger n) {
        int m = Math.max(digits.length, n.digits.length);
        byte[] result = new byte[m + 1];
        LongInteger biggerNumber;
        LongInteger smallerNumber;
        int resultCompare = compareAbs(n);
        if (resultCompare >=0) {
            biggerNumber = new LongInteger(this);
            smallerNumber = new LongInteger(n);
        }
        else {
            biggerNumber = new LongInteger(n);
            smallerNumber = new LongInteger(this);
            }
        for (int i = 0 ; i < smallerNumber.digits.length; i++) {
            int resultAtIndex = (biggerNumber.digits[i] - smallerNumber.digits[i]);
            if(resultAtIndex < 0) {
                result[i] += (byte) ((biggerNumber.digits[i] + 10) - smallerNumber.digits[i]);
                result[i + 1] -= 1;
            }
            else {
                result[i] += resultAtIndex;
            }
        }
        for(int j=smallerNumber.digits.length; j<biggerNumber.digits.length; j++)
        {
            result[j]+= biggerNumber.digits[j];
        }
        this.sign = biggerNumber.sign;
        this.digits = result;
        int trimIndex = 0;
        for (int i = result.length-1; i >= 0 ; i--){
            if(digits[i] > 0) {
                trimIndex = i;
                break;
            }
        }
        digits = Arrays.copyOf(result, trimIndex+1);
    }
}
