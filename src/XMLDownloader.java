/************************************************************
 *  CSCI 470/680      ASSIGNMENT3     SPRING 2018           *
 *  TEAM:    NAGA SATISH KRISHNA REDDY NALLAMILLI (Z1806140)*
 *  		 SAI SEETA RAM BOPPANA (Z1804843)              *
 *  DUE DATE:4/4/2018                                      *
 *  PURPOSE:    This program is to write a Java application
 *  to download XML, parse it, and display the results.    *
 ***********************************************************/


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JFrame;



public class XMLDownloader extends JFrame {



    public static XMLDownloadTask xmlDownloadTask;
    XMLDownloadPanel xmlDownloadPanel;
    private ButtonGroup newMusic;
    private ButtonGroup recentReleases;
    private ButtonGroup topAlbums;
    private JMenu musicMenu;
    private JMenu releaseMenu;
    private JMenu albumsMenu;
    private JMenuBar appMenu;
    private JRadioButtonMenuItem jRadioButtonMenuItem1;
    private JRadioButtonMenuItem newMusicItem;
    private JRadioButtonMenuItem releasesItem;
    private JRadioButtonMenuItem albumsItem;
    private JRadioButtonMenuItem limit10;
    private JRadioButtonMenuItem limit25;
    private JRadioButtonMenuItem limit50;
    private JRadioButtonMenuItem explictYes;
    private JRadioButtonMenuItem explictNo;


    private JRadioButtonMenuItem limit100;

    public XMLDownloader() throws SAXException, ParserConfigurationException, org.xml.sax.SAXException, Exception {
        createAndShowGUI();
    }

    public void createMenu(){

        newMusic = new ButtonGroup();
        recentReleases = new ButtonGroup();
        topAlbums = new ButtonGroup();
        appMenu = new JMenuBar();
        musicMenu = new JMenu();
        newMusicItem = new JRadioButtonMenuItem();
        releasesItem = new JRadioButtonMenuItem();
        albumsItem = new JRadioButtonMenuItem();
        releaseMenu = new JMenu();
        limit10 = new JRadioButtonMenuItem();
        limit25 = new JRadioButtonMenuItem();
        limit50 = new JRadioButtonMenuItem();
        albumsMenu = new JMenu();
        explictYes = new JRadioButtonMenuItem();
        explictNo = new JRadioButtonMenuItem();
        limit100 = new JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout());

        musicMenu.setText("Type");

