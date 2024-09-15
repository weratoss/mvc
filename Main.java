public class Main {
    // 64050676 วีรภัทร พรหมวิจิต
    public static void main(String[] args) {
        // สร้างวัวจากทั้งสามทีม
        Cow cow1 = new Cow("Cow1", "A", "white");
        Cow cow2 = new Cow("Cow2", "A", "black");
        Cow cow3 = new Cow("Cow3", "A", "brown");
        Cow cow4 = new Cow("Cow4", "B", "white");
        Cow cow5 = new Cow("Cow5", "B", "black");
        Cow cow6 = new Cow("Cow6", "B", "brown");
        Cow cow7 = new Cow("Cow7", "C", "white");
        Cow cow8 = new Cow("Cow8", "C", "black");
        Cow cow9 = new Cow("Cow9", "C", "brown");

        Cow[] cows = {cow1, cow2, cow3, cow4, cow5, cow6, cow7, cow8, cow9};

        // สร้างหน้าจอแสดงผลและ Controller
        BowlingView view = new BowlingView(cows);
        BowlingController controller = new BowlingController(cows, view);

        // ตั้งค่า Controller ใน View
        view.setController(controller);

        // แสดงหน้าจอ
        view.setVisible(true);

        // เริ่มเกม
        controller.playGame();
    }
}