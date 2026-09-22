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
    public Collection<ChessMove> getBishopMoves() {
        moves = new ArrayList<ChessMove>();
        BiFunction<Integer, CardDirections, ChessPosition> movement = (num, dir) -> {
            switch(dir) {
                case NORTHEAST -> {return new ChessPosition(pos.getRow()+num, pos.getColumn()+num);}
                case NORTHWEST -> {return new ChessPosition(pos.getRow()+num, pos.getColumn()-num);}
                case SOUTHEAST -> {return new ChessPosition(pos.getRow()-num, pos.getColumn()+num);}
                case SOUTHWEST -> {return new ChessPosition(pos.getRow()-num, pos.getColumn()-num);}
                case null, default -> {return null;}
            }
        };
        straightMoves(movement);
        return moves;
    }
    public Collection<ChessMove> getQueenMoves() {
        moves = new ArrayList<ChessMove>();
        BiFunction<Integer, CardDirections, ChessPosition> movement = (num, dir) -> {
            switch(dir) {
                case NORTH -> {return new ChessPosition(pos.getRow()+num, pos.getColumn());}
                case SOUTH -> {return new ChessPosition(pos.getRow()-num, pos.getColumn());}
                case EAST -> {return new ChessPosition(pos.getRow(), pos.getColumn()+num);}
                case WEST -> {return new ChessPosition(pos.getRow(), pos.getColumn()-num);}
                case NORTHEAST -> {return new ChessPosition(pos.getRow()+num, pos.getColumn()+num);}
                case NORTHWEST -> {return new ChessPosition(pos.getRow()+num, pos.getColumn()-num);}
                case SOUTHEAST -> {return new ChessPosition(pos.getRow()-num, pos.getColumn()+num);}
                case SOUTHWEST -> {return new ChessPosition(pos.getRow()-num, pos.getColumn()-num);}
                case null, default -> {return null;}
            }
        };
        straightMoves(movement);
        return moves;
    }
    public Collection<ChessMove> getKnightMoves() {
        moves = new ArrayList<ChessMove>();
        BiFunction<Integer, CardDirections, ChessPosition> movement = (num, dir) -> {
            if (num >1) return null;
            switch(dir) {
                case NORTH -> {return new ChessPosition(pos.getRow()+2, pos.getColumn()+1);}
                case SOUTH -> {return new ChessPosition(pos.getRow()+2, pos.getColumn()-1);}
                case EAST -> {return new ChessPosition(pos.getRow()-2, pos.getColumn()+1);}
                case WEST -> {return new ChessPosition(pos.getRow()-2, pos.getColumn()-1);}
                case NORTHEAST -> {return new ChessPosition(pos.getRow()+1, pos.getColumn()+2);}
                case NORTHWEST -> {return new ChessPosition(pos.getRow()-1, pos.getColumn()+2);}
                case SOUTHEAST -> {return new ChessPosition(pos.getRow()+1, pos.getColumn()-2);}
                case SOUTHWEST -> {return new ChessPosition(pos.getRow()-1, pos.getColumn()-2);}
                case null, default -> {return null;}
            }
        };
        straightMoves(movement);
        return moves;
    }
    public Collection<ChessMove> getKingMoves() {
        moves = new ArrayList<ChessMove>();
        BiFunction<Integer, CardDirections, ChessPosition> movement = (num, dir) -> {
            if (num >1) return null;
            switch(dir) {
                case NORTH -> {return new ChessPosition(pos.getRow()+num, pos.getColumn());}
                case SOUTH -> {return new ChessPosition(pos.getRow()-num, pos.getColumn());}
                case EAST -> {return new ChessPosition(pos.getRow(), pos.getColumn()+num);}
                case WEST -> {return new ChessPosition(pos.getRow(), pos.getColumn()-num);}
                case NORTHEAST -> {return new ChessPosition(pos.getRow()+num, pos.getColumn()+num);}
                case NORTHWEST -> {return new ChessPosition(pos.getRow()+num, pos.getColumn()-num);}
                case SOUTHEAST -> {return new ChessPosition(pos.getRow()-num, pos.getColumn()+num);}
                case SOUTHWEST -> {return new ChessPosition(pos.getRow()-num, pos.getColumn()-num);}
                case null, default -> {return null;}
            }
        };
        straightMoves(movement);
        return moves;
    }
    public Collection<ChessMove> getPawnMoves() {
        moves = new ArrayList<ChessMove>();
        int dir = 1;
        if (color == ChessGame.TeamColor.BLACK) dir = -1;

        ChessMove move =new ChessMove(pos, new ChessPosition( pos.getRow()+dir, pos.getColumn()-1), null);
        if (isValidMove(move) && isCapture(move)) {
            pawnPromote(move);
        }

        move =new ChessMove(pos, new ChessPosition( pos.getRow()+dir, pos.getColumn()+1), null);
        if (isValidMove(move) && isCapture(move)) {
            pawnPromote(move);
        }

        move =new ChessMove(pos, new ChessPosition( pos.getRow()+dir, pos.getColumn()), null);
        if (isValidMove(move) && !isCapture(move)) {
            pawnPromote(move);
            if ((pos.getRow() == 2 && color == ChessGame.TeamColor.WHITE) ||(pos.getRow() == 7 && color == ChessGame.TeamColor.BLACK)) {
                move =new ChessMove(pos, new ChessPosition( pos.getRow()+(2*dir), pos.getColumn()), null);
                if (isValidMove(move) && !isCapture(move)) {
                    pawnPromote(move);
                }
            }
        }

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
    private void pawnPromote(ChessMove move) {
        if (move.getEndPosition().getRow() != 1 && move.getEndPosition().getRow() != 8) {
            moves.add(move);
            return;
        }
        for (ChessPiece.PieceType piece : ChessPiece.PieceType.values()) {
            if (piece == ChessPiece.PieceType.KING || piece == ChessPiece.PieceType.PAWN) continue;
            moves.add(new ChessMove(move.getStartPosition(), move.getEndPosition(), piece));
        }
    }
}
