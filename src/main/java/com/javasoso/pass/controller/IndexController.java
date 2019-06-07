package com.javasoso.pass.controller;

import com.javasoso.pass.constant.ResultModel;
import com.javasoso.pass.service.TestService;
import com.javasoso.pass.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/*
 * @Api：用在类上，说明该类的作用
 *
 * @ApiOperation：用在方法上，说明方法的作用
 *
 * @ApiImplicitParams：用在方法上包含一组参数说明
 *
 * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 *      paramType：参数放在哪个地方 header-->请求参数的获取：@RequestHeader
 *      query-->请求参数的获取：@RequestParam path（用于restful接口）-->请求参数的获取：@PathVariable
 *      body（不常用） form（不常用） name：参数名 dataType：参数类型 required：参数是否必须传 value：参数的意思
 *      defaultValue：参数的默认值
 *
 * @ApiResponses：用于表示一组响应
 *
 * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息 code：数字，例如400
 *      message：信息，例如"请求参数没填好" response：抛出异常的类
 *
 * @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，
 *      请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 *
 * @ApiModelProperty：描述一个model的属性
 */

@Api(description = "入口")
@RestController
public class IndexController extends BaseController{
    @Autowired
    private TestService testService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ResultModel index() {
        return buildSuccessResponse();
    }

    @ApiOperation(value = "say hello", notes = "备注非必填")
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public ResultModel sayHello(@Valid @ApiParam("用户数据") @RequestBody UserVO userVO){
        return buildSuccessResponse(testService.sayHello(userVO.getUserName()));
    }
}
