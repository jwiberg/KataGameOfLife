import org.junit.Test;
import sun.org.mozilla.javascript.internal.ast.Yield;

import static org.junit.Assert.assertArrayEquals;

/**
 * User: Juha
 * Date: 19.1.2013
 * Time: 15:40
 */

public class GameOfLifeTest {

    @Test(expected = Extinction.class)
    public void zeroLiveCellsShouldGenerateZeroLiveCells() throws Exception {
        String[][] oldGeneration = {};
        generateNextGeneration(oldGeneration);
    }

    @Test(expected = Extinction.class)
    public void oneLiveCellsWithoutNeighboursShouldGenerateNoLiveCells() throws Exception {
        String[][] oldGeneration = {{"*"}};
        generateNextGeneration(oldGeneration);
    }

    @Test(expected = Extinction.class)
    public void twoLiveCellsReturnsNoLiveCells() throws Exception {
        String[][] oldGeneration = {{"*", "*"}};
        generateNextGeneration(oldGeneration);
    }

    @Test(expected = Extinction.class)
    public void oneLiveCellIn2x2GridShouldGenerateNoLiveCells() throws Exception {
        String[][] oldGeneration = {{"*", "."}, {".", "."}};
        generateNextGeneration(oldGeneration);
    }

    @Test(expected = Extinction.class)
    public void twoLiveCellIn2x2GridShouldGenerateNoLiveCells() throws Exception {
        String[][] oldGeneration = {{"*", "."}, {".", "*"}};
        generateNextGeneration(oldGeneration);
    }

/*
    @Test(expected = Extinction.class)
    public void threeLiveCellIn3x3GridShouldGenerateNoLiveCells() throws Exception {
        String[][] oldGeneration = {{"*", ".","*"}, {".", ".","."}, {".", ".","*"}};
        visualizeGeneration(oldGeneration);
        generateNextGeneration(oldGeneration);
    }
*/

    private String[][] generateNextGeneration(String[][] oldGeneration) throws Extinction {
        String[][] newGeneration = null;

        if (isExtinction(oldGeneration)) {
            throw new Extinction();
        }

        return newGeneration;
    }

    private void visualizeGeneration(String[][] generation) {
        for(int x = 0; x < generation.length; x++) {
            for(int y = 0; y < generation[x].length; y++) {
                System.out.print(generation[x][y]);
            }
            System.out.print("\n");
        }
    }

    private boolean isExtinction(String[][] generation) {
        int countOfLiveCells = 0;
        for (int x = 0; x < generation.length; x++) {
            for (int y = 0; y < generation[x].length; y++) {
                if (generation[x][y].equals("*")) {
                    countOfLiveCells++;
                }
            }
        }
        return countOfLiveCells < 3;
    }

    private class Extinction extends Exception {

    }
}
