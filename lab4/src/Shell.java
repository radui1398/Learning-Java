import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shell{
    Catalog catalog;
    List<String> commands;
    public Shell(Catalog catalog){
        commands = new ArrayList<String>();
        commands.add("add");
        commands.add("list");
        commands.add("save");
        commands.add("load");
        commands.add("open");
        this.catalog=catalog;
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String line = scanner.nextLine();
            String[] args = splitArguments(line);
            try {
            if (args[0].equals("exit")){
                return;
            }
            if (!commands.contains(args[0])){
                System.out.println("Command does not exist.");
                continue;
            }
            Command command;
                switch (args[0]){
                    case "add" : {
                        command=new AddCommand(catalog);
                        command.run(args);
                        break;
                    }
                    case "list" : {
                        command=new AddCommand(catalog);
                        command.run(args);
                        break;
                    }
                    case "save" : {
                        command=new AddCommand(catalog);
                        command.run(args);
                        break;
                    }
                    case "load" : {
                        command=new AddCommand(catalog);
                        command.run(args);
                        break;
                    }

                    case "open" : {
                        command=new AddCommand(catalog);
                        command.run(args);
                        break;
                    }
                    default: break;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
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
