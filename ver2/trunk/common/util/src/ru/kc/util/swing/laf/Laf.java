package ru.kc.util.swing.laf;

import java.awt.Color;

import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Laf {
	
	public static void trySetSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static void trySetNimbusLookAndFeel() {
		try {
			//UIManager.put("control", new Color(212, 208, 200));
//			UIManager.put("nimbusBase", new Color(129, 136, 143));
////			UIManager.put("nimbusBase", new Color(115, 163, 212));
//			UIManager.put("nimbusFocus", new Color(28, 28, 31));
//			UIManager.put("nimbusInfoBlue", new Color(28, 30, 33));
////			UIManager.put("nimbusSelectionBackground", new Color(61, 63, 68));
			//UIManager.put("nimbusSelectionBackground", new Color(10, 35, 107));
	        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	            if ("Nimbus".equals(info.getName())) {
	            	UIManager.setLookAndFeel(info.getClassName());
	            	break;
	            }
	        }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static void trySetSeaGlassLookAndFeel() {
		try {
		    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static void trySetJTattooLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
    public static void setupEnterActionForAllButtons() {
        InputMap im = (InputMap) UIManager.getDefaults().get("Button.focusInputMap");
        Object pressedAction = im.get(KeyStroke.getKeyStroke("pressed SPACE"));
        Object releasedAction = im.get(KeyStroke.getKeyStroke("released SPACE"));

        im.put(KeyStroke.getKeyStroke("pressed ENTER"), pressedAction);
        im.put(KeyStroke.getKeyStroke("released ENTER"), releasedAction);
    }
    
    public static void setupComboboxInputMap(){
    	InputMap im = (InputMap) UIManager.get("ComboBox.ancestorInputMap");

        im.put(KeyStroke.getKeyStroke("pressed DOWN"), "selectNext");
        im.put(KeyStroke.getKeyStroke("pressed KP_DOWN"), "selectNext");
        im.put(KeyStroke.getKeyStroke("pressed UP"), "selectPrevious");
        im.put(KeyStroke.getKeyStroke("pressed KP_UP"), "selectPrevious");
    }
    
    //exp: "ComboBox.ancestorInputMap"
    public static void printInputMap(String id){
    	InputMap im = (InputMap) UIManager.get(id);
    	for(KeyStroke ks : im.keys()){
			Object object = im.get(ks);
			System.out.println(ks + " - " + object);
    	}
    }

}
