package com.epam.esm.listener;

import com.epam.esm.dao.audit.UserAuditDao;
import com.epam.esm.entity.User;
import com.epam.esm.entity.audit.AuditOperationEnum;
import com.epam.esm.entity.audit.UserAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Component
public class UserAuditListener {

    private static UserAuditDao userAuditDao;

    @PreUpdate
    public void auditUpdate(User entity) {
        UserAudit userAudit = new UserAudit(entity, AuditOperationEnum.UPDATE);
        userAuditDao.save(userAudit);
    }

    @PrePersist
    public void auditInsert(User entity) {
        UserAudit userAudit = new UserAudit(entity, AuditOperationEnum.INSERT);
        userAuditDao.save(userAudit);
    }

    @PreRemove
    public void auditDelete(User entity) {
        UserAudit userAudit = new UserAudit(entity, AuditOperationEnum.DELETE);
        userAuditDao.save(userAudit);
    }

    @Autowired
    public void initUserAuditDao(UserAuditDao userAuditDao) {
        UserAuditListener.userAuditDao = userAuditDao;
    }
}
