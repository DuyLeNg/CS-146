import java.util.ArrayList;
import java.util.HashMap;
public class Main {
    public static void main(String[] args) {
        instance(1,
                new Jug("A", 3, 0),
                new Jug("B", 5, 0),
                new Jug("C", 11, 11));

        instance(2,
                new Jug("A", 3, 0),
                new Jug("B", 5, 0),
                new Jug("C", 13, 13));

        instance(3,
                new Jug("A", 3, 0),
                new Jug("B", 5, 0),
                new Jug("C", 17, 17));

        instance(4,
                new Jug("A", 3, 0),
                new Jug("B", 5, 0),
                new Jug("C", 19, 19));

        instance(5,
                new Jug("A", 3, 0),
                new Jug("B", 5, 0),
                new Jug("C", 23, 23));
    }

    public static void instance(int instanceNum, Jug jugA, Jug jugB, Jug jugC) {
        State initialState = new State(jugA, jugB, jugC);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Instance %d:\n\n", instanceNum));
        sb.append(String.format("capacity: {'Jug A': %d, 'Jug B': %d, 'Jug C': %d}.\n",
                jugA.getMaxCapacity(), jugB.getMaxCapacity(), jugC.getMaxCapacity()));
        sb.append(String.format("Initial amount of water: {'Jug A': %d, 'Jug B': %d, 'Jug C': %d}.\n",
                jugA.getCurrAmount(), jugB.getCurrAmount(), jugC.getCurrAmount()));
        sb.append("Step: New Measurements:  New States\n");
        sb.append("----:-----------------:----------------------------------------\n");

        ArrayList<State> initalState = new ArrayList<>();
        initalState.add(initialState);

        ArrayList<Step> steps = new ArrayList<>();

        int i = 0;

        Step initalStep = new Step(i, initalState);
        initalStep.createAllCombination();

        steps.add(initalStep);

        formatOutput(sb, initalStep);

        while (true) {
            i++;
            Step tempStep = new Step(i, steps.get(i - 1).getNewStates());
            tempStep.createAllCombination();
            if (tempStep.getNewStates().isEmpty()) {
                break;
            }
            steps.add(tempStep);
            formatOutput(sb, tempStep);
        }
        sb.append("----:-----------------:----------------------------------------\n");


        System.out.println(sb.toString());
        for (Step step : steps) {
            step.cleanup();
        }
    }

    public static void formatOutput(StringBuilder sb, Step step) {
        // Define the fixed width for the middle column
        int columnWidth = 17;

        // Get the length of the measurement string
        int measurementLength = step.printNewMeasurements().length();

        // Calculate the padding for centering the measurements in the column
        int leftPadding = (columnWidth - measurementLength) / 2;
        int rightPadding = columnWidth - measurementLength - leftPadding;

        // Append the formatted output with centering for the middle column
        sb.append(String.format("%4d: %" + leftPadding + "s%s%" + rightPadding + "s %-30s\n",
                step.printStepNumber(),
                "", // left padding space
                step.printNewMeasurements(),
                "", // right padding space
                step.printNewStates()));
    }
}
