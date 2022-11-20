package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public interface Settable {
	public static final GridBagLayout gbLayout = new GridBagLayout();
	public static final GridBagConstraints gbConstraints = new GridBagConstraints();

	void gridBagMake(Component c, int x, int y, int w, int h);

}
