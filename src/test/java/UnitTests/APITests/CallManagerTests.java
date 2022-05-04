package UnitTests.APITests;

import mitchell.dnd.dndzombieorganiser.api.APIConnectionManager;
import mitchell.dnd.dndzombieorganiser.api.CallManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CallManagerTests {

    @Test
    public void negativeCallManagerReturnsNull() {
        CallManager callManager = new CallManager("");
        assertEquals(false, callManager.getJson().isPresent());
        assertEquals(500, callManager.getStatusCode());
    }
}
