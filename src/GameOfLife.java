/**
 * User: Juha
 * Date: 20.1.2013
 * Time: 19:35
 */
public class GameOfLife {

    public  String[][] generateNextGeneration(String[][] oldGeneration) throws Extinction {
        if (isExtinction(oldGeneration)) {
            throw new Extinction();
        }

        String[][] newGeneration = new String[oldGeneration.length][oldGeneration[0].length];

        for (int x = 0; x < oldGeneration.length; x++) {
            for (int y = 0; y < oldGeneration[x].length; y++) {
                if (isTopLeftCorner(x, y)) {
                    newGeneration[x][y] = getLiveOrDeadCell(countTopLeftCornerNeighbours(oldGeneration), oldGeneration[x][y]);
                } else if (isTopRightCorner(oldGeneration, x, y)) {
                    newGeneration[x][y] = getLiveOrDeadCell(countTopRightCornerNeighbours(oldGeneration), oldGeneration[x][y]);
                } else if (isDownRightCorner(oldGeneration, x, y)) {
                    newGeneration[x][y] = getLiveOrDeadCell(countDownRightCornerNeighbours(oldGeneration), oldGeneration[x][y]);
                } else if(isDownLeftCorner(oldGeneration[0], x, y)) {
                    newGeneration[x][y] = getLiveOrDeadCell(countDownLeftCornerNeighbours(oldGeneration), oldGeneration[x][y]);
                }
            }
        }

        return newGeneration;
    }

    private String getLiveOrDeadCell(int countOfNeighbours, String oldCell) {
        if (isFertile(countOfNeighbours, oldCell)
                || isNewborn(countOfNeighbours, oldCell)) {
            return "*";
        } else {
            return ".";
        }
    }

    private int countDownRightCornerNeighbours(String[][] oldGeneration) {
        int countOfNeighbours = 0;
        if (isLiveCell(oldGeneration[oldGeneration.length-2][oldGeneration[oldGeneration.length-2].length-1])) {
            countOfNeighbours++;
        }
        if (isLiveCell(oldGeneration[oldGeneration.length-2][oldGeneration[oldGeneration.length-2].length-2])) {
            countOfNeighbours++;
        }
        if (isLiveCell(oldGeneration[oldGeneration.length-1][oldGeneration[oldGeneration.length-1].length-2])) {
            countOfNeighbours++;
        }
        return countOfNeighbours;
    }

    private int countTopRightCornerNeighbours(String[][] oldGeneration) {
        int countOfNeighbours = 0;
        if (isLiveCell(oldGeneration[oldGeneration.length - 2][0])) {
            countOfNeighbours++;
        }
        if (isLiveCell(oldGeneration[oldGeneration.length - 2][1])) {
            countOfNeighbours++;
        }
        if (isLiveCell(oldGeneration[oldGeneration.length - 1][1])) {
            countOfNeighbours++;
        }
        return countOfNeighbours;
    }

    private int countTopLeftCornerNeighbours(String[][] oldGeneration) {
        int countOfNeighbours = 0;
        if (isLiveCell(oldGeneration[1][0])) {
            countOfNeighbours++;
        }
        if (isLiveCell(oldGeneration[1][1])) {
            countOfNeighbours++;
        }
        if (isLiveCell(oldGeneration[0][1])) {
            countOfNeighbours++;
        }
        return countOfNeighbours;
    }

    private int countDownLeftCornerNeighbours(String[][] oldGeneration) {
        int countOfNeighbours = 0;
        if (isLiveCell(oldGeneration[0][oldGeneration[0].length-2])) {
            countOfNeighbours++;
        }
        if (isLiveCell(oldGeneration[1][oldGeneration[1].length-2])) {
            countOfNeighbours++;
        }
        if (isLiveCell(oldGeneration[1][oldGeneration[1].length-1])) {
            countOfNeighbours++;
        }
        return countOfNeighbours;
    }

    private boolean isLiveCell(String cell) {
        return cell.equals("*");
    }

    private boolean isTopLeftCorner(int x, int y) {
        return x == 0 && y == 0;
    }

    private boolean isTopRightCorner(String[][] oldGeneration, int x, int y) {
        return (x == oldGeneration.length - 1) && y == 0;
    }

    private boolean isDownRightCorner(String[][] oldGeneration, int x, int y) {
        return (x == oldGeneration.length - 1) && (y == oldGeneration[oldGeneration.length-1].length - 1);
    }

    private boolean isDownLeftCorner(String[] strings, int x, int y) {
        return x == 0 && (y == strings.length - 1);
    }

    private boolean isNewborn(int countOfNeighbours, String oldCell) {
        return oldCell.equals(".") && countOfNeighbours == 3;
    }

    private boolean isFertile(int countOfNeighbours, String oldCell) {
        return isLiveCell(oldCell) && (countOfNeighbours == 2 || countOfNeighbours == 3);
    }

    private boolean isExtinction(String[][] generation) {
        int countOfLiveCells = 0;
        for (int x = 0; x < generation.length; x++) {
            for (int y = 0; y < generation[x].length; y++) {
                if (isLiveCell(generation[x][y])) {
                    countOfLiveCells++;
                }
            }
        }
        return countOfLiveCells < 3;
    }

    public class Extinction extends Exception {
    }
}
