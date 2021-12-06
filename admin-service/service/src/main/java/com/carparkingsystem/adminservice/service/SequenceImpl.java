package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.constants.ExceptionType;
import com.carparkingsystem.adminservice.exception.SequenceException;
import com.carparkingsystem.adminservice.model.SequenceID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceImpl implements ISequence{

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public long getNextSequenceId(String key) throws SequenceException {
        //get sequence id
        Query query = new Query(Criteria.where("_id").is(key));
        //increase sequence id by 1
        Update update = new Update();
        update.inc("seq", 1);
        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);
        //this is the magic happened.
        SequenceID seqId = mongoOperation.findAndModify(query, update, options, SequenceID.class);
        //if no id, throws SequenceException
        //optional, just a way to tell user when the sequence id is failed to generate.
        if (seqId == null) {
            throw new SequenceException(ExceptionType.ID_NOT_FOUND.getMessage() + key);
        }
        return seqId.getSeq();
    }
}
