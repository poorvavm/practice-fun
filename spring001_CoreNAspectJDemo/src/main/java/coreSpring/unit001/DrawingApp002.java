package coreSpring.unit001;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp002 {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("coreSpring/springXMLs/app002.xml");
        Triangle triangle = (Triangle) context.getBean("triangleid");
        triangle.draw();
        
    }

}