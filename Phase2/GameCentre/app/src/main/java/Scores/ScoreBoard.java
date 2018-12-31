package Scores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This is ScoreBoard class that will create a scoreBoard object
 */
public class ScoreBoard implements Serializable {

    /**
     * this is a List of all records of Complexity 1
     */
    private List<Record> recordsComplexity1 = new ArrayList<>();

    /**
     * this is a List of all records of Complexity 2
     */
    private List<Record> recordsComplexity2 = new ArrayList<>();

    /**
     * this is a List of all records of Complexity 3
     */
    private List<Record> recordsComplexity3 = new ArrayList<>();

    /**
     * get the current complexity  the game.
     */
    private int currComplexity;

    /**
     * Set this.currComplexity to the given complexity
     *
     * @param complexity the complexity of the record
     */
    public void setComplexity(int complexity) {
        this.currComplexity = complexity;
    }


    /**
     * Return return a List records which corresponding to the current game complexity.
     *
     * @param complexity the complexity the game
     * @return return a List which corresponding to the current game complexity.
     */
    private List<Record> getComplexityRecords(int complexity) {
        if (complexity == 3) {
            return recordsComplexity1;
        }
        if (complexity == 4) {
            return recordsComplexity2;
        } else {
            return recordsComplexity3;
        }
    }


    /**
     * Modify List records which corresponding to the current game complexity(add a record
     * in it)
     *
     * @param record a record
     */
    void addNewRecords(Record record) {
        int complexity = record.getComplexity();
        setComplexity(complexity);

        // get the List records which corresponding to the current game
        // complexity(add a record)
        List<Record> currentRecords = getComplexityRecords(currComplexity);

        // add or replace the record
        int temp = checkRecordIndex(record);
        if (temp == -1) {
            currentRecords.add(record);
        } else if (currentRecords.get(temp).checkLowerScore(record)) {
            currentRecords.remove(temp);
            currentRecords.add(record);
        }
        sortRecords();
    }


    /**
     * Modify and sorted the List records which corresponding to the current game complexity
     */
    private void sortRecords() {
        Collections.sort(getComplexityRecords(currComplexity));
    }


    /**
     * Return a List that contains the top five record if possible
     *
     * @return a List that contains the top five record if possible
     */
    private List<Record> getTopFive() {

        List<Record> result = new ArrayList<>();

        int i = 1;
        while (i <= getComplexityRecords(currComplexity).size() && i <= 5) {
            result.add(getComplexityRecords(currComplexity).get(i - 1));
            i++;
        }
        return result;
    }


    /**
     * Return a List that contains the String type of the top five record if possible.
     *
     * @return Return a List that contains the String type of the top five record if possible,
     * if not possible then show "Not enough users to get a fully rank" instead.
     */
    List TopFiveToString() {
        List<Record> topFive = getTopFive();
        List<String> result = new ArrayList<>();

        for (Record r : topFive) {
            result.add(r.recordToString());
        }

        if (result.size() < 5) {
            int diff = 5 - result.size();
            while (diff > 0) {
                String temp = "Not enough users to get a full rank";
                result.add(temp);
                diff--;
            }
        }
        return result;
    }


    /**
     * Return the index of the record's user's best record in the corresponding
     * complexity records List.
     *
     * @param myRecord the current record.
     * @return the index of the record's user's best record in the corresponding
     * complexity records List.
     */
    int getMyBestRank(Record myRecord) {
        List<Record> records = getComplexityRecords(myRecord.getComplexity());
        int i = 1;
        int result = 0;
        for (Record r : records) {
            if (r.getUserName().equals(myRecord.getUserName())) {
                result = i;
            }
            i++;
        }
        return result;
    }


    /**
     * Return -1 if the record is not in the corresponding complexity records List, or
     * return the index of the record's user's record.
     *
     * @param record a record
     * @return -1 if the record is not in the corresponding complexity records List, or
     * return the index of the record's user's record.
     */
    private int checkRecordIndex(Record record) {
        List<Record> tempRecord = getComplexityRecords(currComplexity);
        for (Record r : tempRecord) {
            if (record.getUserName().equals(r.getUserName())) {
                return tempRecord.indexOf(r);
            }
        }
        return -1;
    }

}












