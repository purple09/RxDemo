package com.purple.rxdemo.fastjson;

/**
 * @Description: TODO describe this class
 * @Copyright: Copyright (c) 2016 chexiang.com. All right reserved.
 * @Author: guizhen
 * @Date: 2016/2/3 17:39
 * @Modifier: guizhen
 * @Update: 2016/2/3 17:39
 */
public class WordResult {
    /*result 内容状态, 0 正常内容  1 命中了脏词  2 内容很水
    categoryId 脏词的分类id
    categoryName 脏词的分类名
    nature 脏词类型  1 黑名单  2 灰名单
    words 命中的脏词
    msg 信息提示*/
    private String result;
    private String categoryId;
    private String categoryName;
    private String nature;
    private String words;
    private String msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
