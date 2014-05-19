package com.webCrawl.extract.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.util.FileHandle;
import com.util.Log;
import com.webCrawl.entity.ECrawlBug;
import com.webCrawl.extract.IExtract;

@Component("commonExtract")
public class CommonExtract extends ECrawlBug implements IExtract{

	public List<Object> extractHtml(String path) {
		try{
			List<Object> list = new ArrayList<Object>();
			File localPathFile = new File(path);
			if(localPathFile.exists()){
				String localFileContent = FileHandle.readFile(path);
				
				Pattern pattern = Pattern.compile("(href|src)=\"(.+?)\"");
				Matcher matcher = pattern.matcher(localFileContent);
				while(matcher.find()){
					String url = matcher.group(2);
					list.add(url);
				}
			}
			return list;
		}catch(Exception e){
			Log.Error("CommonExtract.extractHtml : " + e.getMessage());
		}
		return null;
	}

}
