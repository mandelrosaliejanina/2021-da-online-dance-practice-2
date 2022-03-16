package at.htl.entity;

import at.htl.control.LevelRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CourseTest {


    @Inject
    LevelRepository levelRepository;


    @Order(10)
    @Test
    @Transactional
    public void create(){



        Course course = new Course("Latino für Anfänger","Latino für Anfänger",levelRepository.findById("BRONZE"));
        course.persist();
    }



}
