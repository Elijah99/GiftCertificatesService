package com.epam.esm.listener;

import com.epam.esm.dao.audit.OrderAuditDao;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.audit.AuditOperationEnum;
import com.epam.esm.entity.audit.OrderAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Component
public class OrderAuditListener {

    private static OrderAuditDao orderAuditDao;

    @PostUpdate
    public void auditUpdate(Order entity) {
        OrderAudit orderAudit = new OrderAudit(entity, AuditOperationEnum.UPDATE);
        orderAuditDao.save(orderAudit);
    }

    @PostPersist
    public void auditInsert(Order entity) {
        OrderAudit orderAudit = new OrderAudit(entity, AuditOperationEnum.INSERT);
        orderAuditDao.save(orderAudit);
    }

    @PostRemove
    public void auditDelete(Order entity) {
        OrderAudit orderAudit = new OrderAudit(entity, AuditOperationEnum.DELETE);
        orderAuditDao.save(orderAudit);
    }

    @Autowired
    public void initOrderAuditDao(OrderAuditDao orderAuditDao) {
        OrderAuditListener.orderAuditDao = orderAuditDao;
    }
}
