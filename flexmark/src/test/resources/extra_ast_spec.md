---
title: Core Extra Test Spec
author: Vladimir Schneider
version: 0.2
date: '2016-06-06'
license: '[CC-BY-SA 4.0](http://creativecommons.org/licenses/by-sa/4.0/)'
...

---

## Extra tests

Should be ignored

```````````````````````````````` example(Extra tests: 1) options(IGNORE)
```markdown
---
```
.
<pre><code class="language-markdown">---
</code></pre>
.
Document[0, 20]
  FencedCodeBlock[0, 19] open:[0, 3, "```"] info:[3, 11, "markdown"] content:[12, 16] lines[3] close:[16, 19, "```"]
````````````````````````````````


Should fail

```````````````````````````````` example(Extra tests: 2) options(FAIL)
```markdown
abc
```
.
<pre><code class="language-markdown">---
</code></pre>
.
Document[0, 20]
  FencedCodeBlock[0, 19] open:[0, 3, "```"] info:[3, 11, "markdown"] content:[12, 16] lines[3] close:[16, 19, "```"]
````````````````````````````````


Code fence starting with setext header marker

```````````````````````````````` example Extra tests: 3
```markdown
---
```
.
<pre><code class="language-markdown">---
</code></pre>
.
Document[0, 20]
  FencedCodeBlock[0, 19] open:[0, 3, "```"] info:[3, 11, "markdown"] content:[12, 16] lines[1] close:[16, 19, "```"]
    Text[12, 16] chars:[12, 16, "---\n"]
````````````````````````````````


```````````````````````````````` example Extra tests: 4
```markdown
===
```
.
<pre><code class="language-markdown">===
</code></pre>
.
Document[0, 20]
  FencedCodeBlock[0, 19] open:[0, 3, "```"] info:[3, 11, "markdown"] content:[12, 16] lines[1] close:[16, 19, "```"]
    Text[12, 16] chars:[12, 16, "===\n"]
````````````````````````````````


Make sure indentation is properly implemented

```````````````````````````````` example Extra tests: 5
> - item 1
> - item 2
>     1. item 1
>     2. item 2
.
<blockquote>
  <ul>
    <li>item 1</li>
    <li>item 2
      <ol>
        <li>item 1</li>
        <li>item 2</li>
      </ol>
    </li>
  </ul>
</blockquote>
.
Document[0, 54]
  BlockQuote[0, 54] marker:[0, 1, ">"]
    BulletList[2, 54] isTight
      BulletListItem[2, 11] open:[2, 3, "-"] isTight
        Paragraph[4, 11]
          Text[4, 10] chars:[4, 10, "item 1"]
      BulletListItem[13, 54] open:[13, 14, "-"] isTight
        Paragraph[15, 22]
          Text[15, 21] chars:[15, 21, "item 2"]
        OrderedList[28, 54] isTight delimiter:'.'
          OrderedListItem[28, 38] open:[28, 30, "1."] isTight
            Paragraph[31, 38]
              Text[31, 37] chars:[31, 37, "item 1"]
          OrderedListItem[44, 54] open:[44, 46, "2."] isTight
            Paragraph[47, 54]
              Text[47, 53] chars:[47, 53, "item 2"]
````````````````````````````````


Make sure indentation is properly implemented

```````````````````````````````` example Extra tests: 6
> - item 1
>
> - item 2
>
>     1. item 1
>
>     2. item 2
.
<blockquote>
  <ul>
    <li>
      <p>item 1</p>
    </li>
    <li>
      <p>item 2</p>
      <ol>
        <li>
          <p>item 1</p>
        </li>
        <li>
          <p>item 2</p>
        </li>
      </ol>
    </li>
  </ul>
</blockquote>
.
Document[0, 60]
  BlockQuote[0, 60] marker:[0, 1, ">"]
    BulletList[2, 60] isLoose
      BulletListItem[2, 11] open:[2, 3, "-"] isLoose hadBlankLineAfter
        Paragraph[4, 11] isTrailingBlankLine
          Text[4, 10] chars:[4, 10, "item 1"]
      BulletListItem[15, 60] open:[15, 16, "-"] isLoose hadBlankLineAfter
        Paragraph[17, 24] isTrailingBlankLine
          Text[17, 23] chars:[17, 23, "item 2"]
        OrderedList[32, 60] isLoose delimiter:'.'
          OrderedListItem[32, 42] open:[32, 34, "1."] isLoose hadBlankLineAfter
            Paragraph[35, 42] isTrailingBlankLine
              Text[35, 41] chars:[35, 41, "item 1"]
          OrderedListItem[50, 60] open:[50, 52, "2."] isLoose
            Paragraph[53, 60]
              Text[53, 59] chars:[53, 59, "item 2"]
````````````````````````````````


```````````````````````````````` example Extra tests: 7
- item 
  - sub item  
with lazy continuation
.
<ul>
  <li>item
    <ul>
      <li>sub item<br />
      with lazy continuation</li>
    </ul>
  </li>
</ul>
.
Document[0, 46]
  BulletList[0, 46] isTight
    BulletListItem[0, 46] open:[0, 1, "-"] isTight
      Paragraph[2, 8]
        Text[2, 6] chars:[2, 6, "item"]
      BulletList[10, 46] isTight
        BulletListItem[10, 46] open:[10, 11, "-"] isTight
          Paragraph[12, 46]
            Text[12, 20] chars:[12, 20, "sub item"]
            HardLineBreak[20, 23]
            Text[23, 45] chars:[23, 45, "with  … ation"]
````````````````````````````````


```````````````````````````````` example Extra tests: 8
- item 
  - sub item  
            with lazy continuation
.
<ul>
  <li>item
    <ul>
      <li>sub item<br />
      with lazy continuation</li>
    </ul>
  </li>
</ul>
.
Document[0, 58]
  BulletList[0, 58] isTight
    BulletListItem[0, 58] open:[0, 1, "-"] isTight
      Paragraph[2, 8]
        Text[2, 6] chars:[2, 6, "item"]
      BulletList[10, 58] isTight
        BulletListItem[10, 58] open:[10, 11, "-"] isTight
          Paragraph[12, 58]
            Text[12, 20] chars:[12, 20, "sub item"]
            HardLineBreak[20, 23]
            Text[35, 57] chars:[35, 57, "with  … ation"]
````````````````````````````````


headings should contain inlines

```````````````````````````````` example Extra tests: 9
# Atx Heading with **bold** and _italic_ and `code`

Setext Heading with **bold** and _italic_ and `code`
----------------------------------------------------
.
<h1>Atx Heading with <strong>bold</strong> and <em>italic</em> and <code>code</code></h1>
<h2>Setext Heading with <strong>bold</strong> and <em>italic</em> and <code>code</code></h2>
.
Document[0, 159]
  Heading[0, 51] textOpen:[0, 1, "#"] text:[2, 51, "Atx Heading with **bold** and _italic_ and `code`"]
    Text[2, 19] chars:[2, 19, "Atx H … with "]
    StrongEmphasis[19, 27] textOpen:[19, 21, "**"] text:[21, 25, "bold"] textClose:[25, 27, "**"]
      Text[21, 25] chars:[21, 25, "bold"]
    Text[27, 32] chars:[27, 32, " and "]
    Emphasis[32, 40] textOpen:[32, 33, "_"] text:[33, 39, "italic"] textClose:[39, 40, "_"]
      Text[33, 39] chars:[33, 39, "italic"]
    Text[40, 45] chars:[40, 45, " and "]
    Code[45, 51] textOpen:[45, 46, "`"] text:[46, 50, "code"] textClose:[50, 51, "`"]
      Text[46, 50] chars:[46, 50, "code"]
  Heading[53, 158] text:[53, 105, "Setext Heading with **bold** and _italic_ and `code`"] textClose:[106, 158, "----------------------------------------------------"]
    Text[53, 73] chars:[53, 73, "Setex … with "]
    StrongEmphasis[73, 81] textOpen:[73, 75, "**"] text:[75, 79, "bold"] textClose:[79, 81, "**"]
      Text[75, 79] chars:[75, 79, "bold"]
    Text[81, 86] chars:[81, 86, " and "]
    Emphasis[86, 94] textOpen:[86, 87, "_"] text:[87, 93, "italic"] textClose:[93, 94, "_"]
      Text[87, 93] chars:[87, 93, "italic"]
    Text[94, 99] chars:[94, 99, " and "]
    Code[99, 105] textOpen:[99, 100, "`"] text:[100, 104, "code"] textClose:[104, 105, "`"]
      Text[100, 104] chars:[100, 104, "code"]
````````````````````````````````


## Reference Repository Keep First tests

Test repository KEEP_FIRST behavior, meaning the first reference def is used

```````````````````````````````` example Reference Repository Keep First tests: 1
[ref]

[ref]: /url1
[ref]: /url2
[ref]: /url3
.
<p><a href="/url1">ref</a></p>
.
Document[0, 46]
  Paragraph[0, 6] isTrailingBlankLine
    LinkRef[0, 5] referenceOpen:[0, 1, "["] reference:[1, 4, "ref"] referenceClose:[4, 5, "]"]
      Text[1, 4] chars:[1, 4, "ref"]
  Reference[7, 19] refOpen:[7, 8, "["] ref:[8, 11, "ref"] refClose:[11, 13, "]:"] url:[14, 19, "/url1"]
  Reference[20, 32] refOpen:[20, 21, "["] ref:[21, 24, "ref"] refClose:[24, 26, "]:"] url:[27, 32, "/url2"]
  Reference[33, 45] refOpen:[33, 34, "["] ref:[34, 37, "ref"] refClose:[37, 39, "]:"] url:[40, 45, "/url3"]
````````````````````````````````


## Reference Repository Keep Last tests

Test repository KEEP_LAST behavior, meaning the last reference def is used

```````````````````````````````` example(Reference Repository Keep Last tests: 1) options(keep-last)
[ref]

[ref]: /url1
[ref]: /url2
[ref]: /url3
.
<p><a href="/url3">ref</a></p>
.
Document[0, 45]
  Paragraph[0, 6] isTrailingBlankLine
    LinkRef[0, 5] referenceOpen:[0, 1, "["] reference:[1, 4, "ref"] referenceClose:[4, 5, "]"]
      Text[1, 4] chars:[1, 4, "ref"]
  Reference[7, 19] refOpen:[7, 8, "["] ref:[8, 11, "ref"] refClose:[11, 13, "]:"] url:[14, 19, "/url1"]
  Reference[20, 32] refOpen:[20, 21, "["] ref:[21, 24, "ref"] refClose:[24, 26, "]:"] url:[27, 32, "/url2"]
  Reference[33, 45] refOpen:[33, 34, "["] ref:[34, 37, "ref"] refClose:[37, 39, "]:"] url:[40, 45, "/url3"]
````````````````````````````````


## References

References with up to 3 leading blanks should be processed.

```````````````````````````````` example References: 1
[ref]

 [ref]: /url1
.
<p><a href="/url1">ref</a></p>
.
Document[0, 21]
  Paragraph[0, 6] isTrailingBlankLine
    LinkRef[0, 5] referenceOpen:[0, 1, "["] reference:[1, 4, "ref"] referenceClose:[4, 5, "]"]
      Text[1, 4] chars:[1, 4, "ref"]
  Reference[8, 20] refOpen:[8, 9, "["] ref:[9, 12, "ref"] refClose:[12, 14, "]:"] url:[15, 20, "/url1"]
````````````````````````````````


References with up to 3 leading blanks should be processed.

```````````````````````````````` example References: 2
[ref]

  [ref]: /url1
.
<p><a href="/url1">ref</a></p>
.
Document[0, 22]
  Paragraph[0, 6] isTrailingBlankLine
    LinkRef[0, 5] referenceOpen:[0, 1, "["] reference:[1, 4, "ref"] referenceClose:[4, 5, "]"]
      Text[1, 4] chars:[1, 4, "ref"]
  Reference[9, 21] refOpen:[9, 10, "["] ref:[10, 13, "ref"] refClose:[13, 15, "]:"] url:[16, 21, "/url1"]
````````````````````````````````


References with up to 3 leading blanks should be processed.

```````````````````````````````` example References: 3
[ref]

   [ref]: /url1
.
<p><a href="/url1">ref</a></p>
.
Document[0, 23]
  Paragraph[0, 6] isTrailingBlankLine
    LinkRef[0, 5] referenceOpen:[0, 1, "["] reference:[1, 4, "ref"] referenceClose:[4, 5, "]"]
      Text[1, 4] chars:[1, 4, "ref"]
  Reference[10, 22] refOpen:[10, 11, "["] ref:[11, 14, "ref"] refClose:[14, 16, "]:"] url:[17, 22, "/url1"]
````````````````````````````````


References with up to 3 leading blanks should be processed.

```````````````````````````````` example References: 4
[ref]

    [ref]: /url1
.
<p>[ref]</p>
<pre><code>[ref]: /url1
</code></pre>
.
Document[0, 24]
  Paragraph[0, 6] isTrailingBlankLine
    LinkRef[0, 5] referenceOpen:[0, 1, "["] reference:[1, 4, "ref"] referenceClose:[4, 5, "]"]
      Text[1, 4] chars:[1, 4, "ref"]
  IndentedCodeBlock[11, 24]
````````````````````````````````


## Heading options

If allow atx headers without a space between # and the title is false, don't treat bare `#`'s
without a trailing space as empty headings

```````````````````````````````` example(Heading options: 1) options(no-empty-heading-without-space)
#

##

###

####

#####

######

.
<p>#</p>
<p>##</p>
<p>###</p>
<p>####</p>
<p>#####</p>
<p>######</p>
.
Document[0, 33]
  Paragraph[0, 2] isTrailingBlankLine
    Text[0, 1] chars:[0, 1, "#"]
  Paragraph[3, 6] isTrailingBlankLine
    Text[3, 5] chars:[3, 5, "##"]
  Paragraph[7, 11] isTrailingBlankLine
    Text[7, 10] chars:[7, 10, "###"]
  Paragraph[12, 17] isTrailingBlankLine
    Text[12, 16] chars:[12, 16, "####"]
  Paragraph[18, 24] isTrailingBlankLine
    Text[18, 23] chars:[18, 23, "#####"]
  Paragraph[25, 32] isTrailingBlankLine
    Text[25, 31] chars:[25, 31, "######"]
````````````````````````````````


If allow atx headers without a space between # and the title is false, treat bare `#`'s with a
trailing space as empty headings

```````````````````````````````` example(Heading options: 2) options(no-empty-heading-without-space)
# 

## 

### 

#### 

##### 

###### 

.
<h1></h1>
<h2></h2>
<h3></h3>
<h4></h4>
<h5></h5>
<h6></h6>
.
Document[0, 39]
  Heading[0, 2] textOpen:[0, 1, "#"] text:[2, 2]
  Heading[4, 7] textOpen:[4, 6, "##"] text:[7, 7]
  Heading[9, 13] textOpen:[9, 12, "###"] text:[13, 13]
  Heading[15, 20] textOpen:[15, 19, "####"] text:[20, 20]
  Heading[22, 28] textOpen:[22, 27, "#####"] text:[28, 28]
  Heading[30, 37] textOpen:[30, 36, "######"] text:[37, 37]
````````````````````````````````


Allow atx headers without a space between # and the title

```````````````````````````````` example(Heading options: 3) options(hdr-no-atx-space)
#Heading
##Heading
###Heading
####Heading
#####Heading
######Heading

#Heading #
##Heading #
###Heading #
####Heading #
#####Heading #
######Heading #
.
<h1>Heading</h1>
<h2>Heading</h2>
<h3>Heading</h3>
<h4>Heading</h4>
<h5>Heading</h5>
<h6>Heading</h6>
<h1>Heading</h1>
<h2>Heading</h2>
<h3>Heading</h3>
<h4>Heading</h4>
<h5>Heading</h5>
<h6>Heading</h6>
.
Document[0, 150]
  Heading[0, 8] textOpen:[0, 1, "#"] text:[1, 8, "Heading"]
    Text[1, 8] chars:[1, 8, "Heading"]
  Heading[9, 18] textOpen:[9, 11, "##"] text:[11, 18, "Heading"]
    Text[11, 18] chars:[11, 18, "Heading"]
  Heading[19, 29] textOpen:[19, 22, "###"] text:[22, 29, "Heading"]
    Text[22, 29] chars:[22, 29, "Heading"]
  Heading[30, 41] textOpen:[30, 34, "####"] text:[34, 41, "Heading"]
    Text[34, 41] chars:[34, 41, "Heading"]
  Heading[42, 54] textOpen:[42, 47, "#####"] text:[47, 54, "Heading"]
    Text[47, 54] chars:[47, 54, "Heading"]
  Heading[55, 68] textOpen:[55, 61, "######"] text:[61, 68, "Heading"]
    Text[61, 68] chars:[61, 68, "Heading"]
  Heading[70, 80] textOpen:[70, 71, "#"] text:[71, 78, "Heading"] textClose:[79, 80, "#"]
    Text[71, 78] chars:[71, 78, "Heading"]
  Heading[81, 92] textOpen:[81, 83, "##"] text:[83, 90, "Heading"] textClose:[91, 92, "#"]
    Text[83, 90] chars:[83, 90, "Heading"]
  Heading[93, 105] textOpen:[93, 96, "###"] text:[96, 103, "Heading"] textClose:[104, 105, "#"]
    Text[96, 103] chars:[96, 103, "Heading"]
  Heading[106, 119] textOpen:[106, 110, "####"] text:[110, 117, "Heading"] textClose:[118, 119, "#"]
    Text[110, 117] chars:[110, 117, "Heading"]
  Heading[120, 134] textOpen:[120, 125, "#####"] text:[125, 132, "Heading"] textClose:[133, 134, "#"]
    Text[125, 132] chars:[125, 132, "Heading"]
  Heading[135, 150] textOpen:[135, 141, "######"] text:[141, 148, "Heading"] textClose:[149, 150, "#"]
    Text[141, 148] chars:[141, 148, "Heading"]
````````````````````````````````


Don't allow leading spaces

```````````````````````````````` example(Heading options: 4) options(hdr-no-lead-space)
 # Heading
 ## Heading
 ### Heading
 #### Heading
 ##### Heading
 ###### Heading
.
<p># Heading
## Heading
### Heading
#### Heading
##### Heading
###### Heading</p>
.
Document[0, 80]
  Paragraph[1, 80]
    Text[1, 10] chars:[1, 10, "# Heading"]
    SoftLineBreak[10, 11]
    Text[12, 22] chars:[12, 22, "## Heading"]
    SoftLineBreak[22, 23]
    Text[24, 35] chars:[24, 35, "### H … ading"]
    SoftLineBreak[35, 36]
    Text[37, 49] chars:[37, 49, "####  … ading"]
    SoftLineBreak[49, 50]
    Text[51, 64] chars:[51, 64, "##### … ading"]
    SoftLineBreak[64, 65]
    Text[66, 80] chars:[66, 80, "##### … ading"]
````````````````````````````````


Don't allow leading spaces, don't require atx marker space

```````````````````````````````` example(Heading options: 5) options(hdr-no-lead-space, hdr-no-atx-space)
 #Heading
 ##Heading
 ###Heading
 ####Heading
 #####Heading
 ######Heading
.
<p>#Heading
##Heading
###Heading
####Heading
#####Heading
######Heading</p>
.
Document[0, 74]
  Paragraph[1, 74]
    Text[1, 9] chars:[1, 9, "#Heading"]
    SoftLineBreak[9, 10]
    Text[11, 20] chars:[11, 20, "##Heading"]
    SoftLineBreak[20, 21]
    Text[22, 32] chars:[22, 32, "###Heading"]
    SoftLineBreak[32, 33]
    Text[34, 45] chars:[34, 45, "####H … ading"]
    SoftLineBreak[45, 46]
    Text[47, 59] chars:[47, 59, "##### … ading"]
    SoftLineBreak[59, 60]
    Text[61, 74] chars:[61, 74, "##### … ading"]
````````````````````````````````


Minimum setext marker length 3

```````````````````````````````` example(Heading options: 6) options(setext-marker-length)
Not a Heading 1
=
 
Not a Heading 1
==
 
Heading 1
===
 
 
Not a Heading 2
-
 
Not a Heading 2
--
 
Heading 2
---
 
.
<p>Not a Heading 1
=</p>
<p>Not a Heading 1
==</p>
<h1>Heading 1</h1>
<p>Not a Heading 2
-</p>
<p>Not a Heading 2
--</p>
<h2>Heading 2</h2>
.
Document[0, 116]
  Paragraph[0, 18] isTrailingBlankLine
    Text[0, 15] chars:[0, 15, "Not a … ing 1"]
    SoftLineBreak[15, 16]
    Text[16, 17] chars:[16, 17, "="]
  Paragraph[20, 39] isTrailingBlankLine
    Text[20, 35] chars:[20, 35, "Not a … ing 1"]
    SoftLineBreak[35, 36]
    Text[36, 38] chars:[36, 38, "=="]
  Heading[41, 54] text:[41, 50, "Heading 1"] textClose:[51, 54, "==="]
    Text[41, 50] chars:[41, 50, "Heading 1"]
  Paragraph[59, 77] isTrailingBlankLine
    Text[59, 74] chars:[59, 74, "Not a … ing 2"]
    SoftLineBreak[74, 75]
    Text[75, 76] chars:[75, 76, "-"]
  Paragraph[79, 98] isTrailingBlankLine
    Text[79, 94] chars:[79, 94, "Not a … ing 2"]
    SoftLineBreak[94, 95]
    Text[95, 97] chars:[95, 97, "--"]
  Heading[100, 113] text:[100, 109, "Heading 2"] textClose:[110, 113, "---"]
    Text[100, 109] chars:[100, 109, "Heading 2"]
````````````````````````````````


Minimum setext marker length 3 in lists

```````````````````````````````` example(Heading options: 7) options(setext-marker-length, empty-bullet-no-sub-item-break)
- item 
  - 
 
.
<ul>
  <li>item
  -</li>
</ul>
.
Document[0, 15]
  BulletList[0, 13] isTight
    BulletListItem[0, 13] open:[0, 1, "-"] isTight hadBlankLineAfter
      Paragraph[2, 13] isTrailingBlankLine
        Text[2, 6] chars:[2, 6, "item"]
        SoftLineBreak[7, 8]
        Text[10, 11] chars:[10, 11, "-"]
````````````````````````````````


Minimum setext marker length 3 in lists

```````````````````````````````` example(Heading options: 8) options(setext-marker-length, empty-bullet-sub-item-break)
- item 
  - 
 
.
<ul>
  <li>item
    <ul>
      <li></li>
    </ul>
  </li>
</ul>
.
Document[0, 15]
  BulletList[0, 11] isTight
    BulletListItem[0, 11] open:[0, 1, "-"] isTight
      Paragraph[2, 8]
        Text[2, 6] chars:[2, 6, "item"]
      BulletList[10, 11] isTight
        BulletListItem[10, 11] open:[10, 11, "-"] isTight hadBlankLineAfter
````````````````````````````````


## List Options

### List - No Auto Loose

With auto loose setting for list

```````````````````````````````` example List - No Auto Loose: 1
* item 1
* item 2
* item 3
    * sub item 1
    
    * sub item 2
    
    * sub item 3
* item 4
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3
    <ul>
      <li>
        <p>sub item 1</p>
      </li>
      <li>
        <p>sub item 2</p>
      </li>
      <li>
        <p>sub item 3</p>
      </li>
    </ul>
  </li>
  <li>item 4</li>
</ul>
.
Document[0, 97]
  BulletList[0, 97] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    BulletListItem[18, 88] open:[18, 19, "*"] isTight hadBlankLine
      Paragraph[20, 27]
        Text[20, 26] chars:[20, 26, "item 3"]
      BulletList[31, 88] isLoose
        BulletListItem[31, 44] open:[31, 32, "*"] isLoose hadBlankLineAfter
          Paragraph[33, 44] isTrailingBlankLine
            Text[33, 43] chars:[33, 43, "sub item 1"]
        BulletListItem[53, 66] open:[53, 54, "*"] isLoose hadBlankLineAfter
          Paragraph[55, 66] isTrailingBlankLine
            Text[55, 65] chars:[55, 65, "sub item 2"]
        BulletListItem[75, 88] open:[75, 76, "*"] isLoose
          Paragraph[77, 88]
            Text[77, 87] chars:[77, 87, "sub item 3"]
    BulletListItem[88, 97] open:[88, 89, "*"] isTight
      Paragraph[90, 97]
        Text[90, 96] chars:[90, 96, "item 4"]
````````````````````````````````


Without auto loose setting for list

```````````````````````````````` example(List - No Auto Loose: 2) options(list-no-loose)
* item 1
* item 2
* item 3
    * sub item 1
    
    * sub item 2
    
    * sub item 3
* item 4
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3
    <ul>
      <li>
        <p>sub item 1</p>
      </li>
      <li>
        <p>sub item 2</p>
      </li>
      <li>sub item 3</li>
    </ul>
  </li>
  <li>item 4</li>
</ul>
.
Document[0, 96]
  BulletList[0, 96] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    BulletListItem[18, 88] open:[18, 19, "*"] isTight hadBlankLine
      Paragraph[20, 27]
        Text[20, 26] chars:[20, 26, "item 3"]
      BulletList[31, 88] isTight
        BulletListItem[31, 44] open:[31, 32, "*"] isLoose hadBlankLineAfter
          Paragraph[33, 44] isTrailingBlankLine
            Text[33, 43] chars:[33, 43, "sub item 1"]
        BulletListItem[53, 66] open:[53, 54, "*"] isLoose hadBlankLineAfter
          Paragraph[55, 66] isTrailingBlankLine
            Text[55, 65] chars:[55, 65, "sub item 2"]
        BulletListItem[75, 88] open:[75, 76, "*"] isTight
          Paragraph[77, 88]
            Text[77, 87] chars:[77, 87, "sub item 3"]
    BulletListItem[88, 96] open:[88, 89, "*"] isTight
      Paragraph[90, 96]
        Text[90, 96] chars:[90, 96, "item 4"]
````````````````````````````````


### List - No Auto Loose, Loose Item if Previous Loose

Without auto loose setting for list with loose if previous loose item

```````````````````````````````` example(List - No Auto Loose, Loose Item if Previous Loose: 1) options(list-no-loose, list-loose-if-prev)
* item 1
* item 2
* item 3

* item 4
* item 5

* item 6

* item 7

.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>
    <p>item 3</p>
  </li>
  <li>
    <p>item 4</p>
  </li>
  <li>
    <p>item 5</p>
  </li>
  <li>
    <p>item 6</p>
  </li>
  <li>
    <p>item 7</p>
  </li>
