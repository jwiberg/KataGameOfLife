import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void liveCellInTopLeftCornerWithTwoNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = {{"*", ".", "."}, {"*", "*", "."}, {".", ".", "."}};
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals("*", newGeneration[0][0]);
    }

    @Test
    public void liveCellInTopLeftCornerWithThreeNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = {{"*", "*", "."}, {"*", "*", "."}, {".", ".", "."}};
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertEquals("*", newGeneration[0][0]);
    }

/*    @Test
    public void liveCellInTopRightCornerWithTwoNeighboursShouldLive() throws Exception {
        String[][] oldGeneration = {{".", ".", "."}, {".", "*", "."}, {"*", "*", "."}};
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
    }*/

    private String[][] generateNextGeneration(String[][] oldGeneration) throws Extinction {

        if (isExtinction(oldGeneration)) {
            throw new Extinction();
        }

        String[][] newGeneration = new String[oldGeneration.length][oldGeneration[0].length];

        for (int x = 0; x < oldGeneration.length; x++) {
            for (int y = 0; y < oldGeneration[x].length; y++) {
                int countOfNeighbours = 0;
                if (isTopLeftCorner(x, y)) {
                    countOfNeighbours = countTopLeftCornerNeighbours(oldGeneration);
                }
                if (isFertile(countOfNeighbours, oldGeneration[x][y])
                        || isNewborn(countOfNeighbours, oldGeneration[x][y])) {
                    newGeneration[x][y] = "*";
                } else {
                    newGeneration[x][y] = ".";
                }
            }
        }

        return newGeneration;
    }

    private int countTopLeftCornerNeighbours(String[][] oldGeneration) {
        int countOfNeighbours = 0;
        if (oldGeneration[1][0].equals("*")) {
            countOfNeighbours++;
        }
        if (oldGeneration[1][1].equals("*")) {
            countOfNeighbours++;
        }
        if (oldGeneration[0][1].equals("*")) {
            countOfNeighbours++;
        }
        return countOfNeighbours;
    }

    private boolean isTopLeftCorner(int x, int y) {
        return x == 0 && y == 0;
    }

    private boolean isTopRightCorner(String[][] oldGeneration, int x, int y) {
        return (x == oldGeneration.length - 1) && y == 0;
    }

    private boolean isNewborn(int countOfNeighbours, String oldCell) {
        return oldCell.equals(".") && countOfNeighbours == 3;
    }

    private boolean isFertile(int countOfNeighbours, String oldCell) {
        return oldCell.equals("*") && (countOfNeighbours == 2 || countOfNeighbours == 3);
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

    private class Extinction extends Exception {}

    //Debug method, remove
    public void visualize(String[][] generation) {
        for (int y = 0; y < generation[0].length; y++) {
            for (int x = 0; x < generation.length; x++) {
                System.out.print(generation[x][y]);
            }
            System.out.print("\n");
        }
    }
}
