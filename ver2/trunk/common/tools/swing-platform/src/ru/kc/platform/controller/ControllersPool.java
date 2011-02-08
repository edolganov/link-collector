package ru.kc.platform.controller;

import java.util.HashMap;
import java.util.Iterator;

public class ControllersPool implements Iterable<AbstractController<?>>{
	
	HashMap<Class<?>, AbstractController<?>> map = new HashMap<Class<?>, AbstractController<?>>();
	
	public void add(AbstractController<?> c){
		map.put(c.getClass(), c);
	}
	
	public void addAll(ControllersPool otherPool){
		if(this == otherPool) return;
		else map.putAll(otherPool.map);
	}

	@Override
	public Iterator<AbstractController<?>> iterator() {
		return map.values().iterator();
	}
	
	public AbstractController<?> getController(Class<?> type){
		AbstractController<?> out = map.get(type);
		if(out == null) throw new IllegalStateException("no controller by "+type);
		return out;
		
	}

	public int size() {
		return map.size();
	}

}
