package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Event;
import duke.ui.Ui;

/**
 * The EventCommand class contains methods pertaining to the EventCommand.
 */
public class EventCommand extends Command {

    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(event);
        ui.addTask(event, taskList);
        storage.addData(event.store());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
