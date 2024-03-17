package com.bkool.rest;

import com.bkool.port.usecase.Authentication;
import com.bkool.rest.model.GenerateJWT200ResponseModel;
import com.bkool.rest.model.GenerateJWTRequestModel;
import com.bkool.usecase.params.GetBikeParams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private Authentication authentication;

    @InjectMocks
    private LoginController loginController;

    @Test
    void shouldGenerateJwtToken() {
        GenerateJWTRequestModel generateJWTRequestModel = new GenerateJWTRequestModel();
        generateJWTRequestModel.setUsername("user");
        generateJWTRequestModel.setPassword("pass");

        ArgumentCaptor<GetBikeParams> getBikeParamsArgumentCaptor = ArgumentCaptor.forClass(GetBikeParams.class);
        when(authentication.execute(any())).thenReturn("token");

        ResponseEntity<GenerateJWT200ResponseModel> generateJWT200ResponseModelResponseEntity = loginController.generateJWT(generateJWTRequestModel);

        assertEquals(HttpStatus.OK, generateJWT200ResponseModelResponseEntity.getStatusCode());
        assertEquals("token", generateJWT200ResponseModelResponseEntity.getBody().getToken());

    }
}