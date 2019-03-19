public class OpenCommand extends Command {
    public OpenCommand(Catalog catalog) {
        super(catalog);
    }

    public void run(String[] args) throws Exception {
        if (args.length < 2){
            throw new IllegalArgumentException("2 arguments are required.");
        }
        catalog.open(args[1]);
    }
}
