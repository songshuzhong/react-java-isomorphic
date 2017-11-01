/*
 * 文件名：numberOfCarController.java
 * 版权：Copyright by bonc
 * 描述：
 * 修改人：zhoutao
 * 修改时间：2016年8月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.cn.bonc.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Simple {

	@RequestMapping("/")
	public String login(HttpServletRequest request, Model model){
        model.addAttribute("key123", "value12312");
		return "index";
	}
	
	@RequestMapping(value = {"/jsp"},method = RequestMethod.GET )
    public String jsp(Model model) {
        model.addAttribute("key", "value");
        return "index";
    }

    @RequestMapping(value = "/jsx", headers = "Accept=text/html")
    public void jsx(Model model) {
        model.addAttribute("key", "value");
    }

    @RequestMapping("/jsx/page")
    public String jsxp() {
        Map map = new HashMap();
        map.put("key", "value");
        return "react";
    }

    @ResponseBody
    @RequestMapping(value = "/rest/api", method = RequestMethod.GET)
    public Map restful() {
        Map map = new HashMap();
        map.put("key1", "value1");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/jsx/rest/api", method = RequestMethod.GET)
    public Map reactRestful() {
        Map map = new HashMap();
        map.put("key1", "value1");
        return map;
    }
}
