package chessEngine.currentGame;

import chessEngine.account.Account;
import chessEngine.account.AccountService;
import chessEngine.chess.ChildGenerator;
import chessEngine.chess.EnginePosition;
import chessEngine.chess.engineMove.EngineMove;
import chessEngine.gameRecord.GameRecordService;
import chessEngine.gameRecord.GameRecord;
import chessEngine.move.MoveRequestResponse;
import chessEngine.position.Position;
import chessEngine.position.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CurrentGameService {
    private final AccountService accountService;
    private final GameRecordService gameRecordService;
    private final CurrentGameRepository currentGameRepository;
    private final PositionService positionService;

    public CurrentGameResponse getCurrentGameFor(String username) {
        try {
            Account account = accountService.findByUsername(username).orElseThrow();
            CurrentGame currentGame = account.getCurrentGame();
            Position position = currentGame.getPosition();
            return new CurrentGameResponse(
                    position.getPositionCode(),
                    0,
                    position.isWhiteMoves()
            );
        }
        catch (Exception e) {
            return new CurrentGameResponse(
                    "",
                    1,
                    true
            );
        }
    }

    @Transactional
    public void createFor(String username) {
        try {
            Account account = accountService.findByUsername(username).orElseThrow();
            GameRecord gameRecord = new GameRecord(account);
            gameRecordService.save(gameRecord);
            Position position = positionService.getStartingPosition();
            CurrentGame currentGame = new CurrentGame(account, gameRecord, position);
            currentGameRepository.save(currentGame);
            account.setCurrentGame(currentGame);
            accountService.save(account);
        }
        catch (Exception e ) {
            throw new IllegalArgumentException("Error while creating game for: " + username);
        }
    }
    @Transactional
    public MoveRequestResponse returnMove(String username, MoveRequestResponse moveRequestResponse) {
        Account account  = accountService.findByUsername(username).orElseThrow();
        CurrentGame currentGame = account.getCurrentGame();
        GameRecord gameRecord = currentGame.getGameRecord();
        updateGameBooleanFields(moveRequestResponse);
        EnginePosition enginePosition = makeOpponentsMove(currentGame, gameRecord, moveRequestResponse);
        EngineMove myEngineMove = null;
        try {
            myEngineMove = enginePosition.generateRandomMove();
        }   catch (IllegalStateException e) {
            finishGame(currentGame, gameRecord);
            return new MoveRequestResponse(-1, -1, -1, -1, -1);
        }
        EnginePosition myNewEnginePosition = ChildGenerator.generateChild(enginePosition, myEngineMove);
        MoveRequestResponse myResponse = myEngineMove.getRequestResponse();
        System.out.println(myResponse);

        saveMoveChanges(myNewEnginePosition, currentGame, gameRecord, moveRequestResponse);
        gameRecordService.updateGameRecord(gameRecord.getId(), moveRequestResponse.toString() + myResponse.toString());
        updateGameBooleanFields(myResponse);

        return myResponse;
    }

    private void finishGame(CurrentGame currentGame, GameRecord gameRecord) {
        gameRecord.setFinished(true);
        gameRecord.setFinishedAt(LocalDateTime.now());
        gameRecordService.save(gameRecord);
        currentGameRepository.delete(currentGame);
    }


    private void updateGameBooleanFields(MoveRequestResponse moveRequestResponse) {
        if (moveRequestResponse.getFromY() == 0 && moveRequestResponse.getFromX() == 0) {
            currentGameRepository.setBlackLeftRookMovedTrue();
        }
        else if (moveRequestResponse.getFromY() == 0 && moveRequestResponse.getFromX() == 4) {
            currentGameRepository.setBlackKingMovedTrue();
        }
        else if (moveRequestResponse.getFromY() == 0 && moveRequestResponse.getFromX() == 7) {
            currentGameRepository.setBlackRightRookMovedTrue();
        }
        else if (moveRequestResponse.getFromY() == 7 && moveRequestResponse.getFromX() == 0) {
            currentGameRepository.setWhiteLeftRookMovedTrue();
        }
        else if (moveRequestResponse.getFromY() == 7 && moveRequestResponse.getFromX() == 4) {
            currentGameRepository.setWhiteKingMovedTrue();
        }
        else if (moveRequestResponse.getFromY() == 7 && moveRequestResponse.getFromX() == 7) {
            currentGameRepository.setWhiteRightRookMovedTrue();
        }
    }

    private EnginePosition makeOpponentsMove(
            CurrentGame currentGame,
            GameRecord gameRecord,
            MoveRequestResponse moveRequestResponse
        ) {
        Position position = currentGame.getPosition();
        EngineMove parentEngineMove = gameRecord.getParentEngineMove();
        EnginePosition currentEnginePosition = position.getEnginePosition(parentEngineMove);
        EngineMove nextEngineMove = moveRequestResponse.generateEngineMove();
        if (!currentEnginePosition.possibleLegalMoves().contains(nextEngineMove)) {
            System.out.println("Excepiton, printing illegal piece: ");
            System.out.println(
                    currentEnginePosition
                            .getChessBoard()
                            [moveRequestResponse.getFromY()]
                            [moveRequestResponse.getFromX()]
            );
            System.out.println("Exception, printing possible moves");
            for(EngineMove possibleEngineMove: currentEnginePosition.possibleLegalMoves()) {
                System.out.println(possibleEngineMove);
            }
            throw new IllegalStateException("given move is illegal: " + moveRequestResponse);

        }
        EnginePosition nextEnginePosition = ChildGenerator.generateChild(currentEnginePosition, nextEngineMove);
        saveMoveChanges(nextEnginePosition, currentGame, gameRecord, moveRequestResponse);
        return nextEnginePosition;
    }

    private void saveMoveChanges(
            EnginePosition nextEnginePosition,
            CurrentGame currentGame,
            GameRecord gameRecord,
            MoveRequestResponse moveRequestResponse
        ) {
        Position nextPosition = nextEnginePosition.getPosition();
        Optional<Position> optionalPosition = positionService.getPositionByDetails(nextPosition);
        if (!optionalPosition.isPresent()) {
            positionService.save(nextPosition);
            currentGame.setPosition(nextPosition);
        }
        else {
            currentGame.setPosition(optionalPosition.get());
        }
        currentGameRepository.save(currentGame);
    }


}
