package com.example.snowflake.common;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

/**
 * 实体唯一标识符生成规则
 * Created Date : 2020/08/19
 *
 * @author zzk
 */
//public class BosIdGenerator implements IdentifierGenerator{
//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object any) throws HibernateException {
//        return new BosId(getId(any));
//    }
//
//    private static String getId(Object any) {
//        if (any instanceof BosEntity) {
//            String id =(((BosEntity) any).getId()==null)?null: ((BosEntity) any).getId().toString();
//            if (id == null || id.isEmpty()) {
//                String bosId = compressUUID();
//                if (any.getClass().isAnnotationPresent(BosType.class)) {
//                    bosId += any.getClass().getAnnotation(BosType.class).value();
//                }
//                return bosId;
//            }
//            return id;
//        } else {
//            return compressUUID();
//        }
//    }
//
//    public static String compressUUID() {
//        UUID uuid = UUID.randomUUID();
//        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES * 2);
//        byteBuffer.putLong(uuid.getMostSignificantBits());
//        byteBuffer.putLong(uuid.getLeastSignificantBits());
//        byte[] array = byteBuffer.array();
//        return Base64.getUrlEncoder().encodeToString(array).substring(0, 22);
//    }


//}
