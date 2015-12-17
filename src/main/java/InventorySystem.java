import java.util.ArrayList;
import java.util.List;

public class InventorySystem {

	private List<Item> items = null;

    public InventorySystem(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {

        for (int i = 0; i < items.size(); i++)
        {
            // as long as it is not Wine AND Concert Ticket
            if ((!"Wine".equals(items.get(i).getName())) && !"Concert Ticket".equals(items.get(i).getName()))
            {
                // as long as quality is bigger than 0 + not a wine nor concert ticket
                if (items.get(i).getQuality() > 0)
                {
                    // as long as it is a freshly baked bread + quality is bigger than 0 and (!wine && !concert ticket)
                    if ("Freshly baked bread".equals(items.get(i).getName())) {
                        items.get(i).setQuality(items.get(i).getQuality() - 2);
                    }
                    // as long as it is not gold + not freshly baked bread and quality > 0 and (!wine && !concert ticket)
                    else if (!"Gold".equals(items.get(i).getName()))
                    {
                        // degrade quality by 1
                        items.get(i).setQuality(items.get(i).getQuality() - 1);
                    }
                }
            }
            else // otherwise item is wine OR concert ticket
            {
                // as long as quality is lower than 50
                if (items.get(i).getQuality() < 50)
                {
                    // increment the quality of the item by 1
                    items.get(i).setQuality(items.get(i).getQuality() + 1);

                    // as long as it is a concert ticket
                    if ("Concert Ticket".equals(items.get(i).getName()))
                    {
                        // as long as its sell date is in less than 11 days
                        if (items.get(i).getSellIn() < 11)
                        {
                            // as long as the concert ticket quality is lower than 50
                            if (items.get(i).getQuality() < 50)
                            {
                                // increment the quality of the concert ticket by 1
                                items.get(i).setQuality(items.get(i).getQuality() + 1);
                            }
                        }

                        // as long as the concert ticket sell in date is in less than 6 days
                        if (items.get(i).getSellIn() < 6)
                        {
                            // and its quality is lower than 50
                            if (items.get(i).getQuality() < 50)
                            {
                                // increase the concert ticket quality by 1
                                items.get(i).setQuality(items.get(i).getQuality() + 1);
                            }
                        }
                    } // end if "Concert Ticket"
                } // end if (item == wine || item == concert ticket) && quality < 50
            } // end else (item == wine || item == concert ticket)

            // as long as the item is gold
            if (!"Gold".equals(items.get(i).getName()))
            {
                // decrease its sell in days
                items.get(i).setSellIn(items.get(i).getSellIn() - 1);
            }

            // as long as item has negative number of sell in days
            if (items.get(i).getSellIn() < 0)
            {
                // as long as the item is not a wine + its sell in days is negative
                if (!"Wine".equals(items.get(i).getName()))
                {
                    // and as long as the item is not concert ticket + not a wine AND its sell in days is negative
                    if (!"Concert Ticket".equals(items.get(i).getName()))
                    {
                        // as long as the item has a quality > 0 + (!wine && !concert ticket) AND sell in days is < 0
                        if (items.get(i).getQuality() > 0)
                        {
                            // as long as the item is not gold + has a quality > 0 AND (!wine && !concert ticket) AND sellInDays < 0
                            if (!"Gold".equals(items.get(i).getName()))
                            {
                                // decrease the item's quality
                                items.get(i).setQuality(items.get(i).getQuality() - 1);
                            }
                        } // end as long as the item has a quality > 0 + (!wine && !concert ticket) AND sell in days is < 0
                        // quality is negative + !wine && !concert ticket) AND sell in days is < 0

                    } // end as long as the item is not concert ticket + not a wine AND its sell in days is negative
                    else // item is concert ticket + has negative number of sell in days
                    {
                        // Quality drops to 0 after the concert
                        items.get(i).setQuality(items.get(i).getQuality() - items.get(i).getQuality());
                    } // end item is wine or concert ticket
                } // end as long as the item is not a wine
                else // item is a wine + has negative number of sell in days
                {
                    if (items.get(i).getQuality() < 50)
                    {
                        items.get(i).setQuality(items.get(i).getQuality() + 1);
                    }
                }
            } // end as long as item has negative number of sell in days
        } // end iterating over items
    }

    // Getters & Setters
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}
