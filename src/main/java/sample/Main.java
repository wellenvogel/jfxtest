package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class Main  {

    private static final String JFXJAR="jfxrt.jar";

    public static void main(String[] args) {

        String javaHome = System.getProperty("java.home");
        System.out.println("javaHome=" + javaHome);
        try {
            File f1 = new File(new File(javaHome, "lib"), JFXJAR);
            File f2 = new File(new File(new File(javaHome, "jre"), "lib"), JFXJAR);
            if (f1.exists()) System.out.println("found "+JFXJAR+" at "+f1.getAbsolutePath());
            if (f2.exists()) System.out.println("found "+JFXJAR+" at "+f2.getAbsolutePath());
            URL ulist[] = new URL[2];
            ulist[0] = f1.toURI().toURL();
            ulist[1] = f2.toURI().toURL();
            SampleClassloader cl = new SampleClassloader(ulist, Main.class.getClassLoader());

            Class app = cl.loadClass("sample.Application");
            Method m=app.getMethod("dolaunch");
            m.invoke(app.getConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
