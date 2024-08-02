//package com.soccer.pjt.repository;
//
//import com.soccer.pjt.entity.Player;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.ZonedDateTime;
//import java.time.temporal.ChronoUnit;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
////@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class PlayerDataRepositoryTest {
//
//    @Autowired
//    private PlayerDataRepository playerDataRepository;
//
//    @BeforeEach
//    public void setUp(){
//        playerDataRepository.deleteAll();
//    }
//
//    @Test
//    public void testSaveAndFindPlayer(){
//        Player player = new Player();
//        player.setIdx();
//        player.setX_center(110);
//        player.setY_center(200);
//        player.setWidth(50);
//        player.setHeight(50);
//        player.setId(124);
//        player.setTeam_class(2);
//        player.setBack_number(99);
//        ZonedDateTime now = ZonedDateTime.now();
//        player.setTimestamp(now);
//
//
//        Player savedPlayer = playerDataRepository.save(player);
//
//
//        Player foundPlayer = playerDataRepository.findById(savedPlayer.getIdx()).orElse(null);
//
//
//        assertNotNull(foundPlayer);
//        assertEquals(savedPlayer.getIdx(), foundPlayer.getIdx());
//        assertEquals(savedPlayer.getX_center(), foundPlayer.getX_center());
//        assertEquals(savedPlayer.getY_center(), foundPlayer.getY_center());
//        assertEquals(savedPlayer.getWidth(), foundPlayer.getWidth());
//        assertEquals(savedPlayer.getHeight(), foundPlayer.getHeight());
//        assertEquals(savedPlayer.getId(), foundPlayer.getId());
//        assertEquals(savedPlayer.getTeam_class(), foundPlayer.getTeam_class());
//        assertEquals(savedPlayer.getBack_number(), foundPlayer.getBack_number());
//        assertEquals(savedPlayer.getTimestamp(), foundPlayer.getTimestamp());
//
//        assertEquals(0, ChronoUnit.SECONDS.between(savedPlayer.getTimestamp(), foundPlayer.getTimestamp()));
//    }
//
//}
