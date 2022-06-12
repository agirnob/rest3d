package com.tau.rest3d;

import com.tau.rest3d.Owners.Owner;
import com.tau.rest3d.Owners.OwnerRepository;
import com.tau.rest3d.users.UserRepository;
import com.tau.rest3d.users.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
//import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DataJpaTest
@Sql(scripts = "/create-data.sql")
@Sql(scripts = "/cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
class Rest3dApplicationTests {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserService userService;

    private final OwnerRepository ownerRepository;

    private final Owner dbOwn = new Owner("name", "dbOwn@öail.com", LocalDate.of(1000, Month.DECEMBER, 23), "testPassword");
    private final Owner dbOwn1 = new Owner("name1", "dbOwn@öail1.com", LocalDate.of(1001, Month.DECEMBER, 23), "testPassword1");
    private final Owner dbOwn2 = new Owner("name2", "dbOwn@öail2.com", LocalDate.of(1002, Month.DECEMBER, 23), "testPassword2");

    Rest3dApplicationTests(UserRepository userRepository, UserService userService, OwnerRepository ownerRepository) throws NoSuchAlgorithmException {
        this.userRepository = userRepository;
        this.userService = userService;
        this.ownerRepository = ownerRepository;

    }


    @ParameterizedTest
    @ValueSource(strings = {"t e s t"})
    void didSpacesChanged(String stringWithSpaces) {
        String strTest = stringWithSpaces.replace(' ', '_');
        assertEquals(strTest, userService.changeSpaces(stringWithSpaces));

    }

    @Test
    void uploadworkswithSpaces() throws IOException {
        File file = new File("/home/agirnob/Downloads/USMC_EGA.stl");
        assertNotEquals(file.getName(), userService.getFile(file.getName()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"t e s t"})
    void timeOfSignup(LocalDate dateOfSignUp) {
       assertSame(dbOwn2, ownerRepository.findOwnerByDateOfSignUp(dateOfSignUp));
    }

    @ParameterizedTest
    @ValueSource(strings = {"name", "name1", "name2"})
    void userNameTest(String userName) {
      assertSame(dbOwn1, ownerRepository.findOwnerByUserName(userName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"dbOwn@öail.com", "dbOwn@öail1.com", "dbOwn@öail2.com"})
    void emailTest(String email) {

        assertSame(dbOwn, ownerRepository.findOwnerByEmail(email));
    }

    @Test
    void passoword(String password) {
        assertSame(dbOwn, ownerRepository.findOwnerByPassword("testPassword"));
    }


}
