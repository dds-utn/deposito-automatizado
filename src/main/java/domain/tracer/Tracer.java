package domain.tracer;

import java.time.LocalDateTime;

public class Tracer {
    private static Tracer instance = null;
    public static Boolean isActive = true;

    public static Tracer getInstance() {
        if(instance==null){
            instance = new Tracer();
        }
        return instance;
    }

    private Tracer(){

    }

    public void showTrace(String trace){
        if(isActive)
            System.out.println(LocalDateTime.now() + ": " + trace);
    }
}