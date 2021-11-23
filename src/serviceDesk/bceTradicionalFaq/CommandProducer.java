package serviceDesk.bceTradicionalFaq;


import java.util.ArrayList;
import java.util.List;

public abstract class CommandProducer {
    private List<CommandExecution> executionList = new ArrayList<>();

    public void addExecution(CommandExecution exe){
        executionList.add(exe);
    }

    public void removerExecution(CommandExecution exe){
        executionList.remove(exe);
    }

    public void executeCommand(String command) {
        for (CommandExecution exe : executionList) {
            exe.execute(command);
        }
    }
}
