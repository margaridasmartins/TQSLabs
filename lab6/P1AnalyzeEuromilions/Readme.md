*Token used* : cd2fc9bf34f3cce0d4cbe5addf05779010fa6333

## Sonar Qube analysis

### Quality gate

The project passed the default quality gate, this means that all conditions established passed, if they didn't the measures to be taken in order to pass would appear. Because the default quality gate is new code, the first analysis would always pass.


| **Issue** |  **Problem description**  | **How to solve** |
|:-----:|:--------:|:------:|
| Bug (major)  | Creating a new Random object each time a random value is needed is inefficient and may produce numbers which are not random depending on the JDK. For better efficiency and randomness, create a single Random, then store, and reuse it.|  Create just one private secure Random object (SecureRandom.getInstanceStrong()) |
| Code Smell (major)  | A for loop stop condition should test the loop counter against an invariant value (i.e. one that is true at both the beginning and ending of every loop iteration). Ideally, this means that the stop condition is set to a local variable just before the loop begins.Stop conditions that are not invariant are slightly less efficient, as well as being difficult to understand and maintain, and likely lead to the introduction of errors in the future.  |  Add i++ on the for condition |
| Code Smell (major)   | Unused parameters are misleading. Whatever the values passed to such parameters, the behavior will be the same.| Remove parameter subset |
| Code Smell  (major)  | Defining and using a dedicated logger is highly recommended, instead of using Standard outputs| Replace the use of System.out.println for logger.log |