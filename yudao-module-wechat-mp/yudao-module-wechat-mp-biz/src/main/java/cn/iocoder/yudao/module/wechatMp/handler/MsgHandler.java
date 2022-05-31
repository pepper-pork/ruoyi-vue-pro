package cn.iocoder.yudao.module.wechatMp.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.wechatMp.builder.TextBuilder;
import cn.iocoder.yudao.module.wechatMp.dal.dataobject.account.WxAccountDO;
import cn.iocoder.yudao.module.wechatMp.service.account.WxAccountService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

@Component
@Slf4j
public class MsgHandler implements WxMpMessageHandler {

/*
    @Autowired
    WxReceiveTextService wxReceiveTextService;

    @Autowired
    WxTextTemplateService wxTextTemplateService;

    @Autowired
    WxAccountService wxAccountService;

    @Autowired
    WxFansMsgService wxFansMsgService;
*/


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {
       /* log.info( "收到信息内容:｛｝", JsonUtils.toJsonString(wxMessage) );
        log.info( "关键字:｛｝", wxMessage.getContent() );

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //可以选择将消息保存到本地

            // 获取微信用户基本信息
            try {
                WxMpUser wxmpUser = weixinService.getUserService()
                        .userInfo(wxMessage.getFromUser(), null);
                if (wxmpUser != null) {
                    WxAccountDO wxAccount = wxAccountService.findBy( WxAccountDO::getAccount,wxMessage.getToUser());
                    if(wxAccount != null){

                        if(wxMessage.getMsgType().equals( XmlMsgType.TEXT )){
                            WxFansMsg wxFansMsg = new WxFansMsg();
                            wxFansMsg.setOpenid( wxmpUser.getOpenId() );
                            try {
                                wxFansMsg.setNickname(wxmpUser.getNickname().getBytes("UTF-8"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            wxFansMsg.setHeadimgUrl( wxmpUser.getHeadImgUrl() );
                            wxFansMsg.setWxAccountId( String.valueOf( wxAccount.getId() ) );
                            wxFansMsg.setMsgType( wxMessage.getMsgType());
                            wxFansMsg.setContent( wxMessage.getContent() );
                            wxFansMsg.setIsRes( "1" );
                            wxFansMsg.setCreateTime( DateUtil.date() );
                            wxFansMsg.setUpdateTime( DateUtil.date() );

                            //组装回复消息
                            String content = processContent(wxMessage);
                            content = HtmlUtil.escape( content );
                            wxFansMsg.setResContent( content  );

                            wxFansMsgService.save( wxFansMsg );
                            return new TextBuilder().build(content, wxMessage, weixinService);

                        }
                        if(wxMessage.getMsgType().equals( XmlMsgType.IMAGE )){
                            WxFansMsg wxFansMsg = new WxFansMsg();
                            wxFansMsg.setOpenid( wxmpUser.getOpenId() );
                            try {
                                wxFansMsg.setNickname(wxmpUser.getNickname().getBytes("UTF-8"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            wxFansMsg.setHeadimgUrl( wxmpUser.getHeadImgUrl() );
                            wxFansMsg.setWxAccountId( String.valueOf( wxAccount.getId() ) );
                            wxFansMsg.setMsgType( wxMessage.getMsgType());
                            wxFansMsg.setMediaId( wxMessage.getMediaId() );
                            wxFansMsg.setPicUrl( wxMessage.getPicUrl() );

                            File downloadDir = new File(downloadDirStr);
                            if (!downloadDir.exists()) {
                                downloadDir.mkdirs();
                            }
                            String filepath = downloadDirStr + String.valueOf( System.currentTimeMillis() ) + ".png";
                            //微信pic url下载到本地，防止失效
                            long size = HttpUtil.downloadFile(wxMessage.getPicUrl(), FileUtil.file(filepath));
                            log.info("download pic size : {}",size);
                            wxFansMsg.setPicPath( filepath );
                            wxFansMsg.setIsRes( "0" );
                            wxFansMsg.setCreateTime( DateUtil.date() );
                            wxFansMsg.setUpdateTime( DateUtil.date() );
                            wxFansMsgService.save( wxFansMsg );
                        }

                    }
                }
            } catch (WxErrorException e) {
                if (e.getError().getErrorCode() == 48001) {
                    log.info("该公众号没有获取用户信息权限！");
                }
            }

        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
//        try {
//            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
//                && weixinService.getKefuService().kfOnlineList()
//                .getKfOnlineList().size() > 0) {
//                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
//                    .fromUser(wxMessage.getToUser())
//                    .toUser(wxMessage.getFromUser()).build();
//            }
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }

        //组装默认回复消息
        String content = defaultResponseContent;//默认
        return new TextBuilder().build(content, wxMessage, weixinService);
        */
       return null;
    }

    //处理回复信息，优先级，关键字、智能机器人、默认值
    String processContent(WxMpXmlMessage wxMessage){
        String content = "";
       /* content = processReceiveTextContent(wxMessage);
        if(StringUtils.isNotBlank( content )){
            return content;
        }

        content = defaultResponseContent;*/
        return content;
    }

    //处理关键字
    String processReceiveTextContent(WxMpXmlMessage wxMessage){
        String content = "";
      /*  WxAccountDO wxAccount = wxAccountService.findBy( WxAccountDO::getAccount,wxMessage.getToUser());
        if(wxAccount != null){
            WxReceiveText wxReceiveTextTpl = new WxReceiveText();
            wxReceiveTextTpl.setWxAccountId( String.valueOf( wxAccount.getId() ) );
            wxReceiveTextTpl.setReceiveText( wxMessage.getContent() );
            List<WxReceiveText> wxReceiveTextList = wxReceiveTextService.findListByReceiveTest( wxReceiveTextTpl );
            if(wxReceiveTextList != null && wxReceiveTextList.size() > 0){
                WxReceiveText wxReceiveText = wxReceiveTextList.get( 0 );
                WxTextTemplate wxTextTemplate = wxTextTemplateService.findById( Integer.parseInt( wxReceiveText.getTplId() ) );
                content = wxTextTemplate.getContent();
            }
        }*/
        return content;
    }



}