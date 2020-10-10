package com.itziyou.document;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.itziyou.document.entity.GenDocumentPropertities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class GenDocumentApplicationTests {

    // 项目地址 https://github.com/pingfangushi/screw

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    GenDocumentPropertities genDocumentPropertities;

    @Test
    void run() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);

        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir(genDocumentPropertities.getFileOutputDir())
                // 是否打开目录
                .openOutputDir(genDocumentPropertities.getOpenOutputDir())
                // 文件类型
                .fileType(this.getEngineFileType(genDocumentPropertities.getFileType()))
                // 生成模板实现
                .produceType(this.getEngineTemplateType(genDocumentPropertities.getProduceType())).build();

        // 最后生成的文件名为“表名_描述_版本号”
        // 生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                .version(genDocumentPropertities.getVersion())
                .description(genDocumentPropertities.getDescription())
                .dataSource(dataSource)
                .engineConfig(engineConfig)
                .produceConfig(getProcessConfig())
                .build();

        // 执行生成
        new DocumentationExecute(config).execute();
    }

    /**
     * 配置想要生成的表+ 配置想要忽略的表
     *
     * @return 生成表配置
     */
    private ProcessConfig getProcessConfig() {
        // 表名
        List<String> tableName = genDocumentPropertities.getTableName();
        // 表前缀
        List<String> prefix = genDocumentPropertities.getPrefix();
        // 表后缀
        List<String> suffix = genDocumentPropertities.getSuffix();

        // 两种生成策略，一种是根据指定的表、表前缀、表后缀去生成；二种是忽略指定的表、表前缀、表后缀去生成。1采用忽略策略，2采用指定策略
        Integer strategy = genDocumentPropertities.getStrategy();

        switch (strategy) {
            case 2:
                return ProcessConfig.builder()
                        //根据名称指定表生成
                        .designatedTableName(tableName)
                        //根据表前缀生成
                        .designatedTablePrefix(prefix)
                        //根据表后缀生成
                        .designatedTableSuffix(suffix).build();
            default:
                return ProcessConfig.builder()
                        //忽略表名
                        .ignoreTableName(tableName)
                        //忽略表前缀
                        .ignoreTablePrefix(prefix)
                        //忽略表后缀
                        .ignoreTableSuffix(suffix).build();
        }
    }

    /**
     * 生成文件类型格式转换
     *
     * @param fileType
     * @return
     */
    private EngineFileType getEngineFileType(String fileType) {
        // 转小写
        String fileTypeLowerCase = fileType.toLowerCase();
        switch (fileTypeLowerCase) {
            case "html":
                return EngineFileType.HTML;
            case "word":
                return EngineFileType.WORD;
            default:
                return EngineFileType.MD;
        }
    }


    /**
     * 采用哪种模板生成
     *
     * @param produceType
     * @return
     */
    private EngineTemplateType getEngineTemplateType(String produceType) {
        // 转小写
        String produceTypeLowerCase = produceType.toLowerCase();
        switch (produceTypeLowerCase) {
            case "velocity":
                return EngineTemplateType.velocity;
            default:
                return EngineTemplateType.freemarker;
        }
    }

}
