package com.gzz.createcode.mvc.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.model.CodeCond;
import com.gzz.createcode.mvc.model.Field;
import com.gzz.createcode.mvc.model.Table;
import com.gzz.createcode.mvc.service.CodeService;

import lombok.extern.slf4j.Slf4j;

/**
 * @功能描述 代码生成器控制器类
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */
@RestController
@RequestMapping("/code")
@Slf4j
public class CodeAction {
	@Autowired
	private CodeService service;// 生成器业务罗辑接口
	@Value("${spring.datasource.url}")
	private String url;
	public static String dbName;// 数据库用户名

	/**
	 * @功能描述 系统变量及初始化
	 */
	@PostConstruct
	private void init() {
		String[] split = url.split("[?]")[0].split("/");
		dbName = split[split.length - 1];
		log.info("当前数据库名称是:{}",dbName);
	}

	/**
	 * @功能描述 查询数据库中表名列表
	 */
	@PostMapping("/queryList")
	public List<Table> queryList(@RequestBody CodeCond cond) {
		cond.setDb_name(dbName);
		return service.queryTables(cond);
	}

	/**
	 * @功能描述 查询数据库中表名列表
	 */
	@PostMapping("/queryField")
	public List<Field> queryField(@RequestBody CodeCond cond) {
		cond.setDb_name(dbName);
		return service.queryFields(cond);
	}

	/**
	 * @功能描述 生成代码
	 */
	@PostMapping("/create")
	public void create(@RequestBody CodeCond cond) {
		Utils.delDir(new File(Utils.path() + "com/"));
		cond.setDb_name(dbName);
		service.create(cond);
		Utils.chmod();
	}

	/**
	 * @功能描述 下载代码
	 */
	@GetMapping("/downCode")
	public void downCode(HttpServletResponse response) throws IOException {
		String fileName = "code.zip";
		Utils.createZip(Utils.path() + "com", Utils.path() + fileName);
		Path path = Paths.get(Utils.path() + fileName);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Content-Length", "" + Files.size(path));
		response.setContentType("application/zip");
		OutputStream out = response.getOutputStream();
		out.write(Files.readAllBytes(path));
		out.flush();
		out.close();
	}

}
