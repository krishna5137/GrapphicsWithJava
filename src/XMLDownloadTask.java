/************************************************************
 *  CSCI 470/680      ASSIGNMENT3     SPRING 2018           *
 *  TEAM:    NAGA SATISH KRISHNA REDDY NALLAMILLI (Z1806140)*
 *  		 SAI SEETA RAM BOPPANA (Z1804843)              *
 *  DUE DATE:4/4/2018                                      *
 *  PURPOSE:    This program is to write a Java application
 *  to download XML, parse it, and display the results.    *
 ***********************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;




public class XMLDownloadTask extends SwingWorker{

    ArrayList<Album> albums = null;
    private String placeHolder1;
    private String placeHolder2;
    private String placeHolder3;


    @Override
    protected Object doInBackground() throws Exception {

        URL obj = new URL("https://rss.itunes.apple.com/api/v1/us/"
                + "apple-music/"+placeHolder1+"/all/"+placeHolder2+"/"+placeHolder3+".atom");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();

        StringBuilder response = new StringBuilder();
        if(responseCode == 200){
                try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse(new InputSource(new StringReader(response.toString())), new AlbumHandler());
        }else{
            XMLDownloadPanel.jTextArea.setText("Error Occured: "+responseCode);
        }

        return null;
    }

    XMLDownloadTask(String MN1value, String MN2value, String MN3value){
        this.placeHolder1 = MN1value;
        this.placeHolder2 = MN2value;
        this.placeHolder3 = MN3value;
    }

    public String getPlaceHolder1() {
        return placeHolder1;
    }

    public void setPlaceHolder1(String placeHolder1) {
        this.placeHolder1 = placeHolder1;
    }

    public String getPlaceHolder2() {
        return placeHolder2;
    }

    public void setPlaceHolder2(String placeHolder2) {
        this.placeHolder2 = placeHolder2;
    }

    public String getPlaceHolder3() {
        return placeHolder3;
    }

    public void setPlaceHolder3(String placeHolder3) {
        this.placeHolder3 = placeHolder3;
    }

    public String process() throws IOException, SAXException, ParserConfigurationException, SAXException, Exception{
        doInBackground();

        StringBuilder output = new StringBuilder();

        if(albums.size() == 0){
            output.append("Sorry, No Albums found.");
        }else{
            for(Album a : this.albums){
                output.append(a.getAlbumName()+"; "+a.getArtistName()+"; "+a.getGenre()+ "\n");
            }
            albums = null;
        }

        return output.toString();
    }

    public class AlbumHandler extends DefaultHandler{

    Album currAlbum;

    boolean xName = false;
    boolean xArtist = false;

    String sName = null;
    String sArtist = null;


    @Override
    public void startElement(String namespaceURI,String localName, String qName,
                         Attributes atts)
    throws SAXException {

        if(qName.equalsIgnoreCase("entry")){
            currAlbum = new Album();

            if(albums == null){
                albums = new ArrayList<>();
            }
        }
        if (qName.equalsIgnoreCase("im:name")) {
            xName = true;
        } else if (qName.equalsIgnoreCase("im:artist")) {
            xArtist = true;
        } else if (qName.equalsIgnoreCase("category")) {
            currAlbum.setGenre(atts.getValue("label"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("entry")) {
            //add Employee object to list
             albums.add(currAlbum);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

            if (xName) {
                currAlbum.setAlbumName(new String(ch, start, length));
                xName = false;
            } else if (xArtist) {
                currAlbum.setArtistName(new String(ch, start, length));
                xArtist = false;
            }
        }
    }
}
