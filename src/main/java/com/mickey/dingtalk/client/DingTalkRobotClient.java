package com.mickey.dingtalk.client;

import com.mickey.dingtalk.entity.*;
import com.mickey.dingtalk.exception.DingTalkException;
import com.mickey.dingtalk.type.HideAvatarType;
import com.mickey.dingtalk.type.ResponseCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * 钉钉机器人客户端
 */
public class DingTalkRobotClient {

    @Autowired
    @Qualifier("dingTalkRobotRestTemplate")
    private RestTemplate restTemplate;

    /**
     * 钉钉机器人的WebHook地址
     */
    private String webhook;

    public DingTalkRobotClient(String webhook) {
        this.webhook = webhook;
    }

    /**
     * 支持外部传入WebHook地址的方式发送消息
     *
     * @param url
     * @param message
     * @return
     */
    public DingTalkResponse sendMessageByURL(String url, BaseMessage message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map> entity = new HttpEntity<>(message.toMessageMap(), headers);

        DingTalkResponse dingTalkResponse =
                restTemplate.postForObject(url, entity, DingTalkResponse.class);
        if (!ResponseCodeType.OK.getValue().equals(dingTalkResponse.getErrcode())) {
            throw new DingTalkException(dingTalkResponse.getErrcode(), dingTalkResponse.getErrmsg());
        }

        return dingTalkResponse;
    }

    /**
     * 发送WebHook消息到钉钉
     *
     * @param message
     * @return
     */
    public DingTalkResponse sendMessage(BaseMessage message) {
        return this.sendMessageByURL(webhook, message);
    }

    /**
     * 发送文本消息到钉钉
     *
     * @param message
     * @return
     */
    public DingTalkResponse sendTextMessage(TextMessage message) {
        return this.sendMessage(message);
    }

    /**
     * 发送文本消息到钉钉
     *
     * @param content
     * @return
     */
    public DingTalkResponse sendTextMessage(String content) {
        return this.sendMessage(new TextMessage(content));
    }

    /**
     * 发送文本消息到钉钉
     *
     * @param content
     * @param atMobiles
     * @return
     */
    public DingTalkResponse sendTextMessage(String content, String[] atMobiles) {
        return this.sendMessage(new TextMessage(content, atMobiles));
    }

    /**
     * 发送文本消息到钉钉
     *
     * @param content
     * @param isAtAll
     * @return
     */
    public DingTalkResponse sendTextMessage(String content, boolean isAtAll) {
        return this.sendMessage(new TextMessage(content, isAtAll));
    }

    /**
     * 发送Link消息到钉钉
     *
     * @param message
     * @return
     */
    public DingTalkResponse sendLinkMessage(LinkMessage message) {
        return this.sendMessage(message);
    }

    /**
     * 发送Link消息到钉钉
     *
     * @param title
     * @param text
     * @param messageUrl
     * @return
     */
    public DingTalkResponse sendLinkMessage(String title, String text, String messageUrl) {
        return this.sendMessage(new LinkMessage(title, text, messageUrl));
    }

    /**
     * 发送Link消息到钉钉
     *
     * @param title
     * @param text
     * @param messageUrl
     * @param picUrl
     * @return
     */
    public DingTalkResponse sendLinkMessage(String title, String text, String messageUrl, String picUrl) {
        return this.sendMessage(new LinkMessage(title, text, messageUrl, picUrl));
    }

    /**
     * 发送MarkDown消息到钉钉
     *
     * @param message
     * @return
     */
    public DingTalkResponse sendMarkdownMessage(MarkdownMessage message) {
        return this.sendMessage(message);
    }

    /**
     * 发送MarkDown消息到钉钉
     *
     * @param title
     * @param text
     * @return
     */
    public DingTalkResponse sendMarkdownMessage(String title, String text) {
        return this.sendMessage(new MarkdownMessage(title, text));
    }

    /**
     * 发送MarkDown消息到钉钉
     *
     * @param title
     * @param text
     * @param atMobiles
     * @return
     */
    public DingTalkResponse sendMarkdownMessage(String title, String text, String[] atMobiles) {
        return this.sendMessage(new MarkdownMessage(title, text, atMobiles));
    }

    /**
     * 发送MarkDown消息到钉钉
     *
     * @param title
     * @param text
     * @param isAtAll
     * @return
     */
    public DingTalkResponse sendMarkdownMessage(String title, String text, boolean isAtAll) {
        return this.sendMessage(new MarkdownMessage(title, text, isAtAll));
    }

    /**
     * 发送ActionCard消息到钉钉
     *
     * @param message
     * @return
     */
    public DingTalkResponse sendActionCardMessage(ActionCardMessage message) {
        return this.sendMessage(message);
    }

    /**
     * 发送ActionCard消息到钉钉
     *
     * @param title
     * @param text
     * @return
     */
    public DingTalkResponse sendActionCardMessage(String title, String text) {
        return this.sendMessage(new ActionCardMessage(title, text));
    }

    /**
     * 发送ActionCard消息到钉钉
     *
     * @param title
     * @param text
     * @param hideAvatar
     * @return
     */
    public DingTalkResponse sendActionCardMessage(String title, String text, HideAvatarType hideAvatar) {
        return this.sendMessage(new ActionCardMessage(title, text, hideAvatar));
    }

    /**
     * 发送ActionCard消息到钉钉
     *
     * @param title
     * @param text
     * @param button
     * @return
     */
    public DingTalkResponse sendActionCardMessage(String title, String text, ActionCardButton button) {
        return this.sendMessage(new ActionCardMessage(title, text, button));
    }

    /**
     * 发送ActionCard消息到钉钉
     *
     * @param title
     * @param text
     * @param hideAvatar
     * @param button
     * @return
     */
    public DingTalkResponse sendActionCardMessage(String title, String text, HideAvatarType hideAvatar, ActionCardButton button) {
        return this.sendMessage(new ActionCardMessage(title, text, hideAvatar, button));
    }

    /**
     * 发送FeedCard消息到钉钉
     *
     * @param feedCardMessage
     * @return
     */
    public DingTalkResponse sendFeedCardMessage(FeedCardMessage feedCardMessage) {
        return this.sendMessage(feedCardMessage);
    }

    /**
     * 发送FeedCard消息到钉钉
     *
     * @param feedCardItems
     * @return
     */
    public DingTalkResponse sendFeedCardMessage(List<FeedCardMessageItem> feedCardItems) {
        return this.sendMessage(new FeedCardMessage(feedCardItems));
    }
}