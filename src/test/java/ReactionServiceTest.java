import com.rpggame.game.UserStats;
import com.rpggame.items.ShortSword;
import com.rpggame.map.Flooring;
import com.rpggame.map.Place;
import com.rpggame.monsters.Rat;
import com.rpggame.service.ReactionService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ReactionServiceTest {

    @Test
    public void playerShouldHitMonsterAndMonsterStillLive(){
        UserStats userStats = new UserStats();
        userStats.setLevel(1);
        userStats.setRightHand(new ShortSword());
        Rat rat = new Rat();
        Place place = new Flooring();
        place.setMonster(rat);
        assertFalse(ReactionService.playerHit(place, rat, userStats));
    }

    @Test
    public void playerShouldHitMonsterAndMonsterDead(){
        UserStats userStats = new UserStats();
        userStats.setLevel(10);
        userStats.setRightHand(new ShortSword());
        Rat rat = new Rat();
        Place place = new Flooring();
        place.setMonster(rat);
        assertTrue(ReactionService.playerHit(place, rat, userStats));
    }

    @Test
    public void monsterShouldHitMonsterAndPlayerStillLive(){
        UserStats userStats = new UserStats();
        userStats.setHealth(100);
        userStats.setLevel(1);
        userStats.setRightHand(new ShortSword());
        Rat rat = new Rat();
        Place place = new Flooring();
        place.setMonster(rat);
        assertFalse(ReactionService.monsterHit(rat, userStats));
    }

    @Test
    public void monsterShouldHitMonsterAndPlayerDead(){
        UserStats userStats = new UserStats();
        userStats.setHealth(5);
        userStats.setLevel(1);
        userStats.setRightHand(new ShortSword());
        Rat rat = new Rat();
        Place place = new Flooring();
        place.setMonster(rat);
        assertTrue(ReactionService.monsterHit(rat, userStats));
    }

}
