/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bellminder;


import com.fasterxml.jackson.databind.JsonNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Qoutes extends javax.swing.JFrame {

    /**
     * Creates new form Qoutes
     */
    public Qoutes() {
        setUndecorated(true);
        initComponents();
        setOpacity(0.9f);
        setExtendedState(MAXIMIZED_BOTH);
       
        Boolean onlineQouteStatus;
        try {
            onlineQouteStatus = onlineQouteGenerator();
            if(onlineQouteStatus==false){
                localQouteGenerator();
            }
        } catch (IOException ex) {
            //Logger.getLogger(Qoutes.class.getName()).log(Level.SEVERE, null, ex);
            localQouteGenerator();
        } 
        
       //Close after 15 seconds
        new Timer().schedule(new TimerTask() {
            public void run() {
                  dispose();
            }
        }, 15000);
   
    }
    
    public void localQouteGenerator(){
        JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader("quotes.json")) {               
                Object obj = jsonParser.parse(reader);

                JSONArray qouteList = (JSONArray)obj;
                int min = 0;
                int max = qouteList.size(); //Total siz of the qoutes
                int randomNum = (int)(Math.random() * (max - min)) + min; // Generate random number

                JSONObject qouteInfo = (JSONObject) qouteList.get(randomNum);

                String qoute = (String) qouteInfo.get("quoteText"); 
                String author = (String) qouteInfo.get("quoteAuthor");

                if(author == "" || author==null || author.isEmpty()){
                    author ="Unknown";
                } 

                String word = String.format("<html>"+qoute+"<br/><div style=\"text-align: center;  text-justify: inter-word; font-size: 14px;\">~"+author+"</div></html>");
                qoutelbl.setText(String.format("<html><body style=\"text-align: center;  text-justify: inter-word;\">%s</body></html>",word));

           } catch (IOException e) {
               e.printStackTrace();
           } catch (ParseException e) {
               e.printStackTrace();
           }
    }
    
    public Boolean onlineQouteGenerator() throws IOException{
        Boolean status = false;
        try {   
            //String api1="https://zenquotes.io/api/quotes/"; 
            String api2="https://stoic.tekloon.net/stoic-q1uote"; 
            
            URL url = new URL(api2);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);
            if(connection.getResponseCode()==200){
               
                JsonNode dataNode = root.get("data");
                if (dataNode != null) { // Ensure the "data" field exists
                    status = true;
                    JsonNode quoteNode = dataNode.get("quote");
                    JsonNode authorNode = dataNode.get("author");
                    if (quoteNode != null && authorNode != null) { // Ensure the "quote" field exists
                        status = true;
                        //System.out.println("qoute returns not null");
                        String quote = quoteNode.asText(); // Convert the quote to String
                        String author = authorNode.asText(); // Convert the author to String
                        String word = String.format("<html>"+String.valueOf(quote)+"<br/><div style=\"text-align: center;  text-justify: inter-word; font-size: 14px;\">~"+String.valueOf(author)+"</div></html>");
                        qoutelbl.setText(String.format("<html><body style=\"text-align: center;  text-justify: inter-word;\">%s</body></html>",word));
                    } else {
                         //System.out.println("qoute returns null");
                         status = false;
                    }
                } else {
                        //System.out.println("qoute returns null");
                        status = false;
                }
            }  
        } catch (MalformedURLException ex) {
            Logger.getLogger(Qoutes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        qoutelbl = new javax.swing.JLabel();
        closelbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setExtendedState(6);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        qoutelbl.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        qoutelbl.setForeground(new java.awt.Color(255, 255, 255));
        qoutelbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        qoutelbl.setText("Qoutes");
        qoutelbl.setFocusTraversalPolicyProvider(true);
        qoutelbl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        closelbl.setForeground(new java.awt.Color(255, 255, 255));
        closelbl.setText("Close");
        closelbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closelblMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qoutelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closelbl)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(closelbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qoutelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closelblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closelblMouseClicked
        dispose();
    }//GEN-LAST:event_closelblMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Qoutes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Qoutes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Qoutes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Qoutes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Qoutes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel closelbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel qoutelbl;
    // End of variables declaration//GEN-END:variables
}
