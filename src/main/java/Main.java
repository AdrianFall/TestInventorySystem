import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 17/12/2015.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Starting the Inventory System");

        List<Item> items;
        items = new ArrayList<Item>();
        //String name, int sellIn(days), int quality
        items.add(new Item("School Uniform", 10, 20));
        items.add(new Item("Wine", 2, 0));
        items.add(new Item("Poultry", 5, 7));
        items.add(new Item("Gold", 0, 80));
        items.add(new Item("Concert Ticket", 15, 20));
        items.add(new Item("Chocolate Eclair", 3, 6));

        InventorySystem inventorySystem = new InventorySystem(items);
        System.out.println("updating quality");
        inventorySystem.updateQuality();
    }
}
