
import javax.swing.*;

public class MembershipManagementApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MembershipManagementGUI();
            frame.setTitle("Membership Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 500);
            frame.setLocationRelativeTo(null); // Center the frame on screen
            frame.setVisible(true);
        });
    }
}
