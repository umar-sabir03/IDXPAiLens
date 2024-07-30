package com.pilog.mdm.cloud.idxpailens.idgenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class AiQuestionareIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String str="DAL_AI_LENS_QUESTIONARE";
        String uuid= UUID.randomUUID().toString().replace("-","");
        return str+uuid;
    }
}
