package guide;

import format.Word;
import wekaglue.WekaGlue;
import parser.ParserState;

/**
 *
 * @author Pierre Nugues
 */
public class Guide6 extends Guide {

    public Guide6(WekaGlue wekaModel, ParserState parserState) {
        super(wekaModel, parserState);
    }
    // This is a simple oracle that uses the top and second in the stack and first and second in the queue + the Booleans

    public Features extractFeatures() {
        Features feats;
        String topPostagStack = "nil";
        String secondPostagStack = "nil";
        String thirdPostagStack = "nil";

        String secondPostagQueue = "nil";
        String thirdPostagQueue = "nil";

        if (!parserState.stack.empty()) {
            topPostagStack = parserState.stack.peek().getPostag();
            Word temp = parserState.stack.pop();
            if (!parserState.stack.empty()) {
                secondPostagStack = parserState.stack.peek().getPostag();
                Word temp2 = parserState.stack.pop();
                if (!parserState.stack.empty()) {
                    thirdPostagStack = parserState.stack.peek().getPostag();
                }
                parserState.stack.push(temp2);
            }
            parserState.stack.push(temp);
        }

        if (parserState.queue.size() > 1) {
            secondPostagQueue = parserState.queue.get(1).getPostag();
            if (parserState.queue.size() > 2) {
                thirdPostagQueue = parserState.queue.get(2).getPostag();
            }
        }

        feats = new Features(topPostagStack, secondPostagStack, thirdPostagStack, parserState.queue.get(0).getPostag(), secondPostagQueue, thirdPostagQueue, parserState.canLeftArc(), parserState.canReduce());
        return feats;
    }

    public String predict() {
        Features feats = extractFeatures();
        String[] features = new String[8];
        features[0] = feats.getTopPostagStack();
        features[1] = feats.getSecondPostagStack();
        features[2] = feats.getThirdPostagStack();
        features[3] = feats.getFirstPostagQueue();
        features[4] = feats.getSecondPostagQueue();
        features[5] = feats.getThirdPostagQueue();
        features[6] = String.valueOf(feats.getCanLA());
        features[7] = String.valueOf(feats.getCanRE());

        System.out.println("-------");
        for (int i=0; i<8; i++) {
            System.out.println(features[i]);
        }

        return wekaModel.classify(features);
    }
}
