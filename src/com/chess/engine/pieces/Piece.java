package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;
import java.util.List;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    Piece(final Alliance pieceAlliance,final int piecePosition ) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        isFirstMove = false;
    }
    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }
    public int getPiecePosition(){
        return this.piecePosition;
    }
    public boolean isFirstMove(){
        return this.isFirstMove;
    }
    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public enum PieceType {
        PAWN("P"),
        KNIGHT("N"),
        BISHOP("B"),
        QUEEN("Q"),
        ROOK("R"),
        KING("K");
        private final String pieceName;

        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }
    }

}
