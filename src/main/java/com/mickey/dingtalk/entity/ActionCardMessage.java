package com.mickey.dingtalk.entity;

import com.mickey.dingtalk.type.ButtonOrientationType;
import com.mickey.dingtalk.type.HideAvatarType;
import com.mickey.dingtalk.type.MessageType;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 跳转卡片类型
 */
public class ActionCardMessage extends BaseMessage {

    /**
     * 当按钮数目超过2两个时，按钮一定是垂直排列。
     * 这里的最大值和最小值分别设置为10和0
     */
    private static final int MAX_BUTTON_COUNT = 10;
    private static final int MIN_BUTTON_COUNT = 0;

    /**
     * 标题
     */
    private String title;

    /**
     * 正文，支持MarkDown语法
     */
    private String text;

    /**
     * 是否隐藏头像
     * 0 - 不隐藏头像
     * 1 - 隐藏头像
     */
    private HideAvatarType hideAvatar = HideAvatarType.UNHIDE;

    /**
     * 按钮排列方式
     * 0 - 垂直排列
     * 1 - 水平排列
     */
    private ButtonOrientationType btnOrientation = ButtonOrientationType.HORIZONTAL;

    /**
     * 操作按钮成员变量
     * 这个成员变量没有Set方法，主要是为了防止按钮过多造成的较差体验
     */
    private List<ActionCardButton> buttons = new ArrayList<>();

    public ActionCardMessage() {
    }

    public ActionCardMessage(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public ActionCardMessage(String title, String text, HideAvatarType hideAvatar) {
        this.title = title;
        this.text = text;
        this.hideAvatar = hideAvatar;
    }

    public ActionCardMessage(String title, String text, ActionCardButton button) {
        this.title = title;
        this.text = text;
        this.buttons.add(button);
    }

    public ActionCardMessage(String title, String text, HideAvatarType hideAvatar, ActionCardButton button) {
        this.title = title;
        this.text = text;
        this.hideAvatar = hideAvatar;
        this.buttons.add(button);
    }

    @Override
    protected void init() {
        this.msgtype = MessageType.actionCard;
    }

    @Override
    public Map toMessageMap() {

        if (StringUtils.isEmpty(this.text) || StringUtils.isEmpty(this.title)) {
            throw new IllegalArgumentException("please check the necessary parameters!");
        }

        if (buttons == null || buttons.size() < MIN_BUTTON_COUNT) {
            throw new IllegalArgumentException("the number of buttons is not allow lower than " + MIN_BUTTON_COUNT);
        }

        if (buttons.size() > MAX_BUTTON_COUNT) {
            throw new IllegalArgumentException("the number of buttons is not advise bigger than " + MAX_BUTTON_COUNT);
        }

        HashMap<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("msgtype", this.msgtype);

        HashMap<String, Object> actionCardMap = new HashMap<>(8);
        actionCardMap.put("title", this.title);
        actionCardMap.put("text", this.text);
        actionCardMap.put("hideAvatar", this.hideAvatar.getValue());
        actionCardMap.put("btnOrientation", this.btnOrientation.getValue());

        if (buttons.size() == 1) {
            actionCardMap.put("singleTitle", buttons.get(0).getTitle());
            actionCardMap.put("singleURL", buttons.get(0).getActionURL());
        } else if (buttons.size() > 1) {
            actionCardMap.put("btns", buttons);
        }

        resultMap.put("actionCard", actionCardMap);
        return resultMap;
    }

    /**
     * 增加操作按钮
     *
     * @param button
     */
    public void addButton(ActionCardButton button) {
        if (button == null) {
            throw new IllegalArgumentException("not allow add empty button");
        }
        if (buttons == null || buttons.size() >= MAX_BUTTON_COUNT) {
            throw new IllegalArgumentException("the number of buttons is not advise bigger than " + MAX_BUTTON_COUNT);
        }
        buttons.add(button);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HideAvatarType getHideAvatar() {
        return hideAvatar;
    }

    public void setHideAvatar(HideAvatarType hideAvatar) {
        this.hideAvatar = hideAvatar;
    }

    public ButtonOrientationType getBtnOrientation() {
        return btnOrientation;
    }

    public void setBtnOrientation(ButtonOrientationType btnOrientation) {
        this.btnOrientation = btnOrientation;
    }

    public List<ActionCardButton> getButtons() {
        return buttons;
    }
}
