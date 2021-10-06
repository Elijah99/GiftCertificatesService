package com.epam.esm.listener;

import com.epam.esm.dao.audit.GiftCertificateTagAuditDao;
import com.epam.esm.entity.GiftCertificateTag;
import com.epam.esm.entity.audit.AuditOperationEnum;
import com.epam.esm.entity.audit.GiftCertificateTagAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Component
public class GiftCertificateTagAuditListener {

    private static GiftCertificateTagAuditDao giftCertificateTagAuditDao;

    @PreUpdate
    public void auditUpdate(GiftCertificateTag entity) {
        GiftCertificateTagAudit giftCertificateTagAudit = new GiftCertificateTagAudit(entity, AuditOperationEnum.UPDATE);
        giftCertificateTagAuditDao.save(giftCertificateTagAudit);
    }

    @PrePersist
    public void auditInsert(GiftCertificateTag entity) {
        GiftCertificateTagAudit giftCertificateTagAudit = new GiftCertificateTagAudit(entity, AuditOperationEnum.INSERT);
        giftCertificateTagAuditDao.save(giftCertificateTagAudit);
    }

    @PreRemove
    public void auditDelete(GiftCertificateTag entity) {
        GiftCertificateTagAudit giftCertificateTagAudit = new GiftCertificateTagAudit(entity, AuditOperationEnum.DELETE);
        giftCertificateTagAuditDao.save(giftCertificateTagAudit);
    }

    @Autowired
    public void initGiftCertificateTagAuditDao(GiftCertificateTagAuditDao giftCertificateTagAuditDao) {
        GiftCertificateTagAuditListener.giftCertificateTagAuditDao = giftCertificateTagAuditDao;
    }

}
