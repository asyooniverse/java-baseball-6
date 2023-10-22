package baseball;

public class GameController {
    private final Computer computer = new Computer();
    private final Player player = new Player();
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private CompareSystem compareSystem;
    private GameResult gameResult;

    public void start() {
        outputView.printStartMessage();
        play();
    }

    public void play() {
        computer.selectNumbers();
        boolean isNotThreeStrike = true;
        while (isNotThreeStrike) {
            isNotThreeStrike = compare();
            outputView.printResult(gameResult);
        }
        outputView.printThreeStrike();
        restartOrExit();
    }

    public void restartOrExit() {
        outputView.printRestartOrExit();
        if (inputView.readReplayNumber() == RestartStatus.RESTART) {
            computer.init();
            play();
            return;
        }
        outputView.printExit();
    }

    public boolean compare() {
        outputView.printInputNumberMessage();
        player.setNumbers(inputView.readNumbers());
        compareSystem = new CompareSystem(computer, player);
        gameResult = compareSystem.compare();
        return gameResult.isNotThreeStrike();
    }
}
