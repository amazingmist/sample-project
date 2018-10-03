package poly.app.view.utils;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import poly.app.core.helper.ObjectStructureHelper;

public class TableRenderer {
    private JTable jTable;

    public TableRenderer(JTable jTable) {
        this.jTable = jTable;
    }
    
    public void setCellEditable(boolean isEditable){
        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditable;
            }
        };
        this.jTable.setModel(tableModel);
    }
    
    public void setDataVector(Vector tableData, String[] tableIdentifiers){
        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        tableModel.setDataVector(tableData, new Vector(Arrays.asList(tableIdentifiers)));
        this.jTable.setModel(tableModel);
    }
}