package com.epam.esm.mapper;

import com.epam.esm.dto.RequestParameters;
import com.epam.esm.entity.QueryParameters;
import com.epam.esm.mapper.impl.RequestParametersMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static com.epam.esm.ServiceTestData.DEFAULT_QUERY_PARAMETERS;
import static com.epam.esm.ServiceTestData.DEFAULT_REQUEST_PARAMETERS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class RequestParametersMapperTest {

    private final RequestParametersMapper requestParametersMapper = new RequestParametersMapper();

    @Test
    public void testMapDtoToEntityDefault() {
        QueryParameters actual = requestParametersMapper.mapDtoToEntity(DEFAULT_REQUEST_PARAMETERS);

        assertEquals(DEFAULT_QUERY_PARAMETERS, actual);
    }

    @Test
    public void testMapEntityToDto() {
        RequestParameters actual = requestParametersMapper.mapEntityToDto(DEFAULT_QUERY_PARAMETERS);

        assertEquals(DEFAULT_REQUEST_PARAMETERS, actual);
    }
}
