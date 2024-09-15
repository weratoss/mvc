public class BowlingController {
    private Cow[] cows;
    private BowlingView view;

    public BowlingController(Cow[] cows, BowlingView view) {
        this.cows = cows;
        this.view = view;
    }

    // เริ่มเกม โดยหมุนการเล่นของวัวทั้งหมด
    public void playGame() {
        for (int round = 1; round <= 10; round++) {
            for (Cow cow : cows) {
                playRound(cow);
            }
        }
        view.displayFinalResults(cows);  // แสดงผลลัพธ์เมื่อจบเกม
    }

    // การเล่นแต่ละรอบของวัวแต่ละตัว
    private void playRound(Cow cow) {
        int remainingPins = 10;

        // โยนครั้งแรก
        int firstRoll = cow.roll(remainingPins);
        firstRoll = cow.checkLying(firstRoll, true);
        remainingPins -= firstRoll;

        if (firstRoll == 10) {
            // Cow Strike
            view.displayRound(cow, 10, "Cow Strike");
            cow.addScore(10);
        } else {
            // โยนครั้งที่สอง
            int secondRoll = cow.roll(remainingPins);
            secondRoll = cow.checkLying(secondRoll, false);
            int totalPins = firstRoll + secondRoll;

            if (totalPins == 10) {
                // Cow Spare
                view.displayRound(cow, totalPins, "Cow Spare");
                cow.addScore(10);
            } else {
                // Cow Open
                view.displayRound(cow, totalPins, "Cow Open");
                cow.addScore(totalPins);
            }
        }
    }
}
