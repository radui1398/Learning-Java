package catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Shell that can be used to interact with various commands regarding the catalog.
 */
public class Shell{
    static private Shell instance;

    static public Shell getInstance(){
        if (instance == null){
            instance = new Shell();
        }
        return instance;
    }

    /**
     * Constructor that adds all available commands to the entity.
     */
    private Shell(){
        commands = new HashMap<>();
        commands.put("add", AddCommand.getInstance());
        commands.put("list", ListCommand.getInstance());
        commands.put("save", SaveCommand.getInstance());
        commands.put("load", LoadCommand.getInstance());
        commands.put("open", OpenCommand.getInstance());
        commands.put("report", ReportCommand.getInstance());
        commands.put("svg", SvgCommand.getInstance());

        catalog = Catalog.getInstance();
    }


    Catalog catalog;
    HashMap<String, Command> commands;

    /**
     * Starts the shell itself and keep it running to receive commands.
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String line = scanner.nextLine();
            String[] args = splitArguments(line);

            if (args[0].equals("exit")){
                return;
            }

            if (!commands.containsKey(args[0])){
                System.out.println("Command does not exist.");
                continue;
            }

            try {
                commands.get(args[0]).run(args);
            } catch (IOException | IllegalArgumentException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Retrieves commands available
     * @return a hashmap that contains both the command string and the actual object
     */
    public HashMap<String, Command> getCommands(){
        return commands;
    }

    /**
     * Used to split arguments given in the shell
     * @param line input line received
     * @return list of arguments to be processed
     */
    private String[] splitArguments(String line) {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> args = new ArrayList<>();

        boolean inQuotes = false;
        for (int i = 0; i < line.length(); ++i){
            char c = line.charAt(i);
            if (c == '\"'){
                inQuotes = !inQuotes;
            }
            else if (c == ' '){
                if (inQuotes){
                    builder.append(c);
                }
                else{
                    if (builder.length() > 0){
                        args.add(builder.toString());
                    }
                    builder.setLength(0);
                }
            }
            else{
                builder.append(c);
            }
        }
        if (builder.length() > 0){
            args.add(builder.toString());
        }
        String[] strings = new String[args.size()];
        strings = args.toArray(strings);
        return strings;
    }

}
