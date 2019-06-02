import crackattack.Constants;
import crackattack.controller.board.BoardComboChecker;
import crackattack.controller.board.BoardElementBreaker;
import crackattack.controller.board.BoardGravityChecker;
import crackattack.model.board.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestCheckers {
    public GridElement[][] generateContentFromTemplate(String[] template) {
        assert(Constants.BOARD_ROWS == template.length);
        for (String s: template) {
            assert(Constants.BOARD_COLUMNS == s.length());
        }

        GridElement[][] generated = new GridElement[Constants.BOARD_ROWS][Constants.BOARD_COLUMNS];

        for (int i = 0; i < Constants.BOARD_ROWS; i++) {
            for (int j = 0; j < Constants.BOARD_COLUMNS; j++) {
                Color elementColor = Color.BAR;
                switch (template[i].charAt(j)) {
                    case 'Y':
                        elementColor = Color.YELLOW;
                        break;
                    case 'G':
                        elementColor = Color.GREEN;
                        break;
                    case 'B':
                        elementColor = Color.BLUE;
                        break;
                    case 'P':
                        elementColor = Color.PURPLE;
                        break;
                    case 'O':
                        elementColor = Color.ORANGE;
                        break;
                    case 'A':
                        elementColor = Color.GRAY;
                        break;
                    case ' ':
                        break;
                    default:
                        System.err.println(template[i].charAt(j) + " - Invalid template.");
                        System.exit(1);
                        break;
                }

                if (elementColor != Color.BAR) {
                    generated[i][j] = new Block(elementColor);
                } else {
                    generated[i][j] = null;
                }
            }
        }

        return generated;
    }

    private void copyBoard(GridElement[][] source, GridElement[][] dest) {
        for (int i = 0; i < Constants.BOARD_ROWS; i++) {
            for (int j = 0; j < Constants.BOARD_COLUMNS; j++) {
                dest[i][j] = source[i][j];
            }
        }
    }

    private GridElement[][] copyBoardContents(Board board) {
        GridElement[][] copy = new GridElement[board.getMaxY()][board.getMaxX()];
        for (int i = 0; i < board.getMaxY(); i++) {
            for (int j = 0; j < board.getMaxX(); j++) {
                copy[i][j] = board.getGridElement(new Position(j,i));
            }
        }

        return copy;
    }

    public Board createBoardFromTemplate(String[] template) {
        return new Board(b -> {
            GridElement[][] ge = generateContentFromTemplate(template);
            copyBoard(ge,b);
        });
    }

    @Test
    public void TestGravity() {
        String[] template = {
                "     B",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "   G  ",
                "   Y  ",
                "      ",
                "A     ",
                "   Y  ",
                " G    "
        };

        Board board = createBoardFromTemplate(template);

        BoardGravityChecker gravityChecker = new BoardGravityChecker(board);

        List<Position> fallen = gravityChecker.updateGravity(Arrays.asList(
                new Position(0, 10),
                new Position(1, 0),
                new Position(3, 0),
                new Position(5, 0)
        ));
        List<Position> expected = Arrays.asList(
                new Position(0, 12),
                new Position(3, 12),
                new Position(3, 11),
                new Position(3, 10),
                new Position(5, 12)
        );

        assertEquals(expected.size(), fallen.size());

        for (int i = 0; i < fallen.size(); i++) {
            assertEquals(fallen.get(i), expected.get(i));
        }
    }

    @Test
    public void TestCombosVertical() {
        String[] template = {
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "AG    ",
                "AG    ",
                "AG    "
        };

        Board board = createBoardFromTemplate(template);
        BoardComboChecker checker = new BoardComboChecker(board);
        List<Position> result = checker.checkCombos(Arrays.asList(
                new Position(0, 10),
                new Position(1, 10)
        ));

        List<Position> expected = Arrays.asList(
                new Position(0, 10),
                new Position(0, 11),
                new Position(0, 12),
                new Position(1, 10),
                new Position(1, 11),
                new Position(1, 12)
        );

        assertEquals(expected, result);

        result = checker.checkCombos(Arrays.asList(
                new Position(0, 11),
                new Position(1, 11)
        ));

        expected = Arrays.asList(
                new Position(0, 11),
                new Position(0, 12),
                new Position(0, 10),
                new Position(1, 11),
                new Position(1, 12),
                new Position(1, 10)
        );

        assertEquals(expected, result);

        result = checker.checkCombos(Arrays.asList(
                new Position(0, 12),
                new Position(1, 12)
        ));

        expected = Arrays.asList(
                new Position(0, 12),
                new Position(0, 11),
                new Position(0, 10),
                new Position(1, 12),
                new Position(1, 11),
                new Position(1, 10)
        );

        assertEquals(expected, result);
    }

    @Test
    public void testCombosHorizontal() {
        String[] template = {
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "AAA   "
        };

        Board board = createBoardFromTemplate(template);
        BoardComboChecker checker = new BoardComboChecker(board);
        List<Position> result = checker.checkCombos(Arrays.asList(
                new Position(0, 12)
        ));

        List<Position> expected = Arrays.asList(
                new Position(0, 12),
                new Position(1, 12),
                new Position(2, 12)
        );

        assertEquals(expected, result);

        result = checker.checkCombos(Arrays.asList(
                new Position(1, 12)
        ));

        expected = Arrays.asList(
                new Position(1, 12),
                new Position(2, 12),
                new Position(0, 12)
        );

        assertEquals(expected, result);

        result = checker.checkCombos(Arrays.asList(
                new Position(2, 12)
        ));

        expected = Arrays.asList(
                new Position(2, 12),
                new Position(1, 12),
                new Position(0, 12)
        );

        assertEquals(expected, result);
    }

    @Test
    public void testCombosRightAngles() {
        String[] template = {
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "      ",
                "  A  G",
                "A A  G",
                "A A  G",
                "AAA GG"
        };

        Board board = createBoardFromTemplate(template);
        BoardComboChecker checker = new BoardComboChecker(board);

        List<Position> result = checker.checkCombos(Arrays.asList(
                new Position(0, 12)
        ));

        List<Position> expected = Arrays.asList(
                new Position(0, 12),
                new Position(0, 11),
                new Position(0, 10),
                new Position(1, 12),
                new Position(2, 12)
        );

        assertEquals(expected, result);

        result = checker.checkCombos(Arrays.asList(
                new Position(1, 12)
        ));

        expected = Arrays.asList(
                new Position(1, 12),
                new Position(2, 12),
                new Position(0, 12)
        );

        assertEquals(expected, result);

        result = checker.checkCombos(Arrays.asList(
                new Position(2, 12)
        ));

        expected = Arrays.asList(
                new Position(2, 12),
                new Position(2, 11),
                new Position(2, 10),
                new Position(2, 9),
                new Position(1, 12),
                new Position(0, 12)
        );

        assertEquals(expected, result);

        result = checker.checkCombos(Arrays.asList(
                new Position(2, 11)
        ));

        expected = Arrays.asList(
                new Position(2, 11),
                new Position(2, 12),
                new Position(2, 10),
                new Position(2, 9)
        );

        assertEquals(expected, result);

        result = checker.checkCombos(Arrays.asList(
                new Position(5, 12)
        ));

        expected = Arrays.asList(
                new Position(5, 12),
                new Position(5, 11),
                new Position(5, 10),
                new Position(5, 9)
        );

        assertEquals(expected, result);

        result = checker.checkCombos(Arrays.asList(
                new Position(4, 12)
        ));

        expected = new ArrayList<>();

        assertEquals(expected, result);
    }

    @Test
    public void testBreaker() {
        String[] template = {
                "PPPPPP",
                "PPPPPP",
                "PPPPPP",
                "PPPPPP",
                "PPPPPP",
                "PPPPPP",
                "PPPPPP",
                "PPPPPP",
                "PPPPPP",
                "PPPPPP",
                "PPPPPP",
                "PPPPPP",
                "PPPPPP"
        };

        Board board = createBoardFromTemplate(template);
        BoardElementBreaker breaker = new BoardElementBreaker(board);

        List<Position> expected = Arrays.asList(
                new Position(0,0),
                new Position(2,2),
                new Position(3,3),
                new Position(5,6),
                new Position(0,12),
                new Position(1,12),
                new Position(5,12),
                new Position(3,4)
        );

        List<Position> result = breaker.breakElements(expected);

        assertEquals(expected, result);

        expected = new ArrayList<>();
        result = breaker.breakElements(expected);

        assertNull(result);
    }
}
