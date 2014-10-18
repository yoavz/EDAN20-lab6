Lab 6: Dependency parsing using machine learning techniques 
======================
Report by ***Yoav Zimmerman*** for EDAN20 Fall 2014

Introduction
-------------

Last week, we generated classifiers that will enable us to do dependency parsing using machine learning techniques. In this lab, we will be applying those classifiers on the CONLL-X Swedish Corpus and evaluating the results. Three different numbers of features were extracted last week.

1. The POS tags of the first word on the stack and first word in the queue.
2. The POS tags of the first and second words on the stack and the first and second words in the queue.
3. The POS tags of the first, second, and third words on the stack and the first, second, and third words in the queue.

With each of these models, the parser action was either unlabeled or labeled by the dependency relation, resulting in a total of six models.

Parsing the Corpus
------------------

To implement the parser using our generated models, the same codebase from last week was used. A class to interface with Weka, `WekaGlue.java`, was provided. To implement machine learned parsing, the `parser()` method in `Parser.java` was modified to extract features at each point, call the classifier with the features, and perform the recommended action. If the recommended action is not possible, then the parser defaults to the shift action.

Additional helper classes written were `Guide2.java` and `Guide6.java`, for the 2 feature and 6 feature models respectively (`Guide4.java` was provided).

Results
-------

The program was run with the six models on the CONLL-X blind test set, then compared to the dependency parsed test set using the provided perl script `eval.pl`. The results were the following:

## Model 1 - Unlabeled
```bash
Labeled   attachment score: 42 / 5021 * 100 = 0.84 %
Unlabeled attachment score: 3658 / 5021 * 100 = 72.85 %
Label accuracy score:       42 / 5021 * 100 = 0.84 %
```

## Model 1 - Labeled
```bash
Labeled   attachment score: 3015 / 5021 * 100 = 60.05 %
Unlabeled attachment score: 3662 / 5021 * 100 = 72.93 %
Label accuracy score:       3173 / 5021 * 100 = 63.19 %
```

## Model 2 - Unlabeled
```bash
Labeled   attachment score: 23 / 5021 * 100 = 0.46 %
Unlabeled attachment score: 4071 / 5021 * 100 = 81.08 %
Label accuracy score:       23 / 5021 * 100 = 0.46 %
```

## Model 2 - Labeled
```bash
Labeled   attachment score: 3421 / 5021 * 100 = 68.13 %
Unlabeled attachment score: 4048 / 5021 * 100 = 80.62 %
Label accuracy score:       3551 / 5021 * 100 = 70.72 %
```

## Model 3 - Unlabeled
```bash
Labeled   attachment score: 23 / 5021 * 100 = 0.34 %
Unlabeled attachment score: 4071 / 5021 * 100 = 81.10 %
Label accuracy score:       23 / 5021 * 100 = 0.34 %
```

## Model 3 - Labeled
```bash
Labeled   attachment score: 3421 / 5021 * 100 = 68.15 %
Unlabeled attachment score: 4048 / 5021 * 100 = 80.65 %
Label accuracy score:       3551 / 5021 * 100 = 70.77 %
```

As can be expected, the most complex models for both unlabeled and labeled resulted in the best accuracy scores. Our best unlabeled accuracy score was **80.65%**, which is not too far off from the best score at CoNLL-X, **86.60%**. For labeled accuracy, our best score was **68.15%**, while the best score at CoNLL-X was **86.70%**. It is likely possible to achieve a better labeled attachment score with a model including features optimized for labeled analysis, such as the previous actions dependency relation. 
