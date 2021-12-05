package HW27UserSwaggerAPI;

import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;

public class ObjSetUp {
    protected Faker faker;
    protected long id;
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String phone;
    protected int userStatus;

    @BeforeMethod
    public void setUp() {
        faker = new Faker();
        id = faker.number().randomNumber();
        userName = faker.name().username();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = userName.concat("@gmail.com");
        password = faker.lorem().characters(6);
        phone = faker.phoneNumber().phoneNumber();
        userStatus = faker.number().randomDigit();
    }
}
