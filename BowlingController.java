import java.util.Arrays;
import java.util.Comparator;

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
                playRound(cow, round);
            }
        }
        // แสดงผลลัพธ์เมื่อจบเกม
        calculateRanking();
        calculateTeamScores();
    }

    // การเล่นแต่ละรอบของวัวแต่ละตัว
    private void playRound(Cow cow, int round) {
        int remainingPins = 10;

        // โยนครั้งแรก
        int firstRoll = cow.roll(remainingPins);
        firstRoll = cow.checkLying(firstRoll, true);
        remainingPins -= firstRoll;

        if (firstRoll == 10) {
            // Cow Strike
            view.displayRound(cow, 10, "Cow Strike", round);
            cow.addScore(10);
        } else {
            // โยนครั้งที่สอง
            int secondRoll = cow.roll(remainingPins);
            secondRoll = cow.checkLying(secondRoll, false);
            int totalPins = firstRoll + secondRoll;

            if (totalPins == 10) {
                // Cow Spare
                view.displayRound(cow, totalPins, "Cow Spare", round);
                cow.addScore(10);
            } else {
                // Cow Open
                view.displayRound(cow, totalPins, "Cow Open", round);
                cow.addScore(totalPins);
            }
        }
    }
    private void calculateRanking() {
        String txtDisplay = "";
        // จัดเรียงวัวตามคะแนนจากมากไปน้อย
        Arrays.sort(cows, Comparator.comparingInt(Cow::getScore).reversed());

        // แสดงอันดับวัวแต่ละตัว หากคะแนนเท่ากันให้ครองอันดับร่วม
        int tmp = 0;
        int i = 0;
        for(Cow cow : cows){
            if(tmp != cow.getScore()){
                i+=1;
            }
            txtDisplay += (" rank : " + i + ". " + cow.getName() + " Team :  " + cow.getTeam() +
                     " scored " + cow.getScore() + " points.\n");
            tmp = cow.getScore();
        }
        view.displayFinalResults(txtDisplay); 
    }
    
     // คำนวณคะแนนรวมของแต่ละทีมและส่งข้อมูลไปแสดงผล
    private void calculateTeamScores() {
        int teamAScore = 0;
        int teamBScore = 0;
        int teamCScore = 0;
        String winner;

        // คำนวณคะแนนของวัวแต่ละตัวตามทีม
        for (Cow cow : cows) {
            switch (cow.getTeam()) {
                case "A":
                    teamAScore += cow.getScore();
                    break;
                case "B":
                    teamBScore += cow.getScore();
                    break;
                case "C":
                    teamCScore += cow.getScore();
                    break;
            }
        }
        // หาทีมที่ชนะ
        if (teamAScore > teamBScore && teamAScore > teamCScore) {
            winner = "Team A wins!";
        } else if (teamBScore > teamAScore && teamBScore > teamCScore) {
            winner = "Team B wins!";
        } else if (teamCScore > teamAScore && teamCScore > teamBScore) {
            winner = "Team C wins!";
        } else {
            winner = "It's a tie!";
        }

        // ส่งข้อมูลคะแนนทีมไปยัง View
        view.displayTeamResults(teamAScore, teamBScore, teamCScore, winner);
    }
}
