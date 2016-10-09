package test;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests Network class.
 * 
 */

import network.Network;
import network.NetworkPath;

public class NetworkTest {

    @Test
    public void testSendPacket() throws Exception {
        // Create a network with a default latency of 1 ms between nodes
        Network net = Network.createWithLatency(1);

        // Interconnect network elements
        net.connect("A", "D");                // Uses default network latency
        net.connect("B", "D");
        net.connect("C", "E");
        net.connect("I", "G");
        net.connect("J", "F");
        net.connect("K", "H", 10);            // Connect K computer to H router with a 10ms latency
        net.connect("D", "E", 3);             // D to E has a 3ms latency
        net.connect("D", "F", 2);             // D to F has a 2ms latency
        net.connect("E", "F", 4);             // E to F has a 4ms latency
        net.connect("E", "G", 5);             // E to G has a 5ms latency
        net.connect("G", "F", 3);             // G to F has a 3ms latency
        net.connect("F", "H", 5);             // F to H has a 5ms latency
        
        // Simulate sending a packet from "C" to "J"
        NetworkPath path = net.sendPacket("C", "J");

        // Check followed path
        String followedPath = path.toString();
        
        
        /*Please put space in expected path because 
        I used build function; ArrayList.toString, it returns with spaces
        I can remove the spaces by removeAll build in function but I did not use it. 
        Therefore, I just wanted to write so that you can test it. 
        */
        
        String expectedPath = "[C, E, F, J]";
        assertEquals("Packet didn't follow expected path.", expectedPath, followedPath);

        // Check taken time
        int takenTime = path.getTime();
        int expectedTime = 6;
        assertEquals("Packet didn't take the expected time.", expectedTime, takenTime);
        
     // Simulate sending a packet from "I" to "E"
        NetworkPath path2 = net.sendPacket("I", "E");
        followedPath = path2.toString();
        expectedPath = "[I, G, E]";
        assertEquals("Packet didn't follow expected path.", expectedPath, followedPath);

        // Check taken time
        takenTime = path2.getTime();
        expectedTime = 6;
        assertEquals("Packet didn't take the expected time.", expectedTime, takenTime);
    }
}
