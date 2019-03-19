public class ListCommand extends Command{
    public ListCommand(Catalog catalog) {
        super(catalog);
    }

    public void run(String[] args) throws Exception {
        catalog.list();
    }
}

