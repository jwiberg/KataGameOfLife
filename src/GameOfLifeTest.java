import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * User: Juha
 * Date: 19.1.2013
 * Time: 15:40
 */

public class GameOfLifeTest {

    @Test
    public void zeroLiveCellsShouldGenerateZeroLiveCells() {
        String[][] oldGeneration = {};
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertArrayEquals(new String[0][0], newGeneration);
    }

    @Test
    public void oneLiveCellsWithoutNeighboursShouldGenerateNoLiveCells() {
        String[][] oldGeneration = {{"*"}};
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertArrayEquals(new String[][]{{"."}}, newGeneration);
    }

   @Test
    public void twoLiveCellsReturnsNoLiveCells() {
        String[][] oldGeneration = {{"*","*"}};
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertArrayEquals(new String[][]{{".","."}}, newGeneration);
    }

/*
    @Test
    public void oneLiveCellIn2x2GridShouldGenerateNoLiveCells() {
        String[][] oldGeneration = {{"*","."},{".","."}};
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertArrayEquals(new String[][]{{".","."},{".","."}}, newGeneration);
    }

    @Test
    public void twoLiveCellIn2x2GridShouldGenerateNoLiveCells() {
        String[][] oldGeneration = {{"*","."},{".","*"}};
        String[][] newGeneration = generateNextGeneration(oldGeneration);
        assertArrayEquals(new String[][]{{".","."},{".","."}}, newGeneration);
    }
*/

    private String[][] generateNextGeneration(String[][] oldGeneration) {
        String[][] newGeneration = null;
        //Couple of special cases
        if(oldGeneration.length == 0) {
            newGeneration = new String[oldGeneration.length][0];
        }else if(oldGeneration.length == 1 && oldGeneration[0].length < 3) {
            newGeneration = new String[oldGeneration.length][oldGeneration[0].length];
            for(int i = 0; i < oldGeneration[0].length; i++) {
                newGeneration[0][i] = ".";
            }
        }
        return newGeneration;
    }

}
