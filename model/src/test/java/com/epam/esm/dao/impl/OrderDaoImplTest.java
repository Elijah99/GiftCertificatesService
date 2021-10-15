package com.epam.esm.dao.impl;

import com.epam.esm.TestApplication;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.DaoTestData.ALL_ORDERS;
import static com.epam.esm.DaoTestData.DEFAULT_QUERY_PARAMETERS;
import static com.epam.esm.DaoTestData.FIRST_ORDER;
import static com.epam.esm.DaoTestData.FIRST_ORDER_OPTIONAL;
import static com.epam.esm.DaoTestData.FIRST_USER;
import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = TestApplication.class)
@ComponentScan("com.epam.esm")
@EntityScan({"com.epam.esm.entity", "com.epam.esm.entity.audit"})
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class OrderDaoImplTest {


    private static final long FIRST_USER_ORDERS_COUNT_EXPECTED = 2;
    private static final BigInteger ORDER_ID_INVALID = new BigInteger("999");

    @Autowired
    private OrderDao orderDao;

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindByParameters() {
        List<Order> actual = orderDao.findByParameters(DEFAULT_QUERY_PARAMETERS);
        assertEquals(ALL_ORDERS, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindByIdShouldReturnOrderOptionalWhenOrderPresent() {
        Optional<Order> actual = orderDao.findById(FIRST_ORDER.getId());
        assertEquals(FIRST_ORDER_OPTIONAL, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindByIdShouldReturnOptionalEmptyWhenOrderNotPresent() {
        Optional<Order> actual = orderDao.findById(ORDER_ID_INVALID);
        assertEquals(Optional.empty(), actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindByUserId() {
        List<Order> actual = orderDao.findByUserId(FIRST_USER.getId(), DEFAULT_QUERY_PARAMETERS);
        assertEquals(FIRST_USER.getOrders(), actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testCountShouldReturnNumberOfRows() {
        long actual = orderDao.countByUserId(FIRST_USER.getId());
        assertEquals(FIRST_USER_ORDERS_COUNT_EXPECTED, actual);
    }
}
