package com.zhump.freemarkerCommon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 模板生成HTML工具类
 * @author zhump
 * @Date 2019/01/29
 */
public class FreemarkerUtil {

	/**
	 * 读取模板配置文件 ftl
	 * @param name 模板名称，带后缀
	 * @param path 模板路径
	 * @return
	 */
	public Template getTemplate(String name, String path) {
		try {
			// 通过Freemaker的Configuration读取相应的ftl
			Configuration cfg = new Configuration();
			// 设定去哪里读取相应的ftl模板文件
			cfg.setDirectoryForTemplateLoading(new File(path));
			// 在模板文件目录中找到名称为name的文件
			Template temp = cfg.getTemplate(name);
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 输出HTML文件
	 * 
	 * @param name
	 *            模板名称
	 * @param root
	 *            模板数据
	 * @param outFile
	 *            输出文件，带拓展名
	 * @param path
	 *            模板路径
	 * @param outPath
	 *            生成模板输出路径
	 */
	public String fprint(String name, String path, Map<String, Object> root,
			String outPath, String outFile) {
		FileWriter out = null;
		try {
			if(name == null 
					|| path == null
					|| root == null
					|| outPath == null
					|| outFile == null){
				return null;
			}
			File file = new File(outPath + outFile);
			out = new FileWriter(file);
			Template temp = this.getTemplate(name, path);
			temp.process(root, out);
			return file.getPath();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
