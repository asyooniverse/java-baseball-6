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
        computer.selectNumbers();
        play();
    }

    public void play() {
        boolean isNotThreeStrike = true;
        while (isNotThreeStrike) {
            isNotThreeStrike = compare();
            outputView.printResult(gameResult);
        }
        outputView.printThreeStrike();
    }

    public boolean compare() {
        player.setNumbers(inputView.readNumbers());
        compareSystem = new CompareSystem(computer, player);
        gameResult = compareSystem.compare();
        return gameResult.isNotThreeStrike();
    }
}
