package com.bgnt.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Mbg_GeneratorUtil {
	public void generator() throws Exception
	{
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		//指定 逆向工程配置文件
		File configFile = new File("/Volumes/Disk/study/java/bg-nt/src/main/resources/generatorConfig.xml");
//		Files.newBufferedReader(Paths.get(URI.create()));
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback, warnings);
		myBatisGenerator.generate(null);
	} 
	
	public static void main(String[] args) throws Exception 
	{
		try 
		{
			new Mbg_GeneratorUtil().generator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
