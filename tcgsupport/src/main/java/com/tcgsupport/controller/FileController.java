package com.tcgsupport.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcgsupport.config.SystemConfig;

@RestController
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@ResponseBody
	@RequestMapping(value = "/getbanner/{id}", method = {RequestMethod.GET })
	public HttpEntity<byte[]> getImage(@PathVariable String id) throws IOException {
		
		logger.info(id);
		// リソースファイルを読み込み
		String imgdir = SystemConfig.getInstance().getBannerbasedir();
		//Resource resource = resourceLoader.getResource("classpath:" + "/static/image/" + id);
		Resource resource = resourceLoader.getResource("file:" + imgdir + "/" + id);
		InputStream image = resource.getInputStream();
		
		// byteへ変換
		byte[] b = IOUtils.toByteArray(image);
		
		// レスポンスデータとして返却
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);		
		headers.setContentLength(b.length);
		return new HttpEntity<byte[]>(b, headers);
	}
}
