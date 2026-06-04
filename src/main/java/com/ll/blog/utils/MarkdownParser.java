package com.ll.blog.utils;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@UtilityClass
@Slf4j
public class MarkdownParser {
    public record ArticleMeta(String title, String slug, String summary, String cover,
                              List<String> tags, String date, String content) {}

    public static ArticleMeta parse(Path filePath) {
        try {
            String raw = Files.readString(filePath);
            // 提取 YAML Front Matter（--- 之间）
            String[] parts = raw.split("---", 3);
            String yamlStr = null;
            String content = raw;
            if (parts.length >= 3) {
                yamlStr = parts[1].trim();
                content = parts[2].trim();
            } else if (parts.length == 2) {
                yamlStr = parts[1].trim();
                content = "";
            }

            String title = null;
            String slug = filePath.getFileName().toString().replace(".md", "");
            String summary = "";
            String cover = null;
            List<String> tags = Collections.emptyList();
            String date = null;

            if (yamlStr != null && !yamlStr.isEmpty()) {
                Yaml yaml = new Yaml();
                Map<String, Object> meta = yaml.load(yamlStr);
                if (meta != null) {
                    title = asString(meta.get("title"), null);
                    slug = asString(meta.get("slug"), slug);
                    summary = asString(meta.get("summary"), "");
                    cover = asString(meta.get("cover"), null);

                    // 处理日期：SnakeYAML 可能解析为 Date 或 String
                    Object dateObj = meta.get("date");
                    if (dateObj instanceof Date) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        date = sdf.format((Date) dateObj);
                    } else if (dateObj instanceof String) {
                        date = (String) dateObj;
                    }

                    Object tagsObj = meta.get("tags");
                    if (tagsObj instanceof List) {
                        tags = ((List<?>) tagsObj).stream().map(Object::toString).toList();
                    } else if (tagsObj instanceof String) {
                        tags = Arrays.asList(tagsObj.toString().split("\\s*,\\s*"));
                    }
                }
            }

            if (title == null) title = slug;
            return new ArticleMeta(title, slug, summary, cover, tags, date, content);
        } catch (IOException e) {
            log.error("解析 Markdown 失败: {}", filePath, e);
            return null;
        }
    }

    private static String asString(Object obj, String defaultValue) {
        return obj != null ? obj.toString() : defaultValue;
    }
}
