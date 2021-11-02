package com.epam.esm.listener;

import com.epam.esm.dao.audit.TagAuditDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.audit.AuditOperationEnum;
import com.epam.esm.entity.audit.TagAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Component
public class TagAuditListener {

    private static TagAuditDao tagAuditDao;

    @PostUpdate
    public void auditUpdate(Tag entity) {
        TagAudit tagAudit = new TagAudit(entity, AuditOperationEnum.UPDATE);
        tagAuditDao.save(tagAudit);
    }

    @PostPersist
    public void auditInsert(Tag entity) {
        TagAudit tagAudit = new TagAudit(entity, AuditOperationEnum.INSERT);
        tagAuditDao.save(tagAudit);
    }

    @PostRemove
    public void auditDelete(Tag entity) {
        TagAudit tagAudit = new TagAudit(entity, AuditOperationEnum.DELETE);
        tagAuditDao.save(tagAudit);
    }

    @Autowired
    public void initTagAuditDao(TagAuditDao tagAuditDao) {
        TagAuditListener.tagAuditDao = tagAuditDao;
    }
}
