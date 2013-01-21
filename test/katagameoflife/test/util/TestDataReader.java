package katagameoflife.test.util;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * User: Juha
 * Date: 21.1.2013
 * Time: 15:41
 */
public class TestDataReader {
    public static String[][] readTestGenerationByTestName(String testName, int sideLenght, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String[][] testGeneration = new String[sideLenght][sideLenght];
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals(testName)) {
                    for (int y = 0; y < sideLenght; y++) {
                        char[] dataLine = br.readLine().toCharArray();
                        for (int x = 0; x < sideLenght; x++) {
                            testGeneration[x][y] = dataLine[x] + "";
                        }
                    }
                    break;
                }
            }
            return testGeneration;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
