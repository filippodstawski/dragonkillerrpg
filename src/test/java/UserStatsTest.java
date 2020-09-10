import com.rpggame.game.UserStats;
import com.rpggame.items.HealthPotion;
import com.rpggame.items.Item;
import com.rpggame.items.LongSword;
import com.rpggame.items.WoodenShield;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class UserStatsTest {

    @Test
    public void fromInventoryToLeftHandTest(){
        UserStats userStats = new UserStats();
        userStats.setInventory(new ArrayList<Item>());
        WoodenShield woodenShield = new WoodenShield();
        userStats.addToInventory(woodenShield);
        userStats.fromInventoryToLeftHand(0);
        assertEquals(userStats.getLeftHand(), woodenShield);
    }

    @Test
    public void fromInventoryToRightHandTest(){
        UserStats userStats = new UserStats();
        userStats.setInventory(new ArrayList<Item>());
        LongSword longSword = new LongSword();
        userStats.addToInventory(longSword);
        userStats.fromInventoryToRightHand(0);
        assertEquals(userStats.getRightHand(), longSword);
    }

    @Test
    public void addToInventoryTest(){
        UserStats userStats = new UserStats();
        userStats.setInventory(new ArrayList<Item>());
        HealthPotion healthPotion = new HealthPotion();
        userStats.addToInventory(healthPotion);
        assertEquals(userStats.getInventory().get(0), healthPotion);
    }

    @Test
    public void updateLevelTest(){
        UserStats userStats = new UserStats();
        userStats.setLevel(1);
        userStats.setToNextLevel(100);
        userStats.setExperience(1000);
        userStats.updateLevel();
        assertEquals(userStats.getLevel(), 5);
    }

}
