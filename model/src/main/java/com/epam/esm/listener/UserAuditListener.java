package com.epam.esm.listener;

import com.epam.esm.dao.audit.UserAuditDao;
import com.epam.esm.entity.User;
import com.epam.esm.entity.audit.AuditOperationEnum;
import com.epam.esm.entity.audit.UserAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Component
public class UserAuditListener {

    private static UserAuditDao userAuditDao;

    @PostUpdate
    public void auditUpdate(User entity) {
        UserAudit userAudit = new UserAudit(entity, AuditOperationEnum.UPDATE);
        userAuditDao.save(userAudit);
    }

    @PostPersist
    public void auditInsert(User entity) {
        UserAudit userAudit = new UserAudit(entity, AuditOperationEnum.INSERT);
        userAuditDao.save(userAudit);
    }

    @PostRemove
    public void auditDelete(User entity) {
        UserAudit userAudit = new UserAudit(entity, AuditOperationEnum.DELETE);
        userAuditDao.save(userAudit);
    }

    @Autowired
    public void initUserAuditDao(UserAuditDao userAuditDao) {
        UserAuditListener.userAuditDao = userAuditDao;
    }
}
