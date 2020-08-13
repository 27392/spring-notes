package cn.haohaoli.demo.annotation.scan.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * `@ComponentScan`自定义过滤器
 * @author LiWenHao
 */
@Slf4j
public class CustomFilter implements TypeFilter {

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        log.info("{}", metadataReader.getClassMetadata().getClassName());
        return metadataReader.getClassMetadata().getClassName().contains("e");
    }
}