package com.u_anywhere.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.u_anywhere.model.TestPrintwaveno;
import com.u_anywhere.service.TestPrintwavenoService;
import com.u_anywhere.utils.PDFReadUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/import")
@Api(tags = "导入管理", position = 111)
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ImportController {
	@Autowired
	private TestPrintwavenoService testPrintwavenoService;
	
	@RequestMapping(value = "/pdf", method = RequestMethod.POST)
	@ApiOperation(notes = "导入管理", value = "pdf导入")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file", required = true, dataType = "MultipartFile", allowMultiple = true) })
	public Map importCommonOrder(MultipartFile file, HttpServletRequest request) throws Exception {
		Map ret = new HashMap();
		if (!file.getContentType().equals("application/pdf")) {
			return setErrorMsg(ret,"当前文件不是PDF文件");
		}
		byte[] bytes = file.getBytes();
		String[] lineArr = PDFReadUtil.getLineArr(bytes);
		TestPrintwaveno wave = getPdfWaveNo(ret, lineArr);
		ret.put("xhTotal", wave.getPrintcount());
		if (StringUtils.isEmpty(wave.getWaveno())) {
			ret.put("success", false);
			ret.put("errorMsg", "当前文件不是袋鼠打印单");
			return ret;
		}
		testPrintwavenoService.saveWaveCount(wave.getWaveno(),wave.getPrintcount());
		return ret;
	}
	private TestPrintwaveno getPdfWaveNo(Map ret, String[] lineArr) {
		int idenx = 1, xhStartIndex = -1;
		TestPrintwaveno wave = new TestPrintwaveno();
		for (int i = 0; i < lineArr.length; i++) {
			String line = lineArr[i];
			if (line.startsWith("波次号：")) {
				wave.setWaveno(line.substring("波次号：".length(), "波次号：".length() + 10));
			}
			if (line.startsWith("序号 产品代码 条形码 数量EA 分布")) {
				xhStartIndex = i + 2;
			}
			if (line.startsWith("仓管签名")) {
				xhStartIndex = -1;
			}
			if (xhStartIndex > 0
					&& line.matches("^[1-9]+\\d?\\s[\\d.a-zA-Z]+\\s[\\d.a-zA-Z]+\\s\\d+\\s[\\[\\]\\d\\,\\*]+$")) {
				ret.put(idenx++, line);
			}
		}
		wave.setPrintcount(--idenx);
		return wave;
	}
	@RequestMapping(value = "/folderPath", method = RequestMethod.POST)
	@ApiOperation(notes = "导入管理", value = "导入文件夹下所有pdf文件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "folderPath", required = true, paramType = "query",value="文件夹Path") })
	public Map importCommonOrder(String folderPath, HttpServletRequest request) throws Exception {
		Map ret = new HashMap();
		File dir = new File(folderPath);
		if (!dir.exists() && !dir.isDirectory()) {
			return setErrorMsg(ret, "当前文件夹不存在！" + folderPath);
		}
		File[] files = dir.listFiles();
		for (File file : files) {
			if (!file.isFile()) {
				continue;
			}
			String fileName = file.getName();
			if (fileName.endsWith(".pdf")) {
				String[] lineArr = PDFReadUtil.getLineArr(file);
				TestPrintwaveno wave = getPdfWaveNo(ret, lineArr);
				if (StringUtils.isEmpty(wave.getWaveno())) {
					System.out.println("当前文件" + fileName + "不是袋鼠打印单");
					continue;
				}
				testPrintwavenoService.saveWaveCount(wave.getWaveno(),wave.getPrintcount());
			}
			System.out.println(fileName);
		}
		return ret;
	}
	private Map setErrorMsg(Map ret, String msg) {
		ret.put("success", false);
		ret.put("errorMsg", msg);
		return ret;
	}
}
