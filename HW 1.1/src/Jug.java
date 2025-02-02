public class Jug {
    private String name;
    private int maxCapacity;
    private int currAmount;

    public Jug() {
        this("", 0, 0);
    }

    public Jug(String name, int maxCapacity, int currAmount) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.currAmount = currAmount;
    }

    public Jug(Jug jug) {
        if (jug != null) {
            this.name = jug.name;
            this.maxCapacity = jug.maxCapacity;
            this.currAmount = jug.currAmount;
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrAmount() {
        return currAmount;
    }

    public void setCurrAmount(int newAmount) {
        currAmount = newAmount;
    }

    public String getName() {
        return name;
    }

    public void pourInto(Jug otherJug) {
        otherJug.currAmount = otherJug.currAmount + this.currAmount;
        if (otherJug.currAmount > otherJug.maxCapacity) {
            this.currAmount = otherJug.currAmount - otherJug.maxCapacity;
            otherJug.currAmount = otherJug.maxCapacity;
        } else {
            this.currAmount = 0;
        }
    }

    @Override
    public String toString() {
        return "" + currAmount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Jug)) {
            return false;
        } else {
            Jug jug = (Jug) obj;
            return this.getName().equals(jug.getName())
                    && this.getCurrAmount() == jug.getCurrAmount()
                    && this.getMaxCapacity() == jug.getMaxCapacity();
        }
    }
}
