package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void testStringConversion() {
        assertEquals(
                "[E][\u2717] borrow book (at: Aug 25 2020 02:30 PM)",
                new Event("borrow book", LocalDateTime.parse("2020-08-25T14:30")).toString());
        assertEquals(
                "[E][\u2713] return book (at: Aug 31 2020 08:00 PM)",
                new Event("return book", true, LocalDateTime.parse("2020-08-31T20:00")).toString());
    }

    @Test
    void storeTest() {
        assertEquals(
                "E F borrow book /at 2020-08-25T14:30",
                new Event("borrow book", LocalDateTime.parse("2020-08-25T14:30")).store());
        assertEquals(
                "E T return book /at 2020-08-31T20:00",
                new Event("return book", true, LocalDateTime.parse("2020-08-31T20:00")).store());
    }
}
