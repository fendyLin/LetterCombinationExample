package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LetterCombination {

    private String[] letters;
    private Integer[] digits;

    public LetterCombination(String[] letters, Integer[] digits) {
        this.letters = letters;
        this.digits = digits;
    }

    public void setDigits(Integer[] digits) {
        this.digits = digits;
    }

    public List<String> getCombinations(Integer... digits) {
        this.digits = digits;
        return getCombinations();
    }

    public List<String> getCombinations() {
        Long beginTime = System.currentTimeMillis();
        List<String> results = new ArrayList<>();
        if (letters == null || letters.length == 0 || digits == null || digits.length == 0) {
            return results;
        }
        //获取符合条件的字符集
        Map<Integer, String[]> maps = Stream.of(digits)
                // 过滤出合法数字
                .filter(num -> num != null && num > 1 && num.intValue() < (letters.length + 2))
                .map(num -> new CharsMapping(num, letters[num - 2].split("")))
                .collect(Collectors.toMap(CharsMapping::getKey,chars->chars.getValue(),(v1,v2)->v1));
        if(maps.isEmpty()) {
            return results;
        }
        Integer[] keys = maps.keySet().toArray(new Integer[0]);
        if(keys.length == 1) {
            Collections.addAll(results, maps.get(keys[0]));
            return results;
        }
        String[] chars = maps.get(keys[0]);
        List<String> tempChars = new LinkedList<>();
        Collections.addAll(tempChars, chars);
        recurring(tempChars, maps, keys, 1);
        results.addAll(tempChars);
        Long endTime = System.currentTimeMillis();

        //System.out.println(String.format("letters combination exec use  %d ms.", endTime - beginTime));
        return results;
    }

    private void recurring(List<String> chars, Map<Integer, String[]> maps, Integer[] keys, int index) {
        if(index < keys.length) {
            List<String> newChars = new LinkedList<>();
            String[] chars2 = maps.get(keys[index]);
            index++;
            for (int j = 0; j < chars.size(); j++) {
                for (int j2 = 0; j2 < chars2.length; j2++) {
                    newChars.add(chars.get(j).concat(chars2[j2]));
                }
            }
            if(index < keys.length) {
                recurring(newChars, maps, keys, index);
            }
            chars.clear();
            chars.addAll(newChars);
        }
    }

    class CharsMapping {
        private Integer key;
        private String[] value;

        public CharsMapping(Integer key, String[] value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String[] getValue() {
            return value;
        }
    }
}