</ul>
.
Document[0, 67]
  BulletList[0, 66] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    BulletListItem[18, 27] open:[18, 19, "*"] isLoose hadBlankLineAfter
      Paragraph[20, 27] isTrailingBlankLine
        Text[20, 26] chars:[20, 26, "item 3"]
    BulletListItem[28, 37] open:[28, 29, "*"] isLoose
      Paragraph[30, 37]
        Text[30, 36] chars:[30, 36, "item 4"]
    BulletListItem[37, 46] open:[37, 38, "*"] isLoose hadBlankLineAfter
      Paragraph[39, 46] isTrailingBlankLine
        Text[39, 45] chars:[39, 45, "item 5"]
    BulletListItem[47, 56] open:[47, 48, "*"] isLoose hadBlankLineAfter
      Paragraph[49, 56] isTrailingBlankLine
        Text[49, 55] chars:[49, 55, "item 6"]
    BulletListItem[57, 66] open:[57, 58, "*"] isLoose hadBlankLineAfter
      Paragraph[59, 66] isTrailingBlankLine
        Text[59, 65] chars:[59, 65, "item 7"]
````````````````````````````````


Without auto loose setting for list with loose if previous loose item

```````````````````````````````` example(List - No Auto Loose, Loose Item if Previous Loose: 2) options(list-no-loose, list-loose-if-prev)
* main item 
    * item 1
    * item 2
    * item 3
    
    * item 4
    * item 5
    
    * item 6
    
    * item 7

.
<ul>
  <li>main item
    <ul>
      <li>item 1</li>
      <li>item 2</li>
      <li>
        <p>item 3</p>
      </li>
      <li>
        <p>item 4</p>
      </li>
      <li>
        <p>item 5</p>
      </li>
      <li>
        <p>item 6</p>
      </li>
      <li>
        <p>item 7</p>
      </li>
    </ul>
  </li>
</ul>
.
Document[0, 120]
  BulletList[0, 119] isTight
    BulletListItem[0, 119] open:[0, 1, "*"] isTight hadBlankLine
      Paragraph[2, 13]
        Text[2, 11] chars:[2, 11, "main item"]
      BulletList[17, 119] isTight
        BulletListItem[17, 26] open:[17, 18, "*"] isTight
          Paragraph[19, 26]
            Text[19, 25] chars:[19, 25, "item 1"]
        BulletListItem[30, 39] open:[30, 31, "*"] isTight
          Paragraph[32, 39]
            Text[32, 38] chars:[32, 38, "item 2"]
        BulletListItem[43, 52] open:[43, 44, "*"] isLoose hadBlankLineAfter
          Paragraph[45, 52] isTrailingBlankLine
            Text[45, 51] chars:[45, 51, "item 3"]
        BulletListItem[61, 70] open:[61, 62, "*"] isLoose
          Paragraph[63, 70]
            Text[63, 69] chars:[63, 69, "item 4"]
        BulletListItem[74, 83] open:[74, 75, "*"] isLoose hadBlankLineAfter
          Paragraph[76, 83] isTrailingBlankLine
            Text[76, 82] chars:[76, 82, "item 5"]
        BulletListItem[92, 101] open:[92, 93, "*"] isLoose hadBlankLineAfter
          Paragraph[94, 101] isTrailingBlankLine
            Text[94, 100] chars:[94, 100, "item 6"]
        BulletListItem[110, 119] open:[110, 111, "*"] isLoose hadBlankLineAfter
          Paragraph[112, 119] isTrailingBlankLine
            Text[112, 118] chars:[112, 118, "item 7"]
````````````````````````````````


### List - No Break on Double Blank Line

With break all lists on two blank lines

```````````````````````````````` example(List - No Break on Double Blank Line: 1) options(list-break)
* item 1
* item 2
    * sub item 1
    * sub item 2
    
    
* item 4
* item 5
.
<ul>
  <li>item 1</li>
  <li>item 2
    <ul>
      <li>sub item 1</li>
      <li>sub item 2</li>
    </ul>
  </li>
</ul>
<ul>
  <li>item 4</li>
  <li>item 5</li>
</ul>
.
Document[0, 79]
  BulletList[0, 52] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 52] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
      BulletList[22, 52] isTight
        BulletListItem[22, 35] open:[22, 23, "*"] isTight
          Paragraph[24, 35]
            Text[24, 34] chars:[24, 34, "sub item 1"]
        BulletListItem[39, 52] open:[39, 40, "*"] isTight hadBlankLineAfter
          Paragraph[41, 52] isTrailingBlankLine
            Text[41, 51] chars:[41, 51, "sub item 2"]
  BulletList[62, 79] isTight
    BulletListItem[62, 71] open:[62, 63, "*"] isTight
      Paragraph[64, 71]
        Text[64, 70] chars:[64, 70, "item 4"]
    BulletListItem[71, 79] open:[71, 72, "*"] isTight
      Paragraph[73, 79]
        Text[73, 79] chars:[73, 79, "item 5"]
````````````````````````````````


Without break all lists on two blank lines

```````````````````````````````` example List - No Break on Double Blank Line: 2
* item 1
* item 2
    * sub item 1
    * sub item 2
    
    
* item 4
* item 5
.
<ul>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
    <ul>
      <li>sub item 1</li>
      <li>sub item 2</li>
    </ul>
  </li>
  <li>
    <p>item 4</p>
  </li>
  <li>
    <p>item 5</p>
  </li>
</ul>
.
Document[0, 80]
  BulletList[0, 80] isLoose
    BulletListItem[0, 9] open:[0, 1, "*"] isLoose
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 52] open:[9, 10, "*"] isLoose
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
      BulletList[22, 52] isTight
        BulletListItem[22, 35] open:[22, 23, "*"] isTight
          Paragraph[24, 35]
            Text[24, 34] chars:[24, 34, "sub item 1"]
        BulletListItem[39, 52] open:[39, 40, "*"] isTight hadBlankLineAfter
          Paragraph[41, 52] isTrailingBlankLine
            Text[41, 51] chars:[41, 51, "sub item 2"]
    BulletListItem[62, 71] open:[62, 63, "*"] isLoose
      Paragraph[64, 71]
        Text[64, 70] chars:[64, 70, "item 4"]
    BulletListItem[71, 80] open:[71, 72, "*"] isLoose
      Paragraph[73, 80]
        Text[73, 79] chars:[73, 79, "item 5"]
````````````````````````````````


Without break all lists on two blank lines no auto loose

```````````````````````````````` example(List - No Break on Double Blank Line: 3) options(list-no-loose, list-no-break)
* item 1
* item 2
    * sub item 1
    * sub item 2
    
    
* item 4
* item 5
.
<ul>
  <li>item 1</li>
  <li>
    <p>item 2</p>
    <ul>
      <li>sub item 1</li>
      <li>sub item 2</li>
    </ul>
  </li>
  <li>item 4</li>
  <li>item 5</li>
</ul>
.
Document[0, 79]
  BulletList[0, 79] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 52] open:[9, 10, "*"] isLoose
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
      BulletList[22, 52] isTight
        BulletListItem[22, 35] open:[22, 23, "*"] isTight
          Paragraph[24, 35]
            Text[24, 34] chars:[24, 34, "sub item 1"]
        BulletListItem[39, 52] open:[39, 40, "*"] isTight hadBlankLineAfter
          Paragraph[41, 52] isTrailingBlankLine
            Text[41, 51] chars:[41, 51, "sub item 2"]
    BulletListItem[62, 71] open:[62, 63, "*"] isTight
      Paragraph[64, 71]
        Text[64, 70] chars:[64, 70, "item 4"]
    BulletListItem[71, 79] open:[71, 72, "*"] isTight
      Paragraph[73, 79]
        Text[73, 79] chars:[73, 79, "item 5"]
````````````````````````````````


With break all lists on two blank lines

```````````````````````````````` example(List - No Break on Double Blank Line: 4) options(list-break)
* item 1
* item 2
    * sub item 1
    * sub item 2
    
    
    * sub item 3
    * sub item 4
* item 4
* item 5
.
<ul>
  <li>item 1</li>
  <li>item 2
    <ul>
      <li>sub item 1</li>
      <li>sub item 2</li>
    </ul>
  </li>
</ul>
<pre><code>* sub item 3
* sub item 4
</code></pre>
<ul>
  <li>item 4</li>
  <li>item 5</li>
</ul>
.
Document[0, 113]
  BulletList[0, 52] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 52] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
      BulletList[22, 52] isTight
        BulletListItem[22, 35] open:[22, 23, "*"] isTight
          Paragraph[24, 35]
            Text[24, 34] chars:[24, 34, "sub item 1"]
        BulletListItem[39, 52] open:[39, 40, "*"] isTight hadBlankLineAfter
          Paragraph[41, 52] isTrailingBlankLine
            Text[41, 51] chars:[41, 51, "sub item 2"]
  IndentedCodeBlock[66, 96]
  BulletList[96, 113] isTight
    BulletListItem[96, 105] open:[96, 97, "*"] isTight
      Paragraph[98, 105]
        Text[98, 104] chars:[98, 104, "item 4"]
    BulletListItem[105, 113] open:[105, 106, "*"] isTight
      Paragraph[107, 113]
        Text[107, 113] chars:[107, 113, "item 5"]
````````````````````````````````


Without break on two blank lines

```````````````````````````````` example List - No Break on Double Blank Line: 5
* item 1
* item 2
    * sub item 1
    * sub item 2
    
    
    * sub item 3
    * sub item 4
* item 4
* item 5
.
<ul>
  <li>item 1</li>
  <li>item 2
    <ul>
      <li>
        <p>sub item 1</p>
      </li>
      <li>
        <p>sub item 2</p>
      </li>
      <li>
        <p>sub item 3</p>
      </li>
      <li>
        <p>sub item 4</p>
      </li>
    </ul>
  </li>
  <li>item 4</li>
  <li>item 5</li>
</ul>
.
Document[0, 114]
  BulletList[0, 114] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 96] open:[9, 10, "*"] isTight hadBlankLine
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
      BulletList[22, 96] isLoose
        BulletListItem[22, 35] open:[22, 23, "*"] isLoose
          Paragraph[24, 35]
            Text[24, 34] chars:[24, 34, "sub item 1"]
        BulletListItem[39, 52] open:[39, 40, "*"] isLoose hadBlankLineAfter
          Paragraph[41, 52] isTrailingBlankLine
            Text[41, 51] chars:[41, 51, "sub item 2"]
        BulletListItem[66, 79] open:[66, 67, "*"] isLoose
          Paragraph[68, 79]
            Text[68, 78] chars:[68, 78, "sub item 3"]
        BulletListItem[83, 96] open:[83, 84, "*"] isLoose
          Paragraph[85, 96]
            Text[85, 95] chars:[85, 95, "sub item 4"]
    BulletListItem[96, 105] open:[96, 97, "*"] isTight
      Paragraph[98, 105]
        Text[98, 104] chars:[98, 104, "item 4"]
    BulletListItem[105, 114] open:[105, 106, "*"] isTight
      Paragraph[107, 114]
        Text[107, 113] chars:[107, 113, "item 5"]
````````````````````````````````


### List - No Bullet Match

With bullet matching for items within a list

```````````````````````````````` example List - No Bullet Match: 1
- item 1
* item 1
+ item 1
.
<ul>
  <li>item 1</li>
</ul>
<ul>
  <li>item 1</li>
</ul>
<ul>
  <li>item 1</li>
</ul>
.
Document[0, 27]
  BulletList[0, 9] isTight
    BulletListItem[0, 9] open:[0, 1, "-"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
  BulletList[9, 18] isTight
    BulletListItem[9, 18] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 1"]
  BulletList[18, 27] isTight
    BulletListItem[18, 27] open:[18, 19, "+"] isTight
      Paragraph[20, 27]
        Text[20, 26] chars:[20, 26, "item 1"]
````````````````````````````````


Without bullet matching for items within a list

```````````````````````````````` example(List - No Bullet Match: 2) options(list-no-bullet-match)
- item 1
* item 2
+ item 3
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
</ul>
.
Document[0, 26]
  BulletList[0, 26] isTight
    BulletListItem[0, 9] open:[0, 1, "-"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    BulletListItem[18, 26] open:[18, 19, "+"] isTight
      Paragraph[20, 26]
        Text[20, 26] chars:[20, 26, "item 3"]
````````````````````````````````


### List - No Manual Start

With start

```````````````````````````````` example List - No Manual Start: 1
2. item 1
1. item 2
3. item 3
.
<ol start="2">
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
</ol>
.
Document[0, 30]
  OrderedList[0, 30] isTight start:2 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "2."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "1."] isTight
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 2"]
    OrderedListItem[20, 30] open:[20, 22, "3."] isTight
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 3"]
````````````````````````````````


Without start

```````````````````````````````` example(List - No Manual Start: 2) options(list-no-start)
2. item 1
1. item 1
1. item 1
.
<ol>
  <li>item 1</li>
  <li>item 1</li>
  <li>item 1</li>
</ol>
.
Document[0, 29]
  OrderedList[0, 29] isTight start:2 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "2."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "1."] isTight
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 1"]
    OrderedListItem[20, 29] open:[20, 22, "1."] isTight
      Paragraph[23, 29]
        Text[23, 29] chars:[23, 29, "item 1"]
````````````````````````````````


### List - Paragraph Break Options

Without relaxed start. Lists start only if preceded by a blank line and sub-lists only when
starting with 1.

```````````````````````````````` example(List - Paragraph Break Options: 1) options(bullet-no-para-break, ordered-no-para-break, ordered-no-non-1-item-break)
1. this is a list
1. item 1
   1. item 2
.
<ol>
  <li>this is a list</li>
  <li>item 1
    <ol>
      <li>item 2</li>
    </ol>
  </li>
