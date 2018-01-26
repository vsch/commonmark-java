package com.vladsch.flexmark.ext.gfm.tables;

import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.AttributeProviderFactory;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.IndependentAttributeProviderFactory;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.test.RenderingTestCase;
import com.vladsch.flexmark.util.html.Attributes;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TablesTest extends RenderingTestCase {

    private static final Set<Extension> EXTENSIONS = Collections.singleton(TablesExtension.create());
    static final Parser PARSER = Parser.builder().extensions(EXTENSIONS).build();
    static final HtmlRenderer RENDERER = HtmlRenderer.builder().extensions(EXTENSIONS).build();

    @Override
    public SpecExample example() {
        return null;
    }

    @Test
    public void mustHaveHeaderAndSeparator() {
        assertRendering("Abc|Def", "<p>Abc|Def</p>\n");
        assertRendering("Abc | Def", "<p>Abc | Def</p>\n");
    }

    @Test
    public void separatorMustBeThreeOrMore() {
        assertRendering("Abc|Def\n-|-", "<p>Abc|Def\n-|-</p>\n");
        assertRendering("Abc|Def\n--|--", "<p>Abc|Def\n--|--</p>\n");
    }

    @Test
    public void separatorCanNotHaveLeadingSpaceThenPipe() {
        assertRendering("Abc|Def\n |---|---", "<p>Abc|Def\n|---|---</p>\n");
    }

    @Test
    public void headerMustBeOneLine() {
        assertRendering("No\nAbc|Def\n---|---", "<p>No\nAbc|Def\n---|---</p>\n");
    }

    @Test
    public void oneHeadNoBody() {
        assertRendering("Abc|Def\n---|---", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "</table>\n");
    }

    @Test
    public void oneColumnOneHeadNoBody() {
        String expected = "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th></tr>\n" +
                "</thead>\n" +
                "</table>\n";
        assertRendering("|Abc\n|---\n", expected);
        assertRendering("|Abc|\n|---|\n", expected);
        assertRendering("Abc|\n---|\n", expected);

        // Pipe required on separator
        assertRendering("|Abc\n---\n", "<h2>|Abc</h2>\n");
        // Pipe required on head
        assertRendering("Abc\n|---\n", "<p>Abc\n|---</p>\n");
    }

    @Test
    public void oneColumnOneHeadOneBody() {
        String expected = "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n";
        assertRendering("|Abc\n|---\n|1", expected);
        assertRendering("|Abc|\n|---|\n|1|", expected);
        assertRendering("Abc|\n---|\n1|", expected);

        // Pipe required on separator
        assertRendering("|Abc\n---\n|1", "<h2>|Abc</h2>\n<p>|1</p>\n");

        // Pipe required on body
        assertRendering("|Abc\n|---\n1\n", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th></tr>\n" +
                "</thead>\n" +
                "</table>\n" +
                "<p>1</p>\n");
    }

    @Test
    public void oneHeadOneBody() {
        assertRendering("Abc|Def\n---|---\n1|2", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void separatorMustNotHaveLessPartsThanHead() {
        assertRendering("Abc|Def|Ghi\n---|---\n1|2|3", "<p>Abc|Def|Ghi\n---|---\n1|2|3</p>\n");
    }

    @Test
    public void padding() {
        assertRendering(" Abc  | Def \n --- | --- \n 1 | 2 ", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void paddingWithCodeBlockIndentation() {
        assertRendering("Abc|Def\n---|---\n    1|2", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void pipesOnOutside() {
        assertRendering("|Abc|Def|\n|---|---|\n|1|2|", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void inlineElements() {
        assertRendering("*Abc*|Def\n---|---\n1|2", "<table>\n" +
                "<thead>\n" +
                "<tr><th><em>Abc</em></th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void escapedPipe() {
        assertRendering("Abc|Def\n---|---\n1\\|2|20", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1|2</td><td>20</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void escapedBackslash() {
        assertRendering("Abc|Def\n---|---\n1\\\\|2", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1\\</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void alignLeft() {
        assertRendering("Abc|Def\n:---|---\n1|2", "<table>\n" +
                "<thead>\n" +
                "<tr><th align=\"left\">Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td align=\"left\">1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void alignRight() {
        assertRendering("Abc|Def\n---:|---\n1|2", "<table>\n" +
                "<thead>\n" +
                "<tr><th align=\"right\">Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td align=\"right\">1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void alignCenter() {
        assertRendering("Abc|Def\n:---:|---\n1|2", "<table>\n" +
                "<thead>\n" +
                "<tr><th align=\"center\">Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td align=\"center\">1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void alignCenterSecond() {
        assertRendering("Abc|Def\n---|:---:\n1|2", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th align=\"center\">Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td><td align=\"center\">2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void alignLeftWithSpaces() {
        assertRendering("Abc|Def\n :--- |---\n1|2", "<table>\n" +
                "<thead>\n" +
                "<tr><th align=\"left\">Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td align=\"left\">1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void alignmentMarkerMustBeNextToDashes() {
        assertRendering("Abc|Def\n: ---|---", "<p>Abc|Def\n: ---|---</p>\n");
        assertRendering("Abc|Def\n--- :|---", "<p>Abc|Def\n--- :|---</p>\n");
        assertRendering("Abc|Def\n---|: ---", "<p>Abc|Def\n---|: ---</p>\n");
        assertRendering("Abc|Def\n---|--- :", "<p>Abc|Def\n---|--- :</p>\n");
    }

    @Test
    public void bodyCanNotHaveMoreColumnsThanHead() {
        assertRendering("Abc|Def\n---|---\n1|2|3", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void bodyWithFewerColumnsThanHeadResultsInEmptyCells() {
        assertRendering("Abc|Def|Ghi\n---|---|---\n1|2", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th><th>Ghi</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td><td>2</td><td></td></tr>\n" +
                "</tbody>\n" +
                "</table>\n");
    }

    @Test
    public void insideBlockQuote() {
        assertRendering("> Abc|Def\n> ---|---\n> 1|2", "<blockquote>\n" +
                "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</blockquote>\n");
    }

    @Test
    public void tableEndWithoutEmptyLine() {
        assertRendering("Abc|Def\n---|---\n1|2\ntable, you are over", "<table>\n" +
                "<thead>\n" +
                "<tr><th>Abc</th><th>Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr><td>1</td><td>2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<p>table, you are over</p>\n");
    }

    @Test
    public void attributeProviderIsApplied() {
        AttributeProviderFactory factory = new IndependentAttributeProviderFactory() {
            @Override
            public AttributeProvider create(NodeRendererContext context) {
                return new AttributeProvider() {
                    @Override
                    public void setAttributes(Node node, AttributablePart part, Attributes attributes) {
                        if (node instanceof TableBlock) {
                            attributes.replaceValue("test", "block");
                        } else if (node instanceof TableHead) {
                            attributes.replaceValue("test", "head");
                        } else if (node instanceof TableBody) {
                            attributes.replaceValue("test", "body");
                        } else if (node instanceof TableRow) {
                            attributes.replaceValue("test", "row");
                        } else if (node instanceof TableCell) {
                            attributes.replaceValue("test", "cell");
                        }
                    }
                };
            }
        };

        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(factory)
                .extensions(EXTENSIONS)
                .build();
        String rendered = renderer.render(PARSER.parse("Abc|Def\n---|---\n1|2"));
        assertThat(rendered, is("<table test=\"block\">\n" +
                "<thead test=\"head\">\n" +
                "<tr test=\"row\"><th test=\"cell\">Abc</th><th test=\"cell\">Def</th></tr>\n" +
                "</thead>\n" +
                "<tbody test=\"body\">\n" +
                "<tr test=\"row\"><td test=\"cell\">1</td><td test=\"cell\">2</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n"));
    }

    @Override
    public Parser parser() {
        return PARSER;
    }

    @Override
    public HtmlRenderer renderer() {
        return RENDERER;
    }
}
