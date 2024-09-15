import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BowlingView extends JFrame {
    private JTextArea textArea;
    private JButton resetButton;
    private BowlingController controller;
    private Cow[] cows;

    public BowlingView(Cow[] cows) {
        this.cows = cows;
        setTitle("Bowling Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // สร้าง TextArea สำหรับแสดงผลลัพธ์
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // สร้างปุ่มเริ่มเกมใหม่
        resetButton = new JButton("Start New Game");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame(); // เรียกฟังก์ชันเริ่มเกมใหม่
            }
        });
        // เพิ่ม TextArea และปุ่มลงใน JFrame
        add(scrollPane, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);
    }

    // ฟังก์ชันสำหรับแสดงผลการโยนของวัวในแต่ละรอบ
    public void displayRound(Cow cow, int pinsKnockedDown, String typeScore, int round) {
        textArea.append(" Round : " + round +" | Name :  " + cow.getName() + ", Color : " + cow.getColor() + ", Team : " + cow.getTeam() +
                ", down : " + pinsKnockedDown + " pins, typeScore : " + typeScore + "\n");
    }

    // ฟังก์ชันสำหรับแสดงผลคะแนนสุดท้ายของวัวแต่ละตัว และจัดอันดับตามคะแนน
    public void displayFinalResults(String txtDisplay) {
        textArea.append("\n   Final Results (Rankings):\n");
        textArea.append(txtDisplay);
    }
    // ฟังก์ชันสำหรับแสดงผลคะแนนรวมของทีม
    public void displayTeamResults(int teamAScore, int teamBScore, int teamCScore, String winner) {
        textArea.append("\n   Team Scores:\n");
        textArea.append(" Team A: " + teamAScore + " points\n");
        textArea.append(" Team B: " + teamBScore + " points\n");
        textArea.append(" Team C: " + teamCScore + " points\n");
        textArea.append("\n" + winner + "\n");
    }

    // ฟังก์ชันสำหรับตั้งค่า Controller
    public void setController(BowlingController controller) {
        this.controller = controller;
    }

    // ฟังก์ชันสำหรับเริ่มเกมใหม่
    private void resetGame() {
        textArea.setText(""); // ลบข้อมูลใน TextArea
        if (controller != null) {
            controller.playGame(); // เริ่มเกมใหม่
        }
    }
}