</ol>
.
Document[0, 40]
  OrderedList[0, 40] isTight delimiter:'.'
    OrderedListItem[0, 18] open:[0, 2, "1."] isTight
      Paragraph[3, 18]
        Text[3, 17] chars:[3, 17, "this  …  list"]
    OrderedListItem[18, 40] open:[18, 20, "1."] isTight
      Paragraph[21, 28]
        Text[21, 27] chars:[21, 27, "item 1"]
      OrderedList[31, 40] isTight delimiter:'.'
        OrderedListItem[31, 40] open:[31, 33, "1."] isTight
          Paragraph[34, 40]
            Text[34, 40] chars:[34, 40, "item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line and sub-lists only when
starting with 1.

```````````````````````````````` example(List - Paragraph Break Options: 2) options(bullet-no-para-break, ordered-no-non-1-item-break)
1. this is a list
1. item 1
   2. item 2
.
<ol>
  <li>this is a list</li>
  <li>item 1
  2. item 2</li>
</ol>
.
Document[0, 40]
  OrderedList[0, 40] isTight delimiter:'.'
    OrderedListItem[0, 18] open:[0, 2, "1."] isTight
      Paragraph[3, 18]
        Text[3, 17] chars:[3, 17, "this  …  list"]
    OrderedListItem[18, 40] open:[18, 20, "1."] isTight
      Paragraph[21, 40]
        Text[21, 27] chars:[21, 27, "item 1"]
        SoftLineBreak[27, 28]
        Text[31, 40] chars:[31, 40, "2. item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line and sub-lists only when
starting with 1 or no ordered start restriction

```````````````````````````````` example(List - Paragraph Break Options: 3) options(ordered-non-1-para-break, ordered-no-non-1-item-break)
This is a paragraph
2. this is a list
1. item 1
   2. item 2
.
<p>This is a paragraph</p>
<ol start="2">
  <li>this is a list</li>
  <li>item 1
  2. item 2</li>
</ol>
.
Document[0, 60]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  OrderedList[20, 60] isTight start:2 delimiter:'.'
    OrderedListItem[20, 38] open:[20, 22, "2."] isTight
      Paragraph[23, 38]
        Text[23, 37] chars:[23, 37, "this  …  list"]
    OrderedListItem[38, 60] open:[38, 40, "1."] isTight
      Paragraph[41, 60]
        Text[41, 47] chars:[41, 47, "item 1"]
        SoftLineBreak[47, 48]
        Text[51, 60] chars:[51, 60, "2. item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line and sub-lists only when
starting with 1 or no ordered start restriction

```````````````````````````````` example(List - Paragraph Break Options: 4) options(ordered-non-1-para-break, ordered-non-1-item-break)
This is a paragraph
2. this is a list
1. item 1
   2. item 2
.
<p>This is a paragraph</p>
<ol start="2">
  <li>this is a list</li>
  <li>item 1
    <ol start="2">
      <li>item 2</li>
    </ol>
  </li>
</ol>
.
Document[0, 60]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  OrderedList[20, 60] isTight start:2 delimiter:'.'
    OrderedListItem[20, 38] open:[20, 22, "2."] isTight
      Paragraph[23, 38]
        Text[23, 37] chars:[23, 37, "this  …  list"]
    OrderedListItem[38, 60] open:[38, 40, "1."] isTight
      Paragraph[41, 48]
        Text[41, 47] chars:[41, 47, "item 1"]
      OrderedList[51, 60] isTight start:2 delimiter:'.'
        OrderedListItem[51, 60] open:[51, 53, "2."] isTight
          Paragraph[54, 60]
            Text[54, 60] chars:[54, 60, "item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line and sub-lists only when
starting with 1 or no ordered start restriction

```````````````````````````````` example(List - Paragraph Break Options: 5) options(ordered-non-1-item-break)
1. this is a list
1. item 1
   2. item 2
.
<ol>
  <li>this is a list</li>
  <li>item 1
    <ol start="2">
      <li>item 2</li>
    </ol>
  </li>
</ol>
.
Document[0, 40]
  OrderedList[0, 40] isTight delimiter:'.'
    OrderedListItem[0, 18] open:[0, 2, "1."] isTight
      Paragraph[3, 18]
        Text[3, 17] chars:[3, 17, "this  …  list"]
    OrderedListItem[18, 40] open:[18, 20, "1."] isTight
      Paragraph[21, 28]
        Text[21, 27] chars:[21, 27, "item 1"]
      OrderedList[31, 40] isTight start:2 delimiter:'.'
        OrderedListItem[31, 40] open:[31, 33, "2."] isTight
          Paragraph[34, 40]
            Text[34, 40] chars:[34, 40, "item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line.

```````````````````````````````` example(List - Paragraph Break Options: 6) options(bullet-no-para-break, ordered-no-para-break)
- this is a list
- item 1
  - item 2
.
<ul>
  <li>this is a list</li>
  <li>item 1
    <ul>
      <li>item 2</li>
    </ul>
  </li>
</ul>
.
Document[0, 36]
  BulletList[0, 36] isTight
    BulletListItem[0, 17] open:[0, 1, "-"] isTight
      Paragraph[2, 17]
        Text[2, 16] chars:[2, 16, "this  …  list"]
    BulletListItem[17, 36] open:[17, 18, "-"] isTight
      Paragraph[19, 26]
        Text[19, 25] chars:[19, 25, "item 1"]
      BulletList[28, 36] isTight
        BulletListItem[28, 36] open:[28, 29, "-"] isTight
          Paragraph[30, 36]
            Text[30, 36] chars:[30, 36, "item 2"]
````````````````````````````````


With relaxed start. Lists can start without preceding blank lines.

```````````````````````````````` example List - Paragraph Break Options: 7
This is a paragraph
2. item 1
1. item 2

2. this is a list
1. item 1
    1. item 2

This is a paragraph 
- item 1
- item 2

- this is a list
- item 1
    - item 2
.
<p>This is a paragraph
2. item 1</p>
<ol>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>this is a list</p>
  </li>
  <li>
    <p>item 1</p>
    <ol>
      <li>item 2</li>
    </ol>
  </li>
</ol>
<p>This is a paragraph</p>
<ul>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>this is a list</p>
  </li>
  <li>
    <p>item 1</p>
    <ul>
      <li>item 2</li>
    </ul>
  </li>
</ul>
.
Document[0, 163]
  Paragraph[0, 30]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 29] chars:[20, 29, "2. item 1"]
  OrderedList[30, 83] isLoose delimiter:'.'
    OrderedListItem[30, 40] open:[30, 32, "1."] isLoose hadBlankLineAfter
      Paragraph[33, 40] isTrailingBlankLine
        Text[33, 39] chars:[33, 39, "item 2"]
    OrderedListItem[41, 59] open:[41, 43, "2."] isLoose
      Paragraph[44, 59]
        Text[44, 58] chars:[44, 58, "this  …  list"]
    OrderedListItem[59, 83] open:[59, 61, "1."] isLoose
      Paragraph[62, 69]
        Text[62, 68] chars:[62, 68, "item 1"]
      OrderedList[73, 83] isTight delimiter:'.'
        OrderedListItem[73, 83] open:[73, 75, "1."] isTight hadBlankLineAfter
          Paragraph[76, 83] isTrailingBlankLine
            Text[76, 82] chars:[76, 82, "item 2"]
  Paragraph[84, 105]
    Text[84, 103] chars:[84, 103, "This  … graph"]
  BulletList[105, 163] isLoose
    BulletListItem[105, 114] open:[105, 106, "-"] isLoose
      Paragraph[107, 114]
        Text[107, 113] chars:[107, 113, "item 1"]
    BulletListItem[114, 123] open:[114, 115, "-"] isLoose hadBlankLineAfter
      Paragraph[116, 123] isTrailingBlankLine
        Text[116, 122] chars:[116, 122, "item 2"]
    BulletListItem[124, 141] open:[124, 125, "-"] isLoose
      Paragraph[126, 141]
        Text[126, 140] chars:[126, 140, "this  …  list"]
    BulletListItem[141, 163] open:[141, 142, "-"] isLoose
      Paragraph[143, 150]
        Text[143, 149] chars:[143, 149, "item 1"]
      BulletList[154, 163] isTight
        BulletListItem[154, 163] open:[154, 155, "-"] isTight
          Paragraph[156, 163]
            Text[156, 162] chars:[156, 162, "item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line. Items and sub-items can
start without a blank line.

```````````````````````````````` example(List - Paragraph Break Options: 8) options(bullet-no-para-break, ordered-no-para-break)
This is a paragraph
2. item 1
1. item 2

2. this is a list
1. item 1
   1. item 2

This is a paragraph 
- item 1
- item 2

- this is a list
- item 1
  - item 2
.
<p>This is a paragraph
2. item 1
1. item 2</p>
<ol start="2">
  <li>this is a list</li>
  <li>item 1
    <ol>
      <li>item 2</li>
    </ol>
  </li>
</ol>
<p>This is a paragraph
- item 1
- item 2</p>
<ul>
  <li>this is a list</li>
  <li>item 1
    <ul>
      <li>item 2</li>
    </ul>
  </li>
</ul>
.
Document[0, 159]
  Paragraph[0, 40] isTrailingBlankLine
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 29] chars:[20, 29, "2. item 1"]
    SoftLineBreak[29, 30]
    Text[30, 39] chars:[30, 39, "1. item 2"]
  OrderedList[41, 82] isTight start:2 delimiter:'.'
    OrderedListItem[41, 59] open:[41, 43, "2."] isTight
      Paragraph[44, 59]
        Text[44, 58] chars:[44, 58, "this  …  list"]
    OrderedListItem[59, 82] open:[59, 61, "1."] isTight
      Paragraph[62, 69]
        Text[62, 68] chars:[62, 68, "item 1"]
      OrderedList[72, 82] isTight delimiter:'.'
        OrderedListItem[72, 82] open:[72, 74, "1."] isTight hadBlankLineAfter
          Paragraph[75, 82] isTrailingBlankLine
            Text[75, 81] chars:[75, 81, "item 2"]
  Paragraph[83, 122] isTrailingBlankLine
    Text[83, 102] chars:[83, 102, "This  … graph"]
    SoftLineBreak[103, 104]
    Text[104, 112] chars:[104, 112, "- item 1"]
    SoftLineBreak[112, 113]
    Text[113, 121] chars:[113, 121, "- item 2"]
  BulletList[123, 159] isTight
    BulletListItem[123, 140] open:[123, 124, "-"] isTight
      Paragraph[125, 140]
        Text[125, 139] chars:[125, 139, "this  …  list"]
    BulletListItem[140, 159] open:[140, 141, "-"] isTight
      Paragraph[142, 149]
        Text[142, 148] chars:[142, 148, "item 1"]
      BulletList[151, 159] isTight
        BulletListItem[151, 159] open:[151, 152, "-"] isTight
          Paragraph[153, 159]
            Text[153, 159] chars:[153, 159, "item 2"]
````````````````````````````````


Without relaxed start for bullet Lists start only if preceded by a blank line. Items and
sub-items can start without a blank line.

```````````````````````````````` example(List - Paragraph Break Options: 9) options(bullet-no-para-break)
This is a paragraph
1. item 1
1. item 2

2. this is a list
1. item 1
   1. item 2

This is a paragraph 
- item 1
- item 2

- this is a list
- item 1
  - item 2
.
<p>This is a paragraph</p>
<ol>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>this is a list</p>
  </li>
  <li>
    <p>item 1</p>
    <ol>
      <li>item 2</li>
    </ol>
  </li>
</ol>
<p>This is a paragraph
- item 1
- item 2</p>
<ul>
  <li>this is a list</li>
  <li>item 1
    <ul>
      <li>item 2</li>
    </ul>
  </li>
</ul>
.
Document[0, 159]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  OrderedList[20, 82] isLoose delimiter:'.'
    OrderedListItem[20, 30] open:[20, 22, "1."] isLoose
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 1"]
    OrderedListItem[30, 40] open:[30, 32, "1."] isLoose hadBlankLineAfter
      Paragraph[33, 40] isTrailingBlankLine
        Text[33, 39] chars:[33, 39, "item 2"]
    OrderedListItem[41, 59] open:[41, 43, "2."] isLoose
      Paragraph[44, 59]
        Text[44, 58] chars:[44, 58, "this  …  list"]
    OrderedListItem[59, 82] open:[59, 61, "1."] isLoose
      Paragraph[62, 69]
        Text[62, 68] chars:[62, 68, "item 1"]
      OrderedList[72, 82] isTight delimiter:'.'
        OrderedListItem[72, 82] open:[72, 74, "1."] isTight hadBlankLineAfter
          Paragraph[75, 82] isTrailingBlankLine
            Text[75, 81] chars:[75, 81, "item 2"]
  Paragraph[83, 122] isTrailingBlankLine
    Text[83, 102] chars:[83, 102, "This  … graph"]
    SoftLineBreak[103, 104]
    Text[104, 112] chars:[104, 112, "- item 1"]
    SoftLineBreak[112, 113]
    Text[113, 121] chars:[113, 121, "- item 2"]
  BulletList[123, 159] isTight
    BulletListItem[123, 140] open:[123, 124, "-"] isTight
      Paragraph[125, 140]
        Text[125, 139] chars:[125, 139, "this  …  list"]
    BulletListItem[140, 159] open:[140, 141, "-"] isTight
      Paragraph[142, 149]
        Text[142, 148] chars:[142, 148, "item 1"]
      BulletList[151, 159] isTight
        BulletListItem[151, 159] open:[151, 152, "-"] isTight
          Paragraph[153, 159]
            Text[153, 159] chars:[153, 159, "item 2"]
````````````````````````````````


With relaxed start but not for ordered lists. Ordered lists start only if preceded by a blank
line.

```````````````````````````````` example(List - Paragraph Break Options: 10) options(ordered-no-para-break)
This is a paragraph
2. item 1
1. item 2

2. this is a list
1. item 1
   1. item 2

This is a paragraph 
- item 1
- item 2

- this is a list
- item 1
  - item 2
.
<p>This is a paragraph
2. item 1
1. item 2</p>
<ol start="2">
  <li>this is a list</li>
  <li>item 1
    <ol>
      <li>item 2</li>
    </ol>
  </li>
</ol>
<p>This is a paragraph</p>
<ul>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>this is a list</p>
  </li>
  <li>
    <p>item 1</p>
    <ul>
      <li>item 2</li>
    </ul>
  </li>
</ul>
.
Document[0, 159]
  Paragraph[0, 40] isTrailingBlankLine
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 29] chars:[20, 29, "2. item 1"]
    SoftLineBreak[29, 30]
    Text[30, 39] chars:[30, 39, "1. item 2"]
  OrderedList[41, 82] isTight start:2 delimiter:'.'
    OrderedListItem[41, 59] open:[41, 43, "2."] isTight
      Paragraph[44, 59]
        Text[44, 58] chars:[44, 58, "this  …  list"]
    OrderedListItem[59, 82] open:[59, 61, "1."] isTight
      Paragraph[62, 69]
        Text[62, 68] chars:[62, 68, "item 1"]
      OrderedList[72, 82] isTight delimiter:'.'
        OrderedListItem[72, 82] open:[72, 74, "1."] isTight hadBlankLineAfter
          Paragraph[75, 82] isTrailingBlankLine
            Text[75, 81] chars:[75, 81, "item 2"]
  Paragraph[83, 104]
    Text[83, 102] chars:[83, 102, "This  … graph"]
  BulletList[104, 159] isLoose
    BulletListItem[104, 113] open:[104, 105, "-"] isLoose
      Paragraph[106, 113]
        Text[106, 112] chars:[106, 112, "item 1"]
    BulletListItem[113, 122] open:[113, 114, "-"] isLoose hadBlankLineAfter
      Paragraph[115, 122] isTrailingBlankLine
        Text[115, 121] chars:[115, 121, "item 2"]
    BulletListItem[123, 140] open:[123, 124, "-"] isLoose
      Paragraph[125, 140]
        Text[125, 139] chars:[125, 139, "this  …  list"]
    BulletListItem[140, 159] open:[140, 141, "-"] isLoose
      Paragraph[142, 149]
        Text[142, 148] chars:[142, 148, "item 1"]
      BulletList[151, 159] isTight
        BulletListItem[151, 159] open:[151, 152, "-"] isTight
          Paragraph[153, 159]
            Text[153, 159] chars:[153, 159, "item 2"]
````````````````````````````````


Ordered items must have a blank line before them

```````````````````````````````` example(List - Paragraph Break Options: 11) options(ordered-no-para-break, ordered-no-item-break)
This is a paragraph
2. not item 1
1. not item 2
.
<p>This is a paragraph
2. not item 1
1. not item 2</p>
.
Document[0, 47]
  Paragraph[0, 47]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 33] chars:[20, 33, "2. no … tem 1"]
    SoftLineBreak[33, 34]
    Text[34, 47] chars:[34, 47, "1. no … tem 2"]
````````````````````````````````


Ordered items must have a blank line before them

```````````````````````````````` example(List - Paragraph Break Options: 12) options(ordered-no-para-break, ordered-no-item-break)
1. item 1.0

2. item 2.0
1. not item 2

1. item 3.0
.
<ol>
  <li>
    <p>item 1.0</p>
  </li>
  <li>
    <p>item 2.0
    1. not item 2</p>
  </li>
  <li>
    <p>item 3.0</p>
  </li>
</ol>
.
Document[0, 51]
  OrderedList[0, 51] isLoose delimiter:'.'
    OrderedListItem[0, 12] open:[0, 2, "1."] isLoose hadBlankLineAfter
      Paragraph[3, 12] isTrailingBlankLine
        Text[3, 11] chars:[3, 11, "item 1.0"]
    OrderedListItem[13, 39] open:[13, 15, "2."] isLoose hadBlankLineAfter
      Paragraph[16, 39] isTrailingBlankLine
        Text[16, 24] chars:[16, 24, "item 2.0"]
        SoftLineBreak[24, 25]
        Text[25, 38] chars:[25, 38, "1. no … tem 2"]
    OrderedListItem[40, 51] open:[40, 42, "1."] isLoose
      Paragraph[43, 51]
        Text[43, 51] chars:[43, 51, "item 3.0"]
````````````````````````````````


Ordered items must have a blank line before them, but not bullet items

```````````````````````````````` example(List - Paragraph Break Options: 13) options(ordered-no-para-break, ordered-no-item-break)
This is a paragraph
- item 1
- item 2
.
<p>This is a paragraph</p>
<ul>
  <li>item 1</li>
  <li>item 2</li>
</ul>
.
Document[0, 37]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  BulletList[20, 37] isTight
    BulletListItem[20, 29] open:[20, 21, "-"] isTight
      Paragraph[22, 29]
        Text[22, 28] chars:[22, 28, "item 1"]
    BulletListItem[29, 37] open:[29, 30, "-"] isTight
      Paragraph[31, 37]
        Text[31, 37] chars:[31, 37, "item 2"]
````````````````````````````````


Bullet items must have a blank line before them when preceded by paragraph but should not append
following child paragraph

```````````````````````````````` example(List - Paragraph Break Options: 14) options(bullet-no-para-break, list-no-type-match, list-no-loose)
- item 1 paragraph
  * sublist
- item 2 paragraph

    paragraph
.
<ul>
  <li>item 1 paragraph
    <ul>
      <li>sublist</li>
    </ul>
  </li>
  <li>item 2 paragraph
  <p>paragraph</p>
  </li>
</ul>
.
Document[0, 64]
  BulletList[0, 64] isTight
    BulletListItem[0, 31] open:[0, 1, "-"] isTight
      Paragraph[2, 19]
        Text[2, 18] chars:[2, 18, "item  … graph"]
      BulletList[21, 31] isTight
        BulletListItem[21, 31] open:[21, 22, "*"] isTight
          Paragraph[23, 31]
            Text[23, 30] chars:[23, 30, "sublist"]
    BulletListItem[31, 64] open:[31, 32, "-"] isTight hadBlankLineAfter
      Paragraph[33, 50] isTrailingBlankLine
        Text[33, 49] chars:[33, 49, "item  … graph"]
      Paragraph[55, 64]
        Text[55, 64] chars:[55, 64, "paragraph"]
````````````````````````````````


Bullet items must have a blank line before them

```````````````````````````````` example(List - Paragraph Break Options: 15) options(bullet-no-para-break, bullet-no-item-break)
This is a paragraph
- not item 1
- not item 2
.
<p>This is a paragraph
- not item 1
- not item 2</p>
.
Document[0, 45]
  Paragraph[0, 45]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 32] chars:[20, 32, "- not … tem 1"]
    SoftLineBreak[32, 33]
    Text[33, 45] chars:[33, 45, "- not … tem 2"]
````````````````````````````````


Bullet items must have a blank line before them

```````````````````````````````` example(List - Paragraph Break Options: 16) options(bullet-no-para-break, bullet-no-item-break)
- item 1.0

- item 2.0
- not item 2

- item 3.0
.
<ul>
  <li>
    <p>item 1.0</p>
  </li>
  <li>
    <p>item 2.0
    - not item 2</p>
  </li>
  <li>
    <p>item 3.0</p>
  </li>
</ul>
.
Document[0, 47]
  BulletList[0, 47] isLoose
    BulletListItem[0, 11] open:[0, 1, "-"] isLoose hadBlankLineAfter
      Paragraph[2, 11] isTrailingBlankLine
        Text[2, 10] chars:[2, 10, "item 1.0"]
    BulletListItem[12, 36] open:[12, 13, "-"] isLoose hadBlankLineAfter
      Paragraph[14, 36] isTrailingBlankLine
        Text[14, 22] chars:[14, 22, "item 2.0"]
        SoftLineBreak[22, 23]
        Text[23, 35] chars:[23, 35, "- not … tem 2"]
    BulletListItem[37, 47] open:[37, 38, "-"] isLoose
      Paragraph[39, 47]
        Text[39, 47] chars:[39, 47, "item 3.0"]
````````````````````````````````


Bullet items must have a blank line before them, but not ordered items

```````````````````````````````` example(List - Paragraph Break Options: 17) options(bullet-no-para-break, bullet-no-item-break)
This is a paragraph
1. item 1
2. item 2
.
<p>This is a paragraph</p>
<ol>
  <li>item 1</li>
  <li>item 2</li>
</ol>
.
Document[0, 39]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  OrderedList[20, 39] isTight delimiter:'.'
    OrderedListItem[20, 30] open:[20, 22, "1."] isTight
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 1"]
    OrderedListItem[30, 39] open:[30, 32, "2."] isTight
      Paragraph[33, 39]
        Text[33, 39] chars:[33, 39, "item 2"]
````````````````````````````````


All items must have a blank line before them

```````````````````````````````` example(List - Paragraph Break Options: 18) options(bullet-no-para-break, bullet-no-item-break, ordered-no-para-break, ordered-no-item-break)
This is a paragraph
2. not item 1
1. not item 2

1. item 1.0

2. item 2.0
1. not item 2

1. item 2

This is a paragraph 
- not item 1
- not item 2

- item 1.0

- item 2.0
- not item 1
- not item 2

- item 3.0
.
<p>This is a paragraph
2. not item 1
1. not item 2</p>
<ol>
  <li>
    <p>item 1.0</p>
  </li>
  <li>
    <p>item 2.0
    1. not item 2</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
</ol>
<p>This is a paragraph
- not item 1
- not item 2</p>
<ul>
  <li>
    <p>item 1.0</p>
  </li>
  <li>
    <p>item 2.0
    - not item 1
    - not item 2</p>
  </li>
  <li>
    <p>item 3.0</p>
  </li>
</ul>
.
Document[0, 208]
  Paragraph[0, 48] isTrailingBlankLine
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 33] chars:[20, 33, "2. no … tem 1"]
    SoftLineBreak[33, 34]
    Text[34, 47] chars:[34, 47, "1. no … tem 2"]
  OrderedList[49, 99] isLoose delimiter:'.'
    OrderedListItem[49, 61] open:[49, 51, "1."] isLoose hadBlankLineAfter
      Paragraph[52, 61] isTrailingBlankLine
        Text[52, 60] chars:[52, 60, "item 1.0"]
    OrderedListItem[62, 88] open:[62, 64, "2."] isLoose hadBlankLineAfter
      Paragraph[65, 88] isTrailingBlankLine
        Text[65, 73] chars:[65, 73, "item 2.0"]
        SoftLineBreak[73, 74]
        Text[74, 87] chars:[74, 87, "1. no … tem 2"]
    OrderedListItem[89, 99] open:[89, 91, "1."] isLoose hadBlankLineAfter
      Paragraph[92, 99] isTrailingBlankLine
        Text[92, 98] chars:[92, 98, "item 2"]
  Paragraph[100, 147] isTrailingBlankLine
    Text[100, 119] chars:[100, 119, "This  … graph"]
    SoftLineBreak[120, 121]
    Text[121, 133] chars:[121, 133, "- not … tem 1"]
    SoftLineBreak[133, 134]
    Text[134, 146] chars:[134, 146, "- not … tem 2"]
  BulletList[148, 208] isLoose
    BulletListItem[148, 159] open:[148, 149, "-"] isLoose hadBlankLineAfter
      Paragraph[150, 159] isTrailingBlankLine
        Text[150, 158] chars:[150, 158, "item 1.0"]
    BulletListItem[160, 197] open:[160, 161, "-"] isLoose hadBlankLineAfter
      Paragraph[162, 197] isTrailingBlankLine
        Text[162, 170] chars:[162, 170, "item 2.0"]
        SoftLineBreak[170, 171]
        Text[171, 183] chars:[171, 183, "- not … tem 1"]
        SoftLineBreak[183, 184]
        Text[184, 196] chars:[184, 196, "- not … tem 2"]
    BulletListItem[198, 208] open:[198, 199, "-"] isLoose
      Paragraph[200, 208]
        Text[200, 208] chars:[200, 208, "item 3.0"]
````````````````````````````````


### List - Marker Options

Without ordered items dot only

```````````````````````````````` example List - Marker Options: 1
1. item 1
2. item 2

1) item b

2) item c

.
<ol>
  <li>item 1</li>
  <li>item 2</li>
</ol>
<ol>
  <li>
    <p>item b</p>
  </li>
  <li>
    <p>item c</p>
  </li>
</ol>
.
Document[0, 43]
  OrderedList[0, 20] isTight delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "1."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "2."] isTight hadBlankLineAfter
      Paragraph[13, 20] isTrailingBlankLine
        Text[13, 19] chars:[13, 19, "item 2"]
  OrderedList[21, 42] isLoose delimiter:')'
    OrderedListItem[21, 31] open:[21, 23, "1)"] isLoose hadBlankLineAfter
      Paragraph[24, 31] isTrailingBlankLine
        Text[24, 30] chars:[24, 30, "item b"]
    OrderedListItem[32, 42] open:[32, 34, "2)"] isLoose hadBlankLineAfter
      Paragraph[35, 42] isTrailingBlankLine
        Text[35, 41] chars:[35, 41, "item c"]
````````````````````````````````


With ordered items dot only

```````````````````````````````` example(List - Marker Options: 2) options(ordered-dot-only)
1. item 1
2. item 2

1) item b

2) item c

.
<ol>
  <li>item 1</li>
  <li>item 2</li>
</ol>
<p>1) item b</p>
<p>2) item c</p>
.
Document[0, 43]
  OrderedList[0, 20] isTight delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "1."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "2."] isTight hadBlankLineAfter
      Paragraph[13, 20] isTrailingBlankLine
        Text[13, 19] chars:[13, 19, "item 2"]
  Paragraph[21, 31] isTrailingBlankLine
    Text[21, 30] chars:[21, 30, "1) item b"]
  Paragraph[32, 42] isTrailingBlankLine
    Text[32, 41] chars:[32, 41, "2) item c"]
````````````````````````````````


An ordered list after bullet list with no bullet matching

```````````````````````````````` example(List - Marker Options: 3) options(list-no-bullet-match)
- item 1
- item 2
+ item 3
* item 4

2. item 1
1. item 2
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
  <li>item 4</li>
</ul>
<ol start="2">
  <li>item 1</li>
  <li>item 2</li>
</ol>
.
Document[0, 56]
  BulletList[0, 36] isTight
    BulletListItem[0, 9] open:[0, 1, "-"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "-"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    BulletListItem[18, 27] open:[18, 19, "+"] isTight
      Paragraph[20, 27]
        Text[20, 26] chars:[20, 26, "item 3"]
    BulletListItem[27, 36] open:[27, 28, "*"] isTight hadBlankLineAfter
      Paragraph[29, 36] isTrailingBlankLine
        Text[29, 35] chars:[29, 35, "item 4"]
  OrderedList[37, 56] isTight start:2 delimiter:'.'
    OrderedListItem[37, 47] open:[37, 39, "2."] isTight
      Paragraph[40, 47]
        Text[40, 46] chars:[40, 46, "item 1"]
    OrderedListItem[47, 56] open:[47, 49, "1."] isTight
      Paragraph[50, 56]
        Text[50, 56] chars:[50, 56, "item 2"]
````````````````````````````````


A bullet list after an ordered list with no bullet matching

```````````````````````````````` example(List - Marker Options: 4) options(list-no-bullet-match)
2. item 1
1. item 2

- item 1
- item 2
+ item 3
* item 4
.
<ol start="2">
  <li>item 1</li>
  <li>item 2</li>
</ol>
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
  <li>item 4</li>
</ul>
.
Document[0, 56]
  OrderedList[0, 20] isTight start:2 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "2."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "1."] isTight hadBlankLineAfter
      Paragraph[13, 20] isTrailingBlankLine
        Text[13, 19] chars:[13, 19, "item 2"]
  BulletList[21, 56] isTight
    BulletListItem[21, 30] open:[21, 22, "-"] isTight
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 1"]
    BulletListItem[30, 39] open:[30, 31, "-"] isTight
      Paragraph[32, 39]
        Text[32, 38] chars:[32, 38, "item 2"]
    BulletListItem[39, 48] open:[39, 40, "+"] isTight
      Paragraph[41, 48]
        Text[41, 47] chars:[41, 47, "item 3"]
    BulletListItem[48, 56] open:[48, 49, "*"] isTight
      Paragraph[50, 56]
        Text[50, 56] chars:[50, 56, "item 4"]
````````````````````````````````


An ordered list after bullet list, no type match

```````````````````````````````` example(List - Marker Options: 5) options(list-no-type-match)
- item 1
- item 2

2. item 1
1. item 2
.
<ul>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
</ul>
.
Document[0, 38]
  BulletList[0, 38] isLoose
    BulletListItem[0, 9] open:[0, 1, "-"] isLoose
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "-"] isLoose hadBlankLineAfter
      Paragraph[11, 18] isTrailingBlankLine
        Text[11, 17] chars:[11, 17, "item 2"]
    OrderedListItem[19, 29] open:[19, 21, "2."] isLoose
      Paragraph[22, 29]
        Text[22, 28] chars:[22, 28, "item 1"]
    OrderedListItem[29, 38] open:[29, 31, "1."] isLoose
      Paragraph[32, 38]
        Text[32, 38] chars:[32, 38, "item 2"]
````````````````````````````````


A bullet list after an ordered list, no type match

```````````````````````````````` example(List - Marker Options: 6) options(list-no-type-match)
2. item 1
1. item 2

- item 1
- item 2
.
<ol start="2">
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
</ol>
.
Document[0, 38]
  OrderedList[0, 38] isLoose start:2 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "2."] isLoose
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "1."] isLoose hadBlankLineAfter
      Paragraph[13, 20] isTrailingBlankLine
        Text[13, 19] chars:[13, 19, "item 2"]
    BulletListItem[21, 30] open:[21, 22, "-"] isLoose
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 1"]
    BulletListItem[30, 38] open:[30, 31, "-"] isLoose
      Paragraph[32, 38]
        Text[32, 38] chars:[32, 38, "item 2"]
````````````````````````````````


An ordered list item can interrupt a previous list item's paragraph

```````````````````````````````` example List - Marker Options: 7
1. item 1
lazy continuation
2. item 2
.
<ol>
  <li>item 1
  lazy continuation</li>
  <li>item 2</li>
</ol>
.
Document[0, 38]
  OrderedList[0, 38] isTight delimiter:'.'
    OrderedListItem[0, 28] open:[0, 2, "1."] isTight
      Paragraph[3, 28]
        Text[3, 9] chars:[3, 9, "item 1"]
        SoftLineBreak[9, 10]
        Text[10, 27] chars:[10, 27, "lazy  … ation"]
    OrderedListItem[28, 38] open:[28, 30, "2."] isTight
      Paragraph[31, 38]
        Text[31, 37] chars:[31, 37, "item 2"]
````````````````````````````````


An ordered list sub item can interrupt its parent item's paragraph even if it does not start
with 1 when start setting is disabled.

```````````````````````````````` example(List - Marker Options: 8) options(list-no-start)
1. item 1
lazy continuation
   2. item 1.1
.
<ol>
  <li>item 1
  lazy continuation
    <ol>
      <li>item 1.1</li>
    </ol>
  </li>
</ol>
.
Document[0, 42]
  OrderedList[0, 42] isTight delimiter:'.'
    OrderedListItem[0, 42] open:[0, 2, "1."] isTight
      Paragraph[3, 28]
        Text[3, 9] chars:[3, 9, "item 1"]
        SoftLineBreak[9, 10]
        Text[10, 27] chars:[10, 27, "lazy  … ation"]
      OrderedList[31, 42] isTight start:2 delimiter:'.'
        OrderedListItem[31, 42] open:[31, 33, "2."] isTight
          Paragraph[34, 42]
            Text[34, 42] chars:[34, 42, "item 1.1"]
````````````````````````````````


nested

```````````````````````````````` example(List - Marker Options: 9) options(list-no-start)
4. item 1
3. item 2
   2. item 2.1
1. item 3
.
<ol>
  <li>item 1</li>
  <li>item 2
    <ol>
      <li>item 2.1</li>
    </ol>
  </li>
  <li>item 3</li>
</ol>
.
Document[0, 44]
  OrderedList[0, 44] isTight start:4 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "4."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 35] open:[10, 12, "3."] isTight
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 2"]
      OrderedList[23, 35] isTight start:2 delimiter:'.'
        OrderedListItem[23, 35] open:[23, 25, "2."] isTight
          Paragraph[26, 35]
            Text[26, 34] chars:[26, 34, "item 2.1"]
    OrderedListItem[35, 44] open:[35, 37, "1."] isTight
      Paragraph[38, 44]
        Text[38, 44] chars:[38, 44, "item 3"]
````````````````````````````````


nested, no ordered start, no ordered para break, no ordered item paragraph break

```````````````````````````````` example(List - Marker Options: 10) options(list-no-start, ordered-no-para-break, ordered-no-item-break)
4. item 1
3. item 2
   2. item 2.1
1. item 3
.
<ol>
  <li>item 1
  3. item 2
  2. item 2.1
  1. item 3</li>
</ol>
.
Document[0, 44]
  OrderedList[0, 44] isTight start:4 delimiter:'.'
    OrderedListItem[0, 44] open:[0, 2, "4."] isTight
      Paragraph[3, 44]
        Text[3, 9] chars:[3, 9, "item 1"]
        SoftLineBreak[9, 10]
        Text[10, 19] chars:[10, 19, "3. item 2"]
        SoftLineBreak[19, 20]
        Text[23, 34] chars:[23, 34, "2. it … m 2.1"]
        SoftLineBreak[34, 35]
        Text[35, 44] chars:[35, 44, "1. item 3"]
````````````````````````````````


nested, no ordered start, no ordered para break, no ordered item paragraph break

```````````````````````````````` example(List - Marker Options: 11) options(list-no-start, ordered-no-para-break, ordered-no-item-break)
4. item 1

3. item 2

   2. item 2.1
    
1. item 3
.
<ol>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
    <ol>
      <li>item 2.1</li>
    </ol>
  </li>
  <li>
    <p>item 3</p>
  </li>
</ol>
.
Document[0, 51]
  OrderedList[0, 51] isLoose start:4 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "4."] isLoose hadBlankLineAfter
      Paragraph[3, 10] isTrailingBlankLine
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[11, 37] open:[11, 13, "3."] isLoose hadBlankLineAfter
      Paragraph[14, 21] isTrailingBlankLine
        Text[14, 20] chars:[14, 20, "item 2"]
      OrderedList[25, 37] isTight start:2 delimiter:'.'
        OrderedListItem[25, 37] open:[25, 27, "2."] isTight hadBlankLineAfter
          Paragraph[28, 37] isTrailingBlankLine
            Text[28, 36] chars:[28, 36, "item 2.1"]
    OrderedListItem[42, 51] open:[42, 44, "1."] isLoose
      Paragraph[45, 51]
        Text[45, 51] chars:[45, 51, "item 3"]
````````````````````````````````


no relaxed ordered start with exception for another item's paragraph

```````````````````````````````` example(List - Marker Options: 12) options(list-no-start, ordered-no-para-break)
4. item 1
3. item 2
   2. item 2.1
    
   paragraph 
   1. with lazy continuation looking like an item
1. item 3
.
<ol>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
    <ol>
      <li>item 2.1</li>
    </ol>
    <p>paragraph
    1. with lazy continuation looking like an item</p>
  </li>
  <li>
    <p>item 3</p>
  </li>
</ol>
.
Document[0, 113]
  OrderedList[0, 113] isLoose start:4 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "4."] isLoose
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 104] open:[10, 12, "3."] isLoose hadBlankLine
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 2"]
      OrderedList[23, 35] isTight start:2 delimiter:'.'
        OrderedListItem[23, 35] open:[23, 25, "2."] isTight hadBlankLineAfter
          Paragraph[26, 35] isTrailingBlankLine
            Text[26, 34] chars:[26, 34, "item 2.1"]
      Paragraph[43, 104]
        Text[43, 52] chars:[43, 52, "paragraph"]
        SoftLineBreak[53, 54]
        Text[57, 103] chars:[57, 103, "1. wi …  item"]
    OrderedListItem[104, 113] open:[104, 106, "1."] isLoose
      Paragraph[107, 113]
        Text[107, 113] chars:[107, 113, "item 3"]
````````````````````````````````


no relaxed ordered start with exception for another item's paragraph but only if manual list
start is enabled

```````````````````````````````` example(List - Marker Options: 13) options(ordered-no-para-break, ordered-no-item-break)
4. item 1
3. item 2
   2. item 2.1
   
   paragraph 
   1. with lazy continuation looking like an item
1. item 3
.
<ol start="4">
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2
    2. item 2.1</p>
    <p>paragraph
    1. with lazy continuation looking like an item</p>
  </li>
  <li>
    <p>item 3</p>
  </li>
</ol>
.
Document[0, 112]
  OrderedList[0, 112] isLoose start:4 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "4."] isLoose
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 103] open:[10, 12, "3."] isLoose hadBlankLineAfter
      Paragraph[13, 35] isTrailingBlankLine
        Text[13, 19] chars:[13, 19, "item 2"]
        SoftLineBreak[19, 20]
        Text[23, 34] chars:[23, 34, "2. it … m 2.1"]
      Paragraph[42, 103]
        Text[42, 51] chars:[42, 51, "paragraph"]
        SoftLineBreak[52, 53]
        Text[56, 102] chars:[56, 102, "1. wi …  item"]
    OrderedListItem[103, 112] open:[103, 105, "1."] isLoose
      Paragraph[106, 112]
        Text[106, 112] chars:[106, 112, "item 3"]
