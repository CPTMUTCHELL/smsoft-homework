import com.smsoft.GenericUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class GenericUtilTest {
    private Collection<Integer> testCol;
    private Collection<Integer> anotherTestCol;
    private final Collection<Integer> uniqueColExpected = new ArrayList<>(List.of(1,2,3,11,22,33));
    private final Map<String,Integer> mergedMapExpected = new HashMap<>(Map.of("k1",1,"k2",22,"k3",333,"k4",88800));
    private final Map<String,Integer> map1 = new HashMap<>(Map.of("k1",333,"k2",22,"k3",1));
    private final Map<String,Integer> map2 = new HashMap<>(Map.of("k1",1,"k2",22,"k3",333,"k4",88800));

    @Before
    public void init() {
       testCol=new ArrayList<>(List.of(1,2,3,4));
        anotherTestCol = new ArrayList<>(List.of(11,22,11,22,33,4));

    }

    @Test
    public void containAllElementsTest(){
        boolean actualTrue = GenericUtil.containAllElements(testCol, 2, 3);
        boolean actualFalse = GenericUtil.containAllElements(testCol, 11,2, 3);
        assertTrue(actualTrue);
        assertFalse(actualFalse);
    }
    @Test
    public void uniqueTestWithoutOrderTest(){
        Collection<Integer> uniqueCol = GenericUtil.unique(testCol, anotherTestCol);
        assertTrue(uniqueColExpected.size() == uniqueCol.size() && uniqueColExpected.containsAll(uniqueCol) && uniqueCol.containsAll(uniqueColExpected));

    }
    @Test
    public void mergeMapTest(){
        Map<String,Integer> mergedMap = GenericUtil.mergeMap(map1, map2);
        assertTrue(mergedMap.size() == mergedMapExpected.size()
                && mergedMap.keySet().containsAll(mergedMapExpected.keySet())
                && mergedMap.values().containsAll(mergedMapExpected.values()));

    }
}
