package learn.welcome;

import learn.welcome.ui.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

@ComponentScan
@PropertySource("classpath:application.properties")
public class App {
    public static void main(String[] args) {

        ApplicationContext container = new AnnotationConfigApplicationContext(App.class);
        Controller controller = container.getBean(Controller.class);
        controller.run();
    }

}
