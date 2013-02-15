package com.founder.fix.fixflow.core.model;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * 
 * @author kenshin
 *
 */
public interface DeploymentBuilder {
  
  DeploymentBuilder addInputStream(String resourceName, InputStream inputStream);
  DeploymentBuilder addClasspathResource(String resource);
  DeploymentBuilder addString(String resourceName, String text);
  DeploymentBuilder addZipInputStream(ZipInputStream zipInputStream);

  
  DeploymentBuilder name(String name);
  DeploymentBuilder enableDuplicateFiltering();
  
  /**
   * 更新资源
   * @param resourceId 资源编号
   * @param inputStream 流
   * @return
   */
  DeploymentBuilder updateResource(String resourceId,InputStream inputStream);

  Deployment deploy();
}
