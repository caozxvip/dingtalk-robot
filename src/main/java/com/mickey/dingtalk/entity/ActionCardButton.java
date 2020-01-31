package com.mickey.dingtalk.entity;

/**
 * 跳转卡片中的按钮实体类
 */
public class ActionCardButton {

    /**
     * 按钮标题
     */
    private String title;

    /**
     * 实际点击时调用的URL
     */
    private String actionURL;

    public ActionCardButton() {
    }

    public ActionCardButton(String title, String actionURL) {
        this.title = title;
        this.actionURL = actionURL;
    }

    public static ActionCardButton defaultReadButton(String actionURL) {
        ActionCardButton button = new ActionCardButton();
        button.setTitle("阅读全文");
        button.setActionURL(actionURL);
        return button;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActionURL() {
        return actionURL;
    }

    public void setActionURL(String actionURL) {
        this.actionURL = actionURL;
    }
}
