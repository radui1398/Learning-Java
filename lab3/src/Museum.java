public class Museum extends Node implements Payable {
    int numberExponeates=0;
    int fee;

    public Museum(String name) {
        super(name);
    }

    public int getNumberExponeates() {
        return numberExponeates;
    }

    public void setNumberExponeates(int numberExponates) {
        this.numberExponeates = numberExponates;
    }

    @Override
    public void setEntryFee(int fee) {
        this.fee=fee;
    }

    @Override
    public int getEntryFee() {
        return this.fee;
    }

    @Override
    public void getType() {
        System.out.println("This is a Museum.");
    }
}


