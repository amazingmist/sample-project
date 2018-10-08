/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.view;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import poly.app.core.daoimpl.HocVienDaoImpl;
import poly.app.core.daoimpl.KhoaHocDaoImpl;
import poly.app.core.daoimpl.NguoiHocDaoImpl;
import poly.app.core.entities.HocVien;
import poly.app.core.entities.KhoaHoc;
import poly.app.core.entities.NguoiHoc;
import poly.app.core.helper.DialogHelper;
import poly.app.core.helper.ObjectStructureHelper;
import poly.app.core.utils.DataFactoryUtil;
import poly.app.view.utils.TableRenderer;

public class HocVienJFrame extends javax.swing.JFrame {

    Vector<Vector> tableData = new Vector<>();
    HashMap<Integer, HocVien> hocVienHashMap = new HashMap<>();
    List<NguoiHoc> nguoiHocNotInKhoaHoc = new ArrayList<>();
    KhoaHoc khoaHoc;

    public HocVienJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        reRenderUI();
        addEventForTable();
        addFillterToButtonGroup();
        loadData();
    }

    private void reRenderUI() {
        //        TBL HOC VIEN
        TableRenderer tblRenderer = new TableRenderer(tblHocVien);
        tblRenderer.setDataVector(tableData, ObjectStructureHelper.HOCVIEN_TABLE_IDENTIFIERS);
        tblRenderer.changeHeaderStyle();
        tblRenderer.setColumnAlignment(3, TableRenderer.CELL_ALIGN_CENTER);
        tblRenderer.setColoumnWidthByPersent(2, 50);
    }

    public void loadData() {
        loadDataToTable();
        loadDataToNguoiHocNotInKhoaHocList();
        loadDataToCboHocVienKhac();
    }

    private void addEventForTable() {
        tblHocVien.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int column = e.getColumn();
                    int row = tblHocVien.getSelectedRow();

                    if (column != 3) {
                        return;
                    }

                    float newDiem = Float.parseFloat(tblHocVien.getValueAt(row, column).toString());

                    int maHv = Integer.parseInt(tblHocVien.getValueAt(row, 0).toString());
                    HocVien hocVien = hocVienHashMap.get(maHv);

                    if (newDiem == hocVien.getDiem()) {
                        return;
                    }

                    hocVien.setDiem(newDiem);

                    boolean isUpdated = new HocVienDaoImpl().update(hocVien);
                    if (isUpdated) {
                        Vector vData = tableData.get(row);
                        vData.set(4, hocVien.getXepLoai());
                        DialogHelper.message(rootPane, "Cập nhật điểm thành công", DialogHelper.INFORMATION_MESSAGE);
                    } else {
                        DialogHelper.message(rootPane, "Cập nhật điểm thất bại", DialogHelper.ERROR_MESSAGE);
                    }
                    tblHocVien.updateUI();
                }
            }
        });
    }

    private void addFillterToButtonGroup() {
        btnGroupFillter.add(rdoTatCa);
        btnGroupFillter.add(rdoChuaNhapDiem);
        btnGroupFillter.add(rdoDaNhapDiem);
    }

    private void loadDataToTable() {
        tableData.clear();
        hocVienHashMap.clear();
        khoaHoc = new KhoaHocDaoImpl().selectById(1);
        List<HocVien> dataLoadedList = new HocVienDaoImpl().selectByProperties("khoaHoc", khoaHoc, null, null, null, null);

        try {
            for (HocVien hocVien : dataLoadedList) {
//                Convert hocvien to vector
                Vector vData = DataFactoryUtil.objectToVectorByFields(hocVien, ObjectStructureHelper.HOCVIEN_PROPERTIES);

                String maNh = ((NguoiHoc) vData.get(1)).getMaNh();
                vData.set(1, maNh);

                String hoTen = ((NguoiHoc) vData.get(2)).getHoTen();
                vData.set(2, hoTen);

                vData.add(hocVien.getXepLoai());
                vData.add(false);

                tableData.add(vData);

//                Add hocvien to hashmap
                hocVienHashMap.put(hocVien.getMaHv(), hocVien);
            }
            tblHocVien.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(HocVienJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataToNguoiHocNotInKhoaHocList() {
        nguoiHocNotInKhoaHoc.clear();
        List<Object[]> dataLoaded = new NguoiHocDaoImpl().selectNguoiHocNotInKhoaHoc(khoaHoc.getMaKh());
        for (Object[] objects : dataLoaded) {
            NguoiHoc nguoiHoc = new NguoiHoc();
            nguoiHoc.setMaNh(objects[0].toString());
            nguoiHoc.setHoTen(objects[1].toString());
            nguoiHocNotInKhoaHoc.add(nguoiHoc);
        }
    }

    private void loadDataToCboHocVienKhac() {
        cboHocVienKhac.removeAllItems();
        for (NguoiHoc nguoiHoc : nguoiHocNotInKhoaHoc) {
            cboHocVienKhac.addItem(nguoiHoc.getHoTen());
        }
        cboHocVienKhac.updateUI();
    }

    private void insertModel() {
        int selectedIndex = cboHocVienKhac.getSelectedIndex();
        NguoiHoc selectedNguoiHoc = nguoiHocNotInKhoaHoc.get(selectedIndex);
        NguoiHoc nguoiHoc = new NguoiHocDaoImpl().selectById(selectedNguoiHoc.getMaNh());

        boolean isInserted = false;
        HocVien hocVien = null;
        try {
            float diem = Float.parseFloat(txtDiem.getText());
            hocVien = new HocVien(khoaHoc, nguoiHoc, diem);

            isInserted = new HocVienDaoImpl().insert(hocVien);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isInserted) {
            nguoiHocNotInKhoaHoc.remove(selectedIndex);
            cboHocVienKhac.removeItemAt(selectedIndex);
            cboHocVienKhac.updateUI();

            try {
                Vector vData = DataFactoryUtil.objectToVectorByFields(hocVien, ObjectStructureHelper.HOCVIEN_PROPERTIES);
                String maNh = ((NguoiHoc) vData.get(1)).getMaNh();
                vData.set(1, maNh);

                String hoTen = ((NguoiHoc) vData.get(2)).getHoTen();
                vData.set(2, hoTen);

                vData.add(hocVien.getXepLoai());
                vData.add(false);

                tableData.add(vData);

                //                Add hocvien to hashmap
                hocVienHashMap.put(hocVien.getMaHv(), hocVien);
            } catch (Exception ex) {
                Logger.getLogger(HocVienJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            tblHocVien.updateUI();

            txtDiem.setText("");

            for (int i = 0; i < tableData.size(); i++) {
                if (tableData.get(i).get(0).equals(hocVien.getMaHv())) {
                    tblHocVien.setRowSelectionInterval(i, i);
                    tblHocVien.scrollRectToVisible(new Rectangle(tblHocVien.getCellRect(i, 0, true)));
                    break;
                }
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHocVien = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnCapNhat = new javax.swing.JButton();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoDaNhapDiem = new javax.swing.JRadioButton();
        rdoChuaNhapDiem = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtDiem = new javax.swing.JTextField();
        cboHocVienKhac = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(65, 76, 89));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("QUẢN LÝ HỌC VIÊN");
        jPanel3.add(jLabel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HỌC VIÊN TRONG KHOÁ HỌC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Open Sans", 1, 14))); // NOI18N
        jPanel1.setOpaque(false);

        tblHocVien.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblHocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã học viên", "Mã người học", "Họ và tên", "Điểm số", "Xếp loại", "Xoá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblHocVien.setGridColor(new java.awt.Color(204, 204, 204));
        tblHocVien.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblHocVien.setRowHeight(23);
        tblHocVien.setRowSorter(null);
        tblHocVien.setSelectionBackground(new java.awt.Color(65, 76, 89));
        tblHocVien.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblHocVien.setShowGrid(false);
        tblHocVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHocVienMouseReleased(evt);
            }
        });
        tblHocVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblHocVienKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblHocVien);

        jPanel7.setBackground(new java.awt.Color(245, 245, 245));

        btnCapNhat.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapNhat.setFocusTraversalKeysEnabled(false);
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        rdoTatCa.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoTatCa.setSelected(true);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTatCaActionPerformed(evt);
            }
        });

        rdoDaNhapDiem.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoDaNhapDiem.setText("Đã nhập điểm");
        rdoDaNhapDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaNhapDiemActionPerformed(evt);
            }
        });

        rdoChuaNhapDiem.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        rdoChuaNhapDiem.setText("Chưa nhập điểm");
        rdoChuaNhapDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChuaNhapDiemActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel13.setText("Hiển thị:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(rdoTatCa)
                .addGap(18, 18, 18)
                .addComponent(rdoDaNhapDiem)
                .addGap(18, 18, 18)
                .addComponent(rdoChuaNhapDiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoTatCa)
                    .addComponent(rdoDaNhapDiem)
                    .addComponent(rdoChuaNhapDiem)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HỌC VIÊN KHÁC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Open Sans", 1, 14))); // NOI18N

        txtDiem.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtDiem.setFocusTraversalKeysEnabled(false);
        txtDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDiemMouseClicked(evt);
            }
        });
        txtDiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiemKeyTyped(evt);
            }
        });

        cboHocVienKhac.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboHocVienKhac.setFocusTraversalKeysEnabled(false);
        cboHocVienKhac.setFocusable(false);

        jLabel11.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel11.setText("Điểm");

        jLabel12.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel12.setText("Họ và tên");

        btnThem.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.setFocusTraversalKeysEnabled(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboHocVienKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHocVienKhac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        tblHocVien.clearSelection();
        if (tblHocVien.isEditing()) {
            tblHocVien.getCellEditor().cancelCellEditing();
        }
        txtDiem.setText("");
    }//GEN-LAST:event_formWindowClosing

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (txtDiem.getText().equals("")) {
            DialogHelper.message(this, "Điểm không được để trống", DialogHelper.ERROR_MESSAGE);
        } else {
            this.insertModel();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        List<HocVien> deleteHocVien = new ArrayList<>();
        for (int i = 0; i < tableData.size(); i++) {
            Vector vData = tableData.get(i);
            if ((Boolean) vData.get(vData.size() - 1) == true) {
                deleteHocVien.add(hocVienHashMap.get(vData.get(0)));
            }
        }

        if (deleteHocVien.size() > 0) {
            boolean isConfirm = DialogHelper.confirm(this, "Bạn có muốn xoá những học viên đã chọn?");
            if (!isConfirm) {
                boolean isDeleted = true;
                for (HocVien hocVien : deleteHocVien) {
                    try {
                        new HocVienDaoImpl().delete(hocVien);
                    } catch (Exception e) {
                        isDeleted = false;
                    }
                }

                if (isDeleted) {
                    loadData();
                }else{
                    DialogHelper.message(this, "Quá trình xoá đã xảy ra lỗi", DialogHelper.ERROR_MESSAGE);
                }
            }
        } else {
            DialogHelper.message(this, "Vui lòng chọn học viên cần xoá", DialogHelper.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtDiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiemKeyTyped
        if (!String.valueOf(evt.getKeyChar()).matches("[\\d\\.]")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDiemKeyTyped

    private void tblHocVienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblHocVienKeyPressed
        evt.consume();
    }//GEN-LAST:event_tblHocVienKeyPressed

    private void tblHocVienMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHocVienMouseReleased
        if (evt.getClickCount() == 2) {
            if (tblHocVien.getSelectedColumn() != 3) {
                tblHocVien.getCellEditor().cancelCellEditing();
            }
        }
    }//GEN-LAST:event_tblHocVienMouseReleased

    private void rdoTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTatCaActionPerformed
        tableData.clear();
        for (HocVien hocVien : hocVienHashMap.values()) {
            try {
                Vector vData = DataFactoryUtil.objectToVectorByFields(hocVien, ObjectStructureHelper.HOCVIEN_PROPERTIES);
                String maNh = ((NguoiHoc) vData.get(1)).getMaNh();
                vData.set(1, maNh);

                String hoTen = ((NguoiHoc) vData.get(2)).getHoTen();
                vData.set(2, hoTen);

                vData.add(hocVien.getXepLoai());
                vData.add(false);

                tableData.add(vData);
            } catch (Exception ex) {
                Logger.getLogger(HocVienJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tblHocVien.updateUI();
    }//GEN-LAST:event_rdoTatCaActionPerformed

    private void rdoDaNhapDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaNhapDiemActionPerformed
        tableData.clear();
        for (HocVien hocVien : hocVienHashMap.values()) {
            if (hocVien.getDiem() < 0) {
                continue;
            }
            try {
                Vector vData = DataFactoryUtil.objectToVectorByFields(hocVien, ObjectStructureHelper.HOCVIEN_PROPERTIES);
                String maNh = ((NguoiHoc) vData.get(1)).getMaNh();
                vData.set(1, maNh);

                String hoTen = ((NguoiHoc) vData.get(2)).getHoTen();
                vData.set(2, hoTen);

                vData.add(hocVien.getXepLoai());
                vData.add(false);

                tableData.add(vData);
            } catch (Exception ex) {
                Logger.getLogger(HocVienJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tblHocVien.updateUI();
    }//GEN-LAST:event_rdoDaNhapDiemActionPerformed

    private void rdoChuaNhapDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChuaNhapDiemActionPerformed
        tableData.clear();
        for (HocVien hocVien : hocVienHashMap.values()) {
            if (hocVien.getDiem() >= 0) {
                continue;
            }
            try {
                Vector vData = DataFactoryUtil.objectToVectorByFields(hocVien, ObjectStructureHelper.HOCVIEN_PROPERTIES);
                String maNh = ((NguoiHoc) vData.get(1)).getMaNh();
                vData.set(1, maNh);

                String hoTen = ((NguoiHoc) vData.get(2)).getHoTen();
                vData.set(2, hoTen);

                vData.add(hocVien.getXepLoai());
                vData.add(false);

                tableData.add(vData);
            } catch (Exception ex) {
                Logger.getLogger(HocVienJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tblHocVien.updateUI();
    }//GEN-LAST:event_rdoChuaNhapDiemActionPerformed

    private void txtDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDiemMouseClicked
        tblHocVien.clearSelection();
        rdoTatCa.setSelected(true);
        rdoTatCaActionPerformed(null);
    }//GEN-LAST:event_txtDiemMouseClicked

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
            java.util.logging.Logger.getLogger(HocVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HocVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HocVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HocVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HocVienJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboHocVienKhac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoChuaNhapDiem;
    private javax.swing.JRadioButton rdoDaNhapDiem;
    private javax.swing.JRadioButton rdoTatCa;
    private javax.swing.JTable tblHocVien;
    private javax.swing.JTextField txtDiem;
    // End of variables declaration//GEN-END:variables
    ButtonGroup btnGroupFillter = new ButtonGroup();
}
