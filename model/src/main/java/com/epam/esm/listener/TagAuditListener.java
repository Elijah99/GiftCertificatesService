package com.epam.esm.listener;

import com.epam.esm.dao.audit.TagAuditDao;
import com.epam.esm.dao.audit.TagAuditDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.audit.AuditOperationEnum;
import com.epam.esm.entity.audit.TagAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Component
public class TagAuditListener {

    private static TagAuditDao tagAuditDao;
    
    @PreUpdate
    public void auditUpdate(Tag entity) {
        TagAudit tagAudit = new TagAudit(entity, AuditOperationEnum.UPDATE);
        tagAuditDao.save(tagAudit);
    }

    @PrePersist
    public void auditInsert(Tag entity) {
        TagAudit tagAudit = new TagAudit(entity, AuditOperationEnum.INSERT);
        tagAuditDao.save(tagAudit);
    }

    @PreRemove
    public void auditDelete(Tag entity) {
        TagAudit tagAudit = new TagAudit(entity, AuditOperationEnum.DELETE);
        tagAuditDao.save(tagAudit);
    }

    @Autowired
    public void initTagAuditDao(TagAuditDao tagAuditDao) {
        TagAuditListener.tagAuditDao = tagAuditDao;
    }
}
