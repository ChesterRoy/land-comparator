package com.roy.landcomparator.gui;

import com.roy.landcomparator.beans.Land;
import com.roy.landcomparator.compare.LandComparator;
import com.roy.landcomparator.datasource.DataBaseManager;
import com.roy.landcomparator.xlsx.ExcelFileManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Comparator extends JFrame {

    public Comparator() {
        initComponents();

    }

    private void initComponents() {

        fileChooser = new JFileChooser();
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();

        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jScrollPane2 = new JScrollPane();
        jTable2 = new JTable();
        jLabel2 = new JLabel();

        jButton2 = new JButton();
        jButton3 = new JButton();

        jPanel2 = new JPanel();
        jTextField1 = new JTextField("oracle.jdbc.driver.OracleDriver");
        jTextField2 = new JTextField("jdbc:oracle:thin:@10.10.10.41:1521:armehz");
        jTextField3 = new JTextField("gmt_etld");
        jTextField4 = new JTextField("gmt_etld");
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();

        jButton1 = new JButton();
        jLabel7 = new JLabel();
        jButton4 = new JButton();
        jProgressBar1 = new JProgressBar();
        jProgressBar1.setStringPainted(true);
        jButton5 = new JButton();
        jLabel8 = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Lands from file");

        columnsNames = new Vector<>();
        columnsNames.add("CIDNUM");
        columnsNames.add("CPIPENAME");
        columnsNames.add("NKMSTART");
        columnsNames.add("NKMEND");
        columnsNames.add("NKRPLANYEAR");

        jTable1.setModel(new MyTableModel(listFromFile, columnsNames));

        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new MyTableModel(listFromDB, columnsNames));

        jScrollPane2.setViewportView(jTable2);

        jLabel2.setText("Lands from table");

        jButton2.setText("Parse file");

        jButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parseFile(e);
            }
        });

        jButton3.setText("Parse table");

        jButton3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parseTable(e);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel1)
                                        .addGap(426, 426, 426))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jButton2)))
                                        .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel2)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addGap(368, 368, 368))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton3))
                        .addGap(27, 27, 27))
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel3.setText("Driver:");

        jLabel4.setText("URL:");

        jLabel5.setText("User:");

        jLabel6.setText("Password:");

        jButton1.setText("Test connection");

        jButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getConnection(e);
            }
        });

        jLabel7.setText("here you whill see testing connection result. ");

        jButton4.setText("Compare and update");

        jButton4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                compareAndUpdate(e);
            }
        });

        jButton5.setText("Choose the file");

        jButton5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(e);
            }
        });

        jLabel8.setText("file name.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel6))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField1)
                                                .addComponent(jTextField2)
                                                .addComponent(jTextField3)
                                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
                                        .addGap(60, 60, 60)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(7, 7, 7)
                                                        .addComponent(jLabel6))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jButton1)
                                                .addComponent(jLabel7)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jButton5)
                                                .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton4)
                                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold> 

    private void getConnection(ActionEvent evt) {
        mapFromDB = null;
        dataBaseManager = null;
        dataBaseManager = new DataBaseManager(jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), jTextField4.getText());
        Connection connection = dataBaseManager.getConnection();
        if (connection != null) {
            String[] temp = dataBaseManager.getDB_URL().split("@");
            jLabel7.setText("Connection to " + temp[1] + " " + dataBaseManager.getUSER() + " is open!");
            listFromDB = null;
            refresh2Table();
        } else {
        }

    }

    private void chooseFile(ActionEvent evt) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                fileName = null;
                int returnVal = fileChooser.showOpenDialog(Comparator.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    fileName = file.getAbsolutePath();
                    jLabel8.setText(file.getName());
                    listFromFile = null;
                    refresh1Table();
                }
            }
        });
    }

    private void parseFile(ActionEvent evt) {
        try {
            listFromFile = null;
            refresh2Table();
            mapFromFile = ExcelFileManager.readExcelData();
            int count = 0;
            listFromFile = new Vector<Vector>();
            listFromFile.add(new Vector<Object>(5));
            for (Double key : mapFromFile.keySet()) {
                listFromFile.add(new Vector<Object>(5));
                listFromFile.get(count).add(key);
                listFromFile.get(count).add(mapFromFile.get(key).getCPIPENAME());
                listFromFile.get(count).add(mapFromFile.get(key).getNKMSTART());
                listFromFile.get(count).add(mapFromFile.get(key).getNKMEND());
                listFromFile.get(count).add(mapFromFile.get(key).getNKRPLANYEAR());
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        refresh1Table();
    }

    private void parseTable(ActionEvent evt) {

        try {
            mapFromDB = dataBaseManager.getAllLandsFromDB();
            int count = 0;
            listFromDB = new Vector<Vector>();
            listFromDB.add(new Vector<Object>(5));
            for (Double key : mapFromDB.keySet()) {
                listFromDB.add(new Vector<Object>(5));
                listFromDB.get(count).add(key);
                listFromDB.get(count).add(mapFromDB.get(key).getCPIPENAME());
                listFromDB.get(count).add(mapFromDB.get(key).getNKMSTART());
                listFromDB.get(count).add(mapFromDB.get(key).getNKMEND());
                listFromDB.get(count).add(mapFromDB.get(key).getNKRPLANYEAR());
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refresh2Table();
    }

    private void compareAndUpdate(ActionEvent evt) {

        LandComparator landComparator = new LandComparator(dataBaseManager);

        notAvalibleLands = new ArrayList<>();
        updatedLands = new ArrayList<>();

        try {
            Map<String, List<Double>> list = landComparator.compareLands(mapFromFile, mapFromDB);
            for (String key : list.keySet()) {
                if (key.equals("notAvalible")) {
                    notAvalibleLands = list.get(key);
                }
                if (key.equals("updated")) {
                    updatedLands = list.get(key);
                }
            }
            refreshColoredTable1();
            refreshColoredTable2();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh1Table() {
        MyTableModel tableModel1 = new MyTableModel(listFromFile, columnsNames);
        jTable1.setModel(tableModel1);
    }

    public void refresh2Table() {
        MyTableModel tableModel2 = new MyTableModel(listFromDB, columnsNames);
        jTable2.setModel(tableModel2);
    }

    public void refreshColoredTable1() {
  
        ColorRenderer columnRenderer = new ColorRenderer("CIDNUM", notAvalibleLands);
        MyTableModel tableModel1 = new MyTableModel(listFromFile, columnsNames);
        jTable1.setModel(tableModel1);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumn(jTable1.getColumnName(i)).setCellRenderer(columnRenderer);
        }
    }

    public void refreshColoredTable2() {
        ColorRenderer columnRenderer = new ColorRenderer("CIDNUM", updatedLands);
        MyTableModel tableModel2 = new MyTableModel(listFromDB, columnsNames);
        jTable2.setModel(tableModel2);
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumn(jTable2.getColumnName(i)).setCellRenderer(columnRenderer);
        }
    }

    private void jTextField3ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextField2ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextField4ActionPerformed(ActionEvent evt) {

    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Comparator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comparator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comparator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comparator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comparator().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    
    
    public static javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    JFileChooser fileChooser;
    public static String fileName;
    
    

    Map<Double, Land> mapFromFile;
    Map<Double, Land> mapFromDB;
    Vector<Vector> listFromFile;
    Vector<Vector> listFromDB;
    Vector<String> columnsNames;
    List<Double> notAvalibleLands;
    List<Double> updatedLands;

    DataBaseManager dataBaseManager;

}
