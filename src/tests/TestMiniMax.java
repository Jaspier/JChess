package tests;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Board.Builder;
import com.chess.engine.pieces.*;
import com.chess.engine.player.ai.MiniMax;
import com.chess.engine.player.ai.MoveStrategy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMiniMax {

    @Test
    public void testOpeningDepth1() {
        final Board board = Board.createStandardBoard();
        final MoveStrategy minMax = new MiniMax(1);
        minMax.execute(board);
        final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
        assertEquals(numBoardsEvaluated, 20L);
    }

    @Test
    public void testOpeningDepth2() {
        final Board board = Board.createStandardBoard();
        final MoveStrategy minMax = new MiniMax(2);
        minMax.execute(board);
        final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
        assertEquals(numBoardsEvaluated, 400L);
    }

    @Test
    public void testOpeningDepth3() {
        final Board board = Board.createStandardBoard();
        final MoveStrategy minMax = new MiniMax(3);
        minMax.execute(board);
        final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
        assertEquals(numBoardsEvaluated, 8902L);
    }

    @Test
    public void testOpeningDepth4() {
        final Board board = Board.createStandardBoard();
        final MoveStrategy minMax = new MiniMax(4);
        minMax.execute(board);
        final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
        assertEquals(numBoardsEvaluated, 197281L);
    }

    @Test
    public void testKiwiPeteDepth1() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Rook(Alliance.BLACK, 0));
        builder.setPiece(new King(Alliance.BLACK, 4, false, false));
        builder.setPiece(new Rook(Alliance.BLACK, 7));
        builder.setPiece(new Pawn(Alliance.BLACK, 8));
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 11));
        builder.setPiece(new Queen(Alliance.BLACK, 12));
        builder.setPiece(new Pawn(Alliance.BLACK, 13));
        builder.setPiece(new Bishop(Alliance.BLACK, 14));
        builder.setPiece(new Bishop(Alliance.BLACK, 16));
        builder.setPiece(new Knight(Alliance.BLACK, 17));
        builder.setPiece(new Pawn(Alliance.BLACK, 20));
        builder.setPiece(new Knight(Alliance.BLACK, 21));
        builder.setPiece(new Pawn(Alliance.BLACK, 22));
        builder.setPiece(new Pawn(Alliance.BLACK, 33));
        builder.setPiece(new Pawn(Alliance.BLACK, 47));
        // White Layout
        builder.setPiece(new Pawn(Alliance.WHITE, 27));
        builder.setPiece(new Knight(Alliance.WHITE, 28));
        builder.setPiece(new Pawn(Alliance.WHITE, 36));
        builder.setPiece(new Knight(Alliance.WHITE, 42));
        builder.setPiece(new Queen(Alliance.WHITE, 45));
        builder.setPiece(new Pawn(Alliance.WHITE, 48));
        builder.setPiece(new Pawn(Alliance.WHITE, 49));
        builder.setPiece(new Pawn(Alliance.WHITE, 50));
        builder.setPiece(new Bishop(Alliance.WHITE, 51));
        builder.setPiece(new Bishop(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 53));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        builder.setPiece(new Pawn(Alliance.WHITE, 55));
        builder.setPiece(new Rook(Alliance.WHITE, 56));
        builder.setPiece(new King(Alliance.WHITE, 60, false, false));
        builder.setPiece(new Rook(Alliance.WHITE, 63));
        // Set the current player
        builder.setMoveMaker(Alliance.WHITE);
        final Board board = builder.build();
        final MoveStrategy minMax = new MiniMax(1);
        minMax.execute(board);
        final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
        assertEquals(numBoardsEvaluated, 48);
    }

    @Test
    public void testPosition3Depth1() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 19));
        builder.setPiece(new Rook(Alliance.BLACK, 31));
        builder.setPiece(new Pawn(Alliance.BLACK, 37));
        builder.setPiece(new King(Alliance.BLACK, 39, false, false));
        // White Layout
        builder.setPiece(new King(Alliance.WHITE, 24, false, false));
        builder.setPiece(new Pawn(Alliance.WHITE, 25));
        builder.setPiece(new Rook(Alliance.WHITE, 33));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        // Set the current player
        builder.setMoveMaker(Alliance.WHITE);
        final Board board = builder.build();
        final MoveStrategy minMax = new MiniMax(1);
        minMax.execute(board);
        final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
        assertEquals(numBoardsEvaluated, 14);
    }

    @Test
    public void testPosition3Depth2() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 19));
        builder.setPiece(new Rook(Alliance.BLACK, 31));
        builder.setPiece(new Pawn(Alliance.BLACK, 37));
        builder.setPiece(new King(Alliance.BLACK, 39, false, false));
        // White Layout
        builder.setPiece(new King(Alliance.WHITE, 24, false, false));
        builder.setPiece(new Pawn(Alliance.WHITE, 25));
        builder.setPiece(new Rook(Alliance.WHITE, 33));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        // Set the current player
        builder.setMoveMaker(Alliance.WHITE);
        final Board board = builder.build();
        final MoveStrategy minMax = new MiniMax(2);
        minMax.execute(board);
        final long numBoardsEvaluated = minMax.getNumBoardsEvaluated();
        assertEquals(numBoardsEvaluated, 191);
    }
}
