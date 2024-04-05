package prob1;

import java.util.Random;

/**
 * @author kush
 */
public class SingletonClass {

    private static SingletonClass singletonClass;
    private static int key;

    private SingletonClass() {
        key=generateKey(7);
    }

    public static SingletonClass getSingletonObject(){
        if(singletonClass==null){
            singletonClass=new SingletonClass();
            System.out.println("Key generated Successfully ");
            System.out.println("Your key to activate Avast anti virus is:"+key);
        }
        else{
            System.out.println("Unsuccessful to produce the key....");
        }
        return singletonClass;
    }

    private int generateKey(int n) {
        Random random = new Random();
        int min = (int) Math.pow(10, n - 1);
        int max = (int) Math.pow(10, n) - 1;
        return random.nextInt(max - min + 1) + min;
    }

}
