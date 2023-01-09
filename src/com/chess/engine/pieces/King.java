package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {
    private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(Alliance pieceAlliance, int piecePosition) {
        super(pieceAlliance, piecePosition);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset: CANDIDATE_MOVE_COORDINATE){
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                    isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)){
                    continue;
                }
                if(!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                }
                else if(candidateDestinationTile.getPiece().pieceAlliance != this.pieceAlliance) {
                    final Piece candidateDestinationPiece = candidateDestinationTile.getPiece();
                    legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, candidateDestinationPiece));
                }
            }

        }
        return ImmutableList.copyOf(legalMoves);
    }

    private final boolean isFirstColumnExclusion(final int currentPos, final int offset){
        return BoardUtils.FIRST_COLUMN[currentPos] && (offset == 7 || offset == -9 || offset == -1);
    }
    private final boolean isEighthColumnExclusion(final int currentPos, final int offset){
        return BoardUtils.EIGHTH_COLUMN[currentPos] && (offset == 9 || offset == -7 || offset == 1);
    }
    @Override
    public String toString(){
        return PieceType.KING.toString();
    }
}
