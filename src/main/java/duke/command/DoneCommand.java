package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * The DoneCommand class contains methods pertaining to the DoneCommand.
 */
public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            if (taskList.size() >= taskNumber && taskNumber > 0) {
                Task task = taskList.getTask(taskNumber);
                task.markAsDone();
                storage.updateData(task.store(), taskNumber);
                ui.done(task);
            } else {
                throw new DukeException("Oops! No such task!");
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
