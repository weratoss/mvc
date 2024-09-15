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
    public void displayRound(Cow cow, int pinsKnockedDown, String typeScore) {
        textArea.append("Name :  " + cow.getName() + ", Color : " + cow.getColor() + ", Team : " + cow.getTeam() +
                ", down : " + pinsKnockedDown + " pins, typeScore : " + typeScore + "\n");
    }

    // ฟังก์ชันสำหรับแสดงผลคะแนนสุดท้ายของวัวแต่ละตัว
    public void displayFinalResults(Cow[] cows) {
        textArea.append("\nFinal Results:\n");
        for (Cow cow : cows) {
            textArea.append(cow.getName() + " from " + cow.getTeam() + " scored " + cow.getScore() + " points.\n");
        }
    }

    // ฟังก์ชันสำหรับตั้งค่า Controller
    public void setController(BowlingController controller) {
        this.controller = controller;
    }

    // ฟังก์ชันสำหรับเริ่มเกมใหม่
    private void resetGame() {
        textArea.setText(""); // ลบข้อมูลใน TextArea
        for (Cow cow : cows) {
            cow.addScore(-cow.getScore()); // รีเซ็ตคะแนนของวัวแต่ละตัว
        }
        if (controller != null) {
            controller.playGame(); // เริ่มเกมใหม่
        }
    }
}