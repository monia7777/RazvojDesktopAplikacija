package plantie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgZahtjevi extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    JTextArea textAreaKosarica;

    public static void main(String[] args) {
        try {
        	DlgZahtjevi dialog = new DlgZahtjevi();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgZahtjevi() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 11, 414, 151);
            contentPanel.add(scrollPane);
            {
                textAreaKosarica = new JTextArea();
                scrollPane.setViewportView(textAreaKosarica);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		dispose();
                	}
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
        ZahtjeviZaAdmina();
    }

    private void ZahtjeviZaAdmina() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/dmudric?user=dmudric&password=11");
            String sql = "SELECT * FROM ZahtjeviZaAdmina";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tekst = "";
            while (rs.next()) {
                tekst += "Zahtjev: " + rs.getString("Zahtjev") + "\n";    
            }   
            textAreaKosarica.setText(tekst);
            conn.close();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

}

