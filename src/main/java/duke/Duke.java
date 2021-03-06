package duke;

import java.nio.file.Path;

import duke.command.Command;
import duke.ui.Ui;
import javafx.scene.layout.VBox;

/**
 * The Duke program chat bot which can help users keep track of tasks.
 */
public class Duke {

    /** The path where the tasks are saved. */
    private static final Path PATH = java.nio.file.Paths.get(".", "data.txt");

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /** Creates and initializes Duke. */
    public Duke() {
        this.storage = new Storage(PATH);
        this.taskList = new TaskList(storage.load());
    }

    /**
     * Sets the ui of Duke.
     *
     * @param dialogContainer The dialogContainer for Ui.
     */
    public void setUi(VBox dialogContainer) {
        this.ui = new Ui(dialogContainer);
    }

    /**
     * Gets the response from Duke.
     *
     * @param input Input of user.
     * @return The response of Duke.
     */
    public boolean getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, storage, ui);
            return c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }

    /** Greets the user. */
    public void greet() {
        assert ui != null : "Ui not specified!";
        ui.greet();
    }
}