````````````````````````````````


nested, no bullet para break, no bullet item paragraph break

```````````````````````````````` example(List - Marker Options: 14) options(bullet-no-para-break, bullet-no-item-break)
- item 1
- item 2
  - item 2.1
- item 3
.
<ul>
  <li>item 1
  - item 2
  - item 2.1
  - item 3</li>
</ul>
.
Document[0, 39]
  BulletList[0, 39] isTight
    BulletListItem[0, 39] open:[0, 1, "-"] isTight
      Paragraph[2, 39]
        Text[2, 8] chars:[2, 8, "item 1"]
        SoftLineBreak[8, 9]
        Text[9, 17] chars:[9, 17, "- item 2"]
        SoftLineBreak[17, 18]
        Text[20, 30] chars:[20, 30, "- item 2.1"]
        SoftLineBreak[30, 31]
        Text[31, 39] chars:[31, 39, "- item 3"]
````````````````````````````````


nested, no ordered start, no ordered para break, no ordered item paragraph break

```````````````````````````````` example(List - Marker Options: 15) options(bullet-no-para-break, bullet-no-item-break)
- item 1

- item 2

  - item 2.1
    
- item 3
.
<ul>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
    <ul>
      <li>item 2.1</li>
    </ul>
  </li>
  <li>
    <p>item 3</p>
  </li>
</ul>
.
Document[0, 46]
  BulletList[0, 46] isLoose
    BulletListItem[0, 9] open:[0, 1, "-"] isLoose hadBlankLineAfter
      Paragraph[2, 9] isTrailingBlankLine
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[10, 33] open:[10, 11, "-"] isLoose hadBlankLineAfter
      Paragraph[12, 19] isTrailingBlankLine
        Text[12, 18] chars:[12, 18, "item 2"]
      BulletList[22, 33] isTight
        BulletListItem[22, 33] open:[22, 23, "-"] isTight hadBlankLineAfter
          Paragraph[24, 33] isTrailingBlankLine
            Text[24, 32] chars:[24, 32, "item 2.1"]
    BulletListItem[38, 46] open:[38, 39, "-"] isLoose
      Paragraph[40, 46]
        Text[40, 46] chars:[40, 46, "item 3"]
````````````````````````````````


mismatched item to sub-item

```````````````````````````````` example(List - Marker Options: 16) options(list-item-mismatch-to-subitem)
- item
1. sub-item
1. sub-item
- item
1. sub-item
1. sub-item
.
<ul>
  <li>item
    <ol>
      <li>sub-item</li>
      <li>sub-item</li>
    </ol>
  </li>
  <li>item
    <ol>
      <li>sub-item</li>
      <li>sub-item</li>
    </ol>
  </li>
</ul>
.
Document[0, 61]
  BulletList[0, 61] isTight
    BulletListItem[0, 31] open:[0, 1, "-"] isTight
      Paragraph[2, 7]
        Text[2, 6] chars:[2, 6, "item"]
      OrderedList[7, 31] isTight delimiter:'.'
        OrderedListItem[7, 19] open:[7, 9, "1."] isTight
          Paragraph[10, 19]
            Text[10, 18] chars:[10, 18, "sub-item"]
        OrderedListItem[19, 31] open:[19, 21, "1."] isTight
          Paragraph[22, 31]
            Text[22, 30] chars:[22, 30, "sub-item"]
    BulletListItem[31, 61] open:[31, 32, "-"] isTight
      Paragraph[33, 38]
        Text[33, 37] chars:[33, 37, "item"]
      OrderedList[38, 61] isTight delimiter:'.'
        OrderedListItem[38, 50] open:[38, 40, "1."] isTight
          Paragraph[41, 50]
            Text[41, 49] chars:[41, 49, "sub-item"]
        OrderedListItem[50, 61] open:[50, 52, "1."] isTight
          Paragraph[53, 61]
            Text[53, 61] chars:[53, 61, "sub-item"]
````````````````````````````````


mismatched item to sub-item

```````````````````````````````` example(List - Marker Options: 17) options(list-item-mismatch-to-subitem)
1. item
- sub-item
- sub-item
1. item
- sub-item
- sub-item
.
<ol>
  <li>item
    <ul>
      <li>sub-item</li>
      <li>sub-item</li>
    </ul>
  </li>
  <li>item
    <ul>
      <li>sub-item</li>
      <li>sub-item</li>
    </ul>
  </li>
</ol>
.
Document[0, 59]
  OrderedList[0, 59] isTight delimiter:'.'
    OrderedListItem[0, 30] open:[0, 2, "1."] isTight
      Paragraph[3, 8]
        Text[3, 7] chars:[3, 7, "item"]
      BulletList[8, 30] isTight
        BulletListItem[8, 19] open:[8, 9, "-"] isTight
          Paragraph[10, 19]
            Text[10, 18] chars:[10, 18, "sub-item"]
        BulletListItem[19, 30] open:[19, 20, "-"] isTight
          Paragraph[21, 30]
            Text[21, 29] chars:[21, 29, "sub-item"]
    OrderedListItem[30, 59] open:[30, 32, "1."] isTight
      Paragraph[33, 38]
        Text[33, 37] chars:[33, 37, "item"]
      BulletList[38, 59] isTight
        BulletListItem[38, 49] open:[38, 39, "-"] isTight
          Paragraph[40, 49]
            Text[40, 48] chars:[40, 48, "sub-item"]
        BulletListItem[49, 59] open:[49, 50, "-"] isTight
          Paragraph[51, 59]
            Text[51, 59] chars:[51, 59, "sub-item"]
````````````````````````````````


### Thematic Break - No Relaxed Rules

With relaxed rules. Thematic break can occur without a preceding blank line. Applies to
non-dashed thematic break, dashes are a heading.

```````````````````````````````` example Thematic Break - No Relaxed Rules: 1
This is a paragraph
***
.
<p>This is a paragraph</p>
<hr />
.
Document[0, 24]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  ThematicBreak[20, 23]
````````````````````````````````


Without relaxed rules. Thematic break must be preceded by a blank line. Applies to non-dashed
thematic break, dashes are a heading.

```````````````````````````````` example(Thematic Break - No Relaxed Rules: 2) options(thematic-break-no-relaxed-start)
This is a paragraph
***
.
<p>This is a paragraph
***</p>
.
Document[0, 23]
  Paragraph[0, 23]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 23] chars:[20, 23, "***"]
````````````````````````````````


Nested in other elements

```````````````````````````````` example Thematic Break - No Relaxed Rules: 3
> - ***
.
<blockquote>
  <ul>
    <li>
    <hr />
    </li>
  </ul>
</blockquote>
.
Document[0, 8]
  BlockQuote[0, 7] marker:[0, 1, ">"]
    BulletList[2, 7] isTight
      BulletListItem[2, 7] open:[2, 3, "-"] isTight
        ThematicBreak[4, 7]
````````````````````````````````


### HTML Options

#### HTML Encode Options

Default pass it all through

```````````````````````````````` example HTML Encode Options: 1
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<!-- html comment block -->
<p>paragraph</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Encode all html

```````````````````````````````` example(HTML Encode Options: 2) options(escape-html)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<p>&lt;div&gt;
&lt;p&gt;paragraph&lt;/p&gt;
&lt;/div&gt;</p>
&lt;!-- html comment block --&gt;
<p>&lt;p&gt;paragraph&lt;/p&gt;</p>
<p>This is a paragraph with html &lt;span style=&quot;color:red;&quot;&gt;Test&lt;/span&gt; and an html comment &lt;!-- comment --&gt; embedded in it.</p>
.
Document[0, 196]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 196]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Encode html blocks

```````````````````````````````` example(HTML Encode Options: 3) options(escape-html-blocks)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<p>&lt;div&gt;
&lt;p&gt;paragraph&lt;/p&gt;
&lt;/div&gt;</p>
&lt;!-- html comment block --&gt;
<p>&lt;p&gt;paragraph&lt;/p&gt;</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.</p>
.
Document[0, 196]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 196]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Encode html block comments

```````````````````````````````` example(HTML Encode Options: 4) options(escape-html-comment-blocks)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
&lt;!-- html comment block --&gt;
<p>paragraph</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.</p>
.
Document[0, 196]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 196]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Encode inline html

```````````````````````````````` example(HTML Encode Options: 5) options(escape-inline-html)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<!-- html comment block -->
<p>paragraph</p>
<p>This is a paragraph with html &lt;span style=&quot;color:red;&quot;&gt;Test&lt;/span&gt; and an html comment &lt;!-- comment --&gt; embedded in it.</p>
.
Document[0, 196]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 196]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Encode inline html comments

```````````````````````````````` example(HTML Encode Options: 6) options(escape-inline-html-comments)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<!-- html comment block -->
<p>paragraph</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment &lt;!-- comment --&gt; embedded in it.</p>
.
Document[0, 196]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 196]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


#### HTML Suppress Options

Suppress all html

```````````````````````````````` example(HTML Suppress Options: 1) options(suppress-html)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<p>This is a paragraph with html Test and an html comment  embedded in it.</p>
.
Document[0, 196]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 196]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Suppress html blocks

```````````````````````````````` example(HTML Suppress Options: 2) options(suppress-html-blocks)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.</p>
.
Document[0, 196]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 196]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Suppress html comment blocks

```````````````````````````````` example(HTML Suppress Options: 3) options(suppress-html-comment-blocks)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<p>paragraph</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.</p>
.
Document[0, 196]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 196]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Suppress inline html

```````````````````````````````` example(HTML Suppress Options: 4) options(suppress-inline-html)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<!-- html comment block -->
<p>paragraph</p>
<p>This is a paragraph with html Test and an html comment  embedded in it.</p>
.
Document[0, 196]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 196]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Suppress inline html comments

```````````````````````````````` example(HTML Suppress Options: 5) options(suppress-inline-html-comments)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<!-- html comment block -->
<p>paragraph</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment  embedded in it.</p>
.
Document[0, 196]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 196]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


## HTML Parse Inner Comments

Html comments in block

```````````````````````````````` example HTML Parse Inner Comments: 1
<!-- HTML Comment -->
<div>
</div>
.
<!-- HTML Comment -->
<div>
</div>
.
Document[0, 35]
  HtmlCommentBlock[0, 22]
  HtmlBlock[22, 35]
````````````````````````````````


Html comments in block

```````````````````````````````` example HTML Parse Inner Comments: 2
<div>
    <!-- HTML Comment -->
</div>
.
<div>
    <!-- HTML Comment -->
</div>
.
Document[0, 39]
  HtmlBlock[0, 39]
````````````````````````````````


Html comments in block

```````````````````````````````` example HTML Parse Inner Comments: 3
<div>
</div>
<!-- HTML Comment -->
.
<div>
</div>
<!-- HTML Comment -->
.
Document[0, 35]
  HtmlBlock[0, 35]
````````````````````````````````


Html comments in block, parse inner comments

```````````````````````````````` example(HTML Parse Inner Comments: 4) options(parse-inner-comments)
<!-- HTML Comment -->
<div>
</div>
.
<!-- HTML Comment -->
<div>
</div>
.
Document[0, 34]
  HtmlCommentBlock[0, 22]
  HtmlBlock[22, 34]
````````````````````````````````


Html comments in block, parse inner comments

```````````````````````````````` example(HTML Parse Inner Comments: 5) options(parse-inner-comments)
<div>
    <!-- HTML Comment -->
</div>
.
<div>
    <!-- HTML Comment -->
</div>
.
Document[0, 38]
  HtmlBlock[0, 38]
    HtmlInnerBlock[0, 10] chars:[0, 10, "<div>\n    "]
    HtmlInnerBlockComment[10, 31] chars:[10, 31, "<!--  … t -->"]
    HtmlInnerBlock[31, 38] chars:[31, 38, "\n</div>"]
````````````````````````````````


Html comments in block, parse inner comments

```````````````````````````````` example(HTML Parse Inner Comments: 6) options(parse-inner-comments)
<div>
</div>
<!-- HTML Comment -->
.
<div>
</div>
<!-- HTML Comment -->
.
Document[0, 34]
  HtmlBlock[0, 34]
    HtmlInnerBlock[0, 13] chars:[0, 13, "<div> … div>\n"]
    HtmlInnerBlockComment[13, 34] chars:[13, 34, "<!--  … t -->"]
````````````````````````````````


## Block HTML

math tag

```````````````````````````````` example Block HTML: 1
<math></math>
.
<math></math>
.
Document[0, 14]
  HtmlBlock[0, 14]
````````````````````````````````


user tag

```````````````````````````````` example(Block HTML: 2) options(user-block-tags)
<tag></tag>
.
<tag></tag>
.
Document[0, 11]
  HtmlBlock[0, 11]
````````````````````````````````


## Inline HTML

kbd tag

```````````````````````````````` example Inline HTML: 1
text with <kbd>ENTER</kbd> embedded
.
<p>text with <kbd>ENTER</kbd> embedded</p>
.
Document[0, 36]
  Paragraph[0, 36]
    Text[0, 10] chars:[0, 10, "text with "]
    HtmlInline[10, 15] chars:[10, 15, "<kbd>"]
    Text[15, 20] chars:[15, 20, "ENTER"]
    HtmlInline[20, 26] chars:[20, 26, "</kbd>"]
    Text[26, 35] chars:[26, 35, " embedded"]
````````````````````````````````


non-blocks cannot start HTML block

```````````````````````````````` example(Inline HTML: 2) options(html-block-start-only-on-block-tags)
Best regards,

<br />
Vladimir.
.
<p>Best regards,</p>
<p><br />
Vladimir.</p>
.
Document[0, 31]
  Paragraph[0, 14] isTrailingBlankLine
    Text[0, 13] chars:[0, 13, "Best  … ards,"]
  Paragraph[15, 31]
    HtmlInline[15, 21] chars:[15, 21, "<br />"]
    SoftLineBreak[21, 22]
    Text[22, 31] chars:[22, 31, "Vladimir."]
````````````````````````````````


non-blocks start HTML block

```````````````````````````````` example Inline HTML: 3
Best regards,

<br />
Vladimir.
.
<p>Best regards,</p>
<br />
Vladimir.
.
Document[0, 32]
  Paragraph[0, 14] isTrailingBlankLine
    Text[0, 13] chars:[0, 13, "Best  … ards,"]
  HtmlBlock[15, 32]
````````````````````````````````


## GFM compatibility

### GFM Emphasis

Emphasis around inline code spans

```````````````````````````````` example GFM Emphasis: 1
please add  `add_gtest(`**`your_unittest`**` `**`your_unittest_unittest.cc`**` )`
.
<p>please add  <code>add_gtest(</code><strong><code>your_unittest</code></strong><code></code><strong><code>your_unittest_unittest.cc</code></strong><code>)</code></p>
.
Document[0, 82]
  Paragraph[0, 82]
    Text[0, 12] chars:[0, 12, "pleas … add  "]
    Code[12, 24] textOpen:[12, 13, "`"] text:[13, 23, "add_gtest("] textClose:[23, 24, "`"]
      Text[13, 23] chars:[13, 23, "add_gtest("]
    StrongEmphasis[24, 43] textOpen:[24, 26, "**"] text:[26, 41, "`your_unittest`"] textClose:[41, 43, "**"]
      Code[26, 41] textOpen:[26, 27, "`"] text:[27, 40, "your_ … unittest"] textClose:[40, 41, "`"]
        Text[27, 40] chars:[27, 40, "your_ … ttest"]
    Code[43, 46] textOpen:[43, 44, "`"] text:[44, 45, " "] textClose:[45, 46, "`"]
      Text[44, 45] chars:[44, 45, " "]
    StrongEmphasis[46, 77] textOpen:[46, 48, "**"] text:[48, 75, "`your_unittest_unittest.cc`"] textClose:[75, 77, "**"]
      Code[48, 75] textOpen:[48, 49, "`"] text:[49, 74, "your_ … unittest_unittest.cc"] textClose:[74, 75, "`"]
        Text[49, 74] chars:[49, 74, "your_ … st.cc"]
    Code[77, 81] textOpen:[77, 78, "`"] text:[78, 80, " )"] textClose:[80, 81, "`"]
      Text[78, 80] chars:[78, 80, " )"]
````````````````````````````````


Some weird commonmark processing of emphasis

