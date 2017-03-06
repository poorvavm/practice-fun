package coreSpring.unit014;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp014 {

    public static void main(String[] args) {
        ApplicationContext context_1 = new ClassPathXmlApplicationContext(
                "coreSpring/springXMLs/app014.xml");
        Shape shape = (Shape) context_1.getBean("circle");
        shape.draw();
        
    }

}