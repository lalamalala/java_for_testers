import java.io.File;

public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello,world");
 /*       System.out.println(2 + 2);
        System.out.println(2 -2);
        System.out.println(2 * 2);
        System.out.println(2 / 2);
        System.out.println( 2 + 2 * 2);
        System.out.println("Hello" + " world");
        System.out.println("2+2= "+4);
        System.out.println("2+2= "+2+2);
        System.out.println("2+2= "+(2+2)); */

   //     var configFile = "build.gradle"; // path to file
        var configFile = new File("sandbox/build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());

    }
}
