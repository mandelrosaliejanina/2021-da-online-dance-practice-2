package at.htl.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

@QuarkusTest
class BookingTest {






    @Order(10)
    @Test
    @Transactional
    public void create() {


        User samuel = new User("samu123", "Samuel", "Haus", "ksnlf", "STUDENT");
        samuel.persist();

        Level level = new Level("Gold","1. Level");
        level.persist();
        Course course = new Course("Latino für Anfänger","Latino für Anfänger",level);
        course.persist();


        Booking booking = new Booking(samuel,course);
        booking.persist();

    }


}
