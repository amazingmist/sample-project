/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.view;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import javax.swing.ButtonGroup;
import poly.app.core.constant.CoreConstant;
import poly.app.core.daoimpl.KhoaHocDaoImpl;
import poly.app.core.entities.ChuyenDe;
import poly.app.core.entities.KhoaHoc;
import poly.app.core.helper.ObjectStructureHelper;
import poly.app.core.procedures.record.BangDiemRecord;
import poly.app.core.procedures.record.ThongKeDiemRecord;
import poly.app.core.procedures.record.ThongKeDoanhThuRecord;
import poly.app.core.procedures.record.ThongKeNguoiHocRecord;
import poly.app.core.procedures.sp_BangDiem;
import poly.app.core.procedures.sp_ThongKeDiem;
import poly.app.core.procedures.sp_ThongKeDoanhThu;
import poly.app.core.procedures.sp_ThongKeNguoiHoc;
import poly.app.view.utils.TableRenderer;

public class ThongKeJFrame extends javax.swing.JFrame {
    Vector<Vector> tableData = new Vector<>();
    List<KhoaHoc> khoaHocList;

    public ThongKeJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        reRenderUI();
    }
    
    public void setTab(int selectedIndex) {
        loadKhoaHocList();
        panelTab.setSelectedIndex(selectedIndex);
    }

    private void reRenderUI() {
//        TBL NGUOI HOC
        TableRenderer tblRenderer1 = new TableRenderer(tblTKNguoiHoc);
        tblRenderer1.setCellEditable(false);
        tblRenderer1.setDataVector(tableData, ObjectStructureHelper.THONGKENGUOIHOC_TABLE_IDENTIFIERS);
        tblRenderer1.changeHeaderStyle();
        tblRenderer1.setColumnAlignment(0, TableRenderer.CELL_ALIGN_CENTER);
        tblRenderer1.setColumnAlignment(1, TableRenderer.CELL_ALIGN_CENTER);
        tblRenderer1.setColumnAlignment(2, TableRenderer.CELL_ALIGN_CENTER);
        tblRenderer1.setColumnAlignment(3, TableRenderer.CELL_ALIGN_CENTER);
        tblRenderer1.setColoumnWidthByPersent(1, 20);
        tblRenderer1.setColoumnWidthByPersent(2, 35);
        tblRenderer1.setColoumnWidthByPersent(3, 35);
        
//        TBL BANG DIEM
        TableRenderer tblRenderer2 = new TableRenderer(tblTKBangDiem);
        tblRenderer2.setCellEditable(false);
        tblRenderer2.setDataVector(tableData, ObjectStructureHelper.THONGKEDIEM_TABLE_IDENTIFIERS);
        tblRenderer2.changeHeaderStyle();
        tblRenderer2.setColumnAlignment(0, TableRenderer.CELL_ALIGN_CENTER);
        tblRenderer2.setColumnAlignment(1, TableRenderer.CELL_ALIGN_LEFT);
        tblRenderer2.setColumnAlignment(2, TableRenderer.CELL_ALIGN_CENTER);
        tblRenderer2.setColumnAlignment(3, TableRenderer.CELL_ALIGN_CENTER);
        tblRenderer2.setColoumnWidthByPersent(1, 45);
        tblRenderer2.setColoumnWidthByPersent(3, 35);
        
//        TBL BANG DIEM
        TableRenderer tblRenderer3 = new TableRenderer(tblTKTongHopDiem);
        tblRenderer3.setCellEditable(false);
        tblRenderer3.setDataVector(tableData, ObjectStructureHelper.THONGKETONGHOPDIEM_TABLE_IDENTIFIERS);
        tblRenderer3.changeHeaderStyle();
        tblRenderer3.setColumnAlignment(0, TableRenderer.CELL_ALIGN_LEFT);
        tblRenderer3.setColumnAlignment(1, TableRenderer.CELL_ALIGN_RIGHT);
        tblRenderer3.setColumnAlignment(2, TableRenderer.CELL_ALIGN_RIGHT);
        tblRenderer3.setColumnAlignment(3, TableRenderer.CELL_ALIGN_RIGHT);
        tblRenderer3.setColumnAlignment(4, TableRenderer.CELL_ALIGN_RIGHT);
        tblRenderer3.setColoumnWidthByPersent(0, 80);
        
//        TBL DOANH THU
        TableRenderer tblRenderer4 = new TableRenderer(tblTKDoanhThu);
        tblRenderer4.setCellEditable(false);
        tblRenderer4.setDataVector(tableData, ObjectStructureHelper.THONGKEDOANHTHU_TABLE_IDENTIFIERS);
        tblRenderer4.changeHeaderStyle();
        tblRenderer4.setColumnAlignment(0, TableRenderer.CELL_ALIGN_LEFT);
        tblRenderer4.setColumnAlignment(1, TableRenderer.CELL_ALIGN_RIGHT);
        tblRenderer4.setColumnAlignment(2, TableRenderer.CELL_ALIGN_RIGHT);
        tblRenderer4.setColumnAlignment(3, TableRenderer.CELL_ALIGN_RIGHT);
        tblRenderer4.setColumnAlignment(4, TableRenderer.CELL_ALIGN_RIGHT);
        tblRenderer4.setColumnAlignment(5, TableRenderer.CELL_ALIGN_RIGHT);
        tblRenderer4.setColumnAlignment(6, TableRenderer.CELL_ALIGN_RIGHT);
        
        tblRenderer4.setColoumnWidthByPersent(0, 40);
    }

    private void loadKhoaHocList(){
        if (khoaHocList != null) {
            khoaHocList.clear();
        }
        khoaHocList = new KhoaHocDaoImpl().selectByProperties(null, null, "ngayKg", CoreConstant.SORT_DESC, null, null);
        loadDataToCboKhoaHoc();
        loadDataToCboNamHoc();
    }

    private void loadDataToTableTKNguoiHoc() {
        tableData.clear();
        List<ThongKeNguoiHocRecord> dataLoadedList = new sp_ThongKeNguoiHoc().execute();
        for (ThongKeNguoiHocRecord record : dataLoadedList) {
            Vector vData = new Vector();
            vData.add(record.getNam());
            vData.add(record.getSoLuong());
            vData.add(record.getNgayGhiDanhDau());
            vData.add(record.getNgayGhiDanhCuoi());
            tableData.add(vData);
        }
        tblTKNguoiHoc.updateUI();
    }

    private void loadDataToTableTKBangDiem() {
        tableData.clear();
        int maKH = khoaHocList.get(cboKhoaHoc.getSelectedIndex()).getMaKh();
        List<BangDiemRecord> dataLoadedList = new sp_BangDiem(maKH).execute();
        for (BangDiemRecord record : dataLoadedList) {
            Vector vData = new Vector();
            vData.add(record.getMaNH());
            vData.add(record.getHoTen());
            vData.add(record.getDiem());
            vData.add(record.getXepLoai());
            tableData.add(vData);
        }
        tblTKBangDiem.updateUI();
    }

    private void loadDataToTableTKTongHopDiem() {
        tableData.clear();
        List<ThongKeDiemRecord> dataLoadedList = new sp_ThongKeDiem().execute();
        for (ThongKeDiemRecord record : dataLoadedList) {
            Vector vData = new Vector();
            vData.add(record.getTenChuyenDe());
            vData.add(record.getSoHocVien());
            vData.add(record.getDiemCaoNhat());
            vData.add(record.getDiemThapNhat());
            vData.add(record.getDiemTrungBinh());
            tableData.add(vData);
        }
        tblTKTongHopDiem.updateUI();
    }

    private void loadDataToTableTKDoanhThu() {
        tableData.clear();
        int year = Integer.parseInt(cboNamHoc.getSelectedItem().toString());
        List<ThongKeDoanhThuRecord> dataLoadedList = new sp_ThongKeDoanhThu(year).execute();
        for (ThongKeDoanhThuRecord record : dataLoadedList) {
            Vector vData = new Vector();
            vData.add(record.getTenChuyenDe());
            vData.add(record.getSoKhoaHoc());
            vData.add(record.getSoHocVien());
            vData.add(record.getDoanhThu());
            vData.add(record.getHocPhiCaoNhat());
            vData.add(record.getHocPhiThapNhat());
            vData.add(record.getHocPhiTrungBinh());
            tableData.add(vData);
        }
        tblTKDoanhThu.updateUI();
    }
    
    private void loadDataToCboKhoaHoc(){
        for (KhoaHoc khoaHoc : khoaHocList) {
            ChuyenDe chuyenDe = khoaHoc.getChuyenDe();
            String item = chuyenDe.getMaCd() + "(" + khoaHoc.getNgayKg() + " - " + chuyenDe.getTenCd() + ")";
            cboKhoaHoc.addItem(item);
        }
    }
    
    private void loadDataToCboNamHoc(){
        String tempYear = "";
        for (KhoaHoc khoaHoc : khoaHocList) { 
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(khoaHoc.getNgayKg());
            String item = calendar.get(Calendar.YEAR) + "";
            if (!item.equals(tempYear)) {
                cboNamHoc.addItem(item);
                tempYear = item.toString();
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panelTab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTKNguoiHoc = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTKBangDiem = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cboKhoaHoc = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTKTongHopDiem = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTKDoanhThu = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cboNamHoc = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(65, 76, 89));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");
        jPanel3.add(jLabel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        panelTab.setBackground(new java.awt.Color(255, 255, 255));
        panelTab.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        panelTab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                panelTabStateChanged(evt);
            }
        });

        tblTKNguoiHoc.setAutoCreateRowSorter(true);
        tblTKNguoiHoc.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblTKNguoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTKNguoiHoc.setGridColor(new java.awt.Color(204, 204, 204));
        tblTKNguoiHoc.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblTKNguoiHoc.setRowHeight(23);
        tblTKNguoiHoc.setSelectionBackground(new java.awt.Color(65, 76, 89));
        tblTKNguoiHoc.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTKNguoiHoc.setShowGrid(false);
        jScrollPane1.setViewportView(tblTKNguoiHoc);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );

        panelTab.addTab("Người học", jPanel2);

        tblTKBangDiem.setAutoCreateRowSorter(true);
        tblTKBangDiem.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblTKBangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTKBangDiem.setGridColor(new java.awt.Color(204, 204, 204));
        tblTKBangDiem.setRowHeight(23);
        tblTKBangDiem.setSelectionBackground(new java.awt.Color(65, 76, 89));
        tblTKBangDiem.setShowGrid(false);
        jScrollPane2.setViewportView(tblTKBangDiem);

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        jLabel2.setText("KHÓA HỌC:");

        cboKhoaHoc.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        cboKhoaHoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhoaHocItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboKhoaHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );

        panelTab.addTab("Bảng điểm", jPanel9);

        tblTKTongHopDiem.setAutoCreateRowSorter(true);
        tblTKTongHopDiem.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblTKTongHopDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTKTongHopDiem.setGridColor(new java.awt.Color(204, 204, 204));
        tblTKTongHopDiem.setRowHeight(23);
        tblTKTongHopDiem.setSelectionBackground(new java.awt.Color(65, 76, 89));
        tblTKTongHopDiem.setShowGrid(false);
        jScrollPane3.setViewportView(tblTKTongHopDiem);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );

        panelTab.addTab("Tổng hợp điểm", jPanel10);

        tblTKDoanhThu.setAutoCreateRowSorter(true);
        tblTKDoanhThu.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblTKDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTKDoanhThu.setGridColor(new java.awt.Color(204, 204, 204));
        tblTKDoanhThu.setRowHeight(23);
        tblTKDoanhThu.setSelectionBackground(new java.awt.Color(65, 76, 89));
        tblTKDoanhThu.setShowGrid(false);
        jScrollPane4.setViewportView(tblTKDoanhThu);

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("NĂM:");

        cboNamHoc.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboNamHoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamHocItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNamHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboNamHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
        );

        panelTab.addTab("Doanh thu", jPanel11);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTab, javax.swing.GroupLayout.Alignment.TRAILING))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelTab, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelTabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_panelTabStateChanged
        switch(panelTab.getSelectedIndex()){
            case 0:
                loadDataToTableTKNguoiHoc();
                break;
            case 1:
                loadDataToTableTKBangDiem();
                break;
            case 2:
                loadDataToTableTKTongHopDiem();
                break;
            case 3:
                loadDataToTableTKDoanhThu();
                break;
        }
    }//GEN-LAST:event_panelTabStateChanged

    private void cboKhoaHocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhoaHocItemStateChanged
        if (panelTab.getSelectedIndex() == 1) {
            loadDataToTableTKBangDiem();
        }
    }//GEN-LAST:event_cboKhoaHocItemStateChanged

    private void cboNamHocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamHocItemStateChanged
        if (panelTab.getSelectedIndex() == 3) {
            loadDataToTableTKDoanhThu();
        }
    }//GEN-LAST:event_cboNamHocItemStateChanged

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboKhoaHoc;
    private javax.swing.JComboBox<String> cboNamHoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane panelTab;
    private javax.swing.JTable tblTKBangDiem;
    private javax.swing.JTable tblTKDoanhThu;
    private javax.swing.JTable tblTKNguoiHoc;
    private javax.swing.JTable tblTKTongHopDiem;
    // End of variables declaration//GEN-END:variables
    private ButtonGroup gioiTinhGroup = new ButtonGroup();
    private ButtonGroup vaiTroGroup = new ButtonGroup();
}
