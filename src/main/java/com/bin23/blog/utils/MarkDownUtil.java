package com.bin23.blog.utils;

import org.commonmark.Extension;
import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.ext.image.attributes.ImageAttributesExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class MarkDownUtil {
    /**
     * 转换md格式为html
     * 这样我们就可以借助 CommonMark 解析器
     * 将我们文章的 content 字段由 markdown 格式的字符串转换为页面显示所需的 html 片段
     * 再返回给前端显示
     * @param markdownString
     * @return
     */
    public static String mdToHtml(String markdownString) {
        if (StringUtils.isEmpty(markdownString)) {
            return "";
        }

        java.util.List<Extension> extensions = Arrays.asList(
                // 扩展
                TablesExtension.create(),           // 表格解析,通过静态方法create()获取对象
                ImageAttributesExtension.create(),  // 图片解析,通过静态方法create()获取对象
                AutolinkExtension.create(),         // url格式的文本转成链接,通过静态方法create()获取对象
                StrikethroughExtension.create(),    // 删除线解析,通过静态方法create()获取对象
                HeadingAnchorExtension.create(),    // 航向锚,自动添加biaod通过静态方法create()获取对象
                InsExtension.create(),              // 下划线解析通过静态方法create()获取对象
                YamlFrontMatterExtension.create(),  // yaml数据解析,通过静态方法create()获取对象
                TaskListItemsExtension.create()     // 任务列表解析,通过静态方法create()获取对象
        );
        // Parser.builder() 静态方法,获取Parser构造器对象，即获取Parser.Builder对象
        // Parser.Builder的方法extensions(Iterable<? extends Extension> e)是添加扩展
        Parser parser = Parser.builder().extensions(extensions).build();
        // parser.parse() 解析字符串为Node对象
        Node document = parser.parse(markdownString);
        // renderer为html渲染对象，HtmlRenderer.builder()是静态方法,获取HtmlRenderer构造器对象，及获取HtmlRenderer.Builder对象
        // HtmlRenderer.Builder的方法extensions(Iterable<? extends Extension> e)是添加扩展, 必须和Parser配置一致
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
        // render(Node node)方法，渲染为html代码
        String content = renderer.render(document);
        return content;
    }
}
