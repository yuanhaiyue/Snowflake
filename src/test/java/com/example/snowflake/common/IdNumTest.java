package com.example.snowflake.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IdNumTest {

    private final IdNum idNum=new IdNum(0);

    long id=1456872984371200100L;

    @DisplayName("Long 最大值")
    @Test
    void loginMax(){
        System.out.println();
    }

    @DisplayName(value = "生成一百万个id")
    @Test
    void generateId() {
        int count=1000000;

        Set<Long> idSet=new HashSet<>();
        for(int i=0;i<count;i++){
            idSet.add(idNum.nextId());
        }
        Assertions.assertEquals(count,idSet.size());
    }
    @DisplayName(value = "生成4096个数据并打印")
    @ParameterizedTest(name = "给bosType为{1}的实体生成ID共{0}个如下:")
    @CsvSource({
            "4097,    1"
            // ,"4097,  1"
    })
    public void print(int count,int bosType){

        for(int i=0;i<count;i++){
            System.out.println(idNum.generateId(bosType));
        }

    }
}