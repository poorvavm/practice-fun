package coreSpring.unit013;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp013 {

    public static void main(String[] args) {
        ApplicationContext context_1 = new ClassPathXmlApplicationContext(
                "coreSpring/springXMLs/app013.xml");
        Shape shape = (Shape) context_1.getBean("circleId");
        shape.draw();
        
    }

}