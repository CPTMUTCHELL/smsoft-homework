package com.smsoft;
import com.smartsoft.main.MainApplication;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mojo(name = "vv", defaultPhase = LifecyclePhase.INITIALIZE, threadSafe = true)
public class MyMojo extends AbstractMojo {



    public void execute() throws MojoExecutionException, MojoFailureException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        getLog().info("Loading started at: "+ formatter.format(new Date()));
        //mvn com.smsoft:name-maven-plugin:vv
    }

}