/************************************************************
 *  CSCI 470/680      ASSIGNMENT3     SPRING 2018           *
 *  TEAM:    NAGA SATISH KRISHNA REDDY NALLAMILLI (Z1806140)*
 *  		 SAI SEETA RAM BOPPANA (Z1804843)              *
 *  DUE DATE:4/4/2018                                      *
 *  PURPOSE:    This program is to write a Java application
 *  to download XML, parse it, and display the results.    *
 ***********************************************************/



import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

public class XMLDownloadPanel extends JPanel {


    public static JTextArea jTextArea;

    private JButton getAlbums;
    private JScrollPane scrollPane;
    private JTextArea jTextArea1;
    public boolean allowExplictAlbums = false;


    public XMLDownloadPanel() {
        getAlbums = new JButton();
        scrollPane = new JScrollPane();
        jTextArea = new JTextArea();
        jTextArea.setText("");
        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        scrollPane.setViewportView(jTextArea);

        getAlbums.setText("Get Albums");
        getAlbums.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getAlbumsActionPerformed(evt);
            }
        });

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        scrollPane.setViewportView(jTextArea);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(getAlbums)
                .addContainerGap(177, Short.MAX_VALUE))
            .addComponent(scrollPane, GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(getAlbums)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
        );
    }


    public void download(){
        try {
            this.jTextArea.setText(XMLDownloader.xmlDownloadTask.process());
        } catch (SAXException ex) {
            Logger.getLogger(XMLDownloadPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLDownloadPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(XMLDownloadPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    private void getAlbumsActionPerformed(java.awt.event.ActionEvent evt) {
        this.jTextArea.setText("Please wait...");
        download();
    }
}
