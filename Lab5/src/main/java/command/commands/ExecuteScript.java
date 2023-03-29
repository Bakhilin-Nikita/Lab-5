package command.commands;

import command.HelperController;
import command.inputCmdCollection.InputCommands;
import command.noInputCmdCollection.NoInputCommands;
import org.apache.commons.lang3.text.WordUtils;

import java.io.*;
import java.util.*;

public class ExecuteScript extends Invoker {
    private static final String COMMAND_NAME = ExecuteScript.class.getSimpleName();

    private HelperController helperController;
    Map<String, Invoker> commands = new HashMap<>();

    //А этот мап для команд С входными данными
    Map<String, Invoker> inputCommands = new HashMap<>();


    public ExecuteScript(HelperController helperController) {
        this.helperController = helperController;
    }

    public static String getCOMMAND_NAME() {
        return COMMAND_NAME;
    }

    @Override
    public void doCommand(String e) {
        try {
            execute(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    public void execute(String pathToFile) throws IOException {
        //pathToFile = "/home/studs/s367069/" + pathToFile;
        if (new File(pathToFile).exists()) {
            // настраиваем поток ввода
            getHelperController().setReader(new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile))));

            NoInputCommands noInputCommands = new NoInputCommands(helperController);
            setCommands(noInputCommands.getCommands());

            InputCommands inputCommands = new InputCommands(helperController);
            setInputCommands(inputCommands.getInputCommands());


            // читаем первую строку из файла
            String cmd = getHelperController().getReader().readLine();

            boolean flag = false;

            if (cmd != null) {
                while (cmd != null) {
                    if (!cmd.contains(" ")) {
                        cmd = reformatCmd(cmd);
                        //  No input commands
                        for (Map.Entry<String, Invoker> entry : getCommands().entrySet()) {
                            String key = entry.getKey();
                            if (cmd.equals(key)) {
                                System.out.println("Активирована команда " + entry.getValue().getClass().getSimpleName());
                                entry.getValue().doCommand(cmd);
                            }
                        }

                        for (Map.Entry<String, Invoker> entry : getInputCommands().entrySet()) {
                            String key = entry.getKey();
                            if (cmd.equals(key)) {
                                System.out.println("Активирована команда " + entry.getValue().getClass().getSimpleName());
                                entry.getValue().doCommand(cmd);
                            }
                        }
                    } else {
                        cmd = reformatCmd(cmd);
                        String[] command = cmd.split(" ", 2);
                        if (command[0].equals("ExecuteScript")) {
                            if (getHelperController().addToPaths(command[1])) {
                                doCommand(command[1]);
                                getHelperController().addToPaths(command[1]);
                            } else {
                                System.out.println("Ты не сможешь сломать её!");
                            }
                        } else {
                            for (Map.Entry<String, Invoker> entry : getInputCommands().entrySet()) {
                                String key = entry.getKey();
                                String commandKey = command[0];
                                String commandValue = command[1];
                                if (commandKey.equals(key)) {
                                    System.out.println("Активирована команда " + entry.getValue().getClass().getSimpleName());
                                    entry.getValue().doCommand(commandValue);
                                }
                            }
                        }
                    }
                    cmd = this.helperController.getReader().readLine();
                }
            }
        } else {
            System.out.println("Файл не найден!");
        }
        System.out.println("Скрипт успешно выполнен!");
        this.getHelperController().getPaths().clear();
    }


    private String reformatCmd(String cmd) {

        if (cmd.contains(" ")) {
            String[] arr = cmd.split(" ", 2);
            cmd = arr[0].replaceAll("_", " ");
            cmd = WordUtils.capitalize(cmd);
            cmd = cmd.replaceAll(" ", "");
            cmd = cmd.concat(" " + arr[1]);
        } else {
            cmd = cmd.replaceAll("_", " ");
            cmd = WordUtils.capitalize(cmd);
            cmd = cmd.replaceAll(" ", "");
        }
        return cmd;
    }

    private boolean checkOnExecuteScript(String cmd) {
        if (cmd != null) {
            String[] arr = cmd.split(" ", 2);
            return Objects.equals(arr[0], "execute_script");
        }

        return false;
    }

    public HelperController getHelperController() {
        return helperController;
    }

    public Map<String, Invoker> getInputCommands() {
        return inputCommands;
    }

    public Map<String, Invoker> getCommands() {
        return commands;
    }

    public void setHelperController(HelperController helperController) {
        this.helperController = helperController;
    }

    public void setInputCommands(Map<String, Invoker> inputCommands) {
        this.inputCommands = inputCommands;
    }

    public void setCommands(Map<String, Invoker> commands) {
        this.commands = commands;
    }
}


