package sample;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by demskm81 on 07.07.2015.
 */
public class SampleClassloader extends URLClassLoader {
    ClassLoader parent;
    public SampleClassloader(URL[] urls, ClassLoader parent) {

        super(urls, parent);
        for (URL u : urls){
            System.out.println("Using URL: "+u.toString());
        }
        this.parent=parent;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.startsWith("sample.")){
            System.out.println("loading class "+name);
            InputStream is=parent.getResourceAsStream(name.replace(".","/")+".class");
            if (is != null){
                byte [] buffer=new byte[64000];
                int len=0;
                try {
                    len=is.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (len > 0) {
                    System.out.println("loaded "+name);
                    return defineClass(name,buffer,0,len );
                }
            }
            else {
                System.out.println("cannot directly load "+name);
            }
        }
        System.out.println("loading "+name+" via super");
        return super.loadClass(name);
    }



}
