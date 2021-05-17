package com.example.snowflake.common;

/**
 * @author 14760
 */
public class IdNum {


    private long sequence;

    //设置一个时间初始值    2^41 - 1   差不多可以用69年

    private long twepoch = 1585644268888L;

    //每毫秒内产生的id数 2 的 12次方

    private long sequenceBits = 12L;

    private long timestampLeftShift = sequenceBits ;

    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    //记录产生时间毫秒数，判断是否是同1毫秒

    private long lastTimestamp = -1L;


    public long getTimestamp() {
        return System.currentTimeMillis();
    }



    public IdNum( long sequence) {
        this.sequence = sequence;
        this.lastTimestamp=timeGen();
    }

    // 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id

    public synchronized long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {

            System.err.printf(
                    "clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把seqence序号给递增1，最多就是4096

        if (lastTimestamp == timestamp) {
            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
            //这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
//            sequence = (sequence + 1) & sequenceMask;
            sequence = (sequence + 1)%4096;
            //当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    //获取当前时间戳

    private long timeGen(){
        return System.currentTimeMillis();
    }

    public  long generateId(Integer characteristic){
        return nextId()*100+characteristic;
    }

}
