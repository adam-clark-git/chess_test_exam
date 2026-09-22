package chess;

import java.util.*;
import java.util.function.BiFunction;

public class PieceMoves {
    final private ChessBoard board;
    final private ChessPosition pos;
    final private ChessGame.TeamColor color;
    private List<ChessMove> moves;

    public PieceMoves(ChessBoard board, ChessPosition pos, ChessGame.TeamColor color) {
        this.board = board;
        this.pos = pos;
        this.color = color;
        moves = new ArrayList<ChessMove>();
    }
    public enum CardDirections {
        NORTH,
        NORTHEAST,
        NORTHWEST,
        SOUTH,
        SOUTHEAST,
        SOUTHWEST,
        EAST,
        WEST
    }
    public Collection<ChessMove> getRookMoves() {
        moves = new ArrayList<ChessMove>();
        BiFunction<Integer, CardDirections, ChessPosition> movement = (num, dir) -> {
            switch(dir) {
                case NORTH -> {return new ChessPosition(pos.getRow()+num, pos.getColumn());}
                case SOUTH -> {return new ChessPosition(pos.getRow()-num, pos.getColumn());}
                case EAST -> {return new ChessPosition(pos.getRow(), pos.getColumn()+num);}
                case WEST -> {return new ChessPosition(pos.getRow(), pos.getColumn()-num);}
                case null, default -> {return null;}
            }
        };
        straightMoves(movement);
        return moves;
    }
    private void straightMoves(BiFunction<Integer, CardDirections, ChessPosition> movement) {
        for (CardDirections dir : CardDirections.values()) {
            for (int i = 1; i < 8; i++) {
                ChessPosition endPos = movement.apply(i,dir);
                if (endPos == null) break;
                ChessMove move = new ChessMove(pos,endPos, null);
                if (!isValidMove(move)) {
                    break;
                }
                moves.add(move);
                if (isCapture(move)) {
                    break;
                }
            }
        }
    }
    private boolean isValidMove(ChessMove move) {
        if (move.getEndPosition().getRow() < 1 || move.getEndPosition().getColumn() < 1 || move.getEndPosition().getRow() > 8 || move.getEndPosition().getColumn() > 8) {
            return false;
        }
        return board.getPiece(move.getEndPosition()) == null || board.getPiece(move.getEndPosition()).getTeamColor() != color;
    }
    private boolean isCapture(ChessMove move) {
        return board.getPiece(move.getEndPosition()) != null && board.getPiece(move.getEndPosition()).getTeamColor() != color;
    }
}
