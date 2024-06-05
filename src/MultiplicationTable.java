package src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* สร้าง class ตารางแม่สูตรคูณ
* ใช้ interface MouseListener และ WindowListener เพื่อมา implement ให้แต่ละ method เพื่อจัดการกับ event ในโปรแกรม
* ใช้ interface SwingConstants เพื่อจะใช้ attribute ที่เป็นค่าคงทีสำหรับจัดการตำแหน่งของข้อความ */
public final class MultiplicationTable implements WindowListener, MouseListener, SwingConstants {
    // ประกาศ attributes ของ components ทั้งหมด
    private final JFrame frame;
    private final Container content;
    private JButton btn;
    private JLabel label;
    private JLabel title;
    private JPanel topPanel;
    private JPanel mainPanel;
    private JTextField field;

    // constructor เมื่อสร้าง object ให้ทำการสร้างหน้าต่างโปรแกรมและวาง components ทันที
    public MultiplicationTable(){
        // กำหนดค่าเริ่มต้นให้ attributes เป็น object ของแต่ละ component
        this.frame = new JFrame("Multiplication Table");
        this.content = this.frame.getContentPane();
        this.btn = new JButton("Click here");
        this.topPanel = new JPanel();
        this.mainPanel = new JPanel();
        this.field = new JTextField();
        this.label = new JLabel("Enter number: ");
        this.title = new JLabel();

        // กำหนดขนาดหน้าต่าง, ตำแหน่งการวางโปรแกรมบนหน้าจอ และใช้ layout เป็นแบบ border
        this.frame.setLayout(new BorderLayout());
        this.frame.setVisible(true);
        this.frame.setSize(300, 430);
        this.frame.setLocation(530, 100);
        // ส่ง this keyword เป็น argument หมายถึงให้ใช้ class นี้เป็น listener เมื่อมี event เกิดขึ้นในโปรแกรมจะเรียกใช้ method ที่ implement มาจาก interface
        this.frame.addWindowListener(this);
        this.btn.addMouseListener(this);

        // ให้ panels ใช้ layout เป็นแบบ grid และกำหนด แถว และ คอลัมน์
        this.topPanel.setLayout(new GridLayout(3,1));
        this.mainPanel.setLayout(new GridLayout(13, 1));
        // ให้ panel เพิ่ม components และวาง panel ในส่วนตำแหน่งด้านบ้านโปรแกรม
        this.topPanel.add(this.label);
        this.topPanel.add(this.field);
        this.topPanel.add(this.btn);
        this.content.add(topPanel, BorderLayout.NORTH);

        // ให้ textField มี cursor กระพิบที่ช้องป้อนข้อความตอนเริ่มโปรแกรม
        this.field.grabFocus();
    }

    private void generateNumbers(int n){
        // ลบ components เก่าทั้งหมดที่อยู่ใน main panel ออกก่อนแล้วค่อยวาง components ใหม่เข้าไป
        if(this.mainPanel.countComponents() != 0){
            this.mainPanel.removeAll();
        }
        this.mainPanel.setVisible(false);
        this.field.setText("");
        // ใส่ข้อความ , จัดตำแหน่ง , เปลี่ยนสี และ เพิ่ม component เข้าไปใน main panel
        this.title.setText("The Multiplication Table of: " + n);
        this.title.setForeground(Color.BLUE);
        this.title.setHorizontalAlignment(this.CENTER);
        this.mainPanel.add(this.title);
        String text = null;
        // วน loop เพิ่ม components และนำ main panel ไปใส่ใน content pane อีกที
        for(int i = 1; i <= 12; i++){
            text = n + " x " + i + " = " + n * i;
            JLabel label = new JLabel();
            label.setHorizontalAlignment(this.CENTER);
            label.setText(text);
            this.mainPanel.add(label);
        }
    }

    // implement methods ทั้งหมด
    public void mouseClicked(MouseEvent e) {
        try {
            // ถ้าช้องป้อนข้อความไม่ได้ใส่ข้อความมา
            if(this.field.getText().isBlank()){
                this.showErrorMessage("Invalid number, Please enter a number again.");
            } else {
                // แปลงข้อความที่ได้เป็นเลขจำนวนเต็มและส่ง argument ให้ method ไปทำงาน
                this.generateNumbers(Integer.parseInt(this.field.getText()));
                // เอา main panel มาใส่ใน content pane และจัดตำแหน่งให้วางตรงกลางของเนื้อหา
                this.content.add(this.mainPanel, this.CENTER);
                this.mainPanel.setVisible(true);
            }
        } catch(NumberFormatException exception){
            // ใช้ class NumberFormatException เมื่อเกิดข้อผิดพลาดที่แปลงจากข้อความไปเป็นเลขจำนวนเต็มไม่สำเร็จให้แสดง dialog ที่มีข้อความ error ขึ้นมา
            this.showErrorMessage("Error: " + exception.getMessage());
        } finally {
            this.field.grabFocus();
        }
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void windowOpened(WindowEvent e){}
    public void windowClosing(WindowEvent e){
        System.exit(0);
    }
    public void windowClosed(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowActivated(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}

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