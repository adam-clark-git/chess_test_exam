package chess;

import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
import java.util.*;
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType pieceType;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.pieceType = type;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChessPiece other =  (ChessPiece) obj;
        return other.getPieceType() == getPieceType() && other.getTeamColor() == getTeamColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType);
    }
    @Override
    public String toString() {
        String str = "";
        switch (pieceType) {
            case KING:
                str = "K ";
                break;
            case PAWN:
                str = "P ";
                break;
            case KNIGHT:
                str = "N ";
                break;
            case ROOK:
                str = "R ";
                break;
            case QUEEN:
                str = "Q ";
                break;
            case BISHOP:
                str ="B ";
                break;
        }
        return str;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        PieceMoves moveSet = new PieceMoves(board, myPosition, getTeamColor());
        switch (pieceType) {
            case BISHOP:
                return PieceMoves.getBishopMoves();
                break;
            case ROOK:
                return PieceMoves.getRookMoves(board, myPosition);
                break;
            case KNIGHT:
                return PieceMoves.getKnightMoves(board, myPosition);
                break;
            case QUEEN:
                return PieceMoves.getQueenMoves(board, myPosition);
                break;
            case KING:
                return PieceMoves.getKingMoves();
                break;
            case PAWN:
                return PieceMoves.getPawnMoves();
                break;
            case null, default:
                return null;
        }
    }
}
