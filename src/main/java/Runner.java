import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Runner {
    public static void main(String[] args) {
    	
      //Determine active profile from Maven properties
        String activeProfile = System.getProperty("activeProfile");
        System.out.println("Active Profile : " + activeProfile);

      //Load configuration based on the active profile
        String configFileName = System.getProperty("configFile");
        System.out.println("Config FileName  : " + configFileName);
        if (configFileName == null) {
            System.out.println("Please specify a valid configuration file.");
            return;
        }

    	Properties properties = new Properties();
    	try (InputStream input = Runner.class.getClassLoader().getResourceAsStream(configFileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find the specified config file");
                return;
            }
    	    properties.load(input);
    	} catch (IOException e) {
    	    e.printStackTrace();
    	    return;
    	}

        // Set the path to your project directory
        String projectDirectory = properties.getProperty("project.directory");
        System.out.println("projectDirectory : "+ projectDirectory);
        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(properties.getProperty("maven.home"))); // Set your Maven home directory
        System.out.println("maven.home : "+ properties.getProperty("maven.home"));

        invoker.setWorkingDirectory(new File(projectDirectory));

        InvocationRequest request = new DefaultInvocationRequest();
        request.setGoals(List.of("clean", "install"));

        try {
            invoker.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}