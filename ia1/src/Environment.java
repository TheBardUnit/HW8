// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

import java.util.HashMap;
import java.util.Map;

/**
 * Stubbed class that assigns variables
 */
public class Environment {
    Map<String, Double> hashMap = new HashMap<String, Double>();

    /**
     * Put a variable into the environment
     *
     * @param var - the variable
     * @param val - the variables value
     * @return val - the variable
     */
    public double put(String var, double val) {
        hashMap.put(var, val);
        return val;
    }

    /**
     * Gets a variable from the environment
     *
     * @param pos - the position of scanner/parser should look to find the variable
     * @param var - the variable
     * @return - Value stored in variable residing hashMap
     * @throws EvalException
     */
    public double get(int pos, String var) throws EvalException {
        return hashMap.get(var);
    }
}
