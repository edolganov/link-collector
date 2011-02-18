package ru.kc.main;

import java.io.File;

import ru.kc.common.Context;
import ru.kc.common.dialog.Dialogs;
import ru.kc.common.node.edit.NodeEditionsAggregator;
import ru.kc.common.node.event.ChildAdded;
import ru.kc.common.node.event.ChildDeletedRecursive;
import ru.kc.common.node.event.NodeUpdated;
import ru.kc.model.Node;
import ru.kc.platform.Platform;
import ru.kc.platform.app.App;
import ru.kc.platform.app.AppContext;
import ru.kc.platform.event.EventManager;
import ru.kc.platform.ui.tabbedform.MainForm;
import ru.kc.tools.filepersist.ServiceListener;
import ru.kc.tools.filepersist.impl.InitParams;
import ru.kc.tools.filepersist.impl.PersistServiceImpl;
import ru.kc.util.swing.laf.Laf;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		boolean useNimbusLaf = hasParam(args,"nimbus-laf");
		
		Laf.trySetSystemLookAndFeel();
		if(useNimbusLaf) Laf.trySetNimbusLookAndFeel();
		Laf.setupEnterActionForAllButtons();
		Laf.setupComboboxInputMap();
		
		File dataDir = new File("./data");
		File scriptDir = new File(dataDir,"scripts");
		File scriptsDevDir = new File("./client/main/script-src");
		File knowDir = new File(dataDir,"know");
		Context context = createContext(knowDir);
		String rootControllersPackage = "ru.kc.main";
		String globalPackagePreffix = "ru.kc";
		
		Platform.setDataDir(dataDir);
		Platform.enableLogFile();
		
		App app = Platform.createApp();
		app.setRootUI(new MainForm());
		app.addScriptsDevDir(scriptsDevDir);
		app.addScriptsProdactionDir(scriptDir);
		app.addRootControllersPackage(rootControllersPackage);
		app.addContextData(context);
		app.addGlobalObjectsPackagePreffix(globalPackagePreffix);
		app.addUIObjectHandler(new UIConfig());
		app.init();
		initPersistEvents(app,context);
		app.run();
		
	}



	private static boolean hasParam(String[] args, String value) {
		if(args == null) return false;
		for(String arg : args){
			if(arg.equals(value)) return true;
		}
		return false;
	}



	private static Context createContext(File knowDir) throws Exception {
		int maxNodesInContainer = 100;
		int maxContainerFilesInFolder = 100;
		int maxFoldersInLevel = 100;
		InitParams init = new InitParams(knowDir, maxNodesInContainer, maxContainerFilesInFolder, maxFoldersInLevel);
		PersistServiceImpl ps = new PersistServiceImpl();
		ps.init(init);
		
		NodeEditionsAggregator nodeEditionsAggregator = new NodeEditionsAggregator();
		Dialogs dialogs = new Dialogs();
		
		return new Context(ps,nodeEditionsAggregator,dialogs);
	}
	
	private static void initPersistEvents(App app, Context context) {
		AppContext appContext = app.getInitedContext();
		final EventManager eventManager = appContext.eventManager;
		context.persistService.addListener(new ServiceListener() {
			
			@Override
			public void onAdded(Node parent, Node child) {
				eventManager.fireEventInEDT(this,new ChildAdded(parent, child));
			}
			
			@Override
			public void onDeletedRecursive(Node parent, Node deletedChild) {
				eventManager.fireEventInEDT(this, new ChildDeletedRecursive(parent, deletedChild));
			}
			
			@Override
			public void onNodeUpdated(Node old, Node node) {
				eventManager.fireEventInEDT(this, new NodeUpdated(old, node));
			}
		});
		
		context.nodeEditionsAggregator.init(appContext);
		
		context.dialogs.init(appContext.componentScanner);
	}

}
