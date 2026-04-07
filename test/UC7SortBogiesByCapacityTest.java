import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Case for UC7: Sort Bogies by Capacity (Comparator)
 */
public class UC7SortBogiesByCapacityTest {

    // Mock Bogie class to simulate your application's data structure
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public int getCapacity() { return capacity; }
        public String getName() { return name; }
    }

    @Test
    @DisplayName("Verify that bogies are sorted from lowest to highest capacity")
    void testBogieSortingLogic() {
        // 1. Arrange: Create a list in random order
        List<Bogie> consist = new ArrayList<>();
        consist.add(new Bogie("Sleeper", 72));
        consist.add(new Bogie("First Class", 24));
        consist.add(new Bogie("AC Chair", 56));

        // 2. Act: Apply the Comparator (Business Logic for UC7)
        consist.sort(Comparator.comparingInt(Bogie::getCapacity));

        // 3. Assert: Verify the order by checking the names at each index
        // Expected Order: First Class (24) -> AC Chair (56) -> Sleeper (72)
        assertEquals("First Class", consist.get(0).getName(), "First element should be the lowest capacity");
        assertEquals("AC Chair", consist.get(1).getName(), "Middle element should be AC Chair");
        assertEquals("Sleeper", consist.get(2).getName(), "Last element should be the highest capacity");
    }
}