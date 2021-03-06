package poly.app.view.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class TableRenderer {

    public static final int CELL_ALIGN_LEFT = 2;
    public static final int CELL_ALIGN_CENTER = 0;
    public static final int CELL_ALIGN_RIGHT = 4;

    private JTable jTable;

    public TableRenderer(JTable jTable) {
        this.jTable = jTable;
    }

    public TableRenderer(JTable jTable, Class[] columnClass) {
        this.jTable = new JTable() {
            private static final long serialVersionUID = 1L;
            @Override
            public Class<?> getColumnClass(int column) {
//                return columnClass[column];
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
        jTable = this.jTable;
    }

    public void setCellEditable(boolean isEditable) {
        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditable;
            }
        };
        this.jTable.setModel(tableModel);
    }
    
    public void setCellEditable(int columnIndex) {
        this.jTable.getModel().isCellEditable(-1, columnIndex);
    }

    public void setDataVector(Vector tableData, String[] tableIdentifiers) {
        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        tableModel.setDataVector(tableData, new Vector(Arrays.asList(tableIdentifiers)));
        this.jTable.setModel(tableModel);
    }

    public void changeHeaderStyle() {
        JTableHeader jTableHeader = this.jTable.getTableHeader();
        jTableHeader.setFont(new Font("open sans", Font.PLAIN, 14)); // font name style size
        // canh giua man hinh
        ((DefaultTableCellRenderer) jTableHeader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        // chieu cao header
        jTableHeader.setPreferredSize(new Dimension(0, 25));
        jTableHeader.setForeground(Color.decode("#000")); // change the Foreground
        this.jTable.setFillsViewportHeight(true);
        this.jTable.setBackground(Color.WHITE);
    }

    public void setColumnAlignment(int columnIndex, int cellAlignment) {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(cellAlignment);
        this.jTable.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
    }

    public void setColoumnWidthByPersent(int columnIndex, int persent) {
        double tableWidth = this.jTable.getPreferredSize().getWidth();
        this.jTable.getColumnModel().getColumn(columnIndex).setPreferredWidth((int) (tableWidth * persent / 100));
    }
}
