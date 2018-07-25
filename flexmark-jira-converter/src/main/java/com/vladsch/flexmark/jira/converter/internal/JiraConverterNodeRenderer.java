package com.vladsch.flexmark.jira.converter.internal;

import com.vladsch.flexmark.ast.*;
import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.ast.util.TextCollectingVisitor;
import com.vladsch.flexmark.html.CustomNodeRenderer;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.*;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.html.Escaping;
import com.vladsch.flexmark.util.options.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.vladsch.flexmark.util.sequence.BasedSequence.NULL;

public class JiraConverterNodeRenderer implements NodeRenderer
{
    private final ReferenceRepository referenceRepository;
    private final ListOptions listOptions;
    private int inBlockQuote = 0;

    public JiraConverterNodeRenderer(DataHolder options) {
        this.referenceRepository = options.get(Parser.REFERENCES);
        this.listOptions = ListOptions.getFrom(options);
    }

    @Override
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet<NodeRenderingHandler<? extends Node>>(Arrays.asList(
                new NodeRenderingHandler<AutoLink>(AutoLink.class, new CustomNodeRenderer<AutoLink>() {
                    @Override
                    public void render(AutoLink node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<BlockQuote>(BlockQuote.class, new CustomNodeRenderer<BlockQuote>() {
                    @Override
                    public void render(BlockQuote node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<BulletList>(BulletList.class, new CustomNodeRenderer<BulletList>() {
                    @Override
                    public void render(BulletList node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<BulletListItem>(BulletListItem.class, new CustomNodeRenderer<BulletListItem>() {
                    @Override
                    public void render(BulletListItem node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<Code>(Code.class, new CustomNodeRenderer<Code>() {
                    @Override
                    public void render(Code node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<Document>(Document.class, new CustomNodeRenderer<Document>() {
                    @Override
                    public void render(Document node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<Emphasis>(Emphasis.class, new CustomNodeRenderer<Emphasis>() {
                    @Override
                    public void render(Emphasis node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<FencedCodeBlock>(FencedCodeBlock.class, new CustomNodeRenderer<FencedCodeBlock>() {
                    @Override
                    public void render(FencedCodeBlock node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<HardLineBreak>(HardLineBreak.class, new CustomNodeRenderer<HardLineBreak>() {
                    @Override
                    public void render(HardLineBreak node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<Heading>(Heading.class, new CustomNodeRenderer<Heading>() {
                    @Override
                    public void render(Heading node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<HtmlBlock>(HtmlBlock.class, new CustomNodeRenderer<HtmlBlock>() {
                    @Override
                    public void render(HtmlBlock node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<HtmlCommentBlock>(HtmlCommentBlock.class, new CustomNodeRenderer<HtmlCommentBlock>() {
                    @Override
                    public void render(HtmlCommentBlock node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<HtmlEntity>(HtmlEntity.class, new CustomNodeRenderer<HtmlEntity>() {
                    @Override
                    public void render(HtmlEntity node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<HtmlInline>(HtmlInline.class, new CustomNodeRenderer<HtmlInline>() {
                    @Override
                    public void render(HtmlInline node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<HtmlInlineComment>(HtmlInlineComment.class, new CustomNodeRenderer<HtmlInlineComment>() {
                    @Override
                    public void render(HtmlInlineComment node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<HtmlInnerBlock>(HtmlInnerBlock.class, new CustomNodeRenderer<HtmlInnerBlock>() {
                    @Override
                    public void render(HtmlInnerBlock node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<HtmlInnerBlockComment>(HtmlInnerBlockComment.class, new CustomNodeRenderer<HtmlInnerBlockComment>() {
                    @Override
                    public void render(HtmlInnerBlockComment node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<Image>(Image.class, new CustomNodeRenderer<Image>() {
                    @Override
                    public void render(Image node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<ImageRef>(ImageRef.class, new CustomNodeRenderer<ImageRef>() {
                    @Override
                    public void render(ImageRef node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<IndentedCodeBlock>(IndentedCodeBlock.class, new CustomNodeRenderer<IndentedCodeBlock>() {
                    @Override
                    public void render(IndentedCodeBlock node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<Link>(Link.class, new CustomNodeRenderer<Link>() {
                    @Override
                    public void render(Link node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<LinkRef>(LinkRef.class, new CustomNodeRenderer<LinkRef>() {
                    @Override
                    public void render(LinkRef node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<MailLink>(MailLink.class, new CustomNodeRenderer<MailLink>() {
                    @Override
                    public void render(MailLink node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<OrderedList>(OrderedList.class, new CustomNodeRenderer<OrderedList>() {
                    @Override
                    public void render(OrderedList node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<OrderedListItem>(OrderedListItem.class, new CustomNodeRenderer<OrderedListItem>() {
                    @Override
                    public void render(OrderedListItem node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<Paragraph>(Paragraph.class, new CustomNodeRenderer<Paragraph>() {
                    @Override
                    public void render(Paragraph node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<Reference>(Reference.class, new CustomNodeRenderer<Reference>() {
                    @Override
                    public void render(Reference node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<SoftLineBreak>(SoftLineBreak.class, new CustomNodeRenderer<SoftLineBreak>() {
                    @Override
                    public void render(SoftLineBreak node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<StrongEmphasis>(StrongEmphasis.class, new CustomNodeRenderer<StrongEmphasis>() {
                    @Override
                    public void render(StrongEmphasis node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<Text>(Text.class, new CustomNodeRenderer<Text>() {
                    @Override
                    public void render(Text node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<TextBase>(TextBase.class, new CustomNodeRenderer<TextBase>() {
                    @Override
                    public void render(TextBase node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                }),
                new NodeRenderingHandler<ThematicBreak>(ThematicBreak.class, new CustomNodeRenderer<ThematicBreak>() {
                    @Override
                    public void render(ThematicBreak node, NodeRendererContext context, HtmlWriter html) { JiraConverterNodeRenderer.this.render(node, context, html); }
                })
        ));
    }

    private void render(Document node, NodeRendererContext context, HtmlWriter html) {
        // No rendering itself
        context.renderChildren(node);
    }

    private void render(Heading node, NodeRendererContext context, HtmlWriter html) {
        html.line().raw("h" + node.getLevel() + ". ");
        context.renderChildren(node);
        html.blankLine();
    }

    private void render(BlockQuote node, NodeRendererContext context, HtmlWriter html) {
        html.line().raw("{quote}").line();
        inBlockQuote++;
        context.renderChildren(node);
        inBlockQuote--;
        html.line().raw("{quote}").blankLine();
    }

    private void render(FencedCodeBlock node, NodeRendererContext context, HtmlWriter html) {
        BasedSequence info = node.getInfo();
        if (info.isNotNull() && !info.isBlank()) {
            html.line().raw("{code:lang=" + info.unescape() + "}").line();
        } else {
            html.line().raw("{code}").line();
        }

        html.raw(node.getContentChars().normalizeEOL());
        html.line().raw("{code}").blankLine();
    }

    private void render(ThematicBreak node, NodeRendererContext context, HtmlWriter html) {
        html.line().raw("----").blankLine();
    }

    private void render(IndentedCodeBlock node, NodeRendererContext context, HtmlWriter html) {
        html.line().raw("{noformat}").line();
        html.raw(node.getContentChars().trimTailBlankLines().normalizeEndWithEOL());
        html.line().raw("{noformat}").line();
    }

    private void renderListItemPrefix(ListItem node, NodeRendererContext context, HtmlWriter html) {
        Node parent = node;
        StringBuilder prefix = new StringBuilder();
        while (parent instanceof ListBlock || parent instanceof ListItem) {
            if (parent instanceof BulletList) {
                prefix.append('*');
            } else if (parent instanceof OrderedList) {
                prefix.append('#');
            }
            parent = parent.getParent();
        }

        if (prefix.length() > 0) {
            prefix.append(' ');
        }
        html.line().raw(prefix.toString());
    }

    private void renderListItem(ListItem node, NodeRendererContext context, HtmlWriter html) {
        renderListItemPrefix(node, context, html);
        if (listOptions.isTightListItem(node)) {
            context.renderChildren(node);
        } else {
            context.renderChildren(node);
            if (node.getFirstChild().getNext() != null) {
                html.blankLine();
            }
        }
    }

    private void renderList(ListBlock node, NodeRendererContext context, HtmlWriter html) {
        context.renderChildren(node);
        if (node.getParent() instanceof Document) {
            if (node.getLastChild() == null || listOptions.isTightListItem((ListItem) node.getLastChild())) {
                html.blankLine();
            }
        }
    }

    private void render(BulletList node, NodeRendererContext context, HtmlWriter html) {
        renderList(node, context, html);
    }

    private void render(OrderedList node, NodeRendererContext context, HtmlWriter html) {
        renderList(node, context, html);
    }

    private void render(BulletListItem node, NodeRendererContext context, HtmlWriter html) {
        renderListItem(node, context, html);
    }

    private void render(OrderedListItem node, NodeRendererContext context, HtmlWriter html) {
        renderListItem(node, context, html);
    }

    private static void renderTextBlockParagraphLines(Node node, NodeRendererContext context, HtmlWriter html) {
        context.renderChildren(node);
        html.line();
    }

    private void renderLooseParagraph(final Paragraph node, final NodeRendererContext context, final HtmlWriter html) {
        renderTextBlockParagraphLines(node, context, html);

        if (inBlockQuote > 0 && node.getNext() == null) {
            html.line();
        } else {
            html.blankLine();
        }
    }

    private void render(Paragraph node, NodeRendererContext context, HtmlWriter html) {
        if (!(node.getParent() instanceof ParagraphItemContainer)
                || !((ParagraphItemContainer) node.getParent()).isParagraphWrappingDisabled(node, listOptions, context.getOptions())) {
            renderLooseParagraph(node, context, html);
        } else {
            renderTextBlockParagraphLines(node, context, html);
        }
    }

    public static BasedSequence getSoftLineBreakSpan(Node node) {
        if (node == null) return NULL;

        Node lastNode = node;
        Node nextNode = node.getNext();

        while (nextNode != null && !(nextNode instanceof SoftLineBreak)) {
            lastNode = nextNode;
            nextNode = nextNode.getNext();
        }

        return Node.spanningChars(node.getChars(), lastNode.getChars());
    }

    private void render(SoftLineBreak node, NodeRendererContext context, HtmlWriter html) {
        html.raw(" ");
    }

    private void render(HardLineBreak node, NodeRendererContext context, HtmlWriter html) {
        //html.raw("\\\\");
        html.line();
    }

    private void render(Emphasis node, NodeRendererContext context, HtmlWriter html) {
        html.raw("_");
        context.renderChildren(node);
        html.raw("_");
    }

    private void render(StrongEmphasis node, NodeRendererContext context, HtmlWriter html) {
        html.raw("*");
        context.renderChildren(node);
        html.raw("*");
    }

    private void render(Text node, NodeRendererContext context, HtmlWriter html) {
        html.raw(Escaping.normalizeEOL(node.getChars().unescape()));
    }

    private void render(TextBase node, NodeRendererContext context, HtmlWriter html) {
        context.renderChildren(node);
    }

    private void render(Code node, NodeRendererContext context, HtmlWriter html) {
        html.raw("{{");
        html.raw(Escaping.collapseWhitespace(node.getText(), true));
        html.raw("}}");
    }

    private void render(HtmlBlock node, NodeRendererContext context, HtmlWriter html) {
        if (node.hasChildren()) {
            // inner blocks handle rendering
            context.renderChildren(node);
        } else {
            renderHtmlBlock(node, context, html, context.getHtmlOptions().suppressHtmlBlocks, context.getHtmlOptions().escapeHtmlBlocks);
        }
    }

    private void render(HtmlCommentBlock node, NodeRendererContext context, HtmlWriter html) {
        renderHtmlBlock(node, context, html, context.getHtmlOptions().suppressHtmlCommentBlocks, context.getHtmlOptions().escapeHtmlCommentBlocks);
    }

    private void render(HtmlInnerBlock node, NodeRendererContext context, HtmlWriter html) {
        renderHtmlBlock(node, context, html, context.getHtmlOptions().suppressHtmlBlocks, context.getHtmlOptions().escapeHtmlBlocks);
    }

    private void render(HtmlInnerBlockComment node, NodeRendererContext context, HtmlWriter html) {
        renderHtmlBlock(node, context, html, context.getHtmlOptions().suppressHtmlCommentBlocks, context.getHtmlOptions().escapeHtmlCommentBlocks);
    }

    public void renderHtmlBlock(HtmlBlockBase node, NodeRendererContext context, HtmlWriter html, boolean suppress, boolean escape) {
        if (suppress) return;

        html.line().raw("{code:html}").line();
        html.raw(node.getContentChars().normalizeEOL());
        html.line().raw("{code:html}").line();
    }

    private void render(HtmlInline node, NodeRendererContext context, HtmlWriter html) {
        renderInlineHtml(node, context, html, context.getHtmlOptions().suppressInlineHtml, context.getHtmlOptions().escapeInlineHtml);
    }

    private void render(HtmlInlineComment node, NodeRendererContext context, HtmlWriter html) {
        renderInlineHtml(node, context, html, context.getHtmlOptions().suppressInlineHtmlComments, context.getHtmlOptions().escapeInlineHtmlComments);
    }

    public void renderInlineHtml(HtmlInlineBase node, NodeRendererContext context, HtmlWriter html, boolean suppress, boolean escape) {
        if (suppress) return;
        html.raw("{{").raw(node.getChars().normalizeEOL()).raw("}}");
    }

    private void render(Reference node, NodeRendererContext context, HtmlWriter html) {

    }

    private void render(HtmlEntity node, NodeRendererContext context, HtmlWriter html) {
        html.raw(node.getChars().unescape());
    }

    private void render(AutoLink node, NodeRendererContext context, HtmlWriter html) {
        BasedSequence text = node.getText();
        if (context.isDoNotRenderLinks()) {
            html.text(text);
        } else {
            ResolvedLink resolvedLink = context.resolveLink(LinkType.LINK, text, null);
            html.raw("[").raw(text).raw("|").raw(resolvedLink.getUrl());
        }
    }

    private void render(MailLink node, NodeRendererContext context, HtmlWriter html) {
        String text = node.getText().unescape();
        if (context.isDoNotRenderLinks()) {
            html.text(text);
        } else {
            ResolvedLink resolvedLink = context.resolveLink(LinkType.LINK, text, null);
            html.raw("[").raw(text).raw("|mailto:").raw(resolvedLink.getUrl()).raw("]");
        }
    }

    private void render(Image node, NodeRendererContext context, HtmlWriter html) {
        if (!context.isDoNotRenderLinks()) {
            String altText = new TextCollectingVisitor().collectAndGetText(node);

            ResolvedLink resolvedLink = context.resolveLink(LinkType.IMAGE, node.getUrl().unescape(), null);
            html.raw("!").raw(resolvedLink.getUrl()).raw("!");
        }
    }

    private void render(Link node, NodeRendererContext context, HtmlWriter html) {
        if (context.isDoNotRenderLinks()) {
            context.renderChildren(node);
        } else {
            ResolvedLink resolvedLink = context.resolveLink(LinkType.LINK, node.getUrl().unescape(), null);
            html.raw("[");
            context.renderChildren(node);
            html.raw("|").raw(resolvedLink.getUrl());
            if (node.getTitle() != null && !node.getTitle().isEmpty()) {
                html.raw("|").raw(node.getTitle());
            }
            html.raw("]");
        }
    }

    private void render(ImageRef node, NodeRendererContext context, HtmlWriter html) {
        if (!node.isDefined()) {
            // empty ref, we treat it as text
            assert !node.isDefined();
            html.text(node.getChars().unescape());
        } else {
            if (!context.isDoNotRenderLinks()) {
                Reference reference = node.getReferenceNode(referenceRepository);
                assert reference != null;
                String altText = new TextCollectingVisitor().collectAndGetText(node);

                ResolvedLink resolvedLink = context.resolveLink(LinkType.IMAGE, reference.getUrl().unescape(), null);
                html.raw("!").raw(resolvedLink.getUrl()).raw("!");
            }
        }
    }

    private void render(LinkRef node, NodeRendererContext context, HtmlWriter html) {
        if (!node.isDefined()) {
            // empty ref, we treat it as text
            assert !node.isDefined();
            html.raw("[");
            context.renderChildren(node);
            html.raw("]");

            if (!node.isReferenceTextCombined()) {
                html.raw("[");
                html.raw(node.getReference().unescape());
                html.raw("]");
            }
        } else {
            if (context.isDoNotRenderLinks()) {
                context.renderChildren(node);
            } else {
                Reference reference = node.getReferenceNode(referenceRepository);
                assert reference != null;

                ResolvedLink resolvedLink = context.resolveLink(LinkType.LINK, reference.getUrl().unescape(), null);

                html.raw("[");
                context.renderChildren(node);
                html.raw("|");
                html.raw(resolvedLink.getUrl());
                html.raw("]");
            }
        }
    }

    public static class Factory implements NodeRendererFactory {
        @Override
        public NodeRenderer create(final DataHolder options) {
            return new JiraConverterNodeRenderer(options);
        }
    }
}
