package com.zcwl.ps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcwl.cache.EhCacheMananger;
import com.zcwl.ps.bo.EhCacheNames;

/**
 * 
 * @author Hugo
 * 
 */
@Controller
public class TestController {

	protected static final Logger LOG = LoggerFactory
			.getLogger(TestController.class);

	@RequestMapping("/test/add.do")
	public String add(ModelMap model, HttpSession session) {

		List<String> datas = EhCacheMananger.getInstance().get(
				EhCacheNames.webCache.name(), "TESTDATA");

		if (datas == null) {
			datas = new ArrayList<String>();
			EhCacheMananger.getInstance().put(EhCacheNames.webCache.name(),
					"TESTDATA", datas);
		}

		for (int i = 0; i < 200; i++) {
			datas.add("str" + i);
		}

		model.addAttribute("result", "增加一个成功");

		return "common/result";
	}

	@RequestMapping("/test/del.do")
	public String del(ModelMap model, HttpSession session) {

		List<String> datas = EhCacheMananger.getInstance().get(
				EhCacheNames.webCache.name(), "TESTDATA");

		if (datas != null) {
			for (int i = 0; i < 200; i++) {
				if (datas != null && datas.size() > 0) {
					datas.remove(0);
				}
			}
		}

		model.addAttribute("result", "删除一个成功");
		return "common/result";
	}

	@RequestMapping("/test/loop.do")
	public String loop(ModelMap model, HttpSession session) {
		List<String> datas = EhCacheMananger.getInstance().get(
				EhCacheNames.webCache.name(), "TESTDATA");

		if (datas != null) {
			// 注意：如果在Controller里面直接循环，则多线程中会出现以下异常：java.util.ConcurrentModificationException
			for (String str : datas) {
				System.out.println(str);
			}
		}

		model.addAttribute("datas", datas);
		return "test/loop";
	}

}
