import javax.swing.ToolTipManager;

public class Test {
  public static void main(String[] argv) throws Exception {
    // Get current delay
    int initialDelay = ToolTipManager.sharedInstance().getInitialDelay();

    // Show tool tips immediately
    ToolTipManager.sharedInstance().setInitialDelay(0);

    // Show tool tips after a second
    initialDelay = 10000;
    ToolTipManager.sharedInstance().setInitialDelay(initialDelay);
  }
}