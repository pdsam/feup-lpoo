import crackattack.Constants;
import crackattack.controller.board.BoardChangeObserver;
import crackattack.controller.board.BoardComboChecker;
import crackattack.controller.board.BoardElementBreaker;
import crackattack.controller.board.BoardGravityChecker;
import crackattack.controller.board.commands.SwapCommand;
import crackattack.model.board.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.times;

public class TestCommandsWithObservers {
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
    public void testCompleteBreaking() {

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

        String[] resultTemplate = {
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
                "      "
        };

        Board board = createBoardFromTemplate(template);
        BoardScore score = new BoardScore();

        BoardGravityChecker gravity = Mockito.spy(new BoardGravityChecker(board));
        BoardComboChecker combo = Mockito.spy(new BoardComboChecker(board));
        BoardElementBreaker breaker = Mockito.spy(new BoardElementBreaker(board));

        BoardChangeObserver observer = new BoardChangeObserver(board, score, gravity, combo, breaker);
        board.attachObserver(observer);
        board.notifyObserver(Arrays.asList(new Position(0,12)));


        //Check execution calls
        Mockito.verify(gravity, times(1)).updateGravity(Arrays.asList());
        Mockito.verify(combo, times(1)).checkCombos(Arrays.asList(
                new Position(0, 12))
        );
        Mockito.verify(breaker, times(1)).breakElements(Arrays.asList(
                new Position(0,12),
                new Position(1, 12),
                new Position(2, 12)
        ));

        Mockito.verify(gravity, times(1)).updateGravity(Arrays.asList(
                new Position(0,12),
                new Position(1, 12),
                new Position(2, 12)
        ));
        Mockito.verify(combo, times(1)).checkCombos(Arrays.asList());
        Mockito.verify(breaker, times(1)).breakElements(Arrays.asList());

        GridElement[][] expected = generateContentFromTemplate(resultTemplate);

        for (int i = 0; i < board.getMaxY(); i++) {
            for (int j = 0; j < board.getMaxX(); j++) {
                assertEquals(expected[i][j], board.getGridElement(new Position(j, i)));
            }
        }

        assertEquals(3, score.getScore());
    }

    @Test
    public void testCompleteBreaking2() {
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
                "G     ",
                "A     ",
                "A     ",
                "AAA   "
        };

        String[] resultTemplate = {
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
                "G     "
        };

        Board board = createBoardFromTemplate(template);
        BoardScore score = new BoardScore();

        BoardGravityChecker gravity = Mockito.spy(new BoardGravityChecker(board));
        BoardComboChecker combo = Mockito.spy(new BoardComboChecker(board));
        BoardElementBreaker breaker = Mockito.spy(new BoardElementBreaker(board));

        BoardChangeObserver observer = new BoardChangeObserver(board, score, gravity, combo, breaker);
        board.attachObserver(observer);
        board.notifyObserver(Arrays.asList(new Position(0,12)));


        //Check execution calls
        Mockito.verify(gravity, times(1)).updateGravity(Arrays.asList());
        Mockito.verify(combo, times(2)).checkCombos(Arrays.asList(
                new Position(0, 12))
        );
        Mockito.verify(breaker, times(1)).breakElements(Arrays.asList(
                new Position(0,12),
                new Position(0, 11),
                new Position(0, 10),
                new Position(1, 12),
                new Position(2, 12)
        ));

        Mockito.verify(gravity, times(1)).updateGravity(Arrays.asList(
                new Position(0,12),
                new Position(0, 11),
                new Position(0, 10),
                new Position(1, 12),
                new Position(2, 12)
        ));
        Mockito.verify(breaker, times(1)).breakElements(Arrays.asList());

        GridElement[][] expected = generateContentFromTemplate(resultTemplate);

        for (int i = 0; i < board.getMaxY(); i++) {
            for (int j = 0; j < board.getMaxX(); j++) {
                assertEquals(expected[i][j], board.getGridElement(new Position(j, i)));
            }
        }

