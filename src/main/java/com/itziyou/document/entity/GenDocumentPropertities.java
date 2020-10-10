package com.itziyou.document.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author ziyou
 */
@Configuration
@ConfigurationProperties("zy")
public class GenDocumentPropertities {

    /**
     * 生成文件路径
     */
    private String fileOutputDir;

    /**
     * 生成文档后是否打开文件目录
     */
    private Boolean openOutputDir;

    /**
     * 生成文件类型
     */
    private String fileType;

    /**
     * 生成模板实现
     */
    private String produceType;

    /**
     * 生成文件名的版本
     */
    private String version;

    /**
     * 生成文档信息描述
     */
    private String description;

    /**
     * 两种生成策略，
     * 一种是根据指定的表、表前缀、表后缀去生成；
     * 二种是忽略指定的表、表前缀、表后缀去生成。
     * 1 忽略策略 2指定策略
     */
    private Integer strategy;

    /**
     * 表名
     */
    private List<String> tableName;

    /**
     * 表前缀
     */
    private List<String> prefix;

    /**
     * 表后缀
     */
    private List<String> suffix;

    public String getFileOutputDir() {
        return fileOutputDir;
    }

    public void setFileOutputDir(String fileOutputDir) {
        this.fileOutputDir = fileOutputDir;
    }

    public Boolean getOpenOutputDir() {
        return openOutputDir;
    }

    public void setOpenOutputDir(Boolean openOutputDir) {
        this.openOutputDir = openOutputDir;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getProduceType() {
        return produceType;
    }

    public void setProduceType(String produceType) {
        this.produceType = produceType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTableName() {
        return tableName;
    }

    public void setTableName(List<String> tableName) {
        this.tableName = tableName;
    }

    public List<String> getPrefix() {
        return prefix;
    }

    public void setPrefix(List<String> prefix) {
        this.prefix = prefix;
    }

    public List<String> getSuffix() {
        return suffix;
    }

    public void setSuffix(List<String> suffix) {
        this.suffix = suffix;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }
}
