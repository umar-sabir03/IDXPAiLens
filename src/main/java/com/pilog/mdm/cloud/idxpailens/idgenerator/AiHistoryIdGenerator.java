package com.pilog.mdm.cloud.idxpailens.idgenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class AiHistoryIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String date = "DAL_AI_LENS_HISTORY_";
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return  date + uuid;
    }
}
