package com.example;

import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

public class LetterCombinationTest {

    @Test
    public void test1() {
        String[] letters = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Integer[] digits = {2,3};
        LetterCombination letterCombination = new LetterCombination(letters, digits);

        System.out.println("input digits: {2,3}");
        System.out.println(letterCombination.getCombinations());

        System.out.println("input digits: {9}");
        System.out.println(letterCombination.getCombinations(9));
    }

    @Test
    public void test2() {
        /**
         * The program need to support converting the digits from 0 to 99 into letters
         *
         */
        String[] letters = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz", "adb", "ght", "cer"};
        Integer[] digits = {2,3,10};
        LetterCombination letterCombination = new LetterCombination(letters, digits);

        System.out.println(letterCombination.getCombinations());
    }

}
