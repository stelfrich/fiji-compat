package fiji.gui;

import java.awt.EventQueue;

/**
 * This class helps with invoking tasks much, much later.
 *
 * {@link EventQueue#invokeLater(Runnable)} works fine if something needs to
 * run after all currently queued tasks have been run. However, sometimes
 * these tasks run other tasks which run yet other tasks and we might want
 * to have them run first, before invoking our task.
 *
 * By queuing the same task a predetermined number of times, we achieve this.
 *
 * @author Johannes Schindelin
 */
public class InvokeLater implements Runnable {
	private int count;
	private final Runnable runnable;

	/**
	 * The constructor.
	 *
	 * @param count the number of times to queue the task before
	 *        actually invoking it.
	 * @param runnable the task.
	 */
	public InvokeLater(final int count, final Runnable runnable) {
		this.count = count;
		this.runnable = runnable;
	}

	@Override
	public void run() {
		if (--count <= 0) {
			runnable.run();
		} else {
			EventQueue.invokeLater(this);
		}
	}
}
