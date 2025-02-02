import java.util.ArrayList;
import java.util.*;

public class Step {
    private int stepNumber;
    private static Set<Integer> measurementsFound = new HashSet<>();
    private Set<Integer> newMeasurements = new HashSet<>();
    private ArrayList<State> newStates = new ArrayList<>();
    private ArrayList<State> currentStates = new ArrayList<>();
    private static ArrayList<State> allStatesFound = new ArrayList<>();

    public Step(int stepNumber, ArrayList<State> currentStates) {
        this.stepNumber = stepNumber;
        this.currentStates = currentStates;
    }

    public void createAllCombination() {
        if (stepNumber != 0) {
            for (State state : currentStates) {
                for (int i = 0; i < state.getSize(); i++) {
                    for (int j = 0; j < state.getSize(); j++) {
                        if (i != j) {
                            State tempState = new State(state);
                            tempState.getJug(i).pourInto(tempState.getJug(j));

                            if (state.equals(tempState)) {
                                continue;
                            }

                            if (!allStatesFound.contains(tempState)) {
                                allStatesFound.add(tempState);
                                newStates.add(tempState);
                            }
                            else {
                                updateList(allStatesFound, tempState);
                            }
                        }
                    }
                }
            }
        }
        else {
            for (State state : currentStates) {
                allStatesFound.add(state);
                newStates.add(state);
            }
        }
        updateMeasurement();
    }

    public static void printAllFoundState() {
        System.out.println(allStatesFound);
    }


    public String printNewStates() {
        String returnString = "";

        for (State state : newStates) {
            if (returnString.length() > 0) {
                returnString = returnString.concat(", "); // Add comma only after the first element
            }
            returnString = returnString.concat(state.toString());
        }

        return returnString;
    }



    public ArrayList<State> getNewStates() {
        return newStates;
    }

    public void updateList(ArrayList<State> list, State lookupValue) {
        for (State state : list) {
            if (state.equals(lookupValue)) {
                state.increaseParent();
            }
        }
    }

    public void updateMeasurement() {
        for (State state : newStates) {
            for (Jug jug : state.getAllJugs()) {
                if (!measurementsFound.contains(jug.getCurrAmount())) {
                    measurementsFound.add(jug.getCurrAmount());
                    newMeasurements.add(jug.getCurrAmount());
                }
            }
        }
    }

    public String printNewMeasurements() {
        String returnString = "";

        for (Integer measurememt : newMeasurements) {
            if (returnString.length() > 0) {
                returnString = returnString.concat(", "); // Add comma only after the first element
            }
            returnString = returnString.concat(measurememt.toString());
        }

        return returnString;
    }

    public int printStepNumber() {
        return stepNumber;
    }

    public void cleanup() {
        measurementsFound.clear();
        newMeasurements.clear();
        newStates.clear();
        currentStates.clear();
        allStatesFound.clear();
    }
}
