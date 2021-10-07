package com.epam.esm.dao.impl;

import com.epam.esm.DaoTestData;
import com.epam.esm.TestApplication;
import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.entity.User;
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

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = TestApplication.class)
@ComponentScan("com.epam.esm")
@EntityScan({"com.epam.esm.entity", "com.epam.esm.entity.audit"})
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserDaoImplTest {

    private static final long COUNT_EXPECTED = 4;
    private static final BigInteger FIRST_USER_ID = new BigInteger("1");

    private static final QueryParameters DEFAULT_QUERY_PARAMETERS = new QueryParameters(1, 10, null, null, null, null);


    @Autowired
    private UserDao userDao;

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindById() {
        Optional<User> actual = userDao.findById(FIRST_USER_ID);
        assertEquals(DaoTestData.FIRST_USER_OPTIONAL, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testFindByParameters() {
        List<User> actual = userDao.findByParameters(DEFAULT_QUERY_PARAMETERS);
        assertEquals(DaoTestData.ALL_USERS, actual);
    }

    @Test
    @Sql(scripts = {"/db_drop_script.sql", "/schema.sql", "/db_init_data.sql"})
    public void testCountShouldReturnNumberOfRows() {
        long actual = userDao.count();
        assertEquals(COUNT_EXPECTED, actual);
    }
}
