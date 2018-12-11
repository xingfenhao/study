/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2018 All Rights Reserved.
 */
package id;

/**
 *
 * twitter�ڰѴ洢ϵͳ��MySQLǨ�Ƶ�Cassandra�Ĺ���������Cassandraû��˳��ID���ɻ��ƣ������Լ�������һ��ȫ��ΨһID���ɷ���Snowflake��
 * 1 41λ��ʱ�����У���ȷ�����룬41λ�ĳ��ȿ���ʹ��69�꣩
 * 2 10λ�Ļ�����ʶ��10λ�ĳ������֧�ֲ���1024���ڵ㣩
 * 3 12λ�ļ���˳��ţ�12λ�ļ���˳���֧��ÿ���ڵ�ÿ�������4096��ID��ţ� ���λ�Ƿ���λ��ʼ��Ϊ0��
 * �ŵ㣺�����ܣ����ӳ٣�������Ӧ�ã���ʱ������ ȱ�㣺��Ҫ�����Ŀ����Ͳ���
 *
 * @author fenghao.xing
 * @version : IdWorker.java, v 0.1 2018-04-18 14:04 fenhao.xing Exp $
 */
public class IdWorker {
    private final long workerId;
    private final static long twepoch = 1288834974657L;
    private long sequence = 0L;
    private final static long workerIdBits = 4L;
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits = 10L;
    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;
    private long lastTimestamp = -1L;
    public IdWorker(final long workerId) {
        super();
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    this.maxWorkerId));
        }
        this.workerId = workerId;
    }
    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & this.sequenceMask;
            if (this.sequence == 0) {
                System.out.println("###########" + sequenceMask);
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(
                        String.format(
                                "Clock moved backwards. Refusing to generate id for %d milliseconds",
                                this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift))
                | (this.workerId << this.workerIdShift) | (this.sequence);
        System.out.println("timestamp:" + timestamp + ",timestampLeftShift:"
                + timestampLeftShift + ",nextId:" + nextId + ",workerId:"
                + workerId + ",sequence:" + sequence);
        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

/**
 * 3853460031997952
 * */
    public static void main(String[] args){
        IdWorker worker2 = new IdWorker(2);
        System.out.println(worker2.nextId());
    }

}
