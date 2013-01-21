package katagameoflife;

import katagameoflife.GameOfLife;
import katagameoflife.test.util.TestDataReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Juha
 * Date: 19.1.2013
 * Time: 15:40
 */

public class GameOfLifeTest {

    public static final String TESTDATA_FILE_PATH = "testdata/testdata.txt";

    @Test(expected = GameOfLife.TooSmallWorld.class)
    public void zeroLiveCellsShouldGenerateZeroLiveCells() throws Exception {
        String[][] oldGeneration = {};
        generateNextGeneration(oldGeneration);
    }

    @Test(expected = GameOfLife.TooSmallWorld.class)
    public void oneLiveCellsWithoutNeighboursShouldGenerateNoLiveCells() throws Exception {
        String[][] oldGeneration = {{"*"}};
        generateNextGeneration(oldGeneration);
    }

    @Test(expected = GameOfLife.TooSmallWorld.class)
    public void oneLiveCellIn2x2GridShouldGenerateNoLiveCells() throws Exception {
        String[][] oldGeneration = {{"*", "."}, {".", "."}};
        generateNextGeneration(oldGeneration);
    }

    @Test(expected = GameOfLife.Extinction.class)
    public void oneLonelyCellInBigLonelyWorldLeadsToExtinction() throws Exception {
        String[][] oldGeneration = {{"*", ".", "."}, {".", ".", "."}, {".", ".", "."}};
        generateNextGeneration(oldGeneration);
    }

