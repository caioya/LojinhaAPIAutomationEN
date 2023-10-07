package dataFactory;

import pojo.UserPojo;

public class UserDataFactory {
    public static UserPojo inputUserLoginAndUserPassword(){
        UserPojo user = new UserPojo();
        user.setUsuarioLogin("testAPICaioEN");
        user.setUsuarioSenha("testAPICaioEN");
        return user;
    }
}
