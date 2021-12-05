package backend.controllersSwagger;

import backend.modelsSwagger.UserModel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserController extends BaseController {

    private RequestSpecification userApi() {
        return apiClient("/user");
    }

    public Response addUserToStore(UserModel user) {
        return userApi()
                .when()
                .body(user)
                .post();
    }

    public Response getAddedUser(String userName) {
        return userApi()
                .when()
                .get("/{username}", userName);
    }

    public Response updateUserInStore(UserModel user) {
        return userApi()
                .when()
                .body(user)
                .put();
    }

    public Response deleteAddedUser(String userName) {
        return userApi()
                .when()
                .delete("/{username}", userName);
    }
}
