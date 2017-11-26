package test;

import junit.framework.TestCase;
import org.junit.Test;
import org.ulco.GraphicsObject;
import org.ulco.JSON;
import org.ulco.Point;
import org.ulco.Circle;
import org.junit.Assert.*;

public class CircleTest extends TestCase {

    @Test
    public void testType() throws Exception {
        Circle c = new Circle(new Point(0, 0),10);

        assertTrue(c instanceof Circle);
        assertTrue(c instanceof GraphicsObject);
    }

    @Test
    public void testJson() throws Exception {
        Circle c = new Circle(new Point(0,0), 10);

        assertEquals(JSON.GraphicToJSON(c), "{ type: circle, center: { type: point, x: 0.0, y: 0.0 }, radius: 10.0 }");
    }

    @Test
    public void testCopy() throws Exception {
        Circle c = new Circle(new Point(0,0), 10);



        assertEquals(JSON.GraphicToJSON(c), JSON.GraphicToJSON(c.copy()));
    }
}