        newMusicItem.setSelected(true);
        newMusicItem.setText("New Music");
        newMusicItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                newMusicItemActionPerformed(evt);
            }
        });

        newMusic.add(newMusicItem);
        musicMenu.add(newMusicItem);

        releasesItem.setText("Recent Releases");
        releasesItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                releasesItemActionPerformed(evt);
            }
        });

        newMusic.add(releasesItem);
        musicMenu.add(releasesItem);

        albumsItem.setText("Top Albums");
        albumsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                albumsItemActionPerformed(evt);
            }
        });

        newMusic.add(albumsItem);
        musicMenu.add(albumsItem);

        appMenu.add(musicMenu);

        releaseMenu.setText("Limit");

        limit10.setSelected(true);
        limit10.setText("10");
        recentReleases.add(limit10);
        limit10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    limit10ActionPerformed(evt);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (org.xml.sax.SAXException ex) {
                    Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        releaseMenu.add(limit10);

        limit25.setText("25");
        recentReleases.add(limit25);
        limit25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                limit25ActionPerformed(evt);
            }
        });
        releaseMenu.add(limit25);

        limit50.setText("50");
        recentReleases.add(limit50);
        limit50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                limit50ActionPerformed(evt);
            }
        });
        releaseMenu.add(limit50);

        limit100.setText("100");
        recentReleases.add(limit100);
        limit100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                limit100ActionPerformed(evt);
            }

            private void limit100ActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        releaseMenu.add(limit50);

        appMenu.add(releaseMenu);

        albumsMenu.setText("Explict");

        explictYes.setSelected(true);
        explictYes.setText("Yes");
        topAlbums.add(explictYes);
        explictYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    explictYesActionPerformed(evt);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (org.xml.sax.SAXException ex) {
                    Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        albumsMenu.add(explictYes);

        explictNo.setText("No");
        topAlbums.add(explictNo);
        explictNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                explictNoActionPerformed(evt);
            }
        });
        albumsMenu.add(explictNo);

        appMenu.add(albumsMenu);
    }

    private void createAndShowGUI() throws IOException, SAXException,
            ParserConfigurationException, org.xml.sax.SAXException, Exception{

        createMenu();
        this.xmlDownloadPanel = new XMLDownloadPanel();
            this.xmlDownloadTask = new XMLDownloadTask("top-songs", "10", "explicit");

        this.xmlDownloadTask.process();

        this.add(xmlDownloadPanel);

        setJMenuBar(appMenu);

        pack();
    }



    private void newMusicItemActionPerformed(ActionEvent evt) {
        this.xmlDownloadTask.setPlaceHolder1("top-songs");
        try {
            XMLDownloadPanel.jTextArea.setText("");
            XMLDownloadPanel.jTextArea.setText(this.xmlDownloadTask.process());
        } catch (SAXException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void releasesItemActionPerformed(ActionEvent evt) {

        this.xmlDownloadTask.setPlaceHolder1("new-releases");
        try {
            XMLDownloadPanel.jTextArea.setText("");
            XMLDownloadPanel.jTextArea.setText(this.xmlDownloadTask.process());
        } catch (SAXException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void albumsItemActionPerformed(ActionEvent evt) {

        this.xmlDownloadTask.setPlaceHolder1("top-albums");
        try {
            XMLDownloadPanel.jTextArea.setText("");
            XMLDownloadPanel.jTextArea.setText(this.xmlDownloadTask.process());
        } catch (SAXException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limit10ActionPerformed(ActionEvent evt)
            throws SAXException, ParserConfigurationException, org.xml.sax.SAXException, Exception {

        this.xmlDownloadTask.setPlaceHolder2("10");
        XMLDownloadPanel.jTextArea.setText("");
        XMLDownloadPanel.jTextArea.setText(this.xmlDownloadTask.process());
    }

    private void limit25ActionPerformed(ActionEvent evt) {

        this.xmlDownloadTask.setPlaceHolder2("25");
        try {
            XMLDownloadPanel.jTextArea.setText("");
            XMLDownloadPanel.jTextArea.setText(this.xmlDownloadTask.process());
        } catch (SAXException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limit50ActionPerformed(ActionEvent evt) {

        this.xmlDownloadTask.setPlaceHolder2("50");
        try {
            XMLDownloadPanel.jTextArea.setText("");
            XMLDownloadPanel.jTextArea.setText(this.xmlDownloadTask.process());
        } catch (SAXException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void limit100ActionPerformed(ActionEvent evt)
            throws SAXException, ParserConfigurationException, org.xml.sax.SAXException, Exception {

        this.xmlDownloadTask.setPlaceHolder2("100");
        XMLDownloadPanel.jTextArea.setText("");
        XMLDownloadPanel.jTextArea.setText(this.xmlDownloadTask.process());
    }

    private void explictYesActionPerformed(ActionEvent evt)
            throws SAXException, ParserConfigurationException, org.xml.sax.SAXException, Exception {

        this.xmlDownloadTask.setPlaceHolder3("explicit");
        XMLDownloadPanel.jTextArea.setText("");
        XMLDownloadPanel.jTextArea.setText(this.xmlDownloadTask.process());
    }


    private void explictNoActionPerformed(ActionEvent evt) {

        this.xmlDownloadTask.setPlaceHolder3("non-explicit");
        try {
            this.xmlDownloadTask.process();
        } catch (SAXException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(XMLDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception{

        new XMLDownloader().setVisible(true);
    }
}
