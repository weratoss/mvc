import java.util.Random;

public class Cow {
    private String name;
    private String team;
    private String color;
    private int score;
    private Random random;

    public Cow(String name, String team, String color) {
        this.name = name;
        this.team = team;
        this.color = color;
        this.score = 0;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void setScoreZero(){
        this.score = 0;
    }

    // สุ่มการโยนพิน (สุ่มจำนวนพินที่ล้ม)
    public int roll(int remainingPins) {
        return random.nextInt(remainingPins + 1); // สุ่มตั้งแต่ 0 ถึงพินที่เหลือ 
    }

    // ตรวจจับการโกหกของวัวสีดำหรือการถ่อมตัวของวัวสีขาว
    public int checkLying(int pinsKnockedDown, boolean isFirstRoll) {
        // กรณีวัวสีดำโกหกบอกว่าล้มพินทั้งหมด มีโอกาส 20 %
        if ("black".equals(color) && random.nextDouble() < 0.2) {
            return 10;
        } 
        // กรณีวัวสีขาวถ่อมตัวบอกว่าล้างท่อ มีโอกาส 10 %
        else if ("white".equals(color) && random.nextDouble() < 0.1) {
            return isFirstRoll ? 0 : pinsKnockedDown;
        }
        // กรณีที่ไม่ใช่วัวสีดำเเละขาว
        return pinsKnockedDown;
    }
}