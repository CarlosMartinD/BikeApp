package com.bkool.rest;

import com.bkool.port.usecase.Authentication;
import com.bkool.rest.api.LoginApi;
import com.bkool.rest.model.GenerateJWT200ResponseModel;
import com.bkool.rest.model.GenerateJWTRequestModel;
import com.bkool.usecase.params.AuthenticationParams;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController implements LoginApi {

    private final Authentication authentication;

    @Override
    public ResponseEntity<GenerateJWT200ResponseModel> generateJWT(GenerateJWTRequestModel generateJWTRequestModel) {
        User user = new User(generateJWTRequestModel.getUsername(), generateJWTRequestModel.getPassword());
        String token = authentication.execute(new AuthenticationParams(user));
        GenerateJWT200ResponseModel loginPost200ResponseModel = new GenerateJWT200ResponseModel();
        loginPost200ResponseModel.setToken(token);
        return ResponseEntity.ok(loginPost200ResponseModel);
    }
}
