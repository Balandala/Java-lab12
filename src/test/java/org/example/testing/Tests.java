package org.example.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;

public class Tests {
    public Tests(){
    }
        @Test
        public void Test_empty_string(){
        Throwable exception = Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode(""));
        Assertions.assertEquals(exception.getMessage(), "Zero length string");
    }

    @Test
    public void Test_NPE(){
        Assertions.assertThrows(NullPointerException.class,() -> Integer.decode(null));
    }

    @Test
    public void Test_signed_numbers_parsing(){
        Assertions.assertEquals(-256, Integer.decode("-256"));
        Assertions.assertEquals(0x6, Integer.decode("+0x6"));
    }

    @Test
    public void Test_string_with_wrong_format() {
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("A"));
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("0xZ"));
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("36-"));
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("09"));
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("#H"));
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("one"));
    }

    @Test
    public void Test_sign_in_thee_wrong_place(){
        Assertions.assertEquals(Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("#-B")).getMessage(),
                "Sign character in wrong position");
        Assertions.assertEquals(Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("0x-A")).getMessage(),
                "Sign character in wrong position");
        Assertions.assertEquals(Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("0-5")).getMessage(),
                "Sign character in wrong position");
        Assertions.assertEquals(Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("#+B")).getMessage(),
                "Sign character in wrong position");
        Assertions.assertEquals(Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("0x+A")).getMessage(),
                "Sign character in wrong position");
        Assertions.assertEquals(Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("0+5")).getMessage(),
                "Sign character in wrong position");
    }

    @Test
    public void Test_Integer_MIN_MAX_values(){
        int max = Integer.decode("2147483647");
        int min = Integer.decode("-2147483648");
        Assertions.assertEquals(Integer.MAX_VALUE, max);
        Assertions.assertEquals(Integer.MIN_VALUE, min);
    }

    @Test
    public void Test_big_numbers(){
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("09000000000"));
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("2147483648"));
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("0xFFFFFFFF"));
    }

    @Test
    public void Test_small_numbers(){
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("-09000000000"));
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("-2147483649"));
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("-0xFFFFFFFF"));
    }

    @Test
    public void Test_Decimal() {
        Assertions.assertEquals(125, Integer.decode("125"));
        Assertions.assertEquals(-125, Integer.decode("-125"));
        Assertions.assertEquals(125, Integer.decode("+125"));
    }

    @Test
    public void Test_Octet() {
        Assertions.assertEquals(15, Integer.decode("017"));
        Assertions.assertEquals(-15, Integer.decode("-017"));
        Assertions.assertEquals(15, Integer.decode("+017"));
    }

    @Test
    public void Test_Hex() {
        Assertions.assertEquals(10, Integer.decode("0xA"));
        Assertions.assertEquals(-10, Integer.decode("-0xA"));
        Assertions.assertEquals(10, Integer.decode("+0xA"));
        Assertions.assertEquals(10, Integer.decode("#A"));
        Assertions.assertEquals(-10, Integer.decode("-#A"));
        Assertions.assertEquals(10, Integer.decode("+#A"));
    }


}
