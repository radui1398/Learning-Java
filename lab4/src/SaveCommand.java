public class SaveCommand extends Command{
    public SaveCommand(Catalog catalog) {
        super(catalog);
    }

    public void run(String[] args) throws Exception {
        if (args.length < 2){
            throw new IllegalArgumentException("2 arguments are required.");
        }
        catalog.save(args[1]);
    }
}
