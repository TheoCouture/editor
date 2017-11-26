package test;

import junit.framework.TestCase;
import org.junit.Test;
import org.ulco.*;
import org.junit.Assert.*;

public class TriangleTest extends TestCase{

    @Test
    public void testType() throws Exception {
        Triangle t = new Triangle(new Point(0, 0),10,30);

        assertTrue(t instanceof Triangle);
        assertTrue(t instanceof GraphicsObject);
    }

    @Test
    public void testJson() throws Exception {
        Triangle t = new Triangle(new Point(0,0), 10,30);

        assertEquals(JSON.GraphicToJSON(t), "{ type: triangle, center: { type: point, x: 0.0, y: 0.0 }, height: 10.0, base: 30.0 }");
    }

    @Test
    public void testCopy() throws Exception {
        Triangle t = new Triangle(new Point(0,0), 10,30);



        assertEquals(JSON.GraphicToJSON(t), JSON.GraphicToJSON(t.copy()));
    }
}
