package si.fri.prpo.skupina00.evcharging.services.beans;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@ApplicationScoped
public class LogCallsBean {

    private static final Logger log = Logger.getLogger(LogCallsBean.class.getName());
    private Map<Integer, Integer> mapMethodCounter = new HashMap<>();

    public void methodCallCounter(Integer hashCodeMethod, String name) {
        if(!mapMethodCounter.containsKey(hashCodeMethod)) {  //  Method not yet in hashMap
            mapMethodCounter.put(hashCodeMethod, 1);
        } else {  //  Method is already in hashMap
            mapMethodCounter.replace(hashCodeMethod, mapMethodCounter.get(hashCodeMethod)+1);
        }

        log.info("Metoda " + name + " je bila poklicana: " + mapMethodCounter.get(hashCodeMethod) + "-krat.");

    }
}
