package sample;

public class ValueExampleObject {

    private String filePath;
    private String testCase;
    private String count;

    public ValueExampleObject(String filePath, String testCase, String count) {
        this.filePath = filePath;
        this.testCase = testCase;
        this.count = count;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getTestCase() {
        return testCase;
    }
    
    public String getCount() {
        return count;
    }

}