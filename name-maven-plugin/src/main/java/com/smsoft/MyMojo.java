package com.smsoft;
import com.smsoft.service.Service;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Mojo(name = "vv", defaultPhase = LifecyclePhase.PROCESS_CLASSES, threadSafe = true)
public class MyMojo extends AbstractMojo {
    ExecutorService service = Executors.newFixedThreadPool(2);



    public void execute() throws MojoExecutionException, MojoFailureException {
        service.execute(()->{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            getLog().info("Loading started at: "+ formatter.format(new Date()));
            while (!Service.flag) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getLog().info("i=  " + Service.i);
            }


        });
        service.execute(()->{
            try {
                Service.main(new String[]{"1"});
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        service.shutdown();



//        mvn com.smsoft:name-maven-plugin:vv
    }

}
