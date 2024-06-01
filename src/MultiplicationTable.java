package program.src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* สร้าง class ตารางแม่สูตรคูณ
* ใช้ interface MouseListener เพื่อมา implement ให้ method เพื่อจัดการกับ event การคลิกของปุ่ม
* ใช้ interface SwingConstants เพื่อจะใช้ attribute ที่เป็นค่าคงทีสำหรับจัดการตำแหน่งของข้อความ */
public class MultiplicationTable implements MouseListener, SwingConstants {
    // ประกาศ attributes ของ components
    private final JFrame frame;
    private final Container content;
    private JButton btn;
    private JLabel label;
    private JLabel label2;
    private JPanel panel;
    private JPanel panel2;
    private JTextField field;

    // constructor เมื่อสร้าง object ให้ทำการสร้างหน้าต่างโปรแกรมและวาง component ทันที
    public MultiplicationTable(){
        // กำหนดค่าเริ่มต้นให้ attributes เป็น object ของแต่ละ component
        this.frame = new JFrame("Multiplication Table");
        this.content = this.frame.getContentPane();
        this.btn = new JButton("Click here");
        this.panel = new JPanel();
        this.panel2 = new JPanel();
        this.field = new JTextField();
        this.label = new JLabel("Enter number: ");
        this.label2 = new JLabel();

        // กำหนดขนาดหน้าต่าง, ตำแหน่งการวางโปรแกรมบนหน้าจอ และ ใช้ layout เป็นแบบ border
        this.frame.setLayout(new BorderLayout());
        this.frame.setVisible(true);
        this.frame.setSize(300, 430);
        this.frame.setLocation(530, 100);
        // สร้าง object ด้านใน arguemnt เมื่อมีการคลิกที่ปุ่มปิดโปรแกรมๆจะจบการทำงาน
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // ให้ panels ใช้ layout เป็นแบบ grid และกำหนด แถว และ คอลัมน์
        this.panel.setLayout(new GridLayout(3,1));
        this.panel2.setLayout(new GridLayout(13, 1));
        // ให้ panel เพิ่ม components และวาง panel ในส่วนตำแหน่งด้านบ้านโปรแกรม
        this.panel.add(this.label);
        this.panel.add(this.field);
        this.panel.add(this.btn);
        this.content.add(panel, BorderLayout.NORTH);

        // ให้ textField มี cursor กระพิบที่ช้องป้อนข้อความ
        this.field.grabFocus();
        // เมื่อมี event กับการคลิกปุ่มให้เรียกใช้ method ที่ทำการ implement มาคือ interface MouseListener
        this.btn.addMouseListener(this);
    }

    public void generateNumbers(int n){
        // ลบ components ทั้งหมดที่อยู่ใน panel2 ออกก่อนแล้วค่อยวาง components ใหม่เข้าไป
        this.panel2.removeAll();
        this.panel2.setVisible(false);
        this.field.setText("");
        // ใส่ข้อความ , จัดตำแหน่ง , เปลี่ยนสี และ เพิ่ม component เข้าไปใน panel2
        this.label2.setText("The Multiplication Table of: " + n);
        this.label2.setForeground(Color.BLUE);
        this.label2.setHorizontalAlignment(this.CENTER);
        this.panel2.add(this.label2);
        // วน loop เพิ่ม components และนำ panel2 ไปใส่ใน contentpane อีกที
        for(int i = 1; i <= 12; i++){
            JLabel label = new JLabel();
            label.setHorizontalAlignment(this.CENTER);
            label.setText(n + " x " + i + "= " + n * i);
            this.panel2.add(label);
        }
    }

    public void mouseClicked(MouseEvent e) {
        try {
            // ถ้าช้องป้อนข้อความไม่ได้ใส่ข้อความมา
            if(this.field.getText().isBlank()){
                this.showErrorMessage("Invalid number! Please enter a number again.");
            } else {
                // แปลงข้อความที่ได้เป็นเลขจำนวนเต็มและส่ง argument ให้ method ไปทำงาน
                this.generateNumbers(Integer.parseInt(this.field.getText()));
                // เอา panel2 มาใส่ใน contentpane และจัดตำแหน่งให้วางตรงกลางของเนื้อหา
                this.content.add(this.panel2, this.CENTER);
                this.panel2.setVisible(true);
            }
        } catch(Exception error){
            // ใช้ class Exception เมื่อเกิดข้อผิดพลาดที่แปลงจากข้อความไปเป็นเลขจำนวนเต็มไม่สำเร็จให้แสดง dialog ที่มีข้อความ error
            this.showErrorMessage("Error: " + error.getMessage());
        }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    // แสดงข้อความ error ที่เกิดขึ้น
    public void showErrorMessage(String text){
        JDialog dialog = new JDialog();
        JLabel label = new JLabel(text, this.CENTER);
        label.setForeground(Color.red);
        dialog.setTitle("Error");
        dialog.add(label);
        dialog.setSize(300, 80);
        dialog.setVisible(true);
        dialog.setLocation(530, 140);
        this.field.setText("");
    }

    public static  void main(String []args){
        // สร้าง object ของแม่สูตรคูณ
        MultiplicationTable multiplicationTable = new MultiplicationTable();
    }
}
