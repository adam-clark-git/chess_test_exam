package chess;

import java.util.*;
import java.util.function.BiFunction;

public class PieceMoves {
    private ChessBoard board;
    private ChessPosition pos;
    private ChessGame.TeamColor color;

    public PieceMoves(ChessBoard board, ChessPosition pos, ChessGame.TeamColor color) {
        this.board = board;
        this.pos = pos;
        this.color = color;
    }
    public enum CardDirections {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
    public Collection<ChessMove> getBishopMoves() {
        List<ChessMove> moves = new ArrayList<ChessMove>();
        /*BiFunction<Integer, CardDirections, ChessPosition> movement = (num, dir) -> {
            switch(dir) {
                case NORTH ->
            }
        }*/
        return null;
    }
    private Collection<ChessMove> straightMoves(BiFunction<Integer, CardDirections, ChessPosition> movement) {
        for (CardDirections dir : CardDirections.values()) {

        }
        return null;
    }
    private boolean isValidMove() {
        return true;
    }
    private boolean isCapture() {
        return true;
    }
}
