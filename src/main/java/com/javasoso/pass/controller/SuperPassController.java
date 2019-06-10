package com.javasoso.pass.controller;

import com.github.pagehelper.PageInfo;
import com.javasoso.pass.aop.CheckLogin;
import com.javasoso.pass.constant.ResultModel;
import com.javasoso.pass.model.AppAccount;
import com.javasoso.pass.model.CardAccount;
import com.javasoso.pass.model.Memo;
import com.javasoso.pass.model.ShowType;
import com.javasoso.pass.service.AppService;
import com.javasoso.pass.service.CardService;
import com.javasoso.pass.service.CommonService;
import com.javasoso.pass.service.MemoService;
import com.javasoso.pass.util.BeanUtil;
import com.javasoso.pass.util.ToolsUtil;
import com.javasoso.pass.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 主 controller
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Api(description = "核心服务")
@RestController
@RequestMapping("/api/v1")
public class SuperPassController extends BaseController {
    @Autowired
    private AppService appService;
    @Autowired
    private CardService cardService;
    @Autowired
    private MemoService memoService;
    @Autowired
    private CommonService commonService;

    @CheckLogin
    @ApiOperation(value = "增加账户类", notes = "增加账户")
    @RequestMapping(value = "/addAppAccount", method = RequestMethod.POST)
    public ResultModel addAppAccount(HttpServletRequest request, @Valid @RequestBody AppAccountVO appAccountVO) {
        AppAccount record = appService.addAppAccount(appAccountVO, ToolsUtil.getRemoteIp(request));
        AppAccountVO vo = BeanUtil.copyBean(record, AppAccountVO.class);
        return buildSuccessResponse(vo);
    }

    @CheckLogin
    @ApiOperation(value = "增加卡片类", notes = "增加卡片")
    @RequestMapping(value = "/addCardAccount", method = RequestMethod.POST)
    public ResultModel addCardAccount(HttpServletRequest request, @Valid @RequestBody CardAccountVO cardAccountVO) {
        CardAccount record = cardService.addCardAccount(cardAccountVO, ToolsUtil.getRemoteIp(request));
        CardAccountVO vo = BeanUtil.copyBean(record, CardAccountVO.class);
        return buildSuccessResponse(vo);
    }

    @CheckLogin
    @ApiOperation(value = "增加备忘录", notes = "增加备忘录")
    @RequestMapping(value = "/addMemo", method = RequestMethod.POST)
    public ResultModel addMemo(HttpServletRequest request, @Valid @RequestBody MemoVO memoVO) {
        Memo record = memoService.addMemo(memoVO, ToolsUtil.getRemoteIp(request));
        MemoVO vo = BeanUtil.copyBean(record, MemoVO.class);
        return buildSuccessResponse(vo);
    }

    @CheckLogin
    @ApiOperation(value = "修改账户类", notes = "修改账户")
    @RequestMapping(value = "/updateAppAccount", method = RequestMethod.POST)
    public ResultModel updateAppAccount(HttpServletRequest request, @Valid @RequestBody AppAccountVO appAccountVO) {
        if (appService.updateAppAccount(appAccountVO, ToolsUtil.getRemoteIp(request))){
            return buildSuccessResponse();
        }else {
            return buildErrorResponse("修改失败");
        }
    }

    @CheckLogin
    @ApiOperation(value = "修改卡片类", notes = "修改卡片")
    @RequestMapping(value = "/updateCardAccount", method = RequestMethod.POST)
    public ResultModel updateCardAccount(HttpServletRequest request, @Valid @RequestBody CardAccountVO cardAccountVO) {
        if (cardService.updateCardAccount(cardAccountVO, ToolsUtil.getRemoteIp(request))){
            return buildSuccessResponse();
        }else {
            return buildErrorResponse("修改失败");
        }
    }

    @CheckLogin
    @ApiOperation(value = "修改备忘录", notes = "修改备忘录")
    @RequestMapping(value = "/updateMemo", method = RequestMethod.POST)
    public ResultModel updateMemo(HttpServletRequest request, @Valid @RequestBody MemoVO memoVO) {
        if (memoService.updateMemo(memoVO, ToolsUtil.getRemoteIp(request))){
            return buildSuccessResponse();
        }else {
            return buildErrorResponse("修改失败");
        }
    }

    @CheckLogin
    @ApiOperation(value = "删除账户类", notes = "删除账户")
    @RequestMapping(value = "/deleteAppAccount", method = RequestMethod.POST)
    public ResultModel deleteAppAccount(HttpServletRequest request, @Valid @RequestBody AppAccountVO appAccountVO) {
        if (appService.deleteAppAccount(appAccountVO.getId(), ToolsUtil.getRemoteIp(request))){
            return buildSuccessResponse();
        }else {
            return buildErrorResponse("修改失败");
        }
    }

