/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.view;

import java.awt.Rectangle;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.hibernate.exception.ConstraintViolationException;
import poly.app.core.daoimpl.ChuyenDeDaoImpl;
import poly.app.core.entities.ChuyenDe;
import poly.app.core.helper.ObjectStructureHelper;
import poly.app.core.helper.DialogHelper;
import poly.app.core.helper.URLHelper;
import poly.app.core.utils.DataFactoryUtil;
import poly.app.core.utils.ImageUtil;
import poly.app.view.utils.TableRenderer;

public class ChuyenDeJFrame extends javax.swing.JFrame {

    Vector<Vector> tableData = new Vector<>();
    HashMap<String, ChuyenDe> chuyenDeHashMap = new HashMap<>();
    int selectedIndex = -1;
    boolean isDataLoaded = false;
    JFileChooser jFileChooser;

    public ChuyenDeJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        reRenderUI();
    }

    private void reRenderUI() {
        //        TBL CHUYEN DE
        TableRenderer tblRenderer1 = new TableRenderer(tblChuyenDe);
        tblRenderer1.setCellEditable(false);
        tblRenderer1.setDataVector(tableData, ObjectStructureHelper.CHUYENDE_TABLE_IDENTIFIERS);
        tblRenderer1.changeHeaderStyle();
        tblRenderer1.setColumnAlignment(0, TableRenderer.CELL_ALIGN_LEFT);
        tblRenderer1.setColumnAlignment(1, TableRenderer.CELL_ALIGN_LEFT);
        tblRenderer1.setColumnAlignment(2, TableRenderer.CELL_ALIGN_LEFT);
        tblRenderer1.setColumnAlignment(3, TableRenderer.CELL_ALIGN_LEFT);
        tblRenderer1.setColumnAlignment(4, TableRenderer.CELL_ALIGN_LEFT);
        tblRenderer1.setColoumnWidthByPersent(1, 60);

//        Add data to combobox
        for (String identifier : ObjectStructureHelper.CHUYENDE_TABLE_IDENTIFIERS) {
            cboBoLoc.addItem(identifier);
        }
        cboBoLoc.setSelectedIndex(1);

//        hide tooltip
        JLabel[] tooltips = new JLabel[]{tooltipMa, tooltipTen, tooltipHocPhi, tooltipThoiLuong, tooltipMoTa};
        for (JLabel tooltip : tooltips) {
            tooltip.setVisible(false);
            tooltip.validate();
            tooltip.repaint();
        }
    }

    public void loadDataToTable() {
        tableData.clear();
        chuyenDeHashMap.clear();
        List<ChuyenDe> dataLoadedList = new ChuyenDeDaoImpl().selectAll();

        try {
            for (ChuyenDe chuyenDe : dataLoadedList) {
//                Convert chuyende to vector
                Vector vData = DataFactoryUtil.objectToVectorByFields(chuyenDe, ObjectStructureHelper.CHUYENDE_PROPERTIES);
                tableData.add(vData);

//                Add chuyende to hashmap
                chuyenDeHashMap.put(chuyenDe.getMaCd(), chuyenDe);
            }
            tblChuyenDe.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(ChuyenDeJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        isDataLoaded = true;
    }

    private void changeSelectedIndex() {
        if (selectedIndex >= 0) {
            tblChuyenDe.setRowSelectionInterval(selectedIndex, selectedIndex);
            tblChuyenDe.scrollRectToVisible(new Rectangle(tblChuyenDe.getCellRect(selectedIndex, 0, true)));
            setModelToForm();
        } else {
            resetForm();
        }
    }

    private void setModelToForm() {
        if (selectedIndex == -1) {
            return;
        }
//        get ma nhan vien by selected index
        String maKh = tblChuyenDe.getValueAt(selectedIndex, 0).toString();
        ChuyenDe selectedChuyenDe = chuyenDeHashMap.get(maKh);
        txtMaChuyenDe.setText(selectedChuyenDe.getMaCd());
        txtTenChuyenDe.setText(selectedChuyenDe.getTenCd());
        txtThoiLuong.setText(selectedChuyenDe.getThoiLuong() + "");
        txtHocPhi.setText(selectedChuyenDe.getHocPhi() + "");
        txtMoTa.setText(selectedChuyenDe.getMoTa());

        File avatarFile = new File(URLHelper.URL_CHUYENDE_IMAGE + selectedChuyenDe.getHinh());
        if (!avatarFile.exists() || avatarFile.isDirectory()) {
            lblAvatar.setIcon(null);
        } else {
            lblAvatar.setIcon(ImageUtil.resizeImage(avatarFile, lblAvatar.getWidth(), lblAvatar.getHeight()));
        }
    }

    private void resetForm() {
        txtMaChuyenDe.setText("");
        txtTenChuyenDe.setText("");
        txtThoiLuong.setText("");
        txtHocPhi.setText("");
        txtMoTa.setText("");

        lblAvatar.setIcon(null);
        txtMaChuyenDe.requestFocus();
    }

    private void addModelToTable(ChuyenDe chuyenDe) {
        Vector vData = new Vector();
        vData.add(chuyenDe.getMaCd());
        vData.add(chuyenDe.getTenCd());
        vData.add(chuyenDe.getHocPhi());
        vData.add(chuyenDe.getThoiLuong());
        vData.add(chuyenDe.getHinh());

        tableData.add(vData);
    }

    private ChuyenDe getModelFromForm() {
        ChuyenDe chuyenDe = new ChuyenDe();
        chuyenDe.setMaCd(txtMaChuyenDe.getText());
        chuyenDe.setTenCd(txtTenChuyenDe.getText());
        chuyenDe.setThoiLuong(Integer.parseInt(txtThoiLuong.getText()));
        chuyenDe.setHocPhi(Integer.parseInt(txtHocPhi.getText()));
        chuyenDe.setMoTa(txtMoTa.getText());

        return chuyenDe;
    }

    

    private boolean validateInputAddingState() {
        JLabel[] tooltips = new JLabel[]{tooltipMa, tooltipTen, tooltipHocPhi, tooltipThoiLuong, tooltipMoTa};
        for (JLabel tooltip : tooltips) {
            if (tooltip.isVisible()) {
                return false;
            }
        }
        return true;
    }

    private boolean validateInputEditingState() {
        JLabel[] tooltips = new JLabel[]{tooltipMa, tooltipTen, tooltipHocPhi, tooltipThoiLuong, tooltipMoTa};
        for (JLabel tooltip : tooltips) {
            if (tooltip.isVisible()) {
                return false;
            }
        }
        return true;
    }

    private void setAddingState() {
        txtMaChuyenDe.setEditable(true);
        txtMaChuyenDe.requestFocus();
        btnThem.setEnabled(true);
        btnLamMoi.setEnabled(true);
        btnCapNhat.setEnabled(false);
        btnXoa.setEnabled(false);

        btnLast.setEnabled(false);
        btnPrev.setEnabled(false);
        btnFirst.setEnabled(false);
        btnNext.setEnabled(false);
    }

    private void setEditingState() {
        txtMaChuyenDe.setEditable(false);
        btnThem.setEnabled(false);
        btnLamMoi.setEnabled(true);
        btnCapNhat.setEnabled(true);
        btnXoa.setEnabled(true);
    }

    private void insertModel() {
        if (validateInputAddingState()) {
            ChuyenDe chuyenDe = getModelFromForm();

//            Save image to folder
            try {
                if (jFileChooser.getSelectedFile() != null) {
                    chuyenDe.setHinh(chuyenDe.getMaCd().concat(".jpg"));

                    if (chuyenDe.getHinh() != null) {
                        ImageUtil.deleteImage(URLHelper.URL_CHUYENDE_IMAGE, chuyenDe.getHinh());
                    }
                    ImageUtil.saveImage(URLHelper.URL_CHUYENDE_IMAGE, chuyenDe.getHinh(), jFileChooser.getSelectedFile());
                    jFileChooser = null;
                }
            } catch (Exception ex) {
//                ex.printStackTrace();
            }

            boolean isInserted = new ChuyenDeDaoImpl().insert(chuyenDe);

            if (isInserted) {
                DialogHelper.message(this, "Thêm dữ liệu thành công.", DialogHelper.INFORMATION_MESSAGE);
                loadDataToTable();
                setEditingState();
                hideAllTooltips();

                for (int i = 0; i < tableData.size(); i++) {
                    if (tableData.get(i).get(0).equals(txtMaChuyenDe.getText())) {
                        selectedIndex = i;
                        changeSelectedIndex();
                        setDirectionButton();
                        break;
                    }
                }
            } else {
                DialogHelper.message(this, "Thêm dữ liệu thất bại.", DialogHelper.ERROR_MESSAGE);
            }
        }
    }

    private void deleteModel() {
        boolean isConfirm = DialogHelper.confirm(this, "Bạn chắc chắn muốn xoá?");
        if (isConfirm) {
            String maKh = tblChuyenDe.getValueAt(selectedIndex, 0).toString();
            ChuyenDe chuyenDe = chuyenDeHashMap.get(maKh);

            boolean isDeleted = false;
            try {
                isDeleted = new ChuyenDeDaoImpl().delete(chuyenDe);
            } catch (ConstraintViolationException ex) {
                DialogHelper.message(this, "Không thể xoá chuyên đề có chứa khoá học", DialogHelper.ERROR_MESSAGE);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            if (isDeleted) {
                DialogHelper.message(this, "Xoá dữ liệu thành công.", DialogHelper.INFORMATION_MESSAGE);
                loadDataToTable();
                tblChuyenDe.updateUI();
                changeSelectedIndex();
            } else {
                DialogHelper.message(this, "Xoá dữ liệu thất bại.", DialogHelper.ERROR_MESSAGE);
            }
        }
    }

    private void updateModel() {
        if (validateInputEditingState()) {
            String maKh = tblChuyenDe.getValueAt(selectedIndex, 0).toString();
            ChuyenDe chuyenDeOldData = chuyenDeHashMap.get(maKh);

            ChuyenDe chuyenDeNewData = getModelFromForm();

//            Save image to folder
            try {
                if (jFileChooser.getSelectedFile() != null) {
                    chuyenDeNewData.setHinh(chuyenDeNewData.getMaCd().concat(".jpg"));

                    if (chuyenDeOldData.getHinh() != null) {
                        ImageUtil.deleteImage(URLHelper.URL_CHUYENDE_IMAGE, chuyenDeOldData.getHinh());
                    }
                    ImageUtil.saveImage(URLHelper.URL_CHUYENDE_IMAGE, chuyenDeNewData.getHinh(), jFileChooser.getSelectedFile());
                    jFileChooser = null;
                }
            } catch (Exception ex) {
//                ex.printStackTrace();
            }

            boolean isUpdated = false;
            try {
                chuyenDeNewData = DataFactoryUtil.mergeTwoObject(chuyenDeOldData, chuyenDeNewData);
                chuyenDeHashMap.put(maKh, chuyenDeNewData);

                isUpdated = new ChuyenDeDaoImpl().update(chuyenDeNewData);
            } catch (Exception ex) {
                Logger.getLogger(ChuyenDeJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (isUpdated) {
                Vector vData;
                try {
                    vData = DataFactoryUtil.objectToVectorByFields(chuyenDeNewData, ObjectStructureHelper.CHUYENDE_PROPERTIES);

//                    Find index of updated ChuyenDe in tabledata
                    for (int i = 0; i < tableData.size(); i++) {
                        if (tableData.get(i).get(0).equals(vData.get(0))) {
                            tableData.set(i, vData);
                            break;
                        }
                    }

                    tblChuyenDe.updateUI();
                    DialogHelper.message(this, "Cập nhật dữ liệu thành công.", DialogHelper.INFORMATION_MESSAGE);
                    hideAllTooltips();
                } catch (Exception ex) {
                    Logger.getLogger(ChuyenDeJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                DialogHelper.message(this, "Cập nhật dữ liệu thất bại.", DialogHelper.ERROR_MESSAGE);
            }
        }
    }

    private void setDirectionButton() {
        if (tableData.size() > 0 || selectedIndex != -1) {
            if (selectedIndex == 0) {
                btnFirst.setEnabled(false);
                btnPrev.setEnabled(false);
            }

            if (selectedIndex > 0) {
                btnFirst.setEnabled(true);
                btnPrev.setEnabled(true);
            }

            if (selectedIndex == tableData.size() - 1) {
                btnLast.setEnabled(false);
                btnNext.setEnabled(false);
            }

            if (selectedIndex < tableData.size() - 1) {
                btnLast.setEnabled(true);
                btnNext.setEnabled(true);
            }
        } else {
            btnFirst.setEnabled(false);
            btnPrev.setEnabled(false);
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
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
        tblChuyenDe = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtMaChuyenDe = new javax.swing.JTextField();
        txtTenChuyenDe = new javax.swing.JTextField();
        txtThoiLuong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAvatar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        tooltipTen = new javax.swing.JLabel();
        tooltipThoiLuong = new javax.swing.JLabel();
        tooltipHocPhi = new javax.swing.JLabel();
        tooltipMa = new javax.swing.JLabel();
        tooltipMoTa = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        cboBoLoc = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(65, 76, 89));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("QUẢN LÝ CHUYÊN ĐỀ");
        jPanel3.add(jLabel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        panelTab.setBackground(new java.awt.Color(255, 255, 255));
        panelTab.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        panelTab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                panelTabStateChanged(evt);
            }
        });

        tblChuyenDe.setAutoCreateRowSorter(true);
        tblChuyenDe.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tblChuyenDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblChuyenDe.setGridColor(new java.awt.Color(204, 204, 204));
        tblChuyenDe.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblChuyenDe.setRowHeight(23);
        tblChuyenDe.setSelectionBackground(new java.awt.Color(65, 76, 89));
        tblChuyenDe.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblChuyenDe.setShowGrid(false);
        tblChuyenDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChuyenDeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChuyenDe);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );

        panelTab.addTab("Danh Sách", jPanel2);

        jPanel5.setFocusTraversalKeysEnabled(false);
        jPanel5.setFocusable(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(408, 390));

        txtMaChuyenDe.setEditable(false);
        txtMaChuyenDe.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtMaChuyenDe.setFocusTraversalKeysEnabled(false);
        txtMaChuyenDe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaChuyenDeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaChuyenDeFocusLost(evt);
            }
        });

        txtTenChuyenDe.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtTenChuyenDe.setFocusTraversalKeysEnabled(false);
        txtTenChuyenDe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenChuyenDeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenChuyenDeFocusLost(evt);
            }
        });
        txtTenChuyenDe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTenChuyenDeKeyTyped(evt);
            }
        });

        txtThoiLuong.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtThoiLuong.setFocusTraversalKeysEnabled(false);
        txtThoiLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtThoiLuongFocusLost(evt);
            }
        });
        txtThoiLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtThoiLuongKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel5.setText("Tên chuyên đề");

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel2.setText("Mã chuyên đề");

        jLabel4.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel4.setText("Thời lượng (giờ)");

        lblAvatar.setBackground(new java.awt.Color(255, 255, 255));
        lblAvatar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        lblAvatar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAvatar.setOpaque(true);
        lblAvatar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAvatarMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel6.setText("Học phí");

        txtHocPhi.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtHocPhi.setFocusTraversalKeysEnabled(false);

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel7.setText("Mô tả chuyên đề");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        tooltipTen.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        tooltipTen.setForeground(new java.awt.Color(255, 0, 51));
        tooltipTen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tooltipTen.setText("xxx");

        tooltipThoiLuong.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        tooltipThoiLuong.setForeground(new java.awt.Color(255, 0, 51));
        tooltipThoiLuong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tooltipThoiLuong.setText("xxx");

        tooltipHocPhi.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        tooltipHocPhi.setForeground(new java.awt.Color(255, 0, 51));
        tooltipHocPhi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tooltipHocPhi.setText("xxx");

        tooltipMa.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        tooltipMa.setForeground(new java.awt.Color(255, 0, 51));
        tooltipMa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tooltipMa.setText("xxx");

        tooltipMoTa.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        tooltipMoTa.setForeground(new java.awt.Color(255, 0, 51));
        tooltipMoTa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tooltipMoTa.setText("xxx");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tooltipMa, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtMaChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tooltipTen, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtTenChuyenDe)
                                .addComponent(txtHocPhi)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tooltipThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tooltipHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(tooltipMoTa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tooltipTen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tooltipThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tooltipHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tooltipMa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tooltipMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(245, 245, 245));

        btnThem.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.setFocusTraversalKeysEnabled(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapNhat.setEnabled(false);
        btnCapNhat.setFocusTraversalKeysEnabled(false);
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.setEnabled(false);
        btnXoa.setFocusTraversalKeysEnabled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLamMoi.setFocusTraversalKeysEnabled(false);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnFirst.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        btnFirst.setText("I<");
        btnFirst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFirst.setFocusTraversalKeysEnabled(false);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        btnPrev.setText("<<");
        btnPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrev.setFocusTraversalKeysEnabled(false);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        btnNext.setText(">>");
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.setFocusTraversalKeysEnabled(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        btnLast.setText(">I");
        btnLast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLast.setFocusTraversalKeysEnabled(false);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelTab.addTab("Thông Tin", jPanel1);

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
            .addGap(0, 498, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelTab, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TÌM KIẾM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Open Sans", 1, 14))); // NOI18N

        txtTimKiem.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtTimKiem.setFocusTraversalKeysEnabled(false);
        txtTimKiem.setFocusable(false);
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        cboBoLoc.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        cboBoLoc.setFocusTraversalKeysEnabled(false);
        cboBoLoc.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("Thuộc tính:");

        jLabel11.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel11.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboBoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        selectedIndex = -1;
        tblChuyenDe.clearSelection();
        resetForm();
        setAddingState();
        hideAllTooltips();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.insertModel();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.deleteModel();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblChuyenDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChuyenDeMouseClicked
        selectedIndex = tblChuyenDe.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {
            if (selectedIndex >= 0) {
                panelTab.setSelectedIndex(1);
                setDirectionButton();
            }
        }
    }//GEN-LAST:event_tblChuyenDeMouseClicked

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        this.updateModel();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        setEditingState();
        if (tableData.size() > 0) {
            selectedIndex = 0;
            changeSelectedIndex();
        }
        setDirectionButton();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        setEditingState();
        if (tableData.size() > 0) {
            selectedIndex = tableData.size() - 1;
            changeSelectedIndex();
        }

        setDirectionButton();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        setEditingState();
        if (selectedIndex > 0) {
            selectedIndex--;
            changeSelectedIndex();
        }

        setDirectionButton();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        setEditingState();
        if (selectedIndex < tableData.size() - 1) {
            selectedIndex++;
            changeSelectedIndex();
        }

        setDirectionButton();
    }//GEN-LAST:event_btnNextActionPerformed

    private void panelTabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_panelTabStateChanged
        if (evt != null && isDataLoaded) {
            selectedIndex = tblChuyenDe.getSelectedRow();
            if (selectedIndex == -1) {
                resetForm();
                setAddingState();
            } else if (panelTab.getSelectedIndex() == 1) {
                setEditingState();
                setModelToForm();
                txtTimKiem.setFocusable(false);
                requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_panelTabStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        tblChuyenDe.clearSelection();
        selectedIndex = tblChuyenDe.getSelectedRow();
        resetForm();
        setAddingState();
        panelTab.setSelectedIndex(0);
        txtTimKiem.setText("");
        txtTimKiem.setRequestFocusEnabled(false);
    }//GEN-LAST:event_formWindowClosing

    private void txtTenChuyenDeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenChuyenDeKeyTyped
        if (String.valueOf(evt.getKeyChar()).matches("\\d")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTenChuyenDeKeyTyped

    private void txtThoiLuongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThoiLuongKeyTyped
        if (!String.valueOf(evt.getKeyChar()).matches("[\\d]")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtThoiLuongKeyTyped

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        selectedIndex = -1;
        tblChuyenDe.clearSelection();
        txtTimKiem.setFocusable(true);
        txtTimKiem.requestFocus();
        panelTab.setSelectedIndex(0);
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        selectedIndex = -1;
        tblChuyenDe.clearSelection();
        tblChuyenDe.getRowSorter().setSortKeys(null);
        if (evt != null && isDataLoaded) {
            tableData.clear();
            int cboIndex = cboBoLoc.getSelectedIndex();
            String fieldName = ObjectStructureHelper.CHUYENDE_PROPERTIES[cboIndex];
            for (ChuyenDe chuyenDe : chuyenDeHashMap.values()) {
                Object dataFromField = DataFactoryUtil.getValueByField(chuyenDe, fieldName);

                String strDataFromField = dataFromField.toString().toLowerCase();
                String timKiemString = txtTimKiem.getText().toLowerCase();

                if (strDataFromField.contains(timKiemString)) {
                    try {
                        addModelToTable(chuyenDe);
                    } catch (Exception ex) {
                        Logger.getLogger(ChuyenDeJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            tblChuyenDe.updateUI();
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void lblAvatarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAvatarMouseClicked
        jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpeg", "jpg", "png", "gif");
        jFileChooser.setFileFilter(filter);
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                lblAvatar.setIcon(ImageUtil.resizeImage(jFileChooser.getSelectedFile(), lblAvatar.getWidth(), lblAvatar.getHeight()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_lblAvatarMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        TODO: Clear event when release
        loadDataToTable();
    }//GEN-LAST:event_formWindowOpened

    private void txtMaChuyenDeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaChuyenDeFocusLost
        if (txtMaChuyenDe.getText().length() == 0) {
            showTooltip(tooltipMa, "Không được để trống");
        }

        for (ChuyenDe chuyenDe : chuyenDeHashMap.values()) {
            if (chuyenDe.getMaCd().equals(txtMaChuyenDe.getText())) {
                showTooltip(tooltipMa, "Mã chuyên đề đã tồn tại");
            }
        }
    }//GEN-LAST:event_txtMaChuyenDeFocusLost

    private void txtTenChuyenDeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenChuyenDeFocusLost
        if (txtTenChuyenDe.getText().length() == 0) {
            showTooltip(tooltipTen, "Không được để trống");
        }
    }//GEN-LAST:event_txtTenChuyenDeFocusLost

    private void txtThoiLuongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtThoiLuongFocusLost
        
    }//GEN-LAST:event_txtThoiLuongFocusLost

    private void txtMaChuyenDeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaChuyenDeFocusGained
        hideTooltip(tooltipMa);
    }//GEN-LAST:event_txtMaChuyenDeFocusGained

    private void txtTenChuyenDeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenChuyenDeFocusGained
        hideTooltip(tooltipTen);
    }//GEN-LAST:event_txtTenChuyenDeFocusGained

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
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChuyenDeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboBoLoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JTabbedPane panelTab;
    private javax.swing.JTable tblChuyenDe;
    private javax.swing.JLabel tooltipHocPhi;
    private javax.swing.JLabel tooltipMa;
    private javax.swing.JLabel tooltipMoTa;
    private javax.swing.JLabel tooltipTen;
    private javax.swing.JLabel tooltipThoiLuong;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtMaChuyenDe;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenChuyenDe;
    private javax.swing.JTextField txtThoiLuong;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
