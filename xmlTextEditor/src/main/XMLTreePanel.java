package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.w3c.dom.Document;

public class XMLTreePanel extends JTree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 993681218955509549L;
	
	private JTree tree;
	private XMLTreeModel model;
	
	public XMLTreePanel() {
		setLayout(new BorderLayout());
		
		model = new XMLTreeModel();
		tree = new JTree();
		tree.setModel(model);
		tree.setShowsRootHandles(true);
		tree.setEditable(false);
		
		JScrollPane pane = new JScrollPane(tree);
		pane.setPreferredSize(new Dimension(300,400));

		add(pane, "Center");
		
		final JTextField text = new JTextField();
		text.setEditable(false);
		add(text, "South");
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				Object lpc = e.getPath().getLastPathComponent();
				if (lpc instanceof XMLTreeNode) {
					text.setText( ((XMLTreeNode)lpc).getText() );
				}
			}
		});
		
	}
	
	/* methods that delegate to the custom model */
	public void setDocument(Document document) {
		model.setDocument(document);
	}
	public Document getDocument() {
		return model.getDocument();
	}
	
}
