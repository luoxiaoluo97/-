package com.boki.bokiapi.value.notice;

import lombok.Getter;

/**
 * @Author: LJF
 * @Date: 2020/3/10
 * @Description:
 */
@Getter
public enum NoticeMessage {

    /**
     * 类型 1 私信通知
     */
    TYPE_1(1,"%s 在私信中回复了你：“%s”。"),
    TYPE_2(2,"%s 在“%s” %s楼回复了你：“%s”。"),
    TYPE_3(3,"%s 在“%s” %s楼 @了你。"),
    TYPE_4(4,"您的帖子“%s”被管理团队删除，原因是%s，您的信誉值-10。"),
    TYPE_5(5,"您的楼层“%s”被管理团队删除，原因是%s，您的信誉值-5。"),
    TYPE_6(6,"您的回复“%s”被管理团队删除，原因是%s，您的信誉值-5。"),
    TYPE_7(7,"您关注的大佬 %s 发帖了：“%s”。");

    private Integer type;
    private String raw;

    NoticeMessage(Integer type, String raw) {
        this.type = type;
        this.raw = raw;
    }

    public String createNotice(NoticeElem elem){
        switch (type){
            case 1:{
                return String.format(raw,elem.getFromUserName(),elem.getContent());
            }
            case 2:{
                return String.format(raw,elem.getFromUserName(),elem.getTitle(),elem.getFloorNo(),elem.getContent());
            }
            case 3:{
                return String.format(raw,elem.getFromUserName(),elem.getTitle(),elem.getFloorNo());
            }
            case 4:{
                return String.format(raw,elem.getTitle(),elem.getReason());
            }
            case 5:{
                return String.format(raw,elem.getReply(),elem.getReason());
            }
            case 6:{
                return String.format(raw,elem.getStoreyReply());
            }
            case 7:{
                return String.format(raw,elem.getFromUserName(),elem.getTitle());
            }
        }
        return null;
    }
}
