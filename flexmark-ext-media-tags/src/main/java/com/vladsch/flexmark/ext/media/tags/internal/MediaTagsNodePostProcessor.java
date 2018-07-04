package com.vladsch.flexmark.ext.media.tags.internal;

import com.vladsch.flexmark.ast.Document;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ext.media.tags.AudioLink;
import com.vladsch.flexmark.ext.media.tags.EmbedLink;
import com.vladsch.flexmark.ext.media.tags.PictureLink;
import com.vladsch.flexmark.ext.media.tags.VideoLink;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.NodeTracker;
import com.vladsch.flexmark.util.options.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;

public class MediaTagsNodePostProcessor extends NodePostProcessor {
    public MediaTagsNodePostProcessor(DataHolder options) {
    }

    @Override
    public void process(NodeTracker state, Node node) {
        if (node instanceof Link) {
            Node previous = node.getPrevious();

            if (previous instanceof Text) {
                final BasedSequence chars = previous.getChars();
                if (chars.isContinuedBy(node.getChars())) {
                    AbstractMediaLink mediaLink;
                    if (chars.endsWith(AudioLink.PREFIX) && !isEscaped(chars, AudioLink.PREFIX)) { // AudioLink
                        mediaLink = new AudioLink((Link) node);
                    } else if (chars.endsWith(EmbedLink.PREFIX) && !isEscaped(chars, EmbedLink.PREFIX)) { // EmbedLink
                        mediaLink = new EmbedLink((Link) node);
                    } else if (chars.endsWith(PictureLink.PREFIX) && !isEscaped(chars, PictureLink.PREFIX)) { // PictureLink
                        mediaLink = new PictureLink((Link) node);
                    } else if (chars.endsWith(VideoLink.PREFIX) && !isEscaped(chars, VideoLink.PREFIX)) { // VideoLink
                        mediaLink = new VideoLink((Link) node);
                    } else { // None of the Above, abort postprocess
                        return;
                    }

                    mediaLink.takeChildren(node);
                    node.unlink();
                    state.nodeRemoved(node);
                    previous.insertAfter(mediaLink);
                    state.nodeAddedWithChildren(mediaLink);
                    previous.setChars(chars.subSequence(0, chars.length() - mediaLink.getPrefix().length()));
                    if (previous.getChars().length() == 0) {
                        previous.unlink();
                        state.nodeRemoved(previous);
                    }
                }
            }
        }
    }

    private boolean isEscaped(final BasedSequence chars, String prefix) {
        int backslashCount = chars.subSequence(0, chars.length() - prefix.length()).countTrailing('\\');
        return (backslashCount & 1) != 0;
    }

    public static class Factory extends NodePostProcessorFactory {
        public Factory(DataHolder options) {
            super(false);

            addNodes(Link.class);
        }

        @Override
        public NodePostProcessor create(Document document) {
            return new MediaTagsNodePostProcessor(document);
        }
    }
}
