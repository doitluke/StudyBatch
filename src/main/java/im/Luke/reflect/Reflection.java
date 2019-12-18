package im.Luke.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class Reflection {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        try {
            Class steem = Class.forName("im.Luke.reflect.Steem");
            Field[] fields = steem.getDeclaredFields();
            Object object = steem.newInstance();

            object = steem.newInstance();



            Method[] methods = steem.getDeclaredMethods();

            Stream.of(fields).forEach(field -> {
                System.out.println(field.getName());
            });

            for (Method method : methods) {
                try {
                    method.invoke(object, null);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


class Steem {
    public String STEEMIT = "This is steemit";
    public String STEEPSHOT = "This is steepshot";
    public String DTUBE = "This is DTUBE";

    public void upvote() {
        System.out.println("upvote");
    }

}
