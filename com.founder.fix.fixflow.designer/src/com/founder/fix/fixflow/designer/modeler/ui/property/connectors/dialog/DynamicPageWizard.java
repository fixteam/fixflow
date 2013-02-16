/**
 * 
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

/**
 * @author wangzhiwei
 *
 */
public abstract class DynamicPageWizard extends Wizard { 
    /** 
     * 向导中的所有向导页，注意：指的是增加或者减少之后的向导页
     */ 
    private List<IWizardPage> pages = new ArrayList<IWizardPage>(); 

    private boolean forcePreviousAndNextButtons = false;
    
    /** 
     * 构造函数，创建一个空的向导
     */ 
    protected DynamicPageWizard() { 
        super(); 
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#addPage(org.eclipse.jface.wizard.IWizardPage) 
     */ 
    public void addPage(IWizardPage page) { 
    	 // 重写父类方法，添加向导页，并将向导页的向导设置为当前对象
    	 pages.add(page);
         page.setWizard(this);
    } 
    
    /** 
     * 在指定的向导页前插入向导页
     * 
     * @param page 
     * @param nextPage 
     * @return 
     */ 
    public boolean addPage(IWizardPage page, IWizardPage nextPage) { 
    	 for(int i = 0; i < pages.size(); i++) { 
    		 if(pages.get(i) == nextPage) { 
    			 return addPage(page, i); 
    		 } 
    	 } 
    	 return false; 
    } 
    
    /** 
     * 在指定的位置插入向导页
     * 
     * @param page 
     * @param location 
     */ 
    public boolean addPage(IWizardPage page, int location) { 
    	 // Invalid location 
    	 if(location < 0 || location > pages.size()) 
    		 return false; 
    	
    	 // Create the new page list 
    	 List<IWizardPage> newPages = new ArrayList<IWizardPage>(); 
    	 for(int i = 0; i < location; i++) { 
    		 newPages.add(pages.get(i)); 
    	 } 
    	
    	 page.setWizard(this); 
    	 newPages.add(page); 
    	
    	 for(int i = location; i < pages.size(); i++) { 
    		 newPages.add(pages.get(i)); 
    	 } 
    	
    	 // Set the relationship 
    	 if(location != pages.size()) 
    		 ((IWizardPage)newPages.get(location + 1)).setPreviousPage(page); 
    	
    	 ((IWizardPage)page).setPreviousPage((IWizardPage)newPages.get(location - 1)); 
    	 pages = newPages; 
    	 return true; 
    } 

    
    /** 
     * 删除指定位置的向导页
     * 
     * @param number 
     */ 
    public void removePage(int number) { 
    	 if(number < 0) 
    		 return; 
    	 if(number > pages.size() - 1) 
    		 return; 

    	 if(number == 0) 
    		 pages.remove(0); 
    	 else if(number == pages.size() - 1) 
    		 pages.remove(number); 
    	 else { 
	    	 IWizardPage wizarPage = (IWizardPage)pages.get(number + 1); 
	   		 wizarPage.setPreviousPage((IWizardPage)pages.get(number - 1)); 
	   		 pages.remove(number); 
    	 } 
    } 
    
    /** 
     * 删除指定的向导页
     * 
     * @param page 
     */ 
    public void removePage(IWizardPage page) { 
    	 int number = -1; 
    	 for(int i = 0; i < pages.size(); i++) { 
    		 if(pages.get(i) == page) 
    			 number = i; 
    	 } 
    	
    	 removePage(number); 
    } 
    
    
    /** 
     * 删除向导中某种类名的所有向导页
     * 
     * @param number 
     */ 
    public void removePage(String className) { 
       for(int i = 0; i < pages.size(); i++) { 
    	 if(pages.get(i).getClass().getCanonicalName().equalsIgnoreCase(className)) 
    			 removePage(i); 
    	 } 
    } 
    
	 /* 
	 * (non-Javadoc) 
	 * @see org.eclipse.jface.wizard.Wizard#addPages() 
	 */ 
    public void addPages() { 
    	 // 重写父类方法
    	super.addPages();
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#canFinish() 
     */ 
    public boolean canFinish() { 
    	 // 重写父类方法，检测是否所有向导页的设置都结束
    	for (int i = 0; i < pages.size(); i++) {
            if (!((IWizardPage) pages.get(i)).isPageComplete()) {
				return false;
			}
        }
        return true;
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#createPageControls 
     * (org.eclipse.swt.widgets.Composite) 
     */ 
    public void createPageControls(Composite pageContainer) { 
    	 // 重写父类方法
    	// the default behavior is to create all the pages controls
        for (int i = 0; i < pages.size(); i++) {
            IWizardPage page = (IWizardPage) pages.get(i);
            page.createControl(pageContainer);
            // page is responsible for ensuring the created control is
            // accessable
            // via getControl.
            Assert.isNotNull(page.getControl());
        }
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#dispose() 
     */ 
    public void dispose() { 
    	 // 重写父类方法
    	super.dispose();
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#getDefaultPageImage() 
     */ 
    public Image getDefaultPageImage() { 
    	 // 重写父类方法
    	return super.getDefaultPageImage();
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#getNextPage 
     * (org.eclipse.jface.wizard.IWizardPage) 
     */ 
    public IWizardPage getNextPage(IWizardPage page) { 
    	 // 重写父类方法，获取下一个向导页
    	 int index = pages.indexOf(page);
         if (index == pages.size() - 1 || index == -1) {
 			// last page or page not found
             return null;
 		}
         return (IWizardPage) pages.get(index + 1);
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#getPage(java.lang.String) 
     */ 
    public IWizardPage getPage(String name) { 
    	 // 重写父类方法，获取指定名字的向导页
    	for (int i = 0; i < pages.size(); i++) {
            IWizardPage page = (IWizardPage) pages.get(i);
            String pageName = page.getName();
            if (pageName.equals(name)) {
				return page;
			}
        }
        return null;
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#getPageCount() 
     */ 
    public int getPageCount() { 
    	 // 重写父类方法
    	return pages.size();
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#getPages() 
     */ 
    public IWizardPage[] getPages() { 
    	 // 重写父类方法
    	return (IWizardPage[]) pages.toArray(new IWizardPage[pages.size()]);
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#getPreviousPage 
     * (org.eclipse.jface.wizard.IWizardPage) 
     */ 
    public IWizardPage getPreviousPage(IWizardPage page) { 
    	 // 重写父类方法，获取某个向导页之前的向导页
    	 int index = pages.indexOf(page);
         if (index == 0 || index == -1) {
 			// first page or page not found
             return null;
 		} 
 		return (IWizardPage) pages.get(index - 1);
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#getStartingPage() 
     */ 
    public IWizardPage getStartingPage() { 
    	 // 重写父类方法，获取起始向导页
    	if (pages.size() == 0) {
			return null;
		}
        return (IWizardPage) pages.get(0);
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#performCancel() 
     */ 
    public boolean performCancel() { 
    	 // 重写父类方法
    	return super.performCancel();
    } 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#needsPreviousAndNextButtons() 
     */ 
    public boolean needsPreviousAndNextButtons() { 
    	 // 重写父类方法
    	return forcePreviousAndNextButtons || pages.size() > 1;
    } 
    
    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#setForcePreviousAndNextButtons(boolean) 
     */ 
    public void setForcePreviousAndNextButtons(boolean b) { 
    	 // 重写父类方法
    	forcePreviousAndNextButtons = b;
    } 
    
    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#performFinish() 
     */ 
    public abstract boolean performFinish(); 

    /* 
     * (non-Javadoc) 
     * @see org.eclipse.jface.wizard.Wizard#setDefaultPageImageDescriptor 
     * (org.eclipse.jface.resource.ImageDescriptor) 
     */ 
    public void setDefaultPageImageDescriptor(ImageDescriptor imageDescriptor) { 
    	 // 重写父类方法
    	super.setDefaultPageImageDescriptor(imageDescriptor);
    } 
 } 
