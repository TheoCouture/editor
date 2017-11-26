package test;

import junit.framework.TestCase;
import org.junit.Test;
import org.ulco.*;
import org.junit.Assert.*;

public class ColorTest extends TestCase {

    @Test
    public void testColor() throws Exception {
        Circle c = new Circle(new Point(0, 0),10);

        c.setColor(Colors.Color.RED);
        c.setBordercolor(Colors.Color.YELLOW);

        assertTrue(c.getColor() == Colors.Color.RED);
        assertTrue(c.getBordercolor() == Colors.Color.YELLOW);
    }
}