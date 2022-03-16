package at.htl.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class LevelTest {


    @Order(10)
    @Test
    @Transactional
    public void create() {


        Level level = new Level("Bronze", "1. Level");
        level.persist();

    }



}
