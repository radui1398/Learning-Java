public class Museum extends Node implements Payable {
    private int numberExponeates=0;
    private double fee;

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
    public void setEntryFee(double fee) {
        this.fee=fee;
    }

    @Override
    public double getEntryFee() {
        return this.fee;
    }

    @Override
    public void getType() {
        System.out.println("This is a Museum.");
    }
}


