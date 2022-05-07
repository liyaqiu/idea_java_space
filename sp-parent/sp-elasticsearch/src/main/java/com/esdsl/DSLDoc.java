package com.esdsl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.*;

/**
 * @author lyq
 * @date 2022/1/16 4:01
 */
@Data
@NoArgsConstructor
@ToString
public class DSLDoc {

    String info;
    String info2;
    Integer number;
    String email;
    Name name;
    HashSet<String> buquan = new HashSet<>();


    public DSLDoc(String info, String info2, Integer number, String email, Name name) {
        String[] words = {"sony","sorry","limit","Liming","short"};
        this.info = info;
        this.info2 = info2;
        this.number = number;
        this.email = email;
        this.name = name;
        for (int i = 0; i <new Random().nextInt(5)+1 ; i++) {
            buquan.add(words[new Random().nextInt(5)]);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt(5));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Name{
        String firstName;
        String lastName;
    }
}