    @Test
    public void liveCellInTopLeftCornerWithTwoNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = TestDataReader.readTestGenerationByTestName("liveCellInTopLeftCornerWithTwoNeighboursShouldLive", 3, TESTDATA_FILE_PATH);
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals("*", newGeneration[0][0]);
    }

    @Test
    public void liveCellInTopLeftCornerWithThreeNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = TestDataReader.readTestGenerationByTestName("liveCellInTopLeftCornerWithThreeNeighboursShouldLive", 3, TESTDATA_FILE_PATH);
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals("*", newGeneration[0][0]);
    }

    @Test
    public void liveCellInTopRightCornerWithTwoNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = {{".", ".", "."}, {"*", ".", "."}, {"*", "*", "."}};
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals("*", newGeneration[2][0]);
    }

    @Test
    public void liveCellInTopRightCornerWithThreeNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = {{".", ".", "."}, {"*", "*", "."}, {"*", "*", "."}};
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals("*", newGeneration[2][0]);
    }

    @Test
    public void liveCellInDownRightCornerWithTwoNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = {{".", ".", "."}, {".", ".", "*"}, {".", "*", "*"}};
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals("*", newGeneration[2][2]);
    }

    @Test
    public void liveCellInDownRightCornerWithThreeNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = {{".", ".", "."}, {".", "*", "*"}, {".", "*", "*"}};
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals("*", newGeneration[2][2]);
    }

    @Test
    public void liveCellInDownLeftCornerWithTwoNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = {{".", "*", "*"}, {".", ".", "*"}, {".", ".", "."}};
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals("*", newGeneration[0][2]);
    }

    @Test
    public void liveCellInDownLeftCornerWithThreeNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = {{".", "*", "*"}, {".", "*", "*"}, {".", ".", "."}};
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals("*", newGeneration[0][2]);
    }

    @Test
    public void liveCellInDownLeftCornerWithOnerNeighbourShouldDie() throws Exception {
        String[][] oldGeneration = {{"*", ".", ".", "*"}, {"*", ".", ".", "*"}, {"*", ".", ".", "."}};
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals(".", newGeneration[0][3]);
    }

    @Test
    public void liveCellAtMiddleOfTopSideShouldDieIfNoNeighbours() throws Exception {
        String[][] oldGeneration = TestDataReader.readTestGenerationByTestName("liveCellAtMiddleOfTopSideShouldDieIfNoNeighbours",5, TESTDATA_FILE_PATH);
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        visualize(newGeneration);
        assertEquals(".", newGeneration[2][0]);
    }

    @Test
    public void liveCellAtMiddleOfTopSideShouldLiveWithTwoNeighbours() throws Exception {
        String[][] oldGeneration = {{".", ".", ".", "*", "."}
                , {"*", ".", ".", ".", "*"}
                , {"*", ".", ".", ".", "."}
                , {"*", ".", ".", "*", "."}
                , {".", ".", ".", ".", "."}};
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        visualize(newGeneration);
        assertEquals("*", newGeneration[2][0]);
    }

    @Test
    public void liveCellAtMiddleOfDownSideShouldDieWithOneNeighbour() throws Exception {
        String[][] oldGeneration = TestDataReader.readTestGenerationByTestName("liveCellAtMiddleOfDownSideShouldDieWithOneNeighbour", 5, TESTDATA_FILE_PATH);
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        visualize(newGeneration);
        assertEquals(".", newGeneration[2][4]);
    }

    @Test
    public void liveCellAtMiddleOfDownSideShouldLiveWithTwoNeighbours() throws Exception {
        String[][] oldGeneration = TestDataReader.readTestGenerationByTestName("liveCellAtMiddleOfDownSideShouldLiveWithTwoNeighbours", 5, TESTDATA_FILE_PATH);
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        visualize(newGeneration);
        assertEquals("*", newGeneration[2][4]);
    }

    @Test
    public void liveCellAtMiddleOfLeftSideShouldLiveWithTwoNeighbours() throws Exception {
        String[][] oldGeneration = TestDataReader.readTestGenerationByTestName("liveCellAtMiddleOfLeftSideShouldLiveWithTwoNeighbours", 5, TESTDATA_FILE_PATH);
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        visualize(newGeneration);
        assertEquals("*", newGeneration[0][2]);
    }

    @Test
    public void liveCellAtMiddleOfLeftSideShouldDieWithOneNeighbours() throws Exception {
        String[][] oldGeneration = TestDataReader.readTestGenerationByTestName("liveCellAtMiddleOfLeftSideShouldDieWithOneNeighbours", 5, TESTDATA_FILE_PATH);
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        visualize(newGeneration);
        assertEquals(".", newGeneration[0][2]);
    }

    @Test
    public void liveCellAtMiddleOfRightSideShouldLiveWithTwoNeighbours() throws Exception {
        String[][] oldGeneration = TestDataReader.readTestGenerationByTestName("liveCellAtMiddleOfRightSideShouldLiveWithTwoNeighbours", 5, TESTDATA_FILE_PATH);
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        visualize(newGeneration);
        assertEquals("*", newGeneration[4][2]);
    }

    @Test
    public void liveCellAtMiddleOfRightSideShouldDieWithOneNeighbours() throws Exception {
        String[][] oldGeneration = TestDataReader.readTestGenerationByTestName("liveCellAtMiddleOfRightSideShouldDieWithOneNeighbours", 5, TESTDATA_FILE_PATH);
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        visualize(newGeneration);
        assertEquals(".", newGeneration[4][2]);
    }

    @Test
    public void loafShouldStayLoaf() throws Exception {
        String[][] oldGeneration = TestDataReader.readTestGenerationByTestName("loafShouldStayLoaf", 6, TESTDATA_FILE_PATH);
        visualize(oldGeneration);
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        visualize(newGeneration);
        assertEquals("*", newGeneration[2][1]);
        assertEquals("*", newGeneration[3][1]);
        assertEquals("*", newGeneration[1][2]);
        assertEquals("*", newGeneration[4][2]);
        assertEquals("*", newGeneration[2][3]);
        assertEquals("*", newGeneration[4][3]);
        assertEquals("*", newGeneration[3][4]);
    }

    private String[][] generateNextGeneration(String[][] oldGeneration) throws Exception {
        return new GameOfLife().generateNextGeneration(oldGeneration);
    }

    //Debug method, remove
    private void visualize(String[][] generation) {
        System.out.print("\n");
        for (int y = 0; y < generation[0].length; y++) {
            for (int x = 0; x < generation.length; x++) {
                System.out.print(generation[x][y]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
