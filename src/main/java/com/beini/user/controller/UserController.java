package com.beini.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beini.core.enums.ResultEnum;
import com.beini.core.utils.ResultVOUtil;
import com.beini.core.vo.MiniProgramLoginVO;
import com.beini.core.vo.ResultVO;
import com.beini.user.entity.User;
import com.beini.user.manager.MiniProgramManager;
import com.beini.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "用户信息")
@RestController
@RequestMapping("/user/")
@SuppressWarnings("rawtypes")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private MiniProgramManager miniProgramManager;
	
	/**
	 * 小程序根据jscode登录
	 * 
	 * @param jscode
	 *            登录时获取的 code
	 * @return 登录返回值
	 */
	@ApiOperation(value = "【小程序】根据jscode获取openId")
	@GetMapping("smallRoutineLogin")
	public ResultVO smallRoutineLogin(@ApiParam(value="小程序获取的jscode") @RequestParam(name = "jscode") String jscode) {
		MiniProgramLoginVO vo = new MiniProgramLoginVO(jscode);
		return miniProgramManager.jscode2session(vo);
	}
	
	/**
	 * 根据用户ID获取用户信息
	 * 
	 * @param id
	 *            用户ID
	 * @return 用户信息
	 */
	@ApiOperation(value = "根据用户openId获取用户信息")
	@GetMapping("{openId}")
	public ResultVO findById(@PathVariable(value = "openId") String openId) {
		User user = userService.findById(openId);
		return ResultVOUtil.success(user);
	}

	/**
	 * 根据分页信息获取用户分页信息
	 * 
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页条数
	 * @return 用户分页信息
	 */
	@ApiOperation(value = "根据分页信息获取用户分页信息")
	@GetMapping("")
	public ResultVO findByPage(
			@ApiParam(value="页码") @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@ApiParam(value="分页大小") @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		PageRequest request = new PageRequest(pageNo - 1, pageSize);
		Page<User> page = userService.findAll(request);
		return ResultVOUtil.success(page);
	}

	@ApiOperation(value = "根据用户信息进行数据更新(以主键为依据)")
	@PutMapping
	public ResultVO update(User user) {
		if (user == null || user.getOpenId() == null || "".equals(user.getOpenId())) {
			return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
		}
		if (userService.update(user) == null) {
			return ResultVOUtil.error(ResultEnum.USER_UPDATE_FAIL);
		} else {
			return ResultVOUtil.success();
		}
	}
	
	@ApiOperation(value = "增加用户信息")
	@PostMapping
	public ResultVO save(User user) {
		if (userService.save(user) == null) {
			return ResultVOUtil.error(ResultEnum.USER_INSERT_FAIL);
		} else {
			return ResultVOUtil.success();
		}
	}

	@ApiOperation(value = "根据用户openId删除用户信息")
	@DeleteMapping("{openId}")
	public ResultVO deleteById(@PathVariable(value = "openId") String openId) {
		try {
			userService.delete(openId);
			return ResultVOUtil.success();
		} catch (Exception e) {
			return ResultVOUtil.error(ResultEnum.USER_DELETE_FAIL);
		}
	}
}
