import java.util.ArrayList;

public class State {
    private ArrayList<Jug> jugs = new ArrayList<>();
    private int numOfParrent = 1;

    public State(Jug jugA, Jug jugB, Jug jugC) {
        jugs.add(jugA);
        jugs.add(jugB);
        jugs.add(jugC);
    }

    public State(State state) {
        if (state != null) {
            for (Jug jug : state.jugs) {
                this.jugs.add(new Jug(jug));
            }
            this.numOfParrent = state.numOfParrent;
        }
    }

    public ArrayList<Jug> getAllJugs() {
        return jugs;
    }

    public int getSize() {
        return jugs.size();
    }

    public Jug getJug(int index) {
        return jugs.get(index);
    }

    @Override
    public String toString() {
        return jugs + (numOfParrent > 1 ? "p" + numOfParrent + " " : "");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof State)) {
            return false;
        } else {
            State state = (State) obj;
            return this.jugs.equals(state.jugs);
        }
    }

    public void increaseParent() {
        numOfParrent++;
    }
}
