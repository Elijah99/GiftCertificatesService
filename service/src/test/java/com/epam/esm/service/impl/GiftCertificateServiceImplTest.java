package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.GiftCertificateTagDao;
import com.epam.esm.dao.TagDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class GiftCertificateServiceImplTest {

    @InjectMocks
    private GiftCertificateService giftCertificateService;
    @Mock
    private GiftCertificateDao giftCertificateDao;
    @Mock
    private TagDao tagDao;
    @Mock
    private GiftCertificateTagDao giftCertificateTagDao;

    @Test
    public void testFindAll(){

    }
}
