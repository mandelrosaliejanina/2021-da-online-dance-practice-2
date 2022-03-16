package at.htl.entity;


import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

@QuarkusTest
class UserTest {



    @Order(10)
    @Test
    @Transactional
    public void create() {


        User user = new User("rosi1234", "Rosalie", "Mandel", "fkjan", "STUDENT");
        user.persist();

    }


}
