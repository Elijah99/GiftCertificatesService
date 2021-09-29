package com.epam.esm.listener;

import com.epam.esm.dao.audit.OrderAuditDao;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.audit.AuditOperationEnum;
import com.epam.esm.entity.audit.OrderAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Component
public class OrderAuditListener {

    private static OrderAuditDao orderAuditDao;

    @PreUpdate
    public void auditUpdate(Order entity) {
        OrderAudit orderAudit = new OrderAudit(entity, AuditOperationEnum.UPDATE);
        orderAuditDao.save(orderAudit);
    }

    @PrePersist
    public void auditInsert(Order entity) {
        OrderAudit orderAudit = new OrderAudit(entity, AuditOperationEnum.INSERT);
        orderAuditDao.save(orderAudit);
    }

    @PreRemove
    public void auditDelete(Order entity) {
        OrderAudit orderAudit = new OrderAudit(entity, AuditOperationEnum.DELETE);
        orderAuditDao.save(orderAudit);
    }

    @Autowired
    public void initOrderAuditDao(OrderAuditDao orderAuditDao) {
        OrderAuditListener.orderAuditDao = orderAuditDao;
    }
}
