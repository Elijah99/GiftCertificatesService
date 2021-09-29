package com.epam.esm.listener;

import com.epam.esm.dao.audit.GiftCertificateAuditDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.audit.AuditOperationEnum;
import com.epam.esm.entity.audit.GiftCertificateAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Component
public class GiftCertificateAuditListener {

    private static GiftCertificateAuditDao giftCertificateAuditDao;

    @PreUpdate
    public void auditUpdate(GiftCertificate entity) {
        GiftCertificateAudit giftCertificateAudit = new GiftCertificateAudit(entity, AuditOperationEnum.UPDATE);
        giftCertificateAuditDao.save(giftCertificateAudit);
    }

    @PrePersist
    public void auditInsert(GiftCertificate entity) {
        GiftCertificateAudit giftCertificateAudit = new GiftCertificateAudit(entity, AuditOperationEnum.INSERT);
        giftCertificateAuditDao.save(giftCertificateAudit);
    }

    @PreRemove
    public void auditDelete(GiftCertificate entity) {
        GiftCertificateAudit giftCertificateAudit = new GiftCertificateAudit(entity, AuditOperationEnum.DELETE);
        giftCertificateAuditDao.save(giftCertificateAudit);
    }

    @Autowired
    public void initGiftCertificateAuditDao(GiftCertificateAuditDao giftCertificateAuditDao) {
        GiftCertificateAuditListener.giftCertificateAuditDao = giftCertificateAuditDao;
    }
}
