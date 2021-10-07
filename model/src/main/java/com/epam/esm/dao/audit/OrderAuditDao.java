package com.epam.esm.dao.audit;

import com.epam.esm.entity.audit.OrderAudit;

public interface OrderAuditDao {
    void save(OrderAudit orderAudit);
}
