import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MainTest
{
    private final Optional<String> key;
    private final Optional<Map<String,String>> map;
    private final Optional<String> defaultValue;
    private final String result;

    private static final Map<String,String> MAP = new HashMap() {{ put("aKey","aValue"); }};

    public MainTest(Optional<String> key, Optional<Map<String,String>> map, Optional<String> defaultValue, String result){

        this.key = key;
        this.map = map;
        this.defaultValue = defaultValue;
        this.result = result;
    }

    @Test
    public void test() throws Exception
    {
        assertEquals(result, new Main().lookup(key, map, defaultValue));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {

                //  KEY                         MAP                     DEFAULT VALUE               RESULT
                {   Optional.empty(),           Optional.empty(),       Optional.empty(),           "undefined"},

                {   Optional.of("aKey"),        Optional.empty(),       Optional.empty(),           "undefined"},
                {   Optional.empty(),           Optional.of(MAP),       Optional.empty(),           "undefined"},
                {   Optional.empty(),           Optional.empty(),       Optional.of("default"),     "undefined"},

                {   Optional.of("aKey"),        Optional.of(MAP),       Optional.empty(),           "undefined"},
                {   Optional.empty(),           Optional.of(MAP),       Optional.of("default"),     "undefined"},
                {   Optional.of("aKey"),        Optional.empty(),       Optional.of("default"),     "undefined"},

                {   Optional.of("anotherKey"),  Optional.of(MAP),       Optional.of("default"),     "default"},

                {   Optional.of("aKey"),        Optional.of(MAP),       Optional.of("default"),     "aValue"},

        });
    }
}
