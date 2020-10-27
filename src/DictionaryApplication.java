/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ADMIN
 */
public class DictionaryApplication extends javax.swing.JFrame {

    /**
     * Creates new form DictionaryApplication
     */
    DefaultListModel<String> listModel;
    ArrayList<Word> Words = new ArrayList<>();
    Map<String, String> vnmeses = new HashMap<>();

    public DictionaryApplication() throws IOException {
        Words.sort(Comparator.comparing(t -> t.word_target));
        initComponents();
        listModel = new DefaultListModel<>();
        InitData();
    }

    private void InitData() throws IOException {
        insertFromFileExpand();
        for (Word word : Words) {
            listModel.addElement(word.word_target);
        }
        jList1.setModel(listModel);
    }

    private void searchFilter(String searchTerm) {
        DefaultListModel filteredItems = new DefaultListModel();
        Words.forEach((word) -> {
            String new_word = word.toString().toLowerCase();
            if (new_word.startsWith(searchTerm.toLowerCase())) {
                word.word_target = new_word;
                filteredItems.addElement(word.word_target);
                vnmeses.put(word.word_target, word.word_explain);
            }
        });
        listModel = filteredItems;
        jList1.setModel(listModel);
    }

    public void selectlist(String text) {
        int size = jList1.getModel().getSize();
        int index = binsearch(jList1, text, size);
        if (index != -1) {
            jList1.setSelectedIndex(index);
            jList1.ensureIndexIsVisible(index);
        } else {
            System.out.println("Not found");
        }
    }

    public int binsearch(JList list, String target, int n) {
        int i;
        int pos = -1;
        Object item = list.getModel().getElementAt(n / 2);
        String itemstr = (String) item;
        if (itemstr.length() <= target.length())
            itemstr = itemstr.concat("");
        if (target.compareTo(itemstr.substring(0, target.length())) <= 0) {
            for (i = 0; i <= n / 2; i++) {
                item = list.getModel().getElementAt(i);
                itemstr = (String) item;
                if (itemstr.length() >= target.length()) {
                    if (target.compareTo(itemstr.substring(0, target.length())) == 0) {
                        pos = i;
                        break;
                    }
                }
            }
        } else {
            for (i = n / 2 + 1; i < n; i++) {
                item = list.getModel().getElementAt(i);
                itemstr = (String) item;
                if (itemstr.length() >= target.length()) {
                    if (target.compareTo(itemstr.substring(0, target.length())) == 0) {
                        pos = i;
                        break;
                    }
                }
            }
        }
        return pos;
    }

    public void insertFromFileExpand() throws IOException {
        Scanner input = new Scanner(Paths.get("DictionariesExpand.txt"),
                "UTF-8");
        while (input.hasNextLine()) {
            StringBuilder s = new StringBuilder();
            int count = 0;
            while (input.hasNextLine() && count != 100) {
                String a = input.nextLine();
                if (a.equals("")) {
                    count++;
                }
                s.append(a).append("\n");
            }
            String[] onePart = s.toString().split("@");
            for (String value : onePart) {
                Word new_word = new Word();
                String[] Line = value.split("/");
                if (Line.length == 3) {
                    new_word.word_target = Line[0];
                    new_word.word_explain = "/" + Line[1] + "/" + Line[2];
                    Words.add(new_word);
                    vnmeses.put(new_word.word_target, new_word.word_explain);
                } else if (Line.length == 4) {
                    new_word.word_target = Line[0];
                    new_word.word_explain = "/" + Line[1] + "/" + Line[2] + "/n" + Line[3];
                    Words.add(new_word);
                    vnmeses.put(new_word.word_target, new_word.word_explain);
                }
            }
        }
        input.close();
    }

