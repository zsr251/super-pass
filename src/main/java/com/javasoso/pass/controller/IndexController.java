package com.javasoso.pass.controller;

import com.javasoso.pass.constant.ResultModel;
import com.javasoso.pass.util.AESUtil;
import com.javasoso.pass.util.MD5Util;
import com.javasoso.pass.util.PasswordStrengthUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.javasoso.pass.controller.LoginController.ON;


/**
 * @Api：用在类上，说明该类的作用
 * @ApiOperation：用在方法上，说明方法的作用
 * @ApiImplicitParams：用在方法上包含一组参数说明
 * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面 paramType：参数放在哪个地方 header-->请求参数的获取：@RequestHeader
 * query-->请求参数的获取：@RequestParam path（用于restful接口）-->请求参数的获取：@PathVariable
 * body（不常用） form（不常用） name：参数名 dataType：参数类型 required：参数是否必须传 value：参数的意思
 * defaultValue：参数的默认值
 * @ApiResponses：用于表示一组响应
 * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息 code：数字，例如400
 * message：信息，例如"请求参数没填好" response：抛出异常的类
 * @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景， 请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 * @ApiModelProperty：描述一个model的属性
 */
@Slf4j
@Api(description = "入口")
@RestController
@RequestMapping
public class IndexController extends BaseController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ResultModel index(Integer length, String numSwitch, String capitalSwitch, String smallSwitch, String otherSwitch) {
        return buildSuccessResponse(PasswordStrengthUtil.generatePassword(length == null ? 18 : length, ON.equalsIgnoreCase(numSwitch), ON.equalsIgnoreCase(capitalSwitch), ON.equalsIgnoreCase(smallSwitch), ON.equalsIgnoreCase(otherSwitch)));
    }

    @ApiOperation(value = "生成 secure", notes = "只用于测试，生产由客户端生成")
    @RequestMapping(value = "/generateSecure", method = RequestMethod.GET)
    public ResultModel generateSecure(@RequestParam(required = false) String pwd) {
        pwd = StringUtils.isEmpty(pwd) ? PasswordStrengthUtil.generatePassword() : pwd;
        String secure = UUID.randomUUID().toString().toUpperCase().substring(4,23);
        String salt = BCrypt.gensalt(10);
        String S = MD5Util.encode(secure + pwd);
        Map<String, String> result = new HashMap<>(5);
        result.put("password", pwd);
        result.put("secure", secure);
        result.put("S", S);
        result.put("P", BCrypt.hashpw(S, salt));
        return buildSuccessResponse(result);
    }

    @ApiOperation(value = "生成 S", notes = "生成S，生产由客户端生成")
    @RequestMapping(value = "/generateP", method = RequestMethod.GET)
    public ResultModel generateS(@RequestParam String pwd, @RequestParam String secure) {
        Map<String, String> result = new HashMap<>(2);
        String S = MD5Util.encode(secure + pwd);
        String salt = BCrypt.gensalt(10);
        result.put("S", S);
        result.put("P", BCrypt.hashpw(S, salt));
        return buildSuccessResponse(result);
    }

    @ApiOperation(value = "AES 加密", notes = "生成密文，生产由客户端生成")
    @RequestMapping(value = "/encrypt", method = RequestMethod.GET)
    public ResultModel encrypt(@RequestParam String content, @RequestParam String key) {
        try {
            return buildSuccessResponse(AESUtil.encrypt(content, key.replace("-","")));
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        }
    }

    @ApiOperation(value = "AES 解密", notes = "解密，生产由客户端生成")
    @RequestMapping(value = "/decrypt", method = RequestMethod.GET)
    public ResultModel decrypt(@RequestParam String content, @RequestParam String key) {
        try {
            return buildSuccessResponse(AESUtil.decrypt(content, key.replace("-","")));
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage());
        }
    }
}
