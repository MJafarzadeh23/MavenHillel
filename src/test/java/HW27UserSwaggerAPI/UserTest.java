package HW27UserSwaggerAPI;

import backend.controllersSwagger.UserController;
import backend.modelsSwagger.UserModel;
import backend.straight.UserSwagger;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest extends ObjSetUp {

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder().log(LogDetail.ALL).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }

    UserController userController = new UserController();

    @Test
    public void addNewUserObj() {
        setUp();
        UserSwagger targetUser = new UserSwagger(id, userName, firstName, lastName, email, password, phone, userStatus);

        Response createdUserResponse = RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/user")
                .contentType(ContentType.JSON)
                .when()
                .body(targetUser)
                .post();

        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);
        Assert.assertEquals(createdUserResponse.then().extract().jsonPath().getLong("message"), id);
    }

    @Test
    public void addNewUserBuilder() {
        setUp();
        UserModel targetUser = UserModel.builder()
                .id(id)
                .username(userName)
                .email(email)
                .password(password).build();

        Response createdUserResponse = userController.addUserToStore(targetUser);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);
        Assert.assertEquals(createdUserResponse.then().extract().jsonPath().getLong("message"), id);

        Response actualUserResponse = userController.getAddedUser(userName);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);

        UserModel actualAddedUser = actualUserResponse.as(UserModel.class);
        Assert.assertEquals(actualAddedUser, targetUser);
    }

    @Test
    public void deleteAddedUserBuilder() {
        setUp();
        UserModel targetUser = UserModel.builder()
                .id(id)
                .username(userName).build();

        Response createdUserResponse = userController.addUserToStore(targetUser);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);
        Assert.assertEquals(createdUserResponse.then().extract().jsonPath().getLong("message"), id);

        Response deletedUserResponse = userController.deleteAddedUser(userName);
        Assert.assertEquals(deletedUserResponse.getStatusCode(), 200);

        Response actualUserResponse = userController.getAddedUser(userName);
        Assert.assertEquals(actualUserResponse.getStatusCode(), 404);
    }

    @Test
    public void updateAddedUserBuilder() {
        setUp();
        UserModel targetUser = UserModel.builder()
                .id(id)
                .username(userName)
                .email(email)
                .password(password).build();

        Response createdUserResponse = userController.addUserToStore(targetUser);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);
        Assert.assertEquals(createdUserResponse.then().extract().jsonPath().getLong("message"), id);

        long updatedID = faker.number().randomNumber();
        targetUser.setId(updatedID);
        /*String updatedUserName = faker.name().username();
        targetUser.setUsername(updatedUserName);*/

        Response updatedUserResponse = userController.updateUserInStore(targetUser);
        Assert.assertEquals(updatedUserResponse.getStatusCode(), 200);
        Assert.assertEquals(updatedUserResponse.then().extract().jsonPath().getLong("message"), updatedID);

        Response actualUpdatedUserResponse = userController.getAddedUser(userName);
        Assert.assertEquals(actualUpdatedUserResponse.getStatusCode(), 200);

        UserModel actualAddedUser = actualUpdatedUserResponse.as(UserModel.class);
        Assert.assertEquals(actualAddedUser, targetUser);
    }
}