    public void outToFile() {
        try {
            FileWriter f = new FileWriter("DictionariesExpand.txt");
            for (Word word : Words) {
                f.write("@" + word.word_target);
                f.write("");
                f.write(word.word_explain);
                f.write("\n");
            }
            f.close();
        } catch (Exception e) {
            System.out.println("Loi ghi file: " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jFrame2 = new javax.swing.JFrame();
        JPanel jPanel4 = new JPanel();
        jTextField3 = new javax.swing.JTextField();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JScrollPane jScrollPane5 = new JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        JButton jButton3 = new JButton();
        jFrame1 = new javax.swing.JFrame();
        JPanel jPanel3 = new JPanel();
        JLabel jLabel6 = new JLabel();
        jTextField5 = new javax.swing.JTextField();
        JButton jButton6 = new JButton();
        jFrame3 = new javax.swing.JFrame();
        JPanel jPanel1 = new JPanel();
        JLabel jLabel5 = new JLabel();
        JScrollPane jScrollPane4 = new JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        JScrollPane jScrollPane3 = new JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        JButton jButton2 = new JButton();
        JLabel jLabel7 = new JLabel();
        JLabel jLabel8 = new JLabel();
        JPanel jPanel2 = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        jList1 = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();
        JLabel jLabel1 = new JLabel();
        JScrollPane jScrollPane2 = new JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        JLabel jLabel3 = new JLabel();
        // Variables declaration - do not modify
        JButton jButton1 = new JButton();
        JMenuBar jMenuBar1 = new JMenuBar();
        JMenu jMenu1 = new JMenu();
        JMenuItem jMenuItem1 = new JMenuItem();
        JMenuItem jMenuItem2 = new JMenuItem();
        JMenuItem jMenuItem3 = new JMenuItem();
        JPopupMenu.Separator jSeparator1 = new JPopupMenu.Separator();
        JMenuItem jMenuItem4 = new JMenuItem();

        jFrame2.setTitle("Add ");
        jFrame2.setResizable(false);

        jPanel4.setBackground(new java.awt.Color(51, 255, 255));

        jTextField3.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", Font.BOLD | Font.ITALIC, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("English");

        jLabel4.setFont(new java.awt.Font("Arial", Font.BOLD | Font.ITALIC, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("VIetnamese");

        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jTextArea4.setRows(5);
        jScrollPane5.setViewportView(jTextArea4);

        jButton3.setFont(new java.awt.Font("Arial", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        jButton3.setText("Add");
        jButton3.addActionListener(evt -> jButton3ActionPerformed());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(225, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(225, 225, 225))
                        .addComponent(jScrollPane5)
                        .addComponent(jTextField3)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(221, 221, 221))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
                jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
                jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jFrame2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jFrame1.setTitle("Fix");
        jFrame1.setBackground(new java.awt.Color(153, 255, 255));
        jFrame1.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));

        jLabel6.setFont(new java.awt.Font("Arial", Font.BOLD | Font.ITALIC, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("New Word");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField5.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N

        jButton6.setFont(new java.awt.Font("Arial Black", Font.ITALIC, 14)); // NOI18N
        jButton6.setText("Fix");
        jButton6.addActionListener(evt -> jButton6ActionPerformed());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jTextField5)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(149, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
                jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
                jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jFrame3.setTitle("Google API");
        jFrame3.setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon("image\\Untitled.png")); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jTextArea3.setRows(5);
        jScrollPane4.setViewportView(jTextArea3);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jButton2.setIcon(new javax.swing.ImageIcon("image\\Search.png")); // NOI18N
        jButton2.addActionListener(evt -> jButton2ActionPerformed());

        jLabel7.setFont(new java.awt.Font("Georgia", Font.BOLD, 14)); // NOI18N
        jLabel7.setText("English");

        jLabel8.setFont(new java.awt.Font("Georgia", Font.BOLD, 14)); // NOI18N
        jLabel8.setText("Vietnammese");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
                jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
        );
        jFrame3Layout.setVerticalGroup(
                jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jFrame3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dictionary");

        jPanel2.setBackground(new java.awt.Color(102, 255, 255));

        jList1.setFont(new java.awt.Font("Arial", Font.BOLD, 12)); // NOI18N
        jList1.addListSelectionListener(evt -> jList1ValueChanged());
        jScrollPane1.setViewportView(jList1);

        jTextField1.setFont(new java.awt.Font("Arial", Font.BOLD, 12)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased();
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Georgia", Font.BOLD | Font.ITALIC, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("English");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Georgia", Font.BOLD | Font.ITALIC, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Vietnamese");

        jButton1.setIcon(new javax.swing.ImageIcon("image\\speaker1.gif")); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased();
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButton1))
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)))
        );

        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon("image\\Add.png")); // NOI18N
        jMenuItem1.setText("Add");
        jMenuItem1.addActionListener(evt -> jMenuItem1ActionPerformed());
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon("image\\Edit.png")); // NOI18N
        jMenuItem2.setText("Fix");
        jMenuItem2.addActionListener(evt -> jMenuItem2ActionPerformed());
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon("image\\Delete.png")); // NOI18N
        jMenuItem3.setText("Delete");
        jMenuItem3.addActionListener(evt -> jMenuItem3ActionPerformed());
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setFont(new java.awt.Font("Arial", Font.BOLD, 14)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon("image\\google.png")); // NOI18N
        jMenuItem4.setText("Google API");
        jMenuItem4.addActionListener(evt -> jMenuItem4ActionPerformed());
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void jButton3ActionPerformed() {
        // TODO add your handling code here:
        Word new_word = new Word();
        new_word.word_target = jTextField3.getText();
        new_word.word_explain = jTextArea4.getText();
        int index = Collections.binarySearch(Words, new_word,
                (w1, w2) -> w1.word_target.compareToIgnoreCase(w2.word_target));
        Words.add(-index - 1, new_word);
        vnmeses.put(new_word.word_target, new_word.word_explain);
        listModel.add(-index - 1, new_word.word_target);
        jList1.setModel(listModel);
        outToFile();
    }

    private void jButton6ActionPerformed() {
        // TODO add your handling code here:
        Word new_word = new Word();
        new_word.word_target = jList1.getSelectedValue();
        String u = jList1.getSelectedValue();
        String s = vnmeses.get(new_word.word_target);
        int index = jList1.getSelectedIndex();
        int count = 0;
        for (int i = 0; i < Words.size(); i++) {
            if (u.equals(Words.get(i).word_target)) {
                count = i;
                break;
            }
        }
        Words.remove(count);
        listModel.remove(index);
        new_word.word_target = jTextField5.getText();
        new_word.word_explain = s;
        Words.add(count, new_word);
        vnmeses.put(new_word.word_target, new_word.word_explain);
        listModel.add(count, new_word.word_target);
        jList1.setModel(listModel);
        outToFile();
    }

    private void jMenuItem1ActionPerformed() {
        // TODO add your handling code here:
        jFrame2.pack();
        jFrame2.setVisible(true);
    }

    private void jMenuItem2ActionPerformed() {
        // TODO add your handling code here:
        jFrame1.pack();
        jFrame1.setVisible(true);
    }

    private void jMenuItem3ActionPerformed() {
        // TODO add your handling code here:
        int index = jList1.getSelectedIndex();
        String s = jList1.getSelectedValue();
        int count = 0;
        for (int i = 0; i < Words.size(); i++) {
            if (s.equals(Words.get(i).word_target)) {
                count = i;
                break;
            }
        }
        Words.remove(count);
        listModel.remove(index);
        jList1.setModel(listModel);
        outToFile();
    }

    private void jMenuItem4ActionPerformed() {
        // TODO add your handling code here:
        jFrame3.pack();
        jFrame3.setVisible(true);
    }

    private void jButton2ActionPerformed() {
        // TODO add your handling code here:
        String s = jTextArea3.getText();
        try {
            String u = GoogleAPI.translate("en", "vi", s);
            jTextArea2.setText(u);
        } catch (IOException ex) {
            Logger.getLogger(DictionaryApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static final String VOICENAME = "kevin16";

    private void jButton1MouseReleased() {
        // TODO add your handling code here:
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICENAME);
        voice.allocate();
        try {
            voice.speak(jList1.getSelectedValue());
        } catch (Exception ignored) {
        }
    }

    private void jTextField1KeyReleased() {
        // TODO add your handling code here:
        searchFilter(jTextField1.getText());
        // selectlist(jTextField1.getText());
    }

    private void jList1ValueChanged() {
        // TODO add your handling code here:
        Word new_word = new Word();
        new_word.word_target = jList1.getSelectedValue();
        String s = vnmeses.get(new_word.word_target);
        jTextArea1.setText(s);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DictionaryApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new DictionaryApplication().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(DictionaryApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration


}
