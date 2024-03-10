package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.PawnAttackMove;
import com.chess.engine.board.Move.PawnEnPassantAttackMove;
import com.chess.engine.board.Move.PawnMove;
import com.chess.engine.board.Move.PawnJump;
import com.chess.engine.board.Move.PawnPromotion;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};

    public Pawn(final Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, true);
    }

    public Pawn(final Alliance pieceAlliance, final int piecePosition, final boolean isFirstMove) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);

            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                final Move legalMove = new PawnMove(board, this, candidateDestinationCoordinate);
                if (board.currentPlayer() != null && board.currentPlayer().isInCheck() && BoardUtils.kingThreat(legalMove)) {
                    continue;
                }
                if (this.pieceAlliance.isPawnPromotionSquare(candidateDestinationCoordinate)) {
                    legalMoves.add(new PawnPromotion(legalMove));
                } else {
                    legalMoves.add(legalMove);
                }
            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                ((BoardUtils.INSTANCE.SECOND_ROW.get(this.piecePosition) && this.pieceAlliance.isBlack()) ||
                    (BoardUtils.INSTANCE.SEVENTH_ROW.get(this.piecePosition) && this.pieceAlliance.isWhite()))) {

                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Move legalMove = new PawnJump(board, this, candidateDestinationCoordinate);
                    if (board.currentPlayer() != null && board.currentPlayer().isInCheck() && BoardUtils.kingThreat(legalMove)) {
                        continue;
                    }
                    legalMoves.add(legalMove);
                }
            } else if (currentCandidateOffset == 7 &&
                !((BoardUtils.INSTANCE.EIGHTH_COLUMN.get(this.piecePosition) && this.pieceAlliance.isWhite()) ||
                    (BoardUtils.INSTANCE.FIRST_COLUMN.get(this.piecePosition) && this.pieceAlliance.isBlack()))) {

                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        final Move legalAttackMove = new PawnAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate);
                        if (board.currentPlayer() != null && board.currentPlayer().isInCheck() && BoardUtils.kingThreat(legalAttackMove)) {
                            continue;
                        }
                        if (this.pieceAlliance.isPawnPromotionSquare(candidateDestinationCoordinate)) {
                            legalMoves.add(new PawnPromotion(legalAttackMove));
                        } else {
                            legalMoves.add(legalAttackMove);
                        }
                    }
                } else if (board.getEnPassantPawn() != null) {
                    if (board.getEnPassantPawn().getPiecePosition() == (this.piecePosition + (this.pieceAlliance.getOppositeDirection()))) {
                        final Piece pieceOnCandidate = board.getEnPassantPawn();
                        if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                            final Move legalEnPassantAttackMove = new PawnEnPassantAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate);
                            if (board.currentPlayer() != null && board.currentPlayer().isInCheck() && BoardUtils.kingThreat(legalEnPassantAttackMove)) {
                                continue;
                            }
                            legalMoves.add(legalEnPassantAttackMove);
                        }
                    }
                }
            } else if (currentCandidateOffset == 9 &&
                !((BoardUtils.INSTANCE.FIRST_COLUMN.get(this.piecePosition) && this.pieceAlliance.isWhite()) ||
                    (BoardUtils.INSTANCE.EIGHTH_COLUMN.get(this.piecePosition) && this.pieceAlliance.isBlack()))) {

                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        final Move legalAttackMove = new PawnAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate);
                        if (board.currentPlayer() != null && board.currentPlayer().isInCheck() && BoardUtils.kingThreat(legalAttackMove)) {
                            continue;
                        }
                        if (this.pieceAlliance.isPawnPromotionSquare(candidateDestinationCoordinate)) {
                            legalMoves.add(new PawnPromotion(legalAttackMove));
                        } else {
                            legalMoves.add(legalAttackMove);
                        }
                    }
                } else if (board.getEnPassantPawn() != null) {
                    if (board.getEnPassantPawn().getPiecePosition() == (this.piecePosition - (this.pieceAlliance.getOppositeDirection()))) {
                        final Piece pieceOnCandidate = board.getEnPassantPawn();
                        if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                            final Move legalEnPassantAttackMove = new PawnEnPassantAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate);
                            if (board.currentPlayer() != null && board.currentPlayer().isInCheck() && BoardUtils.kingThreat(legalEnPassantAttackMove)) {
                                continue;
                            }
                            legalMoves.add(legalEnPassantAttackMove);
                        }
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Pawn movePiece(final Move move) {
        return new Pawn(move.getMovedPiece().pieceAlliance, move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }

    public Piece getPromotionPiece() {
        // Can only promote to Queen for simplicity.
        return new Queen(this.pieceAlliance, this.piecePosition, false);
    }
}
