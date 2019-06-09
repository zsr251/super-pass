package com.javasoso.pass.controller;

import com.javasoso.pass.aop.CheckLogin;
import com.javasoso.pass.aop.CheckLoginAspect;
import com.javasoso.pass.constant.PlatformEnum;
import com.javasoso.pass.constant.ResultModel;
import com.javasoso.pass.model.User;
import com.javasoso.pass.service.LoginService;
import com.javasoso.pass.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Api(description = "登录相关")
@RestController
@RequestMapping("/api/v1")
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录", notes = "根据用户名 密码登录，需要增加次数限制")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultModel loginByPassword(HttpServletRequest request, HttpServletResponse response, @RequestHeader("client") Integer client, @Valid @RequestBody LoginVO loginVO) {
        String token = loginService.loginByPassword(loginVO.getUserName(), loginVO.getSuperPass(), PlatformEnum.getEnum(client), request);
        Map<String, String> result = new HashMap<>(1);
        result.put("token", token);
        response.addCookie(new Cookie("token",token));
        response.addCookie(new Cookie("client",String.valueOf(client)));
        return buildSuccessResponse(result);
    }

    @ApiOperation(value = "登出", notes = "登出 清除数据库和 redis")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @CheckLogin
    public ResultModel logout(HttpServletResponse response,@RequestHeader("token") String token, @RequestHeader("client") Integer client) {
        User user = CheckLoginAspect.getCurrentUser();
        if (user == null) {
            return buildSuccessResponse();
        }
        loginService.logout(user.getId(), PlatformEnum.getEnum(client));
        Cookie tc = new Cookie("token",null);
        tc.setMaxAge(0);
        response.addCookie(tc);
        return buildSuccessResponse();
    }

    @ApiOperation(value = "修改密码", notes = "只有认证过的账户才能修改密码")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResultModel changePassword(){
        return new ResultModel(ResultModel.RESULT_NOT_SUPPORTED,"功能暂不支持");
    }
}
