package com.epam.esm.listener;

import com.epam.esm.dao.audit.GiftCertificateAuditDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.audit.AuditOperationEnum;
import com.epam.esm.entity.audit.GiftCertificateAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public class GiftCertificateAuditListener {

    private static GiftCertificateAuditDao giftCertificateAuditDao;

    @PostUpdate
    public void auditUpdate(GiftCertificate entity) {
        GiftCertificateAudit giftCertificateAudit = new GiftCertificateAudit(entity, AuditOperationEnum.UPDATE);
        giftCertificateAuditDao.save(giftCertificateAudit);
    }

    @PostPersist
    public void auditInsert(GiftCertificate entity) {
        GiftCertificateAudit giftCertificateAudit = new GiftCertificateAudit(entity, AuditOperationEnum.INSERT);
        giftCertificateAuditDao.save(giftCertificateAudit);
    }

    @PostRemove
    public void auditDelete(GiftCertificate entity) {
        GiftCertificateAudit giftCertificateAudit = new GiftCertificateAudit(entity, AuditOperationEnum.DELETE);
        giftCertificateAuditDao.save(giftCertificateAudit);
    }

    @Autowired
    public void initGiftCertificateAuditDao(GiftCertificateAuditDao giftCertificateAuditDao) {
        GiftCertificateAuditListener.giftCertificateAuditDao = giftCertificateAuditDao;
    }
}
