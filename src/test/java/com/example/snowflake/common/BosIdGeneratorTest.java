package com.example.snowflake.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BosIdGeneratorTest {

    @Test
    void compressUUID(){
//        int count=1000000;
//
//        Set<String> idset=new HashSet<String>();
//        for(int i=0;i<count;i++){
//            idset.add(BosIdGenerator.compressUUID());
//        }
//        Assertions.assertEquals(count,idset.size());
    }

    @ParameterizedTest(name = "给bostype为{1}的实体生成ID共{0}个如下:")
    @CsvSource({
            "100,    DDA"
           // ,"50,  AAA"
    })
    public void print(int count,String bostype){
        for(int i=0;i<count;i++){
//            System.out.println(BosIdGenerator.compressUUID()+bostype);
        }
    }
}