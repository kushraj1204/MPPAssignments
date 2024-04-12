package librarysystem.Panel;

/**
 * @author kush
 */

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Author extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtAuthorID;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtBio;
    private JTextField txtTele;
    private JTextField txtStreet;
    private JTextField txtState;
    private JTextField txtZip;
    private JTextField txtCity;

    /**
     * Create the panel.
     */
    public Author() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Author ID");
        lblNewLabel.setBounds(37, 45, 48, 14);
        add(lblNewLabel);

        txtAuthorID = new JTextField();
        txtAuthorID.setBounds(178, 45, 249, 27);
        add(txtAuthorID);
        txtAuthorID.setColumns(10);

        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(37, 88, 78, 14);
        add(lblFirstName);

        txtFirstName = new JTextField();
        txtFirstName.setColumns(10);
        txtFirstName.setBounds(178, 88, 249, 27);
        add(txtFirstName);

        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(37, 126, 78, 14);
        add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setColumns(10);
        txtLastName.setBounds(178, 126, 249, 27);
        add(txtLastName);

        JLabel lblBio = new JLabel("Bio");
        lblBio.setBounds(37, 163, 78, 14);
        add(lblBio);

        txtBio = new JTextField();
        txtBio.setColumns(10);
        txtBio.setBounds(178, 163, 249, 27);
        add(txtBio);

        JLabel lblTelephoone = new JLabel("Telephoone");
        lblTelephoone.setBounds(37, 201, 78, 14);
        add(lblTelephoone);

        txtTele = new JTextField();
        txtTele.setColumns(10);
        txtTele.setBounds(178, 201, 249, 27);
        add(txtTele);

        JLabel lblStreet = new JLabel("Street");
        lblStreet.setBounds(37, 245, 78, 14);
        add(lblStreet);

        txtStreet = new JTextField();
        txtStreet.setColumns(10);
        txtStreet.setBounds(178, 239, 249, 27);
        add(txtStreet);

        JLabel lblState = new JLabel("State");
        lblState.setBounds(37, 325, 78, 14);
        add(lblState);

        txtState = new JTextField();
        txtState.setColumns(10);
        txtState.setBounds(178, 319, 249, 27);
        add(txtState);

        JLabel lblZip = new JLabel("Zip");
        lblZip.setBounds(37, 369, 78, 14);
        add(lblZip);

        txtZip = new JTextField();
        txtZip.setColumns(10);
        txtZip.setBounds(178, 363, 249, 27);
        add(txtZip);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(37, 287, 78, 14);
        add(lblCity);

        txtCity = new JTextField();
        txtCity.setColumns(10);
        txtCity.setBounds(178, 277, 249, 27);
        add(txtCity);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSave.setBounds(338, 404, 89, 23);
        add(btnSave);

    }

}