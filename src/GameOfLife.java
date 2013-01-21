import java.util.HashSet;
import java.util.Set;

/**
 * User: Juha
 * Date: 20.1.2013
 * Time: 19:35
 */
public class GameOfLife {

    public String[][] generateNextGeneration(String[][] oldGeneration) throws Extinction, TooSmallWorld {
        if(isTooSmallWorld(oldGeneration)) {
            throw new TooSmallWorld();
        }

        if (isExtinction(oldGeneration)) {
            throw new Extinction();
        }

        //Pitääkö tuo uusi generaatio luoda tuolla metodin sisällä?
        String[][] newGeneration = getEmptyNewGeneration(oldGeneration);

        checkCorners(oldGeneration, newGeneration);
        checkTopSide(oldGeneration, newGeneration);


//        for (int x = 0; x < oldGeneration.length; x++) {
//            for (int y = 0; y < oldGeneration[x].length; y++) {
//                if (isCorner(oldGeneration, x, y)) {
//                    continue;
//                }
//            }
//        }

        return newGeneration;
    }

    private void checkTopSide(String[][] oldGeneration, String[][] newGeneration) {
        for(int x = 1; x < newGeneration.length - 1; x++) {
            Set<Coordinate> coordinates = new HashSet<>();
            coordinates.add(new Coordinate(x+1, 0));
            coordinates.add(new Coordinate(x+1, 1));
            coordinates.add(new Coordinate(x, 1));
            coordinates.add(new Coordinate(x-1, 1));
            coordinates.add(new Coordinate(x-1, 0));
            int liveCells = countLiveCells(coordinates, oldGeneration);
            newGeneration[x][0] = getLiveOrDeadCell(liveCells, oldGeneration[x][0]);
        }
    }

    private void checkCorners(String[][] oldGeneration, String[][] newGeneration) {
        newGeneration[0][0] = getLiveOrDeadCell(countTopLeftCornerLiveNeighbours(oldGeneration), oldGeneration[0][0]);
        newGeneration[oldGeneration.length - 1][0] = getLiveOrDeadCell(countTopRightCornerLiveNeighbours(oldGeneration), oldGeneration[oldGeneration.length - 1][0]);
        newGeneration[oldGeneration.length - 1][oldGeneration[oldGeneration.length - 1].length - 1] = getLiveOrDeadCell(countDownRightCornerLiveNeighbours(oldGeneration), oldGeneration[oldGeneration.length - 1][oldGeneration[oldGeneration.length - 1].length - 1]);
        newGeneration[0][oldGeneration[0].length - 1] = getLiveOrDeadCell(countDownLeftCornerLiveNeighbours(oldGeneration), oldGeneration[0][oldGeneration[0].length - 1]);
    }

    private int countDownRightCornerLiveNeighbours(String[][] oldGeneration) {
        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.add(new Coordinate(oldGeneration.length - 2, oldGeneration[oldGeneration.length - 2].length - 1));
        coordinates.add(new Coordinate(oldGeneration.length - 2, oldGeneration[oldGeneration.length - 2].length - 2));
        coordinates.add(new Coordinate(oldGeneration.length - 1, oldGeneration[oldGeneration.length - 1].length - 2));
        return countLiveCells(coordinates, oldGeneration);
    }

    private int countTopRightCornerLiveNeighbours(String[][] oldGeneration) {
        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.add(new Coordinate(oldGeneration.length - 2, 0));
        coordinates.add(new Coordinate(oldGeneration.length - 2, 1));
        coordinates.add(new Coordinate(oldGeneration.length - 1, 1));
        return countLiveCells(coordinates, oldGeneration);
    }

    private int countTopLeftCornerLiveNeighbours(String[][] oldGeneration) {
        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.add(new Coordinate(1, 0));
        coordinates.add(new Coordinate(1, 1));
        coordinates.add(new Coordinate(0, 1));
        return countLiveCells(coordinates, oldGeneration);
    }

    private int countDownLeftCornerLiveNeighbours(String[][] oldGeneration) {
        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.add(new Coordinate(0, oldGeneration[0].length - 2));
        coordinates.add(new Coordinate(1, oldGeneration[1].length - 2));
        coordinates.add(new Coordinate(1, oldGeneration[1].length - 1));
        return countLiveCells(coordinates, oldGeneration);
    }

    private int countLiveCells(Set<Coordinate> coordinates, String[][] generation) {
        int countOfLiveCells = 0;
        for (Coordinate coordinate : coordinates) {
            if (isLiveCell(generation[coordinate.getX()][coordinate.getY()])) {
                countOfLiveCells++;
            }
        }
        return countOfLiveCells;
    }

    private boolean isCorner(String[][] oldGeneration, int x, int y) {
        return isTopLeftCorner(x, y)
                || isTopRightCorner(oldGeneration, x, y)
                || isDownRightCorner(oldGeneration, x, y)
                || isDownLeftCorner(oldGeneration, x, y);
    }

    private boolean isTopLeftCorner(int x, int y) {
        return x == 0 && y == 0;
    }

    private boolean isTopRightCorner(String[][] oldGeneration, int x, int y) {
        return (x == oldGeneration.length - 1) && y == 0;
    }

    private boolean isDownRightCorner(String[][] oldGeneration, int x, int y) {
        return (x == oldGeneration.length - 1) && (y == oldGeneration[oldGeneration.length - 1].length - 1);
    }

    private boolean isDownLeftCorner(String[][] oldGeneration, int x, int y) {
        return x == 0 && (y == oldGeneration[0].length - 1);
    }

    private boolean isNewborn(int countOfNeighbours, String oldCell) {
        return oldCell.equals(".") && countOfNeighbours == 3;
    }

    private boolean isFertile(int countOfNeighbours, String oldCell) {
        return isLiveCell(oldCell) && (countOfNeighbours == 2 || countOfNeighbours == 3);
    }

    private boolean isLiveCell(String cell) {
        return cell.equals("*");
    }

    private String getLiveOrDeadCell(int countOfNeighbours, String oldCell) {
        if (isFertile(countOfNeighbours, oldCell)
                || isNewborn(countOfNeighbours, oldCell)) {
            return "*";
        } else {
            return ".";
        }
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

    private boolean isTooSmallWorld(String[][] generation) {
        if(generation.length < 3 || generation[0].length < 3) {
            return true;
        }
        return false;
    }

    private String[][] getEmptyNewGeneration(String[][] oldGeneration) {
        String[][] newGeneration = new String[oldGeneration.length][oldGeneration[0].length];
        for (int x = 0; x < newGeneration.length; x++) {
            for(int y = 0; y < newGeneration[0].length; y++) {
                newGeneration[x][y] = ".";
            }
        }
        return newGeneration;
    }

    private class Coordinate {
        private final int x;
        private final int y;

        private Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    public class Extinction extends Exception {
    }

    public class TooSmallWorld extends Exception {
    }
}