```````````````````````````````` example GFM Emphasis: 2
**bold*bold-italic*bold**
.
<p><strong>bold<em>bold-italic</em>bold</strong></p>
.
Document[0, 26]
  Paragraph[0, 26]
    StrongEmphasis[0, 25] textOpen:[0, 2, "**"] text:[2, 23, "bold*bold-italic*bold"] textClose:[23, 25, "**"]
      Text[2, 6] chars:[2, 6, "bold"]
      Emphasis[6, 19] textOpen:[6, 7, "*"] text:[7, 18, "bold-italic"] textClose:[18, 19, "*"]
        Text[7, 18] chars:[7, 18, "bold- … talic"]
      Text[19, 23] chars:[19, 23, "bold"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 3
*a**b**c*
.
<p><em>a<strong>b</strong>c</em></p>
.
Document[0, 10]
  Paragraph[0, 10]
    Emphasis[0, 9] textOpen:[0, 1, "*"] text:[1, 8, "a**b**c"] textClose:[8, 9, "*"]
      Text[1, 2] chars:[1, 2, "a"]
      StrongEmphasis[2, 7] textOpen:[2, 4, "**"] text:[4, 5, "b"] textClose:[5, 7, "**"]
        Text[4, 5] chars:[4, 5, "b"]
      Text[7, 8] chars:[7, 8, "c"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 4
***a**b*
.
<p><em><strong>a</strong>b</em></p>
.
Document[0, 9]
  Paragraph[0, 9]
    Emphasis[0, 8] textOpen:[0, 1, "*"] text:[1, 7, "**a**b"] textClose:[7, 8, "*"]
      StrongEmphasis[1, 6] textOpen:[1, 3, "**"] text:[3, 4, "a"] textClose:[4, 6, "**"]
        Text[3, 4] chars:[3, 4, "a"]
      Text[6, 7] chars:[6, 7, "b"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 5
*b**a***

.
<p><em>b<strong>a</strong></em></p>
.
Document[0, 10]
  Paragraph[0, 9] isTrailingBlankLine
    Emphasis[0, 8] textOpen:[0, 1, "*"] text:[1, 7, "b**a**"] textClose:[7, 8, "*"]
      Text[1, 2] chars:[1, 2, "b"]
      StrongEmphasis[2, 7] textOpen:[2, 4, "**"] text:[4, 5, "a"] textClose:[5, 7, "**"]
        Text[4, 5] chars:[4, 5, "a"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 6
*a**b**c*

.
<p><em>a<strong>b</strong>c</em></p>
.
Document[0, 11]
  Paragraph[0, 10] isTrailingBlankLine
    Emphasis[0, 9] textOpen:[0, 1, "*"] text:[1, 8, "a**b**c"] textClose:[8, 9, "*"]
      Text[1, 2] chars:[1, 2, "a"]
      StrongEmphasis[2, 7] textOpen:[2, 4, "**"] text:[4, 5, "b"] textClose:[5, 7, "**"]
        Text[4, 5] chars:[4, 5, "b"]
      Text[7, 8] chars:[7, 8, "c"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 7
**a*b*c**

.
<p><strong>a<em>b</em>c</strong></p>
.
Document[0, 11]
  Paragraph[0, 10] isTrailingBlankLine
    StrongEmphasis[0, 9] textOpen:[0, 2, "**"] text:[2, 7, "a*b*c"] textClose:[7, 9, "**"]
      Text[2, 3] chars:[2, 3, "a"]
      Emphasis[3, 6] textOpen:[3, 4, "*"] text:[4, 5, "b"] textClose:[5, 6, "*"]
        Text[4, 5] chars:[4, 5, "b"]
      Text[6, 7] chars:[6, 7, "c"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 8
**a b*b*b c**

.
<p><strong>a b<em>b</em>b c</strong></p>
.
Document[0, 15]
  Paragraph[0, 14] isTrailingBlankLine
    StrongEmphasis[0, 13] textOpen:[0, 2, "**"] text:[2, 11, "a b*b*b c"] textClose:[11, 13, "**"]
      Text[2, 5] chars:[2, 5, "a b"]
      Emphasis[5, 8] textOpen:[5, 6, "*"] text:[6, 7, "b"] textClose:[7, 8, "*"]
        Text[6, 7] chars:[6, 7, "b"]
      Text[8, 11] chars:[8, 11, "b c"]
````````````````````````````````


This works as expected:

```````````````````````````````` example GFM Emphasis: 9
**bold *bold-italic* bold**
.
<p><strong>bold <em>bold-italic</em> bold</strong></p>
.
Document[0, 28]
  Paragraph[0, 28]
    StrongEmphasis[0, 27] textOpen:[0, 2, "**"] text:[2, 25, "bold *bold-italic* bold"] textClose:[25, 27, "**"]
      Text[2, 7] chars:[2, 7, "bold "]
      Emphasis[7, 20] textOpen:[7, 8, "*"] text:[8, 19, "bold-italic"] textClose:[19, 20, "*"]
        Text[8, 19] chars:[8, 19, "bold- … talic"]
      Text[20, 25] chars:[20, 25, " bold"]
````````````````````````````````


code mixed with emphasis:

```````````````````````````````` example GFM Emphasis: 10
`code with `**`bold`**` inside`
.
<p><code>code with</code><strong><code>bold</code></strong><code>inside</code></p>
.
Document[0, 32]
  Paragraph[0, 32]
    Code[0, 12] textOpen:[0, 1, "`"] text:[1, 11, "code with "] textClose:[11, 12, "`"]
      Text[1, 11] chars:[1, 11, "code with "]
    StrongEmphasis[12, 22] textOpen:[12, 14, "**"] text:[14, 20, "`bold`"] textClose:[20, 22, "**"]
      Code[14, 20] textOpen:[14, 15, "`"] text:[15, 19, "bold"] textClose:[19, 20, "`"]
        Text[15, 19] chars:[15, 19, "bold"]
    Code[22, 31] textOpen:[22, 23, "`"] text:[23, 30, " inside"] textClose:[30, 31, "`"]
      Text[23, 30] chars:[23, 30, " inside"]
````````````````````````````````


## Fenced Code Options

Change language class prefix

```````````````````````````````` example(Fenced Code Options: 1) options(no-class-prefix)
```text
plain text
```
.
<pre><code class="text">plain text
</code></pre>
.
Document[0, 22]
  FencedCodeBlock[0, 22] open:[0, 3, "```"] info:[3, 7, "text"] content:[8, 19] lines[1] close:[19, 22, "```"]
    Text[8, 19] chars:[8, 19, "plain … text\n"]
````````````````````````````````


no info with custom class

```````````````````````````````` example(Fenced Code Options: 2) options(no-language-class)
```
plain text
```
.
<pre><code class="nohighlight">plain text
</code></pre>
.
Document[0, 18]
  FencedCodeBlock[0, 18] open:[0, 3, "```"] content:[4, 15] lines[1] close:[15, 18, "```"]
    Text[4, 15] chars:[4, 15, "plain … text\n"]
````````````````````````````````


indented code with custom class

```````````````````````````````` example(Fenced Code Options: 3) options(no-language-class)
    plain text
.
<pre><code class="nohighlight">plain text
</code></pre>
.
Document[0, 14]
  IndentedCodeBlock[4, 14]
````````````````````````````````


empty, no info

```````````````````````````````` example Fenced Code Options: 4
```

```
.
<pre><code>
</code></pre>
.
Document[0, 9]
  FencedCodeBlock[0, 8] open:[0, 3, "```"] content:[4, 5] lines[1] close:[5, 8, "```"]
    Text[4, 5] chars:[4, 5, "\n"]
````````````````````````````````


empty, no info, blank line follows

```````````````````````````````` example Fenced Code Options: 5
```

```

.
<pre><code>
</code></pre>
.
Document[0, 10]
  FencedCodeBlock[0, 8] open:[0, 3, "```"] content:[4, 5] lines[1] close:[5, 8, "```"]
    Text[4, 5] chars:[4, 5, "\n"]
````````````````````````````````


empty, info

```````````````````````````````` example Fenced Code Options: 6
```info

```
.
<pre><code class="language-info">
</code></pre>
.
Document[0, 13]
  FencedCodeBlock[0, 12] open:[0, 3, "```"] info:[3, 7, "info"] content:[8, 9] lines[1] close:[9, 12, "```"]
    Text[8, 9] chars:[8, 9, "\n"]
````````````````````````````````


empty, info, blank line follows

```````````````````````````````` example Fenced Code Options: 7
```info

```

.
<pre><code class="language-info">
</code></pre>
.
Document[0, 14]
  FencedCodeBlock[0, 12] open:[0, 3, "```"] info:[3, 7, "info"] content:[8, 9] lines[1] close:[9, 12, "```"]
    Text[8, 9] chars:[8, 9, "\n"]
````````````````````````````````


Unclosed Fenced code should take all input to end

```````````````````````````````` example Fenced Code Options: 8
```
sample 
unclosed

fenced

code



.
<pre><code>sample 
unclosed

fenced

code



</code></pre>
.
Document[0, 38]
  FencedCodeBlock[0, 38] open:[0, 3, "```"] content:[4, 38] lines[9]
    Text[4, 38] chars:[4, 38, "sampl … e\n\n\n\n"]
````````````````````````````````


Wrap content in CodeBlock

```````````````````````````````` example(Fenced Code Options: 9) options(code-content-block)
```info
code content
   indented code line
```
.
<pre><code class="language-info">code content
   indented code line
</code></pre>
.
Document[0, 46]
  FencedCodeBlock[0, 46] open:[0, 3, "```"] info:[3, 7, "info"] content:[8, 43] lines[2] close:[43, 46, "```"]
    CodeBlock[8, 43]
````````````````````````````````


Wrap content in CodeBlock

```````````````````````````````` example(Fenced Code Options: 10) options(code-content-block)
    code content
       indented code line
.
<pre><code>code content
   indented code line
</code></pre>
.
Document[0, 42]
  IndentedCodeBlock[4, 42]
    CodeBlock[4, 42]
````````````````````````````````


## Anchor links option

Change language class prefix

```````````````````````````````` example Anchor links option: 1
inline anchor <a id="test" href="#"></a><em></em> test 
.
<p>inline anchor <a id="test" href="#"></a><em></em> test</p>
.
Document[0, 56]
  Paragraph[0, 56]
    Text[0, 14] chars:[0, 14, "inlin … chor "]
    HtmlInline[14, 36] chars:[14, 36, "<a id … =\"#\">"]
    HtmlInline[36, 40] chars:[36, 40, "</a>"]
    HtmlInline[40, 44] chars:[40, 44, "<em>"]
    HtmlInline[44, 49] chars:[44, 49, "</em>"]
    Text[49, 54] chars:[49, 54, " test"]
````````````````````````````````


## Thematic Break

Break with trailing spaces

```````````````````````````````` example Thematic Break: 1
---                 
.
<hr />
.
Document[0, 21]
  ThematicBreak[0, 20]
````````````````````````````````


## Image links

```````````````````````````````` example Image links: 1
![alt](/url) 
.
<p><img src="/url" alt="alt" /></p>
.
Document[0, 14]
  Paragraph[0, 14]
    Image[0, 12] textOpen:[0, 2, "!["] text:[2, 5, "alt"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 11, "/url"] pageRef:[7, 11, "/url"] linkClose:[11, 12, ")"]
      Text[2, 5] chars:[2, 5, "alt"]
````````````````````````````````


dummy ref

```````````````````````````````` example Image links: 2
[ref]: /url1

![ref][]
.
<p><img src="/url1" alt="ref" /></p>
.
Document[0, 23]
  Reference[0, 12] refOpen:[0, 1, "["] ref:[1, 4, "ref"] refClose:[4, 6, "]:"] url:[7, 12, "/url1"]
  Paragraph[14, 23]
    ImageRef[14, 22] referenceOpen:[14, 16, "!["] reference:[16, 19, "ref"] referenceClose:[19, 20, "]"] textOpen:[20, 21, "["] textClose:[21, 22, "]"]
      Text[16, 19] chars:[16, 19, "ref"]
````````````````````````````````


## Multi-Line Image URL

not parsed, invalid end

```````````````````````````````` example(Multi-Line Image URL: 1) options(multi-line-image-url)
![ref](/url1?
    )
.
<p><img src="/url1?" alt="ref" /></p>
.
Document[0, 19]
  Paragraph[0, 19]
    Image[0, 19] textOpen:[0, 2, "!["] text:[2, 5, "ref"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 13, "/url1?"] pageRef:[7, 13, "/url1?"] urlContent:[18, 18] linkClose:[18, 19, ")"]
      Text[2, 5] chars:[2, 5, "ref"]
````````````````````````````````


empty content

```````````````````````````````` example(Multi-Line Image URL: 2) options(multi-line-image-url)
![ref](/url1?
)
.
<p><img src="/url1?" alt="ref" /></p>
.
Document[0, 15]
  Paragraph[0, 15]
    Image[0, 15] textOpen:[0, 2, "!["] text:[2, 5, "ref"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 13, "/url1?"] pageRef:[7, 13, "/url1?"] urlContent:[14, 14] linkClose:[14, 15, ")"]
      Text[2, 5] chars:[2, 5, "ref"]
````````````````````````````````


empty content

```````````````````````````````` example(Multi-Line Image URL: 3) options(multi-line-image-url)
![ref](/url1?
   )
.
<p><img src="/url1?" alt="ref" /></p>
.
Document[0, 18]
  Paragraph[0, 18]
    Image[0, 18] textOpen:[0, 2, "!["] text:[2, 5, "ref"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 13, "/url1?"] pageRef:[7, 13, "/url1?"] urlContent:[17, 17] linkClose:[17, 18, ")"]
      Text[2, 5] chars:[2, 5, "ref"]
````````````````````````````````


simple content

```````````````````````````````` example(Multi-Line Image URL: 4) options(multi-line-image-url)
![ref](/url1?
one line
)
.
<p><img src="/url1?one%20line%0A" alt="ref" /></p>
.
Document[0, 24]
  Paragraph[0, 24]
    Image[0, 24] textOpen:[0, 2, "!["] text:[2, 5, "ref"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 13, "/url1?"] pageRef:[7, 13, "/url1?"] urlContent:[14, 23, "one line\n"] linkClose:[23, 24, ")"]
      Text[2, 5] chars:[2, 5, "ref"]
````````````````````````````````


simple content

```````````````````````````````` example(Multi-Line Image URL: 5) options(multi-line-image-url)
![ref](/url1?
one line
two line
)
.
<p><img src="/url1?one%20line%0Atwo%20line%0A" alt="ref" /></p>
.
Document[0, 33]
  Paragraph[0, 33]
    Image[0, 33] textOpen:[0, 2, "!["] text:[2, 5, "ref"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 13, "/url1?"] pageRef:[7, 13, "/url1?"] urlContent:[14, 32, "one line\ntwo line\n"] linkClose:[32, 33, ")"]
      Text[2, 5] chars:[2, 5, "ref"]
````````````````````````````````


false title

```````````````````````````````` example(Multi-Line Image URL: 6) options(multi-line-image-url)
![ref](/url1?
one line
"false title"
 "real title")
.
<p><img src="/url1?one%20line%0A%22false%20title%22%0A" alt="ref" title="real title" /></p>
.
Document[0, 51]
  Paragraph[0, 51]
    Image[0, 51] textOpen:[0, 2, "!["] text:[2, 5, "ref"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 13, "/url1?"] pageRef:[7, 13, "/url1?"] urlContent:[14, 37, "one line\n\"false title\"\n"] titleOpen:[38, 39, "\""] title:[39, 49, "real title"] titleClose:[49, 50, "\""] linkClose:[50, 51, ")"]
      Text[2, 5] chars:[2, 5, "ref"]
````````````````````````````````


trailing text

```````````````````````````````` example(Multi-Line Image URL: 7) options(multi-line-image-url)
![ref](/url1?
one line
two line
) trailing text
.
<p><img src="/url1?one%20line%0Atwo%20line%0A" alt="ref" /> trailing text</p>
.
Document[0, 47]
  Paragraph[0, 47]
    Image[0, 33] textOpen:[0, 2, "!["] text:[2, 5, "ref"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 13, "/url1?"] pageRef:[7, 13, "/url1?"] urlContent:[14, 32, "one line\ntwo line\n"] linkClose:[32, 33, ")"]
      Text[2, 5] chars:[2, 5, "ref"]
    Text[33, 47] chars:[33, 47, " trai …  text"]
````````````````````````````````


encoding of &, =

```````````````````````````````` example(Multi-Line Image URL: 8) options(multi-line-image-url)
![ref](/url1?
one = 1 & line
) trailing text
.
<p><img src="/url1?one%20=%201%20&amp;%20line%0A" alt="ref" /> trailing text</p>
.
Document[0, 44]
  Paragraph[0, 44]
    Image[0, 30] textOpen:[0, 2, "!["] text:[2, 5, "ref"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 13, "/url1?"] pageRef:[7, 13, "/url1?"] urlContent:[14, 29, "one = 1 & line\n"] linkClose:[29, 30, ")"]
      Text[2, 5] chars:[2, 5, "ref"]
    Text[30, 44] chars:[30, 44, " trai …  text"]
````````````````````````````````


encoding of +

```````````````````````````````` example(Multi-Line Image URL: 9) options(multi-line-image-url)
![ref](/url1?
one = 1 + line
) trailing text
.
<p><img src="/url1?one%20=%201%20%2B%20line%0A" alt="ref" /> trailing text</p>
.
Document[0, 44]
  Paragraph[0, 44]
    Image[0, 30] textOpen:[0, 2, "!["] text:[2, 5, "ref"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 13, "/url1?"] pageRef:[7, 13, "/url1?"] urlContent:[14, 29, "one = 1 + line\n"] linkClose:[29, 30, ")"]
      Text[2, 5] chars:[2, 5, "ref"]
    Text[30, 44] chars:[30, 44, " trai …  text"]
````````````````````````````````


encoding of embedded EOL

```````````````````````````````` example(Multi-Line Image URL: 10) options(multi-line-image-url)
![ref](/url1?
one = 1 & line \\
line two \\
) trailing text
.
<p><img src="/url1?one%20=%201%20&amp;%20line%20%5C%5C%0Aline%20two%20%5C%5C%0A" alt="ref" /> trailing text</p>
.
Document[0, 59]
  Paragraph[0, 59]
    Image[0, 45] textOpen:[0, 2, "!["] text:[2, 5, "ref"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 13, "/url1?"] pageRef:[7, 13, "/url1?"] urlContent:[14, 44, "one = 1 & line \\\nline two \\\n"] linkClose:[44, 45, ")"]
      Text[2, 5] chars:[2, 5, "ref"]
    Text[45, 59] chars:[45, 59, " trai …  text"]
````````````````````````````````


## Fenced Code

Option not to match closing fence characters to opening ones

```````````````````````````````` example(Fenced Code: 1) options(unmatched-fence)
```
proper unmatched fenced code
~~~
.
<pre><code>proper unmatched fenced code
</code></pre>
.
Document[0, 36]
  FencedCodeBlock[0, 36] open:[0, 3, "```"] content:[4, 33] lines[1] close:[33, 36, "~~~"]
    Text[4, 33] chars:[4, 33, "prope … code\n"]
````````````````````````````````


```````````````````````````````` example(Fenced Code: 2) options(unmatched-fence)
~~~
proper unmatched fenced code
```
.
<pre><code>proper unmatched fenced code
</code></pre>
.
Document[0, 36]
  FencedCodeBlock[0, 36] open:[0, 3, "~~~"] content:[4, 33] lines[1] close:[33, 36, "```"]
    Text[4, 33] chars:[4, 33, "prope … code\n"]
````````````````````````````````


non empty, info, blank line follows, unmatched

```````````````````````````````` example(Fenced Code: 3) options(unmatched-fence)
```info
some text
~~~

.
<pre><code class="language-info">some text
</code></pre>
.
Document[0, 23]
  FencedCodeBlock[0, 21] open:[0, 3, "```"] info:[3, 7, "info"] content:[8, 18] lines[1] close:[18, 21, "~~~"]
    Text[8, 18] chars:[8, 18, "some text\n"]
````````````````````````````````


## IntelliJ Dummy Identifier

allow dummy identifier in url and text

```````````````````````````````` example(IntelliJ Dummy Identifier: 1) options(dummy-identifier)
[⎮text](/ur⎮l)

![al⎮t](/url⎮)

.
<p><a href="/ur%1Fl">⎮text</a></p>
<p><img src="/url%1F" alt="al⎮t" /></p>
.
Document[0, 32]
  Paragraph[0, 15] isTrailingBlankLine
    Link[0, 14] textOpen:[0, 1, "["] text:[1, 6, "%1ftext"] textClose:[6, 7, "]"] linkOpen:[7, 8, "("] url:[8, 13, "/ur%1fl"] pageRef:[8, 13, "/ur%1fl"] linkClose:[13, 14, ")"]
      Text[1, 6] chars:[1, 6, "%1ftext"]
  Paragraph[16, 31] isTrailingBlankLine
    Image[16, 30] textOpen:[16, 18, "!["] text:[18, 22, "al%1ft"] textClose:[22, 23, "]"] linkOpen:[23, 24, "("] url:[24, 29, "/url%1f"] pageRef:[24, 29, "/url%1f"] linkClose:[29, 30, ")"]
      Text[18, 22] chars:[18, 22, "al%1ft"]
````````````````````````````````


## Indented Code Options

trim trailing blank lines by default

```````````````````````````````` example Indented Code Options: 1
    code
    code line
    
    
    
.
<pre><code>code
code line
</code></pre>
.
Document[0, 38]
  IndentedCodeBlock[4, 23]
````````````````````````````````


don't trim trailing blank lines

```````````````````````````````` example(Indented Code Options: 2) options(code-no-trim-trailing)
    code
    code line
    
    
    
.
<pre><code>code
code line
</code></pre>
.
Document[0, 38]
  IndentedCodeBlock[4, 38]
````````````````````````````````


## Links

Url encoded link address should not % encode the query separator `&`

```````````````````````````````` example Links: 1
[test](http://url?opt=a&opt1=b)
.
<p><a href="http://url?opt=a&amp;opt1=b">test</a></p>
.
Document[0, 32]
  Paragraph[0, 32]
    Link[0, 31] textOpen:[0, 1, "["] text:[1, 5, "test"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 30, "http://url?opt=a&opt1=b"] pageRef:[7, 30, "http://url?opt=a&opt1=b"] linkClose:[30, 31, ")"]
      Text[1, 5] chars:[1, 5, "test"]
````````````````````````````````


```````````````````````````````` example Links: 2
[ref]: /url1

[ref][]
.
<p><a href="/url1">ref</a></p>
.
Document[0, 22]
  Reference[0, 12] refOpen:[0, 1, "["] ref:[1, 4, "ref"] refClose:[4, 6, "]:"] url:[7, 12, "/url1"]
  Paragraph[14, 22]
    LinkRef[14, 21] referenceOpen:[14, 15, "["] reference:[15, 18, "ref"] referenceClose:[18, 19, "]"] textOpen:[19, 20, "["] textClose:[20, 21, "]"]
      Text[15, 18] chars:[15, 18, "ref"]
````````````````````````````````


Escaped chars in links

```````````````````````````````` example Links: 3
[test](http://url\(.com\))   

![test](http://url\(.com\))
.
<p><a href="http://url(.com)">test</a></p>
<p><img src="http://url(.com)" alt="test" /></p>
.
Document[0, 59]
  Paragraph[0, 30] isTrailingBlankLine
    Link[0, 26] textOpen:[0, 1, "["] text:[1, 5, "test"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 25, "http://url\(.com\)"] pageRef:[7, 25, "http://url\(.com\)"] linkClose:[25, 26, ")"]
      Text[1, 5] chars:[1, 5, "test"]
  Paragraph[31, 59]
    Image[31, 58] textOpen:[31, 33, "!["] text:[33, 37, "test"] textClose:[37, 38, "]"] linkOpen:[38, 39, "("] url:[39, 57, "http://url\(.com\)"] pageRef:[39, 57, "http://url\(.com\)"] linkClose:[57, 58, ")"]
      Text[33, 37] chars:[33, 37, "test"]
````````````````````````````````


```````````````````````````````` example Links: 4
[ref]: /url1\(.com\)

[ref][]

![ref][]
.
<p><a href="/url1(.com)">ref</a></p>
<p><img src="/url1(.com)" alt="ref" /></p>
.
Document[0, 40]
  Reference[0, 20] refOpen:[0, 1, "["] ref:[1, 4, "ref"] refClose:[4, 6, "]:"] url:[7, 20, "/url1\(.com\)"]
  Paragraph[22, 30] isTrailingBlankLine
    LinkRef[22, 29] referenceOpen:[22, 23, "["] reference:[23, 26, "ref"] referenceClose:[26, 27, "]"] textOpen:[27, 28, "["] textClose:[28, 29, "]"]
      Text[23, 26] chars:[23, 26, "ref"]
  Paragraph[31, 40]
    ImageRef[31, 39] referenceOpen:[31, 33, "!["] reference:[33, 36, "ref"] referenceClose:[36, 37, "]"] textOpen:[37, 38, "["] textClose:[38, 39, "]"]
      Text[33, 36] chars:[33, 36, "ref"]
````````````````````````````````


obfuscate e-mail

```````````````````````````````` example(Links: 5) options(obfuscate-email)
this <example@example.com>
.
<p>this <a href="&#109;&#x61;i&#x6c;&#116;&#x6f;&#58;&#101;xa&#x6d;&#x70;&#x6c;&#x65;&#64;ex&#97;&#109;&#112;&#x6c;&#x65;.&#x63;&#x6f;&#109;">&#x65;&#x78;&#x61;&#x6d;&#112;&#108;&#x65;&#x40;&#x65;&#x78;&#97;&#x6d;&#x70;&#108;&#101;&#46;&#99;&#111;&#x6d;</a></p>
.
Document[0, 26]
  Paragraph[0, 26]
    Text[0, 5] chars:[0, 5, "this "]
    MailLink[5, 26] textOpen:[5, 6, "<"] text:[6, 25, "example@example.com"] textClose:[25, 26, ">"]
````````````````````````````````


spaces in links allowed

```````````````````````````````` example(Links: 6) options(link-spaces)
[Sample Link Spaces](link with spaces.html)
.
<p><a href="link%20with%20spaces.html">Sample Link Spaces</a></p>
.
Document[0, 43]
  Paragraph[0, 43]
    Link[0, 43] textOpen:[0, 1, "["] text:[1, 19, "Sample Link Spaces"] textClose:[19, 20, "]"] linkOpen:[20, 21, "("] url:[21, 42, "link with spaces.html"] pageRef:[21, 42, "link with spaces.html"] linkClose:[42, 43, ")"]
      Text[1, 19] chars:[1, 19, "Sampl … paces"]
````````````````````````````````


spaces in links allowed with title

```````````````````````````````` example(Links: 7) options(link-spaces)
[Sample Link Spaces](link with spaces.html "title")
.
<p><a href="link%20with%20spaces.html" title="title">Sample Link Spaces</a></p>
.
Document[0, 51]
  Paragraph[0, 51]
    Link[0, 51] textOpen:[0, 1, "["] text:[1, 19, "Sample Link Spaces"] textClose:[19, 20, "]"] linkOpen:[20, 21, "("] url:[21, 42, "link with spaces.html"] pageRef:[21, 42, "link with spaces.html"] titleOpen:[43, 44, "\""] title:[44, 49, "title"] titleClose:[49, 50, "\""] linkClose:[50, 51, ")"]
      Text[1, 19] chars:[1, 19, "Sampl … paces"]
````````````````````````````````


```````````````````````````````` example(Links: 8) options(link-spaces)
[Sample Link Spaces](link with spaces.html 
"title")
.
<p><a href="link%20with%20spaces.html" title="title">Sample Link Spaces</a></p>
.
Document[0, 52]
  Paragraph[0, 52]
    Link[0, 52] textOpen:[0, 1, "["] text:[1, 19, "Sample Link Spaces"] textClose:[19, 20, "]"] linkOpen:[20, 21, "("] url:[21, 42, "link with spaces.html"] pageRef:[21, 42, "link with spaces.html"] titleOpen:[44, 45, "\""] title:[45, 50, "title"] titleClose:[50, 51, "\""] linkClose:[51, 52, ")"]
      Text[1, 19] chars:[1, 19, "Sampl … paces"]
````````````````````````````````


embed double quotes

```````````````````````````````` example(Links: 9) options(link-spaces)
[Sample Link Spaces](link" with spaces.html 
"title")
.
<p><a href="link%22%20with%20spaces.html" title="title">Sample Link Spaces</a></p>
.
Document[0, 53]
  Paragraph[0, 53]
    Link[0, 53] textOpen:[0, 1, "["] text:[1, 19, "Sample Link Spaces"] textClose:[19, 20, "]"] linkOpen:[20, 21, "("] url:[21, 43, "link\" with spaces.html"] pageRef:[21, 43, "link\" with spaces.html"] titleOpen:[45, 46, "\""] title:[46, 51, "title"] titleClose:[51, 52, "\""] linkClose:[52, 53, ")"]
      Text[1, 19] chars:[1, 19, "Sampl … paces"]
````````````````````````````````


```````````````````````````````` example(Links: 10) options(link-spaces)
[Sample Link Spaces](<link with spaces.html> "title")
.
<p><a href="link%20with%20spaces.html" title="title">Sample Link Spaces</a></p>
.
Document[0, 53]
  Paragraph[0, 53]
    Link[0, 53] textOpen:[0, 1, "["] text:[1, 19, "Sample Link Spaces"] textClose:[19, 20, "]"] linkOpen:[20, 21, "("] urlOpen:[21, 22, "<"] url:[22, 43, "link with spaces.html"] urlClose:[43, 44, ">"] pageRef:[22, 43, "link with spaces.html"] titleOpen:[45, 46, "\""] title:[46, 51, "title"] titleClose:[51, 52, "\""] linkClose:[52, 53, ")"]
      Text[1, 19] chars:[1, 19, "Sampl … paces"]
````````````````````````````````


```````````````````````````````` example(Links: 11) options(link-spaces)
[Sample Link Spaces](<link with spaces.html>  
"title")
.
<p><a href="link%20with%20spaces.html" title="title">Sample Link Spaces</a></p>
.
Document[0, 55]
  Paragraph[0, 55]
    Link[0, 55] textOpen:[0, 1, "["] text:[1, 19, "Sample Link Spaces"] textClose:[19, 20, "]"] linkOpen:[20, 21, "("] urlOpen:[21, 22, "<"] url:[22, 43, "link with spaces.html"] urlClose:[43, 44, ">"] pageRef:[22, 43, "link with spaces.html"] titleOpen:[47, 48, "\""] title:[48, 53, "title"] titleClose:[53, 54, "\""] linkClose:[54, 55, ")"]
      Text[1, 19] chars:[1, 19, "Sampl … paces"]
````````````````````````````````


embed double quotes

```````````````````````````````` example(Links: 12) options(link-spaces)
[Sample Link Spaces](<link" with spaces.html> 
"title")
.
<p><a href="link%22%20with%20spaces.html" title="title">Sample Link Spaces</a></p>
.
Document[0, 55]
  Paragraph[0, 55]
    Link[0, 55] textOpen:[0, 1, "["] text:[1, 19, "Sample Link Spaces"] textClose:[19, 20, "]"] linkOpen:[20, 21, "("] urlOpen:[21, 22, "<"] url:[22, 44, "link\" with spaces.html"] urlClose:[44, 45, ">"] pageRef:[22, 44, "link\" with spaces.html"] titleOpen:[47, 48, "\""] title:[48, 53, "title"] titleClose:[53, 54, "\""] linkClose:[54, 55, ")"]
      Text[1, 19] chars:[1, 19, "Sampl … paces"]
````````````````````````````````


no trailing space before >

```````````````````````````````` example(Links: 13) options(link-spaces)
[Sample Link Spaces](<link with spaces.html >  
"title")
.
<p><a href="link%20with%20spaces.html%20" title="title">Sample Link Spaces</a></p>
.
Document[0, 56]
  Paragraph[0, 56]
    Link[0, 56] textOpen:[0, 1, "["] text:[1, 19, "Sample Link Spaces"] textClose:[19, 20, "]"] linkOpen:[20, 21, "("] urlOpen:[21, 22, "<"] url:[22, 44, "link with spaces.html "] urlClose:[44, 45, ">"] pageRef:[22, 44, "link with spaces.html "] titleOpen:[48, 49, "\""] title:[49, 54, "title"] titleClose:[54, 55, "\""] linkClose:[55, 56, ")"]
      Text[1, 19] chars:[1, 19, "Sampl … paces"]
````````````````````````````````


embed double quotes

```````````````````````````````` example(Links: 14) options(link-spaces)
[Sample Link Spaces](<link" with spaces.html > 
"title")
.
<p><a href="link%22%20with%20spaces.html%20" title="title">Sample Link Spaces</a></p>
.
Document[0, 56]
  Paragraph[0, 56]
    Link[0, 56] textOpen:[0, 1, "["] text:[1, 19, "Sample Link Spaces"] textClose:[19, 20, "]"] linkOpen:[20, 21, "("] urlOpen:[21, 22, "<"] url:[22, 45, "link\" with spaces.html "] urlClose:[45, 46, ">"] pageRef:[22, 45, "link\" with spaces.html "] titleOpen:[48, 49, "\""] title:[49, 54, "title"] titleClose:[54, 55, "\""] linkClose:[55, 56, ")"]
      Text[1, 19] chars:[1, 19, "Sampl … paces"]
````````````````````````````````


```````````````````````````````` example Links: 15
<http://example.com/linkWithoutSpaces.html> 
.
<p><a href="http://example.com/linkWithoutSpaces.html">http://example.com/linkWithoutSpaces.html</a></p>
.
Document[0, 45]
  Paragraph[0, 45]
    AutoLink[0, 43] textOpen:[0, 1, "<"] text:[1, 42, "http://example.com/linkWithoutSpaces.html"] textClose:[42, 43, ">"]
````````````````````````````````


```````````````````````````````` example(Links: 16) options(link-spaces)
<http://example.com/link with spaces.html> 
.
<p>&lt;http://example.com/link with spaces.html&gt;</p>
.
Document[0, 43]
  Paragraph[0, 43]
    Text[0, 42] chars:[0, 42, "<http … html>"]
````````````````````````````````


```````````````````````````````` example(Links: 17) options(link-spaces)
<http://example.com/link with spaces.html > 
.
<p>&lt;http://example.com/link with spaces.html &gt;</p>
.
Document[0, 44]
  Paragraph[0, 44]
    Text[0, 43] chars:[0, 43, "<http … tml >"]
````````````````````````````````


embed double quotes

```````````````````````````````` example(Links: 18) options(link-spaces)
<http://example.com/link" with spaces.html>
.
<p>&lt;http://example.com/link&quot; with spaces.html&gt;</p>
.
Document[0, 43]
  Paragraph[0, 43]
    Text[0, 43] chars:[0, 43, "<http … html>"]
````````````````````````````````


```````````````````````````````` example(Links: 19) options(link-spaces)
<http://example.com/link" with spaces.html >
.
<p>&lt;http://example.com/link&quot; with spaces.html &gt;</p>
.
Document[0, 44]
  Paragraph[0, 44]
    Text[0, 44] chars:[0, 44, "<http … tml >"]
````````````````````````````````


Spaces in link elements

```````````````````````````````` example(Links: 20) options(space-in-link-elements)
[Test Text]       (/url)
.
<p><a href="/url">Test Text</a></p>
.
Document[0, 24]
  Paragraph[0, 24]
    Link[0, 24] textOpen:[0, 1, "["] text:[1, 10, "Test Text"] textClose:[10, 11, "]"] linkOpen:[18, 19, "("] url:[19, 23, "/url"] pageRef:[19, 23, "/url"] linkClose:[23, 24, ")"]
      Text[1, 10] chars:[1, 10, "Test Text"]
````````````````````````````````


Spaces in image elements

```````````````````````````````` example(Links: 21) options(space-in-link-elements)
![Test Text]        (/url)
.
<p><img src="/url" alt="Test Text" /></p>
.
Document[0, 26]
  Paragraph[0, 26]
    Image[0, 26] textOpen:[0, 2, "!["] text:[2, 11, "Test Text"] textClose:[11, 12, "]"] linkOpen:[20, 21, "("] url:[21, 25, "/url"] pageRef:[21, 25, "/url"] linkClose:[25, 26, ")"]
      Text[2, 11] chars:[2, 11, "Test Text"]
````````````````````````````````


## LinkRefs and ImageRefs

empty link ref

```````````````````````````````` example LinkRefs and ImageRefs: 1
[ ]
.
<p>[ ]</p>
.
Document[0, 4]
  Paragraph[0, 4]
    LinkRef[0, 3] referenceOpen:[0, 1, "["] reference:[2, 2] referenceClose:[2, 3, "]"]
      Text[1, 2] chars:[1, 2, " "]
````````````````````````````````


empty link ref

```````````````````````````````` example LinkRefs and ImageRefs: 2
[ ][]
.
<p>[ ][]</p>
.
Document[0, 6]
  Paragraph[0, 6]
    LinkRef[0, 5] referenceOpen:[0, 1, "["] reference:[2, 2] referenceClose:[2, 3, "]"] textOpen:[3, 4, "["] textClose:[4, 5, "]"]
      Text[1, 2] chars:[1, 2, " "]
````````````````````````````````


empty link ref

```````````````````````````````` example LinkRefs and ImageRefs: 3
[ ][ ]
.
<p>[ ][ ]</p>
.
Document[0, 7]
  Paragraph[0, 7]
    LinkRef[0, 6] textOpen:[0, 1, "["] text:[2, 2] textClose:[2, 3, "]"] referenceOpen:[3, 4, "["] reference:[5, 5] referenceClose:[5, 6, "]"]
      Text[1, 2] chars:[1, 2, " "]
````````````````````````````````


empty image ref

```````````````````````````````` example LinkRefs and ImageRefs: 4
![ ]
.
<p>![ ]</p>
.
Document[0, 5]
  Paragraph[0, 5]
    ImageRef[0, 4] referenceOpen:[0, 2, "!["] reference:[3, 3] referenceClose:[3, 4, "]"]
      Text[2, 3] chars:[2, 3, " "]
````````````````````````````````


empty link ref

```````````````````````````````` example LinkRefs and ImageRefs: 5
![ ][]
.
<p>![ ][]</p>
.
Document[0, 7]
  Paragraph[0, 7]
    ImageRef[0, 6] referenceOpen:[0, 2, "!["] reference:[3, 3] referenceClose:[3, 4, "]"] textOpen:[4, 5, "["] textClose:[5, 6, "]"]
      Text[2, 3] chars:[2, 3, " "]
````````````````````````````````


empty link ref

```````````````````````````````` example LinkRefs and ImageRefs: 6
![ ][ ]
.
<p>[ ][ ]</p>
.
Document[0, 8]
  Paragraph[0, 8]
    ImageRef[1, 7] textOpen:[1, 2, "["] text:[3, 3] textClose:[3, 4, "]"] referenceOpen:[4, 5, "["] reference:[6, 6] referenceClose:[6, 7, "]"]
      Text[2, 3] chars:[2, 3, " "]
````````````````````````````````


## Block Quotes

Extend block quote to next blank line

```````````````````````````````` example(Block Quotes: 1) options(block-quote-extend)
> 1. one
2. two
.
<blockquote>
  <ol>
    <li>one</li>
    <li>two</li>
  </ol>
</blockquote>
.
Document[0, 15]
  BlockQuote[0, 15] marker:[0, 1, ">"]
    OrderedList[2, 15] isTight delimiter:'.'
      OrderedListItem[2, 9] open:[2, 4, "1."] isTight
        Paragraph[5, 9]
          Text[5, 8] chars:[5, 8, "one"]
      OrderedListItem[9, 15] open:[9, 11, "2."] isTight
        Paragraph[12, 15]
          Text[12, 15] chars:[12, 15, "two"]
````````````````````````````````


without block quote to next blank line causes an interrupted list with a second list after the
quote.

```````````````````````````````` example Block Quotes: 2
> 1. one
2. two
.
<blockquote>
  <ol>
    <li>one</li>
  </ol>
</blockquote>
<ol start="2">
  <li>two</li>
</ol>
.
Document[0, 16]
  BlockQuote[0, 9] marker:[0, 1, ">"]
    OrderedList[2, 9] isTight delimiter:'.'
      OrderedListItem[2, 9] open:[2, 4, "1."] isTight
        Paragraph[5, 9]
          Text[5, 8] chars:[5, 8, "one"]
  OrderedList[9, 16] isTight start:2 delimiter:'.'
    OrderedListItem[9, 16] open:[9, 11, "2."] isTight
      Paragraph[12, 16]
        Text[12, 15] chars:[12, 15, "two"]
````````````````````````````````


Block quotes don't ignore interspersing blank lines

```````````````````````````````` example Block Quotes: 3
> Block Quote
>> Nested Quote
>>> Another Quote

>>>> Nested Quote

.
<blockquote>
  <p>Block Quote</p>
  <blockquote>
    <p>Nested Quote</p>
    <blockquote>
      <p>Another Quote</p>
    </blockquote>
  </blockquote>
</blockquote>
<blockquote>
  <blockquote>
    <blockquote>
      <blockquote>
        <p>Nested Quote</p>
      </blockquote>
    </blockquote>
  </blockquote>
</blockquote>
.
Document[0, 68]
  BlockQuote[0, 48] marker:[0, 1, ">"]
    Paragraph[2, 14]
      Text[2, 13] chars:[2, 13, "Block … Quote"]
    BlockQuote[15, 48] marker:[15, 16, ">"]
      Paragraph[17, 30]
        Text[17, 29] chars:[17, 29, "Neste … Quote"]
      BlockQuote[32, 48] marker:[32, 33, ">"]
        Paragraph[34, 48]
          Text[34, 47] chars:[34, 47, "Anoth … Quote"]
  BlockQuote[49, 67] marker:[49, 50, ">"]
    BlockQuote[50, 67] marker:[50, 51, ">"]
      BlockQuote[51, 67] marker:[51, 52, ">"]
        BlockQuote[52, 67] marker:[52, 53, ">"]
          Paragraph[54, 67]
            Text[54, 66] chars:[54, 66, "Neste … Quote"]
````````````````````````````````


Block quotes ignore interspersing blank lines

```````````````````````````````` example(Block Quotes: 4) options(block-ignore-blank)
> Block Quote
>> Nested Quote
>>> Another Quote

>>>> Nested Quote

.
<blockquote>
  <p>Block Quote</p>
  <blockquote>
    <p>Nested Quote</p>
    <blockquote>
      <p>Another Quote</p>
      <blockquote>
        <p>Nested Quote</p>
      </blockquote>
    </blockquote>
  </blockquote>
</blockquote>
.
Document[0, 68]
  BlockQuote[0, 67] marker:[0, 1, ">"]
    Paragraph[2, 14]
      Text[2, 13] chars:[2, 13, "Block … Quote"]
    BlockQuote[15, 67] marker:[15, 16, ">"]
      Paragraph[17, 30]
        Text[17, 29] chars:[17, 29, "Neste … Quote"]
      BlockQuote[32, 67] marker:[32, 33, ">"]
        Paragraph[34, 48] isTrailingBlankLine
          Text[34, 47] chars:[34, 47, "Anoth … Quote"]
        BlockQuote[52, 67] marker:[52, 53, ">"]
          Paragraph[54, 67] isTrailingBlankLine
            Text[54, 66] chars:[54, 66, "Neste … Quote"]
````````````````````````````````


Block quotes ignore interspersing blank lines but don't include any lines without prefix after
blank lines

```````````````````````````````` example(Block Quotes: 5) options(block-ignore-blank, block-quote-extend)
> Block Quote 
> this is still block quote 


this is not

.
<blockquote>
  <p>Block Quote
  this is still block quote</p>
</blockquote>
<p>this is not</p>
.
Document[0, 59]
  BlockQuote[0, 44] marker:[0, 1, ">"]
    Paragraph[2, 44] isTrailingBlankLine
      Text[2, 13] chars:[2, 13, "Block … Quote"]
      SoftLineBreak[14, 15]
      Text[17, 42] chars:[17, 42, "this  … quote"]
  Paragraph[46, 58] isTrailingBlankLine
    Text[46, 57] chars:[46, 57, "this  … s not"]
````````````````````````````````


## Hard Line Break Limit

Only attribute two last spaces of a hard line break space sequence

```````````````````````````````` example(Hard Line Break Limit: 1) options(hard-line-break-limit)
hard    
line breaks
.
<p>hard<br />
line breaks</p>
.
Document[0, 20]
  Paragraph[0, 20]
    Text[0, 4] chars:[0, 4, "hard"]
    HardLineBreak[6, 9]
    Text[9, 20] chars:[9, 20, "line  … reaks"]
````````````````````````````````


Only attribute two last spaces of a hard line break space sequence

```````````````````````````````` example(Hard Line Break Limit: 2) options(hard-line-break-limit)
hard    ⏎
line breaks
.
<p>hard<br />
line breaks</p>
.
Document[0, 21]
  Paragraph[0, 21]
    Text[0, 4] chars:[0, 4, "hard"]
    HardLineBreak[6, 10]
    Text[10, 21] chars:[10, 21, "line  … reaks"]
````````````````````````````````


default, all trailing spaces and eol

```````````````````````````````` example Hard Line Break Limit: 3
hard  ⏎
line breaks
.
<p>hard<br />
line breaks</p>
.
Document[0, 20]
  Paragraph[0, 20]
    Text[0, 4] chars:[0, 4, "hard"]
    HardLineBreak[4, 8]
    Text[8, 19] chars:[8, 19, "line  … reaks"]
````````````````````````````````


default, all trailing spaces and eol

```````````````````````````````` example Hard Line Break Limit: 4
hard    ⏎
line breaks
.
<p>hard<br />
line breaks</p>
.
Document[0, 22]
  Paragraph[0, 22]
    Text[0, 4] chars:[0, 4, "hard"]
    HardLineBreak[4, 10]
    Text[10, 21] chars:[10, 21, "line  … reaks"]
````````````````````````````````


EOL with \r should not include \r as part of text

```````````````````````````````` example Hard Line Break Limit: 5
soft⏎
line breaks
.
<p>soft
line breaks</p>
.
Document[0, 18]
  Paragraph[0, 18]
    Text[0, 4] chars:[0, 4, "soft"]
    SoftLineBreak[4, 6]
    Text[6, 17] chars:[6, 17, "line  … reaks"]
````````````````````````````````


EOL with \r should not include \r as part of text

```````````````````````````````` example Hard Line Break Limit: 6
soft ⏎
line breaks
.
<p>soft
line breaks</p>
.
Document[0, 19]
  Paragraph[0, 19]
    Text[0, 4] chars:[0, 4, "soft"]
    SoftLineBreak[5, 7]
    Text[7, 18] chars:[7, 18, "line  … reaks"]
````````````````````````````````


## Source Position Attribute

```````````````````````````````` example(Source Position Attribute: 1) options(src-pos)
<http://url> 
`code` 
_text_ 
![alt](/url) 
![img]
![img][]
[text](/url) 
[ref]
[ref][]
<name@mail.com>
**text**

---

# Heading

- item

<!-- -->

1. item

<!-- -->

    indented code
    

[img]: /img.png
[ref]: /url

    text

.
<p md-pos="0-113"><a md-pos="1-11" href="http://url">http://url</a>
<code md-pos="15-19">code</code>
<em md-pos="23-27">text</em>
<img src="/url" alt="alt" md-pos="30-42" />
<img src="/img.png" alt="img" md-pos="44-50" />
<img src="/img.png" alt="img" md-pos="51-59" />
<a href="/url" md-pos="60-72">text</a>
<a href="/url" md-pos="74-79">ref</a>
<a href="/url" md-pos="80-87">ref</a>
<a md-pos="89-102" href="mailto:name@mail.com">name@mail.com</a>
<strong md-pos="106-110">text</strong></p>
<hr md-pos="114-117" />
<h1 md-pos="121-128">Heading</h1>
<ul>
  <li md-pos="130-137">item</li>
</ul>
<!-- -->
<ol>
  <li md-pos="148-156">item</li>
</ol>
<!-- -->
<pre md-pos="171-185"><code md-pos="171-185">indented code
</code></pre>
<pre md-pos="224-229"><code md-pos="224-229">text
</code></pre>
.
Document[0, 230]
  Paragraph[0, 113] isTrailingBlankLine
    AutoLink[0, 12] textOpen:[0, 1, "<"] text:[1, 11, "http://url"] textClose:[11, 12, ">"]
    SoftLineBreak[13, 14]
    Code[14, 20] textOpen:[14, 15, "`"] text:[15, 19, "code"] textClose:[19, 20, "`"]
      Text[15, 19] chars:[15, 19, "code"]
    SoftLineBreak[21, 22]
    Emphasis[22, 28] textOpen:[22, 23, "_"] text:[23, 27, "text"] textClose:[27, 28, "_"]
      Text[23, 27] chars:[23, 27, "text"]
    SoftLineBreak[29, 30]
    Image[30, 42] textOpen:[30, 32, "!["] text:[32, 35, "alt"] textClose:[35, 36, "]"] linkOpen:[36, 37, "("] url:[37, 41, "/url"] pageRef:[37, 41, "/url"] linkClose:[41, 42, ")"]
      Text[32, 35] chars:[32, 35, "alt"]
    SoftLineBreak[43, 44]
    ImageRef[44, 50] referenceOpen:[44, 46, "!["] reference:[46, 49, "img"] referenceClose:[49, 50, "]"]
      Text[46, 49] chars:[46, 49, "img"]
    SoftLineBreak[50, 51]
    ImageRef[51, 59] referenceOpen:[51, 53, "!["] reference:[53, 56, "img"] referenceClose:[56, 57, "]"] textOpen:[57, 58, "["] textClose:[58, 59, "]"]
      Text[53, 56] chars:[53, 56, "img"]
    SoftLineBreak[59, 60]
    Link[60, 72] textOpen:[60, 61, "["] text:[61, 65, "text"] textClose:[65, 66, "]"] linkOpen:[66, 67, "("] url:[67, 71, "/url"] pageRef:[67, 71, "/url"] linkClose:[71, 72, ")"]
      Text[61, 65] chars:[61, 65, "text"]
    SoftLineBreak[73, 74]
    LinkRef[74, 79] referenceOpen:[74, 75, "["] reference:[75, 78, "ref"] referenceClose:[78, 79, "]"]
      Text[75, 78] chars:[75, 78, "ref"]
    SoftLineBreak[79, 80]
    LinkRef[80, 87] referenceOpen:[80, 81, "["] reference:[81, 84, "ref"] referenceClose:[84, 85, "]"] textOpen:[85, 86, "["] textClose:[86, 87, "]"]
      Text[81, 84] chars:[81, 84, "ref"]
    SoftLineBreak[87, 88]
    MailLink[88, 103] textOpen:[88, 89, "<"] text:[89, 102, "name@mail.com"] textClose:[102, 103, ">"]
    SoftLineBreak[103, 104]
    StrongEmphasis[104, 112] textOpen:[104, 106, "**"] text:[106, 110, "text"] textClose:[110, 112, "**"]
      Text[106, 110] chars:[106, 110, "text"]
  ThematicBreak[114, 117]
  Heading[119, 128] textOpen:[119, 120, "#"] text:[121, 128, "Heading"]
    Text[121, 128] chars:[121, 128, "Heading"]
  BulletList[130, 137] isTight
    BulletListItem[130, 137] open:[130, 131, "-"] isTight hadBlankLineAfter
      Paragraph[132, 137] isTrailingBlankLine
        Text[132, 136] chars:[132, 136, "item"]
  HtmlCommentBlock[138, 147]
  OrderedList[148, 156] isTight delimiter:'.'
    OrderedListItem[148, 156] open:[148, 150, "1."] isTight hadBlankLineAfter
      Paragraph[151, 156] isTrailingBlankLine
        Text[151, 155] chars:[151, 155, "item"]
  HtmlCommentBlock[157, 166]
  IndentedCodeBlock[171, 185]
  Reference[191, 206] refOpen:[191, 192, "["] ref:[192, 195, "img"] refClose:[195, 197, "]:"] url:[198, 206, "/img.png"]
  Reference[207, 218] refOpen:[207, 208, "["] ref:[208, 211, "ref"] refClose:[211, 213, "]:"] url:[214, 218, "/url"]
  IndentedCodeBlock[224, 229]
````````````````````````````````


fenced code

```````````````````````````````` example(Source Position Attribute: 2) options(src-pos)
```text
text
```

.
<pre md-pos="0-17"><code class="language-text" md-pos="8-13">text
</code></pre>
.
Document[0, 18]
  FencedCodeBlock[0, 16] open:[0, 3, "```"] info:[3, 7, "text"] content:[8, 13] lines[1] close:[13, 16, "```"]
    Text[8, 13] chars:[8, 13, "text\n"]
````````````````````````````````


single line paragraph lines

```````````````````````````````` example(Source Position Attribute: 3) options(src-pos, src-pos-lines)
text
.
<p md-pos="0-4"><span md-pos="0-4">text</span></p>
.
Document[0, 4]
  Paragraph[0, 4]
    Text[0, 4] chars:[0, 4, "text"]
````````````````````````````````


list item

```````````````````````````````` example(Source Position Attribute: 4) options(src-pos, src-pos-lines)
* a 
.
<ul>
  <li md-pos="0-4"><span md-pos="2-3">a</span></li>
</ul>
.
Document[0, 4]
  BulletList[0, 4] isTight
    BulletListItem[0, 4] open:[0, 1, "*"] isTight
      Paragraph[2, 4]
        Text[2, 3] chars:[2, 3, "a"]
````````````````````````````````


fenced code with trailing spaces and tabs on close

```````````````````````````````` example(Source Position Attribute: 5) options(src-pos)
```text
text
``` →

test
.
<pre md-pos="0-19"><code class="language-text" md-pos="8-13">text
</code></pre>
<p md-pos="20-24">test</p>
.
Document[0, 24]
  FencedCodeBlock[0, 16] open:[0, 3, "```"] info:[3, 7, "text"] content:[8, 13] lines[1] close:[13, 16, "```"]
    Text[8, 13] chars:[8, 13, "text\n"]
  Paragraph[20, 24]
    Text[20, 24] chars:[20, 24, "test"]
````````````````````````````````


no source wrap HTML

```````````````````````````````` example(Source Position Attribute: 6) options(src-pos, src-pos-lines)
<div>
<p>HTML Block</p>
</div>

This is <span>inline html</span>
.
<div>
<p>HTML Block</p>
</div>
<p md-pos="32-64"><span md-pos="32-64">This is <span>inline html</span></span></p>
.
Document[0, 64]
  HtmlBlock[0, 31]
  Paragraph[32, 64]
    Text[32, 40] chars:[32, 40, "This is "]
    HtmlInline[40, 46] chars:[40, 46, "<span>"]
    Text[46, 57] chars:[46, 57, "inlin …  html"]
    HtmlInline[57, 64] chars:[57, 64, "</span>"]
````````````````````````````````


source wrap HTML

```````````````````````````````` example(Source Position Attribute: 7) options(src-pos, src-pos-lines, src-wrap-html)
<div>
<p>HTML Block</p>
</div>

This is <span>inline html</span>
.
<div md-pos="0-30">
  <div>
<p>HTML Block</p>
</div>
</div>
<p md-pos="32-64"><span md-pos="32-64">This is <span>inline html</span></span></p>
.
Document[0, 64]
  HtmlBlock[0, 31]
  Paragraph[32, 64]
    Text[32, 40] chars:[32, 40, "This is "]
    HtmlInline[40, 46] chars:[40, 46, "<span>"]
    Text[46, 57] chars:[46, 57, "inlin …  html"]
    HtmlInline[57, 64] chars:[57, 64, "</span>"]
````````````````````````````````


source wrap HTML blocks

```````````````````````````````` example(Source Position Attribute: 8) options(src-pos, src-pos-lines, src-wrap-blocks)
<div>
<p>HTML Block</p>
</div>

This is <span>inline html</span>
.
<div md-pos="0-30">
  <div>
<p>HTML Block</p>
</div>
</div>
<p md-pos="32-64"><span md-pos="32-64">This is <span>inline html</span></span></p>
.
Document[0, 64]
  HtmlBlock[0, 31]
  Paragraph[32, 64]
    Text[32, 40] chars:[32, 40, "This is "]
    HtmlInline[40, 46] chars:[40, 46, "<span>"]
    Text[46, 57] chars:[46, 57, "inlin …  html"]
    HtmlInline[57, 64] chars:[57, 64, "</span>"]
````````````````````````````````


Wrap individual paragraph lines in source position marked spans

```````````````````````````````` example(Source Position Attribute: 9) options(src-pos, src-pos-lines)
paragraph test 
with multiple lazy lines
all should be src pos wrapped

- item
with multiple lazy lines
all should be src pos wrapped

<!-- -->

- item
with multiple lazy lines
all should be src pos wrapped

- item
<!-- -->

1. item
with multiple lazy lines
all should be src pos wrapped

1. item

<!-- -->

1. item
with multiple lazy lines
all should be src pos wrapped
1. item

<!-- -->

- [ ] item
with multiple lazy lines
all should be src pos wrapped
.
<p md-pos="0-71"><span md-pos="0-14">paragraph test</span>
<span md-pos="16-40">with multiple lazy lines</span>
<span md-pos="41-70">all should be src pos wrapped</span></p>
<ul>
  <li md-pos="72-134"><span md-pos="74-78">item</span>
  <span md-pos="79-103">with multiple lazy lines</span>
  <span md-pos="104-133">all should be src pos wrapped</span></li>
</ul>
<!-- -->
<ul>
  <li md-pos="145-207">
    <p md-pos="147-207"><span md-pos="147-151">item</span>
    <span md-pos="152-176">with multiple lazy lines</span>
    <span md-pos="177-206">all should be src pos wrapped</span></p>
  </li>
  <li md-pos="208-215">
    <p md-pos="210-215"><span md-pos="210-214">item</span></p>
  </li>
</ul>
<!-- -->
<ol>
  <li md-pos="225-288">
    <p md-pos="228-288"><span md-pos="228-232">item</span>
    <span md-pos="233-257">with multiple lazy lines</span>
    <span md-pos="258-287">all should be src pos wrapped</span></p>
  </li>
  <li md-pos="289-297">
    <p md-pos="292-297"><span md-pos="292-296">item</span></p>
  </li>
</ol>
<!-- -->
<ol>
  <li md-pos="308-371"><span md-pos="311-315">item</span>
  <span md-pos="316-340">with multiple lazy lines</span>
  <span md-pos="341-370">all should be src pos wrapped</span></li>
  <li md-pos="371-379"><span md-pos="374-378">item</span></li>
</ol>
<!-- -->
<ul>
  <li md-pos="390-455"><span md-pos="392-400">[ ] item</span>
  <span md-pos="401-425">with multiple lazy lines</span>
  <span md-pos="426-455">all should be src pos wrapped</span></li>
</ul>
.
Document[0, 455]
  Paragraph[0, 71] isTrailingBlankLine
    Text[0, 14] chars:[0, 14, "parag …  test"]
    SoftLineBreak[15, 16]
    Text[16, 40] chars:[16, 40, "with  … lines"]
    SoftLineBreak[40, 41]
    Text[41, 70] chars:[41, 70, "all s … apped"]
  BulletList[72, 134] isTight
    BulletListItem[72, 134] open:[72, 73, "-"] isTight hadBlankLineAfter
      Paragraph[74, 134] isTrailingBlankLine
        Text[74, 78] chars:[74, 78, "item"]
        SoftLineBreak[78, 79]
        Text[79, 103] chars:[79, 103, "with  … lines"]
        SoftLineBreak[103, 104]
        Text[104, 133] chars:[104, 133, "all s … apped"]
  HtmlCommentBlock[135, 144]
  BulletList[145, 215] isLoose
    BulletListItem[145, 207] open:[145, 146, "-"] isLoose hadBlankLineAfter
      Paragraph[147, 207] isTrailingBlankLine
        Text[147, 151] chars:[147, 151, "item"]
        SoftLineBreak[151, 152]
        Text[152, 176] chars:[152, 176, "with  … lines"]
        SoftLineBreak[176, 177]
        Text[177, 206] chars:[177, 206, "all s … apped"]
    BulletListItem[208, 215] open:[208, 209, "-"] isLoose
      Paragraph[210, 215]
        Text[210, 214] chars:[210, 214, "item"]
  HtmlCommentBlock[215, 224]
  OrderedList[225, 297] isLoose delimiter:'.'
    OrderedListItem[225, 288] open:[225, 227, "1."] isLoose hadBlankLineAfter
      Paragraph[228, 288] isTrailingBlankLine
        Text[228, 232] chars:[228, 232, "item"]
        SoftLineBreak[232, 233]
        Text[233, 257] chars:[233, 257, "with  … lines"]
        SoftLineBreak[257, 258]
        Text[258, 287] chars:[258, 287, "all s … apped"]
    OrderedListItem[289, 297] open:[289, 291, "1."] isLoose hadBlankLineAfter
      Paragraph[292, 297] isTrailingBlankLine
        Text[292, 296] chars:[292, 296, "item"]
  HtmlCommentBlock[298, 307]
  OrderedList[308, 379] isTight delimiter:'.'
    OrderedListItem[308, 371] open:[308, 310, "1."] isTight
      Paragraph[311, 371]
        Text[311, 315] chars:[311, 315, "item"]
        SoftLineBreak[315, 316]
        Text[316, 340] chars:[316, 340, "with  … lines"]
        SoftLineBreak[340, 341]
        Text[341, 370] chars:[341, 370, "all s … apped"]
    OrderedListItem[371, 379] open:[371, 373, "1."] isTight hadBlankLineAfter
      Paragraph[374, 379] isTrailingBlankLine
        Text[374, 378] chars:[374, 378, "item"]
  HtmlCommentBlock[380, 389]
  BulletList[390, 455] isTight
    BulletListItem[390, 455] open:[390, 391, "-"] isTight
      Paragraph[392, 455]
        LinkRef[392, 395] referenceOpen:[392, 393, "["] reference:[394, 394] referenceClose:[394, 395, "]"]
          Text[393, 394] chars:[393, 394, " "]
        Text[395, 400] chars:[395, 400, " item"]
        SoftLineBreak[400, 401]
        Text[401, 425] chars:[401, 425, "with  … lines"]
        SoftLineBreak[425, 426]
        Text[426, 455] chars:[426, 455, "all s … apped"]
````````````````````````````````


Wrap individual paragraph lines in source position marked spans

```````````````````````````````` example(Source Position Attribute: 10) options(src-pos, src-pos-lines)
paragraph test 
with multiple lazy lines
all should be src pos wrapped
.
<p md-pos="0-70"><span md-pos="0-14">paragraph test</span>
<span md-pos="16-40">with multiple lazy lines</span>
<span md-pos="41-70">all should be src pos wrapped</span></p>
.
Document[0, 70]
  Paragraph[0, 70]
    Text[0, 14] chars:[0, 14, "parag …  test"]
    SoftLineBreak[15, 16]
    Text[16, 40] chars:[16, 40, "with  … lines"]
    SoftLineBreak[40, 41]
    Text[41, 70] chars:[41, 70, "all s … apped"]
````````````````````````````````


Wrap individual paragraph lines in source position marked spans with spanning inlines

```````````````````````````````` example(Source Position Attribute: 11) options(src-pos, src-pos-lines)
paragraph `test 
 with` multiple lazy **lines
paragraph `test
with` multiple lazy **lines
  all** should be src pos *wrapped
lines*
.
<p md-pos="0-131"><span md-pos="0-15">paragraph <code>test </code></span><span md-pos="18-45"><code>with</code> multiple lazy **lines</span>
<span md-pos="46-61">paragraph <code>test </code></span><span md-pos="62-89"><code>with</code> multiple lazy <strong>lines</strong></span>
<span md-pos="92-124"><strong>all</strong> should be src pos <em>wrapped</em></span>
<span md-pos="125-130"><em>lines</em></span></p>
.
Document[0, 131]
  Paragraph[0, 131]
    Text[0, 10] chars:[0, 10, "paragraph "]
    Code[10, 23] textOpen:[10, 11, "`"] text:[11, 22, "test \nwith"] textClose:[22, 23, "`"]
      Text[11, 16] chars:[11, 16, "test "]
      SoftLineBreak[16, 17]
      Text[18, 22] chars:[18, 22, "with"]
    Text[23, 45] chars:[23, 45, " mult … lines"]
    SoftLineBreak[45, 46]
    Text[46, 56] chars:[46, 56, "paragraph "]
    Code[56, 67] textOpen:[56, 57, "`"] text:[57, 66, "test\nwith"] textClose:[66, 67, "`"]
      Text[57, 61] chars:[57, 61, "test"]
      SoftLineBreak[61, 62]
      Text[62, 66] chars:[62, 66, "with"]
    Text[67, 82] chars:[67, 82, " mult … lazy "]
    StrongEmphasis[82, 97] textOpen:[82, 84, "**"] text:[84, 95, "lines\nall"] textClose:[95, 97, "**"]
      Text[84, 89] chars:[84, 89, "lines"]
      SoftLineBreak[89, 90]
      Text[92, 95] chars:[92, 95, "all"]
    Text[97, 116] chars:[97, 116, " shou …  pos "]
    Emphasis[116, 131] textOpen:[116, 117, "*"] text:[117, 130, "wrapped\nlines"] textClose:[130, 131, "*"]
      Text[117, 124] chars:[117, 124, "wrapped"]
      SoftLineBreak[124, 125]
      Text[125, 130] chars:[125, 130, "lines"]
````````````````````````````````


```````````````````````````````` example(Source Position Attribute: 12) options(src-pos, src-pos-lines-splice)
paragraph `test 
 with` multiple lazy **lines
paragraph `test
with` multiple lazy **lines
  all** should be src pos *wrapped
lines*
.
<p md-pos="0-131"><span md-pos="0-15">paragraph <code>test </code></span><span md-pos="18-45"><code class="line-spliced">with</code> multiple lazy **lines</span>
<span md-pos="46-61">paragraph <code>test </code></span><span md-pos="62-89"><code class="line-spliced">with</code> multiple lazy <strong>lines</strong></span>
<span md-pos="92-124"><strong>all</strong> should be src pos <em>wrapped</em></span>
<span md-pos="125-130"><em>lines</em></span></p>
.
Document[0, 131]
  Paragraph[0, 131]
    Text[0, 10] chars:[0, 10, "paragraph "]
    Code[10, 23] textOpen:[10, 11, "`"] text:[11, 22, "test \nwith"] textClose:[22, 23, "`"]
      Text[11, 16] chars:[11, 16, "test "]
      SoftLineBreak[16, 17]
      Text[18, 22] chars:[18, 22, "with"]
    Text[23, 45] chars:[23, 45, " mult … lines"]
    SoftLineBreak[45, 46]
    Text[46, 56] chars:[46, 56, "paragraph "]
    Code[56, 67] textOpen:[56, 57, "`"] text:[57, 66, "test\nwith"] textClose:[66, 67, "`"]
      Text[57, 61] chars:[57, 61, "test"]
      SoftLineBreak[61, 62]
      Text[62, 66] chars:[62, 66, "with"]
    Text[67, 82] chars:[67, 82, " mult … lazy "]
    StrongEmphasis[82, 97] textOpen:[82, 84, "**"] text:[84, 95, "lines\nall"] textClose:[95, 97, "**"]
      Text[84, 89] chars:[84, 89, "lines"]
      SoftLineBreak[89, 90]
      Text[92, 95] chars:[92, 95, "all"]
    Text[97, 116] chars:[97, 116, " shou …  pos "]
    Emphasis[116, 131] textOpen:[116, 117, "*"] text:[117, 130, "wrapped\nlines"] textClose:[130, 131, "*"]
      Text[117, 124] chars:[117, 124, "wrapped"]
      SoftLineBreak[124, 125]
      Text[125, 130] chars:[125, 130, "lines"]
````````````````````````````````


Wrap individual paragraph lines in source position marked spans tight list items

```````````````````````````````` example(Source Position Attribute: 13) options(src-pos, src-pos-lines)
- item
with multiple lazy lines
all should be src pos wrapped

<!-- -->

1. item
with multiple lazy lines
all should be src pos wrapped

.
<ul>
  <li md-pos="0-62"><span md-pos="2-6">item</span>
  <span md-pos="7-31">with multiple lazy lines</span>
  <span md-pos="32-61">all should be src pos wrapped</span></li>
</ul>
<!-- -->
<ol>
  <li md-pos="73-136"><span md-pos="76-80">item</span>
  <span md-pos="81-105">with multiple lazy lines</span>
  <span md-pos="106-135">all should be src pos wrapped</span></li>
</ol>
.
Document[0, 137]
  BulletList[0, 62] isTight
    BulletListItem[0, 62] open:[0, 1, "-"] isTight hadBlankLineAfter
      Paragraph[2, 62] isTrailingBlankLine
        Text[2, 6] chars:[2, 6, "item"]
        SoftLineBreak[6, 7]
        Text[7, 31] chars:[7, 31, "with  … lines"]
        SoftLineBreak[31, 32]
        Text[32, 61] chars:[32, 61, "all s … apped"]
  HtmlCommentBlock[63, 72]
  OrderedList[73, 136] isTight delimiter:'.'
    OrderedListItem[73, 136] open:[73, 75, "1."] isTight hadBlankLineAfter
      Paragraph[76, 136] isTrailingBlankLine
        Text[76, 80] chars:[76, 80, "item"]
        SoftLineBreak[80, 81]
        Text[81, 105] chars:[81, 105, "with  … lines"]
        SoftLineBreak[105, 106]
        Text[106, 135] chars:[106, 135, "all s … apped"]
````````````````````````````````


Wrap individual paragraph lines in source position marked spans loose list items

```````````````````````````````` example(Source Position Attribute: 14) options(src-pos, src-pos-lines)
- item

- item
with multiple lazy lines
all should be src pos wrapped

<!-- -->

1. item

1. item
with multiple lazy lines
all should be src pos wrapped

.
<ul>
  <li md-pos="0-7">
    <p md-pos="2-7"><span md-pos="2-6">item</span></p>
  </li>
  <li md-pos="8-70">
    <p md-pos="10-70"><span md-pos="10-14">item</span>
    <span md-pos="15-39">with multiple lazy lines</span>
    <span md-pos="40-69">all should be src pos wrapped</span></p>
  </li>
</ul>
<!-- -->
<ol>
  <li md-pos="81-89">
    <p md-pos="84-89"><span md-pos="84-88">item</span></p>
  </li>
  <li md-pos="90-153">
    <p md-pos="93-153"><span md-pos="93-97">item</span>
    <span md-pos="98-122">with multiple lazy lines</span>
    <span md-pos="123-152">all should be src pos wrapped</span></p>
  </li>
</ol>
.
Document[0, 154]
  BulletList[0, 70] isLoose
    BulletListItem[0, 7] open:[0, 1, "-"] isLoose hadBlankLineAfter
      Paragraph[2, 7] isTrailingBlankLine
        Text[2, 6] chars:[2, 6, "item"]
    BulletListItem[8, 70] open:[8, 9, "-"] isLoose hadBlankLineAfter
      Paragraph[10, 70] isTrailingBlankLine
        Text[10, 14] chars:[10, 14, "item"]
        SoftLineBreak[14, 15]
        Text[15, 39] chars:[15, 39, "with  … lines"]
        SoftLineBreak[39, 40]
        Text[40, 69] chars:[40, 69, "all s … apped"]
  HtmlCommentBlock[71, 80]
  OrderedList[81, 153] isLoose delimiter:'.'
    OrderedListItem[81, 89] open:[81, 83, "1."] isLoose hadBlankLineAfter
      Paragraph[84, 89] isTrailingBlankLine
        Text[84, 88] chars:[84, 88, "item"]
    OrderedListItem[90, 153] open:[90, 92, "1."] isLoose hadBlankLineAfter
      Paragraph[93, 153] isTrailingBlankLine
        Text[93, 97] chars:[93, 97, "item"]
        SoftLineBreak[97, 98]
        Text[98, 122] chars:[98, 122, "with  … lines"]
        SoftLineBreak[122, 123]
        Text[123, 152] chars:[123, 152, "all s … apped"]
````````````````````````````````


## Jekyll Macros in URLs

Allow funny URLs

```````````````````````````````` example(Jekyll Macros in URLs: 1) options(jekyll-macros-in-urls)
[ref]({{ macro }}/someDir/someFile.someExt)
.
<p><a href="%7B%7B%20macro%20%7D%7D/someDir/someFile.someExt">ref</a></p>
.
Document[0, 43]
  Paragraph[0, 43]
    Link[0, 43] textOpen:[0, 1, "["] text:[1, 4, "ref"] textClose:[4, 5, "]"] linkOpen:[5, 6, "("] url:[6, 42, "{{ macro }}/someDir/someFile.someExt"] pageRef:[6, 42, "{{ macro }}/someDir/someFile.someExt"] linkClose:[42, 43, ")"]
      Text[1, 4] chars:[1, 4, "ref"]
````````````````````````````````


Allow funny URLs

```````````````````````````````` example(Jekyll Macros in URLs: 2) options(jekyll-macros-in-urls)
[ref]({{ macro }}/someDir/someFile.{{someExt}})
.
<p><a href="%7B%7B%20macro%20%7D%7D/someDir/someFile.%7B%7BsomeExt%7D%7D">ref</a></p>
.
Document[0, 47]
  Paragraph[0, 47]
    Link[0, 47] textOpen:[0, 1, "["] text:[1, 4, "ref"] textClose:[4, 5, "]"] linkOpen:[5, 6, "("] url:[6, 46, "{{ macro }}/someDir/someFile.{{someExt}}"] pageRef:[6, 46, "{{ macro }}/someDir/someFile.{{someExt}}"] linkClose:[46, 47, ")"]
      Text[1, 4] chars:[1, 4, "ref"]
````````````````````````````````


Allow funny URLs

```````````````````````````````` example(Jekyll Macros in URLs: 3) options(jekyll-macros-in-urls)
[ref]({{ macro|()|$/| }}someFile.ext)
.
<p><a href="%7B%7B%20macro%7C()%7C$/%7C%20%7D%7DsomeFile.ext">ref</a></p>
.
Document[0, 37]
  Paragraph[0, 37]
    Link[0, 37] textOpen:[0, 1, "["] text:[1, 4, "ref"] textClose:[4, 5, "]"] linkOpen:[5, 6, "("] url:[6, 36, "{{ macro|()|$/| }}someFile.ext"] pageRef:[6, 36, "{{ macro|()|$/| }}someFile.ext"] linkClose:[36, 37, ")"]
      Text[1, 4] chars:[1, 4, "ref"]
````````````````````````````````


## Lists - Markdown Navigator

Embedded headings Markdown Navigator options

```````````````````````````````` example(Lists - Markdown Navigator: 1) options(list-markdown-navigator)
1. Some Lists
    
    # Test

.
<ol>
  <li>Some Lists
  <h1>Test</h1>
  </li>
</ol>
.
Document[0, 31]
  OrderedList[0, 29] isTight delimiter:'.'
    OrderedListItem[0, 29] open:[0, 2, "1."] isTight hadBlankLineAfter
      Paragraph[3, 14] isTrailingBlankLine
        Text[3, 13] chars:[3, 13, "Some Lists"]
      Heading[23, 29] textOpen:[23, 24, "#"] text:[25, 29, "Test"]
        Text[25, 29] chars:[25, 29, "Test"]
````````````````````````````````


Allow all breaks for list items

```````````````````````````````` example(Lists - Markdown Navigator: 2) options(list-markdown-navigator)
paragraph
* list item

paragraph
* 

paragraph
*

paragraph
1. numbered item

paragraph
1. 

paragraph
1.

paragraph
2. numbered item

paragraph
2. 

paragraph
2.
.
<p>paragraph</p>
<ul>
  <li>list item</li>
</ul>
<p>paragraph</p>
<ul>
  <li></li>
</ul>
<p>paragraph</p>
<ul>
  <li></li>
</ul>
<p>paragraph</p>
<ol>
  <li>numbered item</li>
</ol>
<p>paragraph</p>
<ol>
  <li></li>
</ol>
<p>paragraph</p>
<ol>
  <li></li>
</ol>
<p>paragraph</p>
<ol>
  <li>numbered item</li>
</ol>
<p>paragraph</p>
<ol>
  <li></li>
</ol>
<p>paragraph</p>
<ol>
  <li></li>
</ol>
.
Document[0, 162]
  Paragraph[0, 10]
    Text[0, 9] chars:[0, 9, "paragraph"]
  BulletList[10, 22] isTight
    BulletListItem[10, 22] open:[10, 11, "*"] isTight hadBlankLineAfter
      Paragraph[12, 22] isTrailingBlankLine
        Text[12, 21] chars:[12, 21, "list item"]
  Paragraph[23, 33]
    Text[23, 32] chars:[23, 32, "paragraph"]
  BulletList[33, 34] isTight
    BulletListItem[33, 34] open:[33, 34, "*"] isTight hadBlankLineAfter
  Paragraph[37, 47]
    Text[37, 46] chars:[37, 46, "paragraph"]
  BulletList[47, 48] isTight
    BulletListItem[47, 48] open:[47, 48, "*"] isTight hadBlankLineAfter
  Paragraph[50, 60]
    Text[50, 59] chars:[50, 59, "paragraph"]
  OrderedList[60, 77] isTight delimiter:'.'
    OrderedListItem[60, 77] open:[60, 62, "1."] isTight hadBlankLineAfter
      Paragraph[63, 77] isTrailingBlankLine
        Text[63, 76] chars:[63, 76, "numbe …  item"]
  Paragraph[78, 88]
    Text[78, 87] chars:[78, 87, "paragraph"]
  OrderedList[88, 90] isTight delimiter:'.'
    OrderedListItem[88, 90] open:[88, 90, "1."] isTight hadBlankLineAfter
  Paragraph[93, 103]
    Text[93, 102] chars:[93, 102, "paragraph"]
  OrderedList[103, 105] isTight delimiter:'.'
    OrderedListItem[103, 105] open:[103, 105, "1."] isTight hadBlankLineAfter
  Paragraph[107, 117]
    Text[107, 116] chars:[107, 116, "paragraph"]
  OrderedList[117, 134] isTight start:2 delimiter:'.'
    OrderedListItem[117, 134] open:[117, 119, "2."] isTight hadBlankLineAfter
      Paragraph[120, 134] isTrailingBlankLine
        Text[120, 133] chars:[120, 133, "numbe …  item"]
  Paragraph[135, 145]
    Text[135, 144] chars:[135, 144, "paragraph"]
  OrderedList[145, 147] isTight start:2 delimiter:'.'
    OrderedListItem[145, 147] open:[145, 147, "2."] isTight hadBlankLineAfter
  Paragraph[150, 160]
    Text[150, 159] chars:[150, 159, "paragraph"]
  OrderedList[160, 162] isTight start:2 delimiter:'.'
    OrderedListItem[160, 162] open:[160, 162, "2."] isTight
````````````````````````````````


```````````````````````````````` example Lists - Markdown Navigator: 3
- ****adsfasddasfdsa****
.
<ul>
  <li><strong><strong>adsfasddasfdsa</strong></strong></li>
</ul>
.
Document[0, 25]
  BulletList[0, 25] isTight
    BulletListItem[0, 25] open:[0, 1, "-"] isTight
      Paragraph[2, 25]
        StrongEmphasis[2, 24] textOpen:[2, 4, "**"] text:[4, 22, "**adsfasddasfdsa**"] textClose:[22, 24, "**"]
          StrongEmphasis[4, 22] textOpen:[4, 6, "**"] text:[6, 20, "adsfasddasfdsa"] textClose:[20, 22, "**"]
            Text[6, 20] chars:[6, 20, "adsfa … sfdsa"]
````````````````````````````````


### Headings

trailing marker does not require spaces before it

```````````````````````````````` example(Headings: 1) options(hdr-no-atx-space)
# Heading####
.
<h1>Heading</h1>
.
Document[0, 13]
  Heading[0, 13] textOpen:[0, 1, "#"] text:[2, 9, "Heading"] textClose:[9, 13, "####"]
    Text[2, 9] chars:[2, 9, "Heading"]
````````````````````````````````


### Blank Lines in AST

```````````````````````````````` example(Blank Lines in AST: 1) options(keep-blank-lines)
## Heading


----

paragraph 1

paragraph 2

* item 1
  * item 1.1 
  
* item 2  

  * item 1.1 
  
> block quote 1 
> 
> block quote 2
>

.
<h2>Heading</h2>
<hr />
<p>paragraph 1</p>
<p>paragraph 2</p>
<ul>
  <li>
    <p>item 1</p>
    <ul>
      <li>item 1.1</li>
    </ul>
  </li>
  <li>
    <p>item 2</p>
    <ul>
      <li>item 1.1</li>
    </ul>
  </li>
</ul>
<blockquote>
  <p>block quote 1</p>
  <p>block quote 2</p>
</blockquote>
.
Document[0, 139]
  Heading[0, 10] textOpen:[0, 2, "##"] text:[3, 10, "Heading"]
    Text[3, 10] chars:[3, 10, "Heading"]
  BlankLine[11, 12]
  BlankLine[12, 13]
  ThematicBreak[13, 17]
  BlankLine[18, 19]
  Paragraph[19, 31] isTrailingBlankLine
    Text[19, 30] chars:[19, 30, "parag … aph 1"]
  BlankLine[31, 32]
  Paragraph[32, 44] isTrailingBlankLine
    Text[32, 43] chars:[32, 43, "parag … aph 2"]
  BlankLine[44, 45]
  BulletList[45, 97] isLoose
    BulletListItem[45, 68] open:[45, 46, "*"] isLoose
      Paragraph[47, 54]
        Text[47, 53] chars:[47, 53, "item 1"]
      BulletList[56, 68] isTight
        BulletListItem[56, 68] open:[56, 57, "*"] isTight hadBlankLineAfter
          Paragraph[58, 68] isTrailingBlankLine
            Text[58, 66] chars:[58, 66, "item 1.1"]
    BlankLine[68, 71]
    BulletListItem[71, 97] open:[71, 72, "*"] isLoose hadBlankLineAfter
      Paragraph[73, 82] isTrailingBlankLine
        Text[73, 79] chars:[73, 79, "item 2"]
      BlankLine[82, 83]
      BulletList[85, 97] isTight
        BulletListItem[85, 97] open:[85, 86, "*"] isTight hadBlankLineAfter
          Paragraph[87, 97] isTrailingBlankLine
            Text[87, 95] chars:[87, 95, "item 1.1"]
  BlankLine[97, 100]
  BlockQuote[100, 136] marker:[100, 101, ">"]
    Paragraph[102, 117] isTrailingBlankLine
      Text[102, 115] chars:[102, 115, "block … ote 1"]
    Paragraph[122, 136] isTrailingBlankLine
      Text[122, 135] chars:[122, 135, "block … ote 2"]
  BlankLine[136, 138]
  BlankLine[138, 139]
````````````````````````````````


```````````````````````````````` example(Blank Lines in AST: 2) options(keep-blank-lines)
* item 1
  * item 1.1 
  
  
* item 2
  * item 2.1 
  
  
paragraph  
.
<ul>
  <li>
    <p>item 1</p>
    <ul>
      <li>
        <p>item 1.1</p>
      </li>
    </ul>
  </li>
  <li>
    <p>item 2</p>
    <ul>
      <li>
        <p>item 2.1</p>
      </li>
    </ul>
  </li>
</ul>
<p>paragraph</p>
.
Document[0, 69]
  BulletList[0, 52] isLoose
    BulletListItem[0, 23] open:[0, 1, "*"] isLoose
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
      BulletList[11, 23] isLoose
        BulletListItem[11, 23] open:[11, 12, "*"] isLoose hadBlankLineAfter
          Paragraph[13, 23] isTrailingBlankLine
            Text[13, 21] chars:[13, 21, "item 1.1"]
    BlankLine[23, 26]
    BlankLine[26, 29]
    BulletListItem[29, 52] open:[29, 30, "*"] isLoose
      Paragraph[31, 38]
        Text[31, 37] chars:[31, 37, "item 2"]
      BulletList[40, 52] isLoose
        BulletListItem[40, 52] open:[40, 41, "*"] isLoose hadBlankLineAfter
          Paragraph[42, 52] isTrailingBlankLine
            Text[42, 50] chars:[42, 50, "item 2.1"]
  BlankLine[52, 55]
  BlankLine[55, 58]
  Paragraph[58, 69]
    Text[58, 67] chars:[58, 67, "paragraph"]
````````````````````````````````


## Custom Style HTML

strong emphasis

```````````````````````````````` example(Custom Style HTML: 1) options(style-strong-emphasis)
**bold**

*italic*

`code`

.
<p><span class="text-bold">bold</span></p>
<p><em>italic</em></p>
<p><code>code</code></p>
.
Document[0, 28]
  Paragraph[0, 9] isTrailingBlankLine
    StrongEmphasis[0, 8] textOpen:[0, 2, "**"] text:[2, 6, "bold"] textClose:[6, 8, "**"]
      Text[2, 6] chars:[2, 6, "bold"]
  Paragraph[10, 19] isTrailingBlankLine
    Emphasis[10, 18] textOpen:[10, 11, "*"] text:[11, 17, "italic"] textClose:[17, 18, "*"]
      Text[11, 17] chars:[11, 17, "italic"]
  Paragraph[20, 27] isTrailingBlankLine
    Code[20, 26] textOpen:[20, 21, "`"] text:[21, 25, "code"] textClose:[25, 26, "`"]
      Text[21, 25] chars:[21, 25, "code"]
````````````````````````````````


emphasis

```````````````````````````````` example(Custom Style HTML: 2) options(style-emphasis)
**bold**

*italic*

`code`

.
<p><strong>bold</strong></p>
<p><span class="text-italic">italic</span></p>
<p><code>code</code></p>
.
Document[0, 28]
  Paragraph[0, 9] isTrailingBlankLine
    StrongEmphasis[0, 8] textOpen:[0, 2, "**"] text:[2, 6, "bold"] textClose:[6, 8, "**"]
      Text[2, 6] chars:[2, 6, "bold"]
  Paragraph[10, 19] isTrailingBlankLine
    Emphasis[10, 18] textOpen:[10, 11, "*"] text:[11, 17, "italic"] textClose:[17, 18, "*"]
      Text[11, 17] chars:[11, 17, "italic"]
  Paragraph[20, 27] isTrailingBlankLine
    Code[20, 26] textOpen:[20, 21, "`"] text:[21, 25, "code"] textClose:[25, 26, "`"]
      Text[21, 25] chars:[21, 25, "code"]
````````````````````````````````


code

```````````````````````````````` example(Custom Style HTML: 3) options(style-code)
**bold**

*italic*

`code`

.
<p><strong>bold</strong></p>
<p><em>italic</em></p>
<p><span class="text-code">code</span></p>
.
Document[0, 28]
  Paragraph[0, 9] isTrailingBlankLine
    StrongEmphasis[0, 8] textOpen:[0, 2, "**"] text:[2, 6, "bold"] textClose:[6, 8, "**"]
      Text[2, 6] chars:[2, 6, "bold"]
  Paragraph[10, 19] isTrailingBlankLine
    Emphasis[10, 18] textOpen:[10, 11, "*"] text:[11, 17, "italic"] textClose:[17, 18, "*"]
      Text[11, 17] chars:[11, 17, "italic"]
  Paragraph[20, 27] isTrailingBlankLine
    Code[20, 26] textOpen:[20, 21, "`"] text:[21, 25, "code"] textClose:[25, 26, "`"]
      Text[21, 25] chars:[21, 25, "code"]
````````````````````````````````


## Suppress HTML Block EOL

```````````````````````````````` example(Suppress HTML Block EOL: 1) options(suppress-format-eol)

---

# Heading

> block quote

.
<hr /><h1>Heading</h1><blockquote>
<p>block quote</p>
</blockquote>
.
Document[0, 32]
  ThematicBreak[1, 4]
  Heading[6, 15] textOpen:[6, 7, "#"] text:[8, 15, "Heading"]
    Text[8, 15] chars:[8, 15, "Heading"]
  BlockQuote[17, 31] marker:[17, 18, ">"]
    Paragraph[19, 31]
      Text[19, 30] chars:[19, 30, "block … quote"]
````````````````````````````````


## Unescape Entities

do not unescape entities

```````````````````````````````` example(Unescape Entities: 1) options(no-unescape-entities)
stuff &Aacute; stuff
.
<p>stuff &Aacute; stuff</p>
.
Document[0, 20]
  Paragraph[0, 20]
    Text[0, 6] chars:[0, 6, "stuff "]
    HtmlEntity[6, 14] "&Aacute;"
    Text[14, 20] chars:[14, 20, " stuff"]
````````````````````````````````


## Deep HTML Parsing

```````````````````````````````` example(Deep HTML Parsing: 1) options(deep-html-parsing)
<div>

  This is markdown text

</div>

This is markdown paragraph
.
<div>
<p>This is markdown text</p>
</div>
<p>This is markdown paragraph</p>
.
Document[0, 66]
  HtmlBlock[0, 6]
  Paragraph[9, 31] isTrailingBlankLine
    Text[9, 30] chars:[9, 30, "This  …  text"]
  HtmlBlock[32, 39]
  Paragraph[40, 66]
    Text[40, 66] chars:[40, 66, "This  … graph"]
````````````````````````````````


```````````````````````````````` example(Deep HTML Parsing: 2) options(deep-html-parsing)
<div><strong>

  This is markdown text

</div>

This is markdown paragraph
.
<div><strong>
<p>This is markdown text</p>
</div>
<p>This is markdown paragraph</p>
.
Document[0, 74]
  HtmlBlock[0, 14]
  Paragraph[17, 39] isTrailingBlankLine
    Text[17, 38] chars:[17, 38, "This  …  text"]
  HtmlBlock[40, 47]
  Paragraph[48, 74]
    Text[48, 74] chars:[48, 74, "This  … graph"]
````````````````````````````````


```````````````````````````````` example(Deep HTML Parsing: 3) options(deep-html-parsing)
<div><!--

  This is comment

  -->
  
  This is markdown text

</div>

This is markdown paragraph
.
<div><!--

  This is comment

  -->
<p>This is markdown text</p>
</div>
<p>This is markdown paragraph</p>
.
Document[0, 98]
  HtmlCommentBlock[0, 36]
  Paragraph[41, 63] isTrailingBlankLine
    Text[41, 62] chars:[41, 62, "This  …  text"]
  HtmlBlock[64, 71]
  Paragraph[72, 98]
    Text[72, 98] chars:[72, 98, "This  … graph"]
````````````````````````````````


```````````````````````````````` example(Deep HTML Parsing: 4) options(deep-html-parsing)
<div>
  <!--

  This is comment

  -->
  
  This is markdown text

</div>

This is markdown paragraph
.
<div>
  <!--

  This is comment

  -->
<p>This is markdown text</p>
</div>
<p>This is markdown paragraph</p>
.
Document[0, 101]
  HtmlBlock[0, 39]
  Paragraph[44, 66] isTrailingBlankLine
    Text[44, 65] chars:[44, 65, "This  …  text"]
  HtmlBlock[67, 74]
  Paragraph[75, 101]
    Text[75, 101] chars:[75, 101, "This  … graph"]
````````````````````````````````


```````````````````````````````` example(Deep HTML Parsing: 5) options(deep-html-parsing)
<hr>
# Heading
.
<hr>
# Heading
.
Document[0, 14]
  HtmlBlock[0, 14]
````````````````````````````````


```````````````````````````````` example(Deep HTML Parsing: 6) options(deep-html-parsing)
<div attr
    attr1="test"
>

    html text
    
</div>    

.
<div attr
    attr1="test"
>
<pre><code>html text
</code></pre>
</div>    
.
Document[0, 61]
  HtmlBlock[0, 29]
  IndentedCodeBlock[34, 44]
  HtmlBlock[49, 60]
````````````````````````````````


```````````````````````````````` example(Deep HTML Parsing: 7) options(deep-html-parsing)
<div attr
    attr1="test"
    
>

    html text
    
</div>    

.
<div attr
    attr1="test"
<blockquote>
</blockquote>
<pre><code>html text
</code></pre>
</div>    
.
Document[0, 66]
  HtmlBlock[0, 27]
  BlockQuote[32, 33] marker:[32, 33, ">"]
  IndentedCodeBlock[39, 49]
  HtmlBlock[54, 65]
````````````````````````````````


```````````````````````````````` example(Deep HTML Parsing: 8) options(deep-html-parsing)
<p>par</p>
    <ul>
      <li>list item</li>
    </ul>
.
<p>par</p>
    <ul>
      <li>list item</li>
    </ul>
.
Document[0, 54]
  HtmlBlock[0, 54]
````````````````````````````````


```````````````````````````````` example(Deep HTML Parsing: 9) options(deep-html-parsing)
<br>some text
.
<p><br>some text</p>
.
Document[0, 13]
  Paragraph[0, 13]
    HtmlInline[0, 4] chars:[0, 4, "<br>"]
    Text[4, 13] chars:[4, 13, "some text"]
````````````````````````````````


## Directional punctuations

Directional punctuation delimiter parsing

```````````````````````````````` example Directional punctuations: 1
可以**foo()**可以
.
<p>可以**foo()**可以</p>
.
Document[0, 14]
  Paragraph[0, 14]
    Text[0, 13] chars:[0, 13, "可以**f … )**可以"]
````````````````````````````````


```````````````````````````````` example Directional punctuations: 2
可以**()foo()**可以
.
<p>可以**()foo()**可以</p>
.
Document[0, 16]
  Paragraph[0, 16]
    Text[0, 15] chars:[0, 15, "可以**( … )**可以"]
````````````````````````````````


```````````````````````````````` example Directional punctuations: 3
可以**()foo**可以
.
<p>可以**()foo**可以</p>
.
Document[0, 14]
  Paragraph[0, 14]
    Text[0, 13] chars:[0, 13, "可以**( … o**可以"]
````````````````````````````````


Directional punctuation delimiter parsing

```````````````````````````````` example(Directional punctuations: 4) options(directional-punctuation)
可以**foo()**可以
.
<p>可以<strong>foo()</strong>可以</p>
.
Document[0, 13]
  Paragraph[0, 13]
    Text[0, 2] chars:[0, 2, "可以"]
    StrongEmphasis[2, 11] textOpen:[2, 4, "**"] text:[4, 9, "foo()"] textClose:[9, 11, "**"]
      Text[4, 9] chars:[4, 9, "foo()"]
    Text[11, 13] chars:[11, 13, "可以"]
````````````````````````````````


```````````````````````````````` example(Directional punctuations: 5) options(directional-punctuation)
可以**()foo()**可以
.
<p>可以<strong>()foo()</strong>可以</p>
.
Document[0, 15]
  Paragraph[0, 15]
    Text[0, 2] chars:[0, 2, "可以"]
    StrongEmphasis[2, 13] textOpen:[2, 4, "**"] text:[4, 11, "()foo()"] textClose:[11, 13, "**"]
      Text[4, 11] chars:[4, 11, "()foo()"]
    Text[13, 15] chars:[13, 15, "可以"]
````````````````````````````````


```````````````````````````````` example(Directional punctuations: 6) options(directional-punctuation)
可以**()foo**可以
.
<p>可以<strong>()foo</strong>可以</p>
.
Document[0, 13]
  Paragraph[0, 13]
    Text[0, 2] chars:[0, 2, "可以"]
    StrongEmphasis[2, 11] textOpen:[2, 4, "**"] text:[4, 9, "()foo"] textClose:[9, 11, "**"]
      Text[4, 9] chars:[4, 9, "()foo"]
    Text[11, 13] chars:[11, 13, "可以"]
````````````````````````````````


```````````````````````````````` example(Directional punctuations: 7) options(directional-punctuation)
可以**foo(**可以
.
<p>可以**foo(**可以</p>
.
Document[0, 12]
  Paragraph[0, 12]
    Text[0, 12] chars:[0, 12, "可以**f … (**可以"]
````````````````````````````````


```````````````````````````````` example(Directional punctuations: 8) options(directional-punctuation)
可以**)foo(**可以
.
<p>可以**)foo(**可以</p>
.
Document[0, 13]
  Paragraph[0, 13]
    Text[0, 13] chars:[0, 13, "可以**) … (**可以"]
````````````````````````````````


```````````````````````````````` example(Directional punctuations: 9) options(directional-punctuation)
可以**)foo**可以
.
<p>可以**)foo**可以</p>
.
Document[0, 12]
  Paragraph[0, 12]
    Text[0, 12] chars:[0, 12, "可以**) … o**可以"]
````````````````````````````````


## Soft-Breaks in code

Multi-line code span in paragraph

```````````````````````````````` example(Soft-Breaks in code: 1) options(code-soft-breaks)
line ```{
"key1": "xxx",
"key2": [
"xxx",
"zzz"
]
} ```
.
<p>line <code>{
&quot;key1&quot;: &quot;xxx&quot;,
&quot;key2&quot;: [
&quot;xxx&quot;,
&quot;zzz&quot;
]
}</code></p>
.
Document[0, 55]
  Paragraph[0, 55]
    Text[0, 5] chars:[0, 5, "line "]
    Code[5, 55] textOpen:[5, 8, "```"] text:[8, 52, "{\n\"ke … y1\": \"xxx\",\n\"key2\": [\n\"xxx\",\n\"zzz\"\n]\n} "] textClose:[52, 55, "```"]
      Text[8, 9] chars:[8, 9, "{"]
      SoftLineBreak[9, 10]
      Text[10, 24] chars:[10, 24, "\"key1 … xxx\","]
      SoftLineBreak[24, 25]
      Text[25, 34] chars:[25, 34, "\"key2\": ["]
      SoftLineBreak[34, 35]
      Text[35, 41] chars:[35, 41, "\"xxx\","]
      SoftLineBreak[41, 42]
      Text[42, 47] chars:[42, 47, "\"zzz\""]
      SoftLineBreak[47, 48]
      Text[48, 49] chars:[48, 49, "]"]
      SoftLineBreak[49, 50]
      Text[50, 52] chars:[50, 52, "} "]
````````````````````````````````


```````````````````````````````` example(Soft-Breaks in code: 2) options(code-soft-break-spaces)
line ```{
"key1": "xxx",
"key2": [
"xxx",
"zzz"
]
} ```
.
<p>line <code>{ &quot;key1&quot;: &quot;xxx&quot;, &quot;key2&quot;: [ &quot;xxx&quot;, &quot;zzz&quot; ] }</code></p>
.
Document[0, 55]
  Paragraph[0, 55]
    Text[0, 5] chars:[0, 5, "line "]
    Code[5, 55] textOpen:[5, 8, "```"] text:[8, 52, "{\n\"ke … y1\": \"xxx\",\n\"key2\": [\n\"xxx\",\n\"zzz\"\n]\n} "] textClose:[52, 55, "```"]
      Text[8, 9] chars:[8, 9, "{"]
      SoftLineBreak[9, 10]
      Text[10, 24] chars:[10, 24, "\"key1 … xxx\","]
      SoftLineBreak[24, 25]
      Text[25, 34] chars:[25, 34, "\"key2\": ["]
      SoftLineBreak[34, 35]
      Text[35, 41] chars:[35, 41, "\"xxx\","]
      SoftLineBreak[41, 42]
      Text[42, 47] chars:[42, 47, "\"zzz\""]
      SoftLineBreak[47, 48]
      Text[48, 49] chars:[48, 49, "]"]
      SoftLineBreak[49, 50]
      Text[50, 52] chars:[50, 52, "} "]
````````````````````````````````


## Spec 0.28 Changes

```````````````````````````````` example Spec 0.28 Changes: 1
***foo***
.
<p><em><strong>foo</strong></em></p>
.
Document[0, 10]
  Paragraph[0, 10]
    Emphasis[0, 9] textOpen:[0, 1, "*"] text:[1, 8, "**foo**"] textClose:[8, 9, "*"]
      StrongEmphasis[1, 8] textOpen:[1, 3, "**"] text:[3, 6, "foo"] textClose:[6, 8, "**"]
        Text[3, 6] chars:[3, 6, "foo"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 2
_____foo_____
.
<p><em><strong><strong>foo</strong></strong></em></p>
.
Document[0, 14]
  Paragraph[0, 14]
    Emphasis[0, 13] textOpen:[0, 1, "_"] text:[1, 12, "____foo____"] textClose:[12, 13, "_"]
      StrongEmphasis[1, 12] textOpen:[1, 3, "__"] text:[3, 10, "__foo__"] textClose:[10, 12, "__"]
        StrongEmphasis[3, 10] textOpen:[3, 5, "__"] text:[5, 8, "foo"] textClose:[8, 10, "__"]
          Text[5, 8] chars:[5, 8, "foo"]
````````````````````````````````


spec 0.27 compatibility

```````````````````````````````` example(Spec 0.28 Changes: 3) options(spec-027)
***foo***
.
<p><strong><em>foo</em></strong></p>
.
Document[0, 9]
  Paragraph[0, 9]
    StrongEmphasis[0, 9] textOpen:[0, 2, "**"] text:[2, 7, "*foo*"] textClose:[7, 9, "**"]
      Emphasis[2, 7] textOpen:[2, 3, "*"] text:[3, 6, "foo"] textClose:[6, 7, "*"]
        Text[3, 6] chars:[3, 6, "foo"]
````````````````````````````````


```````````````````````````````` example(Spec 0.28 Changes: 4) options(spec-027)
_____foo_____
.
<p><strong><strong><em>foo</em></strong></strong></p>
.
Document[0, 13]
  Paragraph[0, 13]
    StrongEmphasis[0, 13] textOpen:[0, 2, "__"] text:[2, 11, "___foo___"] textClose:[11, 13, "__"]
      StrongEmphasis[2, 11] textOpen:[2, 4, "__"] text:[4, 9, "_foo_"] textClose:[9, 11, "__"]
        Emphasis[4, 9] textOpen:[4, 5, "_"] text:[5, 8, "foo"] textClose:[8, 9, "_"]
          Text[5, 8] chars:[5, 8, "foo"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 5
[link](foo(and(bar)))
.
<p><a href="foo(and(bar))">link</a></p>
.
Document[0, 22]
  Paragraph[0, 22]
    Link[0, 21] textOpen:[0, 1, "["] text:[1, 5, "link"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 20, "foo(and(bar))"] pageRef:[7, 20, "foo(and(bar))"] linkClose:[20, 21, ")"]
      Text[1, 5] chars:[1, 5, "link"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 6
[link](foo(and(bar))
.
<p>[link](foo(and(bar))</p>
.
Document[0, 21]
  Paragraph[0, 21]
    LinkRef[0, 6] referenceOpen:[0, 1, "["] reference:[1, 5, "link"] referenceClose:[5, 6, "]"]
      Text[1, 5] chars:[1, 5, "link"]
    Text[6, 20] chars:[6, 20, "(foo( … bar))"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 7
*foo [bar](/url)*
.
<p><em>foo <a href="/url">bar</a></em></p>
.
Document[0, 18]
  Paragraph[0, 18]
    Emphasis[0, 17] textOpen:[0, 1, "*"] text:[1, 16, "foo [bar](/url)"] textClose:[16, 17, "*"]
      Text[1, 5] chars:[1, 5, "foo "]
      Link[5, 16] textOpen:[5, 6, "["] text:[6, 9, "bar"] textClose:[9, 10, "]"] linkOpen:[10, 11, "("] url:[11, 15, "/url"] pageRef:[11, 15, "/url"] linkClose:[15, 16, ")"]
        Text[6, 9] chars:[6, 9, "bar"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 8
[link](/my uri)
.
<p>[link](/my uri)</p>
.
Document[0, 16]
  Paragraph[0, 16]
    LinkRef[0, 6] referenceOpen:[0, 1, "["] reference:[1, 5, "link"] referenceClose:[5, 6, "]"]
      Text[1, 5] chars:[1, 5, "link"]
    Text[6, 15] chars:[6, 15, "(/my uri)"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 9
[link](</my uri>)
.
<p>[link](&lt;/my uri&gt;)</p>
.
Document[0, 18]
  Paragraph[0, 18]
    LinkRef[0, 6] referenceOpen:[0, 1, "["] reference:[1, 5, "link"] referenceClose:[5, 6, "]"]
      Text[1, 5] chars:[1, 5, "link"]
    Text[6, 17] chars:[6, 17, "(</my … uri>)"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 10
[link](foo
bar)
.
<p>[link](foo
bar)</p>
.
Document[0, 16]
  Paragraph[0, 16]
    LinkRef[0, 6] referenceOpen:[0, 1, "["] reference:[1, 5, "link"] referenceClose:[5, 6, "]"]
      Text[1, 5] chars:[1, 5, "link"]
    Text[6, 10] chars:[6, 10, "(foo"]
    SoftLineBreak[10, 11]
    Text[11, 15] chars:[11, 15, "bar)"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 11
[link](<foo
bar>)
.
<p>[link](<foo
bar>)</p>
.
Document[0, 18]
  Paragraph[0, 18]
    LinkRef[0, 6] referenceOpen:[0, 1, "["] reference:[1, 5, "link"] referenceClose:[5, 6, "]"]
      Text[1, 5] chars:[1, 5, "link"]
    Text[6, 7] chars:[6, 7, "("]
    HtmlInline[7, 16] chars:[7, 16, "<foo\nbar>"]
    Text[16, 17] chars:[16, 17, ")"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 12
[link](<foo(and(bar)>)
.
<p><a href="foo(and(bar)">link</a></p>
.
Document[0, 23]
  Paragraph[0, 23]
    Link[0, 22] textOpen:[0, 1, "["] text:[1, 5, "link"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] urlOpen:[7, 8, "<"] url:[8, 20, "foo(and(bar)"] urlClose:[20, 21, ">"] pageRef:[8, 20, "foo(and(bar)"] linkClose:[21, 22, ")"]
      Text[1, 5] chars:[1, 5, "link"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 13
![foo ![bar](/url)](/url2)
.
<p><img src="/url2" alt="foo bar" /></p>
.
Document[0, 27]
  Paragraph[0, 27]
    Image[0, 26] textOpen:[0, 2, "!["] text:[2, 18, "foo ![bar](/url)"] textClose:[18, 19, "]"] linkOpen:[19, 20, "("] url:[20, 25, "/url2"] pageRef:[20, 25, "/url2"] linkClose:[25, 26, ")"]
      Text[2, 6] chars:[2, 6, "foo "]
      Image[6, 18] textOpen:[6, 8, "!["] text:[8, 11, "bar"] textClose:[11, 12, "]"] linkOpen:[12, 13, "("] url:[13, 17, "/url"] pageRef:[13, 17, "/url"] linkClose:[17, 18, ")"]
        Text[8, 11] chars:[8, 11, "bar"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 14
![foo [bar](/url)](/url2)
.
<p><img src="/url2" alt="foo bar" /></p>
.
Document[0, 26]
  Paragraph[0, 26]
    Image[0, 25] textOpen:[0, 2, "!["] text:[2, 17, "foo [bar](/url)"] textClose:[17, 18, "]"] linkOpen:[18, 19, "("] url:[19, 24, "/url2"] pageRef:[19, 24, "/url2"] linkClose:[24, 25, ")"]
      Text[2, 6] chars:[2, 6, "foo "]
      Link[6, 17] textOpen:[6, 7, "["] text:[7, 10, "bar"] textClose:[10, 11, "]"] linkOpen:[11, 12, "("] url:[12, 16, "/url"] pageRef:[12, 16, "/url"] linkClose:[16, 17, ")"]
        Text[7, 10] chars:[7, 10, "bar"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 15
[![moon](moon.jpg)](/uri)
.
<p><a href="/uri"><img src="moon.jpg" alt="moon" /></a></p>
.
Document[0, 26]
  Paragraph[0, 26]
    Link[0, 25] textOpen:[0, 1, "["] text:[1, 18, "![moon](moon.jpg)"] textClose:[18, 19, "]"] linkOpen:[19, 20, "("] url:[20, 24, "/uri"] pageRef:[20, 24, "/uri"] linkClose:[24, 25, ")"]
      Image[1, 18] textOpen:[1, 3, "!["] text:[3, 7, "moon"] textClose:[7, 8, "]"] linkOpen:[8, 9, "("] url:[9, 17, "moon.jpg"] pageRef:[9, 17, "moon.jpg"] linkClose:[17, 18, ")"]
        Text[3, 7] chars:[3, 7, "moon"]
````````````````````````````````


However, links may not contain other links, at any level of nesting.

```````````````````````````````` example Spec 0.28 Changes: 16
[foo [bar](/uri)](/uri)
.
<p>[foo <a href="/uri">bar</a>](/uri)</p>
.
Document[0, 24]
  Paragraph[0, 24]
    Text[0, 5] chars:[0, 5, "[foo "]
    Link[5, 16] textOpen:[5, 6, "["] text:[6, 9, "bar"] textClose:[9, 10, "]"] linkOpen:[10, 11, "("] url:[11, 15, "/uri"] pageRef:[11, 15, "/uri"] linkClose:[15, 16, ")"]
      Text[6, 9] chars:[6, 9, "bar"]
    Text[16, 23] chars:[16, 23, "](/uri)"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 17
[foo *[bar [baz](/uri)](/uri)*](/uri)
.
<p>[foo <em>[bar <a href="/uri">baz</a>](/uri)</em>](/uri)</p>
.
Document[0, 38]
  Paragraph[0, 38]
    Text[0, 5] chars:[0, 5, "[foo "]
    Emphasis[5, 30] textOpen:[5, 6, "*"] text:[6, 29, "[bar [baz](/uri)](/uri)"] textClose:[29, 30, "*"]
      Text[6, 7] chars:[6, 7, "["]
      Text[7, 11] chars:[7, 11, "bar "]
      Link[11, 22] textOpen:[11, 12, "["] text:[12, 15, "baz"] textClose:[15, 16, "]"] linkOpen:[16, 17, "("] url:[17, 21, "/uri"] pageRef:[17, 21, "/uri"] linkClose:[21, 22, ")"]
        Text[12, 15] chars:[12, 15, "baz"]
      Text[22, 29] chars:[22, 29, "](/uri)"]
    Text[30, 37] chars:[30, 37, "](/uri)"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 18
![[[foo](uri1)](uri2)](uri3)
.
<p><img src="uri3" alt="[foo](uri2)" /></p>
.
Document[0, 29]
  Paragraph[0, 29]
    Image[0, 28] textOpen:[0, 2, "!["] text:[2, 21, "[[foo](uri1)](uri2)"] textClose:[21, 22, "]"] linkOpen:[22, 23, "("] url:[23, 27, "uri3"] pageRef:[23, 27, "uri3"] linkClose:[27, 28, ")"]
      Text[2, 3] chars:[2, 3, "["]
      Link[3, 14] textOpen:[3, 4, "["] text:[4, 7, "foo"] textClose:[7, 8, "]"] linkOpen:[8, 9, "("] url:[9, 13, "uri1"] pageRef:[9, 13, "uri1"] linkClose:[13, 14, ")"]
        Text[4, 7] chars:[4, 7, "foo"]
      Text[14, 21] chars:[14, 21, "](uri2)"]
````````````````````````````````


```````````````````````````````` example Spec 0.28 Changes: 19
[link](<foo(and(bar)>)
.
<p><a href="foo(and(bar)">link</a></p>
.
Document[0, 23]
  Paragraph[0, 23]
    Link[0, 22] textOpen:[0, 1, "["] text:[1, 5, "link"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] urlOpen:[7, 8, "<"] url:[8, 20, "foo(and(bar)"] urlClose:[20, 21, ">"] pageRef:[8, 20, "foo(and(bar)"] linkClose:[21, 22, ")"]
      Text[1, 5] chars:[1, 5, "link"]
````````````````````````````````


Line source tracing should not split links and should chunk text content into their own spans

```````````````````````````````` example(Spec 0.28 Changes: 20) options(src-pos, src-pos-lines)
[hello
world](#test)
.
<p md-pos="0-20"><span md-pos="0-6"><a href="#test" md-pos="0-20"><span md-pos="0-6">hello</span>
<span md-pos="7-12">world</span></a></span></p>
.
Document[0, 20]
  Paragraph[0, 20]
    Link[0, 20] textOpen:[0, 1, "["] text:[1, 12, "hello\nworld"] textClose:[12, 13, "]"] linkOpen:[13, 14, "("] url:[14, 19, "#test"] pageRef:[14, 14] anchorMarker:[14, 15, "#"] anchorRef:[15, 19, "test"] linkClose:[19, 20, ")"]
      Text[1, 6] chars:[1, 6, "hello"]
      SoftLineBreak[6, 7]
      Text[7, 12] chars:[7, 12, "world"]
````````````````````````````````


```````````````````````````````` example(Spec 0.28 Changes: 21) options(src-pos, src-pos-lines)
[hello **bold
  world**](#test)
.
<p md-pos="0-31"><span md-pos="0-13"><a href="#test" md-pos="0-31"><span md-pos="0-13">hello <strong>bold</strong></span>
<span md-pos="16-21"><strong>world</strong></span></a></span></p>
.
Document[0, 31]
  Paragraph[0, 31]
    Link[0, 31] textOpen:[0, 1, "["] text:[1, 23, "hello **bold\nworld**"] textClose:[23, 24, "]"] linkOpen:[24, 25, "("] url:[25, 30, "#test"] pageRef:[25, 25] anchorMarker:[25, 26, "#"] anchorRef:[26, 30, "test"] linkClose:[30, 31, ")"]
      Text[1, 7] chars:[1, 7, "hello "]
      StrongEmphasis[7, 23] textOpen:[7, 9, "**"] text:[9, 21, "bold\nworld"] textClose:[21, 23, "**"]
        Text[9, 13] chars:[9, 13, "bold"]
        SoftLineBreak[13, 14]
        Text[16, 21] chars:[16, 21, "world"]
````````````````````````````````


## Custom List Markers

```````````````````````````````` example(Custom List Markers: 1) options(custom-list-marker)
* list item
+ lazy continuation 
- lazy continuation 
.
<ul>
  <li>list item
  + lazy continuation
  - lazy continuation</li>
</ul>
.
Document[0, 53]
  BulletList[0, 53] isTight
    BulletListItem[0, 53] open:[0, 1, "*"] isTight
      Paragraph[2, 53]
        Text[2, 11] chars:[2, 11, "list item"]
        SoftLineBreak[11, 12]
        Text[12, 31] chars:[12, 31, "+ laz … ation"]
        SoftLineBreak[32, 33]
        Text[33, 52] chars:[33, 52, "- laz … ation"]
````````````````````````````````


```````````````````````````````` example(Custom List Markers: 2) options(custom-list-marker)
* list item
/ another list item
/ another list item 2
.
<ul>
  <li>list item</li>
</ul>
<ul>
  <li>another list item</li>
  <li>another list item 2</li>
</ul>
.
Document[0, 53]
  BulletList[0, 12] isTight
    BulletListItem[0, 12] open:[0, 1, "*"] isTight
      Paragraph[2, 12]
        Text[2, 11] chars:[2, 11, "list item"]
  BulletList[12, 53] isTight
    OrderedListItem[12, 32] open:[12, 13, "/"] isTight
      Paragraph[14, 32]
        Text[14, 31] chars:[14, 31, "anoth …  item"]
    OrderedListItem[32, 53] open:[32, 33, "/"] isTight
      Paragraph[34, 53]
        Text[34, 53] chars:[34, 53, "anoth … tem 2"]
````````````````````````````````