        assertEquals(5, score.getScore());
    }

    @Test
    public void testCompleteBreakingComboSequence() {
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
                "A     ",
                "G     ",
                "G     ",
                "GAA   "
        };

        String[] resultTemplate = {
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
                "      "
        };

        Board board = createBoardFromTemplate(template);
        BoardScore score = new BoardScore();

        BoardGravityChecker gravity = Mockito.spy(new BoardGravityChecker(board));
        BoardComboChecker combo = Mockito.spy(new BoardComboChecker(board));
        BoardElementBreaker breaker = Mockito.spy(new BoardElementBreaker(board));

        BoardChangeObserver observer = new BoardChangeObserver(board, score, gravity, combo, breaker);
        board.attachObserver(observer);
        board.notifyObserver(Arrays.asList(new Position(0,12)));


        //Check execution calls
        Mockito.verify(gravity, times(1)).updateGravity(Arrays.asList());
        Mockito.verify(combo, times(2)).checkCombos(Arrays.asList(
                new Position(0, 12))
        );
        Mockito.verify(breaker, times(1)).breakElements(Arrays.asList(
                new Position(0,12),
                new Position(0, 11),
                new Position(0, 10)
        ));

        Mockito.verify(gravity, times(1)).updateGravity(Arrays.asList(
                new Position(0,12),
                new Position(0, 11),
                new Position(0, 10)
        ));
        Mockito.verify(breaker, times(1)).breakElements(Arrays.asList(
                new Position(0, 12),
                new Position(1, 12),
                new Position(2, 12)
        ));
        Mockito.verify(gravity, times(1)).updateGravity(Arrays.asList(
                new Position(0, 12),
                new Position(1, 12),
                new Position(2, 12)
        ));
        Mockito.verify(combo, times(1)).checkCombos(Arrays.asList());
        Mockito.verify(breaker, times(1)).breakElements(Arrays.asList());

        GridElement[][] expected = generateContentFromTemplate(resultTemplate);

        for (int i = 0; i < board.getMaxY(); i++) {
            for (int j = 0; j < board.getMaxX(); j++) {
                assertEquals(expected[i][j], board.getGridElement(new Position(j, i)));
            }
        }

        assertEquals(9, score.getScore());
    }

    @Test
    public void testSwapNotification() {
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
                "A     ",
                "B     ",
                "G     "
        };

        String[] resultTemplate = {
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
                "B     ",
                "GA    "
        };

        Board board = createBoardFromTemplate(template);
        board.getSelector().setPos(new Position(0, 10));
        BoardScore score = new BoardScore();

        BoardGravityChecker gravity = Mockito.spy(new BoardGravityChecker(board));
        BoardComboChecker combo = Mockito.spy(new BoardComboChecker(board));
        BoardElementBreaker breaker = Mockito.spy(new BoardElementBreaker(board));

        BoardChangeObserver observer = Mockito.spy(new BoardChangeObserver(board, score, gravity, combo, breaker));
        board.attachObserver(observer);
        new SwapCommand(board).exec();


        //Check execution calls
        Mockito.verify(observer, times(1)).update(Arrays.asList(
                new Position(0,10),
                new Position(1,10)
        ));
        Mockito.verify(gravity, times(1)).updateGravity(Arrays.asList(
                new Position(0,10),
                new Position(1,10)
        ));
        Mockito.verify(combo, times(1)).checkCombos(Arrays.asList(
                new Position(1,12)
        ));
        Mockito.verify(breaker, times(1)).breakElements(Arrays.asList());

        GridElement[][] expected = generateContentFromTemplate(resultTemplate);

        for (int i = 0; i < board.getMaxY(); i++) {
            for (int j = 0; j < board.getMaxX(); j++) {
                assertEquals(expected[i][j], board.getGridElement(new Position(j, i)));
            }
        }

        assertEquals(0, score.getScore());
    }
}
