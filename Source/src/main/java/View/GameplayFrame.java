package View;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.*;



/**
 * GameplayFrame class represents the main game window for the Sudoku application.
 * It displays the Sudoku grid, user name, and selected difficulty level.
 * 
 * @author Karol Pitera
 */

@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class GameplayFrame extends javax.swing.JFrame {
    
    /**
 * The panel where the Sudoku grid will be displayed.
 */
    
    private Model.ButtonModel buttonModel;
    
    private javax.swing.JPanel sudokuGridPanel;

   /**
     * Creates a new GameplayFrame window.
     * 
     * @param userName         the name of the user
     * @param difficultyLevel  the difficulty level of the game
     */
    public GameplayFrame(String userName, String difficultyLevel) {
        initComponents();
        setLocationRelativeTo(null); // This will center the frame on the screen
        nameLabel.setText(userName);  // Display user name
        lavelLabel.setText(difficultyLevel);  // Display difficulty level
        
        sudokuGridPanel = new javax.swing.JPanel();
        sudokuGridPanel.setLayout(new java.awt.GridLayout(9, 9));
        
        setupSudokuGrid();
        }
 /**
     * Sets up the 9x9 Sudoku grid with interactive buttons.
     */
   private void setupSudokuGrid() {
    buttonModel = new Model.ButtonModel(); // Inicjalizacja modelu
    JButton[][] buttons = new JButton[9][9];

    // Ustawienie początkowej planszy w modelu
    buttonModel.setInitialGrid();
    
    for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {
            JButton button = new JButton("");
            button.setPreferredSize(new Dimension(67, 67));
            button.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));

            // Ustawienia wizualne
            if ((row >= 3 && row <= 5) || (col >= 3 && col <= 5)) {
                button.setBackground(new Color(211, 211, 211));
            } else {
                button.setBackground(Color.WHITE);
            }
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Generation unique ID
            int id = row * 9 + col;
            
            button.setName("cell_" + id);
            button.setToolTipText("Click to enter number");

            // Set init value from model
            int value = buttonModel.getInitialGrid()[row][col];
            buttonModel.addButton(id, button); 
            buttonModel.setValue(id, value); 

            // Block buttons with init value
            if (value != 0) {
                button.setEnabled(false);
                button.setForeground(Color.BLUE); // Underline init value
            } else {
                button.setToolTipText("Click to enter number");

                // Button action for empty cells
                button.addActionListener(e -> {
                    String input = JOptionPane.showInputDialog("Enter a number (1-9):");
                    if (input != null && input.matches("[1-9]")) {
                        buttonModel.setValue(id, Integer.parseInt(input)); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number between 1 and 9.");
                    }
                });
            }

            buttons[row][col] = button;
            sudokuGridPanel.add(button); 
        }
    }

    sudokuPanel.setViewportView(sudokuGridPanel);
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameMessageLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        levelMessageLabel = new javax.swing.JLabel();
        lavelLabel = new javax.swing.JLabel();
        sudokuPanel = new javax.swing.JScrollPane();
        sudokuTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nameMessageLabel.setText("Name");

        nameLabel.setText("userName");

        levelMessageLabel.setText("Level:");

        lavelLabel.setText("choseLevel");

        sudokuPanel.setColumnHeaderView(sudokuTable);
        sudokuPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sudokuPanel.setPreferredSize(new java.awt.Dimension(221, 221));

        sudokuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "A", "B", "C", "D", "E", "F", "G", "H", "I"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        sudokuTable.setToolTipText("");
        sudokuTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        sudokuTable.setAutoscrolls(false);
        sudokuTable.setFocusable(false);
        sudokuTable.setPreferredSize(new java.awt.Dimension(603, 603));
        sudokuTable.setRequestFocusEnabled(false);
        sudokuTable.setRowHeight(67);
        sudokuTable.setShowGrid(true);
        sudokuPanel.setViewportView(sudokuTable);
        if (sudokuTable.getColumnModel().getColumnCount() > 0) {
            sudokuTable.getColumnModel().getColumn(0).setResizable(false);
            sudokuTable.getColumnModel().getColumn(1).setResizable(false);
            sudokuTable.getColumnModel().getColumn(2).setResizable(false);
            sudokuTable.getColumnModel().getColumn(3).setResizable(false);
            sudokuTable.getColumnModel().getColumn(4).setResizable(false);
            sudokuTable.getColumnModel().getColumn(5).setResizable(false);
            sudokuTable.getColumnModel().getColumn(6).setResizable(false);
            sudokuTable.getColumnModel().getColumn(7).setResizable(false);
            sudokuTable.getColumnModel().getColumn(8).setResizable(false);
        }

        jButton1.setMnemonic('b');
        jButton1.setText("Back");
        jButton1.setToolTipText("Clik to change name or level");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameMessageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(levelMessageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lavelLabel)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(sudokuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameMessageLabel)
                            .addComponent(nameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(levelMessageLabel)
                            .addComponent(lavelLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sudokuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * Action performed when the 'Back' button is clicked.
 * This method closes the current gameplay window and opens the initial frame for setting the name and difficulty level.
 */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        InitFrame initView = new InitFrame();  // Pass name and level to GameplayFrame
        initView.setVisible(true);  // Display the GameplayFrame
        this.dispose();  // Close the InitFrame (optional, based on your flow)
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Button which come back to init window.
    */
    private javax.swing.JButton jButton1;
    /**
    * Label displaying the difficulty level of the Sudoku game.
    */
    private javax.swing.JLabel lavelLabel;
    /**
    * Label displaying the level message text ("Level:").
    */
    private javax.swing.JLabel levelMessageLabel;
    /**
    * Label displaying the user's name.
    */
    private javax.swing.JLabel nameLabel;
    /**
    * Label displaying the name message label.
    */
    private javax.swing.JLabel nameMessageLabel;
    /**
    * Panel is responsible for table wraping
    */
    private javax.swing.JScrollPane sudokuPanel;
    /**
    * Table includes buttons for sudoku numbers
    */
    private javax.swing.JTable sudokuTable;
    // End of variables declaration//GEN-END:variables

}
