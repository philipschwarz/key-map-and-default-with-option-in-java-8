import java.util.Map;
import java.util.Optional;

public class Main {

    public String lookup(Optional<String> aKey, Optional<Map<String,String>> aMap, Optional<String> aValue)
    {
        return  aKey.flatMap( key ->
                    aMap.flatMap( map ->
                        aValue.map( value ->
                            map.getOrDefault(key,value)
                        )
                    )
                ).orElse("undefined");
    }
}
