package ru.kc.tools.filepersist.impl;

import ru.kc.tools.filepersist.persist.FileSystemImpl;

public class Context {
	
	public final InitContextExt init;
	public final FactoryImpl factory;
	public final TreeImpl tree;
	public final FileSystemImpl fs;
	public final UpdaterImpl updater;
	public final Listeners listeners;
	public final Converter converter;
	public final TextServiceImpl textService;
	public final SnapshotServiceImpl snapshotService;
	
	public Context(InitContextExt init, FactoryImpl factory, TreeImpl tree,
			FileSystemImpl fs, UpdaterImpl updater, TextServiceImpl textService, SnapshotServiceImpl snapshotService) {
		super();
		this.init = init;
		this.factory = factory;
		this.tree = tree;
		this.fs = fs;
		this.updater = updater;
		this.listeners = new Listeners();
		this.converter = new Converter();
		this.textService = textService;
		this.snapshotService = snapshotService;
	}
	

	
	

	



}
