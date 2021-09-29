package com.epam.esm.dao.audit;

import com.epam.esm.entity.audit.UserAudit;

public interface UserAuditDao {
    void save(UserAudit userAudit);
}
