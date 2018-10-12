package poly.app.view.utils;

import javax.swing.JLabel;

public class TooltipUtil {
    public static void hideAllTooltips(JLabel[] tooltips) {
        for (JLabel tooltip : tooltips) {
            tooltip.setVisible(false);
            tooltip.validate();
            tooltip.repaint();
        }
    }
    
    public static  boolean isHideAllTooltips(JLabel[] tooltips) {
        for (JLabel tooltip : tooltips) {
            if (tooltip.isVisible()) {
                return false;
            }
        }
        return true;
    }

    public static  void showTooltip(JLabel tootip, String msg) {
        tootip.setText(msg);
        tootip.setVisible(true);
        tootip.validate();
        tootip.repaint();
    }
    
    public static  void hideTooltip(JLabel tootip) {
        tootip.setVisible(false);
        tootip.validate();
        tootip.repaint();
    }
}