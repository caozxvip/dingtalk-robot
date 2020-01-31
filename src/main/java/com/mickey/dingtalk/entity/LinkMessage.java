package com.mickey.dingtalk.entity;

import com.mickey.dingtalk.type.MessageType;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 链接消息类型
 */
public class LinkMessage extends BaseMessage {

    /**
     * 消息简介
     */
    private String text;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 封面图片URL
     */
    private String picUrl;

    /**
     * 消息跳转URL
     */
    private String messageUrl;

    public LinkMessage() {
    }

    public LinkMessage(String title, String text, String messageUrl) {
        this.text = text;
        this.title = title;
        this.messageUrl = messageUrl;
    }

    public LinkMessage(String title, String text, String messageUrl, String picUrl) {
        this.text = text;
        this.title = title;
        this.picUrl = picUrl;
        this.messageUrl = messageUrl;
    }

    @Override
    protected void init() {
        this.msgtype = MessageType.link;
    }

    @Override
    public Map toMessageMap() {

        if (StringUtils.isEmpty(this.messageUrl) || StringUtils.isEmpty(this.title) ||
                StringUtils.isEmpty(this.text) || !MessageType.link.equals(msgtype)) {
            throw new IllegalArgumentException("please check the necessary parameters!");
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("msgtype", this.msgtype);

        HashMap<String, String> linkItems = new HashMap<>();
        linkItems.put("title", this.title);
        linkItems.put("text", this.text);
        linkItems.put("picUrl", this.picUrl);
        linkItems.put("messageUrl", this.messageUrl);
        resultMap.put("link", linkItems);

        return resultMap;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }
}