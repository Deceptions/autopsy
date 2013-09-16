/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011-2013 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.corecomponents;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import org.sleuthkit.autopsy.coreutils.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import org.openide.nodes.Node;
import org.openide.util.lookup.ServiceProvider;
import org.sleuthkit.autopsy.corecomponentinterfaces.DataContentViewer;
import org.sleuthkit.autopsy.datamodel.DataConversion;
import org.sleuthkit.datamodel.Content;
import org.sleuthkit.datamodel.TskException;

/**
 * Hex view of file contents.
 */
@ServiceProvider(service = DataContentViewer.class, position = 1)
public class DataContentViewerHex extends javax.swing.JPanel implements DataContentViewer {
    private static final long pageLength = 16384;
    private final byte[] data = new byte[(int) pageLength];
    private static int currentPage = 1;
    private int totalPages;
    private Content dataSource;

    private static final Logger logger = Logger.getLogger(DataContentViewerHex.class.getName());

    /**
     * Creates new form DataContentViewerHex
     */
    public DataContentViewerHex() {
        initComponents();
        customizeComponents();
        this.resetComponent();
        logger.log(Level.INFO, "Created HexView instance: " + this);
    }

    private void customizeComponents() {
        outputViewPane.setComponentPopupMenu(rightClickMenu);
        ActionListener actList = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem jmi = (JMenuItem) e.getSource();
                if (jmi.equals(copyMenuItem)) {
                    outputViewPane.copy();
                } else if (jmi.equals(selectAllMenuItem)) {
                    outputViewPane.selectAll();
                }
            }
        };
        copyMenuItem.addActionListener(actList);
        selectAllMenuItem.addActionListener(actList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightClickMenu = new javax.swing.JPopupMenu();
        copyMenuItem = new javax.swing.JMenuItem();
        selectAllMenuItem = new javax.swing.JMenuItem();
        hexViewerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputViewPane = new JTextPane(){
     public boolean getScrollableTracksViewportWidth() {
     return (getSize().width < 400);
 }};
        this.outputViewPane.setBackground(new java.awt.Color(255, 255, 255)); // to make sure the background color is white
        totalPageLabel = new javax.swing.JLabel();
        ofLabel = new javax.swing.JLabel();
        currentPageLabel = new javax.swing.JLabel();
        pageLabel = new javax.swing.JLabel();
        prevPageButton = new javax.swing.JButton();
        nextPageButton = new javax.swing.JButton();
        pageLabel2 = new javax.swing.JLabel();
        goToPageTextField = new javax.swing.JTextField();
        goToPageLabel = new javax.swing.JLabel();

        copyMenuItem.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.copyMenuItem.text")); // NOI18N
        rightClickMenu.add(copyMenuItem);

        selectAllMenuItem.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.selectAllMenuItem.text")); // NOI18N
        rightClickMenu.add(selectAllMenuItem);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        outputViewPane.setEditable(false);
        outputViewPane.setFont(new java.awt.Font("Courier New", 0, 11)); // NOI18N
        outputViewPane.setMinimumSize(new java.awt.Dimension(700, 20));
        outputViewPane.setPreferredSize(new java.awt.Dimension(700, 400));
        jScrollPane1.setViewportView(outputViewPane);

        totalPageLabel.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.totalPageLabel.text_1")); // NOI18N

        ofLabel.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.ofLabel.text_1")); // NOI18N

        currentPageLabel.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.currentPageLabel.text_1")); // NOI18N
        currentPageLabel.setMaximumSize(new java.awt.Dimension(18, 14));
        currentPageLabel.setMinimumSize(new java.awt.Dimension(18, 14));
        currentPageLabel.setPreferredSize(new java.awt.Dimension(18, 14));

        pageLabel.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.pageLabel.text_1")); // NOI18N
        pageLabel.setMaximumSize(new java.awt.Dimension(33, 14));
        pageLabel.setMinimumSize(new java.awt.Dimension(33, 14));
        pageLabel.setPreferredSize(new java.awt.Dimension(33, 14));

        prevPageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/corecomponents/btn_step_back.png"))); // NOI18N
        prevPageButton.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.prevPageButton.text")); // NOI18N
        prevPageButton.setBorderPainted(false);
        prevPageButton.setContentAreaFilled(false);
        prevPageButton.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/corecomponents/btn_step_back_disabled.png"))); // NOI18N
        prevPageButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        prevPageButton.setPreferredSize(new java.awt.Dimension(23, 23));
        prevPageButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/corecomponents/btn_step_back_hover.png"))); // NOI18N
        prevPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevPageButtonActionPerformed(evt);
            }
        });

        nextPageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/corecomponents/btn_step_forward.png"))); // NOI18N
        nextPageButton.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.nextPageButton.text")); // NOI18N
        nextPageButton.setBorderPainted(false);
        nextPageButton.setContentAreaFilled(false);
        nextPageButton.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/corecomponents/btn_step_forward_disabled.png"))); // NOI18N
        nextPageButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        nextPageButton.setPreferredSize(new java.awt.Dimension(23, 23));
        nextPageButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/corecomponents/btn_step_forward_hover.png"))); // NOI18N
        nextPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPageButtonActionPerformed(evt);
            }
        });

        pageLabel2.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.pageLabel2.text")); // NOI18N
        pageLabel2.setMaximumSize(new java.awt.Dimension(29, 14));
        pageLabel2.setMinimumSize(new java.awt.Dimension(29, 14));
        pageLabel2.setPreferredSize(new java.awt.Dimension(29, 14));

        goToPageTextField.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.goToPageTextField.text")); // NOI18N
        goToPageTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToPageTextFieldActionPerformed(evt);
            }
        });

        goToPageLabel.setText(org.openide.util.NbBundle.getMessage(DataContentViewerHex.class, "DataContentViewerHex.goToPageLabel.text")); // NOI18N

        javax.swing.GroupLayout hexViewerPanelLayout = new javax.swing.GroupLayout(hexViewerPanel);
        hexViewerPanel.setLayout(hexViewerPanelLayout);
        hexViewerPanelLayout.setHorizontalGroup(
            hexViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hexViewerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(currentPageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ofLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalPageLabel)
                .addGap(50, 50, 50)
                .addComponent(pageLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prevPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nextPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(goToPageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goToPageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );
        hexViewerPanelLayout.setVerticalGroup(
            hexViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hexViewerPanelLayout.createSequentialGroup()
                .addGroup(hexViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(hexViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currentPageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ofLabel)
                        .addComponent(totalPageLabel))
                    .addComponent(pageLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prevPageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goToPageLabel)
                    .addComponent(goToPageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(hexViewerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(hexViewerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void prevPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevPageButtonActionPerformed
        setDataView(currentPage - 1);
    }//GEN-LAST:event_prevPageButtonActionPerformed

    private void nextPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPageButtonActionPerformed
        setDataView(currentPage + 1);
    }//GEN-LAST:event_nextPageButtonActionPerformed

    private void goToPageTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToPageTextFieldActionPerformed
        String pageNumberStr = goToPageTextField.getText();
        int pageNumber = 0;
        
        try {
            pageNumber = Integer.parseInt(pageNumberStr);
        } catch (NumberFormatException ex) {
            pageNumber = totalPages + 1;
        }
        if (pageNumber > totalPages || pageNumber < 1) {
            JOptionPane.showMessageDialog(this, "Please enter a valid page number between 1 and " + totalPages,
                    "Invalid page number", JOptionPane.WARNING_MESSAGE);
            return;
        }
        setDataView(pageNumber);
    }//GEN-LAST:event_goToPageTextFieldActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JLabel currentPageLabel;
    private javax.swing.JLabel goToPageLabel;
    private javax.swing.JTextField goToPageTextField;
    private javax.swing.JPanel hexViewerPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextPageButton;
    private javax.swing.JLabel ofLabel;
    private javax.swing.JTextPane outputViewPane;
    private javax.swing.JLabel pageLabel;
    private javax.swing.JLabel pageLabel2;
    private javax.swing.JButton prevPageButton;
    private javax.swing.JPopupMenu rightClickMenu;
    private javax.swing.JMenuItem selectAllMenuItem;
    private javax.swing.JLabel totalPageLabel;
    // End of variables declaration//GEN-END:variables

    
    /**
     * Sets the DataView (The tabbed panel)
     *
     * @param page Page to display (1-based counting)
     */
    private void setDataView(int page) {
        if (this.dataSource == null) {
            return;
        }
        
        if (page == 0) {
            return;
        }
        
        currentPage = page;
        long offset = (currentPage - 1) * pageLength;
        
        
        // change the cursor to "waiting cursor" for this operation
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        String errorText = null;     

        int bytesRead = 0;
        if (dataSource.getSize() > 0) {
            try {
                bytesRead = dataSource.read(data, offset, pageLength); // read the data
            } catch (TskException ex) {
                errorText = "(offset " + offset + "-" + (offset + pageLength)
                    + " could not be read)";
                logger.log(Level.WARNING, "Error while trying to show the hex content.", ex);
            }
        }

        // set the data on the bottom and show it
        if (bytesRead <= 0) {
            errorText = "(offset " + offset + "-" + (offset + pageLength)
                    + " could not be read)";
        }
        

        // disable or enable the next button
        if ((errorText == null) && (currentPage < totalPages)) {
            nextPageButton.setEnabled(true);
        } 
        else {
            nextPageButton.setEnabled(false);
        }

        if ((errorText == null) && (currentPage > 1)) {
            prevPageButton.setEnabled(true);
        } 
        else {
            prevPageButton.setEnabled(false);
        }
        
        currentPageLabel.setText(Integer.toString(currentPage));
        setComponentsVisibility(true); // shows the components that not needed

        // set the output view
        if (errorText == null) {
            int showLength = bytesRead < pageLength ? bytesRead : (int) pageLength;
            outputViewPane.setText(DataConversion.byteArrayToHex(data, showLength, offset, outputViewPane.getFont()));
        }
        else {
            outputViewPane.setText(errorText);
        }

        outputViewPane.moveCaretPosition(0);
        this.setCursor(null);
    }

    @Override
    public void setNode(Node selectedNode) {
        if ((selectedNode == null) || (!isSupported(selectedNode))) {
            resetComponent();
            return;
        }
        
        Content content = (selectedNode).getLookup().lookup(Content.class);
        if (content == null) {
            resetComponent();
            return;
        }
        
        dataSource = content;
        totalPages = 0;
        if (dataSource.getSize() > 0) {
            totalPages = Math.round((dataSource.getSize() - 1) / pageLength) + 1;
        }
        totalPageLabel.setText(Integer.toString(totalPages));
            
        this.setDataView(1);
    }

    @Override
    public String getTitle() {
        return "Hex";
    }

    @Override
    public String getToolTip() {
        return "Displays the binary contents of a file as hexidecimal, with "
                + "bytes that are displayable as ASCII characters on the right.";
    }

    @Override
    public DataContentViewer createInstance() {
        return new DataContentViewerHex();
    }

    @Override
    public void resetComponent() {
        // clear / reset the fields
        currentPage = 1;
        this.dataSource = null;
        currentPageLabel.setText("");
        totalPageLabel.setText("");
        outputViewPane.setText("");
        setComponentsVisibility(false); // hides the components that not needed
    }

    /**
     * To set the visibility of specific components in this class.
     *
     * @param isVisible whether to show or hide the specific components
     */
    private void setComponentsVisibility(boolean isVisible) {
        currentPageLabel.setVisible(isVisible);
        totalPageLabel.setVisible(isVisible);
        ofLabel.setVisible(isVisible);
        prevPageButton.setVisible(isVisible);
        nextPageButton.setVisible(isVisible);
        pageLabel.setVisible(isVisible);
        pageLabel2.setVisible(isVisible);
        goToPageTextField.setVisible(isVisible);
        goToPageLabel.setVisible(isVisible);
    }

    @Override
    public boolean isSupported(Node node) {
        if (node == null) {
            return false;
        }
        Content content = node.getLookup().lookup(Content.class);
        if (content != null && content.getSize() > 0) {
            return true;
        }
  
        return false;
    }

    @Override
    public int isPreferred(Node node, boolean isSupported) {
        if (isSupported) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Component getComponent() {
        return this;
    }

    /* Show the right click menu only if evt is the correct mouse event */
    private void maybeShowPopup(java.awt.event.MouseEvent evt) {
        if (evt.isPopupTrigger()) {
            rightClickMenu.setLocation(evt.getLocationOnScreen());
            rightClickMenu.setVisible(true);
            copyMenuItem.setEnabled(outputViewPane.getSelectedText() != null);
        } else {
            rightClickMenu.setVisible(false);
        }
    }
}
