package View;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

/**
 * GameplayFrame represents the main game window for the Sudoku application.
 * It displays the Sudoku grid, user name, and selected difficulty level.
 * The frame provides functionality to interact with the gameplay interface.
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
    private javax.swing.JPanel sudokuGridPanel;
    
     /**
     * The listener for gameplay actions (e.g., button clicks).
     */
    private ViewListener viewListener;
    
     /**
     * Sets the listener for gameplay actions.
     * 
     * @param listener the listener to handle gameplay actions (e.g., button clicks)
     */
    public void setGameplayActionListener(ViewListener listener) {
    this.viewListener = listener;
}

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
        sudokuPanel.setViewportView(sudokuGridPanel);
        
        }
    
     /**
     * Sets the viewport for the Sudoku grid panel.
     */
    public void setViewportView() {
        sudokuPanel.setViewportView(sudokuGridPanel);
    }
 
     /**
     * Adds a button to the Sudoku grid panel.
     * 
     * @param button the button to be added to the grid
     * @throws IllegalStateException if the sudokuGridPanel has not been initialized
     */
    public void addToGridPanel(JButton button){
         if (sudokuGridPanel == null) {
        throw new IllegalStateException("sudokuGridPanel has not been initialized.");
    }
    sudokuGridPanel.add(button);
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
     * This method triggers the viewListener to navigate back to the initial 
     * initFrame
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      if (viewListener != null) {
            viewListener.onBackButtonClicked();
      }
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
