package com.javasoso.pass.controller;

import com.javasoso.pass.aop.CheckLogin;
import com.javasoso.pass.aop.CheckLoginAspect;
import com.javasoso.pass.constant.PlatformEnum;
import com.javasoso.pass.constant.ResultModel;
import com.javasoso.pass.model.User;
import com.javasoso.pass.service.LoginService;
import com.javasoso.pass.service.UserService;
import com.javasoso.pass.util.BeanUtil;
import com.javasoso.pass.util.PasswordStrengthUtil;
import com.javasoso.pass.vo.LoginVO;
import com.javasoso.pass.vo.RegisterVO;
import com.javasoso.pass.vo.UserVO;
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
    @Autowired
    private UserService userService;
    /**
     * 开关
     */
    public static final String ON = "on";

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

    @ApiOperation(value = "注册", notes = "使用用户名密码注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultModel register(@Valid @RequestBody RegisterVO registerVO) {
        User user = userService.register(registerVO.getUserName(), registerVO.getSuperPass());
        UserVO u = BeanUtil.copyBean(user, UserVO.class);
        return buildSuccessResponse(u);
    }

    @CheckLogin
    @ApiOperation(value = "手机认证", notes = "增加手机认证")
    @RequestMapping(value = "/addPhone", method = RequestMethod.POST)
    public ResultModel addPhoneCertificate() {
        return new ResultModel(ResultModel.RESULT_NOT_SUPPORTED, "功能暂不支持");
    }

    @CheckLogin
    @ApiOperation(value = "邮箱认证", notes = "增加邮箱认证")
    @RequestMapping(value = "/addEmail", method = RequestMethod.POST)
    public ResultModel addEmailCertificate() {
        return new ResultModel(ResultModel.RESULT_NOT_SUPPORTED, "功能暂不支持");
    }

    @CheckLogin
    @ApiOperation(value = "发送手机验证码", notes = "用于增加手机认证")
    @RequestMapping(value = "/sendPhoneCode", method = RequestMethod.POST)
    public ResultModel sendPhoneCode() {
        return new ResultModel(ResultModel.RESULT_NOT_SUPPORTED, "功能暂不支持");
    }

    @CheckLogin
    @ApiOperation(value = "发送邮箱验证码", notes = "用于增加邮箱认证")
    @RequestMapping(value = "/sendEmailCode", method = RequestMethod.POST)
    public ResultModel sendEmailCode() {
        return new ResultModel(ResultModel.RESULT_NOT_SUPPORTED, "功能暂不支持");
    }

    @ApiOperation(value = "生成密码", notes = "生成随机密码")
    @RequestMapping(value = "/generatePassword", method = RequestMethod.GET)
    public ResultModel generatePassword(Integer length, String numSwitch, String capitalSwitch, String smallSwitch, String otherSwitch) {
        Map<String, String> result = new HashMap<>(1);
        result.put("password", PasswordStrengthUtil.generatePassword(length == null ? 18 : length, ON.equalsIgnoreCase(numSwitch), ON.equalsIgnoreCase(capitalSwitch), ON.equalsIgnoreCase(smallSwitch), ON.equalsIgnoreCase(otherSwitch)));
        return buildSuccessResponse(result);
    }

    @ApiOperation(value = "检查密码强度", notes = "检测密码的强度 服务端不存储")
    @RequestMapping(value = "/checkStrength", method = RequestMethod.GET)
    public ResultModel checkStrength(@RequestParam String pwd) {
        Map<String, String> result = new HashMap<>(3);
        result.put("password", pwd);
        result.put("strength", String.valueOf(PasswordStrengthUtil.checkPasswordStrength(pwd)));
        result.put("level", String.valueOf(PasswordStrengthUtil.getPasswordLevel(pwd)));
        return buildSuccessResponse(result);
    }
}
