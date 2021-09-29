package com.epam.esm.dao.audit;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.audit.TagAudit;

public interface TagAuditDao {
    void save(TagAudit tagAudit);

}
