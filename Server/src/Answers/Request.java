package Answers;

import Commands.Command;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 3057652508928000982L;
    private Command command;
    private Object[] args;

    public Request(Command command, Object[] args) {
        this.command = command;
        this.args = args;
    }

    public Command getCommand() {
        return command;
    }

    public Object[] getArgs() {
        return args;
    }
}
