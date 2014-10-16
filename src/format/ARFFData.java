package format;

import java.io.*;
import java.util.*;
import guide.Features;

/**
 *
 * @author Pierre Nugues
 */
// The class to store ARFF compatible data
// The data will be use to train the classifier
public class ARFFData {

    public void saveFeatures(File file, List<Features> featureList, List<String> actionList) throws IOException {
        if (featureList.size() != actionList.size()) {
            System.out.println("Probable mistake. Feature and action lists have different size!");
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

        for (int i = 0; i < featureList.size(); i++) {

            // method 1
            if (featureList.get(i).getSecondPostagQueue() == null) {
                writer.write((featureList.get(i)).getTopPostagStack() + "\t");
                writer.write((featureList.get(i)).getFirstPostagQueue() + "\t");
                writer.write((featureList.get(i)).getCanLA() + "\t");
                writer.write((featureList.get(i)).getCanRE() + "\t");
                writer.write(actionList.get(i) + "\n");
            }

            // method 2
            else if (featureList.get(i).getThirdPostagQueue() == null) {
                writer.write((featureList.get(i)).getTopPostagStack() + "\t");
                writer.write((featureList.get(i)).getSecondPostagStack() + "\t");
                writer.write((featureList.get(i)).getFirstPostagQueue() + "\t");
                writer.write((featureList.get(i)).getSecondPostagQueue() + "\t");
                writer.write((featureList.get(i)).getCanLA() + "\t");
                writer.write((featureList.get(i)).getCanRE() + "\t");
                writer.write(actionList.get(i) + "\n");
            }

            //method 3
            else {
                writer.write((featureList.get(i)).getTopPostagStack() + "\t");
                writer.write((featureList.get(i)).getSecondPostagStack() + "\t");
                writer.write((featureList.get(i)).getThirdPostagStack() + "\t");
                writer.write((featureList.get(i)).getFirstPostagQueue() + "\t");
                writer.write((featureList.get(i)).getSecondPostagQueue() + "\t");
                writer.write((featureList.get(i)).getThirdPostagQueue() + "\t");
                writer.write((featureList.get(i)).getCanLA() + "\t");
                writer.write((featureList.get(i)).getCanRE() + "\t");
                writer.write(actionList.get(i) + "\n");
            }
        }
        writer.close();
    }
}
