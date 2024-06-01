package program.src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MultiplicationTable implements MouseListener, SwingConstants {
    private JFrame frame;
    private Container content;
    private JButton btn;
    private JLabel label;
    private JLabel label2;
    private JPanel panel;
    private JPanel panel2;
    private JTextField field;

    public MultiplicationTable(){
        this.frame = new JFrame("Multiplication Table");
        this.content = this.frame.getContentPane();
        this.btn = new JButton("Click here");
        this.panel = new JPanel();
        this.panel2 = new JPanel();
        this.field = new JTextField();
        this.label = new JLabel("Enter number: ");
        this.label2 = new JLabel();

        this.frame.setLayout(new BorderLayout());
        this.frame.setVisible(true);
        this.frame.setSize(300, 430);
        this.frame.setLocation(530, 100);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.panel.setLayout(new GridLayout(3,1));
        this.panel2.setLayout(new GridLayout(13, 1));
        this.panel.add(this.label);
        this.panel.add(this.field);
        this.panel.add(this.btn);
        this.content.add(panel, BorderLayout.NORTH);

        this.field.grabFocus();
        this.btn.addMouseListener(this);
    }

    public void generate(int n){
        this.panel2.removeAll();
        this.panel2.setVisible(false);
        this.field.setText("");
        this.label2.setText("The Multiplication Table of: " + n);
        this.label2.setForeground(Color.BLUE);
        this.label2.setHorizontalAlignment(this.CENTER);
        this.panel2.add(this.label2);
        for(int i = 1; i <= 12; i++){
            JLabel label = new JLabel();
            label.setHorizontalAlignment(this.CENTER);
            label.setText(n + " x " + i + "= " + n * i);
            this.panel2.add(label);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if(this.field.getText().isBlank()){
                this.showErrorMessage("Invalid number! Please try again.");
            } else {
                this.generate(Integer.parseInt(this.field.getText()));
                this.content.add(this.panel2, this.CENTER);
                this.panel2.setVisible(true);
            }
        } catch(Exception error){
            this.showErrorMessage("Error: " + error.getMessage());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

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
        MultiplicationTable multiplicationTable = new MultiplicationTable();
    }
}
