package com.epam.esm.dao.audit;

import com.epam.esm.entity.GiftCertificateTag;
import com.epam.esm.entity.audit.GiftCertificateTagAudit;

public interface GiftCertificateTagAuditDao {
    void save(GiftCertificateTagAudit entity);
}