    @CheckLogin
    @ApiOperation(value = "删除卡片类", notes = "删除卡片")
    @RequestMapping(value = "/deleteCardAccount", method = RequestMethod.POST)
    public ResultModel deleteCardAccount(HttpServletRequest request, @Valid @RequestBody CardAccountVO cardAccountVO) {
        if (cardService.deleteCardAccount(cardAccountVO.getId(), ToolsUtil.getRemoteIp(request))){
            return buildSuccessResponse();
        }else {
            return buildErrorResponse("修改失败");
        }
    }

    @CheckLogin
    @ApiOperation(value = "删除备忘录", notes = "删除备忘录")
    @RequestMapping(value = "/deleteMemo", method = RequestMethod.POST)
    public ResultModel deleteMemo(HttpServletRequest request, @Valid @RequestBody MemoVO memoVO) {
        if (memoService.deleteMemo(memoVO.getId(), ToolsUtil.getRemoteIp(request))){
            return buildSuccessResponse();
        }else {
            return buildErrorResponse("修改失败");
        }
    }

    @CheckLogin
    @ApiOperation(value = "分页搜索账户类", notes = "搜索账户")
    @RequestMapping(value = "/searchAppAccount", method = RequestMethod.GET)
    public ResultModel searchAppAccount(Integer pageNum, Integer pageSize) {
        PageInfo<AppAccount> pageInfo = appService.searchByPage(new AppAccountVO(), pageNum, pageSize);
        PageVO<AppAccountVO> result = new PageVO<>();
        result.setList(BeanUtil.copyList(pageInfo.getList(), AppAccountVO.class));
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotalPage(pageInfo.getPages());
        return buildSuccessResponse(result);
    }

    @CheckLogin
    @ApiOperation(value = "分页搜索卡片类", notes = "搜索卡片类")
    @RequestMapping(value = "/searchCardAccount", method = RequestMethod.GET)
    public ResultModel searchCardAccount(Integer pageNum, Integer pageSize) {
        PageInfo<CardAccount> pageInfo = cardService.searchByPage(new CardAccountVO(), pageNum, pageSize);
        PageVO<CardAccountVO> result = new PageVO<>();
        result.setList(BeanUtil.copyList(pageInfo.getList(), CardAccountVO.class));
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotalPage(pageInfo.getPages());
        return buildSuccessResponse(result);
    }

    @CheckLogin
    @ApiOperation(value = "分页搜索备忘录", notes = "搜索备忘录")
    @RequestMapping(value = "/searchMemo", method = RequestMethod.GET)
    public ResultModel searchMemo(Integer pageNum, Integer pageSize) {
        PageInfo<Memo> pageInfo = memoService.searchByPage(new MemoVO(), pageNum, pageSize);
        PageVO<MemoVO> result = new PageVO<>();
        result.setList(BeanUtil.copyList(pageInfo.getList(), MemoVO.class));
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotalPage(pageInfo.getPages());
        return buildSuccessResponse(result);
    }

    @CheckLogin
    @ApiOperation(value = "获取账户类", notes = "获取账户类账号")
    @RequestMapping(value = "/getAppAccount", method = RequestMethod.GET)
    public ResultModel getAppAccount(HttpServletRequest request, @RequestParam Integer id) {
        AppAccount record = appService.searchById(id, ToolsUtil.getRemoteIp(request));
        AppAccountVO vo = BeanUtil.copyBean(record, AppAccountVO.class);
        return buildSuccessResponse(vo);
    }

    @CheckLogin
    @ApiOperation(value = "获取卡片类", notes = "获取卡片类账号")
    @RequestMapping(value = "/getCardAccount", method = RequestMethod.GET)
    public ResultModel getCardAccount(HttpServletRequest request, @RequestParam Integer id) {
        CardAccount record = cardService.searchById(id, ToolsUtil.getRemoteIp(request));
        CardAccountVO vo = BeanUtil.copyBean(record, CardAccountVO.class);
        return buildSuccessResponse(vo);
    }

    @CheckLogin
    @ApiOperation(value = "获取备忘录", notes = "获取账户类账号")
    @RequestMapping(value = "/getMemo", method = RequestMethod.GET)
    public ResultModel getMemo(HttpServletRequest request, @RequestParam Integer id) {
        Memo record = memoService.searchById(id, ToolsUtil.getRemoteIp(request));
        MemoVO vo = BeanUtil.copyBean(record, MemoVO.class);
        return buildSuccessResponse(vo);
    }

    @CheckLogin
    @ApiOperation(value = "获取默认图标", notes = "获取默认图标")
    @RequestMapping(value = "/searchShowType", method = RequestMethod.GET)
    public ResultModel searchShowType(Integer accountType) {
        ShowTypeVO showTypeVO = new ShowTypeVO();
        showTypeVO.setAccountType(accountType);
        List<ShowType> showTypeList = commonService.searchShowType(showTypeVO);
        List<ShowTypeVO> voList = BeanUtil.copyList(showTypeList, ShowTypeVO.class);
        return buildSuccessResponse(voList);
    }


}
