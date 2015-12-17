import static org.junit.Assert.*;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class InventorySystemTest {

    InventorySystem inventorySystem;

	@Test
	public void testJunit() {
		assertTrue(true);
	}

    @Before
    public void setup() throws Exception {
        List<Item> items;
        items = new ArrayList<Item>();
        //String name, int sellIn(days), int quality
        items.add(new Item("School Uniform", 10, 20));
        items.add(new Item("Wine", 2, 0));
        items.add(new Item("Poultry", 5, 7));
        items.add(new Item("Gold", 0, 80));
        items.add(new Item("Concert Ticket", 15, 20));
        items.add(new Item("Chocolate Eclair", 3, 6));

        inventorySystem = new InventorySystem(items);
    }

    @Test
    public void testSpecialBehaviours() throws Exception {
        inventorySystem.updateQuality();

        List<Item> items = inventorySystem.getItems();
        for (Item i : items) {
            //*	"Wine" actually increases in Quality the older it gets
            if (i.getName().equals("Wine")) {
                 // Ensure its quality has increased
                Assert.assertTrue(i.getQuality() > 0);
            }
            // * "Gold" never has to be sold or decreases in Quality
            if (i.getName().equals("Gold")) {
                Assert.assertEquals(80, i.getQuality());
                Assert.assertFalse(i.getSellIn() < 0);
            }

            // * "Concert Ticket", like Wine, increases in Quality as it's SellIn value reduces (i.e as it gets closer to the concert).
            if (i.getName().equals("Concert Ticket")) {
                Assert.assertTrue(i.getQuality() > 20);
                Assert.assertEquals(21, i.getQuality());
                // Ensure concert ticket has 14 days left
                Assert.assertEquals(14, i.getSellIn());

                // Iterate for 4 times, to get the concert ticket down to 10 days left
                for (int n = 0; n < 4; n++) {
                    inventorySystem.updateQuality();

                }
                Assert.assertEquals(10, i.getSellIn());
                Assert.assertEquals(25, i.getQuality());

                int currentQuality = i.getQuality();
                // Quality increases by 2 when there are 10 days or less
                for (int j = 0; j < 5; j++) {
                    inventorySystem.updateQuality();
                    Assert.assertEquals(currentQuality + ((j+1) * 2), i.getQuality());
                }

                Assert.assertEquals(5, i.getSellIn());
                Assert.assertEquals(35, i.getQuality());
                currentQuality = i.getQuality();
                // and by 3 when there are 5 days or less but Quality drops to 0 after the concert
                for (int m = 0; m < 5; m++) {
                    inventorySystem.updateQuality();
                    Assert.assertEquals(currentQuality + ((m+1) * 3), i.getQuality());
                }
                Assert.assertEquals(0, i.getSellIn());
                Assert.assertEquals(50, i.getQuality());

                // Ensure quality drops to 0 after the concert
                inventorySystem.updateQuality();
                Assert.assertEquals(-1, i.getSellIn());
                Assert.assertEquals(0, i.getQuality());
            }

        }
    }

}
