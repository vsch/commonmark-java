package com.vladsch.flexmark.util.html;

import com.vladsch.flexmark.util.collection.BitFieldSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * Used to collect line text for further processing
 * <p>
 * control output of new lines limiting them to terminate text but not create blank lines,
 * and control number of blank lines output, eliminate spaces before and after \n, other than indents
 * controlled by this class.
 * <p>
 * consecutive \n in the data are going go be collapsed to a single \n. To get blank lines need to use
 * <p>
 * tab is converted to a space if {@link #F_CONVERT_TABS} or {@link #F_COLLAPSE_WHITESPACE} option is selected
 * <p>
 * spaces before and after \n are removed controlled by {@link #F_TRIM_TRAILING_WHITESPACE} and {@link #F_TRIM_LEADING_WHITESPACE}
 * <p>
 * use {@link #line()}, {@link #lineIf(boolean)}, {@link #blankLine()}, {@link #blankLineIf(boolean)}
 * and {@link #blankLine(int)} for getting these appended to result
 */
@SuppressWarnings({ "UnusedReturnValue", "SameParameterValue" })
public interface LineAppendable extends Appendable {
    enum Options {
        CONVERT_TABS,                       // expand tabs on column multiples of 4
        COLLAPSE_WHITESPACE,                // collapse multiple tabs and spaces to single space, implies CONVERT_TABS
        TRIM_TRAILING_WHITESPACE,           // don't output trailing whitespace
        PASS_THROUGH,                       // just pass everything through to appendable with no formatting
        TRIM_LEADING_WHITESPACE,            // allow leading spaces on a line, else remove
        ALLOW_LEADING_EOL,                  // allow EOL at offset 0
        PREFIX_PRE_FORMATTED,               // when prefixing lines, prefix pre-formatted lines
    }

    Options O_CONVERT_TABS = Options.CONVERT_TABS;
    Options O_COLLAPSE_WHITESPACE = Options.COLLAPSE_WHITESPACE;
    Options O_TRIM_TRAILING_WHITESPACE = Options.TRIM_TRAILING_WHITESPACE;
    Options O_PASS_THROUGH = Options.PASS_THROUGH;
    Options O_TRIM_LEADING_WHITESPACE = Options.TRIM_LEADING_WHITESPACE;
    Options O_ALLOW_LEADING_EOL = Options.ALLOW_LEADING_EOL;
    Options O_PREFIX_PRE_FORMATTED = Options.PREFIX_PRE_FORMATTED;
    BitFieldSet<Options> O_FORMAT_ALL = BitFieldSet.of(O_CONVERT_TABS, O_COLLAPSE_WHITESPACE, O_TRIM_TRAILING_WHITESPACE, O_TRIM_LEADING_WHITESPACE);

    int F_CONVERT_TABS = BitFieldSet.intMask(O_CONVERT_TABS);                                    // expand tabs on column multiples of 4
    int F_COLLAPSE_WHITESPACE = BitFieldSet.intMask(O_COLLAPSE_WHITESPACE);                      // collapse multiple tabs and spaces to single space
    int F_TRIM_TRAILING_WHITESPACE = BitFieldSet.intMask(O_TRIM_TRAILING_WHITESPACE);            // don't output trailing whitespace
    int F_PASS_THROUGH = BitFieldSet.intMask(O_PASS_THROUGH);                                    // just pass everything through to appendable with no formatting
    // NOTE: F_ALLOW_LEADING_WHITESPACE is now inverted and named F_TRIM_LEADING_WHITESPACE
    int F_TRIM_LEADING_WHITESPACE = BitFieldSet.intMask(O_TRIM_LEADING_WHITESPACE);              // allow leading spaces on a line, else remove
    int F_ALLOW_LEADING_EOL = BitFieldSet.intMask(O_ALLOW_LEADING_EOL);                          // allow EOL at offset 0
    int F_PREFIX_PRE_FORMATTED = BitFieldSet.intMask(O_PREFIX_PRE_FORMATTED);                    // when prefixing lines, prefix pre-formatted lines
    int F_FORMAT_ALL = F_CONVERT_TABS | F_COLLAPSE_WHITESPACE | F_TRIM_TRAILING_WHITESPACE;     // select all formatting options

    int F_WHITESPACE_REMOVAL = LineAppendable.F_COLLAPSE_WHITESPACE | LineAppendable.F_TRIM_TRAILING_WHITESPACE | LineAppendable.F_TRIM_LEADING_WHITESPACE;

    // Use F_ prefixed constants
    @Deprecated int CONVERT_TABS = F_CONVERT_TABS;
    @Deprecated int COLLAPSE_WHITESPACE = F_COLLAPSE_WHITESPACE;
    @Deprecated int TRIM_TRAILING_WHITESPACE = F_TRIM_TRAILING_WHITESPACE;
    @Deprecated int PASS_THROUGH = F_PASS_THROUGH;
    // NOTE: F_ALLOW_LEADING_WHITESPACE is now inverted and named F_TRIM_LEADING_WHITESPACE
    @Deprecated int TRIM_LEADING_WHITESPACE = F_TRIM_LEADING_WHITESPACE;
    @Deprecated int ALLOW_LEADING_EOL = F_ALLOW_LEADING_EOL;
    @Deprecated int PREFIX_PRE_FORMATTED = F_PREFIX_PRE_FORMATTED;
    @Deprecated int FORMAT_ALL = F_FORMAT_ALL;

    static BitFieldSet<Options> toOptionSet(int options) {
        return BitFieldSet.of(Options.class, options);
    }

    static BitFieldSet<Options> toOptionSet(Options... options) {
        return BitFieldSet.of(Options.class, options);
    }

    /**
     * Get current options as bit mask flags
     *
     * @return option flags
     */
    default int getOptions() {
        return getOptionSet().toInt();
    }

    /**
     * Get current options as set which can be used to modify options
     *
     * @return mutable option set
     */
    @NotNull
    BitFieldSet<Options> getOptionSet();

    @NotNull
    LineAppendable pushOptions();

    @NotNull
    LineAppendable popOptions();

    @NotNull
    default LineAppendable noTrimLeading() {
        return changeOptions(0, F_TRIM_LEADING_WHITESPACE);
    }

    @NotNull
    default LineAppendable trimLeading() {
        return changeOptions(F_TRIM_LEADING_WHITESPACE, 0);
    }

    @NotNull
    default LineAppendable preserveSpaces() {
        return changeOptions(0, F_TRIM_LEADING_WHITESPACE | F_COLLAPSE_WHITESPACE);
    }

    @NotNull
    default LineAppendable noPreserveSpaces() {
        return changeOptions(F_TRIM_LEADING_WHITESPACE | F_COLLAPSE_WHITESPACE, 0);
    }

    @NotNull
    default LineAppendable removeOptions(int flags) {
        return changeOptions(0, flags);
    }

    @NotNull
    default LineAppendable addOptions(int flags) {
        return changeOptions(flags, 0);
    }

    @NotNull
    LineAppendable changeOptions(int addFlags, int removeFlags);

    /**
     * Set options on processing text
     *
     * @param flags option flags
     *
     * @return this
     */
    @NotNull
    default LineAppendable setOptions(int flags) {
        return setOptions(toOptionSet(flags));
    }

    @NotNull
    default LineAppendable setOptions(Options... options) {
        return setOptions(toOptionSet(options).toInt());
    }

    /**
     * Set options on processing text
     *
     * @param options option set
     *
     * @return this
     */
    @NotNull
    default LineAppendable setOptions(BitFieldSet<Options> options) {
        return setOptions(options.toInt());
    }

    /**
     * Get builder used for accumulation
     *
     * @return builder used for accumulation
     */
    @NotNull
    ISequenceBuilder<?, ?> getBuilder();

    // these methods are monitored for content and formatting applied
    @Override
    @NotNull LineAppendable append(@NotNull CharSequence csq);

    @Override
    @NotNull LineAppendable append(@NotNull CharSequence csq, int start, int end);

    @Override
    @NotNull LineAppendable append(char c);

    @NotNull LineAppendable append(char c, int count);

    /**
     * Append lines from another line formatting appendable.
     * <p>
     * NOTE: does not apply formatting options. Instead appends already formatted lines as is
     * <p>
     * If there is an accumulating line, it will be terminated by an EOL before appending lines
     *
     * @param lineAppendable lines to append
     *
     * @return this
     */
    @NotNull
    default LineAppendable append(@NotNull LineAppendable lineAppendable) {
        return append(lineAppendable, 0, Integer.MAX_VALUE);
    }

    /**
     * Append lines from another line formatting appendable.
     * <p>
     * NOTE: does not apply formatting options. Instead appends already formatted lines as is
     * <p>
     * If there is an accumulating line, it will be terminated by an EOL before appending lines
     *
     * @param lineAppendable lines to append
     * @param startLine      start line to append
     * @param endLine        end line to append
     *
     * @return this
     */
    @NotNull LineAppendable append(@NotNull LineAppendable lineAppendable, int startLine, int endLine);

    /**
     * Add a new line, if there was any unterminated text appended
     *
     * @return this
     */
    @NotNull LineAppendable line();

    /**
     * Add a new line, keep trailing spaces if there was any unterminated text appended
     *
     * @param count number of trailing spaces to add
     *
     * @return this
     */
    @NotNull LineAppendable lineWithTrailingSpaces(int count);

    /**
     * Add a new line, if predicate is true and there was any unterminated text appended
     *
     * @param predicate if true then new line will be started
     *
     * @return this
     */
    @NotNull LineAppendable lineIf(boolean predicate);

    /**
     * Add a blank line, if there is not one already appended.
     *
     * @return this
     */
    @NotNull LineAppendable blankLine();

    /**
     * Add a blank line, if predicate is true and there isn't already blank lines appended.
     *
     * @param predicate when true append blank line
     *
     * @return this
     */
    @NotNull LineAppendable blankLineIf(boolean predicate);

    /**
     * Add a blank lines, if there isn't already given number of blank lines appended.
     * Will append only enough blank lines to increase it to given level. If more are already in the wings then nothing is done.
     *
     * @param count number of blank lines to append
     *
     * @return this
     */
    @NotNull LineAppendable blankLine(int count);

    /**
     * @return true if in pre-formatted region
     */
    boolean isPreFormatted();

    /**
     * Open preformatted section and suspend content modification
     *
     * @param addPrefixToFirstLine if true will add current prefix to first line
     *
     * @return this
     */
    @NotNull LineAppendable openPreFormatted(boolean addPrefixToFirstLine);

    /**
     * Close preformatted section and suspend content modification
     *
     * @return this
     */
    @NotNull LineAppendable closePreFormatted();

    /**
     * Increase the indent level, will terminate the current line if there is unterminated text
     * <p>
     * NOTE: this is equivalent to pushPrefix(), addPrefix(getIndentPrefix()) but adds flag to
     * validate that {@link #unIndent()} is called only on prefixes added by indent()
     *
     * @return this
     */
    @NotNull LineAppendable indent();

    /**
     * Decrease the indent level, min level is 0, will terminate the current line if there is unterminated text
     * <p>
     * NOTE: this is equivalent to popPrefix() but with validation
     * that it is called only on prefixes added by {@link #indent()}
     *
     * @return this
     */
    @NotNull LineAppendable unIndent();

    /**
     * Decrease the indent level, if there is unterminated text then unindented prefix
     * is to be applied after the next EOL.
     * <p>
     * Will NOT terminate the current line if there is unterminated text
     * <p>
     * NOTE: should be used with {@link #addIndentOnFirstEOL(Runnable)} if callback is invoked
     *
     * @return this
     */
    @NotNull LineAppendable unIndentNoEol();

    /**
     * Get prefix appended after a new line character for every indent level
     *
     * @return char sequence of the current indent prefix used for each indent level
     */
    @NotNull CharSequence getIndentPrefix();

    /**
     * Set prefix to append after a new line character for every indent level
     *
     * @param prefix prefix characters for new lines appended after this is set
     *
     * @return this
     */
    @NotNull LineAppendable setIndentPrefix(@Nullable CharSequence prefix);

    /**
     * Get prefix being applied to all lines, even in pre-formatted sections
     * This is the prefix that will be set after EOL
     *
     * @return char sequence of the current prefix
     */
    @NotNull
    CharSequence getPrefix();

    /**
     * Get prefix used before EOL
     *
     * @return char sequence of the current prefix
     */
    @NotNull
    CharSequence getBeforeEolPrefix();

    /**
     * Add to prefix appended after a new line character for every line
     * and after a new line in pre-formatted sections
     * <p>
     * This appends the sequence to current prefix
     *
     * @param prefix prefix characters to add to current prefix for new lines appended after this is set
     *
     * @return this
     */
    @NotNull
    default LineAppendable addPrefix(@NotNull CharSequence prefix) {
        return addPrefix(prefix, getPendingEOL() == 0);
    }

    /**
     * Set prefix appended after a new line character for every line
     * and after a new line in pre-formatted sections
     * <p>
     * This appends the sequence to current prefix
     *
     * @param prefix prefix characters to add to current prefix for new lines appended after this is set
     *
     * @return this
     */
    @NotNull
    default LineAppendable setPrefix(@NotNull CharSequence prefix) {
        return setPrefix(prefix, getPendingEOL() == 0);
    }

    /**
     * Add to prefix appended after a new line character for every line
     * and after a new line in pre-formatted sections
     * <p>
     * This appends the sequence to current prefix
     *
     * @param prefix   prefix characters to add to current prefix for new lines appended after this is set
     * @param afterEol if true prefix will take effect after EOL
     *
     * @return this
     */
    @NotNull LineAppendable addPrefix(@NotNull CharSequence prefix, boolean afterEol);

    /**
     * Get pending prefix after EOL
     *
     * @return change in prefix length after next eol
     */
    int getAfterEolPrefixDelta();

    /**
     * Set prefix appended after a new line character for every line
     * and after a new line in pre-formatted sections
     * <p>
     * This appends the sequence to current prefix
     *
     * @param prefix   prefix characters to add to current prefix for new lines appended after this is set
     * @param afterEol if true prefix will take effect after EOL
     *
     * @return this
     */
    @NotNull LineAppendable setPrefix(@Nullable CharSequence prefix, boolean afterEol);

    /**
     * Save the current prefix on the stack
     *
     * @return this
     */
    @NotNull LineAppendable pushPrefix();

    /**
     * Pop a prefix from the stack and set the current prefix
     *
     * @return this
     */
    @NotNull
    default LineAppendable popPrefix() {
        return popPrefix(false);
    }

    /**
     * Pop a prefix from the stack and set the current prefix
     *
     * @param afterEol if true prefix will take effect after EOL
     *
     * @return this
     */
    @NotNull LineAppendable popPrefix(boolean afterEol);

    @NotNull
    default LineAppendable setLineOnFirstText() {
        return lineOnFirstText(true);
    }

    @NotNull
    default LineAppendable clearLineOnFirstText() {
        return lineOnFirstText(false);
    }

    /**
     * Get the number of lines appended
     *
     * @return number of lines appended
     */
    int getLineCount();

    /**
     * Get completed Line information at given line index
     *
     * @return line info
     */
    @NotNull
    LineInfo getLineInfo(int lineIndex);

    /**
     * Get completed Line at given line index
     *
     * @return line char sequence
     */
    @NotNull BasedSequence getLine(int lineIndex);

    /**
     * Get Line content of given line
     *
     * @return char sequence for the line
     */
    @NotNull
    default CharSequence getLineContent(int lineIndex) {
        LineInfo lineInfo = getLineInfo(lineIndex);
        CharSequence line = getLine(lineIndex);
        return line.subSequence(lineInfo.prefixLength, lineInfo.prefixLength + lineInfo.textLength);
    }

    /**
     * Get prefix of given line
     *
     * @return line prefix char sequence
     */
    @NotNull
    default CharSequence getLinePrefix(int lineIndex) {
        LineInfo lineInfo = getLineInfo(lineIndex);
        CharSequence line = getLine(lineIndex);
        return line.subSequence(0, lineInfo.prefixLength);
    }

//    /**
//     * Set prefix index for a given line
//     *
//     * @param lineIndex      index of the line
//     * @param prefixEndIndex index where the prefix for the line ends
//     */
//    void setLinePrefixIndex(int lineIndex, int prefixEndIndex);
//
//    /**
//     * Set content and prefix for a line
//     *
//     * @param lineIndex index of the line
//     * @param prefix    prefix of the line
//     * @param content   content text of the line
//     */
//    void setLinePrefixIndex(int lineIndex, @NotNull CharSequence prefix, @NotNull CharSequence content);

    /**
     * Get column offset after last append
     *
     * @return column offset after last append
     */
    int column();

    /**
     * Get text offset of all output lines, excluding any text for the last line being accumulated
     *
     * @return offset of text as would be returned for all
     */
    int offset();

    /**
     * Get offset after last append as if EOL was added but without the EOL itself
     *
     * @return offset as would be returned by {@link #offset()} after line() call less 1 for EOL
     */
    int offsetWithPending();

    /**
     * Test if trailing text ends in space or tab
     *
     * @return true if ending in space or tab
     */
    boolean isPendingSpace();

    /**
     * Get number of spaces at end of pending text
     *
     * @return number of eols at end of text
     */
    int getPendingSpace();

    /**
     * Get number of EOLs at end of appendable, this is actually number of tail blank lines
     *
     * @return number of eols at end of text
     */
    int getPendingEOL();

    @NotNull LineAppendable removeLines(int startLine, int endLine);

    /**
     * get the resulting text for all lines
     *
     * @param maxTrailingBlankLines maximum blank lines to allow, if -1 then no trailing EOL will be generated
     *
     * @return resulting text
     *
     * @deprecated use the two argument toString()
     */
    @NotNull
    @Deprecated
    default String toString(int maxTrailingBlankLines) {
        return toString(Integer.MAX_VALUE, maxTrailingBlankLines);
    }

    /**
     * get the resulting text for all lines
     *
     * @param maxBlankLines         maximum blank lines to allow in the text
     * @param maxTrailingBlankLines maximum trailing blank lines
     *
     * @return resulting text
     */
    @NotNull
    String toString(int maxBlankLines, int maxTrailingBlankLines);

    /**
     * append lines to appendable with given maximum trailing blank lines
     *
     * @param out                   appendable to output the resulting lines
     * @param maxTrailingBlankLines maximum trailing blank lines, if -1 then no EOL will be generated on the last line
     *
     * @throws IOException if thrown by appendable
     * @deprecated use {@link #appendTo(T, int, int)} with separate in body and trailing blank line arguments
     */
    @Deprecated
    default <T extends Appendable> T appendTo(@NotNull T out, int maxTrailingBlankLines) throws IOException {
        return appendTo(out, Integer.MAX_VALUE, maxTrailingBlankLines);
    }

    /**
     * append lines to appendable with given maximum trailing blank lines and given prefix to add to all lines
     *
     * NOTE:
     *
     * @param out                   appendable to output the resulting lines
     * @param maxBlankLines         maximum blank lines to allow in the body,
     * @param maxTrailingBlankLines maximum trailing blank lines at the end, if &lt;maxBlankLines then maxBlankLines will be used, if -1 then no trailing EOL will be added
     * @param startLine             line from which to start output
     * @param endLine               line at which to stop output
     *
     * @return out
     *
     * @throws IOException if thrown by appendable
     */
    <T extends Appendable> T appendTo(@NotNull T out, int maxBlankLines, int maxTrailingBlankLines, int startLine, int endLine) throws IOException;

    default <T extends Appendable> T appendTo(@NotNull T out, int maxBlankLines, int maxTrailingBlankLines) throws IOException {
        return appendTo(out, maxBlankLines, maxTrailingBlankLines, 0, Integer.MAX_VALUE);
    }

    /**
     * append lines to appendable with 0 blank lines, if these are desired at the end of the output use {@link #appendTo(Appendable, int, int)}.
     *
     * @param out appendable to output the resulting lines
     *
     * @throws IOException if thrown by appendable
     */
    default <T extends Appendable> T appendTo(@NotNull T out) throws IOException {
        return appendTo(out, 0, 0, 0, Integer.MAX_VALUE);
    }

    default <T extends Appendable> T appendToSilently(@NotNull T out, int maxBlankLines, int maxTrailingBlankLines, int startLine, int endLine) {
        try {
            appendTo(out, maxBlankLines, maxTrailingBlankLines, startLine, endLine);
        } catch (IOException ignored) {

        }
        return out;
    }

    default <T extends Appendable> T appendToSilently(@NotNull T out, int maxBlankLines, int maxTrailingBlankLines) {
        appendToSilently(out, maxBlankLines, maxTrailingBlankLines, 0, Integer.MAX_VALUE);
        return out;
    }

    default <T extends Appendable> T appendToSilently(@NotNull T out) {
        return appendToSilently(out, 0, 0, 0, Integer.MAX_VALUE);
    }

    /**
     * Normalize the appendable by removing extra blank lines in the body or at the end of given line range
     *
     * @param maxBlankLines         maximum blank lines to allow in the body
     * @param maxTrailingBlankLines maximum trailing blank lines ending on endLine, if &lt;maxBlankLines then maxBlankLines will be used
     * @param startLine             line from which to start output
     * @param endLine               line at which to stop output
     *
     * @return this
     */
    LineAppendable normalizeTo(int maxBlankLines, int maxTrailingBlankLines, int startLine, int endLine);

    default LineAppendable normalizeTo(int maxBlankLines, int maxTrailingBlankLines) {
        return normalizeTo(maxBlankLines, maxTrailingBlankLines, 0, Integer.MAX_VALUE);
    }

    /**
     * Iterate over individual lines
     *
     * @param maxTrailingBlankLines max blank lines
     * @param startLine             start line
     * @param endLine               end line
     * @param processor             consumer for lines
     */
    void forAllLines(int maxTrailingBlankLines, int startLine, int endLine, @NotNull LineProcessor processor);

    @NotNull LineAppendable lineOnFirstText(boolean value);

    /**
     * Add indent on first EOL appended and run runnable
     *
     * @param runnable runnable to run if adding indent on first EOL
     *
     * @return this
     */
    @NotNull LineAppendable addIndentOnFirstEOL(@NotNull Runnable runnable);

    /**
     * Remove runnable, has no effect if EOL was already appended and runnable was run
     *
     * @param runnable runnable added with addIndentOnFirstEOL
     *
     * @return this
     */
    @NotNull LineAppendable removeIndentOnFirstEOL(@NotNull Runnable runnable);
}