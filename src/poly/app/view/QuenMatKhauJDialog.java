/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.view;

import poly.app.core.dao.MaXacNhanDao;
import poly.app.core.daoimpl.MaXacNhanImpl;
import poly.app.core.daoimpl.NhanVienDaoImpl;
import poly.app.core.entities.MaXacNhan;
import poly.app.core.helper.DialogHelper;
import poly.app.core.helper.ShareHelper;
import poly.app.core.utils.EMailUtil;
import poly.app.core.utils.StringUtil;

/**
 *
 * @author vothanhtai
 */
public class QuenMatKhauJDialog extends javax.swing.JDialog {
    /**
     * Creates new form QuenMatKhau
     */
    public QuenMatKhauJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.getRootPane().setDefaultButton(btnXacNhan);
    }

    private boolean luuMaXacNhan(String maXacNhan) {
        MaXacNhanDao maXacNhanDao = new MaXacNhanImpl();
        MaXacNhan entity = new MaXacNhan(ShareHelper.USER, maXacNhan);
        return maXacNhanDao.insert(entity);
    }

    private boolean guiMaXacNhan(String maXacNhan) {
        String email = ShareHelper.USER.getEmail();
        String msgSubject = "Khôi phục mật khẩu";
        String msgBody = "<h2 style='color: #B93B2D'>Xin chào!<br>Hãy dùng mã xác nhận dưới đây để khôi phục lại mật khẩu.</h2>"
                + "<br>Mã nhân viên: " + ShareHelper.USER.getMaNv()
                + "<br>Mã xác nhận: <b>" + maXacNhan
                + "</b><br>Hãy sử dụng mã xác nhận trên để tiến hình khôi phục mật khẩu.";
        return new EMailUtil(email, msgBody, msgSubject).sendMail();
    }
    
    private boolean checkMaXacNhan(String maXacNhan){
        return new MaXacNhanImpl().getById(ShareHelper.USER.getMaNv()).getMaXacNhan().equals(maXacNhan);
    }
    
    private boolean xoaMaXacNhan(){
        return new MaXacNhanImpl().deleteById(ShareHelper.USER.getMaNv());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnXacNhan = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(254, 203, 101));

        jPanel2.setOpaque(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/app/view/icon/secure-icon-png-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
        );

        jPanel3.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Open Sans", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(9, 90, 11));
        jLabel2.setText("Quên mật khẩu");

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLabel3.setText("Email liên hệ");

        txtEmail.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N

        btnXacNhan.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        btnCancel.setText("Thoát");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(239, 41, 47));
        jLabel4.setText("<html>Mã xác nhận sẽ được gửi đến<br>email của bạn");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtEmail))
                .addGap(15, 15, 15))
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        String email = txtEmail.getText();
        ShareHelper.USER = new NhanVienDaoImpl().getNhanVienByEmail(email);
        if (ShareHelper.USER == null) {
            DialogHelper.message(this, "Không tồn tại nhân viên có email: " + email, DialogHelper.ERROR_MESSAGE);
        } else {
            String randdomMaXacNhan = StringUtil.randomString();
            boolean isSent = this.guiMaXacNhan(randdomMaXacNhan);
            if (isSent) {
                DialogHelper.message(this, "Đã gửi mã xác nhận\nVui lòng nhập mã xác nhận và tiến hành đổi mật khẩu", DialogHelper.INFORMATION_MESSAGE);

                boolean isSaved = this.luuMaXacNhan(randdomMaXacNhan);
                if (isSaved) {
                    String inputMaXacNhan;
                    boolean isCorrectMaXacNhan = false;
                    do {
                        inputMaXacNhan = DialogHelper.prompt(this, "Nhập mã xác nhận");
                        isCorrectMaXacNhan = checkMaXacNhan(inputMaXacNhan);
                        if (inputMaXacNhan == null || isCorrectMaXacNhan) {
                            break;
                        }else{
                            DialogHelper.message(this, "Mã xác nhận không đúng!\nVui lòng kiểm tra lại", DialogHelper.ERROR_MESSAGE);
                        }
                    } while (true);
                    
                    if(isCorrectMaXacNhan){
                        this.dispose();
                        new DatLaiMatKhau(null, true).setVisible(true);
                    }else{
                        DialogHelper.message(this, "Mã xác nhận đã hết hạn!\nVui lòng nhận lại mã mới", DialogHelper.ERROR_MESSAGE);
                    }
                    
                    this.xoaMaXacNhan();
                }else{
                    DialogHelper.message(this, "Đã xảy ra lỗi!\nVui lòng thử lại sau", DialogHelper.ERROR_MESSAGE);
                }
                
            } else {
                DialogHelper.message(this, "Gửi mã xác nhận thất bại\nVui lòng kiểm tra lại email", DialogHelper.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(QuenMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhauJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuenMatKhauJDialog dialog = new QuenMatKhauJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}
