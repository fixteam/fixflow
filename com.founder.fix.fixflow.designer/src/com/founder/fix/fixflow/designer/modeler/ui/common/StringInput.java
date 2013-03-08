package com.founder.fix.fixflow.designer.modeler.ui.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;

public class StringInput extends PlatformObject implements IStorageEditorInput {

	private IStorage storage;
	private String model;

	public StringInput(String input,String model) {
		
		this.storage = new StringStorage(input);
		this.model=model;
		
	}

	public boolean exists() {
		return true;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return storage.getName();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public IStorage getStorage() {
		return storage;
	}

	public String getToolTipText() {
		return "String-based file: " + storage.getName();
	}
	
	public class StringStorage extends PlatformObject implements IStorage {

		private String string;

		public StringStorage(String input) {
			this.string = input;
		}

		public InputStream getContents() throws CoreException {
			return new ByteArrayInputStream(string.getBytes());
		}

		public IPath getFullPath() {
			return null;
		}

		public String getName() {
			
			return "1."+model;
		}

		public boolean isReadOnly() {
			return false;
		}

	}


}
