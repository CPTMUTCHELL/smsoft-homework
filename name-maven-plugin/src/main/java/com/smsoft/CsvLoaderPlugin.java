package com.smsoft;
import com.smsoft.service.Service;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Mojo(name = "vv", defaultPhase = LifecyclePhase.PROCESS_CLASSES, threadSafe = true)
public class CsvLoaderPlugin extends AbstractMojo {
    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final ExecutorService service = Executors.newFixedThreadPool(2);
    private final TimeUnit time = TimeUnit.SECONDS;
    public void execute() {
        var callables = new ArrayList<Callable<Void>>();
        var runnables = new ArrayList<Runnable>();
        AtomicReference<Date> start= new AtomicReference<>();
        AtomicReference<Date> finish= new AtomicReference<>();
        runnables.add(()->{
            start.set(new Date());
            getLog().info("Loading started at: "+ FORMATTER.format(start.get()));
            while (!Service.flag) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getLog().info("counter = " + Service.counter);

            }
        });
        runnables.add(()->{
            try {
                Service.main(new String[]{"C:\\Users\\1\\Desktop\\sm\\smsoft-homework\\csv-subsequent-loader\\src\\main\\resources\\test_case.csv"});
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        runnables.forEach(r->callables.add(toCallable(r)));

        try {
            service.invokeAll(callables);
        } catch (Exception e) {
            getLog().info("Exception occurred "+e.getMessage());
        }
        finish.set(new Date());
        long diff=time.convert(finish.get().getTime() - start.get().getTime(),TimeUnit.MILLISECONDS);
        getLog().info("Loading finished at "+FORMATTER.format(new Date())+ " total time is "+ diff+ "s");


//        mvn com.smsoft:name-maven-plugin:vv
    }
    private Callable<Void> toCallable(final Runnable runnable) {
        return () -> {
            runnable.run();
            return null;
        };
    }

}
